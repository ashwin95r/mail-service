
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MediaType;

public class MailGunClient {
    public static void main(String[] args) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-399c536f7b315f9ef0318b4776ba8a6e"));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox30f1890eb10b4a7e825353d825dc3666.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun Sandbox <postmaster@sandbox30f1890eb10b4a7e825353d825dc3666.mailgun.org>");
        formData.add("to", "ash <meashwinr@gmail.com>");
        formData.add("to", "ash <me.ashwin.r@gmail.com>");
        formData.add("cc", "ash <meashwin.r@gmail.com>");
        formData.add("subject", "Hello ash");
        formData.add("text", "Congratulations ash, you just sent an email with Mailgun!  You are truly awesome!");

        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);
        String output = clientResponse.getEntity(String.class);

        System.out.println("Email sent successfully : " + output);
    }
}
