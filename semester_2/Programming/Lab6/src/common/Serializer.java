package common;

import java.io.*;

public class Serializer {
    public byte[] serialize(Object object) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        objOut.writeObject(object);
        objOut.flush();
        objOut.close();
        return byteOut.toByteArray();
    }

    public Object deserialize(byte[] bytes) {
        ByteArrayInputStream byteInp = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream objInp = new ObjectInputStream(byteInp);
            return objInp.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
