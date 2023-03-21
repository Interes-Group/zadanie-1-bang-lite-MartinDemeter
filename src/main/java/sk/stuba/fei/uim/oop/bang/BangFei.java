package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.plocha.HraciaPlocha;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class BangFei {

    private final Hrac[] hraci;

    private int aktualnyHrac;

    private HraciaPlocha hraciaPlocha;

    public BangFei() {
        System.out.println("--- Welcome to FEI BANG ---");
        int pocetHracov = 0;
        while (pocetHracov < 2 || pocetHracov > 4) {
            pocetHracov = ZKlavesnice.readInt("*** Enter number of players (2-4): ***");
            if (pocetHracov < 2 || pocetHracov > 4) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }
        this.hraci = new Hrac[pocetHracov];
        for (int i = 0; i < pocetHracov; i++) {
            this.hraci[i] = new Hrac(ZKlavesnice.readString("*** Enter name for PLAYER " + (i+1) + " : ***"));
        }

        this.hraciaPlocha = new HraciaPlocha(this.hraci);
        this.zaciatokHry();
    }

    private void zaciatokHry() {
        System.out.println("--- GAME STARTED ---");
        while (this.pocetAktivnychHracov() > 1) {
            Hrac aktivnyHrac = this.hraci[this.aktualnyHrac];
            if (!aktivnyHrac.jeAktivny()) {
                ArrayList<Karta> kartyDoBalicka = aktivnyHrac.zoberKartyHraca();
                for (Karta karta : kartyDoBalicka) {
                    this.hraciaPlocha.pridajKartyDoBalika(karta);
                }
                this.pocitadlo();
                continue;
            }

            System.out.println();
            System.out.println();
            this.hrajKolo(aktivnyHrac);
            this.pocitadlo();
        }
        System.out.println();
        System.out.println();
    }

    private void hrajKolo(Hrac aktivnyHrac) {

    }

    private int pocetAktivnychHracov() {
        int pocet = 0;
        for (Hrac hrac : this.hraci) {
            if (hrac.jeAktivny()) {
                pocet++;
            }
        }
        return pocet;
    }

    private void pocitadlo() {
        this.aktualnyHrac++;
        this.aktualnyHrac %= this.hraci.length;
    }
}
