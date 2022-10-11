package Exceptions;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class AutoException extends Exception {
    private int errorno;
    private String errormsg;

    public AutoException() {
    }

    public AutoException(int errorno, String errormsg) {
        this.errorno = errorno;
        this.errormsg = errormsg;
    }
    public void exceptionLog() throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        StringBuffer log = new StringBuffer();
        log.append("\n******************************");
        log.append("\nError Number: "+ errorno);
        log.append("\nError Message: "+ errormsg);
        log.append("\nTime: "+timestamp.toString());

        FileWriter a1= new FileWriter("ExceptionLog.txt");
        a1.write(log.toString());
        a1.close();
        System.out.println("Write the error log into log file");

    }
    public String fix(int errorno){
        this.errorno = errorno;
        FixAuto f1 = new Fix1to4();
        switch (errorno) {
            case 1 -> {
                System.out.println("Missing filename or wrong filename.");
                return f1.fix1(1);
            }
            case 2 -> {
                System.out.println("Missing price for Automobile in text file");
                return "";
            }
            case 3 -> {
                System.out.println("MMissing OptionSet data");
                return "";
            }
            case 4 -> {
                System.out.println("Missing Option data");
                return "";
            }
            default -> {
                System.out.println("Wrong error number");
                return "";
            }
        }

    }


}
