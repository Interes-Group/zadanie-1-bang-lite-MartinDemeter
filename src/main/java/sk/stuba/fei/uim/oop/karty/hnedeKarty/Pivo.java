package sk.stuba.fei.uim.oop.karty.hnedeKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class Pivo extends HnedaKarta {

    private static final String CARD_NAME = "Pivo";

    public Pivo() {
        super(CARD_NAME);
    }


    @Override
    public void hraj(Hrac hrac) {
        super.hraj(hrac);
        hrac.plusZivot();

    }
}
