package ru.otus.java.hw13;

import java.io.*;

public class ExampleClient implements AutoCloseable{

    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;


    public ExampleClient(InputStream dataInputStream, OutputStream dataOutputStream) {
        this.dataInputStream = new DataInputStream(dataInputStream);
        this.dataOutputStream = new DataOutputStream(dataOutputStream);
    }

    public void send (String message,String message2,String message3) throws IOException {
        dataOutputStream.writeUTF(message);
        dataOutputStream.writeUTF(message2);
        dataOutputStream.writeUTF(message3);
        dataOutputStream.flush();
        String result = dataInputStream.readUTF();
        System.out.println(result);
    }

    @Override
    public void close() throws Exception {
        dataInputStream.close();
        dataOutputStream.close();
    }
}
