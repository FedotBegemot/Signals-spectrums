package com.company;

public class Meander {

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

    public double[] get_meander_signal(int frequency) {
        double[] meander_sig = new double[fd];
        int iteration = 0;
        for (int i = 0; i < fd; i++) {
            if (Math.abs(time[i]) > (iteration + 1) / (double) frequency) {
                iteration = iteration + 1;
            }
            if (Math.abs(time[i]) > (iteration + 0.5) / (double) frequency) {
                meander_sig[i] = 0;
            } else {
                meander_sig[i] = 1;
            }
        }
        return meander_sig;
    }

    public void get_dots(int frequency){
        double[] y = get_meander_signal(frequency);
        double[] x = get_time();
        for(int i=0; i<fd; i++)
        {
            System.out.println(x[i]+ " " + y[i]);
        }
    }
}
