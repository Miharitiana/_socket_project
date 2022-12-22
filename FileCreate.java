package creatfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileCreate {
    
    public void createFichier(String nomFichier,String detail){
        File file = new File(nomFichier + ".txt");
        FileWriter write;
        FileReader read;
        try {
            write=new FileWriter(file,true);
            read = new FileReader(file);

            BufferedWriter writer = new BufferedWriter(write);
            BufferedReader reader = new BufferedReader(read);
            String line = reader.readLine();

            if (line != null) {
                writer.write("\n");
            }

            writer.write(detail);
            System.out.println(detail);
            writer.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
