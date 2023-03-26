package sk.stuba.fei.uim.oop.karty.hnedeKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class Bang extends HnedaKarta {

    private static final String CARD_NAME = "Bang";

    public Bang() {
        super(CARD_NAME);
    }

    @Override
    public void hraj(Hrac hrac) {
        hrac.hrajBang();
    }

    @Override
    public boolean vyzadujeCielovehoHraca() {
        return true;
    }
}
