package facultyAdminssion;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import databaseOperations.DatabaseHelper;

public class DatabaseOperations {
	static DatabaseHelper databaseHelper = new DatabaseHelper();
	
	static String[] columns = {"nume", "nume casatorie", "data nasterii", 
			"cnp", "adresa nastere", "nationalitate", "cetatenie", "adresa actuala","email", "telefon",
			"numele tatalui", "numele mamei", "stare civila", "nota bac", "nota info", "nota mate", "nota examen"};
	
	static String[] columns2 = {"nume", "medie"};
	
	static String tableName = "D:\\madalina\\Facultate\\Master An 1\\Semestrul 2\\CSS\\Managing-the-faculty-admission\\facultyAdminssion\\students.txt";
	static String tableB = "studentsB.txt";
	static String tableT = "studentsT.txt";
	static String tableR = "studentR.txt";

	
	public static void insertStudent(Student s) {
		System.out.println("aici");
		String[] std= {s.getTxtStudentname()
						,s.getTxtMariagestudentname()
						,s.getTxtBirthDay()+"/"+s.getTxtBirthMonth()+"/"+s.getTxtBirthyear()
						,s.getTxtCnp() 
						,s.getTxtBirthplace()+" "+s.getTxtBirthcity()+" "+s.getTxtBirthcountry()
						,s.getTxtNationality() ,s.getTxtCitizenship()
						,s.getTxtPlace()+" "+s.getTxtCity()+" "+s.getTxtStreet()+" "+s.getTxtNr()+" "
								+s.getTxtBlock()+" "+s.getTxtSc()+" "+s.getTxtEt()+" "+s.getTxtAp()
						,s.getTxtEmail() ,s.getTxtPhone()
						,s.getTxtFathername() ,s.getTxtMothername()
						,s.isMarried()==true?"true":"false" ,s.getTxtOldfaculty()
						,s.getTxtGradeexam() ,s.getTxtGradeinfo() ,s.getTxtGrademath(), s.getTxtGradeadmExam()};
		
		databaseHelper.createTable(tableName, columns);
		databaseHelper.insertQuery(tableName, std);
	} 

	public static ArrayList<List<String>>selectStudentsBT(int tb) {
		ArrayList<List<String>> std = new ArrayList<List<String>>();
		if(tb==1)
			 std = databaseHelper.selectQuery(tableB);
		else
			std = databaseHelper.selectQuery(tableT);

		ArrayList<List<String>> result = new ArrayList<>();
		for(List<String> ss:std) {
			result.add(new ArrayList<String>());
			result.get(result.size()-1).add(ss.get(0)); result.get(result.size()-1).add(ss.get(1)); 
		}
		return result;
	}
	
	public static ArrayList<List<String>>selectStudents() {
		ArrayList<List<String>> std = databaseHelper.selectQuery(tableName);
		ArrayList<List<String>> result = new ArrayList<>();
		for(List<String> ss:std) {
			result.add(new ArrayList<String>());
			result.get(result.size()-1).add(ss.get(0)); result.get(result.size()-1).add(ss.get(3)); 
			result.get(result.size()-1).add(ss.get(7)); result.get(result.size()-1).add(ss.get(8));
			result.get(result.size()-1).add(ss.get(9));
			result.get(result.size()-1).add(ss.get(14)); result.get(result.size()-1).add(ss.get(15)); 
			result.get(result.size()-1).add(ss.get(16)); result.get(result.size()-1).add(ss.get(17));
		}
		return result;
	}

	public static void updateStudent(String cnp, String colAct, String newVal) {
		List<String> std = databaseHelper.selectWhereQuery(tableName, columns, "cnp", cnp);
		 databaseHelper.updateQuery(tableName, "cnp",cnp,colAct,newVal);
	}		
	
	public static boolean compute(String b, String t) {
		int budget, tax;
		if(b.equals("") || t.equals("")) return false;
		
		budget = Integer.parseInt(b);
		tax = Integer.parseInt(t);

		ArrayList<List<String>> std = databaseHelper.selectQuery(tableName);
		List<String> result = new ArrayList<String>();
		for(List<String> ss:std) {
			double mean = (Integer.parseInt(ss.get(14))+Integer.parseInt(ss.get(15))+Integer.parseInt(ss.get(16))+Integer.parseInt(ss.get(17)))/4;
			result.add(ss.get(0)+"-"+Double.toString(mean));
		}
		String aux = null;
		for(int i=0; i<result.size(); i++)
			for(int j=i+1; j<result.size(); j++) {
				if(Double.parseDouble(result.get(i).split("-")[1])<Double.parseDouble(result.get(j).split("-")[1])) {
					aux=result.get(i);
					result.set(i, result.get(j));
					result.set(j, aux);
				}		
			}	
		for(String s: result) System.out.println(s);

		deleteFiles();
		int i;
		databaseHelper.createTable(tableB, columns2);
		databaseHelper.createTable(tableT, columns2);

		for(i=0; i<budget; i++) 
		{
			String[] sB= {result.get(i).split("-")[0],result.get(i).split("-")[1]}; 
			databaseHelper.insertQuery(tableB, sB);

		}
		for(int j=0; j<tax; j++) {
			String[] sT= {result.get(i++).split("-")[0],result.get(i++).split("-")[1]}; 
			databaseHelper.insertQuery(tableT, sT);
		}	
		return true;
		
	}
	
	static void deleteFiles() {
		File file = new File(tableB);
		File file2 = new File(tableT);
		if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
		
		if(file2.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
	}
	
	public static boolean deleteStudent(String cnp) {
		System.out.println(cnp);
		if(cnp!="") {
			databaseHelper.deleteQuery(tableName, cnp, cnp);
			return true;
		}
		return false;
		
	}
		
	
}
