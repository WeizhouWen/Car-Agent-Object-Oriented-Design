package Client;

import Model.Automobile;
import Util.FileIO;
import Util.Property;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class CarModelOptionsIO {
    public Property create_property(String fileName) throws IOException {
        var File = new FileIO();
        return File.buildProperty(fileName);
    }
     public static void main(String[] args) throws IOException, ClassNotFoundException {
         Socket clientSocket = null;
         ObjectInputStream in = null;
         ObjectOutputStream out = null;
         try {
             clientSocket= new Socket(InetAddress. getLocalHost(), 4444);
             in= new ObjectInputStream(clientSocket.getInputStream());
             out = new ObjectOutputStream(clientSocket.getOutputStream());
         } catch (UnknownHostException e) {
             System.err.println("Don't know about host: taranis.");
             System.exit(1);
         } catch (IOException e) {
             System.err.println("Couldn't get I/O for the connection to: taranis.");
             System.exit(1);
         }
         CarModelOptionsIO p1 = new CarModelOptionsIO();

         Property Prius =  p1.create_property("Prius.txt");
         out.writeObject(Prius);
         System.out.println("---------------Sent property Prius to Server--------------");
         Automobile car = (Automobile)in.readObject();
         if(car != null) {
             System.out.println("----------------Automobile received-------------");
             System.out.println("Car make:"+car.getMake());
             System.out.println("Car model:"+car.getModel());
         };

         Property ModelX =  p1.create_property("ModelX.txt");
         out.writeObject(ModelX);
         System.out.println("---------------Sent property ModelX to Server--------------");
         Automobile car2 = (Automobile)in.readObject();
         if(car2 != null) {
             System.out.println("----------------Automobile received-------------");
             System.out.println("Car make:"+car2.getMake());
             System.out.println("Car model:"+car2.getModel());
         };

         Property ModelY =  p1.create_property("ModelY.txt");
         out.writeObject(ModelY);
         System.out.println("---------------Sent property ModelY to Server--------------");
         Automobile car3 = (Automobile)in.readObject();
         if(car3 != null) {
             System.out.println("----------------Automobile received-------------");
             System.out.println("Car make:"+car3.getMake());
             System.out.println("Car model:"+car3.getModel());
         };



     }


}
