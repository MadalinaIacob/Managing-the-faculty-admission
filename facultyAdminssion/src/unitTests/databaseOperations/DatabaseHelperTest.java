package unitTests.databaseOperations;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import databaseOperations.DatabaseHelper;

/**
 * 
 * @author andre
 *	In lucru, a se vedea linkul https://docs.google.com/document/d/1CLcEc31n6SWkSICaTTf-Yx2cT1cuyyuEyIf39j6duKA/edit?usp=sharing
 *
 */
class DatabaseHelperTest {

	static String testTableName = "test_table_name12345.txt";
	static String[] testColumns = new String[] {"test_col1", "test_col2"};
	static String rowSeparator = "\r\n";
	
	@Test
	void testCreateTable_tableDidNotExistPreviously() {
		//delete the file if it already exists
		File file = new File(testTableName);
		if(file.exists()) {
			file.delete();
		}
		
		//create the table
		DatabaseHelper dbh = new DatabaseHelper();
		dbh.createTable(testTableName, testColumns);

		file = new File(testTableName);
		assertTrue(file.exists());

		//add the column names
		StringBuilder sb = new StringBuilder();
		for(String column: testColumns) {
			sb.append(column).append("-");
		}
		sb.append("\r\n");
		String expectedResult = sb.toString();
		String actualResult = readFile(testTableName);
		
		System.out.println("**"+expectedResult+"***"+actualResult+"**");
		assertEquals(expectedResult, actualResult);
		
		file.delete();
	}

