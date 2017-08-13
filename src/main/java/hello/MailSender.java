package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

public class MailSender {

    // Define all services here.
    private SendGridClient sgClient;
    private MailGunClient mgClient;

    public MailSender() {
        this.sgClient  = new SendGridClient();
        this.mgClient = new MailGunClient();
    }

    public void send(Mail mail) throws IOException, EmailException {
        try {
            sgClient.send(mail);
        } catch (EmailException e) {
            mgClient.send(mail);
        }
    }
}
