
package ut04.propuestos.ejercicio05;

import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MailManager extends javax.swing.JFrame{

    public MailManager() {
        initComponents();
        
        DefaultTableModel model = (DefaultTableModel) tableEmails.getModel();
        model.setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmails = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fieldTo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fieldSubject = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        fieldMessage = new javax.swing.JTextPane();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fieldPassword = new javax.swing.JPasswordField();
        fieldEmail = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MailManager");

        tableEmails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Correo", "Asunto", "Mensaje"
            }
        ));
        tableEmails.setEnabled(false);
        jScrollPane1.setViewportView(tableEmails);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Correos Recibidos");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mensaje Nuevo");

        fieldTo.setEnabled(false);

        jLabel4.setText("Para");

        jLabel5.setText("Asunto");

        fieldSubject.setEnabled(false);

        fieldMessage.setEnabled(false);
        jScrollPane3.setViewportView(fieldMessage);

        jLabel6.setText("Correo");

        jLabel7.setText("Contraseña");

        btnLogin.setText("Iniciar Sesion");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Correo Electrónico");

        btnSend.setText("Enviar");
        btnSend.setEnabled(false);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(fieldPassword)
                        .addGap(208, 208, 208))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fieldEmail)
                        .addGap(18, 18, 18)
                        .addComponent(btnLogin)
                        .addGap(91, 91, 91))))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSend))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldSubject)
                            .addComponent(fieldTo)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLogin)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String email = fieldEmail.getText();
        char[] passwordChars = fieldPassword.getPassword();
        String password = new String(passwordChars);
        if(email != null && !email.isEmpty() && !password.isEmpty()){
            if(login(email, password)){
                fieldTo.setEnabled(true);
                fieldSubject.setEnabled(true);
                fieldMessage.setEnabled(true);
                btnSend.setEnabled(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, introduce tanto el correo como la contraseña.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String email = fieldTo.getText();
        String subject = fieldSubject.getText();
        String message = fieldMessage.getText();
        if(email != null && !email.isEmpty() && subject != null && !subject.isEmpty() && message != null && !message.isEmpty()){
            send(email, subject, message);
        }else{
            JOptionPane.showMessageDialog(null, "No puede haber campos de texto vacios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MailManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new MailManager().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSend;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextPane fieldMessage;
    private javax.swing.JPasswordField fieldPassword;
    private javax.swing.JTextField fieldSubject;
    private javax.swing.JTextField fieldTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tableEmails;
    // End of variables declaration//GEN-END:variables

    private String EMAIL;
    private String PASSWORD;
    private Store STORE;
    private String PROVIDER;
    
        
    private static String getProviderFromEmail(String email) {
        String[] parts = email.split("@");

        if (parts.length != 2) {
            throw new IllegalArgumentException("La dirección de correo electrónico no tiene el formato esperado");
        }
        
        String[] domainParts = parts[1].split("\\.");
        if (domainParts.length != 2) {
            throw new IllegalArgumentException("Formato de dominio no válido en la dirección de correo electrónico");
        }
        
        return domainParts[0].toLowerCase();
    }
        
    private static Properties getImapConfig(String provider) {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        switch (provider.toLowerCase()) {
            case "gmail" -> {
                properties.put("mail.imaps.host", "imap.gmail.com");
                properties.put("mail.imaps.port", "993");
                properties.put("mail.imaps.ssl.enable", "true");
            }
            case "outlook" -> {
                properties.put("mail.imaps.host", "outlook.office365.com");
                properties.put("mail.imaps.port", "993");
                properties.put("mail.imaps.ssl.enable", "true");
            }
            case "yahoo" -> {
                properties.put("mail.imaps.host", "imap.mail.yahoo.com");
                properties.put("mail.imaps.port", "993");
                properties.put("mail.imaps.ssl.enable", "true");
            }
            case "icloud" -> {
                properties.put("mail.imaps.host", "imap.mail.me.com");
                properties.put("mail.imaps.port", "993");
                properties.put("mail.imaps.ssl.enable", "true");
            }
            default ->
                throw new IllegalArgumentException("Proveedor de correo no reconocido: " + provider);
        }
        return properties;
    }
    
    private boolean login(String email, String password) {
        try {
            PROVIDER = getProviderFromEmail(email);
            Properties properties = getImapConfig(PROVIDER);
            Session session = Session.getInstance(properties);
            
            STORE = session.getStore();
            STORE.connect(email, password);
            EMAIL = email;
            PASSWORD = password;
            
            listenNewMessages();
            
            return true;
        } catch (IllegalArgumentException  e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("IllegalArgumentException " + e);
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Error al Iniciar Sesion. Verifica los datos y vuelve a intentarlo.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("MessagingException " + e);
        }
        return false;
    }

    private void listenNewMessages() {
        new Thread(() -> {
            try {
                Folder inbox = STORE.getFolder("INBOX");
                inbox.open(Folder.READ_ONLY);
                int indexMessage = inbox.getMessageCount();
                inbox.close();
                while(true)
                {
                    indexMessage = isThereNewMessage(indexMessage);
                }
            } catch (MessagingException ex) {
                System.out.println("listenNewMessages MessagingException " + ex);
            } catch (IllegalStateException ex){
                System.out.println("listenNewMessages IllegalStateException " + ex);
            }
        }).start();
    }

    private int isThereNewMessage(int indexMessage) {
        try (
            Folder inbox = STORE.getFolder("INBOX");
        ){
            inbox.open(Folder.READ_ONLY);
            int lastIndexMessage = inbox.getMessageCount();
            if(indexMessage < lastIndexMessage)
            {
                Message[] lastMessages = inbox.getMessages(indexMessage + 1, lastIndexMessage);
                addMessages(lastMessages);
                return lastIndexMessage;
            }else{
                Thread.sleep(1000);
                return indexMessage;
            }
        } catch (MessagingException ex) {
            System.out.println("isThereNewMessage MessagingException " + ex);
        } catch (InterruptedException ex) {
            System.out.println("isThereNewMessage InterruptedException " + ex);
        } catch (IllegalStateException ex){
            System.out.println("isThereNewMessage IllegalStateException " + ex);
        }
        return 0;
    }
    
    private void addMessages(Message[] messages) throws MessagingException {
        DefaultTableModel model = (DefaultTableModel) tableEmails.getModel();
        for(Message m: messages)
        {
            String from = InternetAddress.toString(m.getFrom());
            String subject = m.getSubject();
//            String body = getTextFromMessage(message);
            String body = "";
            model.addRow(new Object[]{from, subject, body});
        }
    }
    
//    private String getTextFromMessage(Message message) {
//        try {
//            StringBuilder sb = new StringBuilder();
//            Object content = message.getContent();
//
//            if (content instanceof Multipart multipart) {
//                for (int i = 0; i < multipart.getCount(); i++) {
//                    BodyPart bodyPart = multipart.getBodyPart(i);
//                    System.out.println(bodyPart.getContent());
//                    if (bodyPart.getContentType().startsWith("text/plain")) {
//                        sb.append(bodyPart.getContent());
//                    }else if (bodyPart.getContentType().startsWith("text/html")) {
//                        sb.append("HTML Content: ").append(bodyPart.getContent());
//                    }
//                }
//            } else {
//                sb.append(content);
//            }
//
//            return sb.toString();
//        } catch (IOException | MessagingException e) {
//            return "";
//        }
//    }

    private void send(String email, String subject, String message) {
        try {
            String provider = getProviderFromEmail(email);
            Properties properties = getSmtpConfig(provider);
            Session session = Session.getInstance(properties);

            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(EMAIL));
            mimeMessage.setRecipients(RecipientType.TO, InternetAddress.parse(email));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport transport = session.getTransport("smtp");
            transport.connect(EMAIL, PASSWORD);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

            JOptionPane.showMessageDialog(null, "Correo enviado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (AuthenticationFailedException e) {
            JOptionPane.showMessageDialog(null, "Error de autenticación. Verifica el nombre de usuario y la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error de autenticación: " + e);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Error al enviar el correo. Verifica los datos y vuelve a intentarlo.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al enviar el correo: " + e);
        }
    }

    
    private static Properties getSmtpConfig(String provider) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        switch (provider.toLowerCase()) {
            case "gmail" -> {
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");
            }
            case "outlook" -> {
                properties.put("mail.smtp.host", "smtp.office365.com");
                properties.put("mail.smtp.port", "587");
            }
            case "yahoo" -> {
                properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
                properties.put("mail.smtp.port", "587");
            }
            case "icloud" -> {
                properties.put("mail.smtp.host", "smtp.mail.me.com");
                properties.put("mail.smtp.port", "587");
            }
            default -> throw new IllegalArgumentException("Proveedor de correo no reconocido: " + provider);
        }

        return properties;
    }
}
