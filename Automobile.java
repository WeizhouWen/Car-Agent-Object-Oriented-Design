package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Automobile implements Serializable {
    private String make;
    private String model;
    private double baseprice;
    private ArrayList<OptionSet> optset;
    private int OptionSetSize;
    private ArrayList<String> Choice ;
    Automobile(){

    }
    public Automobile(String make,String model, int baseprice, int OptionSetsize){
        this.make = make;
        this.model = model;
        this.baseprice = baseprice;
        this.OptionSetSize=OptionSetsize;
        optset =new ArrayList<OptionSet>(OptionSetsize);
        Choice = new ArrayList<String>(OptionSetsize);
        for(int i=0;i<OptionSetsize;i++){
            Choice.add("");
        }

    }
    public String getOptionChoice(String setName){
        int index= findOptionSet(setName);
        return Choice.get(index);
    }
    public float getOptionChoicePrice(String setName){
        String optionName=getOptionChoice(setName);
        int index_optionset=findOptionSet(setName);
        return optset.get(index_optionset).getOptionChoice().getPrice();
    }
    public void setOptionChoice(String setName, String optionName){
        //set Arraylist
        int index_optionset=findOptionSet(setName);
        Choice.set(index_optionset,optionName);
        // set the optionset
        optset.set(index_optionset,optset.get(index_optionset).setOptionChoice(optionName));
    }
    public void setOptionChoice(int index_optionset, String optionName){
        //set Arraylist
        Choice.set(index_optionset,optionName);
        // set the optionset
        optset.set(index_optionset,optset.get(index_optionset).setOptionChoice(optionName));
    }
    public int getTotalPrice(){
        double totalPrice = baseprice;
        for(int i=0;i<optset.size();i++){
            totalPrice+=(double)getOptionChoicePrice(optset.get(i).getNameOfOptionSet());

        }
        return (int) totalPrice;
    }



    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(double baseprice) {
        this.baseprice = baseprice;
    }
    public void appendOptset(String optsetName, int optionSize)
    {
        optset.add(new OptionSet(optsetName,optionSize));
    }
    public void appendopt(int index_optionset, String name_Option, float price_option )
    {
        optset.set(index_optionset,optset.get(index_optionset).appendopt(name_Option,price_option));
    }

    public void setOptset(int index, String opsetName, int optionSize){
        if (index < optset.size()) {
            optset.set(index, new OptionSet(opsetName, optionSize));
        }
        else{
            System.out.println("Can not find the OptionSet");
        }// valid and not delete
    }
    public void setOption(int index_OptionSet, int index_Option, String name_Option, float price){
        if (index_OptionSet < optset.size()) {
            if(index_Option< optset.get(index_OptionSet).getOptionSize()){
                optset.get(index_OptionSet).setOption(index_Option,name_Option,price);
            }
            else{}
        }
        else{
            System.out.println("Can not find the OptionSet");
        }
    }
    // find optionset with name
    public int findOptionSet(String name){
        for(int i=0;i<optset.size();i++){
            if(Objects.equals(optset.get(i).getNameOfOptionSet(), name)&& name != null) return i;
        }
        System.out.println("Can not find the OptionSet");
        return -1;
    }
    //delete a optionset
    public void deleteOptionSet(int index){
        if(index< optset.size()){
            optset.remove(index);
            OptionSetSize--;
            System.out.println("Successfully delete the "+ index+"th OptionSet");

        }else{
            System.out.println("Can not find the Optionset, failed to delete");
        }
    }
    public void print(){
        System.out.println("The Name of make: "+ make);
        System.out.println("The Name of Automotive: "+ model);
        System.out.println("The Number of OptionSet: "+ OptionSetSize);
        System.out.println("Choice of the optionset"+Choice.toString());
        System.out.println("Base price:$"+baseprice);
        System.out.println("Total price:$"+getTotalPrice());
        StringBuffer optionSetlist=new StringBuffer();
        int i=0;
        for(OptionSet currentOptionSet: optset){
            if(currentOptionSet!=null) {
                optionSetlist.append(i+"th "+currentOptionSet.getNameOfOptionSet()+"\n" );
                currentOptionSet.print();
                i++;
            }
        }
        System.out.println("Total optionset: \n"+ optionSetlist);

    }


    public void update_Optset_name(int index, String newName) {
        optset.set(index,optset.get(index).setNameOfOptionSet(newName));
    }


    public void update_Option_price(String optionSetname, String option, float newprice) {
        int index_optionset= findOptionSet(optionSetname);
        int index_option= optset.get(index_optionset).findOption(option);
        optset.set(index_optionset,optset.get(index_optionset).setOption(index_option,option,newprice));
    }


}
