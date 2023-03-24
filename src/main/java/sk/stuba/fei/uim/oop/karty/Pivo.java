package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.plocha.Plocha;

public class Pivo extends Karta{

    private static final String CARD_NAME = "Pivo";

    public Pivo() {
        super(CARD_NAME);
    }

    @Override
    public void zahrajKartu(Hrac hrac) {
        hrac.plusZivot();
    }
}
