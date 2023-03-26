package sk.stuba.fei.uim.oop.karty.modreKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.plocha.Plocha;

public class Vazenie extends ModraKarta {

    private static final String CARD_NAME = "Vazenie";

    public Vazenie() {
        super(CARD_NAME);
    }


    @Override
    public boolean vyzadujeCielovehoHraca() {
        return super.vyzadujeCielovehoHraca();
    }

    @Override
    public void hraj(Hrac hrac) {
        super.hraj(hrac);
        hrac.getSpoecialneKarty().add(this);
    }
}
