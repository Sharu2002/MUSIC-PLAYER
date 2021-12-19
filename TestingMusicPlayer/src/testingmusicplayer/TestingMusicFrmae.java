/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package testingmusicplayer;

import jaco.mp3.player.MP3Player;
import java.io.File;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.util.Vector;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;



import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author sharu
 */
public class TestingMusicFrmae extends javax.swing.JFrame {

    /**
     * Creates new form MusicFrame
     */
    MP3Player player;
    File songFile;
    String currentDirectory = "D:\\Music";
    String currentPath;
    String imagePath;
    String appName = "Music Player";
    boolean repeat = false;
    boolean windowCollapsed = false;
    int xMouse , yMouse;
    Connection con;
    PreparedStatement insert;
    
    public TestingMusicFrmae() throws ClassNotFoundException, SQLException {
        initComponents();
        
       // play();
        
        
        songFile = new File("D:\\Music\\Imagine Dragons - Believer.mp3");
        String fileName = songFile.getName();
        songDisplay.setText(fileName);
        
        player = mp3Player();
        
        player.addToPlayList(songFile);
        
        currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        imagePath = "\\Iamges";
        
        
        int c;
        String[] s = new String[100];
        
        Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer?useSSL=false", "root",
                    "Sharu#2022");
            insert = con.prepareStatement("select * from music");

             DefaultListModel<String> dlm = new DefaultListModel<String>();
             jList1 = new JList<>(dlm);
            
            java.sql.ResultSet rs = insert.executeQuery();
            ResultSetMetaData Rss = (ResultSetMetaData) rs.getMetaData();

            c = Rss.getColumnCount();
            System.out.println(c);

            DefaultListModel Df = (DefaultListModel) jList1.getModel();

           //Df.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();
                
               System.out.println(rs.getString("NAME"));

                for (int a = 1; a <= c; a++) {
                    s[a] = new String();
                    s[a] = rs.getString("NAME");
                    System.out.println(s[a]);
                    dlm.addElement(rs.getString("NAME"));
                    dlm.addElement(rs.getString("URL"));
                }
                String[] strings = new String[500];
for(int  i = 0 ; i < s.length;i++)
            {
                strings[i] = new String();
                strings[i]  = s[i];
            }
               // Df.addRow(v2);
            }
            jList1.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings  = {"HEsdfs" , "EFSdf" , "SDFSDF"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        
        
    
    }
                
    

    
