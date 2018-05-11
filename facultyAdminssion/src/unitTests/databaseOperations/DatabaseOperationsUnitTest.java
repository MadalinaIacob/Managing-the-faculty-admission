package unitTests.databaseOperations;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import facultyAdminssion.DatabaseOperations;
import facultyAdminssion.Student;

class DatabaseOperationsUnitTest {

	static Student s = new Student();
	DatabaseOperations databaseOperations = new DatabaseOperations();

	@BeforeAll
	void setUp() throws Exception {
		s.setTxtStudentname("test test"); s.setTxtMariagestudentname("test");
		s.setTxtBirthDay("21"); s.setTxtBirthMonth("04"); s.setTxtBirthyear("2000");
		s.setTxtCnp("1930124347208");
		s.setTxtBirthplace("Localitate"); s.setTxtBirthcity("oras"); s.setTxtBirthcountry("tara");
		s.setTxtNationality("test"); s.setTxtCitizenship("test");
		s.setTxtPlace("test"); s.setTxtCity("test"); s.setTxtStreet("test");
		s.setTxtNr("12"); s.setTxtBlock("A"); s.setTxtSc("B"); s.setTxtEt("5"); s.setTxtAp("2");
		s.setTxtEmail("test@gmail.com"); s.setTxtPhone("0749867245");
		s.setTxtFathername("test"); s.setTxtMothername("test");
		s.setTxtOldfaculty("test");
		s.setTxtGradeexam("9.50"); s.setTxtGradeinfo("10"); s.setTxtGrademath("5");
		s.setMarried(false); s.setNotMarried(true);
		s.setTxtGradeadmExam("6.50");
	}

	@Test
	void testInsertStudent() throws ParseException{
		String expectedResult = readFile("students.txt");
		String st = s.getTxtStudentname()+"-"+
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
		String actualResult = readFile("students.txt");
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testSelectStudentB() {
		String expectedResult = readFile("studentsB.txt");
		ArrayList<List<String>> actualResult = databaseOperations.selectStudentsBT(1);
		String result = null;
		for(List<String> str : actualResult) {
			result = result + str;
		}
		assertEquals(expectedResult, result);
	}
	
	@Test
	void testSelectStudentT() {
		String expectedResult = readFile("studentsB.txt");
		ArrayList<List<String>> actualResult = databaseOperations.selectStudentsBT(0);
		String result = null;
		for(List<String> str : actualResult) {
			result = result + str;
		}
		assertEquals(expectedResult, result);
	}
	
	@Test
	void testSelectStudentBT() {
		String expectedResult = readFile("studentsB.txt");
		ArrayList<List<String>> actualResult = databaseOperations.selectStudentsBT(2);
		String result = null;
		for(List<String> str : actualResult) {
			result = result + str;
		}
		assertEquals(expectedResult, result);
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
