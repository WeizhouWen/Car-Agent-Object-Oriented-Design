package Util;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;

public class Property implements Serializable {
    private Hashtable<String,String> prop= new Hashtable<>();

    public Property() {
    }
    public void print(){
        System.out.println("The property hashtable: ");
        for(String key: prop.keySet()) System.out.println(key + " : " + prop.get(key));
    }
    public String getProperty(String key){
        return prop.get(key);
    }

    public void load(FileInputStream in) throws IOException {
        int ch;
        StringBuilder key = new StringBuilder();
        StringBuilder value=new StringBuilder();
        boolean iskey=true;
        while((ch=in.read())!=-1){
            //System.out.println("ch="+(char)ch);
            if(iskey &&ch!=61) {
                key.append((char)ch);
                continue;
            }

            else if (ch==61){
                iskey=false;
                continue;
            }
            else if(!iskey &&ch!=10){
                value.append((char) ch);
                continue;
            }
            else if(ch==10)
            prop.put(key.toString(), value.toString());
            //System.out.println("input "+key.toString()+" :"+value.toString());
            key=new StringBuilder();
            value= new StringBuilder();
            iskey=true;
        }
        prop.put(key.toString(), value.toString());
    }



}
