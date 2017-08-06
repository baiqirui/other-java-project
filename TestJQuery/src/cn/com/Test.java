package cn.com;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;

public class Test
{
    public static void main(String[] args)
        throws Exception
    {
        FileReader in= new FileReader("D:/test/a.txt");
        FileWriter out = new FileWriter("D:/test/b.txt");
        StringWriter sw = new StringWriter();
        int data = in.read();
        while (data != -1)
        {
            out.write(data);
            out.flush();
            
            sw.write(data);
            sw.flush();
            data = in.read();
        }
        
    }
}
