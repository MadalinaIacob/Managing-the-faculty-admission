package unitTests.databaseOperations;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import databaseOperations.DatabaseHelper;

/**
 * 
 * @author andre
 *	A se vedea https://docs.google.com/document/d/1CLcEc31n6SWkSICaTTf-Yx2cT1cuyyuEyIf39j6duKA/edit?usp=sharing
 *
 */
@DisplayName("Test cases for DatabaseHelper implementation of DatabaseManager interface")
class DatabaseHelperTest {

	static String testTableName = "test_table_name12345.txt";
	static String[] testColumns = new String[] {"test_col1", "test_col2"};
	static String rowSeparator = "\r\n";
	static String columnSeparator = "-";
	static DatabaseHelper dbh;
	static int id;
	
	@BeforeAll
    static void initAll() {
		dbh = new DatabaseHelper();
    }
	
	@BeforeEach
    void init() {
		String columnNames="id-name-age-isStudent-";
		String row1 = "1-Ana-19-yes-";
		String row2 = "2-Mihai-20-yes-";
		String row3 = "3-Doru-19-no-";
		String tableContent = columnNames + rowSeparator 
				+ row1 + rowSeparator 
				+ row2 + rowSeparator 
				+ row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
    }
	
	@AfterEach
    void tearDown() {
		File file = new File(testTableName);
		if(file.exists()) {
			file.delete();
		}
    }
	
	@Test
	void testCreateTable__Should_CreateTableAndInsertTheColumnNames_When_TableDoesNotExist() {
		//sterge tabelul daca exista deja
		File file = new File(testTableName);
		if(file.exists()) {
			file.delete();
		}
		
		//creeaza tabelul
		dbh.createTable(testTableName, testColumns);

		file = new File(testTableName);
		assertTrue(file.exists());

		//insereaza numele coloanelor
		StringBuilder sb = new StringBuilder();
		for(String column: testColumns) {
			sb.append(column).append(columnSeparator);
		}
		sb.append(rowSeparator);
		String expectedResult = sb.toString();
		String actualResult = readFile(testTableName);
		
		assertEquals(expectedResult, actualResult,  "createTable ar trebui sa creeze un fisier nou si sa insereze numele coloanelor");
	}

	@Test
	@DisplayName("Nu altera tabelul incercand sa il creezi a doua oara")
	void testCreateTable__Should_DoNothing_When_TableAlreadyExist() {
		String existingContent = readFile(testTableName);
		//incearca sa creeze tabelul, stim ca exista deja
		dbh.createTable(testTableName, testColumns);
		File file = new File(testTableName);
		assertTrue(file.exists());

		//continutul tabelului existent ar trebui sa fie nealterat
		String actualResult = readFile(testTableName);
		assertEquals(existingContent, actualResult,  "createTable ar trebui sa nu altereze un tabel cu acelasi nume deja existent");
		
		file.delete();
	}
	
	@Test
	void testInsertQuery__Should_InsertNewLineContainingTheValuesFromList_When_InputListSizeEqualsTableColumnsSize() {
		String existingContent = readFile(testTableName);
		
		String[] toInsert = new String[] {"test_col1", "test_col2", "test_col3"};
		dbh.insertQuery(testTableName, toInsert);
		
		String expectedContent = existingContent;
		for(String cont: toInsert) {
			expectedContent+= cont+"-";
		}
		expectedContent+=rowSeparator;
		
		String actualContent = readFile(testTableName);
		
		assertEquals(expectedContent, actualContent, "InsertQuery insereaza o noua inregistrare in fisier");
	}
	
