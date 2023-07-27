package org.example.utils.emailGeneration;


public class EmailGeneration {
/*

    public void sample(){

        File file=new File("target/extentReport/index.html");
       // File file=new File("D:\\Dinesh_Arul\\Automation_Projects\\UI\\Receipts\\Flight_Shopping_E2E\\pom.xml");

        Properties properties=new Properties();
        properties.put("mail.smtp.host", "smtphqs.coair.com");
        properties.setProperty("mail.port", "25");
        try{

        BodyPart messageBodyPart=new MimeBodyPart();
            messageBodyPart.setText("Deekay");
      //  BodyPart attachmentBodyPart=new MimeBodyPart();
       // String pathofreport="D:\\Dinesh_Arul\\Automation_Projects\\UI\\Receipts\\Flight_Shopping_E2E\\target\\extentReport\\index.html";
       //     String pathofreport="D:\\Dinesh_Arul\\Automation_Projects\\UI\\Receipts\\Flight_Shopping_E2E\\pom.xml";
      //  DataSource source=new FileDataSource(pathofreport);
      //  attachmentBodyPart.setDataHandler(new DataHandler(source));
      //  attachmentBodyPart.setFileName(source.getName());

        MimeBodyPart mimeBodyPart=new MimeBodyPart();
        mimeBodyPart.attachFile(file);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
          //  multipart.addBodyPart(attachmentBodyPart);
            multipart.addBodyPart(mimeBodyPart);


        Session session=Session.getInstance(properties,new jakarta.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("","");}
            }
        );

            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress("EQA-CSL-AUTO-DONOTREPLY@united.com"));
            message.setContent(multipart);
            //abrar.syed@united.com
            //,anil.puli@united.com,ranjit.narravula@united.com,mahendra.nagireddy@united.com
            message.addRecipients(Message.RecipientType.TO,InternetAddress.parse("dinesh.arul@united.com"));
            message.addRecipients(Message.RecipientType.CC,InternetAddress.parse(""));
            message.setSubject("Flight_Shopping_ReportFrom_RestAssured");
            message.setText("Hi All,\n" +
                    "\n" +
                    "Good Day!\n" +
                    "\n" +
                    "Plese find the Sample Report for the FlightShopping Execution.\n" +
                    "\n" +
                    "Regards,\n" +
                    "FlightShopping Team.");
            Transport.send(message);
            System.out.println("Mail Sent Successfully");
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

         */
}
