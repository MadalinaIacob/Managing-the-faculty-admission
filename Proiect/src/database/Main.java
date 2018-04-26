package database;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author crs
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
//        databaseHelper.createTable("students.txt", "nume", "prenume", "nota");
//        databaseHelper.insertQuery("students.txt", "Hello", "Hello", "8");
//        databaseHelper.insertQuery("students.txt", "Ion", "Vasile", "10");
//        databaseHelper.insertQuery("students.txt", "Hello", "Hello", "");
//        databaseHelper.insertQuery("students.txt", "DASC", "zxzx", "");
        try {
            //databaseHelper.deleteQuery("students.txt", "nume","Hello");
            // databaseHelper.updateQuery("students.txt", "nota","null","Maria","Maria","5");
//        databaseHelper.selectWhereQuery("students.txt", "nume", "nota","7");
            databaseHelper.downloadPdf();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