	@Test
    void testInsertQuery__Should_ThrowException_When_InputListSizeIsSmallerThanTableColumnsSize() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            //throw new IllegalArgumentException("a message");
        	String[] toInsert = new String[] {"test_col3"};
    		dbh.insertQuery(testTableName, toInsert);
        });
        assertEquals("Randul de inserat are MAI PUTINE valori decat se asteapta.", exception.getMessage());
    }
	
	@Test
    void testInsertQuery__Should_ThrowException_When_InputListSizeIsBiggerThanTableColumnsSize() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
        	String[] toInsert = new String[] {"test_col1", "test_col2", "test_col3", "test_col4", "test_col5"};
    		dbh.insertQuery(testTableName, toInsert);
        });
        assertEquals("Randul de inserat are MAI MULTE valori decat se asteapta.", exception.getMessage());
    }

	@RepeatedTest(10)
    void testInsertQuery__Should_Insert10Rows_When_InputListIsOK(RepetitionInfo repetitionInfo) {
		id = repetitionInfo.getCurrentRepetition();
		String[] columnsName = {"id"+id, "name"+id, "age"+id, ((id%2==0)?"yes":"no")};
		dbh.insertQuery(testTableName, columnsName);
		
		String result = readFile(testTableName);
		System.out.println();
		assertAll(result,
	            () -> assertTrue(result.contains("id"+id)),
	            () -> assertTrue(result.contains("age"+id)),
	            () -> assertTrue(result.contains("name"+id))
	        );
		int countRows = result.length() - result.replace(rowSeparator, "").length();
        assertTrue(countRows>= id);
    }

	@Test
	void testDeleteQuery__Should_Delete2Rows_When_ageEquals19() {
		String columnNames="id-name-age-isStudent-";
		String row2 = "2-Mihai-20-yes-";
		
		dbh.deleteQuery(testTableName, "age", "19");
		
		String expectedResult = columnNames + rowSeparator + 
				row2 + rowSeparator;
		String actualResult = readFile(testTableName);
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void testDeleteQuery__Should_Delete1Row_When_ageEquals20() {
		String columnNames="id-name-age-isStudent-";
		String row1 = "1-Ana-19-yes-";
		String row3 = "3-Doru-19-no-";
		
		dbh.deleteQuery(testTableName, "age", "20");
		
		String expectedResult = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row3 + rowSeparator;
		String actualResult = readFile(testTableName);
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testDeleteQuery__Should_NotDeleteRows_When_noRowsHaveNameOana() {
		String columnNames="id-name-age-isStudent-";
		String row1 = "1-Ana-19-yes-";
		String row2 = "2-Mihai-20-yes-";
		String row3 = "3-Doru-23-no-";
		String tableContent = columnNames + rowSeparator 
				+ row1 + rowSeparator 
				+ row2 + rowSeparator 
				+ row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		dbh.deleteQuery(testTableName, "name", "Oana");
		
		String actualResult = readFile(testTableName);
		
		assertEquals(tableContent, actualResult);
	}
	
	@Test
    void testDeleteQuery__Should_ThrowException_When_columnNameDoesNotExist() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
        	dbh.deleteQuery(testTableName, "knowsToDance", "Oana");
        });
        assertEquals("Nu exista in tabel coloana indicata.", exception.getMessage());
    }
	
	@Test
	void testSelectQuery__Should_ReturnAllTheValues_When_TableExists() {
		List<List<String>> actualResult = dbh.selectQuery(testTableName);
		
		List<List<String>> expectedResult = Arrays.asList(
				Arrays.asList("1", "Ana", "19", "yes"),
				Arrays.asList("2", "Mihai", "20", "yes"),
				Arrays.asList("3", "Doru", "19", "no")
				);
		
		assertEquals(actualResult, expectedResult, "SelectQuery ar trebui sa returneze toate valorile din tabel");
	}
	
	@Test
	void testSelectQuery__Should_ReturnAllTheValues_When_TableHasDifferentRowLengths() {
		String columnNames="id-name-age-isStudent-";
		String row1 = "1-Ana-19-yes-Ana-";
		String row2 = "2-Mihai--yes-";
		String row3 = "3-null-23-";
		String tableContent = columnNames + rowSeparator + 
				row1 + rowSeparator + 
				row2 + rowSeparator + 
				row3 + rowSeparator;
		
		createFile(testTableName, tableContent);
		
		List<List<String>> actualResult = dbh.selectQuery(testTableName);
		
		List<List<String>> expectedResult = Arrays.asList(
				Arrays.asList("1", "Ana", "19", "yes", "Ana"),
				Arrays.asList("2", "Mihai", "", "yes"),
				Arrays.asList("3", "null", "23")
				);
		
		assertEquals(actualResult, expectedResult, "SelectQuery ar trebui sa returneze toate valorile din tabel");
	}
	
	@Test
	void testSelectWhereQuery__Should_ReturnIndicatedColumnValues_When_1RowSelected() {
		String[] columnName = {"id", "name", "isStudent"};
		ArrayList<String> actualResult = dbh.selectWhereQuery(testTableName, columnName, "id", "3");
		
		List<String> expectedResult = Arrays.asList("3", "Doru", "no");
		
		assertEquals(actualResult, expectedResult);
	}
	
	@Test
	void testSelectWhereQuery__Should_ReturnIndicatedColumnValues_When_2RowsSelected() {
		String[] columnName = {"id", "name", "isStudent"};
		ArrayList<String> actualResult = dbh.selectWhereQuery(testTableName, columnName, "isStudent", "yes");
		
		List<String> expectedResult = Arrays.asList("1", "Ana", "yes", "2", "Mihai", "yes");
		
		assertEquals(actualResult, expectedResult);
	}
	
	@Test
	void testSelectWhereQuery__Should_ReturnEmptyList_When_NoRowsSelected() {
		String[] columnName = {"id", "name", "isStudent"};
		ArrayList<String> actualResult = dbh.selectWhereQuery(testTableName, columnName, "isStudent", "what?");
		
		List<String> expectedResult = Arrays.asList();
		
		assertEquals(actualResult, expectedResult);
	}
	
	@Test
    void testSelectWhereQuery__Should_ThrowException_When_SelectedColumnNameDoesNotExist() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
        	String[] columnName = {"id", "cat", "isStudent"};
    		dbh.selectWhereQuery(testTableName, columnName, "isStudent", "yes");
    		
        });
        assertEquals("Tabelul nu contine coloana ceruta in extractie.", exception.getMessage());
    }
	
	@Test
    void testSelectWhereQuery__Should_ThrowException_When_SelectionColumnNameDoesNotExist() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
        	String[] columnName = {"id", "age", "isStudent"};
    		dbh.selectWhereQuery(testTableName, columnName, "adress", "iasi");
    		
        });
        assertEquals("Tabelul nu contine coloana indicata pentru triere.", exception.getMessage());
    }
	
	@Test
	void testUpdateQuery__Should_Update1Column_When_1ColumnIndicated() {
		String tableContent = readFile(testTableName);
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
