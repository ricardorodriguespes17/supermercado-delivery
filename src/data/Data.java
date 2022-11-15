package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data<E> implements Serializable {

    private String fileName;
    private List<E> data;

    public Data(String fileName) {
        this.fileName = fileName;
        this.data = new ArrayList<>();
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
            data = (ArrayList<E>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<E> getData() {
        return data;
    }

    public void addData(E newData) {
        data.add(newData);
        saveData();
    }

}
