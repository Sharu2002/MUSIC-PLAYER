/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testingmusicplayer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
//import com.mysql.jdbc.ResultSet;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/**
 *
 * @author sharu
 */
public class Signinpage implements ActionListener{
    
    String final_user;
    JFrame f;
    JButton btn_signin ,empty;
    JLabel lbl_username , lbl_password , lbl_siginin,lbl_welcomeback;
    JTextField txt_username , txt_password;
    
    //SQL Connection
    Connection con;
    PreparedStatement insert;
    
    Signinpage()
    {
        final_user = "";
        f = new JFrame();
        empty = new JButton();
        txt_username = new JTextField();
        txt_password = new JTextField();
        btn_signin = new JButton("Sign In");
        lbl_welcomeback = new JLabel("Welcome Back :)");
        lbl_welcomeback.setFont(new Font("Ink Free" , Font.BOLD , 90 ));
        lbl_welcomeback.setForeground(Color.WHITE);
        lbl_welcomeback.setBounds(80 , 50, 800, 120);
        lbl_username = new JLabel("User Name");
        lbl_password = new JLabel("Password");
        lbl_username.setForeground(Color.red);
        lbl_username.setBounds(50 , 200, 800, 120);
        lbl_username.setFont(new Font("Ink Free" , Font.BOLD ,50 ));
        
        txt_username.setForeground(Color.white);
        txt_username.setBounds(320 , 225, 400, 60);
        txt_username.setFont(new Font("Ink Free" , Font.PLAIN ,40 ));
        txt_username.setBackground(Color.black);
        txt_username.setBorder(new LineBorder(Color.WHITE , 1));
        
        txt_password.setForeground(Color.WHITE);
        txt_password.setBounds(320 , 325, 400, 60);
        txt_password.setFont(new Font("Ink Free" , Font.PLAIN ,40 ));
        txt_password.setBackground(Color.black);
        txt_password.setBorder(new LineBorder(Color.WHITE , 1));
        
        
        lbl_password.setForeground(Color.red);
        lbl_password.setBounds(50 , 300, 800, 120);
        lbl_password.setFont(new Font("Ink Free" , Font.BOLD , 50 ));
        
        btn_signin.setBounds(300, 420, 200, 80);
        btn_signin.setBackground(Color.green);
        btn_signin.setForeground(Color.BLACK);
        btn_signin.setFont(new Font("Ink Free" , Font.BOLD,30));
        btn_signin.setFocusable(false);
        btn_signin.addActionListener(this);
        f.getContentPane().setBackground(Color.BLACK);
        f.add(lbl_welcomeback);
        f.add(lbl_username);
        f.add(lbl_password);
        f.add(txt_username);
        f.add(txt_password);
        f.add(btn_signin);
        f.add(empty);
        f.setVisible(true);
        f.setSize(800 , 600);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_signin)
        {
            String login = "SELECT * FROM javaminiproject WHERE USERNAME=? AND PASSWORD=?";
            try
            {
                java.sql.ResultSet rs;
                          
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaminiproject?useSSL=false","root","Sharu#2022");
                insert = con.prepareStatement(login);
                insert.setString(1, txt_username.getText());
                insert.setString(2, txt_password.getText());
                
                rs = insert.executeQuery();
                 if (rs.next()) 
                     {
                         final_user = txt_username.getText();
                         f.dispose();
                         
                       
                          
               
                     }
                 else
                 {
                         JOptionPane.showMessageDialog(null,"You are not registerd try registering first");
                         f.dispose();
                        
                 }
          
            }
            catch(Exception ex)
            {
                
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
