package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.plocha.Plocha;

public class Bang extends Karta{

    private static final String CARD_NAME = "Bang";

    public Bang() {
        super(CARD_NAME);
    }

    @Override
    public void zahrajKartu(Hrac hrac) {
        super.zahrajKartu(hrac);
        hrac.minusZivot();
    }
}
