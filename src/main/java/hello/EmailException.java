package hello;

public class EmailException extends Exception {
    String message;
    Integer status;

    EmailException(String m, Integer i) {
        this.message = m;
        this.status = i;
    }

    public String getMessage() {
        return "Received: "+this.message+" Status code: "+this.status;
    }

}
