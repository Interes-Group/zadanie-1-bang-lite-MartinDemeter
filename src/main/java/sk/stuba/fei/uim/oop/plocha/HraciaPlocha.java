package sk.stuba.fei.uim.oop.plocha;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;

import java.util.ArrayList;

public class HraciaPlocha {

    private ArrayList<Karta> balikKariat;
    public HraciaPlocha(Hrac[] hraci) {
    }

    public void pridajKartyDoBalika(Karta karta) {
        this.balikKariat.add(karta);
    }
}
