package Scale;

import Model.Automobile;

public class EditOptions extends Thread implements Editable,Runnable{
    private Automobile a1;
    private int x;
    private int threadno;
    private Thread t;
    public EditOptions(int x, Automobile x1) {
        this.x = x;
        threadno = x;
        this.a1 = x1;
        t = new Thread(this,"Thread"+threadno);
        t.start();;
    }
    public void run() {
        switch(x) {
            case 0:
                System.out.println("Start thread " + threadno + " -synchronized");
                break;
            case 1:
                System.out.println("Start thread " + threadno + " -synchronized");
                break;
            case 2:
                System.out.println("Start thread " + threadno + " - non-synchronized");
                break;
            case 3:
                System.out.println("Start thread " + threadno + " - non-synchronized");
                break;
        }
        ops();
        System.out.println("Stopping thread " + threadno);
    }
    public void ops() {
        switch (x) {
            case 0: //sync
                update("Color","Red","Blue",1,0);
                break;
            case 1: //sync - 0 and 1 work together
                update("Color","Red","Yellow",1,1);
                break;
            case 2: //2 and 3 work together, non_sync
                update("Color","Red","Blue",0,2);
                break;
            case 3:
                update("Color","Red","Yellow",0,3);
                break;
        }
    }
    public void wait_for_over(){
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread "+ threadno+" is finished ");
    }




    @Override
    public  void  update( String optionset, String old_option, String newc_option, int sync, int threadno) {
        if(sync==1){// sync
            sync_update(optionset,old_option,newc_option);
        }
        if(sync==0){//non_sync
            non_sync_update(optionset,old_option,newc_option);
        }

    }
    public synchronized void sync_update(String optionset, String old_option, String newc_option){
        if(a1.getOptionChoice(optionset).equals(old_option)) a1.setOptionChoice(optionset,newc_option);
    }
    public void non_sync_update(String optionset, String old_option, String newc_option){
        a1.setOptionChoice(optionset,newc_option);
    }


}
