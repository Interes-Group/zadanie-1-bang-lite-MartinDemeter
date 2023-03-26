package sk.stuba.fei.uim.oop.karty.modreKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class Dynamit extends ModraKarta {

    private static final String CARD_NAME = "Dynamit";

    public Dynamit() {
        super(CARD_NAME);
    }

    @Override
    public void hraj(Hrac hrac) {
        super.hraj(hrac);
        hrac.getSpecialneKarty().add(this);
    }

    @Override
    public boolean jeKartahratelna(Hrac hrac) {
        return super.jeKartahratelna(hrac);
    }
}
