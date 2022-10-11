package Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class OptionSet implements Serializable {
    private ArrayList<Option> opt;
    private String nameOfOptionSet;
    private int optionSize;
    private String choice;// the index of chosen option

    OptionSet(){

    }
    OptionSet(String n, int size){
        nameOfOptionSet=n;
        opt = new ArrayList<Option>(size);
        optionSize=size;

    }

    public OptionSet setOptionChoice(String optionName) {
        this.choice = optionName;
        return this;
    }

    protected String getNameOfOptionSet() {
        return nameOfOptionSet;
    }
    protected int getOptionSize(){return optionSize;}

    protected OptionSet appendopt(String name_Option, float price_option){
        opt.add(new Option(name_Option,price_option));

        return this;
    }
    protected OptionSet setOption(int index, String name_Option, float price_option){
        if(index<optionSize) {
            opt.set(index,opt.get(index).setName(name_Option));
            opt.set(index,opt.get(index).setPrice(price_option));
        }
        else{
            System.out.println("Can not find the Option");
        }
        return this;
    }
    protected OptionSet setNameOfOptionSet(String nameOfOptionSet) {
        this.nameOfOptionSet = nameOfOptionSet;
        return this;
    }
    // find the Option with name
    protected int findOption(String name){
        for(int i=0;i<opt.size();i++){
            if(name!=null && Objects.equals(name, opt.get(i).getName())) return i;

        }
        System.out.println("Can not find the Option");
        return -1;
    }
    protected void deleteOption(int index){
        if(index<opt.size())
        {
            opt.remove(index);
            System.out.println("Successfully delete the "+ index+"th Option");
            optionSize--;
        }
        else{
            System.out.println("Can not find the option");
        }
    }
    // print the properties of option set
    protected  void print(){
        System.out.println("OptionSet Name:"+nameOfOptionSet);
        System.out.println("The number of options: " + optionSize);
        System.out.println("The Choice of customer is: "+choice);
        StringBuffer optionList= new StringBuffer();
        int i=0;
        for(Option currentOption: opt){
            if(currentOption!= null)
            {
                optionList.append(i+". "+currentOption.name+": $"+currentOption.price+"   \n");
                i++;
            }
        }
        System.out.println("The price of each "+nameOfOptionSet+":");
        System.out.println(optionList.toString());
    }
    protected Option getoption(int index){
        return opt.get(index);
    }
    protected  Option getOptionChoice(){
        return opt.get(findOption(choice));
    }


    protected class Option implements Serializable {
        private String name;
        private float price;
        Option(){

        }
        Option(String name, float price){
            this.name = name;
            this.price = price;
        }

        protected String getName() {
            return name;
        }

        protected Option setName(String name) {
            this.name = name;
            return this;
        }

        protected float getPrice() {
            return price;
        }

        protected Option setPrice(float price) {
            this.price = price;
            return this;
        }
    }

}
