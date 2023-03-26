package sk.stuba.fei.uim.oop.karty.hnedeKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;
public abstract class HnedaKarta extends Karta {

    public HnedaKarta(String meno) {
        super(meno);
    }

    @Override
    public void hraj(Hrac hrac) {
        hrac.getKartyNaRuke().remove(this);
    }
}
