package unitTests.databaseOperations;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import facultyAdminssion.Student;
import facultyAdminssion.ValidateStudent;

class ValidateStudentTest {
	static Student s = new Student();
	ValidateStudent validate = new ValidateStudent();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
	void testValidateCorrectStudent() throws ParseException {
		boolean result = true;
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateIncorrectName() throws ParseException {
		boolean result = false;
		s.setTxtStudentname("test2");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateCorrectName() throws ParseException {
		boolean result = true;
		s.setTxtStudentname("test-test test.");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateCorrectCNP() throws ParseException {
		boolean result = true;
		s.setTxtCnp("2970223219059");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	

	@Test
	void testValidateIncorrectLowerLengthCNP() throws ParseException {
		boolean result = false;
		s.setTxtCnp("123456789012");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateIncorrectGreaterLengthCNP() throws ParseException {
		boolean result = false;
		s.setTxtCnp("12345678901223");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateIncorrectBirthYear() throws ParseException {
		boolean result = false;
		s.setTxtBirthyear("2006");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateIncorrectBirthDay() throws ParseException {
		boolean result = false;
		s.setTxtBirthDay("32");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateIncorrectBirthMonth() throws ParseException {
		boolean result = false;
		s.setTxtBirthMonth("13");;
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateCorrectString() throws ParseException {
		boolean result = true;
		s.setTxtNationality("test");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateIncorrectString() throws ParseException {
		boolean result = false;
		s.setTxtNationality("test123");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateCorrectNumber() throws ParseException {
		boolean result = true;
		s.setTxtNr("123");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateIncorrectNumber() throws ParseException {
		boolean result = false;
		s.setTxtNr("123a");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateCorrectEmail() throws ParseException {
		boolean result = true;
		s.setTxtEmail("test@test.com");;
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateInCorrectEmailMissDomanin() throws ParseException {
		boolean result = false;
		s.setTxtEmail("test.com");;
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateInCorrectEmailMissDot() throws ParseException {
		boolean result = false;
		s.setTxtEmail("test@com");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateCorrectPhoneNumber() throws ParseException {
		boolean result = true;
		s.setTxtPhone("0745671232");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateIncorrectLengthPhoneNumber() throws ParseException {
		boolean result = false;
		s.setTxtNr("07456712");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateIncorrectBeginingPhoneNumber() throws ParseException {
		boolean result = false;
		s.setTxtPhone("0845671232");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateCorrectGrade() throws ParseException {
		boolean result = true;
		s.setTxtGradeadmExam("9.6");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
	@Test
	void testValidateIncorrectGrade() throws ParseException {
		boolean result = false;
		s.setTxtGradeadmExam("10.6");
		assertEquals("Result", result, ValidateStudent.validate(s));
	}
	
}
