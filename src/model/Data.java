package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data<E> implements Serializable {

    private String fileName;
    private E data;

    public Data(String fileName, E data) {
        this.fileName = fileName;
        this.data = data;
        loadData();
    }

    public void saveData() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/database/" + fileName + ".dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/database/" + fileName + ".dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            data = (E) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception ex) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
        saveData();
    }

}
