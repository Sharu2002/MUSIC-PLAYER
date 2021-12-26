package testingmusicplayer;


import jaco.mp3.player.MP3Player;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.Connection;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sharu
 */


public class MusicSample extends DefaultListCellRenderer implements ActionListener 
{
    
    String user;
<<<<<<< HEAD
    
    JButton btn_register;JFrame f3;
    JTextField txt_password1;

=======
>>>>>>> bc4c6ede99e5cb6250dda11c652271dd285b7453
    int song_count = 0;
    MP3Player player;
    File songfile;
    String currentDirectory = "D:\\Music";
    String currentPath;
    String imagePath;
    boolean repeat = false;
    JLabel title, no_of_songs;
    JPanel p;
    JFrame f;
    JButton btn_play, btn_pause, btn_upload, btn_volumedown, btn_icon, btn_volumeup;
    JTextField txt_display;
    JList l;
    JScrollPane pane;
    String[] songlist;
    JButton empty;
    JLabel acc_name;
    Connection con;
    PreparedStatement insert;
    
    //Signin panel
    JFrame pnl_siginin;
    JLabel lbl_username , lbl_password , lbl_welcomeback;
    JTextField txt_username;
           JPasswordField txt_password;
    JButton panel_siginin_button;
    //Siginin Panel

    JButton account_symbol , btn_signin , btn_logout , btn_playlist , delete_account , change_password;
    
    boolean sigined_in;

    MusicSample(boolean sigined_in ,String user) {
        this.sigined_in = sigined_in;
       this.user = user;
        int c;
        l = new JList();
        account_symbol = new JButton();
        account_symbol.setBounds(20, 20, 60, 60);
        if(! sigined_in)
            account_symbol.setText("");
        else
            account_symbol.setText(user.substring(0, 1));
        account_symbol.setBackground(Color.black);
        account_symbol.setForeground(Color.white);
        account_symbol.setBorder(new LineBorder(Color.darkGray));
        account_symbol.setFont(new Font("Arial", Font.PLAIN, 34));
        
        acc_name = new JLabel();
        acc_name.setBounds(100, 20, 140, 60);
        if(! sigined_in)
            acc_name.setText("Signed Out");
        else
            acc_name.setText("Signed In");
        
        acc_name.setBackground(Color.black);
        acc_name.setForeground(Color.cyan);
        acc_name.setBorder(new LineBorder(Color.black));
        acc_name.setFont(new Font("Arial", Font.PLAIN, 16));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer?useSSL=false", "root",
                    "Sharu#2022");
            insert = con.prepareStatement("select * from music");

            java.sql.ResultSet rs = insert.executeQuery();
            ResultSetMetaData Rss = (ResultSetMetaData) rs.getMetaData();

            c = Rss.getColumnCount();

            DefaultListModel Df = new DefaultListModel();

