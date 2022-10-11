package Util;


import Exceptions.AutoException;
import Model.Automobile;

import java.io.*;
import java.util.ArrayList;

public class FileIO {
    public FileIO() {
    }
    public Automobile buildAutoObject(String fileName) throws AutoException {
        try {
            FileReader file = new FileReader(fileName);

            BufferedReader buff = new BufferedReader(file);
            ArrayList<String> readlist = new ArrayList<String>();
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null)
                    eof = true;
                else
                    readlist.add(line);

            }
            buff.close();
            // first three value is name, base price and number odf optionset
            var FordZTW = new Automobile(readlist.get(0),readlist.get(1), Integer.parseInt(readlist.get(2)), Integer.parseInt(readlist.get(3)));
            int OptionSet_number = Integer.parseInt(readlist.get(3));
            int[] Option_number = new int[OptionSet_number];

            int i = 4;
            for (int j = 0; j < OptionSet_number; j++) {
                FordZTW.appendOptset(readlist.get(i), Integer.parseInt(readlist.get(i + 1)));
                Option_number[j] = Integer.parseInt(readlist.get(i + 1));
                i += 2;
            }


            int set_number = 0;
            while (set_number < OptionSet_number) {
                for (int option = 0; option < Option_number[set_number]; option++) {
                    FordZTW.appendopt(set_number,readlist.get(i), Integer.parseInt(readlist.get(i + 1)));
                    i += 2;
                }
                set_number++;
            }

            // input of choice
            for (int j = 0; j < OptionSet_number; j++) {
                FordZTW.setOptionChoice(j,readlist.get(i));
                i += 1;
            }

            System.out.println("**********Text file input:******************");
            FordZTW.print();
            return FordZTW;
        }
        catch (IOException e) {
            System.out.println("Can not found the file, try to fix it");
            throw new AutoException(1,"File is not found");

        }

    }
    public Automobile deserializeFile(String fileName)  {
        try {

            FileInputStream a2= new FileInputStream(fileName);
            ObjectInputStream In = new ObjectInputStream(a2);
            Automobile FordZTW = (Automobile) In.readObject();
            return FordZTW;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        }
        return null;


    }
    public String serializeFile(Automobile FordZTW)  {
        try {
            String fileName = "FordZTW.dat";
            FileOutputStream a1 = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(a1);
            out.writeObject(FordZTW);
            return fileName;
        } catch (IOException e) {
            System.out.println("Failed to write the object");
            System.out.println(e.getMessage());
            return "";
        }

    }
    public Property buildProperty(String filename) throws IOException {
        Property prop = new Property();
        FileInputStream in = new FileInputStream(filename);
        prop.load(in);
        System.out.println("Property is built: ");
        prop.print();
        return prop;
    }

}
