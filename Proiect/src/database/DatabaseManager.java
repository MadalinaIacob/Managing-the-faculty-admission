/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package database;

/**
 *
 * @author crs
 */
public interface DatabaseManager {

    public void createTable(String tableName, String... columnsName);

    public void insertQuery(String tableName);

    public void deleteQuery(String tableName);

    public void selectQuery(String tableName);

    public void updateQuery(String tableName);

}
