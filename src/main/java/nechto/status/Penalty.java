package nechto.status;

import nechto.entity.Scores;
import nechto.enums.Status;

import java.util.List;

public class Penalty implements StatusInterface {

    @Override
    public float count(List<Status> statuses, List<Scores> scoresList) {
        return -2;
    }
}
