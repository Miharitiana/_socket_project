package serveur;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import creatfile.FileCreate;

public class Serveur extends javax.swing.JFrame {
     static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
 
    public Serveur() {
        initComponents();
    }

    
   
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg_txt = new javax.swing.JTextField();
        Send = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        msg_area.setColumns(20);
        msg_area.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); 
        msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        msg_txt.setFont(new java.awt.Font("Segoe UI", 2, 12));
        msg_txt.setText("Message Serveur");
        msg_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_txtActionPerformed(evt);
            }
        });

        Send.setBackground(new java.awt.Color(0, 153, 153));
        Send.setFont(new java.awt.Font("Segoe UI Black", 2, 12));
        Send.setText("Send");
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(msg_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Send))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(msg_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }

    private void SendActionPerformed(java.awt.event.ActionEvent evt) {
    
         try{
        String msgout = "";
        msgout=msg_txt.getText().trim();
        msg_area.append("Serveur :" + msgout + "\n");
        dout.writeUTF(msgout);
        }catch(Exception e){
        
        }      
    }

    private void msg_txtActionPerformed(java.awt.event.ActionEvent evt) {
       
    }

       public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Serveur().setVisible(true);
            }
        });
        
         FileCreate f = new FileCreate();
        String nomFichier = "serveur";

       
            String msgin = "";
        try {
            ss = new ServerSocket(1029 );
           
            s = ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream()) ;
   
            while(!msgin.equals("quit")){
                msgin = din .readUTF();
                msg_area.setText(msg_area.getText().trim()+"\n Client :"+msgin+"\n"); 
                f.createFichier(nomFichier, msgin);
              }
        }catch(Exception e){
                e.printStackTrace();
       }
       
    }


    private static javax.swing.JButton Send;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private static javax.swing.JTextField msg_txt;
  
}
