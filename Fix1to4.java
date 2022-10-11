package Exceptions;

public class Fix1to4 implements FixAuto {
    @Override
    public String fix1(int i){
        String a = "FocusWagonZTW.txt";
        System.out.println("Error 1: file not found is solved");
        return a;

    }
}
