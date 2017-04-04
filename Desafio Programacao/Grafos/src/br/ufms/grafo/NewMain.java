/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.grafo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class NewMain {

    static int MN = 2002;

    static int cost[] = new int[MN];
    static int price[] = new int[MN];

    static int N, T, M, P;

    static int dp[][] = new int[MN][MN];

    static int go(int i, int age) {
        if (i == N) {
            return dp[i][age] = 0;
        }

        if (dp[i][age] != -1) {
            return dp[i][age];
        }
        if (age == M) {
            return dp[i][age] = go(i + 1, 1) + P - price[age - 1] + cost[0];
        }
        int op1 = go(i + 1, 1) + P - price[age - 1] + cost[0];
        int op2 = go(i + 1, age + 1) + cost[age];
        return dp[i][age] = menor(op1, op2);
    }

    static int menor(int a, int b) {
        if (a > b) {
            return b;
        } else if (b > a) {
            return a;
        } else if (a == b) {
            return a;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader leia = new BufferedReader(new InputStreamReader(System.in));
        String entrada[] = leia.readLine().split(" ");
        N = Integer.parseInt(entrada[0]);
        T = Integer.parseInt(entrada[1]);
        M = Integer.parseInt(entrada[2]);
        P = Integer.parseInt(entrada[3]);
        while (true) {
            for (int i = 0; i < M; ++i) {
                cost[i] = Integer.parseInt(entrada[1]);
            }
            for (int i = 0; i < M; ++i) {
                price[i] = Integer.parseInt(entrada[1]);
            }
//            memset(dp, -1, sizeof dp);
            System.out.println(go(0, T));
            int i = 0, a = T;

            List ans = new ArrayList();
            for (int j = 0; j < MN; ++j) {
                dp[N][j] = 0;
            }
            while (i < N) {
                if (a == M) {
                    ans.add(i + 1);
                    a = 1;
                } else {
                    int op1 = dp[i + 1][1] + P - price[a - 1] + cost[0];
                    int op2 = dp[i + 1][a + 1] + cost[a];
                    if (op1 <= op2) {
                        ans.add(i + 1);
                        a = 1;
                    } else {
                        a++;
                    }
                }
                i++;
            }
            if (ans.size() == 0) {
                System.out.println("0");;
                continue;
            }
            for (int k = 0; k < ans.size(); ++k) {
                if (k > 0) {
                    System.out.println("' '");
                }
                System.out.println(ans.get(k));
            }
            System.out.println("");
        }
    }
}
