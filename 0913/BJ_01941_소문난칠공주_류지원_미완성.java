package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_01941_소문난칠공주_류지원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int [5][5];

        for(int i=0; i<5; i++) {
            String Line=br.readLine();
            for(int j=0; j<5; j++) {
                map[i][j]=Line.charAt(j);
            }
        }







    }
}
