package sk.stuba.fei.uim.oop.karty.modreKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.plocha.Plocha;

public class Barrel extends ModraKarta {

    private static final String CARD_NAME = "Barrel";

    public Barrel() {
        super(CARD_NAME);
    }

    @Override
    public void hraj(Hrac hrac) {
        hrac.getSpoecialneKarty().add(this);
        super.hraj(hrac);
    }



    @Override
    public boolean jeKartahratelna(Hrac hrac) {
        for (Karta karta : hrac.getSpoecialneKarty())
            if (karta instanceof Barrel){
                System.out.println("Barrel sa neda zahrat");
                return false;
            }
        return true;
    }
}

