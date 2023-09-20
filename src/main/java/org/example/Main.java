package org.example;

// db 연결
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static Connection makeConnection() {
        String url = "jdbc:mysql://localhost:3306/book?characterEncoding=UTF-8 & serverTumezone=UTC";
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
        Scanner sc = new Scanner(System.in);
        Connection con = makeConnection();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        boolean exit = false;
        int choose = 0;

        while(!exit) {
            String id = null, book_name=null, publisher=null, author=null, property=null, new_value=null;
            myStmt = con.prepareStatement("select * from book_s");
            myRs = myStmt.executeQuery();
            display(myRs);

            System.out.println("메뉴를 선택해 주세요(1.추가, 2.삭제, 3.수정, 4.끝내기): ");
            choose = sc.nextInt();
            switch (choose) {
                case 1: try {
                    myStmt = con.prepareStatement("insert into book_s values (?, ?, ?, ?)");
                    System.out.println("id을 입력하세요: ");
                    id = sc.next();
                    System.out.println("책제목을 입력하세요: ");
                    book_name = sc.next();
                    System.out.println("출판사을 입력하세요: ");
                    publisher = sc.next();
                    System.out.println("저자을 입력하세요: ");
                    author = sc.next();

                    myStmt.setString(1, id);
                    myStmt.setString(2, book_name);
                    myStmt.setString(3, publisher);
                    myStmt.setString(4, author);

                    myRs = myStmt.executeQuery();
                    display(myRs);
                } catch (Exception exc){
                    exc.printStackTrace();
                }
                    break;
                case 2: try {
                    myStmt = con.prepareStatement("delete from book_s where id=?");
                    System.out.println("삭제할 id을 입력하세요: ");
                    id = sc.next();

                    myStmt.setString(1, id);

                    myRs = myStmt.executeQuery();
                    display(myRs);
                } catch (Exception exc){
                    exc.printStackTrace();
                }
                    break;
                case 3: try {
                    myStmt = con.prepareStatement("update book_s set ?=? where id=?");
                    System.out.println("수정할 id을 입력하세요: ");
                    id = sc.next();

                    System.out.println("수정할 속성은?(title, publicher, author): ");
                    property = sc.next();

                    System.out.println("수정할 내용: ");
                    new_value = sc.next();

                    myStmt.setString(1, property);
                    myStmt.setString(2, new_value);
                    myStmt.setString(3, id);

                    myRs = myStmt.executeQuery();
                    display(myRs);
                } catch (Exception exc){
                    exc.printStackTrace();
                }
                    break;
                case 4: exit = true;
                        break;
                default: System.out.println("다시 메뉴를 선택해 주세요(1.추가, 2.삭제, 3.수정, 4.끝내기): ");
                        break;
            };
        }
    }

    private static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            String id = myRs.getString("id");
            String title = myRs.getString("title");
            String publisher = myRs.getString("publisher");
            String author = myRs.getString("author");

            System.out.printf("%s, %s, %s, %s\n", id, title, publisher, author);
        }
    }
}