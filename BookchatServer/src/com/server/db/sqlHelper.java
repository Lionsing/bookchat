package com.server.db;

import java.sql.*;
import java.util.*;

import com.common.*;
/*
 * 1 public static boolean checkLogin(String name, String password)��֤�û�����Ϣ 
 * 2 public static boolean register(String name, String password)��֤�Ƿ�ע��ɹ� 
 */
public class sqlHelper {
    //��һ�������ݿ��IP��ַ���˿ں����ƣ� �ڶ������������ݿ���û����� ����������ʱ���ݿ������
    private static String[] connectionParameters = { "jdbc:mysql://localhost:3306/test","root","dear_zz520"};
    
    //��½���
    public static boolean checkLogin(String name, String password)
    {
        Connection conn = null;//�������ݿ����Ӷ���
        Statement stmt = null; //�������ݿ���ʽ
        ResultSet rs = null; //�������������
        try {
            //����mysql�������ַ���
            Class.forName("com.mysql.jdbc.Driver");
            //��ȡ���ݿ�����
            conn = DriverManager.getConnection(connectionParameters[0], connectionParameters[1], connectionParameters[2]);
            //��ȡ���ʽ����ʵ��
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE name='"+name+"' AND password = '"+password+"'");
            if (rs.next()) {
                return true;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //ע��
    public static boolean register(String name, String password)
    {
        Connection conn = null;//�������ݿ����Ӷ���
        Statement stmt = null; //�������ݿ���ʽ
        ResultSet rs = null; //�������������
        try {
          //����mysql�������ַ���
            Class.forName("com.mysql.jdbc.Driver");
            //��ȡ���ݿ�����
            conn = DriverManager.getConnection(connectionParameters[0], connectionParameters[1], connectionParameters[2]);
            //��ȡ���ʽ����ʵ��
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE name='"+name+"'");
            //������ڸ��û����򷵻�false,��������ע��
            if(rs.next()) {
                conn.close();
                return false;
            } else {
                //����1�ʹ�������˼�¼
                int i = stmt.executeUpdate("INSERT INTO users (name, password) VAlUES ('"+name+"', '"+password+"')");
                
                if (i == 1) return true;
                else {
                    conn.close();
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
