package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import javax.ws.rs.core.MultivaluedHashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SendGridClient implements MailService {
    private ObjectMapper mapper = new ObjectMapper();
    private String SG_API_KEY = "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";
    private String SG_URL = "https://api.sendgrid.com/v3/mail/send";

    public HashMap sgObject(Mail mail) {
        HashMap mp = new HashMap();
        HashMap mp1 = new HashMap();
        List<Object> l = new ArrayList<>();
        for(String it: mail.to) {
            if (it == "") {
                continue;
            }
            HashMap mp2 = new HashMap();
            mp2.put("email", it);
            l.add(mp2);
        }
        if (l.size() > 0) {
            mp1.put("to", l);
        }
        l = new ArrayList<>();
        for(String it: mail.cc) {
            if (it == "") {
                continue;
            }
            HashMap mp2 = new HashMap();
            mp2.put("email", it);
            l.add(mp2);
        }
        if (l.size() > 0) {
            mp1.put("cc", l);
        }
        l = new ArrayList<>();
        for(String it: mail.bcc) {
            if (it == "") {
                continue;
            }
            HashMap mp2 = new HashMap();
            mp2.put("email", it);
            l.add(mp2);
        }
        if (l.size() > 0) {
            mp1.put("bcc", l);
        }
        mp1.put("subject", mail.subject);
        l = new ArrayList<>();
        l.add(mp1);
        mp.put("personalizations", l);

        mp1 = new HashMap();
        mp1.put("email", "test@test.com");
        mp.put("from", mp1);

        mp1 = new HashMap();
        mp1.put("type", "text/plain");
        mp1.put("value", mail.message);
        l = new ArrayList<>();
        l.add(mp1);
        mp.put("content",l);
        return mp;
    }

    public void send(Mail mail) throws IOException, EmailException {
        Client client = Client.create();
        WebResource webResource = client.resource(SG_URL);

        // Create JSON of required format
        String str = mapper.writeValueAsString(this.sgObject(mail));

        // Make a POST request
        ClientResponse clientResponse = webResource.getRequestBuilder().
                header("Authorization", "Bearer "+SG_API_KEY).
                header("Content-Type", "application/json").
                post(ClientResponse.class, str);

        // Check response
        if (clientResponse.getStatus() != 202) {
            throw new EmailException(clientResponse.toString(), clientResponse.getStatus());
        }
    }
}
