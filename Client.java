
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import creatfile.FileCreate;


public class Client extends javax.swing.JFrame {
    
    static Socket s;
    static DataInputStream din ;
    static DataOutputStream dout;

    
    public Client() {
        initComponents();
    }
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        msg_txt = new javax.swing.JTextField();
        send = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 0, 51));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setName("Client");

        msg_txt.setFont(new java.awt.Font("Segoe UI", 2, 12)); 
        msg_txt.setText("message client");
        msg_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_txtActionPerformed(evt);
            }
        });

        send.setBackground(new java.awt.Color(0, 153, 153));
        send.setFont(new java.awt.Font("Segoe UI Black", 2, 14));
        send.setText("Send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        msg_area.setColumns(20);
        msg_area.setFont(new java.awt.Font("Trebuchet MS", 0, 12));
        msg_area.setRows(5);
        msg_area.setText("message\n");
        jScrollPane1.setViewportView(msg_area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(msg_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msg_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {
            
              try{

    String msgout = "";
    msgout = msg_txt.getText().trim(); 
    msg_area.append(" \n Client: " + msgout + "\n");
    dout.writeUTF(msgout);
    
        }catch(Exception e){
        }  
    }

    private void msg_txtActionPerformed(java.awt.event.ActionEvent evt) {
      
    }

   
    public static void main(String args[]) {
       
        FileCreate file=new FileCreate();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
        
         String nomFichier = "Client";
      
        try {
        s = new Socket("127.0.1.1",1029);
        System.out.println(s);
        din= new DataInputStream(s .getInputStream());
        dout =new DataOutputStream(s.getOutputStream());
        String msgin= "";

        
        while (!msgin.equals("quit")){
            msgin= din.readUTF();
            msg_area.setText(msg_area.getText().trim()+"\n Serveur:"+msgin);
            file.createFichier(nomFichier,msgin);
        } 
        }catch(Exception e){
        
        }
    }

    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private static javax.swing.JTextField msg_txt;
    private static javax.swing.JButton send;
  

}
