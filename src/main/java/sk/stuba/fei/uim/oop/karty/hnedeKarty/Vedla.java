package sk.stuba.fei.uim.oop.karty.hnedeKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class Vedla extends HnedaKarta {

    private static final String CARD_NAME = "Vedla";

    public Vedla() {
        super(CARD_NAME);
    }

    @Override
    public void hraj(Hrac hrac) {
        super.hraj(hrac);
    }

    @Override
    public boolean jeKartahratelna(Hrac hrac) {
        return false;
    }
}
