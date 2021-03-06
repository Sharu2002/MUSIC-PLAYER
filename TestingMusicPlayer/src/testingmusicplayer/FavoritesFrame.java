package testingmusicplayer;

//Comment addded
import jaco.mp3.player.MP3Player;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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


public class FavoritesFrame extends DefaultListCellRenderer implements ActionListener {
JButton btn_register;JFrame f3;
    JTextField txt_password1;
    int song_count = 0;
    String user;
    MP3Player player;
    JButton btn_add;
    JButton btn_remove;
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
    JTextField txt_username , txt_password;
    JButton panel_siginin_button;
    //Siginin Panel

    JButton account_symbol , btn_signin , btn_logout , btn_playlist, delete_account , change_password;
    
    boolean sigined_in;
    JLabel lbl_addsongs;
    JLabel lbl_removesongs;
    
    //btn_add 
    JFrame add_f;
    JList add_l;
    JScrollPane add_pane;
    String[] add_songlist;
    JButton add_done;
    JLabel add_message;
    
    //btn_add
    
    //btn_remove
    JFrame remove_f;
    JList remove_l;
    JScrollPane remove_pane;
    String[] remove_songlist;
    JButton remove_done;
    JLabel remove_message;
    //btn_reomve

    
    FavoritesFrame(boolean sigined_in ,String user) {
        
        
        
        lbl_addsongs = new JLabel("Add Songs");
        lbl_addsongs.setBounds(830, 30, 150, 50);
        lbl_addsongs.setBackground(Color.black);
        lbl_addsongs.setBorder(new LineBorder(Color.black ,1));
        lbl_addsongs.setFont(new Font("Arial " , Font.PLAIN , 22));
        lbl_addsongs.setForeground(Color.white);
        
        btn_add = new JButton();
        btn_add.setBounds(760, 25, 60, 60);
        btn_add.addActionListener(this);
        btn_add.setBorder(new LineBorder(Color.black ,1));
        btn_add.setBackground(Color.BLACK);
        btn_add.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\Done-64-128px\\icons8-done-64.png"));
        btn_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn_remove = new JButton();
        btn_remove.setBounds(520, 25, 60, 60);
        btn_remove.addActionListener(this);
        btn_remove.setBorder(new LineBorder(Color.black ,1));
        btn_remove.setBackground(Color.BLACK);
        btn_remove.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\Remove-32-64px\\icons8-remove-32.png"));
        btn_remove.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        lbl_removesongs = new JLabel("Remove Songs");
        lbl_removesongs.setBounds(580, 30, 180, 50);
        lbl_removesongs.setBackground(Color.black);
        lbl_removesongs.setBorder(new LineBorder(Color.black ,1));
        lbl_removesongs.setFont(new Font("Arial " , Font.PLAIN , 22));
        lbl_removesongs.setForeground(Color.white);
        
        
        
        this.user  =user;
        System.out.println(user);
        this.sigined_in = sigined_in;
       
        int c;
        l = new JList();
        account_symbol = new JButton();
        account_symbol.setBounds(20, 20, 60, 60);
       
        account_symbol.setText(user.substring(0, 1));
        account_symbol.setBackground(Color.black);
        account_symbol.setForeground(Color.white);
        account_symbol.setBorder(new LineBorder(Color.darkGray));
        account_symbol.setFont(new Font("Arial", Font.PLAIN, 34));
        
        acc_name = new JLabel();
        acc_name.setBounds(100, 20, 140, 60);
      
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
               
                if(rs.getString(user).equals("1") )
                {
                    song_count++;
                
                
                String song = rs.getString("NAME");
                Df.addElement(song);
                }

            }
            l.setModel(Df);

        } catch (ClassNotFoundException classNotFoundException) {
            Logger.getLogger(FavoritesFrame.class.getName()).log(Level.SEVERE, null, classNotFoundException);
        } catch (SQLException sQLException) {
            Logger.getLogger(FavoritesFrame.class.getName()).log(Level.SEVERE, null, sQLException);
        } catch (HeadlessException headlessException) {
        }

        // l.setCellRenderer(new MyListCellThing());
        title = new JLabel("Favourites");
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
        
        btn_logout = new JButton("Logout");
        btn_logout.setBounds(20, 180, 210, 50);
         btn_logout.setBackground(Color.darkGray);
        btn_logout.setForeground(Color.white);
        btn_logout.setFocusable(false);
        btn_logout.setBorder(new LineBorder(Color.black));
        btn_logout.setFont(new Font("Arial", Font.PLAIN, 24));
        btn_logout.addActionListener(this);
        btn_logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        btn_playlist = new JButton("Music");
        btn_playlist.setBounds(20, 260, 210, 50);
         btn_playlist.setBackground(Color.darkGray);
        btn_playlist.setForeground(Color.white);
        btn_playlist.setFocusable(false);
        btn_playlist.setBorder(new LineBorder(Color.black));
        btn_playlist.setFont(new Font("Arial", Font.PLAIN, 24));
        btn_playlist.addActionListener(this);
        btn_playlist.setCursor(new Cursor(Cursor.HAND_CURSOR));
        

         delete_account = new JButton("Delete Account");
        delete_account.setBounds(20, 340, 210, 50);
         delete_account.setBackground(Color.darkGray);
        delete_account.setForeground(Color.white);
        delete_account.setFocusable(false);
        delete_account.setBorder(new LineBorder(Color.black));
        delete_account.setFont(new Font("Arial", Font.PLAIN, 24));
        delete_account.addActionListener(this);
        delete_account.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        change_password = new JButton("Change Password");
        change_password.setBounds(20, 420, 210, 50);
         change_password.setBackground(Color.darkGray);
        change_password.setForeground(Color.white);
        change_password.setFocusable(false);
        change_password.setBorder(new LineBorder(Color.black));
        change_password.setFont(new Font("Arial", Font.PLAIN, 24));
        change_password.addActionListener(this);
        change_password.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
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
        btn_play.setIcon(
                new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-play-48.png"));
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
        
       
        btn_play.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_pause.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_upload.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_volumedown.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        
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
        
        f.add(btn_pause);
        f.add(btn_add);
        f.add(btn_upload);
        f.add(btn_play);
        f.add(pane);
        // f.add(btn_volumedown);
        f.add(btn_icon);
        f.add(txt_display);
        f.add(no_of_songs);
      f.add(btn_remove);
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
           f.add(delete_account);
       f.add(change_password);
        f.add(lbl_addsongs);
        f.add(lbl_removesongs);
       // p.add(btn_signin);
       f.add(account_symbol);
        f.setSize(1200, 630);
        f.setLayout(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add_l = new JList();
        remove_l = new JList();
        //Option pane
        
        UIManager UI=new UIManager();
 UI.put("OptionPane.background", Color.white);
 UI.put("OptionPane.messageForeground", Color.black);
 UI.put("Panel.background", Color.white);
        //Option pane
        
        add_l.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                
                    add_lValueChanged(evt);
                
            }
        });
        
        remove_l.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                
                    remove_lValueChanged(evt);
                
            }
        });
        
        l.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                
                try {
                    lValueChanged(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(FavoritesFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            
        });

    }

    private static class FileTypeFilter extends FileFilter {

        private String extension;
        private String description;

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
            openFileChooser.setFileFilter(new FileTypeFilter(".mp3", "Open MP3 files Only!"));
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
        
        if(e.getSource()==btn_add)
        {
            int c;
            
            add_f = new JFrame();
            add_done = new JButton();
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
          
                String song = rs.getString("NAME");
                Df.addElement(song);

            }
            add_l.setModel(Df);
            

        } catch (ClassNotFoundException classNotFoundException) {
            Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, classNotFoundException);
        } catch (SQLException sQLException) {
            Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, sQLException);
        } catch (HeadlessException headlessException) {
        }
          add_l.setDragEnabled(true);
        add_l.setFixedCellHeight(60);
        add_l.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add_l.setFont(new Font("Arial", Font.PLAIN, 21));
        add_l.setVisibleRowCount(4);
        add_l.setForeground(Color.white);
        add_l.setSelectionBackground(Color.darkGray);
        add_l.setSelectionForeground(Color.cyan);

        // l.setBounds(100, 100, 700, 500);
        add_l.setBackground(Color.black);
        add_pane = new JScrollPane(add_l);
        add_pane.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add_pane.setBackground(Color.black);
        add_pane.setBounds(20, 20, 920, 370);  
        add_done.setBounds(450, 390, 70, 70);
        add_done.setBackground(Color.black);
        add_done.setBorder(new LineBorder(Color.black ,1));
        add_done.setFocusable(false);
        add_done.addActionListener(this);
        add_done.setIcon(
                new javax.swing.ImageIcon("D:\\Downloads\\Done-64-128px\\icons8-done-64.png"));
        add_f.setVisible(true);
        add_f.setSize(960 , 500);
               add_f.add(add_done);
               add_f.add(add_pane);

        add_f.getContentPane().setBackground(Color.black);
        add_f.setLayout(null);
        
        }
        
        if(e.getSource() == btn_remove)
        {
            
            System.out.println(user);
              int c;
            
            remove_f = new JFrame();
            remove_done = new JButton();
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
               
                if(rs.getString(user).equals("1") )
                {
                    
                
                
                String song = rs.getString("NAME");
                Df.addElement(song);
                }

            }
            remove_l.setModel(Df);

        } catch (ClassNotFoundException classNotFoundException) {
            Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, classNotFoundException);
        } catch (SQLException sQLException) {
            Logger.getLogger(MusicSample.class.getName()).log(Level.SEVERE, null, sQLException);
        } catch (HeadlessException headlessException) {
        }
          remove_l.setDragEnabled(true);
        remove_l.setFixedCellHeight(60);
        remove_l.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        remove_l.setFont(new Font("Arial", Font.PLAIN, 21));
        remove_l.setVisibleRowCount(4);
        remove_l.setForeground(Color.white);
        remove_l.setSelectionBackground(Color.darkGray);
        remove_l.setSelectionForeground(Color.cyan);

        // l.setBounds(100, 100, 700, 500);
        remove_l.setBackground(Color.black);
        remove_pane = new JScrollPane(remove_l);
        remove_pane.setCursor(new Cursor(Cursor.HAND_CURSOR));
        remove_pane.setBackground(Color.black);
        remove_pane.setBounds(20, 20, 920, 370);  
        remove_done.setBounds(450, 390, 70, 70);
        remove_done.setBackground(Color.black);
        remove_done.setBorder(new LineBorder(Color.black ,1));
        remove_done.setFocusable(false);
        remove_done.addActionListener(this);
        remove_done.setIcon(
                new javax.swing.ImageIcon("D:\\Downloads\\Done-64-128px\\icons8-done-64.png"));
        remove_f.setVisible(true);
        remove_f.setSize(960 , 500);
               remove_f.add(remove_done);
               remove_f.add(remove_pane);

        remove_f.getContentPane().setBackground(Color.black);
        remove_f.setLayout(null);
        }
        
        if(e.getSource()== remove_done)
        {
            System.out.println("button sada");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer?useSSL=false", "root",
                        "Sharu#2022");
            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FavoritesFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FavoritesFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

          remove_f.dispose();
          f.dispose();
          new FavoritesFrame(true , user);
        }
        
        if(e.getSource()== add_done)
        {
            System.out.println("button sada");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer?useSSL=false", "root",
                        "Sharu#2022");
            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FavoritesFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FavoritesFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

          add_f.dispose();
          f.dispose();
          new FavoritesFrame(true , user);
   
        }
        
        if(e.getSource() == btn_logout)
        {
            
            {
                if(player != null)
                    player.stop();
                f.dispose();
                new MusicSample(false , "");
            }
        }
        
        if(e.getSource() == btn_playlist)
        {
         if(player != null)
                player.stop();
            f.dispose();
             new MusicSample(true ,user);

        }
        if(e.getSource() == btn_signin)
        {
            if(sigined_in)
            {
                JOptionPane.showMessageDialog(null,"You are already Signed In");
            }
            

        }
      
    }
		
    private MP3Player mp3Player() {
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }
    
        private void add_lValueChanged(javax.swing.event.ListSelectionEvent e) {
                            System.out.println("hello");
				java.util.List<String> obj = add_l.getSelectedValuesList();
                                System.out.println(obj);
                                
                                for(String str : obj)
                                {
                                    System.out.println(str);
                                    try {
                                        System.out.println(user);
                                        insert = con.prepareStatement("update music set "+ user + "=? where NAME =?");
                                         insert.setString(1, "1");
                                        insert.setString(2, str);
                                        insert.executeUpdate();
                                        System.out.println(str);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(FavoritesFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                   
                
                                }
					
			}
    
    
    private void remove_lValueChanged(javax.swing.event.ListSelectionEvent e) {
                            System.out.println("hello");
				java.util.List<String> obj = remove_l.getSelectedValuesList();
                                System.out.println(obj);
                                
                                for(String str : obj)
                                {
                                    System.out.println(str);
                                    try {
                                        System.out.println(user);
                                        insert = con.prepareStatement("update music set "+ user + "=? where NAME =?");
                                         insert.setString(1, "0");
                                        insert.setString(2, str);
                                        insert.executeUpdate();
                                        System.out.println(str);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(FavoritesFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                   
                
                                }
					
			}

                    public void valueChanged(ListSelectionEvent e) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        new FavoritesFrame(false , "");
    }

}

class MyListCellThing extends JLabel implements ListCellRenderer {

    public MyListCellThing() {
        // setOpaque(true);

    }

    public MyListCellThing getListCellRendererComponent(JList l, String[] songlist, int index, boolean isSelected,
            boolean cellHasFocus) {
        // Assumes the stuff in the list has a pretty toString
        // setText(songlist.toString());

        // based on the index you set the color. This produces the every other effect.
        if (index % 2 == 0)
            l.setBackground(Color.BLACK);
        else
            l.setBackground(Color.DARK_GRAY);
        return this;

    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }
}
