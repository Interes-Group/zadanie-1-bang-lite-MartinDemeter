package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.plocha.Plocha;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;


import java.util.ArrayList;

public class BangFei {

    private final Hrac[] hraci;

    private int aktualnyHrac;

    private Plocha plocha;


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

        this.plocha = new Plocha(this.hraci);
        this.zaciatokHry();
    }

    private void zaciatokHry() {
        System.out.println("\n--- GAME STARTED ---");
        while (this.pocetAktivnychHracov() > 1) {
            Hrac aktivnyHrac = this.hraci[this.aktualnyHrac];
            if (!aktivnyHrac.jeAktivny()) {
                ArrayList<Karta> kartyDoBalicka = aktivnyHrac.zoberKartyHraca();
                for (Karta karta : kartyDoBalicka) {
                    this.plocha.pridajKartuDoBalika(karta);
                }
                this.pocitadlo();
                continue;
            }

            System.out.println("\n--- PLAYER " + aktivnyHrac.getMeno() + " STARTS TURN ---\n");
            this.koloHry(aktivnyHrac);
            this.pocitadlo();
        }

    }

    private void koloHry(Hrac aktivnyHrac) {
        aktivnyHrac.getKartyNaRuke().add(this.plocha.dajKartu());
        aktivnyHrac.getKartyNaRuke().add(this.plocha.dajKartu());

        System.out.println(this.plocha.getOdhadyovaciBalikKariet().size());
        System.out.println(this.plocha.getBalikKariet().size());

        int cisloKarty = 0;
//        vypisKariet(aktivnyHrac, hratelneKarty);
        do {
            if (this.pocetAktivnychHracov() == 1) {
                vytaz();
                break;
            }

            this.vypisHracov(aktivnyHrac);
            System.out.println("Tvoje Karty: ");
            for (int i = 0; i < aktivnyHrac.getKartyNaRuke().size(); i++) {
                System.out.print("[" + (i + 1) + "] " + aktivnyHrac.getKartyNaRuke().get(i).getClass().getSimpleName() + "  ");
            }

            cisloKarty = vyberKartu(aktivnyHrac.getKartyNaRuke(), "play");
            if (cisloKarty >= 0){
                aktivnyHrac.getKartyNaRuke().get(cisloKarty).zahrajKartu(aktivnyHrac);
                this.plocha.pridajKartuDoOdhadzovaciehoBalika(aktivnyHrac.getKartyNaRuke().get(cisloKarty));
                aktivnyHrac.odhodKartu(cisloKarty);
            }

            if (aktivnyHrac.getKartyNaRuke().isEmpty()) {
//                System.out.println(aktivnyHrac.getKartyNaRuke().size());
//                System.out.println(aktivnyHrac.getKartyNaRuke().isEmpty());
                break;
            }

        } while (cisloKarty != -1);

       if (aktivnyHrac.getKartyNaRuke().size() > aktivnyHrac.getZivoty()) {
           aktivnyHrac.odhodKarty(this.plocha.getOdhadyovaciBalikKariet());
       }
    }




//    private void vypisKariet(Hrac aktivnyHrac, ArrayList<Karta> hratelneKarty) {
//
//        do {
//        System.out.println("Tvoje Karty: ");
//            for (int i = 0; i < hratelneKarty.size(); i++) {
//                System.out.print("[" + (i + 1) + "] " + hratelneKarty.get(i).getClass().getSimpleName() + "  ");
//            }
//            int cisloKarty = vyberKartu(hratelneKarty, "play");
//            hratelneKarty.get(cisloKarty).zahrajKartu(aktivnyHrac);
//        } while ();
//    }


    private int vyberKartu(ArrayList<Karta> karty, String slovo) {
        int cisloKarty = 0;
        while (true) {
            cisloKarty = ZKlavesnice.readInt("\n*** Zadaj cislo karty ktoru chces zahrat alebo 0 pre koniec kola " + slovo + ": ***") - 1;
            if (cisloKarty < -1 || cisloKarty > karty.size() - 1) {
                System.out.println(" !!! You enter wrong number of card. Try Again! !!! ");
            } else {
                break;
            }
        }
        return cisloKarty;
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

    private void vypisHracov(Hrac aktivnyHrac) {
        for (Hrac hrac : this.hraci) {
            if(aktivnyHrac.equals(hrac))
                System.out.print("Na tahu: ");
            System.out.println(hrac.getMeno());
            System.out.println(" Počet žvotov: " + hrac.getZivoty());
            System.out.println(" Počet kariet na ruke: " + hrac.getKartyNaRuke().size() + "\n");

        }
    }

    private void pocitadlo() {
        this.aktualnyHrac++;
        this.aktualnyHrac %= this.hraci.length;
    }

    private void vytaz() {
        for (Hrac hrac : this.hraci) {
            if (hrac.jeAktivny()) {
                System.out.println("\n--- GAME FINISHED ---");
                System.out.println("*** And the WINNER is " + hrac.getMeno() + " ***");

            }
        }
//    return null;
    }
}
