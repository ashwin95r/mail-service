package hello;

import java.util.List;
import java.util.regex.*;

public class Mail {
    public List<String> to;
    public List<String> cc;
    public List<String> bcc;
    public String subject;
    public String message;

    public Boolean validateEmailAddress(String emailAddress) {
        Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher = regexPattern.matcher(emailAddress);
        if (regMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}

