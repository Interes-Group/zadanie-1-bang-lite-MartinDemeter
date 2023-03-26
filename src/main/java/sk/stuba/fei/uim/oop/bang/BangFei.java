package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.karty.modreKarty.Dynamit;
import sk.stuba.fei.uim.oop.karty.modreKarty.Vazenie;
import sk.stuba.fei.uim.oop.plocha.Plocha;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class BangFei {

    private final ArrayList<Hrac> hraci;

    private int aktualnyHrac;

    private final Plocha plocha;

    public BangFei() {
        System.out.println("--- Vitaj zahráme si FEI BANG ---");
        int pocetHracov = 0;
        while (pocetHracov < 2 || pocetHracov > 4) {
            pocetHracov = ZKlavesnice.readInt("*** Zadaj počet hráčov (2-4): ***");
            if (pocetHracov < 2 || pocetHracov > 4) {
                System.out.println(" !!! Zadal si číslo mimo rozsahu. Skús to znova! !!!");
            }
        }
        this.plocha = new Plocha();
        this.hraci = new ArrayList<>();
        for (int i = 0; i < pocetHracov; i++) {
            this.hraci.add(new Hrac(this.plocha, ZKlavesnice.readString("*** Zadaj meno pre hráča " + (i + 1) + " : ***")));
        }

        for (Hrac hrac : hraci) {
            for (int i = 0; i < 4; i++) {
                hrac.zoberKartu();
            }
        }

        this.zaciatokHry();
    }

    private void zaciatokHry() {
        System.out.println("\n--- HRA ZAČALA ---");
        while (this.pocetAktivnychHracov() > 1) {

            Hrac aktivnyHrac = this.hraci.get(aktualnyHrac);
            skontrolujEfekty(aktivnyHrac);
            if (!aktivnyHrac.jeAktivny()) {
                this.pocitadlo();
                continue;
            }

            System.out.println("\n--- HRÁČ " + aktivnyHrac.getMeno() + " ZAČAL KOLO ---\n");
            if (!aktivnyHrac.isVoVazenie()) {
                this.koloHry(aktivnyHrac);
            }
            this.pocitadlo();
        }

    }

    private void koloHry(Hrac aktivnyHrac) {
        this.plocha.prehodKarty();
        if (this.plocha.getBalikKariet().size() >= 1) {
            if (this.plocha.getBalikKariet().size() == 1) {
                aktivnyHrac.getKartyNaRuke().add(this.plocha.dajKartu());
            } else {
                aktivnyHrac.getKartyNaRuke().add(this.plocha.dajKartu());
                aktivnyHrac.getKartyNaRuke().add(this.plocha.dajKartu());
            }
        }

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

            cisloKarty = aktivnyHrac.vyberKartu();

            if (cisloKarty >= 0) {
                if (aktivnyHrac.getKartyNaRuke().get(cisloKarty).vyzadujeCielovehoHraca()) {
                    cielovyHrac = aktivnyHrac.vyberHraca(this.hraci, cisloKarty);
                    aktivnyHrac.zahrajKartu(cielovyHrac, cisloKarty);
                    aktivnyHrac.getKartyNaRuke().remove(cisloKarty);
                } else if (aktivnyHrac.getKartyNaRuke().get(cisloKarty).vyzadujeHracov()) {
                    aktivnyHrac.zahrajKartu(this.hraci, cisloKarty);
                } else {
                    aktivnyHrac.zahrajKartu(aktivnyHrac, cisloKarty);
                }
            }

            if (aktivnyHrac.getKartyNaRuke().isEmpty()) {
                break;
            }
        } while (cisloKarty != -1);

        if (aktivnyHrac.getKartyNaRuke().size() > aktivnyHrac.getZivoty()) {
            aktivnyHrac.odhodKarty();
        }
    }

    public void skontrolujEfekty(Hrac aktivnyHrac) {
        ArrayList<Karta> kartyNaStole;
        int predchdzajuciHrac = ((aktualnyHrac) + this.hraci.size() - 1) % this.hraci.size();
        kartyNaStole = aktivnyHrac.getSpecialneKarty();
        for (Karta karta : kartyNaStole) {
            if (karta instanceof Dynamit) {
                aktivnyHrac.hrajDynamit(this.hraci, karta, predchdzajuciHrac);
                break;
            }
        }
        aktivnyHrac.setVoVazenie();
        for (Karta karta : kartyNaStole) {
            if (karta instanceof Vazenie) {
                aktivnyHrac.hrajVazenie(karta);
                break;
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

            if (aktivnyHrac.equals(hrac)) {
                System.out.print("Na ťahu: ");
            }
            System.out.println(hrac.getMeno());
            System.out.println(" Počet životov: " + hrac.getZivoty());
            System.out.print(" Karty na stole: ");
            if (hrac.getSpecialneKarty().size() != 0) {
                hrac.vypisKartyNaStole();
            }

            System.out.println("\n Počet kariet na ruke: " + hrac.getKartyNaRuke().size() + "\n");
        }
    }

    private void pocitadlo() {
        this.aktualnyHrac++;
        this.aktualnyHrac %= this.hraci.size();
    }

    private void vytaz() {
        for (Hrac hrac : this.hraci) {
            if (hrac.jeAktivny()) {
                System.out.println("\n--- KONIEC HRY ---");
                System.out.println("*** A VÝŤAZ je " + hrac.getMeno() + " ***");

            }
        }
    }
}
