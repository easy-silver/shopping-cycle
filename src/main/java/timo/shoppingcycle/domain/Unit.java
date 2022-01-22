package timo.shoppingcycle.domain;

import lombok.Getter;

/**
 * 물건 단위
 */
@Getter
public enum Unit {
    ml("밀리리터"),
    l("리터"),
    ea("개");

    private String title;

    Unit(String title) {
        this.title = title;
    }
}
