package nechto.status;

import nechto.entity.Scores;
import nechto.enums.Status;

import java.util.List;

import static nechto.enums.Status.*;

public class Human implements StatusInterface {

    @Override
    public float count(List<Status> statuses, List<Scores> scoresList) {
        float scores = 0;
        int amountOfContaminated = 0;
        int burned = 0;
        int players = 0;
        if(statuses.contains(WON)) {
            scores+=1;
            for (Scores score : scoresList) {
                if (score.getStatuses().contains(CONTAMINATED)) {
                    amountOfContaminated++;
                } else if (score.getStatuses().contains(BURNED)) {
                    burned++;
                }
                players++;
            }
            int amountOfWinners = players - amountOfContaminated - 1;
            if(amountOfContaminated > players/2) {
                scores += amountOfContaminated - burned - amountOfWinners;
            }
        }
        return scores;
    }
}