//    public void play() throws ClassNotFoundException, SQLException
//    {
//        int c;
//        String[] s = new String[100];
//        
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer?useSSL=false", "root",
//                    "Sharu#2022");
//            insert = con.prepareStatement("select * from music");
//
//             DefaultListModel<String> dlm = new DefaultListModel<String>();
//             jList1 = new JList<>(dlm);
//            
//            java.sql.ResultSet rs = insert.executeQuery();
//            ResultSetMetaData Rss = (ResultSetMetaData) rs.getMetaData();
//
//            c = Rss.getColumnCount();
//
//            DefaultListModel Df = (DefaultListModel) jList1.getModel();
//
////            Df.setRowCount(0);
//
//            while (rs.next()) {
//                Vector v2 = new Vector();
//                
//
//                for (int a = 1; a <= c; a++) {
//                    s[a] = new String();
//                    s[a] = rs.getString("NAME");
//                  System.out.println(rs.getString("NAME"));
//                    dlm.addElement(rs.getString("NAME"));
//                    dlm.addElement(rs.getString("URL"));
//                }
//
//               // Df.addRow(v2);
//            }
//            
//            jList1.setModel(new javax.swing.AbstractListModel<String>() {
//            String[] strings =s ;
//            public int getSize() { return s.length; }
//            public String getElementAt(int i) { return s[i]; }
//        });
//
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_next = new javax.swing.JButton();
        btn_pause = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        btn_play = new javax.swing.JButton();
        btn_upload = new javax.swing.JButton();
        btn_vold = new javax.swing.JButton();
        btn_volu = new javax.swing.JButton();
        songDisplay = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Music Player");
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        btn_next.setBackground(new java.awt.Color(0, 0, 0));
        btn_next.setIcon(new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-end-48.png")); // NOI18N

        btn_pause.setBackground(new java.awt.Color(0, 0, 0));
        btn_pause.setIcon(new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-pause-48.png")); // NOI18N
        btn_pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pauseActionPerformed(evt);
            }
        });

        btn_prev.setBackground(new java.awt.Color(0, 0, 0));
        btn_prev.setIcon(new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-skip-to-start-48.png")); // NOI18N

        btn_play.setBackground(new java.awt.Color(0, 0, 0));
        btn_play.setIcon(new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-play-48.png")); // NOI18N
        btn_play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_playActionPerformed(evt);
            }
        });

        btn_upload.setBackground(new java.awt.Color(0, 0, 0));
        btn_upload.setIcon(new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-upload-48.png")); // NOI18N
        btn_upload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_uploadMouseClicked(evt);
            }
        });
        btn_upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_uploadActionPerformed(evt);
            }
        });

        btn_vold.setBackground(new java.awt.Color(0, 0, 0));
        btn_vold.setIcon(new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-low-volume-50.png")); // NOI18N
        btn_vold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voldActionPerformed(evt);
            }
        });

        btn_volu.setBackground(new java.awt.Color(0, 0, 0));
        btn_volu.setIcon(new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-audio-50.png")); // NOI18N
        btn_volu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voluActionPerformed(evt);
            }
        });

        songDisplay.setEditable(false);
        songDisplay.setBackground(new java.awt.Color(0, 0, 0));
        songDisplay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        songDisplay.setForeground(new java.awt.Color(255, 255, 255));
        songDisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(songDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btn_vold, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_upload, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_prev, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_play, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_pause, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_volu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(songDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_next, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_pause, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_prev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_play, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_upload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_vold, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addComponent(btn_volu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(0, 0, 0));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Song");
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(0, 0, 0));
        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("Recent Plays");
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(0, 0, 0));
        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("PlayList");
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(0, 0, 0));
        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("Now Playing");
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(0, 0, 0));
        jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("Settings");
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 36)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(38, 226, 226));
        jTextField1.setText("My Music");
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(0, 0, 0));

        jList1.setBackground(new java.awt.Color(0, 0, 0));
        jList1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jList1.setForeground(new java.awt.Color(255, 255, 255));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "1", "1", "1", "1", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setFixedCellHeight(80);
        jList1.setSelectionBackground(new java.awt.Color(0, 0, 0));
        jList1.setSelectionForeground(new java.awt.Color(38, 226, 226));
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setIcon(new javax.swing.ImageIcon("D:\\Users\\Sharu\\NetBeansProjects\\MusicPlayer\\icons8-music-record-50.png")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jSeparator1)
                                .addGap(16, 16, 16))
                            .addComponent(jScrollPane1)))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 584, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_playActionPerformed

    player.play();
    }//GEN-LAST:event_btn_playActionPerformed

    private void btn_pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pauseActionPerformed
        player.pause();
    }//GEN-LAST:event_btn_pauseActionPerformed

    private void btn_uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_uploadActionPerformed
        // TODO add your handling code here:
        JFileChooser openFileChooser = new JFileChooser(currentDirectory);
        openFileChooser.setFileFilter(new FileTypeFilter(".mp3","Open MP3 files Only!"));
        int result = openFileChooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            songFile = openFileChooser.getSelectedFile();
            player.addToPlayList(songFile);
            player.skipForward();
            currentDirectory = songFile.getAbsolutePath();
            songDisplay.setText(songFile.getName());
        }
    }//GEN-LAST:event_btn_uploadActionPerformed

    private void btn_uploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_uploadMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_uploadMouseClicked

    private void btn_voluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voluActionPerformed
        volumeUpControl(0.1);
    }//GEN-LAST:event_btn_voluActionPerformed

    private void btn_voldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voldActionPerformed
        volumeDownControl(0.1);
    }//GEN-LAST:event_btn_voldActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        String selectedElement = (String) jList1.getSelectedValue();
        if(selectedElement == "Imagine Dragons  -  Believer" )
        {
            player.stop();
            songFile = new File("D:\\Music\\Imagine Dragons - Believer.mp3");
            String fileName = songFile.getName();
            songDisplay.setText(fileName);

            player = mp3Player();

            player.addToPlayList(songFile);

            currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            imagePath = "\\Iamges";
            player.play();
        }

        else if(selectedElement == "Bazzi - Mine")
        {
            player.stop();
            songFile = new File("D:\\Music\\Bazzi Mine.mp3");
            String fileName = songFile.getName();
            songDisplay.setText(fileName);

            player = mp3Player();

            player.addToPlayList(songFile);

            currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            imagePath = "\\Iamges";
            player.play();
        }

        else if(selectedElement == "Imagine Dragons  -  What Ever It Takes")
        {
            player.stop();
            songFile = new File("D:\\Music\\Imagine Dragons - Whatever It Takes (Lyrics - Lyric Video) (320  kbps) (mp3juices.site).mp3");
            String fileName = songFile.getName();
            songDisplay.setText(fileName);

            player = mp3Player();

            player.addToPlayList(songFile);

            currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            imagePath = "\\Iamges";
            player.play();
        }

        else if(selectedElement == "Imagine Dragons  -RadioActive")
        {
            player.stop();
            songFile = new File("D:\\Music\\Imagine Dragons - Radioactive.mp3");
            String fileName = songFile.getName();
            songDisplay.setText(fileName);

            player = mp3Player();

            player.addToPlayList(songFile);

            currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            imagePath = "\\Iamges";
            player.play();
        }

        else if(selectedElement == "Imagine Dragons - BleedingOut")
        {
            player.stop();
            songFile = new File("D:\\Music\\Imagine Dragons - Bleeding Out Lyrics.mp3");
            String fileName = songFile.getName();
            songDisplay.setText(fileName);

            player = mp3Player();

            player.addToPlayList(songFile);

            currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            imagePath = "\\Iamges";
            player.play();
        }

        else if(selectedElement == "Twenty One Pilots - Chlorine")
        {
            player.stop();
            songFile = new File("D:\\Music\\twenty one pilots - Chlorine (Official Video) (320  kbps) (mp3juices.site).mp3");
            String fileName = songFile.getName();
            songDisplay.setText(fileName);

            player = mp3Player();

            player.addToPlayList(songFile);

            currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            imagePath = "\\Iamges";
            player.play();
        }
        else if(selectedElement == "Ed Sheeran - Shape Of You")
        {
            player.stop();
            songFile = new File("D:\\Music\\Ed Sheeran  Shape of You Official Video.mp3");
            String fileName = songFile.getName();
            songDisplay.setText(fileName);

            player = mp3Player();

            player.addToPlayList(songFile);

            currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            imagePath = "\\Iamges";
            player.play();
        }

        else if(selectedElement == "Prefect - Ed Sheeran")
        {
            player.stop();
            songFile = new File("D:\\Music\\ed_sheeran_-_perfect.mp3");
            String fileName = songFile.getName();
            songDisplay.setText(fileName);

            player = mp3Player();

            player.addToPlayList(songFile);

            currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            imagePath = "\\Iamges";
            player.play();
        }
        else if(selectedElement == "Manike Mahe Kitae - Yohani")
        {
            player.stop();
            songFile = new File("D:\\Music\\Manike Mage Hithe.mp3");
            String fileName = songFile.getName();
            songDisplay.setText(fileName);

            player = mp3Player();

            player.addToPlayList(songFile);

            currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            imagePath = "\\Iamges";
            player.play();
        }
    }//GEN-LAST:event_jList1ValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestingMusicFrmae.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TestingMusicFrmae().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TestingMusicFrmae.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TestingMusicFrmae.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_pause;
    private javax.swing.JButton btn_play;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_upload;
    private javax.swing.JButton btn_vold;
    private javax.swing.JButton btn_volu;
    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField songDisplay;
    // End of variables declaration//GEN-END:variables
