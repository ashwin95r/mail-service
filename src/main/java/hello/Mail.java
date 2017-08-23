package hello;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.regex.*;

public class Mail {
    public List<String> to;
    public List<String> cc;
    public List<String> bcc;
    public String subject;
    public String message;

    private static Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");

    public Boolean validateEmail(String emailAddress) {
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

        if (to == null) {
            return "Missing To address";
        }

        for(String str: to) {
            if(!validateEmail(str)) {
                return "Invalid email id: " + str;
            }
        }

        if(cc != null) {
            for (String str : cc) {
                if (!validateEmail(str)) {
                    return "Invalid email id: " + str;
                }
            }
        }

        if(bcc != null ) {
            for (String str : bcc) {
                if (!validateEmail(str)) {
                    return "Invalid email id: " + str;
                }
            }
        }
        return "";
    }
}

