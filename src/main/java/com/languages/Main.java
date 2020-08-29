package com.languages;


import java.util.Scanner;

public class Main {

    public static final int MAKSYMALNA_PREDKOSC = 100;
    public static final int KROK_PRZYSPIESZENIA_ZWOLNIENIA = 20;

    public static void main(String[] args) {

        // Imitujemy jazde samochodem
        // metoda main juz jest skonczona, jest napisana tak jak nalezy musisz teraz tylko pozmieniac metody które sa nizej napisane aby samochod
        // Mogl przyspieszac maksymalnie do MAKSYMALNA_PREDKOSC, jezeli bd chcial przekroczyc ta wartosc to wyswietlisz komunikat ze nie wolno
        // Przy zwalnianiu to samo tylko do 0, ponizej 0 nie moze zejsc predkosc
        // Napisac metode która bedzie zmieniala stan radia i przy kazdej zmianie stanu wyswietlila aktualny jego stan
        // No i ogolem przy kazdej zmianie predkosc tez wyswietlic aktualna predkosc
        // A przy wylaczeniu samochodu to wyjscie z programu

        int aktualnaPredkosc = 0;
        boolean czyRadioJestWlaczone = false;
        Scanner scanner = new Scanner(System.in);

        for(;;){
            System.out.println("Co chcesz zrobic\n" +
                    "1. Przyspieszyc\n" +
                    "2. Zwolnic\n" +
                    "3. Wlaczyc radio\n" +
                    "4. Wylaczyc samochod\n");
            int decision = scanner.nextInt();
            switch(decision){
                case 1:{
                    aktualnaPredkosc = zmianaPredkosc(aktualnaPredkosc,true);
                    break;
                }
                case 2:{
                    aktualnaPredkosc = zmianaPredkosc(aktualnaPredkosc,false);
                    break;
                }
                case 3:{
                    czyRadioJestWlaczone = wlaczRadio(czyRadioJestWlaczone);
                    break;
                }
                case 4:{
                    wylaczSamochod();
                    break;
                }

            }
        }
    }

    public static int zmianaPredkosc(int aktualnaPredkosc, boolean czyPrzyspieszyc){
        return 0;
    }
    public static boolean wlaczRadio(boolean aktualnyStanRadia){
        return false;
    }
    public static void wylaczSamochod(){

    }

}

