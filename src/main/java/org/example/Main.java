package org.example;

// db 연결
import java.io.UnsupportedEncodingException;
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
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = con.prepareStatement("select * from employees where salary > ? and department=?");

            myStmt.setDouble(1, 80000);
            myStmt.setString(2, "Computer");

            myRs = myStmt.executeQuery();
            System.out.println("The prepared statement: salary > 80000, department = Computer\n");
            display(myRs);

            System.out.println("\n\nReuse the prepared statement but with different parameters:"
                    + "\n\nsalary > 25000, department = Marine\n");

            myStmt.setDouble(1, 25000);
            myStmt.setString(2, "Marine");

            myRs = myStmt.executeQuery();
            display(myRs);

        } catch (Exception exc){
            exc.printStackTrace();
        }
        finally {
            if (myRs != null){
                myRs.close();
            }

            if (myStmt != null){
                myStmt.close();
            }

            if (con != null){
                con.close();
            }
        }
    }

    private static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            String lastName = myRs.getString("last_name");
            String firstName = myRs.getString("first_name");
            double salary = myRs.getDouble("salary");
            String department = myRs.getString("department");

            System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
        }
    }
}