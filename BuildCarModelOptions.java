package Server;

import Model.Automobile;
import Model.LHMAuto;
import Util.Property;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BuildCarModelOptions implements  AutoServer {

    public BuildCarModelOptions() {
    }
    @Override
    public Automobile BuildCarModelOptions(Automobile car, Property prop) throws IOException, ClassNotFoundException {
        car.appendOptset(prop.getProperty("Option1"),2);
        car.appendopt(0,prop.getProperty("OptionValue1a"),5);
        car.appendopt(0,prop.getProperty("OptionValue1b"),10);
        car.appendOptset(prop.getProperty("Option2"),2);
        car.appendopt(1,prop.getProperty("OptionValue2a"),15);
        car.appendopt(1,prop.getProperty("OptionValue2b"),20);
        System.out.println("---------Car is built at server:-------------------------------------");
        return car;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Automobile car1;
        Automobile car2;
        Automobile car3;
        LHMAuto a1= new LHMAuto();
        BuildCarModelOptions current = new BuildCarModelOptions();
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

        Property prop1 =(Property) in.readObject();
        System.out.println("-------------Property received-------------");
        car1 = new Automobile(prop1.getProperty("CarMake"), prop1.getProperty("CarModel"),100,2 );

        car1=current.BuildCarModelOptions(car1,prop1);
        out.writeObject(car1);
        System.out.println("Sent Automobile model to client");
        System.out.println("car make: "+ prop1.getProperty("CarMake"));
        System.out.println("car model: "+ prop1.getProperty("CarModel"));


        Property prop2 =(Property) in.readObject();
        System.out.println("-------------Property received-------------");
        car2 = new Automobile(prop2.getProperty("CarMake"), prop2.getProperty("CarModel"),100,2 );
        car2=current.BuildCarModelOptions(car2,prop2);
        out.writeObject(car2);
        System.out.println("Sent Automobile model to client");
        System.out.println("car make: "+ prop2.getProperty("CarMake"));
        System.out.println("car model: "+ prop2.getProperty("CarModel"));

        Property prop3 =(Property) in.readObject();
        System.out.println("-------------Property received-------------");
        car3 = new Automobile(prop3.getProperty("CarMake"), prop3.getProperty("CarModel"),100,2 );
        car3=current.BuildCarModelOptions(car3,prop3);
        out.writeObject(car3);
        System.out.println("Sent Automobile model to client");
        System.out.println("car make: "+ prop3.getProperty("CarMake"));
        System.out.println("car model: "+ prop3.getProperty("CarModel"));

        a1.addAutomobile(car1);
        a1.addAutomobile(car2);
        a1.addAutomobile(car3);
        a1.print();





        ServerSocket serverSocket2 = null;
        Socket clientSocket2 = null;
        try {
            serverSocket2 = new ServerSocket(4000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
        try {
            clientSocket2 = serverSocket2.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        ObjectOutputStream out2 = new ObjectOutputStream(clientSocket2.getOutputStream());
        ObjectInputStream in2 = new ObjectInputStream(clientSocket2.getInputStream());

        out2.writeObject(a1);




    }




}
