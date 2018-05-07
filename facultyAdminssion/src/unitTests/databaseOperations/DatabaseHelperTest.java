package unitTests.databaseOperations;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

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
	
	@Test
	void testCreateTable_tableDidNotExistPreviously() {
		//delete the file if it already exist
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

	/*@Test
	void testDeleteQuery() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectQuery() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectWhereQuery() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateQuery() {
		fail("Not yet implemented");
	}*/

	public static void createFile(String fileName) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName, false);
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
