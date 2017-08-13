package hello;

import java.io.IOException;

public interface MailService {
    public void send(Mail mail) throws EmailException, IOException;
}
