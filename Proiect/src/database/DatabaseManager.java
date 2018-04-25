/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package database;

import java.util.List;

/**
 *
 * @author crs
 */
public interface DatabaseManager {

    public void createTable(String tableName, String... columnsName);

    public void insertQuery(String tableName, String... columnsName);

    public void deleteQuery(String tableName, String columnName, String columnValue);

    public List<String> selectQuery(String tableName, String columnName);

    public List<String> selectWhereQuery(String tableName, String columnName, String condition, String value);

    public void updateQuery(String tableName,  String columnName, String columnValue, String... columnsName);

}
