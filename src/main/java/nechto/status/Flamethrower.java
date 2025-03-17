package nechto.status;

import nechto.entity.Scores;
import org.springframework.stereotype.Component;

import java.util.List;

import static nechto.enums.Status.FLAMETHROWER;

@Component
public class Flamethrower implements Status {

    @Override
    public float count(List<nechto.enums.Status> statuses, List<Scores> scoresList) {
        return 0.3f;
    }

    @Override
    public nechto.enums.Status getStatus() {
        return FLAMETHROWER;
    }
}
