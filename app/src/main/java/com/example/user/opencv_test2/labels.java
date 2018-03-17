package com.example.user.opencv_test2;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
/**
 * Created by User on 24.02.2018.
 */

public class labels
{
    String mPath;
    class label
    {
        int num;
        String theLabel;

        public label(String s, int n){
            theLabel = s;
            num = n;
        }
    }

    ArrayList<label> theList = new ArrayList<label>();

    public labels(String Path) {
        mPath = Path;
    }

    //Checks whether or not the list with taken photos is empty
    public boolean isEmpty(){
        return !(theList.size() > 0);
    }

    public void add(String s, int n){
        theList.add(new label(s, n));
    }

    public String get(int i){
        Iterator<label> ILabel = theList.iterator();
        while (ILabel.hasNext()){
            label l = ILabel.next();
            if(l.num==i){
                return l.theLabel;
            }
        }
        return "";
    }

    public int get(String s){
        Iterator<label>ILabel = theList.iterator();
        while (ILabel.hasNext()){
            label l = ILabel.next();
            if(l.theLabel.equalsIgnoreCase(s)){
                return l.num;
            }
        }
        return -1;
    }

    public void Save(){
        try {
            File f = new File(mPath + "faces.txt");
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            Iterator<label> ILabel = theList.iterator();
            while (ILabel.hasNext()){
                label l =ILabel.next();
                bw.write(l.theLabel+ "," + l.num);
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e){
            Log.e("error", e.getMessage()+ " " + e.getCause());
            e.printStackTrace();
        }
    }

    public void Read(){
        try{
            FileInputStream fileInputStream = new FileInputStream(mPath + "faces.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

            String strLine;
            theList = new ArrayList<label>();
            while((strLine = br.readLine()) != null){
                StringTokenizer tokens = new StringTokenizer(strLine, ",");
                String s = tokens.nextToken();
                String sn = tokens.nextToken();

                theList.add(new label(s, Integer.parseInt(sn)));
            }
            br.close();
            fileInputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public int max(){
        int m = 0;
        Iterator<label> ILabel = theList.iterator();
        while(ILabel.hasNext()){
            label l = ILabel.next();
            if(l.num > m){
                m = l.num;
            }
        }
        return  m;
    }
}