            while (rs.next()) {
                song_count++;
                String song = rs.getString("NAME");
                Df.addElement(song);

            }
            l.setModel(Df);

        } catch (ClassNotFoundException classNotFoundException) {
            Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, classNotFoundException);
        } catch (SQLException sQLException) {
            Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, sQLException);
        } catch (HeadlessException headlessException) {
        }

        // l.setCellRenderer(new MyListCellThing());
        title = new JLabel("My Music");
        l.setDragEnabled(true);
        l.setFixedCellHeight(60);

        l.setFont(new Font("Arial", Font.PLAIN, 21));
        l.setVisibleRowCount(4);
        l.setForeground(Color.white);
        l.setSelectionBackground(Color.darkGray);
        l.setSelectionForeground(Color.cyan);

        // l.setBounds(100, 100, 700, 500);
        l.setBackground(Color.black);
        pane = new JScrollPane(l);
        pane.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pane.setBackground(Color.black);
        pane.setBounds(250, 100, 920, 370);
        pane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.gray;
            }

        });
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        txt_display = new JTextField();
        pane.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        txt_display.setEditable(false);
        txt_display.setBounds(120, 500, 760, 80);

        // Panel

        p = new JPanel();
        p.setBackground(Color.darkGray);
        p.setBounds(20, 100, 210, 370);
        
        btn_signin = new JButton("Sign in");
        btn_signin.setBounds(20, 100, 210, 50);
         btn_signin.setBackground(Color.darkGray);
        btn_signin.setForeground(Color.white);
        btn_signin.setFocusable(false);
        btn_signin.setBorder(new LineBorder(Color.black));
        btn_signin.setFont(new Font("Arial", Font.PLAIN, 24));
        btn_signin.addActionListener(this);
        btn_signin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        delete_account = new JButton("Delete Account");
        delete_account.setBounds(20, 180, 210, 50);
         delete_account.setBackground(Color.darkGray);
        delete_account.setForeground(Color.white);
        delete_account.setFocusable(false);
        delete_account.setBorder(new LineBorder(Color.black));
        delete_account.setFont(new Font("Arial", Font.PLAIN, 24));
        delete_account.addActionListener(this);
        delete_account.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        change_password = new JButton("Change Password");
        change_password.setBounds(20, 260, 210, 50);
         change_password.setBackground(Color.darkGray);
        change_password.setForeground(Color.white);
        change_password.setFocusable(false);
        change_password.setBorder(new LineBorder(Color.black));
        change_password.setFont(new Font("Arial", Font.PLAIN, 24));
        change_password.addActionListener(this);
        change_password.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn_logout = new JButton("Logout");
        btn_logout.setBounds(20, 340, 210, 50);
         btn_logout.setBackground(Color.darkGray);
        btn_logout.setForeground(Color.white);
        btn_logout.setFocusable(false);
        btn_logout.setBorder(new LineBorder(Color.black));
        btn_logout.setFont(new Font("Arial", Font.PLAIN, 24));
        btn_logout.addActionListener(this);
        btn_logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        //alignment to btn_playlist was added
        
        btn_playlist = new JButton("Favourites");
        btn_playlist.setBounds(20, 420, 210, 50);
         btn_playlist.setBackground(Color.darkGray);
        btn_playlist.setForeground(Color.white);
        btn_playlist.setFocusable(false);
        btn_playlist.setBorder(new LineBorder(Color.black));
        btn_playlist.setFont(new Font("Arial", Font.PLAIN, 24));
        btn_playlist.addActionListener(this);
        btn_playlist.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //alignment to btn_playlist completed
        

        // Panel closing

        txt_display.setBackground(Color.black);
        txt_display.setForeground(Color.white);
        txt_display.setScrollOffset(20);
        txt_display.setAutoscrolls(true);
        txt_display.setBorder(new LineBorder(Color.darkGray));
        txt_display.setFont(new Font("Arial", Font.PLAIN, 24));

        title.setBackground(Color.black);
        title.setForeground(Color.white);
        title.setBounds(250, 20, 200, 60);
        title.setBorder(new LineBorder(Color.black));
        title.setFont(new Font("Arial", Font.PLAIN, 34));

        no_of_songs = new JLabel();
        no_of_songs.setBackground(Color.black);
        no_of_songs.setForeground(Color.cyan);
        no_of_songs.setBounds(1000, 25, 200, 60);
        no_of_songs.setBorder(new LineBorder(Color.black));
        no_of_songs.setFont(new Font("Arial", Font.PLAIN, 24));
        no_of_songs.setText(("" + song_count) + " songs");

        empty = new JButton();
        btn_icon = new JButton();
        f = new JFrame();
        btn_play = new JButton();
        btn_pause = new JButton();
        btn_upload = new JButton();
        btn_pause.setIcon(
                new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-pause-48.png")); // NOI18N
        btn_pause.setBackground(new java.awt.Color(0, 0, 0));
        btn_pause.setBounds(980, 500, 80, 80);
        btn_upload.setIcon(
                new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-upload-48.png")); // NOI18N
                                                                                                                     // btn_upload.setBackground(new
                                                                                                                     // java.awt.Color(0,
                                                                                                                     // 0,
                                                                                                                     // 0));
        btn_upload.setBounds(1070, 500, 80, 80);
        btn_upload.setBackground(Color.black);
        btn_volumedown = new JButton();
        btn_volumedown.setIcon(
                new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-low-volume-50.png")); // NOI18N
        btn_volumedown.setBackground(new java.awt.Color(0, 0, 0));
        btn_volumedown.setBounds(800, 500, 80, 80);
        btn_play.setFocusable(false);
        btn_volumedown.setFocusable(false);
        btn_upload.setFocusable(false);
        btn_pause.setFocusable(false);
        btn_icon.setBounds(10, 500, 80, 80);
        btn_icon.setIcon(new javax.swing.ImageIcon(
                "D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-music-record-50.png"));
        btn_icon.setBorder(new LineBorder(Color.black, 1));

        btn_volumeup = new JButton();
        btn_volumeup.setIcon(
                new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-audio-50.png")); // NOI18N
                                                                                                                    // btn_volumeup.setBackground(new
                                                                                                                    // java.awt.Color(0,
                                                                                                                    // 0,
                                                                                                                    // 0));
        btn_volumeup.setBounds(700, 500, 80, 80);
        btn_volumeup.setBackground(Color.black);
        btn_volumeup.setBorder(new LineBorder(Color.black, 1));

        //alignment btnPlay was added
        btn_play.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_pause.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_upload.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_volumedown.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //alignment to btn_playlist closed
        
        
        btn_play.setBorder(new LineBorder(Color.black, 1));
        btn_pause.setBorder(new LineBorder(Color.black, 1));
        btn_volumedown.setBorder(new LineBorder(Color.black, 1));
        btn_upload.setBorder(new LineBorder(Color.black, 1));

        btn_play.addActionListener(this);
        btn_pause.addActionListener(this);
        btn_volumedown.addActionListener(this);
        btn_upload.addActionListener(this);
        btn_volumeup.addActionListener(this);
        btn_volumeup.setFocusable(false);

        btn_play.setBounds(890, 500, 80, 80);
        btn_play.setBackground(new java.awt.Color(0, 0, 0));
        btn_play.setIcon(
                new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-play-48.png"));
        f.add(btn_pause);
        f.add(btn_upload);
        f.add(btn_play);
        f.add(pane);
        // f.add(btn_volumedown);
        f.add(btn_icon);
        f.add(txt_display);
        f.add(no_of_songs);
        // f.add(btn_volumeup);
        //f.add(p);
        f.add(acc_name);
        f.add(empty);
        f.getContentPane().setBackground(Color.black);
        f.setVisible(true);
        f.add(title);
        f.add(btn_playlist);
        f.add(btn_signin);
        f.add(btn_logout);
       // p.add(btn_signin);
       f.add(account_symbol);
       f.add(delete_account);
       f.add(change_password);
        f.setSize(1200, 630);
        f.setLayout(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Option pane
        
        UIManager UI=new UIManager();
 UI.put("OptionPane.background", Color.white);
 UI.put("OptionPane.messageForeground", Color.black);
 UI.put("Panel.background", Color.white);
        //Option pane
        
        l.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                try {
                    lValueChanged(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }


//Method to filter .mp3 files from the specified folder

    private static class FileTypeFilter extends FileFilter {

        private String extension; // .mp3 extension
        private String description; // mesage  - "Only mp3 files"


        public FileTypeFilter(String extension, String description) {
            this.extension = extension;
            this.description = description;
        }

        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            return file.getName().toLowerCase().endsWith(extension);
        }

        public String getDescription() {
            return description + String.format(" (*%s)", extension);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_play) {
            player.play();
        }

        if (e.getSource() == btn_pause) {
            player.pause();
        }

        if (e.getSource() == btn_volumedown) {
            volumeDownControl(0.1);

        }
        if (e.getSource() == btn_volumeup) {
            volumeUpControl(0.1);
        }

        if (e.getSource() == btn_upload) {
            JFileChooser openFileChooser = new JFileChooser(currentDirectory);
            openFileChooser.setFileFilter(new FileTypeFilter(".mp3", "Open MP3 files Only!")); //Lists all .mp3 files in the current directory
            int result = openFileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                songfile = openFileChooser.getSelectedFile();
                player.addToPlayList(songfile);
                player.skipForward();
                currentDirectory = songfile.getAbsolutePath();
                txt_display.setText(songfile.getName());
            }
        }
        
        if(e.getSource() == change_password )
        {
            if(! sigined_in)
            {
                JOptionPane.showMessageDialog(null,"You are not Signed in");
            }
            else
            {
                
    Connection con;
     PreparedStatement insert;
     String fu;
     

        
    
     f3 = new JFrame();
        JButton empty = new JButton();
        
         txt_password1 = new JTextField();
         btn_register = new JButton("Change");
        JLabel lbl_welcomeback = new JLabel("Enter New Password");
        lbl_welcomeback.setFont(new Font("Ink Free" , Font.BOLD , 50 ));
        lbl_welcomeback.setForeground(Color.WHITE);
        lbl_welcomeback.setBounds(200 , 50, 800, 120);
       
        JLabel lbl_password = new JLabel("Password");
       
        
        
        txt_password1.setForeground(Color.WHITE);
        txt_password1.setBounds(320 , 225, 400, 60);
        txt_password1.setFont(new Font("Ink Free" , Font.PLAIN ,40 ));
        txt_password1.setBackground(Color.black);
        txt_password1.setBorder(new LineBorder(Color.WHITE , 1));
        
        
        lbl_password.setForeground(Color.red);
        lbl_password.setBounds(50 , 200, 800, 120);
        lbl_password.setFont(new Font("Ink Free" , Font.BOLD , 50 ));
        
        btn_register.setBounds(300, 420, 200, 80);
        btn_register.setBackground(Color.green);
        btn_register.setForeground(Color.BLACK);
        btn_register.setFont(new Font("Ink Free" , Font.BOLD,30));
        btn_register.setFocusable(false);
        btn_register.addActionListener(this);
        f3.getContentPane().setBackground(Color.BLACK);
        f3.add(lbl_welcomeback);

        f3.add(lbl_password);
  
        f3.add(txt_password1);
        f3.add(btn_register);
        f3.add(empty);
        f3.setVisible(true);
        f3.setSize(800 , 600);
        f3.setLayout(null);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
            }
        }
        
         
         if(e.getSource() == btn_register)
         {
                 try {
                  
     
        
                  
                 String password = txt_password1.getText(); 

           
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer?useSSL=false", "root",
                    "Sharu#2022");
            insert = con.prepareStatement("update users set PASSWORD=? where USERNAME =? ");


            
            insert.setString(1, password);

            insert.setString(2, user);
            insert.executeUpdate();
            
 JOptionPane.showMessageDialog(null,"Password changed successfully");
                         f3.dispose();
                            
           

        } catch (ClassNotFoundException classNotFoundException) {
            Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, classNotFoundException);
        } catch (SQLException sQLException) {
            Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, sQLException);
        } catch (HeadlessException headlessException) {

        }
         }
        
        if(e.getSource() == delete_account )
        {
            if(! sigined_in)
            {
                JOptionPane.showMessageDialog(null,"You are not Signed in");
            }
            
            else
            {
                
                 try
            {
                
                Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer?useSSL=false", "root",
                    "Sharu#2022");
            insert = con.prepareStatement("delete from users where USERNAME =? ");

            insert.setString(1, user);
            insert.executeUpdate();
            
            insert = con.prepareStatement("ALTER TABLE music DROP " + user );
            insert.executeUpdate();
            JOptionPane.showMessageDialog(null,"Your Account was deleted Successfully");
            if(player != null)
                    player.stop();
                f.dispose();
                new MusicSample(false ,"");

            
          
            }catch (SQLException ex) {
                Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
        }
        
        if(e.getSource() == btn_logout)
        {
            if(! sigined_in)
            {
                JOptionPane.showMessageDialog(null,"You are not Signed in");
            }
            else
            {
                if(player != null)
                    player.stop();
                f.dispose();
                new MusicSample(false ,"");
            }
        }
        
        if(e.getSource() == btn_playlist)
        {
            if(! sigined_in)
            {
                 JOptionPane.showMessageDialog(null,"Sign In to view your favourites");
            }
            else
            {
                if(player != null)
                    player.stop();
                f.dispose();
                System.out.println(user);
                new FavoritesFrame(true , user);
            }

        }
        if(e.getSource() == btn_signin)
        {
            if(sigined_in)
            {
                JOptionPane.showMessageDialog(null,"You are already Signed In");
            }
            
            else
            {
               // final_user = "";
        pnl_siginin = new JFrame();
        pnl_siginin.setDefaultCloseOperation(pnl_siginin.DO_NOTHING_ON_CLOSE);
        empty = new JButton();
        txt_username = new JTextField();
        txt_password = new JPasswordField();
        panel_siginin_button = new JButton("Sign In");
        lbl_welcomeback = new JLabel("Welcome:)");
        lbl_welcomeback.setFont(new Font("Ink Free" , Font.BOLD , 90 ));
        lbl_welcomeback.setForeground(Color.WHITE);
        lbl_welcomeback.setBounds(180 , 50, 800, 120);
        lbl_username = new JLabel("User Name");
        lbl_password = new JLabel("Password");
        lbl_username.setForeground(Color.white);
        lbl_username.setBounds(50 , 200, 800, 120);
        lbl_username.setFont(new Font("Arial" , Font.BOLD ,50 ));
        
        txt_username.setForeground(Color.white);
        txt_username.setBounds(320 , 225, 400, 60);
        txt_username.setFont(new Font("Arial" , Font.PLAIN ,40 ));
        txt_username.setBackground(Color.black);
        txt_username.setBorder(new LineBorder(Color.WHITE , 1));
        
        txt_password.setForeground(Color.WHITE);
        txt_password.setBounds(320 , 325, 400, 60);
        txt_password.setFont(new Font("Arial" , Font.PLAIN ,40 ));
        txt_password.setBackground(Color.black);
        txt_password.setBorder(new LineBorder(Color.WHITE , 1));
        
        
        lbl_password.setForeground(Color.white);
        lbl_password.setBounds(50 , 300, 800, 120);
        lbl_password.setFont(new Font("Arial" , Font.BOLD , 50 ));
        
        panel_siginin_button.setBounds(300, 420, 200, 80);
        panel_siginin_button.setBackground(Color.black);
        panel_siginin_button.setForeground(Color.white);
        panel_siginin_button.setBorder(new LineBorder(Color.WHITE ,1));
        panel_siginin_button.setFont(new Font("Arial" , Font.BOLD,30));
        panel_siginin_button.setFocusable(false);
        panel_siginin_button.addActionListener(this);
        pnl_siginin.getContentPane().setBackground(Color.BLACK);
        pnl_siginin.add(lbl_welcomeback);
        pnl_siginin.add(lbl_username);
        pnl_siginin.add(lbl_password);
        pnl_siginin.add(txt_username);
        pnl_siginin.add(txt_password);
        pnl_siginin.add(panel_siginin_button);
        pnl_siginin.add(empty);
        pnl_siginin.setVisible(true);
        pnl_siginin.setSize(800 , 600);
        pnl_siginin.setLayout(null);
        pnl_siginin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.user = txt_username.getText();
              
              }
        }
        
        if(e.getSource() == panel_siginin_button)
        {
            
            if(player != null)
                player.stop();
            String final_user = txt_username.getText();
            // SQL query to retrieve  from the table "users"
            String login = "SELECT * FROM users WHERE USERNAME=?";
            try
            {
                java.sql.ResultSet rs;
                          
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                //Connecting to the data base
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer?useSSL=false","root","Sharu#2022");
                insert = con.prepareStatement(login);
                insert.setString(1, txt_username.getText());
                
                
                rs = insert.executeQuery();
                
                 if (rs.next()) 
                     {
                           System.out.println(rs.getString("PASSWORD"));
                           System.out.println(txt_password.getText());
                         if(rs.getString("PASSWORD").equals(txt_password.getText()))
                         {
                           
                             
                             pnl_siginin.dispose();
                             sigined_in = true;
                                acc_name.setText("Signed In");
                                f.dispose();
                                new MusicSample(true ,txt_username.getText() );
                             
                         }
                         else
                         {
                             JOptionPane.showMessageDialog(null,"Incorrect password . Try Again ");
                         }
  
                     }
                 else
                 {
                         JOptionPane.showMessageDialog(null,"You are not registerd do you want to register ? ");
                          login = "insert into users values(?,?)";
                          insert = con.prepareStatement(login);
                    insert.setString(1, txt_username.getText());
                    insert.setString(2, txt_password.getText()); 
                    insert.executeUpdate();
                    
                    insert = con.prepareStatement("ALTER TABLE music ADD " + txt_username.getText() + " VARCHAR(10) NOT NULL");
                   
                    insert.executeUpdate();
                    pnl_siginin.dispose();
                    
                    account_symbol.setText(txt_username.getText().substring(0, 1));
                             sigined_in = true;
                                acc_name.setText("Signed In");
                                f.dispose();
                                new MusicSample(true ,txt_username.getText());
                         
                        
                 }
          
            } catch (SQLException ex) {
                Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private MP3Player mp3Player() {
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }

    private void lValueChanged(javax.swing.event.ListSelectionEvent evt) throws SQLException {
        int c;
        if (this.player != null) {
            player.stop();
        }
        String selectedElement = (String) l.getSelectedValue();
        System.out.println(selectedElement);
        insert = con.prepareStatement("select * from music where NAME =?");
        insert.setString(1, selectedElement);

        java.sql.ResultSet rs = insert.executeQuery();
        ResultSetMetaData Rss = (ResultSetMetaData) rs.getMetaData();

        c = Rss.getColumnCount();
        // player.stop();
        if (rs.next())
            songfile = new File(rs.getString("URL"));
        String fileName = selectedElement;
        txt_display.setText(fileName);

        player = mp3Player();

        player.addToPlayList(songfile);

        currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        imagePath = "\\Iamges";
        player.play();

    }
//Methods to control volume ------------ Volume down control
    private void volumeDownControl(Double valueMinus) {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();

        for (Mixer.Info mixerInfo : mixers) {
            Mixer mixer = AudioSystem.getMixer(mixerInfo);

            Line.Info[] lineInfos = mixer.getTargetLineInfo();

            for (Line.Info lineInfo : lineInfos) {
                Line line = null;
                boolean opened = true;

                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;

                    if (!opened) {
                        line.open();
                    }
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);

                    float currentVolume = volControl.getValue();
                    Double volumeToCut = valueMinus;
                    float changeCalc = (float) ((float) currentVolume - (double) volumeToCut);
                    volControl.setValue(changeCalc);
                } catch (LineUnavailableException exception) {
                } catch (IllegalArgumentException illException) {
                } finally {
                    if (line != null && !opened) {
                        line.close();
                    }
                }

            }
        }
    }
//Methods to control volume  ------------Volume up control

    private void volumeUpControl(Double valueMinus) {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();

        for (Mixer.Info mixerInfo : mixers) {
            Mixer mixer = AudioSystem.getMixer(mixerInfo);

            Line.Info[] lineInfos = mixer.getTargetLineInfo();

            for (Line.Info lineInfo : lineInfos) {
                Line line = null;
                boolean opened = true;

                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;

                    if (!opened) {
                        line.open();
                    }
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);

                    float currentVolume = volControl.getValue();
                    Double volumeToCut = valueMinus;
                    float changeCalc = (float) ((float) currentVolume + (double) volumeToCut);
                    volControl.setValue(changeCalc);
                } catch (LineUnavailableException exception) {
                } catch (IllegalArgumentException illException) {
                } finally {
                    if (line != null && !opened) {
                        line.close();
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        new MusicSample(false , "");
    }

}

