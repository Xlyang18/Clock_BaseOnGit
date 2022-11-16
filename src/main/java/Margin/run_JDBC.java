package Margin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class run_JDBC {
    // 连接数据库
    public static Connection run_jdbc(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("MySQl Driver loaded successfully 连接驱动加载成功");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clock_data","root","******");
            System.out.println("MySQL Connection successful 连接成功");
            return connect;

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Connection run_jdbc_NoPrompt(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clock_data","root","******");
            return connect;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        run_jdbc();
    }
}
