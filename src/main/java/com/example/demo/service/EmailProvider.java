package com.example.demo.service;

import com.example.demo.exceptions.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailProvider {

    @Autowired
    private JavaMailSender mailSender;

    public String sendConfirmationEmail(String receiver,String title)throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        System.out.println("receiver "+receiver);
        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        try {
            helper.setTo(receiver);
            helper.setText("\n" +
                    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                    "<html style=\"-moz-osx-font-smoothing: grayscale; -webkit-font-smoothing: antialiased; background-color: #464646; margin: 0; padding: 0;\">\n" +
                    "    <head>\n" +
                    "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "        <meta name=\"format-detection\" content=\"telephone=no\">\n" +
                    "        <title>Blog AppEmail</title>\n" +
                    "        \n" +
                    "    </head>\n" +
                    "    <body bgcolor=\"#d7d7d7\" class=\"generic-template\" style=\"-moz-osx-font-smoothing: grayscale; -webkit-font-smoothing: antialiased; background-color: #d7d7d7; margin: 0; padding: 0;\">\n" +
                    "        <!-- Header Start -->\n" +
                    "        <div class=\"bg-white header\" bgcolor=\"#ffffff\" style=\"background-color: white; width: 100%;\">\n" +
                    "            <table align=\"center\" bgcolor=\"#ffffff\" style=\"border-left: 10px solid white; border-right: 10px solid white; max-width: 600px; width: 100%;\">\n" +
                    "                <tr height=\"80\">\n" +
                    "                    <td align=\"left\" class=\"vertical-align-middle\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: middle;\">\n" +
                    "                        <a href=\"https://www.go.com.mt/\" target=\"_blank\" style=\"-webkit-text-decoration-color: #F16522; color: #F16522; text-decoration: none; text-decoration-color: #F16522;\">\n" +
                    "                            <span style=\"border: 0; font-size: 32px; margin: 0; max-width: 100%; padding: 0;color: black\">Blog App</span>\n" +
                    "                        </a>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "        </div>\n" +
                    "        <!-- Header End -->\n" +
                    "\n" +
                    "        <!-- Content Start -->\n" +
                    "        <table cellpadding=\"0\" cellspacing=\"0\" cols=\"1\" bgcolor=\"#d7d7d7\" align=\"center\" style=\"max-width: 600px;\">\n" +
                    "            <tr bgcolor=\"#d7d7d7\">\n" +
                    "                <td height=\"50\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "            </tr>\n" +
                    "\n" +
                    "            <!-- This encapsulation is required to ensure correct rendering on Windows 10 Mail app. -->\n" +
                    "            <tr bgcolor=\"#d7d7d7\">\n" +
                    "                <td style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\">\n" +
                    "                    <!-- Seperator Start -->\n" +
                    "                    <table cellpadding=\"0\" cellspacing=\"0\" cols=\"1\" bgcolor=\"#d7d7d7\" align=\"center\" style=\"max-width: 600px; width: 100%;\">\n" +
                    "                        <tr bgcolor=\"#d7d7d7\">\n" +
                    "                            <td height=\"30\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "                        </tr>\n" +
                    "                    </table>\n" +
                    "                    <!-- Seperator End -->\n" +
                    "\n" +
                    "                    <!-- Generic Pod Left Aligned Start -->\n" +
                    "                    <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" cols=\"3\" bgcolor=\"white\" class=\"bordered-left-right\" style=\"border-left: 10px solid #d7d7d7; border-right: 10px solid #d7d7d7; max-width: 600px; width: 100%;\">\n" +
                    "                        <tr height=\"50\"><td colspan=\"3\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td></tr>\n" +
                    "                        <tr align=\"center\">\n" +
                    "                            <td width=\"36\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "                            <td class=\"text-primary\" style=\"color: #F16522; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\">\n" +
                    "                                <h1 style=\"color: #F16522; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 30px; font-weight: 700; line-height: 34px; margin-bottom: 0; margin-top: 0;\">Your Blog App account has been registered</h1>\n" +
                    "                            </td>\n" +
                    "                            <td width=\"36\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "                        </tr>\n" +
                    "                        <tr height=\"30\"><td colspan=\"3\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td></tr>\n" +
                    "                        <tr align=\"left\">\n" +
                    "                            <td width=\"36\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "                            <td style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\">\n" +
                    "                                <p style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 22px; margin: 0;\">Hi,  \n" +
                    "</p>\n" +
                    "                                <br>\n" +
                    "                                <p style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 22px; margin: 0;\">\n" +
                    "                                  click the link below to continue \n" +
                    "</p>\n" +
                    "<br/>\n" +
                    "                         <div align=\"center\" style=\"padding:14px 20px 14px 20px;background-color:#F16522;border-radius:4px\">\n" +
                    "<a href=\"#\" style=\"font-family:helvetica neue,helvetica,arial,sans-serif;font-weight:bold;font-size:18px;line-height:1.5;color:#ffffff;text-decoration:none;display:block;text-align:center;max-width:400px;overflow:hidden;text-overflow:ellipsis\">\n" +
                    "Click\n" +
                    "</a>\n" +
                    "</div>\n" +
                    "<br/><br/>\n" +
                    "          \n" +
                    "                                                              <p style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 22px; margin: 0;\">\n" +
                    "\n" +
                    "Best regards\n" +
                    "</p><br/>\n" +
                    "                              <p style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 22px; margin: 0;\">\n" +
                    "Blog App\n" +
                    "</p>\n" +
                    "                            </td>\n" +
                    "  \n" +
                    "                        </tr>\n" +
                    "\n" +
                    "                        \n" +
                    "       \n" +
                    "                        <tr height=\"50\"><td colspan=\"3\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td></tr>\n" +
                    "                    </table>\n" +
                    "                    <!-- Generic Pod Left Aligned End -->\n" +
                    "\n" +
                    "                    <!-- Seperator Start -->\n" +
                    "                    <table cellpadding=\"0\" cellspacing=\"0\" cols=\"1\" bgcolor=\"#d7d7d7\" align=\"center\" style=\"max-width: 600px; width: 100%;\">\n" +
                    "                        <tr bgcolor=\"#d7d7d7\">\n" +
                    "                            <td height=\"50\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td>\n" +
                    "                        </tr>\n" +
                    "                    </table>\n" +
                    "                    <!-- Seperator End -->\n" +
                    "\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "        </table>\n" +
                    "        <!-- Content End -->\n" +
                    "\n" +
                    "        <!-- Footer Start -->\n" +
                    "        <div class=\"bg-gray-dark footer\" bgcolor=\"#464646\" height=\"165\" style=\"background-color: #464646; width: 100%;\">\n" +
                    "            <table align=\"center\" bgcolor=\"#464646\" style=\"max-width: 600px; width: 100%;\">\n" +
                    "                \n" +
                    "                <tr height=\"15\"><td style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td></tr>\n" +
                    "\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\">\n" +
                    "                        <span width=\"50\" style=\"border: 32px; font-size: 26px; margin: 0; max-width: 100%; padding: 0;color: #F16522;\">Blog App</span>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                \n" +
                    "                <tr height=\"2\"><td style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td></tr>\n" +
                    "\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\">\n" +
                    "                        <p class=\"text-white\" style=\"color: white; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 22px; margin: 0;\">Copyright nelCorp 2020. All rights reserved.</p>\n" +
                    "                        <p class=\"text-primary\" style=\"color: #F16522; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 22px; margin: 0;\">\n" +
                    "                        </p>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                \n" +
                    "                <tr height=\"15\"><td style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td></tr>\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\">\n" +
                    "                        <a href=\"#\" style=\"-webkit-text-decoration-color: #464646; color: #F16522; text-decoration: none; text-decoration-color: #464646;\"><img width=\"25\" htight=\"25\" src=\"http://dgtlmrktng.s3.amazonaws.com/go/emails/generic-email-template/fb.png\" target=\"_blank\" alt=\"Facebook\" style=\"border: 0; font-size: 0; margin: 0; max-width: 100%; padding: 0;\"></a>&nbsp;\n" +
                    "                        <a href=\"#\" style=\"-webkit-text-decoration-color: #464646; color: #F16522; text-decoration: none; text-decoration-color: #464646;\"><img width=\"25\" htight=\"25\" src=\"http://dgtlmrktng.s3.amazonaws.com/go/emails/generic-email-template/youtube.png\" target=\"_blank\" alt=\"Youtube\" style=\"border: 0; font-size: 0; margin: 0; max-width: 100%; padding: 0;\"></a>&nbsp;\n" +
                    "                        <a href=\"#\" style=\"-webkit-text-decoration-color: #464646; color: #F16522; text-decoration: none; text-decoration-color: #464646;\"><img width=\"25\" htight=\"25\" src=\"http://dgtlmrktng.s3.amazonaws.com/go/emails/generic-email-template/linkedin.png\" target=\"_blank\" alt=\"LinkedIn\" style=\"border: 0; font-size: 0; margin: 0; max-width: 100%; padding: 0;\"></a>&nbsp;\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr height=\"10\"><td style=\"color: #464646; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 16px; vertical-align: top;\"></td></tr>\n" +
                    "\n" +
                    "            </table>\n" +
                    "        </div>\n" +
                    "        <!-- Footer End -->\n" +
                    "    </body>\n" +
                    "</html>",true);
            helper.setSubject(title);
            mailSender.send(message);
        }catch (Exception c){
            System.out.println(c);
        }

        return "email sent";
    }

}
