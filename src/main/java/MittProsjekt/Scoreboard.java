package MittProsjekt;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Scoreboard {
    HashMap<String, List<Double>> scoreMap;
    ScoreFileSupport fileSupport = new ScoreFileSupport();
    int antallPaaLeaderboard = 3;

    Scoreboard(){
        scoreMap = fileSupport.getScoreMap();
        System.out.println(scoreMap.toString());
    }

    public void newTime(String vanskelighetsgrad, Double tid){
        System.out.println("Tid " + tid);
        List<Double> l = scoreMap.get(vanskelighetsgrad);
        if (l.size()<3){
            l.add(tid);
            Collections.sort(l);
            write();
            return;
        }
        if (l.get(antallPaaLeaderboard-1)>tid){
            l.add(tid);
            Collections.sort(l);
            l.remove(antallPaaLeaderboard);
            write();
        }
        System.out.println(l.toString());
    }

    public void write(){
        fileSupport.writeFile(scoreMap);
    }
}
