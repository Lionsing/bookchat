package com.server;

import java.net.*;
import java.util.*;
import java.io.*;

import com.common.Message;
import com.common.User;
import com.server.db.sqlHelper;;
/**
 * 
 * ��̨������
 *
 */

public class Myserver {
    public static void main(String[] args)
    {
        Myserver ms = new Myserver();
        
    }
    public Myserver()
    {
        try {
            System.out.println("��6666�˿ڼ���...");
            //��6666�˿ڼ���
            ServerSocket ss = new ServerSocket(6666);
            while (true) {
                // �������ȴ�����
                Socket s = ss.accept();
                // ���ܴӿͻ��˷����Ķ�����
                ObjectInputStream ois = new ObjectInputStream(
                        s.getInputStream());
                User u = (User) ois.readObject();

                System.out.println(u.getName() + " " + u.getPassword());
                
                sqlHelper sqlh = new sqlHelper();
                boolean b = sqlh.register(u.getName(), u.getPassword());
                Message ms = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                
                if (b == true) {
                    ms.setMesType("1");
                    oos.writeObject(ms);
                } else {
                    ms.setMesType("2");
                    oos.writeObject(ms);
                    s.close();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
