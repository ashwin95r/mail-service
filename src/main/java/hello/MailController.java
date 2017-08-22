package hello;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {
    private ObjectMapper mapper = new ObjectMapper();
    private MailSender m = new MailSender();

    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
    public ResponseEntity sendmail(@RequestBody String input) throws IOException, EmailException {
        // Parse JSON into Mail class
        Mail mail = mapper.readValue(input, Mail.class);

        String msg;
        if ((msg = mail.validate()) != "") {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
        // Send mail.
        m.send(mail);


        return ResponseEntity.status(HttpStatus.OK).build();
    }

}