package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import principal.Principal;

public class User implements Serializable, Comparable<User> {
    private String username;
    private String name;
    private String password;
    private String type;
    private Date lastMessageDate;
    public static String TYPE_ADMIN = "admin";
    public static String TYPE_CLIENT = "client";
    public static String TYPE_DELIVERY_PEOPLE = "delivery-people";

    public User(String name, String username, String password, String type) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
        lastMessageDate = new Date();
        Principal.supermarketData.addUser(this);
    }

    public final void copia(File fonte, File destino) throws IOException {
        OutputStream out;
        try (InputStream in = new FileInputStream(fonte)) {
            out = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
        out.close();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(Date lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    @Override
    public int compareTo(User o) {
        if (lastMessageDate.before(o.lastMessageDate)) {
            return 1;
        }
        if (lastMessageDate.after(o.lastMessageDate)) {
            return -1;
        }
        return 0;
    }

}