	@Test
	void testCreateTable_tableAlreadyExists() {
		//create a table 
		String existingContent = "hard to guess";
		try {
			FileWriter fw = new FileWriter(testTableName, false);
			fw.write(existingContent);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//try to create again the same table, but do not change anything
		DatabaseHelper dbh = new DatabaseHelper();
		dbh.createTable(testTableName, testColumns);

		File file = new File(testTableName);
		assertTrue(file.exists());

		//verify the content to be as in the first table
		String actualResult = readFile(testTableName);
		
		System.out.println("**"+existingContent+"***"+actualResult+"**");
		assertEquals(existingContent, actualResult);
		
		file.delete();
	}
	
	@Test
	void testInsertQuery_insertWhateverYouReceive() {
		String existingContent = "hard to guess";
		try {
			FileWriter fw = new FileWriter(testTableName, false);
			fw.write(existingContent);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] toInsert = new String[] {"test_col1", "test_col2"};
		DatabaseHelper dbh = new DatabaseHelper();
		dbh.insertQuery(testTableName, toInsert);
		
		String expectedContent = existingContent;
		for(String cont: toInsert) {
			expectedContent+= cont+"-";
		}
		expectedContent+="\r\n";
		
		String actualContent = readFile(testTableName);
		System.out.println("testInsertQuery_:\nActualContent: *" + actualContent+"*, expectedContent: *"+expectedContent+"* ");
		
		assertEquals(expectedContent, actualContent);
	}

	@Test
	void testDeleteQuery_whereAge19_2Rows() {
		String columnNames="id-name-age-isStudent";
		String row1 = "1-Ana-19-yes";
		String row2 = "2-Mihai-20-yes";
		String row3 = "3-Doru-19-no";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		DatabaseHelper dbh = new DatabaseHelper();
		dbh.deleteQuery(testTableName, "age", "19");
		
		String expectedResult = columnNames + rowSeparator + 
				row2 + rowSeparator;
		String actualResult = readFile(testTableName);
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void testDeleteQuery_whereAge18_1Row() {
		String columnNames="id-name-age-isStudent";
		String row1 = "1-Ana-18-yes";
		String row2 = "2-Mihai-20-yes";
		String row3 = "18-Doru-23-no";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		DatabaseHelper dbh = new DatabaseHelper();
		dbh.deleteQuery(testTableName, "age", "18");
		
		String expectedResult = columnNames + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		String actualResult = readFile(testTableName);
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testDeleteQuery_columnValueDoesNotExist() {
		String columnNames="id-name-age-isStudent";
		String row1 = "1-Ana-19-yes";
		String row2 = "2-Mihai-20-yes";
		String row3 = "3-Doru-23-no";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		DatabaseHelper dbh = new DatabaseHelper();
		dbh.deleteQuery(testTableName, "name", "Oana");
		
		String actualResult = readFile(testTableName);
		
		assertEquals(tableContent, actualResult);
	}
	
	@Test
	void testDeleteQuery_columnNameDoesNotExist() {
		String columnNames="id-name-age-isStudent";
		String row1 = "1-Ana-19-yes";
		String row2 = "2-Mihai-20-yes";
		String row3 = "3-Doru-23-no";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		DatabaseHelper dbh = new DatabaseHelper();
		dbh.deleteQuery(testTableName, "knowsToDance", "Oana");
		
		String actualResult = readFile(testTableName);
		
		System.out.println("tableContent -*"+tableContent+"*-");
		System.out.println("actualResult -*"+actualResult+"*-");
		assertEquals(tableContent, actualResult);
	}
	
	@Test
	void testSelectQuery_normalTable() {
		String columnNames="id-name-age-isStudent";
		String row1 = "1-Ana-19-yes";
		String row2 = "2-Mihai-20-yes";
		String row3 = "3-Doru-23-no";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		DatabaseHelper dbh = new DatabaseHelper();
		List<List<String>> actualResult = dbh.selectQuery(testTableName);
		
		List<List<String>> expectedResult = Arrays.asList(
				Arrays.asList("1", "Ana", "19", "yes"),
				Arrays.asList("2", "Mihai", "20", "yes"),
				Arrays.asList("3", "Doru", "23", "no")
				);
		
		assertEquals(actualResult, expectedResult);
	}

	@Test
	void testSelectQuery_differentLengthRows() {
		String columnNames="id-name-age-isStudent";
		String row1 = "1-Ana-19-yes-Ana";
		String row2 = "2-Mihai--yes";
		String row3 = "3-null-23";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		DatabaseHelper dbh = new DatabaseHelper();
		List<List<String>> actualResult = dbh.selectQuery(testTableName);
		
		List<List<String>> expectedResult = Arrays.asList(
				Arrays.asList("1", "Ana", "19", "yes", "Ana"),
				Arrays.asList("2", "Mihai", "", "yes"),
				Arrays.asList("3", "null", "23")
				);
		
		assertEquals(actualResult, expectedResult);
	}
	
	@Test
	void testSelectWhereQuery_forIsStudentYes_1listContaining1rows() {
		String columnNames="id-name-age-isStudent";
		String row1 = "1-Ana-19-yes";
		String row2 = "2-Mihai-20-yes";
		String row3 = "3-Doru-23-no";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		DatabaseHelper dbh = new DatabaseHelper();
		String[] columnName = {"id", "name", "isStudent"};
		ArrayList<String> actualResult = dbh.selectWhereQuery(testTableName, columnName, "id", "3");
		
		List<String> expectedResult = Arrays.asList("3", "Doru", "no");
		
		assertEquals(actualResult, expectedResult);
	}
	
	@Test
	void testSelectWhereQuery_forIsStudentYes_1listContaining2rows() {
		String columnNames="id-name-age-isStudent";
		String row1 = "1-Ana-19-yes";
		String row2 = "2-Mihai-20-yes";
		String row3 = "3-Doru-23-no";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		DatabaseHelper dbh = new DatabaseHelper();
		String[] columnName = {"id", "name", "isStudent"};
		ArrayList<String> actualResult = dbh.selectWhereQuery(testTableName, columnName, "isStudent", "yes");
		
		List<String> expectedResult = Arrays.asList("1", "Ana", "yes", "2", "Mihai", "yes");
		
		assertEquals(actualResult, expectedResult);
	}
	
	@Test
	void testSelectWhereQuery_forIsStudentWhat_emptyList() {
		String columnNames="id-name-age-isStudent";
		String row1 = "1-Ana-19-yes";
		String row2 = "2-Mihai-20-yes";
		String row3 = "3-Doru-23-no";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		DatabaseHelper dbh = new DatabaseHelper();
		String[] columnName = {"id", "name", "isStudent"};
		ArrayList<String> actualResult = dbh.selectWhereQuery(testTableName, columnName, "isStudent", "what?");
		
		List<String> expectedResult = Arrays.asList();
		
		assertEquals(actualResult, expectedResult);
	}
	

	@Test
	void testUpdateQuery_setAge21whereAge20_1column() {
		String columnNames="id-name-age-isStudent-";
		String row1 = "1-Ana-19-yes-";
		String row2 = "2-Mihai-20-yes-";
		String row3 = "3-Doru-23-no-";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		DatabaseHelper dbh = new DatabaseHelper();
		String[] newColumnValue = {"2", "Mihai", "21", "yes"};
		dbh.updateQuery(testTableName, "age", "20", newColumnValue);
		
		String actualResult = readFile(testTableName);
		String expectedResult = tableContent.replaceAll("20", "21");
		assertEquals(actualResult, expectedResult);
	}

	public static void createFile(String fileName, String content) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName, false);
			fw.append(content);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readFile(String fileName) {
		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();

			String str = new String(data, "UTF-8");
			return str;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
