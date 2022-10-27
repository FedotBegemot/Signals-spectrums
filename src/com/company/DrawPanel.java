package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class DrawPanel extends JComponent {

    private final int SCALE_FOR_HARMONIC_X = 1025;            //всякие константы, для масштабирования графиков
    private final int SCALE_FOR_HARMONIC_Y = 54;
    private final int SCALE_FOR_MEANDER_X = 195;
    private final int SCALE_FOR_MEANDER_Y = 105;
    private final double SCALE_FOR_HARMONIC_SPECTRUM_X = 400;
    private final double SCALE_FOR_HARMONIC_SPECTRUM_Y = 6.5;
    private final double SCALE_FOR_MEANDER_SPECTRUM_X = 475;
    private final double SCALE_FOR_MEANDER_SPECTRUM_Y = 10;
    private final int SPECTRUM_CALIBRATION = 4;
    private final int MEANDER_CALIBRATION = 16;

    Harmonic_signal Harm = new Harmonic_signal();
    Meander Mean = new Meander();

    private final double [] x = Harm.get_time();
    private final double [] y1 = Harm.get_harmonic_signal(1);  //гармонический сигнал с частотой 1 Гц
    private final double [] y2 = Harm.get_harmonic_signal(2);  //гармонический с частотой 2 Гц
    private final double [] y3 = Harm.get_harmonic_signal(4);  //гармонический с частотой 4 Гц
    private final double [] y4 = Harm.get_harmonic_signal(8);  //гармонический с частотой 8 Гц

    double[] imaginary = new double[1024]; //типо массив с комплексными частями чисел, которых на деле нет, но требуются реализацией FFT
    private final double [] y1Spectrum = FFTbase.fft(Harm.get_harmonic_signal(SPECTRUM_CALIBRATION), imaginary, true);  //спектр гармонического сигнала с частотой 1
    private final double [] y2Spectrum = FFTbase.fft(Harm.get_harmonic_signal(2*SPECTRUM_CALIBRATION), imaginary, true);  //спектр гармонического сигнала с частотой 2
    private final double [] y3Spectrum = FFTbase.fft(Harm.get_harmonic_signal(4*SPECTRUM_CALIBRATION), imaginary, true);  //спектр гармонического сигнала с частотой 4
    private final double [] y4Spectrum = FFTbase.fft(Harm.get_harmonic_signal(8*SPECTRUM_CALIBRATION), imaginary, true);  //спектр гармонического сигнала с частотой 8

    private final double [] y5 = Mean.get_meander_signal(1);  //однополярный меандр с частотой 1 Гц
    private final double [] y6 = Mean.get_meander_signal(2);  //меандр с частотой 2 Гц
    private final double [] y7 = Mean.get_meander_signal(4);  //меандр с частотой 4 Гц
    private final double [] y8 = Mean.get_meander_signal(8);  //меандр с частотой 8 Гц

    private final double [] y5Spectrum = FFTbase.fft(Mean.get_meander_signal(MEANDER_CALIBRATION), imaginary, true);  //спектр меандра с частотой 1
    private final double [] y6Spectrum = FFTbase.fft(Mean.get_meander_signal(2*MEANDER_CALIBRATION), imaginary, true);  //спектр меандра с частотой 2
    private final double [] y7Spectrum = FFTbase.fft(Mean.get_meander_signal(4*MEANDER_CALIBRATION), imaginary, true);  //спектр меандра с частотой 4
    private final double [] y8Spectrum = FFTbase.fft(Mean.get_meander_signal(8*MEANDER_CALIBRATION), imaginary, true);  //спектр меандра с частотой 8

    Image image_for_harmonic;
    Image image_for_meander;
    Image image_for_harmonic_spectrum;
    Image image_for_meander_spectrum;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Line");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new DrawPanel());
        frame.pack();
        frame.setSize(new Dimension(800, 1000));
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {

        image_for_harmonic = new ImageIcon("Фон для гармонического сигнала.png").getImage();
        image_for_meander = new ImageIcon("Фон-для-меандра.png").getImage();
        image_for_harmonic_spectrum = new ImageIcon("Фон-для-гармонического спектра.png").getImage();
        image_for_meander_spectrum = new ImageIcon("Фон-для-спектра-меандра.png").getImage();

        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.BLACK);
        g2.setFont(new Font("Times New Roman",Font.BOLD,30));
        g2.drawString("Harmonic signal", 135, 100);
        g2.drawString("Harmonic Spectrum", 520, 100);
        g2.drawString("1 Hz", 35, 225);
        g2.drawString("2 Hz", 35, 425);
        g2.drawString("4 Hz", 35, 625);
        g2.drawString("8 Hz", 35, 825);

        g2.setPaint(Color.BLACK);
        g2.setFont(new Font("Times New Roman",Font.BOLD,30));
        g2.drawString("Unipolar Meander", 920, 100);
        g2.drawString("Meander Spectrum", 1320, 100);
        g2.drawString("1 Hz", 835, 225);
        g2.drawString("2 Hz", 835, 425);
        g2.drawString("4 Hz", 835, 625);
        g2.drawString("8 Hz", 835, 825);

        g2.drawImage(image_for_harmonic, 100, 150, null);
        g2.drawImage(image_for_harmonic, 100, 350, null);
        g2.drawImage(image_for_harmonic, 100, 550, null);
        g2.drawImage(image_for_harmonic, 100, 750, null);

        g2.drawImage(image_for_meander, 900, 145, null);
        g2.drawImage(image_for_meander, 900, 345, null);
        g2.drawImage(image_for_meander, 900, 545, null);
        g2.drawImage(image_for_meander, 900, 745, null);

        g2.drawImage(image_for_harmonic_spectrum, 500, 150, null);
        g2.drawImage(image_for_harmonic_spectrum, 500, 350, null);
        g2.drawImage(image_for_harmonic_spectrum, 500, 550, null);
        g2.drawImage(image_for_harmonic_spectrum, 500, 750, null);

        g2.drawImage(image_for_meander_spectrum, 1300, 150, null);
        g2.drawImage(image_for_meander_spectrum, 1300, 350, null);
        g2.drawImage(image_for_meander_spectrum, 1300, 550, null);
        g2.drawImage(image_for_meander_spectrum, 1300, 750, null);

        //ГАРМОНИЧЕСКИЕ СИГНАЛЫ
        for (int i = 96; i < 292; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]* SCALE_FOR_HARMONIC_X + 44, y1[i]* SCALE_FOR_HARMONIC_Y + 214,
                    x[i+1]* SCALE_FOR_HARMONIC_X + 44, y1[i+1]* SCALE_FOR_HARMONIC_Y +214));
        }
        for (int i = 49; i < 245; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]* SCALE_FOR_HARMONIC_X + 93, y2[i]* SCALE_FOR_HARMONIC_Y + 414,
                    x[i+1]* SCALE_FOR_HARMONIC_X + 93, y2[i+1]* SCALE_FOR_HARMONIC_Y + 414));
        }
        for (int i = 74; i < 268; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]* SCALE_FOR_HARMONIC_X + 68, y3[i]* SCALE_FOR_HARMONIC_Y + 614,
                    x[i+1]* SCALE_FOR_HARMONIC_X + 68, y3[i+1]* SCALE_FOR_HARMONIC_Y + 614));
        }
        for (int i = 36; i < 232; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]* SCALE_FOR_HARMONIC_X + 105, y4[i]* SCALE_FOR_HARMONIC_Y + 814,
                    x[i+1]* SCALE_FOR_HARMONIC_X + 105, y4[i+1]* SCALE_FOR_HARMONIC_Y + 814));
        }


        //TODO: replace all numbers with constants
        //МЕАНДРЫ
        for (int i = 0; i < 1023; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]* SCALE_FOR_MEANDER_X + 943, y5[i]* SCALE_FOR_MEANDER_Y + 160,
                    x[i+1]* SCALE_FOR_MEANDER_X +943, y5[i+1]* SCALE_FOR_MEANDER_Y + 160));
        }
        for (int i = 0; i < 1023; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]* SCALE_FOR_MEANDER_X + 943, y6[i]* SCALE_FOR_MEANDER_Y + 360,
                    x[i+1]* SCALE_FOR_MEANDER_X + 943, y6[i+1]* SCALE_FOR_MEANDER_Y + 360));
        }
        for (int i = 0; i < 1023; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]* SCALE_FOR_MEANDER_X + 943, y7[i]* SCALE_FOR_MEANDER_Y +560,
                    x[i+1]* SCALE_FOR_MEANDER_X +943, y7[i+1]* SCALE_FOR_MEANDER_Y +560));
        }
        for (int i = 0; i < 1023; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]* SCALE_FOR_MEANDER_X + 943, y8[i]* SCALE_FOR_MEANDER_Y + 760,
                    x[i+1]* SCALE_FOR_MEANDER_X + 943, y8[i+1]* SCALE_FOR_MEANDER_Y + 760));
        }

        //СПЕКТРЫ ГАРМОНИЧЕСКОГО СИГНАЛА
        for (int i = 24; i < 560; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]*SCALE_FOR_HARMONIC_SPECTRUM_X + 530, -y1Spectrum[i]* SCALE_FOR_HARMONIC_SPECTRUM_Y + 269.5,
                    x[i+1]*SCALE_FOR_HARMONIC_SPECTRUM_X + 530, -y1Spectrum[i+1]*SCALE_FOR_HARMONIC_SPECTRUM_Y + 269.5));
        }
        for (int i = 24; i < 560; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]*SCALE_FOR_HARMONIC_SPECTRUM_X + 530, -y2Spectrum[i]* SCALE_FOR_HARMONIC_SPECTRUM_Y + 469.5,
                    x[i+1]*SCALE_FOR_HARMONIC_SPECTRUM_X + 530, -y2Spectrum[i+1]* SCALE_FOR_HARMONIC_SPECTRUM_Y + 469.5));
        }
        for (int i = 24; i < 560; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]*SCALE_FOR_HARMONIC_SPECTRUM_X + 530, -y3Spectrum[i]*(SCALE_FOR_HARMONIC_SPECTRUM_Y + 2) + 669.5,
                    x[i+1]*SCALE_FOR_HARMONIC_SPECTRUM_X + 530, -y3Spectrum[i+1]*(SCALE_FOR_HARMONIC_SPECTRUM_Y + 2) + 669.5));
        }
        for (int i = 24; i < 560; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]*SCALE_FOR_HARMONIC_SPECTRUM_X + 530, -y4Spectrum[i]*(SCALE_FOR_HARMONIC_SPECTRUM_Y + 2) + 869.5,
                    x[i+1]*SCALE_FOR_HARMONIC_SPECTRUM_X + 530, -y4Spectrum[i+1]*(SCALE_FOR_HARMONIC_SPECTRUM_Y + 2) + 869.5));
        }

        //СПЕКТРЫ МЕАНДРА
        for (int i = 15; i < 470; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]*SCALE_FOR_MEANDER_SPECTRUM_X + 1330, -y5Spectrum[i]* SCALE_FOR_MEANDER_SPECTRUM_Y + 269.5,
                    x[i+1]*SCALE_FOR_MEANDER_SPECTRUM_X + 1330, -y5Spectrum[i+1]*SCALE_FOR_MEANDER_SPECTRUM_Y + 269.5));
        }
        for (int i = 15; i < 470; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]*SCALE_FOR_MEANDER_SPECTRUM_X + 1330, -y6Spectrum[i]* SCALE_FOR_MEANDER_SPECTRUM_Y + 469.5,
                    x[i+1]*SCALE_FOR_MEANDER_SPECTRUM_X + 1330, -y6Spectrum[i+1]* SCALE_FOR_MEANDER_SPECTRUM_Y + 469.5));
        }
        for (int i = 15; i < 470; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]*SCALE_FOR_MEANDER_SPECTRUM_X + 1330, -y7Spectrum[i]*SCALE_FOR_MEANDER_SPECTRUM_Y + 669.5,
                    x[i+1]*SCALE_FOR_MEANDER_SPECTRUM_X + 1330, -y7Spectrum[i+1]*SCALE_FOR_MEANDER_SPECTRUM_Y + 669.5));
        }
        for (int i = 15; i < 470; i++) {
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(x[i]*SCALE_FOR_MEANDER_SPECTRUM_X + 1330, -y8Spectrum[i]*SCALE_FOR_MEANDER_SPECTRUM_Y + 869.5,
                    x[i+1]*SCALE_FOR_MEANDER_SPECTRUM_X + 1330, -y8Spectrum[i+1]*SCALE_FOR_MEANDER_SPECTRUM_Y + 869.5));
        }
    }
}
