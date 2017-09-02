package storage;

import api.AdParseException;
import api.interfaces.Settings;
import api.SettingsParam;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Класс подключения к бд реализующий паттерн синглтон
 */
public class SingleDatabase {
    private static SingleDatabase dbIsntance;
    private static Connection con ;
    /**
     * Возвращает класс для соединения с бд
     * @return
     */
    public static SingleDatabase getInstance(){
        if(dbIsntance==null){
            dbIsntance= new SingleDatabase();
        }
        return dbIsntance;
    }

    /**
     * Возвращает соединение с бд
     * @return
     */
    public Connection getConnection(Settings settings) throws AdParseException {
        if(con==null){
            try {
                Class.forName("org.postgresql.Driver");
                String host = settings.get(SettingsParam.JDBC_URL.toString());
                String login = settings.get(SettingsParam.DB_LOGIN.toString());
                String password = settings.get(SettingsParam.DB_PASSWORD.toString());
                con = DriverManager.getConnection(host, login, password);
                con.setAutoCommit(true);
            } catch (Exception e) {
                throw new AdParseException(e);
            }
        }
        return con;
    }

    /**
     * закрытие соединения
     */
    public void close(){
        if(dbIsntance != null){
            dbIsntance.close();
            dbIsntance = null;
        }
    }
}
