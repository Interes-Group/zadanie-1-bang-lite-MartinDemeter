package sk.stuba.fei.uim.oop.hrac;

import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.karty.hnedeKarty.Bang;
import sk.stuba.fei.uim.oop.karty.hnedeKarty.CatBalou;
import sk.stuba.fei.uim.oop.karty.hnedeKarty.Vedla;
import sk.stuba.fei.uim.oop.karty.modreKarty.Barrel;
import sk.stuba.fei.uim.oop.plocha.Plocha;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Hrac {

    private final String meno;

    private int zivoty;

    private ArrayList<Karta> kartyNaRuke;

    private ArrayList<Karta> spoecialneKarty;

    private final Plocha plocha;

    public Hrac(Plocha plocha, String meno) {
        this.kartyNaRuke = new ArrayList<Karta>();
        this.spoecialneKarty = new ArrayList<Karta>();
        this.plocha = plocha;
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

    public void zoberKartu() {
        this.kartyNaRuke.add(this.plocha.dajKartu());
    }

    public void odhodKarty() {
        while (this.kartyNaRuke.size() > this.zivoty) {
//            int rando = (int)((Math.random()*this.kartyNaRuke.size()));
            int random = random(this.kartyNaRuke.size());
//            this.plocha.getOdhadyovaciBalikKariet().add(this.kartyNaRuke.get(rando));
            this.plocha.pridajKartuDoOdhadzovaciehoBalika(this.kartyNaRuke.get(random));
            this.kartyNaRuke.remove(random);
//            System.out.println(random);
        }
    }

    private int random(int velkost) {
        int rando = (int) ((Math.random() * velkost));
        return rando;
    }

    private void odhodKartu(Karta karta) {
//        odhadzovaciBalik.add(karta);
        this.plocha.pridajKartuDoOdhadzovaciehoBalika(karta);

    }

    public ArrayList<Karta> getKartyNaRuke() {
        return kartyNaRuke;
    }

    public ArrayList<Karta> getSpoecialneKarty() {
        return spoecialneKarty;
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

    public void vypisKarty() {
        for (int i = 0; i < this.kartyNaRuke.size(); i++) {
            System.out.print("[" + (i + 1) + "] " + this.kartyNaRuke.get(i).getClass().getSimpleName() + "  ");
        }
    }

    public void vypisKartyNaStole() {
        for (int i = 0; i < this.spoecialneKarty.size(); i++) {
            System.out.print(this.spoecialneKarty.get(i).getClass().getSimpleName() + "  ");
        }
    }

    public void zahrajKartu(ArrayList<Hrac> hraci, int cisloKarty) {
        this.kartyNaRuke.get(cisloKarty).hraj(this);
        this.hrajIndianov(hraci);
    }

    public void zahrajKartu(Hrac cielovyHrac, int cisloKarty) {

        if (this.kartyNaRuke.get(cisloKarty).jeKartahratelna(this)){
            this.kartyNaRuke.get(cisloKarty).hraj(cielovyHrac);
        }
//        this.odhodKartu(this.kartyNaRuke.get(cisloKarty));

//        if (this.kartyNaRuke.get(cisloKarty) instanceof CatBalou) {
//            this.kartyNaRuke.remove(cisloKarty);
//        }



    }

    //    if (cisloKarty >= 0){
//        aktivnyHrac.getKartyNaRuke().get(cisloKarty).zahrajKartu(aktivnyHrac);
//        this.plocha.pridajKartuDoOdhadzovaciehoBalika(aktivnyHrac.getKartyNaRuke().get(cisloKarty));
//        aktivnyHrac.odhodKartu(cisloKarty);
//    }
    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }

    public Hrac vyberHraca(ArrayList<Hrac> hraci, int karta) {
        int cisloHraca = 0;
        Karta danaKarta = this.getKartyNaRuke().get(karta);

        ArrayList<Hrac> hraciNaVyber = new ArrayList<>();


        for (Hrac hrac : hraci) {
            if (hrac.jeAktivny()) {
                hraciNaVyber.add(hrac);
            }
        }
        hraciNaVyber.remove(this);
        vypisHracov(hraciNaVyber);
        while (true) {
            cisloHraca = ZKlavesnice.readInt("\n*** Zadaj cislo hraca na ktoreho chces pouzit kartu: ***") - 1;
//           System.out.println(cisloHraca);
            if ((cisloHraca < 0 || cisloHraca + 1 > hraciNaVyber.size()) || !danaKarta.jeKartahratelna(hraciNaVyber.get(cisloHraca))) {
                System.out.println(" !!! Zadal si neplatane cislo! !!! ");
            } else {
                break;
            }
        }

        return hraciNaVyber.get(cisloHraca);

    }

    private boolean jeHratelna() {
        if (this.kartyNaRuke.size() > 0 || this.spoecialneKarty.size() > 0) {
            return true;
        }

        return false;
    }

    private void vypisHracov(ArrayList<Hrac> hraciNaVyber) {
        for (int i = 0; i < hraciNaVyber.size(); i++) {
            System.out.print("[" + (i + 1) + "] " + hraciNaVyber.get(i).getMeno() + "  ");
        }

    }

    public int vyberKartu(String slovo) {
        int cisloKarty = 0;
        while (true) {
            cisloKarty = ZKlavesnice.readInt("\n*** Zadaj cislo ktore chces " + slovo + " alebo 0 pre koniec kola: ***") - 1;
            if (cisloKarty < -1 || cisloKarty > this.getKartyNaRuke().size() - 1) {
                System.out.println(" !!! You enter wrong number of card. Try Again! !!! ");
            } else {
                break;
            }
        }
        return cisloKarty;
    }

    public void hrajBang() {
        int random = random(4);
        System.out.println(random);
        for (Karta karta : this.spoecialneKarty) {
            if (karta instanceof Barrel) {
                if (random == 1){
                    return;
                }
            }
        }

        for (Karta karta : this.kartyNaRuke) {
            if (karta instanceof Vedla) {
                this.plocha.getOdhadyovaciBalikKariet().add(karta);
                karta.hraj(this);
                return;
            }
        }
        this.minusZivot();
    }

    public void hrajCatBalou() {
        int cisloVolby = 0;
        while (true) {
            cisloVolby = ZKlavesnice.readInt("\nVyber [1]ruka alebo [2]stol") - 1;
            if (cisloVolby > 0 && cisloVolby < 2) {
                if (this.getKartyNaRuke().size() == 0) {
                    System.out.println(" !!! Na ruke nema hrac karty! !!! ");
                } else if (this.getSpoecialneKarty().size() == 0) {
                    System.out.println(" !!! Na stole nema hrac karty! !!! ");
                } else {
                    break;
                }

            } else {
                break;
            }
        }

        if (cisloVolby == 0) {
            this.kartyNaRuke.remove(random(this.kartyNaRuke.size()));

        } else {
            this.spoecialneKarty.remove(random(this.spoecialneKarty.size()));
        }
    }

    public void hrajIndianov(ArrayList<Hrac> hraci) {
        ArrayList<Hrac> hraciIndiani= new ArrayList<>();
        hraciIndiani.addAll(hraci);
        hraciIndiani.remove(this);

        for(Hrac hrac : hraciIndiani) {
            boolean nasielBang = false;
            System.out.println(hrac.getMeno());
            for (Karta karta : hrac.getKartyNaRuke()) {
                if (karta instanceof Bang) {
                    System.out.println("bang");
                    hrac.getKartyNaRuke().remove(karta);
                    nasielBang = true;
                    break;
                }
            }
            if (!nasielBang && hrac.jeAktivny()) {
                hrac.minusZivot();
                System.out.println("minus zivot");
            }
        }
    }
}
