package sk.stuba.fei.uim.oop.karty.modreKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class Vazenie extends ModraKarta {

    private static final String CARD_NAME = "Vazenie";

    public Vazenie() {
        super(CARD_NAME);
    }


    @Override
    public boolean vyzadujeCielovehoHraca() {
        return true;
    }

    @Override
    public void hraj(Hrac hrac) {

        hrac.getSpecialneKarty().add(this);
    }
}
