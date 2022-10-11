package Adapter;

import Exceptions.AutoException;
import Model.Automobile;
import Model.LHMAuto;
import Scale.EditOptions;
import Util.FileIO;
import Util.Property;

import java.io.IOException;
import java.util.LinkedHashMap;

public abstract class proxyAutomobile implements CreateAuto,UpdateAuto {
    private static LHMAuto a1 = new LHMAuto();
    @Override
    public void BuildAuto(String filename)  {
        FileIO lab2 = new FileIO();
        try {
            a1.addAutomobile(lab2.buildAutoObject(filename));
        } catch (AutoException e) {
            try {
                e.exceptionLog();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                a1.addAutomobile(lab2.buildAutoObject(e.fix(1)));
            } catch (AutoException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    public void printAuto(String Modelname) {
        System.out.println("The vehicle information is printed");
        a1.findAutomobile(Modelname).print();

    }
    @Override
    public void updateOptionSetName(String Modelname, String OptionSetname, String newName) {
        Automobile model= a1.findAutomobile(Modelname);
        int index=model.findOptionSet(OptionSetname);
        model.update_Optset_name(index, newName);
        LinkedHashMap<String,Automobile> Auto=a1.getAuto();
        Automobile oldmodel= a1.findAutomobile(Modelname);
        Auto.replace(Modelname,oldmodel,model);
        a1.setAuto(Auto);// write back
    }

    @Override
    public void updateOptionPrice(String Modelname, String OptionSetname, String Option, float newprice) {
        Automobile model= a1.findAutomobile(Modelname);
        int index=model.findOptionSet(OptionSetname);
        model.update_Option_price(OptionSetname,Option,newprice);
        LinkedHashMap<String,Automobile> Auto=a1.getAuto();
        Automobile oldmodel= a1.findAutomobile(Modelname);
        Auto.replace(Modelname,oldmodel,model);
        a1.setAuto(Auto);


    }
    @Override
    public void update_choice(String Modelname, int x){
        Automobile car = a1.findAutomobile(Modelname);
        var edit = new EditOptions(x,car);
        edit.wait_for_over();
        LinkedHashMap<String,Automobile> Auto=a1.getAuto();
        Auto.remove(Modelname);
        Auto.put(Modelname,car);
        a1.setAuto(Auto);// write back


    }
    public Automobile BuildCarModelOptions(Automobile car, Property prop) throws IOException, ClassNotFoundException {
       return car;
    }




}
