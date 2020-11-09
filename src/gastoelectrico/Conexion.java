/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gastoelectrico;
 import java.sql.*;


public class Conexion {
    //Conexion local
    public static Connection conectar(){
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/gastoelectrico","root","");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
        
        return null;
    }
}
