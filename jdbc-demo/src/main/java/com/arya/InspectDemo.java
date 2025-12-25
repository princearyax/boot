package com.arya;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class InspectDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Meowww....");
            System.out.println(System.getProperty("user.dir"));
            System.exit(0);
        }
        String url = "jdbc:postgresql://localhost:5432/learn_jdbc";
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM accounts";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(3));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
