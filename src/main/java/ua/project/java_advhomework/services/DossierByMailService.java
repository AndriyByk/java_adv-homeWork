package ua.project.java_advhomework.services;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.project.java_advhomework.models.dto.DrownedRussianShipForMailingDTO;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class DossierByMailService {
    private JavaMailSender javaMailSender;

    public void send(DrownedRussianShipForMailingDTO shipWithMail, MultipartFile multipartFile) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        try {
            mimeMessage.setFrom(new InternetAddress(shipWithMail.getEmail()));
            helper.setTo(shipWithMail.getEmail());
            helper.setText("<h1>It is dossier about drowned russian warship.</h1>",true);

            String fileFormat = multipartFile.getContentType().split("/")[1];
            helper.addAttachment("picture_of_drowned_warship." + fileFormat, multipartFile);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
