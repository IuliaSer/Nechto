package nechto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
        NECHTO,
        CONTAMINATED,
        HUMAN,
        WON,
        LOOSE,
        DANGEROUS,
        USEFULL,
        VICTIM,
        FLAMETHROWER,
        BURNED,
        PENALTY;

        private String name;

        Status(String name) {
                this.name = name;
        }
}

