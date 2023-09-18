package org.example;

// db 연결
import java.sql.*;

public class Main {
    public static Connection makeConnection() {
        String url = "jdbc:mysql://localhost:3306/sampledb?characterEncoding=UTF-8 & serverTumezone=UTC";
        String id = "root";
        String password = "4828";
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 적재 성공");
            con = DriverManager.getConnection(url, id, password);
            System.out.println("데이터베이스 연결 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("연결에 실패하였습니다.");
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) throws SQLException {
        Connection con = makeConnection();
        Statement s = con.createStatement();
        String query = "SELECT * FROM student";
        ResultSet rows = s.executeQuery(query);

        while (rows.next()) {
            int id = rows.getInt("id");
            String name = rows.getString("name");
            String title = rows.getString("dept");
            System.out.println(id + " " + name + " " + title);
        }
            con.close();
    }
}