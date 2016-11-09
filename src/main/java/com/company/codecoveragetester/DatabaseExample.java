package com.company.codecoveragetester;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Example class having a database connection (HSQLDB)
 * 
 * @author Wouter Poncin <wouter.poncin@sns.nl>
 */
public class DatabaseExample {
    
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseExample.class);
    
    private static final int ACTIVE_ITEMS_STATUS_CODE = 10;
    
    private final Connection databaseConnection;

    private static final String TABLE = "testtable";
    private boolean hasTableBeenInitiated = false;
    
    public DatabaseExample() throws Exception {
        String url = "jdbc:hsqldb:file:target/testdatabase";
        LOG.info("Connecting to: {}", url);
        
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        basicDataSource.setUrl(url);
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("");
        databaseConnection = basicDataSource.getConnection();
    }
    
    private void initiateDatabaseWhenNecessary() throws Exception {
        LOG.info("Initiating database");
        if (hasTableBeenInitiated) {
            LOG.info("Table has been initialized");
            return;
        }
        
        // Check whether the table is present
        final String SQL = "select count(*) from " + TABLE;

        try {
            try (ResultSet set = databaseConnection.createStatement().executeQuery(SQL)) {
                set.next();
                LOG.info("Number of records present: {}", set.getInt(1));
            }
        } catch (SQLException ex) {
            LOG.debug("Error encountered, creating the table now: {}", ex.getLocalizedMessage());
            createTable();
        }
        hasTableBeenInitiated = true;
    }
    
    private void createTable() throws Exception {
        LOG.info("Creating table: {}", TABLE);
        final String SQL = ""
                + "create table " + TABLE + " "
                + "( "
                + " record_id int not null, "
                + " record_value varchar(100) not null, "
                + " status_code int not null, "
                + " "
                + " primary key (record_id) "
                + ")";

        databaseConnection.createStatement().execute(SQL);
    }
    
    public void deleteAllRecords() throws Exception {
        LOG.info("Deleting all records from the database");
        
        initiateDatabaseWhenNecessary();
        
        final String SQL = "delete from " + TABLE;
        databaseConnection.createStatement().execute(SQL);
    }
    
    public void insertItemInTheDatabase(final int recordId, final String recordValue, final int statusCode) throws Exception {
        LOG.info("Inserting record in the database: {}, {}, {}", recordId, recordValue, statusCode);
        
        initiateDatabaseWhenNecessary();
        
        // Note: to be protected against SQL injection
        final String SQL = ""
                + "insert into " + TABLE + " "
                + "(record_id, record_value, status_code) "
                + "values (" + recordId + ", '" + recordValue + "', " + statusCode + ")";
        LOG.info("SQL: {}", SQL);
        databaseConnection.createStatement().execute(SQL);
    }
    
    public List<String> fetchActivatedItemsFromTheDatabase() throws Exception {
        LOG.info("Fetching activated items from the database");
        
        initiateDatabaseWhenNecessary();
        
        List<String> output = new ArrayList<>();
        
        // Fetch only the activated items
        final String SQL = ""
                + "select   record_value "
                + "from     " + TABLE + " "
                + "where    status_code = " + ACTIVE_ITEMS_STATUS_CODE;
        
        try (ResultSet set = databaseConnection.createStatement().executeQuery(SQL)) {
            while (set.next()) {
                output.add(set.getString("record_value"));
            }
        }
        
        return output;
    }
}
