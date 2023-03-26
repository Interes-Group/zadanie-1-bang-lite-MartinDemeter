package sk.stuba.fei.uim.oop.hrac;

import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.karty.hnedeKarty.Bang;
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

    private boolean voVazenie;

    public Hrac(Plocha plocha, String meno) {
        this.kartyNaRuke = new ArrayList<>();
        this.spoecialneKarty = new ArrayList<>();
        this.plocha = plocha;
        this.meno = meno;
        this.zivoty = 4;
        this.voVazenie = false;
    }

    public String getMeno() {
        return meno;
    }

    public int getZivoty() {
        return zivoty;
    }

    public void setVoVazenie() {
        this.voVazenie = false;
    }

    public boolean isVoVazenie() {
        return voVazenie;
    }

    public void setKarty(ArrayList<Karta> karty) {
        this.kartyNaRuke = karty;
    }

    public ArrayList<Karta> zoberKartyHraca() {
        ArrayList<Karta> zobrateKarty = new ArrayList<>(this.kartyNaRuke);
        this.kartyNaRuke.clear();
        return zobrateKarty;
    }

    public void zoberKartu() {
        this.kartyNaRuke.add(this.plocha.dajKartu());
    }

    public void odhodKarty() {
        while (this.kartyNaRuke.size() > this.zivoty) {
            int nahodne = nahodne(this.kartyNaRuke.size());
            this.plocha.pridajKartuDoOdhadzovaciehoBalika(this.kartyNaRuke.get(nahodne));
            this.kartyNaRuke.remove(nahodne);
        }
    }

    private int nahodne(int velkost) {
        return (int) ((Math.random() * velkost));
    }

    public ArrayList<Karta> getKartyNaRuke() {
        return kartyNaRuke;
    }

    public ArrayList<Karta> getSpecialneKarty() {
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

    private void odhodKartu(Karta karta) {
        this.plocha.pridajKartuDoOdhadzovaciehoBalika(karta);
    }
    public void vypisKarty() {
        for (int i = 0; i < this.kartyNaRuke.size(); i++) {
            System.out.print("[" + (i + 1) + "] " + this.kartyNaRuke.get(i).getClass().getSimpleName() + "  ");
        }
    }

    public void vypisKartyNaStole() {
        for (Karta karta : this.spoecialneKarty) {
            System.out.print(karta.getClass().getSimpleName() + "  ");
        }
    }

    public void zahrajKartu(ArrayList<Hrac> hraci, int cisloKarty) {
        this.kartyNaRuke.get(cisloKarty).hraj(this);
        this.hrajIndianov(hraci);
    }

    public void zahrajKartu(Hrac cielovyHrac, int cisloKarty) {
        if (this.kartyNaRuke.get(cisloKarty).jeKartahratelna(this)) {
            this.kartyNaRuke.get(cisloKarty).hraj(cielovyHrac);
        }
    }

    private void vypisHracov(ArrayList<Hrac> hraciNaVyber) {
        for (int i = 0; i < hraciNaVyber.size(); i++) {
            System.out.print("[" + (i + 1) + "] " + hraciNaVyber.get(i).getMeno() + "  ");
        }
    }

    public Hrac vyberHraca(ArrayList<Hrac> hraci, int karta) {
        int cisloHraca = 0;
        ArrayList<Hrac> hraciNaVyber = new ArrayList<>();

        for (Hrac hrac : hraci) {
            if (hrac.jeAktivny()) {
                hraciNaVyber.add(hrac);
            }
        }
        hraciNaVyber.remove(this);
        vypisHracov(hraciNaVyber);
        while (true) {
            cisloHraca = ZKlavesnice.readInt("\n*** Zadaj číslo hráča na ktorého chceš použiť kartu: ***") - 1;
            if ((cisloHraca < 0 || cisloHraca + 1 > hraciNaVyber.size()) ) {
                    System.out.println(" !!! Zadal si neplatné číslo! !!! ");

            } else {
                break;
            }
        }
        return hraciNaVyber.get(cisloHraca);
    }

    public int vyberKartu() {
        int cisloKarty = 0;
        while (true) {
            cisloKarty = ZKlavesnice.readInt("\n*** Zadaj číslo ktoré chceš zahrať alebo 0 pre koniec kola: ***") - 1;
            if (cisloKarty < -1 || cisloKarty > this.getKartyNaRuke().size() - 1) {
                System.out.println(" !!! Zadal si zlé číslo karty alebo sa daná karta nedá zahrať, skús to znova! !!! ");
            } else {
                break;
            }
        }
        return cisloKarty;
    }

    public void hrajBang() {
        int nahodne = nahodne(4);
        for (Karta karta : this.spoecialneKarty) {
            if (karta instanceof Barrel) {
                if (nahodne == 1) {
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
            cisloVolby = ZKlavesnice.readInt("\nVyber [1]ruka alebo [2]stôl") - 1;
            if (cisloVolby >= 0 && cisloVolby < 2) {
                if (this.getKartyNaRuke().size() == 0 && cisloVolby == 0) {
                    System.out.println(" !!! Na ruke nemá hráč karty! !!! ");
                } else if (this.getSpecialneKarty().size() == 0 && cisloVolby == 1) {
                    System.out.println(" !!! Na stole nemá hráč karty! !!! ");
                } else {
                    break;
                }
            } else {
                System.out.println("Neplatný rozsah");
            }
        }

        if (cisloVolby == 0) {
            this.kartyNaRuke.remove(nahodne(this.kartyNaRuke.size()));
        } else {
            this.spoecialneKarty.remove(nahodne(this.spoecialneKarty.size()));
        }
    }

    public void hrajIndianov(ArrayList<Hrac> hraci) {
        ArrayList<Hrac> hraciIndiani = new ArrayList<>(hraci);
        hraciIndiani.remove(this);

        for (Hrac hrac : hraciIndiani) {
            boolean nasielBang = false;
            for (Karta karta : hrac.getKartyNaRuke()) {
                if (karta instanceof Bang) {
                    hrac.getKartyNaRuke().remove(karta);
                    nasielBang = true;
                    break;
                }
            }
            if (!nasielBang && hrac.jeAktivny()) {
                hrac.minusZivot();
            }
        }
    }

    public void hrajDynamit(ArrayList<Hrac> hraci, Karta karta, int predosliHrac) {
        int nahodne = nahodne(8);
        if (nahodne == 1) {
            this.minusZivot();
            this.minusZivot();
            this.minusZivot();
            this.odhodKartu(karta);
            if (zivoty < 0) {
                zivoty = 0;
            }
        } else {
            hraci.get(predosliHrac).spoecialneKarty.add(karta);
        }
        this.spoecialneKarty.remove(karta);
    }

    public void hrajVazenie(Karta karta) {
        int nahodne = nahodne(4);
        if (nahodne == 1) {
            this.voVazenie = false;
        } else {
            this.voVazenie = true;
        }
        this.spoecialneKarty.remove(karta);
    }
}
