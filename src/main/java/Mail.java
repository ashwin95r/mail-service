package hello;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;
import java.util.regex.*;

public class Mail {
    //private String from;
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

//    public Boolean validate() {
////        validateEmailAddress(this.from);
//        for(String it: this.to) {
//            validateEmailAddress(it);
//        }
//        for(String it: this.cc) {
//            validateEmailAddress(it);
//        }
//        for(String it: this.bcc) {
//            validateEmailAddress(it);
//        }
//        return true;
//    }
}

