package com.company;

public class Main {

    public static void main(String[] args) {

        Harmonic_signal Harm = new Harmonic_signal();
        double[] af = new double[1024];
        double[] res = new double[8];
        res = FFTbase.fft(Harm.get_harmonic_signal(8), af, true);
        for (int i = 0; i<1024; i++){
            System.out.println(res[i]);
        }
//        Harm.get_dots(8);
//        Harm.spectrum_dots(1024);

//        Meander Mean = new Meander();
//        Mean.get_dots(8);



    }
}
