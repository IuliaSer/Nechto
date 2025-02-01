package nechto.service;

import nechto.enums.Status;
import nechto.status.*;
import org.springframework.stereotype.Component;

@Component
public class StatusFactory {
    StatusInterface createStatus(Status status) {
        switch (status) {
            case NECHTO: return new Nechto();
            case WON: return new Won();
            case HUMAN: return new Human();
            case BURNED: return new Burned();
            case VICTIM: return new Victim();
            case CONTAMINATED: return new Contaminated();
            case DANGEROUS: return new Dangerous();
            case USEFULL: return new Usefull();
            case FLAMETHROWER: return new Flamethrower();
            case PENALTY: return new Penalty();
        }
        return null;
    }
}
