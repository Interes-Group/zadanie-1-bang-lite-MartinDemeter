package sk.stuba.fei.uim.oop.plocha;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.*;
import sk.stuba.fei.uim.oop.karty.hnedeKarty.*;
import sk.stuba.fei.uim.oop.karty.modreKarty.Barrel;
import sk.stuba.fei.uim.oop.karty.modreKarty.Dynamit;
import sk.stuba.fei.uim.oop.karty.modreKarty.Vazenie;

import java.util.ArrayList;
import java.util.Collections;

public class Plocha {

    private ArrayList<Karta> balikKariet;

    private ArrayList<Karta> odhadyovaciBalikKariet;

    public Plocha() {
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
        for (int i = 0; i < 20; i++) {
            balikKariet.add(new Dynamit());
        }
        balikKariet.add(new Indiani());
        balikKariet.add(new Indiani());
        balikKariet.add(new Barrel());
        balikKariet.add(new Barrel());
        balikKariet.add(new Dynamit());

         Collections.shuffle(this.balikKariet);

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
        Karta karta = this.balikKariet.get(0);
        this.balikKariet.remove(0);
        return karta;
    }
    public void prehodKarty() {
        if (this.balikKariet.size()==0 || this.balikKariet.size()==1){
            this.balikKariet.addAll(this.odhadyovaciBalikKariet);
            this.odhadyovaciBalikKariet.clear();
        }
    }
}
