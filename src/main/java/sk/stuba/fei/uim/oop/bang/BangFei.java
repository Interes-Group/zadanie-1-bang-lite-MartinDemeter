package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.karty.modreKarty.Dynamit;
import sk.stuba.fei.uim.oop.plocha.Plocha;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;


import java.util.ArrayList;

public class BangFei {

    private final ArrayList<Hrac> hraci;

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
        this.plocha = new Plocha();
        this.hraci = new ArrayList<>();
        for (int i = 0; i < pocetHracov; i++) {
            this.hraci.add(new Hrac(this.plocha ,ZKlavesnice.readString("*** Enter name for PLAYER " + (i+1) + " : ***"))) ;
        }



        for (Hrac hrac : hraci) {
            for (int i = 0; i < 4; i++) {
//                hrac.getKartyNaRuke().add(this.plocha.getBalikKariet().remove(0));
                hrac.zoberKartu();
            }
        }

        this.zaciatokHry();
    }

    private void zaciatokHry() {
        System.out.println("\n--- GAME STARTED ---");
        while (this.pocetAktivnychHracov() > 1) {
            Hrac aktivnyHrac = this.hraci.get(aktualnyHrac);
            skontrolujEfekty(aktivnyHrac);




            if (!aktivnyHrac.jeAktivny()) {
//                ArrayList<Karta> kartyDoBalicka = aktivnyHrac.zoberKartyHraca();
//                for (Karta karta : kartyDoBalicka) {
//                    this.plocha.pridajKartuDoBalika(karta);
//                }
                this.pocitadlo();
                continue;
            }

            System.out.println("\n--- PLAYER " + aktivnyHrac.getMeno() + " STARTS TURN ---\n");
            this.koloHry(aktivnyHrac);
            this.pocitadlo();
        }

    }

    private void koloHry(Hrac aktivnyHrac) {

        this.plocha.prehodKarty();
        if (this.plocha.getBalikKariet().size() >= 1) {
            if (this.plocha.getBalikKariet().size() == 1){
                aktivnyHrac.getKartyNaRuke().add(this.plocha.dajKartu());
            } else {
                aktivnyHrac.getKartyNaRuke().add(this.plocha.dajKartu());
                aktivnyHrac.getKartyNaRuke().add(this.plocha.dajKartu());
            }
        }

        System.out.println(this.plocha.getOdhadyovaciBalikKariet().size());
        System.out.println(this.plocha.getBalikKariet().size());

        int cisloKarty = 0;
        Hrac cielovyHrac;

        do {
            if (this.pocetAktivnychHracov() == 1) {
                vytaz();
                break;
            }

            this.vypisDetailHracov(aktivnyHrac);
            System.out.println("Tvoje Karty: ");
            aktivnyHrac.vypisKarty();

            cisloKarty = aktivnyHrac.vyberKartu("zahrat");

            if (cisloKarty >= 0) {
                System.out.println("cislo karty" + cisloKarty);
                if (aktivnyHrac.getKartyNaRuke().get(cisloKarty).vyzadujeCielovehoHraca()) {
                    cielovyHrac = aktivnyHrac.vyberHraca(this.hraci, cisloKarty);
                    aktivnyHrac.zahrajKartu(cielovyHrac, cisloKarty);
                    aktivnyHrac.getKartyNaRuke().remove(cisloKarty);
                }else if (aktivnyHrac.getKartyNaRuke().get(cisloKarty).vyzadujeHracov()) {
                    aktivnyHrac.zahrajKartu(this.hraci, cisloKarty);
                } else {
                    aktivnyHrac.zahrajKartu(aktivnyHrac, cisloKarty);
                }


            }

            if (aktivnyHrac.getKartyNaRuke().isEmpty()) {
//                System.out.println(aktivnyHrac.getKartyNaRuke().size());
//                System.out.println(aktivnyHrac.getKartyNaRuke().isEmpty());
                break;
            }

        } while (cisloKarty != -1);

       if (aktivnyHrac.getKartyNaRuke().size() > aktivnyHrac.getZivoty()) {
           aktivnyHrac.odhodKarty();
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


//    private int vyberVPoli(int velkostPola, String slovo) {
//        int cisloKarty = 0;
//        while (true) {
//            cisloKarty = ZKlavesnice.readInt("\n*** Zadaj cislo ktore chces " + slovo + " alebo 0 pre koniec kola: ***") - 1;
//            if (cisloKarty < -1 || cisloKarty > velkostPola - 1) {
//                System.out.println(" !!! You enter wrong number of card. Try Again! !!! ");
//            } else {
//                break;
//            }
//        }
//        return cisloKarty;
//    }

    public void skontrolujEfekty(Hrac aktualnyHrac) {
        ArrayList<Karta> kartyNaStole = new ArrayList<>();
        kartyNaStole = aktualnyHrac.getSpoecialneKarty();
        for (Karta karta : kartyNaStole) {
            if (karta instanceof Dynamit) {

            }
        }
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

    private void vypisDetailHracov(Hrac aktivnyHrac) {
        for (Hrac hrac : this.hraci) {

            if (!hrac.jeAktivny()) {
                ArrayList<Karta> kartyDoBalicka = hrac.zoberKartyHraca();
                for (Karta karta : kartyDoBalicka) {
                    this.plocha.pridajKartuDoBalika(karta);
                }
            }

            if(aktivnyHrac.equals(hrac)) {
                System.out.print("Na tahu: ");
            }
            System.out.println(hrac.getMeno());
            System.out.println(" Počet žvotov: " + hrac.getZivoty());
            System.out.print(" Karty na stole: ");
            if (hrac.getSpoecialneKarty().size() != 0) {
                hrac.vypisKartyNaStole();
            }

            System.out.println("\n Počet kariet na ruke: " + hrac.getKartyNaRuke().size() + "\n");

        }
    }


    private Hrac vyberHraca() {
        int cisloHraca = 0;
        ArrayList<Hrac> hraciNaVyber = new ArrayList<>();
        hraciNaVyber.addAll(this.hraci);
        hraciNaVyber.remove(aktualnyHrac);
//        vypisHracov(hraciNaVyber);
        while (true) {
            cisloHraca = ZKlavesnice.readInt("\n*** Zadaj cislo hraca na ktoreho chces pouzit kartu: ***") - 1;
            System.out.println(hraciNaVyber.size());
            System.out.println(cisloHraca);
            if (cisloHraca < 0 || cisloHraca + 1 > hraciNaVyber.size()) {
                System.out.println(" !!! You enter wrong number of card. Try Again! !!! ");
            } else {
                break;
            }
        }

        return hraciNaVyber.get(cisloHraca);
    }

    private void pocitadlo() {
        this.aktualnyHrac++;
        this.aktualnyHrac %= this.hraci.size();
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
