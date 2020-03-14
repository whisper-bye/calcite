package com.gerardnico.calcite;

import org.apache.calcite.jdbc.CalciteConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Static method on {@link org.apache.calcite.jdbc.CalciteConnection}
 */
public class CalciteConnections {

    /**
     *
     * @param connection
     * @return a {@link CalciteConnection}
     */
    public static CalciteConnection unwrap(Connection connection) {
        try {
            return connection.unwrap(CalciteConnection.class);
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    /**
     * Return a connection without any model
     * @return
     */
    public static CalciteConnection getConnectionWithoutModel() {
        try {
            Class.forName("org.apache.calcite.jdbc.Driver");
            Properties info = new Properties();
            info.setProperty("lex", "JAVA");
            return  unwrap(DriverManager.getConnection("jdbc:calcite:", info));
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
