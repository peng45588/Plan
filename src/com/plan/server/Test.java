package com.plan.server;

import java.io.*;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by snow on 15-6-13.
 */
public class Test {
    static String address = "./store.txt";
    public static void main(String []args){
        System.out.println("add1:"+addStock("fdayfeia123"));
        System.out.println("add2:"+addStock("dfhjl"));
        System.out.println("getall:"+getAllStock());
        System.out.println("remo:"+removeStock("dfhjl"));
    }
    public static boolean addStock(String stock){
        BufferedReader br = null;
        try {
            File file = new File(address);
            if (!file.exists())
                file.createNewFile();
            br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(address), "UTF-8"));
            String str = br.readLine();
            //str 一個jsonarray
            JSONArray jsonArray = new JSONArray();
            if (str!=null&&(!str.equals("")))
                jsonArray = new JSONArray(str);
            for (int i=0;i<jsonArray.length();i++)
                if (jsonArray.get(i).equals(stock)){
                    br.close();
                    return false;
                }
            jsonArray.put(stock);
            br.close();

            PrintWriter pw = new PrintWriter(new File(address));
            pw.println(jsonArray.toString());
            pw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getAllStock(){
        BufferedReader br = null;
        try {
            File file = new File(address);
            if (!file.exists())
                file.createNewFile();
            br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(address), "UTF-8"));
            String str = br.readLine();
            //str 一個jsonarray
            if (str==null){
                br.close();
                return "";
            }
            br.close();
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean removeStock(String stock){

        BufferedReader br = null;
        try {
            File file = new File(address);
            if (!file.exists())
                file.createNewFile();
            br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(address), "UTF-8"));
            String str = br.readLine();
            //str 一個jsonarray
            if (str!=null&&(!str.equals(""))) {
                JSONArray jsonArray = new JSONArray(str);
                for (int i = 0; i < jsonArray.length(); i++) {
                    //刪除某股
                    if (jsonArray.get(i).equals(stock))
                        jsonArray.remove(i);
                }
                br.close();

                PrintWriter pw = new PrintWriter(new File(address));
                pw.println(jsonArray.toString());
                pw.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
