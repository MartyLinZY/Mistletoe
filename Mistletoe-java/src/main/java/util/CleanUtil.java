package util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CleanUtil {
    public static void clean(String temp){
        FileWriter writer;
        try {
            FileReader reader=new FileReader("src/main/resources/hw.java");
            writer = new FileWriter("src/main/resources/hw.java");
            temp=temp.replace("#","//#");
            System.out.println(temp);
            writer.write(temp);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
       CleanUtil.clean("111");
    }
}
