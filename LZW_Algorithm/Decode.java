//Name: Yash Anil Bonde
//StudentId: 801119029


package Algo_Project1;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_16BE;

public class Decode {
    public static void main(String[] args) throws IOException {
        decode(args[0],Integer.parseInt(args[1]));
    }
    public static void decode(String file, Integer n) throws IOException {


        //Createing and initiating map with Integer as key and Strig as value
        Map<Integer,String> map = new HashMap<>();
        int mapsize=0;
        for(int i=0;i<256;i++){
            map.put(i,Character.toString((char) i));
            mapsize++;
        }
        //maxsize of table
        int maxsize = (int) Math.pow(2,n);

        //Reading the input from the file using FileInputStream and InputStreamReader
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF_16BE");


        //Arraylist to store the value from the encoded file
        ArrayList<String> str = new ArrayList<>();
        int val;
        //Adding value to the arraylist
        while((val=inputStreamReader.read())!=-1){
            str.add(String.valueOf(val));
        }

        //Algorithm to decode the value from the file
        StringBuilder res = new StringBuilder();
        String new_String = null;

        int code = Integer.parseInt(str.get(0));
        String string = map.get(code);
        res.append(string);
        for(int i=1;i<str.size();i++){
            code = Integer.parseInt(str.get(i));
            if(!map.containsKey(code)){
                new_String = string + string.charAt(0);
            }else{
                new_String = map.get(code);
            }
            res.append(new_String);
            if(map.size()<maxsize){
                map.put(map.size(),string+new_String.charAt(0));
            }
            string = new_String;
        }
        //


        //Creating the file to write the decoded output
        File file1 = new File(file.substring(0,file.lastIndexOf("."))+"_decoded.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        //writting teh output to the file
        outputStreamWriter.write(res.toString());
        //Closing the outputStreamWriter
        outputStreamWriter.close();

    }

}
