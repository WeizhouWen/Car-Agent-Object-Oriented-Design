package Server;

import Model.Automobile;
import Util.Property;

import java.io.IOException;

public interface AutoServer {

    Automobile BuildCarModelOptions(Automobile car, Property prop) throws IOException, ClassNotFoundException;

}
