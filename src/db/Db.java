package db;

import exception.DbException;
import exception.FileException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Db {

    private static Connection con;

    //inicia a conexão com o banco de dados
    public static Connection getConnection() {
        if (con == null) {
            try{
                    Properties properties = loadProperties();

                    String url = properties.getProperty("dburl");
                    con = DriverManager.getConnection(url, properties);

            } catch (Exception e) {
                throw new DbException("Error while trying to connect to database: " + e.getMessage());
            }
        }
        return con;
    }

    //fecha a conexão com o banco de dados
    public static void closeConnection() {
        if(con != null) {
            try{
                con.close();
            } catch (SQLException e) {
                throw new DbException("Error while closing connection: " + e.getMessage());
            }
        }
    }

    //Carrega as informações de acesso ao banco de dados
    public static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(fs);

            return properties;

        } catch (FileNotFoundException e) {
            throw new FileException("File db.properties not found: " + e.getMessage());
        } catch (IOException e) {
            throw new FileException("Error while reading db.properties: " + e.getMessage());
        }
    }


    public static void closeStatement(PreparedStatement ps) {
        if(ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
