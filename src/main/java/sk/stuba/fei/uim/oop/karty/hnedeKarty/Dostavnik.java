package sk.stuba.fei.uim.oop.karty.hnedeKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class Dostavnik extends HnedaKarta {

    private static final String CARD_NAME = "Dostavnik";

    public Dostavnik() {
        super(CARD_NAME);
    }

    @Override
    public void hraj(Hrac hrac) {
        super.hraj(hrac);
        hrac.zoberKartu();
        hrac.zoberKartu();
    }
}
