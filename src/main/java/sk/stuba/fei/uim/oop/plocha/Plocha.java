package sk.stuba.fei.uim.oop.plocha;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.*;

import java.util.ArrayList;
import java.util.Collections;

public class Plocha {

    private ArrayList<Karta> balikKariet;

    private ArrayList<Karta> odhadyovaciBalikKariet;

    public Plocha(Hrac[] hraci) {
        this.balikKariet =  new ArrayList<>();
        this.odhadyovaciBalikKariet = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            balikKariet.add(new Bang());
        }
        for (int i = 0; i < 15; i++) {
            balikKariet.add(new Vedla());
        }
        for (int i = 0; i < 8; i++) {
            balikKariet.add(new Pivo());
        }
        for (int i = 0; i < 6; i++) {
            balikKariet.add(new CatBalou());
        }
        for (int i = 0; i < 4; i++) {
            balikKariet.add(new Dostavnik());
        }
        for (int i = 0; i < 3; i++) {
            balikKariet.add(new Vazenie());
        }
        balikKariet.add(new Indiani());
        balikKariet.add(new Indiani());
        balikKariet.add(new Barrel());
        balikKariet.add(new Barrel());
        balikKariet.add(new Dynamit());

         Collections.shuffle(this.balikKariet);
        for (Hrac hrac : hraci) {
            ArrayList<Karta> noveKarty = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                noveKarty.add(balikKariet.remove(0));
            }
            hrac.setKarty(noveKarty);
        }
    }

    public void vytlacPlochu() {
        System.out.println();
    }

    public void pridajKartuDoBalika(Karta karta) {
        this.balikKariet.add(karta);
    }

    public ArrayList<Karta> getBalikKariet() {
        return balikKariet;
    }

    public void pridajKartuDoOdhadzovaciehoBalika(Karta karta) {
        this.odhadyovaciBalikKariet.add(karta);
    }

    public ArrayList<Karta> getOdhadyovaciBalikKariet() {
        return odhadyovaciBalikKariet;
    }

    public Karta dajKartu() {
        if (this.balikKariet.size()==0){
            this.balikKariet.addAll(this.odhadyovaciBalikKariet);
            this.odhadyovaciBalikKariet.clear();
        }

        Karta karta = this.balikKariet.get(0);
        this.balikKariet.remove(0);

        return karta;
    }
}
