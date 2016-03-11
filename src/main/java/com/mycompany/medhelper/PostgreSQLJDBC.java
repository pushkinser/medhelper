/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.medhelper;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Сергей
 */
public class PostgreSQLJDBC {
    
    private static final String url = "jdbc:postgresql://localhost:5432/med_help";
    private static final String user = "postgres";
    private static final String password = "BAGLAI";
    
    private static Connection con;  // Для соединения с базой данных используют класс Connection: 
    private static Statement stmt;  // для того, чтобы посылать запросы к базе данных, необходимо создать экземпляр класса Statement
    private static ResultSet rs;
    
    public static void main(String[] arg) throws SQLException{
        
        String query ="SELECT * FROM patient;"; //тут должен быть запрос
      
        try {
            // загружаем драйвер, и он автоматически зарегистрируется себя для использования вместе с JDBC. 
            
            Class.forName("org.postgresql.Driver"); 
            
            //открываем соединение с базой данных. url - это строка, по которой JDBC определяет куда, где и чем устанавливать соединение. Она имеет следующий формат: jdbc:postgresql://сервер:порт/база_данных ,где сервер – это адрес сервера, где расположена СУБД, порт – это tcp-порт, на котором будет устанавливаться соединение и база_данных – это имя базы данных, которую мы хотим использовать.
            con = DriverManager.getConnection(url, user, password);  
           
            // getting Statement object to execute query
            stmt = con.createStatement();
            
            // executing SELECT query
            rs = stmt.executeQuery(query); //Метод executeQuery отправляет переданный ему запрос к базе данных и в качестве ответа возвращает результат в виде класса ResultSet. 
            int i = 0;
            while (rs.next()) {
                i++;
                System.out.println(rs.getString(i));
            }
            rs.close();
            stmt.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostgreSQLJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
