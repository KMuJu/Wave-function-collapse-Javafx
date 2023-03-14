package MittProsjekt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ScoreFileSupport {
    

    public void writeFile(HashMap<String, List<Double>> scoreMapList){
        String write = "";
        for (String key : scoreMapList.keySet()){
            write += key + ":\r\n";
            for (Double d : scoreMapList.get(key)){
                write += Double.toString(d) + "\r\n";
            }
            write += "\r\n";
        }

        try{
            // System.out.println("AAA " + write);
            FileWriter fileWriter = new FileWriter("MittProsjekt\\scores.txt");
            fileWriter.write(write);
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public HashMap<String, List<Double>> getScoreMap(){
        HashMap<String, List<Double>> retur = new HashMap<>();
        try{
            String linje = Files.readString(new File("MittProsjekt\\scores.txt").toPath());
            System.out.println(linje);
            for (String v : linje.split("\r\n\r\n")){
                List<Double> l = new ArrayList<>();
                for (int i = 1; i<v.split("\n").length; i++){
                    l.add(Double.parseDouble(v.split("\n")[i]));
                }
                retur.put(v.split(":")[0], l);
            }
            

            return retur;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void writeFile(String text){
        try{
            FileWriter fileWriter = new FileWriter("MittProsjekt\\scores.txt");
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
