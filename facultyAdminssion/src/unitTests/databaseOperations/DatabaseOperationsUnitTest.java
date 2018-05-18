package unitTests.databaseOperations;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import facultyAdminssion.DatabaseOperations;
import facultyAdminssion.Student;

class DatabaseOperationsUnitTest {

	static Student s = new Student();
	DatabaseOperations databaseOperations = new DatabaseOperations();

	@BeforeAll
	static void setUp() throws Exception {
		s.setTxtStudentname("test test"); s.setTxtMariagestudentname("test");
		s.setTxtBirthDay("21"); s.setTxtBirthMonth("04"); s.setTxtBirthyear("2000");
		s.setTxtCnp("1930124347208");
		s.setTxtBirthplace("Localitate"); s.setTxtBirthcity("oras"); s.setTxtBirthcountry("tara");
		s.setTxtNationality("test"); s.setTxtCitizenship("test");
		s.setTxtPlace("test"); s.setTxtCity("test"); s.setTxtStreet("test");
		s.setTxtEmail("test@gmail.com"); s.setTxtPhone("0749867245");
		s.setTxtFathername("test"); s.setTxtMothername("test");
		s.setTxtOldfaculty("test");
		s.setTxtGradeexam("9.50"); s.setTxtGradeinfo("10"); s.setTxtGrademath("5");
		s.setMarried(false); s.setNotMarried(true);
		s.setTxtGradeadmExam("6.50");
	}

	@Test
	void testInsertStudent() throws ParseException{
		String file = "D:\\madalina\\Facultate\\Master An 1\\Semestrul 2\\CSS\\Managing-the-faculty-admission\\facultyAdminssion\\students.txt";
		String expectedResult = "nume-nume casatorie-data nasterii-cnp-adresa nastere-nationalitate-cetatenie-adresa actuala-email-telefon-numele tatalui-numele mamei-stare civila-nota bac-nota info-nota mate-nota examen-";
		expectedResult = expectedResult + readFile(file);
		String st = (s.getTxtStudentname()+"-"+
					s.getTxtMariagestudentname()+"-"+
				s.getTxtBirthDay()+"/"+
					s.getTxtBirthMonth()+"/"+
				s.getTxtBirthyear()+"-"+
					s.getTxtCnp()+"-"+
				s.getTxtBirthplace()+" "+
					s.getTxtBirthcity()+" "+
				s.getTxtBirthcountry()+"-"+
					s.getTxtNationality()+"-"+
				s.getTxtCitizenship()+"-"+
					s.getTxtPlace()+" "+
				s.getTxtCity()+" "+
					s.getTxtStreet()+" "+"null null null null null-"+
				s.getTxtEmail()+"-"+
					s.getTxtPhone()+"-"+
				s.getTxtFathername()+"-"+
					s.getTxtMothername()+"-"+
					s.isMarried()+"-"+
				s.getTxtOldfaculty()+"-"+
					s.getTxtGradeexam()+"-"+
				s.getTxtGradeinfo()+"-"+
					s.getTxtGrademath()+"-"+
				s.getTxtGradeadmExam()+"-\r\n").toString();
		expectedResult = expectedResult+"\r\n"+st;
		databaseOperations.insertStudent(s);
		String actualResult = readFile(file);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testSelectStudentB() {
		String file = "D:\\madalina\\Facultate\\Master An 1\\Semestrul 2\\CSS\\Managing-the-faculty-admission\\facultyAdminssion\\studentsB.txt";
		String expectedResult = readFile(file);
		ArrayList<List<String>> result = databaseOperations.selectStudentsBT(1);
		StringBuilder sb = new StringBuilder();
		for(List<String> row : result) {
			for(String column : row) {
				sb.append(column).append("-");
			}
			sb.append("\r\n");
		}
		String actualResult =  "nume-medie-\r\n" + sb.toString();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testSelectStudentT() {
		String file = "D:\\madalina\\Facultate\\Master An 1\\Semestrul 2\\CSS\\Managing-the-faculty-admission\\facultyAdminssion\\studentsT.txt";
		String expectedResult = readFile(file);
		ArrayList<List<String>> result = databaseOperations.selectStudentsBT(0);
		StringBuilder sb = new StringBuilder();
		for(List<String> row : result) {
			for(String column : row) {
				sb.append(column).append("-");
			}
			sb.append("\r\n");
		}
		String actualResult = "nume-medie-\r\n" + sb.toString();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testSelectStudentBT() {
		String file = "D:\\madalina\\Facultate\\Master An 1\\Semestrul 2\\CSS\\Managing-the-faculty-admission\\facultyAdminssion\\studentsB.txt";
		String expectedResult = readFile(file);
		ArrayList<List<String>> result = databaseOperations.selectStudentsBT(2);
		  Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	        	 databaseOperations.selectStudentsBT(2);
	    		
	        });
	        assertEquals("Parametru incorect", exception.getMessage());
	}
	
	@Test
	void testSelectStudents() {
		String file = "D:\\madalina\\Facultate\\Master An 1\\Semestrul 2\\CSS\\Managing-the-faculty-admission\\facultyAdminssion\\students.txt";
		String expectedResult = readFile(file);
		ArrayList<List<String>> result = databaseOperations.selectStudents();
		StringBuilder sb = new StringBuilder();
		for(List<String> row : result) {
			for(String column : row) {
				sb.append(column).append("-");
			}
			sb.append("\r\n");
		}
		String actualResult = sb.toString();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test 
	void testUpdateStudent() {
		String file = "D:\\madalina\\Facultate\\Master An 1\\Semestrul 2\\CSS\\Managing-the-faculty-admission\\facultyAdminssion\\students.txt";
		String expectedResult = readFile(file);
		String st = "testUpdate"+"-"+
					s.getTxtMariagestudentname()+"-"+
				s.getTxtBirthDay()+"-"+
					s.getTxtBirthMonth()+"-"+
				s.getTxtBirthyear()+"-"+
					s.getTxtCnp()+"-"+
				s.getTxtBirthplace()+"-"+
					s.getTxtBirthcity()+"-"+
				s.getTxtBirthcountry()+"-"+
					s.getTxtNationality()+"-"+
				s.getTxtCitizenship()+"-"+
					s.getTxtPlace()+"-"+
				s.getTxtCity()+"-"+
					s.getTxtStreet()+"-"+
				s.getTxtEmail()+"-"+
					s.getTxtPhone()+"-"+
				s.getTxtFathername()+"-"+
					s.getTxtMothername()+"-"+
				s.getTxtOldfaculty()+"-"+
					s.getTxtGradeexam()+"-"+
				s.getTxtGradeinfo()+"-"+
					s.getTxtGrademath()+"-"+
				s.isMarried()+"-"+
					s.isNotMarried()+"-"+
				s.getTxtGradeadmExam();
		expectedResult += st;
		databaseOperations.insertStudent(s);
		databaseOperations.updateStudent(s.getTxtCnp(),"nume","testUpdate");
		String actualResult = readFile(file);
		assertEquals(expectedResult, actualResult);
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
