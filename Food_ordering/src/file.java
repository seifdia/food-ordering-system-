import java.io.*;
import java.util.*;
public class file {
    private static String path;
    public file(String path){
        this.path=path;
    }
    // READING
    public static List <String> read() throws IOException {
        ArrayList<String> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
        reader.close();
        return data;
    }
    //WHRITING
    public static void write(List<String> data) throws IOException{
        BufferedWriter writer=new BufferedWriter(new FileWriter(path));
        for (String record:data){
            writer.write(record);
            writer.newLine();
        }
        writer.close();
    }

}