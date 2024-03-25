package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;


    private Properties properties;

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }


    private void createStatement(String string) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        createStatement(String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s, %s);",
                tableName,
                "id SERIAL PRIMARY KEY",
                "name TEXT"
        ));
    }

    public void dropTable(String tableName) throws Exception {
        createStatement(String.format(
                "DROP TABLE %s;", tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        createStatement(String.format(
                "ALTER TABLE %s ADD %s %s",
                tableName,
                columnName,
                type
        ));
    }

    public void dropColumn(String tableName, String columnName) {
        createStatement(String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName
        ));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        createStatement(String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        ));
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("prop.properties")) {
            properties.load(in);
        }
        TableEditor tb = new TableEditor(properties);
        tb.createTable("prop");
        System.out.println(tb.getTableScheme("prop"));
    }
}