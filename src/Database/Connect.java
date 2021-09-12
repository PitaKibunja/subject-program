/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
//import zili.Enroll;

/**
 *
 * @author user
 */
public class Connect {
    private static Connection con=null;
    private static PreparedStatement pst=null;
    private static Statement st=null;
    private static ResultSet rs=null;
    
    private static final String url="jdbc:mysql://localhost:3306/classsubjects";
    private static final String pass="";
    private static final String user="root";
    private static final String driver="com.mysql.jdbc.Driver";
        public static Connection getConnection(){
        try{
            Class.forName(driver);
            return con= DriverManager.getConnection(url,user,pass);
            //
            
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }
        public static void theQuery(String query, String msg) throws SQLException{
        try{
           st=getConnection().createStatement();
           st.executeUpdate(query);
           JOptionPane.showMessageDialog(null,msg);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
        }
        public boolean validat_users(String user, String pass, String role) throws SQLException{
        con=getConnection();
        pst=con.prepareStatement("SELECT * FROM users WHERE username=?,password=? and role=?");
        pst.setString(1,user);
        pst.setString(2, pass);
        pst.setString(3, role);
        rs=pst.executeQuery();
        if(rs.next()){
            return true;
            
        }
        else{
            return false;
        }
        
        }
        public static void Delete(String name) throws SQLException{
            con=getConnection();
            pst=con.prepareStatement("DELETE * FROM `subjects` WHERE subject=?");
            pst.setString(1, name);
            rs=pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null,"Deleted"+name);
            }else{
                JOptionPane.showMessageDialog(null,"Not such Subject found");
            }
            
        }
}
