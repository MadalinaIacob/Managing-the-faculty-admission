package facultyAdminssion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ValidateStudent {
	public static boolean validate(Student s) throws ParseException {
		return validateName(s.getTxtStudentname())
				&& validateName(s.getTxtMariagestudentname())
				&& validateName(s.getTxtFathername())
				&& validateName(s.getTxtMothername())
				&&validateCNP(s.getTxtCnp())
				&&checkDate(s.getTxtBirthDay()+"/"+s.getTxtBirthMonth()+"/"+s.getTxtBirthyear())
				&&checkString(s.getTxtNationality())
				&&checkString(s.getTxtBirthcity())
				&&checkString(s.getTxtBirthplace())
				&&checkString(s.getTxtBirthcountry())
				&&checkString(s.getTxtCitizenship())
				&&checkString(s.getTxtPlace())
				&&checkString(s.getTxtCity())
				&&checkString(s.getTxtStreet())
				&&checkNumber(s.getTxtNr())
				&&checkString(s.getTxtBlock())
				&&checkString(s.getTxtSc())
				&&checkNumber(s.getTxtEt())
				&&checkNumber(s.getTxtAp())
				&&checkEmail(s.getTxtEmail())
				&&checkPhoneNumber(s.getTxtPhone())
				&&checkString(s.getTxtOldfaculty())
				&&checkGrades(s.getTxtGradeexam())
				&&checkGrades(s.getTxtGradeinfo())
				&&checkGrades(s.getTxtGrademath())
				&&checkGrades(s.getTxtGradeadmExam());
	}
	
	static boolean validateName(String name) {
		return name.matches("^[\\p{L} .'-]+$");		
	}
	
	static boolean validateCNP(String cnp) {
		if(cnp.matches("[0-9]+")==false) {
			return false;
		}
		if (cnp.length()>13 || cnp.length()<13) {
			return false;
		}
		String [] cnpNumbers = cnp.split("");
		int suma = Integer.parseInt(cnpNumbers[0])*2
				+Integer.parseInt(cnpNumbers[1])*7
				+Integer.parseInt(cnpNumbers[2])*9
				+Integer.parseInt(cnpNumbers[3])*1
				+Integer.parseInt(cnpNumbers[4])*4
				+Integer.parseInt(cnpNumbers[5])*6
				+Integer.parseInt(cnpNumbers[6])*3
				+Integer.parseInt(cnpNumbers[7])*5
				+Integer.parseInt(cnpNumbers[8])*8
				+Integer.parseInt(cnpNumbers[9])*2
				+Integer.parseInt(cnpNumbers[10])*7
				+Integer.parseInt(cnpNumbers[11])*9;
		if (suma%11==10 && suma%11 == Integer.parseInt(cnpNumbers[12])) {
			return true;
			
		}

		return false;
	}

	static boolean checkDate(String dt) throws ParseException {
		int year = Integer.parseInt(dt.split("/")[2]);
		if(Calendar.getInstance().get(Calendar.YEAR) - year < 18) return false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		
		try {
			Date date = sdf.parse(dt);
			return true;
		} catch (ParseException e) {
			System.out.println("Data nu exista");
			return false;
		}		
	}

	static boolean checkString(String s) {
		if(s==null) return true;
		return s.matches("^[a-zA-Z]+$");
	}
	
	static boolean checkNumber(String nb) {
		if(nb==null) return true;
		return nb.matches("^[0-9]*$");
	}
	
	static boolean checkEmail(String email) {
		return email.matches("^(.+)@(.+)$");
	}
	
	static boolean checkPhoneNumber(String nb) {
		return nb.matches("^(07)[0-9]{8}$");
	}

	static boolean checkGrades(String grade) {
		return grade.matches("^(10.00)|([1-9]\\.[0-9]{2})$");
	}
	
}
