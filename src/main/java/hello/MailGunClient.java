package hello;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MediaType;

public class MailGunClient implements MailService {
    private String MG_API = "key-399c536f7b315f9ef0318b4776ba8a6e";
    private String MG_URL = "https://api.mailgun.net/v3/sandbox30f1890eb10b4a7e825353d825dc3666.mailgun.org/messages";
    private String MG_FROM = "Mailgun Sandbox <postmaster@sandbox30f1890eb10b4a7e825353d825dc3666.mailgun.org>";

    private MultivaluedMapImpl mgObject(Mail mail) {
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", MG_FROM);
        for(String it: mail.to) {
            if (it == "") {
                continue;
            }
            formData.add("to", "<" + it + ">");
        }
        for(String it: mail.cc) {
            if (it == "") {
                continue;
            }
            formData.add("cc", "<" + it + ">");
        }
        for(String it: mail.bcc) {
            if (it == "") {
                continue;
            }
            formData.add("bcc", "<" + it + ">");
        }
        formData.add("subject", mail.subject);
        formData.add("text", mail.message);
        return formData;
    }

    public void send(Mail mail) throws EmailException {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", MG_API));
        WebResource webResource = client.resource(MG_URL);

        // Create FORM data
        MultivaluedMapImpl formData = mgObject(mail);

        // Make POST request
        ClientResponse clientResponse = webResource.
                type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);

        // Check response
        if (clientResponse.getStatus() != 200) {
            throw new EmailException(clientResponse.toString(), clientResponse.getStatus());
        }
    }
}
