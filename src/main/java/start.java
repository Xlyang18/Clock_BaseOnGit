import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;
import static Margin.get_time.*;
import static Margin.run_JDBC.*;
import static java.lang.Thread.sleep;

public class start {
    // 31-红色，32-绿色，33-黄色，34-蓝色，35-紫色
    private static String getFormatLogString(String content, int colour, int type) {
        boolean hasType = type != 1 && type != 3 && type != 4;
        if (hasType) {
            return String.format("\033[%dm%s\033[0m", colour, content);
        } else {
            return String.format("\033[%d;%dm%s\033[0m", colour, type, content);
        }
    }
    // 获取输入的静态方法
    private static Scanner input = new Scanner(System.in);

    // 生成一个十位数的随机码，用作绑定事件的标识
    public static String randomString(){
        String x="";
        for(int n=0;n<10;n++ ) {
            x+=(int)(10*(Math.random()));
        }

        return x;
    }
    public static void countOne() throws SQLException {
        Statement statement = run_jdbc_NoPrompt().createStatement();
        String sql = "SELECT COUNT(*) FROM center;";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String countData = resultSet.getString("COUNT(*)");
            System.out.println(getFormatLogString(countData+" pieces of information were successfully captured from the database",33,0));
        }
    }



    public static void addOne(String data) throws SQLException {
        String randomone = randomString();
        String timeone = getStringDate();

        Statement statement = run_jdbc_NoPrompt().createStatement();
        String sql = "INSERT INTO center(timeOne,dataOne,randomOne) VALUES('"+timeone+"','"+data+"','"+randomone+"');";
        statement.executeUpdate(sql);
        System.out.println("===============================|");
        System.out.println(getStringDate()); // 获取时间-yyyy-MM-dd HH:mm:ss
        System.out.print("commit- ");
        System.out.println(randomone+"   2022  +0800");
        System.out.print("add record-");
        System.out.println(getFormatLogString(data,34,0));
        System.out.println(getFormatLogString("The record has been submitted to the local computer",32,0));
    }
    public static void logOne() throws SQLException {
        System.out.println("===============================|");
        Statement statement = run_jdbc_NoPrompt().createStatement();
        String sql = "SELECT * FROM center";
        ResultSet resultSet = statement.executeQuery(sql);
        String timeone;
        String data;
        String randomone;
        while (resultSet.next()) {
            timeone = resultSet.getString("timeOne");
            data = resultSet.getString("dataOne");
            randomone = resultSet.getString("randomOne");
            System.out.println(timeone+"  "+data+"  "+ randomone);
        }
        countOne();
        System.out.println(getFormatLogString("The query has been completed",32,0));

    }

    public static String input_jp(){
        System.out.print("[@Xlyang-offline] ~% ");
        String input_data = input.nextLine();
        return input_data;


    }
    public static void loop() throws SQLException {
        String input_data;
        input_data = input_jp();
        int lenData = input_data.length();
        if (lenData == 3){
            input_data = input_data+" NULL";
        }
        String inputs; // 获取输入内容的前半段：指令类型
        String inputdata; // 获取输入内容的后半段：内容信息
        int inputlen = Integer.parseInt(String.valueOf(input_data.length()));
        inputs = input_data.substring(0,3);
        inputdata = input_data.substring(4,inputlen);

        if (Objects.equals(inputs,"add")){
            addOne(inputdata);
        }
        else if(Objects.equals(inputs,"log")){
            logOne();
        }
        else if(Objects.equals(inputs,"all")){
            System.out.println(getFormatLogString("This feature is being developed and tested",33,0));
        }
        else if(Objects.equals(inputs,"hel")){
            System.out.println(getFormatLogString("The directives currently supported by the update are:",33,0));
            System.out.println(getFormatLogString("-> add *  [添加新的记录]",33,0));
            System.out.println(getFormatLogString("-> log *  [查看最近记录]",33,0));
            System.out.println(getFormatLogString("-> all *  [检查全部记录]",33,0));


        }
        else{
            System.out.println(getFormatLogString("The input syntax is incorrect. " +
                    "You can enter the \"help\" command to obtain help",31,0));
        }
        loop();
    }

    public static void main(String[] args) throws InterruptedException, SQLException {
        System.out.println(getFormatLogString("welcome to use -Clock_BaseOnGit-",33,0));
        loop();

    }
}
