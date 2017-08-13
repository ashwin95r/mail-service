package hello;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.List;

public class SendGridClient {
    public MultivaluedHashMap<String, Object> sgObject(hello.Mail mail) {
        MultivaluedHashMap<String, Object> mp = new MultivaluedHashMap<>();
        MultivaluedHashMap<String, Object> mp1 = new MultivaluedHashMap<>();
        MultivaluedHashMap<String, Object> mp2 = new MultivaluedHashMap<>();
        mp2.putSingle("email", mail.to.get(0));
        mp1.putSingle("to", mp2);
        mp1.putSingle("subject", mail.subject);
        mp.putSingle("personalizations", mp1);
        MultivaluedHashMap<String, Object> mp3 = new MultivaluedHashMap<>();
        mp3.putSingle("email", "test@test.com");
        mp.putSingle("from", mp3);
        MultivaluedHashMap<String, Object> mp4 = new MultivaluedHashMap<>();
        mp4.putSingle("type", "text/plain");
        mp4.putSingle("value", mail.message);
        return mp;
    }

    public static void main(String[] args) throws IOException {
        Client client = Client.create();
        WebResource webResource = client.resource("https://api.sendgrid.com/v3/mail/send");
        webResource.setProperty("Authorization", "Bearer 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");

        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("to", "ash <meashwinr@gmail.com>");
        formData.add("to", "ash <me.ashwin.r@gmail.com>");
        formData.add("cc", "ash <meashwin.r@gmail.com>");
        formData.add("subject", "Hello ash");
        formData.add("text", "Congratulations ash, you just sent an email with Mailgun!  You are truly awesome!");

        ClientResponse clientResponse = webResource.getRequestBuilder().header("Authorization", "Bearer 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").
                header("Content-Type", "application/json").
                post(ClientResponse.class, "{\"from\": {\"email\" : \"abx@q.com\"}}");
        String output = clientResponse.getEntity(String.class);

        System.out.println("Email sent successfully : " + output);
    }
}
