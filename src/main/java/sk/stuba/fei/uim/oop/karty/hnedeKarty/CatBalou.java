package sk.stuba.fei.uim.oop.karty.hnedeKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class CatBalou extends HnedaKarta {

    private static final String CARD_NAME = "Cat Balou";

    public CatBalou() {
        super(CARD_NAME);
    }


    @Override
    public boolean vyzadujeCielovehoHraca() {
        return true;
    }

    @Override
    public void hraj(Hrac hrac) {
        hrac.hrajCatBalou();
    }

    @Override
    public boolean jeKartahratelna(Hrac hrac) {
        if (hrac.getSpecialneKarty().size() > 0 || hrac.getKartyNaRuke().size() > 0) {
            return true;
        }
        System.out.println("Na tohoto hráča sa karta neda zahrať");
        return false;
    }
}
