package sk.stuba.fei.uim.oop.hrac;

import sk.stuba.fei.uim.oop.karty.Karta;

import java.util.ArrayList;

public class Hrac {

    private final String meno;

    private int zivoty;

    private ArrayList<Karta> kartyNaRuke;

    private ArrayList<Karta> spoecialneKarty;
    public Hrac(String meno) {
        this.kartyNaRuke = new ArrayList<Karta>();
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
        this.kartyNaRuke = karty;
    }

    public ArrayList<Karta> zoberKartyHraca() {
        ArrayList<Karta> zobrateKarty = new ArrayList<>(this.kartyNaRuke);
        this.kartyNaRuke.clear();
        return zobrateKarty;
    }

//    public ArrayList<Karta> kartyNaRuke() {
//        ArrayList<Karta> karty = new ArrayList<Karta>();
//        for (Karta karta : this.kartyNaRuke) {
//            karty.add(karta);
//        }
//        return karty;
//    }

    public void odhodKarty(ArrayList<Karta> odhadyovaciBalikKariet) {
        while (this.kartyNaRuke.size() > this.zivoty) {
            int rando = (int)((Math.random()*this.kartyNaRuke.size()));
            odhadyovaciBalikKariet.add(this.kartyNaRuke.get(rando));
            this.kartyNaRuke.remove(rando);
            System.out.println(rando);
        }
    }

    public void odhodKartu(int cisloKarty) {
        this.kartyNaRuke.remove(cisloKarty);
    }

    public ArrayList<Karta> getKartyNaRuke() {
        return kartyNaRuke;
    }

    public void minusZivot() {
        this.zivoty--;
    }

    public void plusZivot() {
        this.zivoty++;
    }

    public boolean jeAktivny() {
        return this.zivoty > 0;
    }

    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }
}
