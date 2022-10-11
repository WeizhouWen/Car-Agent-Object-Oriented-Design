package Model;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class LHMAuto<T extends Automobile> implements Serializable {

    private LinkedHashMap <String,T> Auto = new LinkedHashMap<String,T>();

    public LHMAuto() {
    }
    public void addAutomobile(Automobile a1){
        Auto.put(a1.getMake()+a1.getModel(), (T) a1);
    }
    public void removeAutomobile(String make, String Model){
        Auto.remove(make+Model);
    }
    public Automobile findAutomobile(String make, String Model){
        return Auto.get(make+Model);
    }
    public Automobile findAutomobile(String key){
        return Auto.get(key);
    }
    public LinkedHashMap<String, T> getAuto() {
        return Auto;
    }

    public void setAuto(LinkedHashMap<String, T> auto) {
        Auto = auto;
    }

    public void print()
    {
        int i=0;
        System.out.println("**********************The Automobile in the LHM list*****************************");
        for(String key: Auto.keySet()){

            System.out.println("**************The "+ i+"th Automobile*****************");
            System.out.println(findAutomobile(key).getModel());
            i++;
        }
    }



}
