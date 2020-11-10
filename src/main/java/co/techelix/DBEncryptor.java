package co.techelix;


import co.techelix.cipher.ICipher;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Db encryptor.
 */
public class DBEncryptor {


    private Connection dbConnection;
    private String databaseName;
    private ICipher cipher;
    private Map<String, List<String>> tableColumnMapList = new HashMap<>();
    private int batchSize = 50;


    /**
     * Sets database name.
     *
     * @param databaseName the database name
     * @return instance of DBEncryptor
     */
    public DBEncryptor setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    /**
     * Sets batch size.
     *
     * @param batchSize the batch size
     * @return the batch size
     */
    public DBEncryptor setBatchSize(int batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    /**
     * Sets cipher.
     *
     * @param cipher the cipher
     * @return instance of DBEncryptor
     */
    public DBEncryptor setCipher(ICipher cipher) {
        this.cipher = cipher;
        return this;
    }

    /**
     * Sets table column map list.
     *
     * @param tableColumnMapList the table column map list
     * @return instance of DBEncryptor
     */
    public DBEncryptor setTableColumnMapList(Map<String, List<String>> tableColumnMapList) {
        this.tableColumnMapList = tableColumnMapList;
        return this;
    }

    /**
     * Gets database name.
     *
     * @return the database name
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Gets cipher.
     *
     * @return the cipher
     */
    public ICipher getCipher() {
        return cipher;
    }

    /**
     * Gets table column map list.
     *
     * @return the table column map list
     */
    public Map<String, List<String>> getTableColumnMapList() {
        return tableColumnMapList;
    }

    /**
     * Gets batch size.
     *
     * @return the batch size
     */
    public int getBatchSize() {
        return batchSize;
    }

    /**
     * Open and connect SQLite db connection.
     *
     * @return the connection to the database
     */
    private Connection openAndConnectSQLiteDB() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + databaseName;
        try {
            dbConnection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return dbConnection;
    }

    /**
     * Prepare update SQL statement string.
     *
     * @param tableColumnMap the table column map
     * @return the SQL statement
     */
    private String generateUpdateStatement(Map.Entry<String, List<String>> tableColumnMap) {

        String updateSQL = String.format("update %s set ", tableColumnMap.getKey());
        for (int i = 1; i < tableColumnMap.getValue().size(); i++) {
            updateSQL = updateSQL.concat(String.format("%s = ?,", tableColumnMap.getValue().get(i)));
        }

        updateSQL = updateSQL.substring(0, updateSQL.length() - 1).concat(String.format(" where %s = ?", tableColumnMap.getValue().get(0)));
        System.out.println(updateSQL);
        return updateSQL;
    }

    /**
     * Prepare fetch SQL statement string.
     *
     * @param tableColumnMap the table column map
     * @return the SQL statement
     */
    private String generateSelectStatement(Map.Entry<String, List<String>> tableColumnMap) {

        String insertSQL = "select ";
        for (int i = 0; i < tableColumnMap.getValue().size(); i++) {
            insertSQL = insertSQL.concat(String.format("%s ,", tableColumnMap.getValue().get(i)));
        }

        insertSQL = insertSQL.substring(0, insertSQL.length() - 1).concat(String.format("from %s ", tableColumnMap.getKey()));
        System.out.println(insertSQL);
        return insertSQL;
    }


    /**
     * Execute encryptor.
     */
    public void execute() {

        openAndConnectSQLiteDB();

        if (dbConnection != null) {

            for (Map.Entry<String, List<String>> tableColumnMap : tableColumnMapList.entrySet()) {

                System.out.println("Working With Table: "+tableColumnMap.getKey());
                String fetchSQLStatement = generateSelectStatement(tableColumnMap);
                String updateSQLStatement = generateUpdateStatement(tableColumnMap);

                ResultSet resultSet = executeAndFetchData(fetchSQLStatement);
                batchUpdate(updateSQLStatement, resultSet, tableColumnMap);
            }

            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Execute and fetch data result set.
     *
     * @param sqlStatement the sql statement
     * @return the result set
     */
    private ResultSet executeAndFetchData(String sqlStatement) {
        ResultSet resultSet = null;

        try {
            Statement stmt = dbConnection.createStatement();
            resultSet = stmt.executeQuery(sqlStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    private boolean batchUpdate(String sql, ResultSet rs, Map.Entry<String, List<String>> tableColumnMap) {
        PreparedStatement preparedStatement;
        int rowCount = 0;
        try {
            preparedStatement =
                    dbConnection.prepareStatement(sql);

            if (preparedStatement != null) {
                while (rs.next()) {

                    int paramIndex = 1;

                    for (int i = 1; i < tableColumnMap.getValue().size(); i++) {

                        String toUpdateValue =rs.getString(tableColumnMap.getValue().get(i));

                        if(toUpdateValue == null){
                            preparedStatement.setString(paramIndex,null);
                        }else{
                            preparedStatement.setString(paramIndex, cipher.encode(rs.getString(tableColumnMap.getValue().get(i))));
                        }
                        paramIndex++;
                    }
                    preparedStatement.setInt(paramIndex, rs.getInt(tableColumnMap.getValue().get(0)));
                    preparedStatement.addBatch();

                    rowCount++;

                    if (rowCount % batchSize == 0) {
                        preparedStatement.executeBatch();
                    }
                }

                preparedStatement.executeBatch();
                preparedStatement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
