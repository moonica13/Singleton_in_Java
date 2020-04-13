package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        //try-with-resources to make the res autocloseable
        try (Connection connection = SingletonDB.getInstance().getConnection()) {

            DatabaseMetaData metaData = connection.getMetaData();

            try (ResultSet rs = metaData.getTables(null, null, "%", null)) {

                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();

                //printing the names of the tables from the chosen db
                while (rs.next()) {

                    String tableName = rs.getString("TABLE_NAME");

                    System.out.println("\n----------");
                    System.out.println(tableName);
                    System.out.println("----------");

                    for (int i = 0; i < columnCount; i++) {
                        String columnName = rsMeta.getColumnName(i);
                        System.out.format("%s:%s\n", columnName, rs.getString(i));
                    }

                    //printing the columns for the specified table, along with some characteristics
                    System.out.println("\nCOLUMNS IN THIS TABLE: ");

                    ResultSet columns = metaData.getColumns(null, null, tableName, null);
                    while(columns.next())
                    {
                        String columnName = columns.getString("COLUMN_NAME");
                        String isNullable = columns.getString("IS_NULLABLE");
                        String is_autoIncrement = columns.getString("IS_AUTOINCREMENT");

                        System.out.println( " Column: " + columnName + " --- " + "Nullable: " + isNullable + " --- " + "Autoincrement: " + is_autoIncrement);
                    }

                }
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong!");
            e.printStackTrace();
        }

    }
}
