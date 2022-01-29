package utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Out {
    private static String message;
    private static BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));
    public static void print(String linha) {
        setWrite(linha);
    }

    public static void print() {
        setWrite("");
    }

    public static void println(String linha) {
        setWrite(linha + "\n");
    }

    public static void println() {
        setWrite("\n");
    }

    private static void setWrite(String str) {
        try{
            bw.write(str);
            bw.flush();
        }catch(IOException e){
            message = "IOException: " + e.getMessage();
        }

    }

    public static void close() {
        try{
            bw.close();
        }catch(IOException e){
            message = "IOException: " + e.getMessage();
        }
    }
}
