package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getConnection() throws Exception {

        // H2接続情報
        String url = "jdbc:h2:tcp://localhost/~/test"; // ←DB名（必要なら変更）
        String user = "sa";
        String password = "";

        Class.forName("org.h2.Driver");

        return DriverManager.getConnection(url, user, password);
    }
}