package sk.stuba.fei.uim.oop.hrac;

import sk.stuba.fei.uim.oop.karty.Karta;

import java.util.ArrayList;

public class Hrac {

    private final String meno;

    private int zivoty;

    private ArrayList<Karta> karty;

    public Hrac(String meno) {
        this.karty = new ArrayList<Karta>();
        this.meno = meno;
        this.zivoty = 4;

    }

    public String getMeno() {
        return meno;
    }

    public int getZivoty() {
        return zivoty;
    }

    public void setKarty(ArrayList<Karta> karty) {
        this.karty = karty;
    }

    public ArrayList<Karta> zoberKartyHraca() {
        ArrayList<Karta> zobrateKarty = new ArrayList<>(this.karty);
        this.karty.clear();
        return zobrateKarty;
    }

    public boolean jeAktivny() {
        return this.zivoty > 0;
    }
}
