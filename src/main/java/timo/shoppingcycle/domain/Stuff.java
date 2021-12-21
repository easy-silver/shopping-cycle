package timo.shoppingcycle.domain;

/**
 * 물건의 정보를 관리하는 Stuff 클래스
 */
public class Stuff {

    private Long id;

    private final String name;

    public Stuff(String name) {
        this.name = name;
    }

}
