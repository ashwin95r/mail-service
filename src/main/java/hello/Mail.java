package hello;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.regex.*;

public class Mail {
    @NotNull
    public List<String> to;
    public List<String> cc;
    public List<String> bcc;
    @NotNull
    public String subject;
    @NotNull
    public String message;

    public Boolean validateEmail(String emailAddress) {
        Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher = regexPattern.matcher(emailAddress);
        if (regMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public String validate() {
        if (message == null || message.isEmpty()) {
            return "Empty Body";
        }
        return "";
    }
}

