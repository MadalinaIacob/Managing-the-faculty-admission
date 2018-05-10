package unitTests.facultyAdminssion;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import facultyAdminssion.Student;

class StudentTest {
	
	static Student s = new Student();
	
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
	void testSetGetName() {
		String result = "test";
		s.setTxtStudentname("test");
		assertEquals("Result", result, s.getTxtStudentname());
	}
	
	@Test
	void testSetGetBirthDay() {
		String result = "02";
		s.setTxtBirthDay("02");;
		assertEquals("Result", result, s.getTxtBirthDay());
	}
	
	@Test
	void testSetGetBirthMonth() {
		String result = "07";
		s.setTxtBirthMonth("07");;
		assertEquals("Result", result, s.getTxtBirthMonth());
	}
	
	@Test
	void testSetGetBirthYear() {
		String result = "2000";
		s.setTxtBirthyear("2000");;
		assertEquals("Result", result, s.getTxtBirthyear());
	}
	
	@Test
	void testSetGetCNP() {
		String result = "2970223219059";
		s.setTxtCnp("2970223219059");
		assertEquals("Result", result, s.getTxtCnp());
	}
	
	@Test
	void testSetGetBirthCity() {
		String result = "city";
		s.setTxtBirthcity("city");
		assertEquals("Result", result, s.getTxtBirthcity());
	}
	
	@Test
	void testSetGetBirthPlace() {
		String result = "place";
		s.setTxtBirthplace("place");
		assertEquals("Result", result, s.getTxtBirthplace());
	}
	
	@Test
	void testSetGetBirthCountry() {
		String result = "country";
		s.setTxtBirthcountry("country");
		assertEquals("Result", result, s.getTxtBirthcountry());
	}
	
	@Test
	void testSetGetNationality() {
		String result = "nat";
		s.setTxtNationality("nat");
		assertEquals("Result", result, s.getTxtNationality());
	}
	
	@Test
	void testSetGetCitizenship() {
		String result = "cit";
		s.setTxtCitizenship("cit");
		assertEquals("Result", result, s.getTxtCitizenship());
	}
	
	@Test
	void testSetGetNr() {
		String result = "1";
		s.setTxtNr("1");
		assertEquals("Result", result, s.getTxtNr());
	}
	
	@Test
	void testSetGetPlace() {
		String result = "place";
		s.setTxtPlace("place");
		assertEquals("Result", result, s.getTxtPlace());
	}
	
	@Test
	void testSetGetSc() {
		String result = "A";
		s.setTxtSc("A");
		assertEquals("Result", result, s.getTxtSc());
	}
	
	@Test
	void testSetGetCity() {
		String result = "city";
		s.setTxtCity("city");
		assertEquals("Result", result, s.getTxtCity());
	}
	
	@Test
	void testSetGetEt() {
		String result = "2";
		s.setTxtEt("2");
		assertEquals("Result", result, s.getTxtEt());
	}

	@Test
	void testSetGetStreet() {
		String result = "str";
		s.setTxtStreet("str");
		assertEquals("Result", result, s.getTxtStreet());
	}
	
	@Test
	void testSetGetAp() {
		String result = "12";
		s.setTxtAp("12");
		assertEquals("Result", result, s.getTxtAp());
	}
	
	@Test
	void testSetGetBlock() {
		String result = "A";
		s.setTxtBlock("A");
		assertEquals("Result", result, s.getTxtBlock());
	}
	
	@Test
	void testSetGetPhone() {
		String result = "0745671232";
		s.setTxtPhone("0745671232");
		assertEquals("Result", result, s.getTxtPhone());
	}
	
	@Test
	void testSetGetEmail() {
		String result = "t@t.com";
		s.setTxtEmail("t@t.com");
		assertEquals("Result", result, s.getTxtEmail());
	}
	
	@Test
	void testSetGetMotherName() {
		String result = "mother";
		s.setTxtMothername("mother");
		assertEquals("Result", result, s.getTxtMothername());
	}
	
	@Test
	void testSetGetFatherName() {
		String result = "father";
		s.setTxtFathername("father");
		assertEquals("Result", result, s.getTxtFathername());
	}

	
	@Test
	void testSetGetOldFaculty() {
		String result = "faculty";
		s.setTxtOldfaculty("faculty");
		assertEquals("Result", result, s.getTxtOldfaculty());
	}
	
	@Test
	void testSetGetGradeExam() {
		String result = "10";
		s.setTxtGradeexam("10");
		assertEquals("Result", result, s.getTxtGradeexam());
	}
	
	@Test
	void testSetGetGradeInfo() {
		String result = "10";
		s.setTxtGradeinfo("10");
		assertEquals("Result", result, s.getTxtGradeinfo());
	}
	
	@Test
	void testSetGetGradeMath() {
		String result = "10";
		s.setTxtGrademath("10");
		assertEquals("Result", result, s.getTxtGrademath());
	}
	
	@Test
	void testSetGetisMarried() {
		boolean result = true;
		s.setMarried(true);
		assertEquals("Result", result, s.isMarried());
	}
	
	@Test
	void testSetGetIsNotMarried() {
		boolean result = true;
		s.setNotMarried(true);
		assertEquals("Result", result, s.isNotMarried());
	}
	
	@Test
	void testSetGetGradeAdmExam() {
		String result = "10";
		s.setTxtGradeadmExam("10");
		assertEquals("Result", result, s.getTxtGradeadmExam());
	}
}
