//Name: Yash Anil Bonde
//StudentId: 801119029

package Algo_Project1;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_16BE;

public class Encode {

    public static void main(String[] args) throws IOException {
        encode(args[0],Integer.parseInt(args[1]));
    }

    public static void encode(String file, Integer n) throws IOException {
        //Creating the map with String as key and interger as value to store the ascii code of the characters
        Map<String,Integer> map = new HashMap<>();
        int mapsize=0;
        for(int i=0;i<256;i++){
            map.put(Character.toString((char) i),i);
            mapsize++;
        }
        //max table size
        int max_size = (int) Math.pow(2,n);
        //Reading the data from file and appending it to the stringbuilder
        Scanner scanner=new Scanner(new File(file));
        StringBuilder str = new StringBuilder();
        while(scanner.hasNext()){
            str.append(scanner.nextLine());
        }
        String data = str.toString();
        StringBuilder string = new StringBuilder();
        Character symbol = null;
        StringBuilder currString = new StringBuilder();
        StringBuilder res = new StringBuilder();

        //Creating a file to store the encoded value and using UTF_16BE encoder
        File file1 = new File(file.substring(0,file.lastIndexOf("."))+".lzw");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, UTF_16BE);


        //Algo to encode the given input
        for(int i=0;i<data.length();i++){
            symbol = data.charAt(i);
            currString = new StringBuilder();
            currString.append(string.toString()).append(symbol);
            if(map.containsKey(currString.toString())){
                string = new StringBuilder(currString.toString());
            }else{

                res.append(map.get(string.toString())).append(" ");
                int ch = map.get(string.toString());
                //Writting the encoded value to the file
                outputStreamWriter.write(map.get(string.toString()));

                if(map.size()<max_size){
                    map.put(currString.toString(),mapsize++);
                }
                string = new StringBuilder();
                string.append(symbol);
            }
        }

        res.append(map.get(string.toString())).append(" ");
        //Writting the encoded value to the file
        outputStreamWriter.write(map.get(string.toString()));
        //Closing teh outputstreamwritter
        outputStreamWriter.close();



    }


}
