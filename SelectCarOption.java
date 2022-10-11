package Client;

import Model.Automobile;
import Model.LHMAuto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SelectCarOption<T extends Automobile> {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket clientSocket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        try {
            clientSocket= new Socket(InetAddress. getLocalHost(), 4000);
            in= new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            System.exit(1);
        }
        LHMAuto carModels = (LHMAuto) in.readObject();
        System.out.println("The following cars are available");
        carModels.print();

    }
}
