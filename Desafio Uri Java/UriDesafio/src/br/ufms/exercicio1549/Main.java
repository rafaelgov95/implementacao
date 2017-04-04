/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.exercicio1549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author rafael
 */
public class Main {

    static double R, r, H;
    static double pi = Math.PI;
    static double eps = 1e-9;

    static double volume(double h) {
        Double B = r + ((R - r) / H) * h;
        return ((pi * h) / 3.0) * (B * B + B * r + r * r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader leia = new BufferedReader(new InputStreamReader(System.in));
        int tc;
        tc = Integer.parseInt(leia.readLine());
        String entrada[];
        while (tc > 0) {
            int n;
            double L;
            entrada = leia.readLine().split(" ");
            n = Integer.parseInt(entrada[0]);
            L = Double.parseDouble(entrada[1]);
            entrada = leia.readLine().split(" ");
            r = Double.parseDouble(entrada[0]);
            R = Double.parseDouble(entrada[1]);
            H = Double.parseDouble(entrada[2]);
            L /= n;

            double lo = eps, hi = H;
            int iter = 1000;
            while (lo - eps < hi && iter > 0) {
                double mid = (lo + hi) * 0.5;
                double v_mid = volume(mid);
                if (Math.abs(v_mid - L) < eps) {
                    break;
                }

                if (v_mid < L) {
                    lo = mid;
                } else {
                    hi = mid;
                }
                iter--;
            }
            System.out.printf("%.2f\n", lo);
            tc--;
        }
    }

}
