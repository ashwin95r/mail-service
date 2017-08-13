package hello;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    hello.SendGridClient sgClient  = new hello.SendGridClient();

//    @RequestMapping("/greeting")
//    public hello.Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new hello.Greeting(counter.incrementAndGet(),
//                String.format(template, name));
//    }

    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
    public void sendmail(@RequestBody String input) throws IOException {
        System.out.println(input);
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        hello.Mail mail = mapper.readValue(input, hello.Mail.class);
        System.out.println(mail);


        String str = mapper.writeValueAsString(sgClient.sgObject(mail));
        System.out.println(str);
    }
}