package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.plocha.Plocha;

public abstract class Karta {
    protected String meno;



    public Karta(String meno) {
        this.meno = meno;
    }


    public void hraj(Hrac hrac){

    }

    public boolean vyzadujeCielovehoHraca() {
        return false;
    }

    public boolean vyzadujeHracov() {
        return false;
    }
    public boolean jeKartahratelna(Hrac hrac) {
        return true;
    }

}
