package lihu.zlm.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件工具类
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年8月25日 下午4:32:42 
 *
 */
public class MailUtil {

    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    public static void sendMail(String host, Integer port, String from, String to, String cc,
            String title, String content, String contentType) throws AddressException,
            MessagingException {
        if (contentType == null) {
            contentType = "text/html;charset=UTF-8";
        }

        Properties p = new Properties();
        p.put("mail.smtp.host", host);
        if (port != null) {
            p.put("mail.smtp.port", port);
        }
        p.put("mail.smtp.auth", "false");

        Session session = Session.getInstance(p, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        @SuppressWarnings("static-access")
        InternetAddress[] address = new InternetAddress().parse(to); // 发送多人
        msg.setRecipients(Message.RecipientType.TO, address);
        if (cc != null)
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, true));
        msg.setSubject(title);
        msg.setSentDate(new Date());
        msg.setContent(content, "text/html;charset=UTF-8");
        msg.saveChanges();
        Transport.send(msg);
        logger.info("向{}发送邮件成功，内容{}", to, content);
    }
}
