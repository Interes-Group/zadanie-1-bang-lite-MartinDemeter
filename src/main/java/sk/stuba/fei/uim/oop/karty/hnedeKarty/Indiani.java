package sk.stuba.fei.uim.oop.karty.hnedeKarty;


import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.ArrayList;

public class Indiani extends HnedaKarta {

    private static final String CARD_NAME = "Indiani";

    public Indiani() {
        super(CARD_NAME);
    }

    @Override
    public boolean vyzadujeHracov() {
        return true;
    }

    public void hraj(Hrac hrac) {
        super.hraj(hrac);
    }

}
