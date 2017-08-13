import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.io.IOException;


public class SendGridClient {
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
