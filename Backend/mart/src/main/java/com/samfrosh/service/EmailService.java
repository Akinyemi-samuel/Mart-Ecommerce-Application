package com.samfrosh.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private final JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(EmailService.class);

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void send(String to, String email){
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(email, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom("akinyemisamuelayo@gmail.com");
            mimeMessageHelper.setSubject("confirm your email");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("Failed to send email" + e);
            throw new IllegalStateException("Failed to send email");
        }
    }

    public String registrationSuccessfulBuildEmail(String name) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""  + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }



    public String passwordResetTokenBuildemail(String lastName, String token){
       return  "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <meta name=\"color-scheme\" content=\"light dark\" />\n" +
                "    <meta name=\"supported-color-schemes\" content=\"light dark\" />\n" +
                "    <title></title>\n" +
                "    <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
                "    /* Base ------------------------------ */\n" +
                "    \n" +
                "    @import url(\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap\");\n" +
                "    body {\n" +
                "      width: 100% !important;\n" +
                "      height: 100%;\n" +
                "      margin: 0;\n" +
                "      -webkit-text-size-adjust: none;\n" +
                "    }\n" +
                "    \n" +
                "    a {\n" +
                "      color: #3869D4;\n" +
                "    }\n" +
                "    \n" +
                "    a img {\n" +
                "      border: none;\n" +
                "    }\n" +
                "    \n" +
                "    td {\n" +
                "      word-break: break-word;\n" +
                "    }\n" +
                "    \n" +
                "    .preheader {\n" +
                "      display: none !important;\n" +
                "      visibility: hidden;\n" +
                "      mso-hide: all;\n" +
                "      font-size: 1px;\n" +
                "      line-height: 1px;\n" +
                "      max-height: 0;\n" +
                "      max-width: 0;\n" +
                "      opacity: 0;\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "    /* Type ------------------------------ */\n" +
                "    \n" +
                "    body,\n" +
                "    td,\n" +
                "    th {\n" +
                "      font-family: \"Nunito Sans\", Helvetica, Arial, sans-serif;\n" +
                "    }\n" +
                "    \n" +
                "    h1 {\n" +
                "      margin-top: 0;\n" +
                "      color: #333333;\n" +
                "      font-size: 22px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    \n" +
                "    h2 {\n" +
                "      margin-top: 0;\n" +
                "      color: #333333;\n" +
                "      font-size: 16px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    \n" +
                "    h3 {\n" +
                "      margin-top: 0;\n" +
                "      color: #333333;\n" +
                "      font-size: 14px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    \n" +
                "    td,\n" +
                "    th {\n" +
                "      font-size: 16px;\n" +
                "    }\n" +
                "    \n" +
                "    p,\n" +
                "    ul,\n" +
                "    ol,\n" +
                "    blockquote {\n" +
                "      margin: .4em 0 1.1875em;\n" +
                "      font-size: 16px;\n" +
                "      line-height: 1.625;\n" +
                "    }\n" +
                "    \n" +
                "    p.sub {\n" +
                "      font-size: 13px;\n" +
                "    }\n" +
                "    /* Utilities ------------------------------ */\n" +
                "    \n" +
                "    .align-right {\n" +
                "      text-align: right;\n" +
                "    }\n" +
                "    \n" +
                "    .align-left {\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    \n" +
                "    .align-center {\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .u-margin-bottom-none {\n" +
                "      margin-bottom: 0;\n" +
                "    }\n" +
                "    /* Buttons ------------------------------ */\n" +
                "    \n" +
                "    .button {\n" +
                "      background-color: #3869D4;\n" +
                "      border-top: 10px solid #3869D4;\n" +
                "      border-right: 18px solid #3869D4;\n" +
                "      border-bottom: 10px solid #3869D4;\n" +
                "      border-left: 18px solid #3869D4;\n" +
                "      display: inline-block;\n" +
                "      color: #FFF;\n" +
                "      text-decoration: none;\n" +
                "      border-radius: 3px;\n" +
                "      box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16);\n" +
                "      -webkit-text-size-adjust: none;\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "    \n" +
                "    .button--green {\n" +
                "      background-color: #22BC66;\n" +
                "      border-top: 10px solid #22BC66;\n" +
                "      border-right: 18px solid #22BC66;\n" +
                "      border-bottom: 10px solid #22BC66;\n" +
                "      border-left: 18px solid #22BC66;\n" +
                "    }\n" +
                "    \n" +
                "    .button--red {\n" +
                "      background-color: #FF6136;\n" +
                "      border-top: 10px solid #FF6136;\n" +
                "      border-right: 18px solid #FF6136;\n" +
                "      border-bottom: 10px solid #FF6136;\n" +
                "      border-left: 18px solid #FF6136;\n" +
                "    }\n" +
                "    \n" +
                "    @media only screen and (max-width: 500px) {\n" +
                "      .button {\n" +
                "        width: 100% !important;\n" +
                "        text-align: center !important;\n" +
                "      }\n" +
                "    }\n" +
                "    /* Attribute list ------------------------------ */\n" +
                "    \n" +
                "    .attributes {\n" +
                "      margin: 0 0 21px;\n" +
                "    }\n" +
                "    \n" +
                "    .attributes_content {\n" +
                "      background-color: #F4F4F7;\n" +
                "      padding: 16px;\n" +
                "    }\n" +
                "    \n" +
                "    .attributes_item {\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    /* Related Items ------------------------------ */\n" +
                "    \n" +
                "    .related {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 25px 0 0 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .related_item {\n" +
                "      padding: 10px 0;\n" +
                "      color: #CBCCCF;\n" +
                "      font-size: 15px;\n" +
                "      line-height: 18px;\n" +
                "    }\n" +
                "    \n" +
                "    .related_item-title {\n" +
                "      display: block;\n" +
                "      margin: .5em 0 0;\n" +
                "    }\n" +
                "    \n" +
                "    .related_item-thumb {\n" +
                "      display: block;\n" +
                "      padding-bottom: 10px;\n" +
                "    }\n" +
                "    \n" +
                "    .related_heading {\n" +
                "      border-top: 1px solid #CBCCCF;\n" +
                "      text-align: center;\n" +
                "      padding: 25px 0 10px;\n" +
                "    }\n" +
                "    /* Discount Code ------------------------------ */\n" +
                "    \n" +
                "    .discount {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 24px;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "      background-color: #F4F4F7;\n" +
                "      border: 2px dashed #CBCCCF;\n" +
                "    }\n" +
                "    \n" +
                "    .discount_heading {\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .discount_body {\n" +
                "      text-align: center;\n" +
                "      font-size: 15px;\n" +
                "    }\n" +
                "    /* Social Icons ------------------------------ */\n" +
                "    \n" +
                "    .social {\n" +
                "      width: auto;\n" +
                "    }\n" +
                "    \n" +
                "    .social td {\n" +
                "      padding: 0;\n" +
                "      width: auto;\n" +
                "    }\n" +
                "    \n" +
                "    .social_icon {\n" +
                "      height: 20px;\n" +
                "      margin: 0 8px 10px 8px;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    /* Data table ------------------------------ */\n" +
                "    \n" +
                "    .purchase {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 35px 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_content {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 25px 0 0 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_item {\n" +
                "      padding: 10px 0;\n" +
                "      color: #51545E;\n" +
                "      font-size: 15px;\n" +
                "      line-height: 18px;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_heading {\n" +
                "      padding-bottom: 8px;\n" +
                "      border-bottom: 1px solid #EAEAEC;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_heading p {\n" +
                "      margin: 0;\n" +
                "      color: #85878E;\n" +
                "      font-size: 12px;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_footer {\n" +
                "      padding-top: 15px;\n" +
                "      border-top: 1px solid #EAEAEC;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_total {\n" +
                "      margin: 0;\n" +
                "      text-align: right;\n" +
                "      font-weight: bold;\n" +
                "      color: #333333;\n" +
                "    }\n" +
                "    \n" +
                "    .purchase_total--label {\n" +
                "      padding: 0 15px 0 0;\n" +
                "    }\n" +
                "    \n" +
                "    body {\n" +
                "      background-color: #F2F4F6;\n" +
                "      color: #51545E;\n" +
                "    }\n" +
                "    \n" +
                "    p {\n" +
                "      color: #51545E;\n" +
                "    }\n" +
                "    \n" +
                "    .email-wrapper {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "      background-color: #F2F4F6;\n" +
                "    }\n" +
                "    \n" +
                "    .email-content {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "    }\n" +
                "    /* Masthead ----------------------- */\n" +
                "    \n" +
                "    .email-masthead {\n" +
                "      padding: 25px 0;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .email-masthead_logo {\n" +
                "      width: 94px;\n" +
                "    }\n" +
                "    \n" +
                "    .email-masthead_name {\n" +
                "      font-size: 16px;\n" +
                "      font-weight: bold;\n" +
                "      color: #A8AAAF;\n" +
                "      text-decoration: none;\n" +
                "      text-shadow: 0 1px 0 white;\n" +
                "    }\n" +
                "    /* Body ------------------------------ */\n" +
                "    \n" +
                "    .email-body {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .email-body_inner {\n" +
                "      width: 570px;\n" +
                "      margin: 0 auto;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 570px;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "      background-color: #FFFFFF;\n" +
                "    }\n" +
                "    \n" +
                "    .email-footer {\n" +
                "      width: 570px;\n" +
                "      margin: 0 auto;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 570px;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .email-footer p {\n" +
                "      color: #A8AAAF;\n" +
                "    }\n" +
                "    \n" +
                "    .body-action {\n" +
                "      width: 100%;\n" +
                "      margin: 30px auto;\n" +
                "      padding: 0;\n" +
                "      -premailer-width: 100%;\n" +
                "      -premailer-cellpadding: 0;\n" +
                "      -premailer-cellspacing: 0;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .body-sub {\n" +
                "      margin-top: 25px;\n" +
                "      padding-top: 25px;\n" +
                "      border-top: 1px solid #EAEAEC;\n" +
                "    }\n" +
                "    \n" +
                "    .content-cell {\n" +
                "      padding: 45px;\n" +
                "    }\n" +
                "    /*Media Queries ------------------------------ */\n" +
                "    \n" +
                "    @media only screen and (max-width: 600px) {\n" +
                "      .email-body_inner,\n" +
                "      .email-footer {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "    }\n" +
                "    \n" +
                "    @media (prefers-color-scheme: dark) {\n" +
                "      body,\n" +
                "      .email-body,\n" +
                "      .email-body_inner,\n" +
                "      .email-content,\n" +
                "      .email-wrapper,\n" +
                "      .email-masthead,\n" +
                "      .email-footer {\n" +
                "        background-color: #333333 !important;\n" +
                "        color: #FFF !important;\n" +
                "      }\n" +
                "      p,\n" +
                "      ul,\n" +
                "      ol,\n" +
                "      blockquote,\n" +
                "      h1,\n" +
                "      h2,\n" +
                "      h3,\n" +
                "      span,\n" +
                "      .purchase_item {\n" +
                "        color: #FFF !important;\n" +
                "      }\n" +
                "      .attributes_content,\n" +
                "      .discount {\n" +
                "        background-color: #222 !important;\n" +
                "      }\n" +
                "      .email-masthead_name {\n" +
                "        text-shadow: none !important;\n" +
                "      }\n" +
                "    }\n" +
                "    \n" +
                "    :root {\n" +
                "      color-scheme: light dark;\n" +
                "      supported-color-schemes: light dark;\n" +
                "    }\n" +
                "    </style>\n" +
                "    <!--[if mso]>\n" +
                "    <style type=\"text/css\">\n" +
                "      .f-fallback  {\n" +
                "        font-family: Arial, sans-serif;\n" +
                "      }\n" +
                "    </style>\n" +
                "  <![endif]-->\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <span class=\"preheader\">Use this link to reset your password. The link is only valid for 24 hours.</span>\n" +
                "    <table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "      <tr>\n" +
                "        <td align=\"center\">\n" +
                "          <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "            <tr>\n" +
                "              <td class=\"email-masthead\">\n" +
                "                <a href=\"https://example.com\" class=\"f-fallback email-masthead_name\">\n" +
                "                CourseHub\n" +
                "              </a>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <!-- Email Body -->\n" +
                "            <tr>\n" +
                "              <td class=\"email-body\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                  <!-- Body content -->\n" +
                "                  <tr>\n" +
                "                    <td class=\"content-cell\">\n" +
                "                      <div class=\"f-fallback\">\n" +
                "                        <h1\"> Hi " + lastName + "  ,</h1>\n" +
                "                        <p>You recently requested to reset your password for your <b>CourseHub</b> account. Use the token below to reset it. <br> <strong>This password reset is only valid for the next 1 hour.</strong></p>\n" +
                "                        <!-- Action -->\n" +
                "                        <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                          <tr>\n" +
                "                            <td align=\"center\">\n" +
                "                              <!-- Border based button\n" +
                "           https://litmus.com/blog/a-guide-to-bulletproof-buttons-in-email-design -->\n" +
                "                              <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "                                <tr>\n" +
                "                                  <td align=\"center\">\n" +
                "                                    <a href=\"{{action_url}}\" class=\"f-fallback button button--green\" target=\"_blank\">" + token + "</a>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </table>\n" +
                "                        <p>As a security measure, we received a request to reset your password. If you did not request a password reset, please ignore this email or <a href=\"mailto:akinyemisamuelayo@gmail.com\">contact support</a> if you have questions.</p>\n" +
                "                        <p>Thanks,\n" +
                "                          <br>The CourseHub team</p>\n" +
                "                        <!-- Sub copy -->\n" +
                "                      </div>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td>\n" +
                "                <table class=\"email-footer\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                  <tr>\n" +
                "                    <td class=\"content-cell\" align=\"center\">\n" +
                "                      <p class=\"f-fallback sub align-center\">\n" +
                "                        CourseHub\n" +
                "                        <br>1234 coursehub Rd.\n" +
                "                        <br>Suite 1234\n" +
                "                      </p>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>";
    }
}
