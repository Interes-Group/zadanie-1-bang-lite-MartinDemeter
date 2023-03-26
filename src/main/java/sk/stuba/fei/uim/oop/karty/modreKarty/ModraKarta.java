package sk.stuba.fei.uim.oop.karty.modreKarty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;

public abstract class ModraKarta extends Karta {
    public ModraKarta(String meno) {
        super(meno);
    }

    @Override
    public void hraj(Hrac hrac) {
        hrac.getKartyNaRuke().remove(this);
    }


}
