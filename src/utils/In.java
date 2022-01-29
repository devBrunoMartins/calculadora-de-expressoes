package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class In {
    private static String message;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String read(){
        

        String str = null;
        try{
            
            str = br.readLine();

        }catch(IOException e){
            message = "IOException: " + e.getMessage();
        }

        return str;
    }

    public static void close(){
        try{
            br.close();
        }catch (IOException e){
            Out.println("ERRO: " + e.getMessage());
        }
    }

}
