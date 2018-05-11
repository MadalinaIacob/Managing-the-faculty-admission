/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crs
 */
public class DatabaseHelper implements DatabaseManager {

    @Override
    public void createTable(String tableName, String... columnsName) {
    	System.out.println("table creation");

        FileWriter fw = null;
        String output = "";
        try {
            fw = new FileWriter(tableName, true);
            for (String columnName : columnsName) {
                output = output + columnName + "-";
            }
            fw.write(output);
            fw.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void insertQuery(String tableName, String... columnsName) {
    	System.out.println("insert");
        FileWriter fw = null;
        String output = "";
        try {
            fw = new FileWriter(tableName, true);
            for (String columnName : columnsName) {
                if (columnName.equals("")) {
                    output = output + "null" + "-";
                } else {
                    output = output + columnName + "-";
                }
            }
            fw.append(output);
            fw.append("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteQuery(String tableName, String columnName, String columnValue) {
        BufferedReader br = null;
        BufferedWriter writer = null;
        String firstLine = null;
        File tempFile = new File("tempcopy.txt");
        File inputFile = new File(tableName);

        int indexColumn = -1;
        boolean ok = false;
        try {
            br = new BufferedReader(new FileReader(tableName));
            writer = new BufferedWriter(new FileWriter(tempFile));
            String line;
            firstLine = br.readLine();
            writer.write(firstLine + System.getProperty("line.separator"));
            String splitFirstLine[] = firstLine.split("-");
            for (int i = 0; i < splitFirstLine.length; i++) {
                if (splitFirstLine[i].equals(columnName)) {
                    indexColumn = i;
                }
            }
            while ((line = br.readLine()) != null && indexColumn != -1) {
                String[] split = line.split("-");
                ok = false;
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equals(columnValue) && indexColumn == i) {
                        ok = true;
                        break;
                    }
                }
                if (ok == true) {
                    continue;
                } else {
                    writer.write(line + System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        inputFile.delete();
        boolean successful = tempFile.renameTo(new File(tableName));
    }

    @Override
    public ArrayList<List<String>> selectQuery(String tableName)
    {
    	ArrayList<List<String>> result = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(tableName));
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("-");
                result.add(new ArrayList<String>());
                for (int i = 0; i < split.length; i++) {
                        result.get(result.size()-1).add(split[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.out.print(result);
        return result;
    }

    @Override
    public ArrayList<String> selectWhereQuery(String tableName, String[] columnName, String condition, String value) {
        ArrayList<String> result = new ArrayList<>();
        BufferedReader br = null;
        String firstLine = null;
        List<Integer> indexColumn = new ArrayList<Integer>();
        int indexCondition = -1;
        boolean ok = false;
        try {
            br = new BufferedReader(new FileReader(tableName));
            String line;
            firstLine = br.readLine();
            String splitFirstLine[] = firstLine.split("-");
            for (int i = 0; i < splitFirstLine.length; i++) {
            	for(String s:columnName)
                if (splitFirstLine[i].equals(s)) {
                    indexColumn.add(i);
                }
            }

            for (int i = 0; i < splitFirstLine.length; i++) {
                if (splitFirstLine[i].equals(condition)) {
                    indexCondition = i;
                }
            }
            while ((line = br.readLine()) != null && indexCondition != -1) {
                String[] split = line.split("-");
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equals(value) && indexCondition == i) {
                        for (int j = 0; j < split.length; j++) {
                        	for(Integer elem:indexColumn)
                        		if (elem == j) {
                        			result.add(split[j]);
                        		}
                        	}
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public void updateQuery(String tableName, String columnName, String columnValue, String... columnsName) {
        BufferedReader br = null;
        BufferedWriter writer = null;
        String firstLine = null;
        File tempFile = new File("tempcopy.txt");
        File inputFile = new File(tableName);

        int indexColumn = -1;
        boolean ok = false;
        try {
            br = new BufferedReader(new FileReader(tableName));
            writer = new BufferedWriter(new FileWriter(tempFile));
            String line;
            firstLine = br.readLine();
            writer.write(firstLine + System.getProperty("line.separator"));
            String splitFirstLine[] = firstLine.split("-");
            for (int i = 0; i < splitFirstLine.length; i++) {
                if (splitFirstLine[i].equals(columnName)) {
                    indexColumn = i;
                }
            }
            while ((line = br.readLine()) != null && indexColumn != -1) {
                String[] split = line.split("-");
                ok = false;
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equals(columnValue) && indexColumn == i) {
                        ok = true;
                        break;
                    }
                }
                if (ok == true) {
                    line = "";
                    for (String column : columnsName) {
                        if (column.equals("")) {
                            line = line + "null" + "-";
                        } else {
                            line = line + column + "-";
                        }
                        line=line+column;
                    }
                    writer.write(line + System.getProperty("line.separator"));
                } else {
                    writer.write(line + System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        inputFile.delete();
        boolean successful = tempFile.renameTo(new File(tableName));
    }
}
