package timo.shoppingcycle.domain;

import lombok.Getter;

@Getter
public enum Unit {
    ml("밀리리터"),
    l("리터")
    ;

    private String title;

    Unit(String title) {
        this.title = title;
    }
}