private MP3Player mp3Player()
{
    MP3Player mp3Player = new MP3Player();
    return mp3Player;
}



private void volumeDownControl(Double valueMinus)
{
    Mixer.Info[] mixers = AudioSystem.getMixerInfo();
    
    for(Mixer.Info mixerInfo : mixers)
    {
        Mixer mixer = AudioSystem.getMixer(mixerInfo);
        
        Line.Info[] lineInfos = mixer.getTargetLineInfo();
        
        for(Line.Info lineInfo : lineInfos)
        {
           Line line = null;
           boolean opened = true;
           
           try{
           line = mixer.getLine(lineInfo);
           opened = line.isOpen() || line instanceof Clip;
           
           if(!opened)
           {
               line.open();
           }
           FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
           
           float currentVolume = volControl.getValue();
           Double volumeToCut = valueMinus;
           float changeCalc = (float) ((float)currentVolume - (double)volumeToCut);
           volControl.setValue(changeCalc);
           }catch (LineUnavailableException exception){
           }catch (IllegalArgumentException illException){}
           finally{
               if(line != null && !opened)
               {
                   line.close();
               }
           }
                   
        }
    }
}

private void volumeUpControl(Double valueMinus)
{
    Mixer.Info[] mixers = AudioSystem.getMixerInfo();
    
    for(Mixer.Info mixerInfo : mixers)
    {
        Mixer mixer = AudioSystem.getMixer(mixerInfo);
        
        Line.Info[] lineInfos = mixer.getTargetLineInfo();
        
        for(Line.Info lineInfo : lineInfos)
        {
           Line line = null;
           boolean opened = true;
           
           try{
           line = mixer.getLine(lineInfo);
           opened = line.isOpen() || line instanceof Clip;
           
           if(!opened)
           {
               line.open();
           }
           FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
           
           float currentVolume = volControl.getValue();
           Double volumeToCut = valueMinus;
           float changeCalc = (float) ((float)currentVolume + (double)volumeToCut);
           volControl.setValue(changeCalc);
           }catch (LineUnavailableException exception){
           }catch (IllegalArgumentException illException){}
           finally{
               if(line != null && !opened)
               {
                   line.close();
               }
           }
                   
        }
    }
}

private void volumeControl(Double valueMinus)
{
    Mixer.Info[] mixers = AudioSystem.getMixerInfo();
    
    for(Mixer.Info mixerInfo : mixers)
    {
        Mixer mixer = AudioSystem.getMixer(mixerInfo);
        
        Line.Info[] lineInfos = mixer.getTargetLineInfo();
        
        for(Line.Info lineInfo : lineInfos)
        {
           Line line = null;
           boolean opened = true;
           
           try{
           line = mixer.getLine(lineInfo);
           opened = line.isOpen() || line instanceof Clip;
           
           if(!opened)
           {
               line.open();
           }
           FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
           
           float currentVolume = volControl.getValue();
           Double volumeToCut = valueMinus;
           float changeCalc = (float) ((double)volumeToCut);
           volControl.setValue(changeCalc);
           }catch (LineUnavailableException exception){
           }catch (IllegalArgumentException illException){}
           finally{
               if(line != null && !opened)
               {
                   line.close();
               }
           }
                   
        }
    }
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
    
    
    

}



