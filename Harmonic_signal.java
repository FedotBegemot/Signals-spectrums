package com.company;

import java.lang.Math;

public class Harmonic_signal {

    private final int A = 1;
    private final int C = 300;
    private final int  fd = 1024;
    private final double [] time = get_time();

    public double[] get_time(){
        double[] t = new double[fd];
        double temp = 0;
        for(int i=0; i < fd; i++)
        {
        t[i] = temp;
        temp += 1 / (double) fd;
        }
        return t;
    }

    public double[] get_harmonic_signal(int frequency){
        double[] harmonic_sig = new double[fd];
        double[] temp = new double[fd];
        for (int i = 0; i < fd; i++) {
            temp[i] = Math.toRadians(2 * Math.PI * C * frequency * time[i]);
            harmonic_sig[i] = A * Math.cos(temp[i]);
        }
            return harmonic_sig;
    }

    public void get_dots(int frequency){
        double[] y = get_harmonic_signal(frequency);
        double[] x = get_time();
        for(int i=0; i<fd; i++)
        {
            System.out.println(x[i]+ " " + y[i]);
        }
    }
}
