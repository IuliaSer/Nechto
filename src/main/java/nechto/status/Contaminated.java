package nechto.status;

import nechto.entity.Scores;
import nechto.enums.Status;

import java.util.List;

import static nechto.enums.Status.WON;

public class Contaminated implements StatusInterface {

    @Override
    public float count(List<Status> statuses, List<Scores> scoresList) {
        return statuses.contains(WON) ? 1 : -1;
    }
}
