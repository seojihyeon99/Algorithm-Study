package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 류지원
 * 메모리 : 340880KB, 시간 : 1416ms
 * 풀이방법 : 분할 정복으로 재귀하면서 풀이하였다.
 */

public class BJ_01780_종이의개수_류지원 {
    static int color0, color1, colorM1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer ST;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for(int i=0; i<N; i++) {    // 배열 입력받기
            ST=new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) map[i][j]=Integer.parseInt(ST.nextToken());
        }
        dfs(map, N);        // 분할정복 재귀 시작.
        System.out.println(colorM1 + "\n" + color0 + "\n" + color1);    // 출력
    }

    public static void dfs(int[][] map, int cnt) {  // 분할정복 재귀 메서드
        if(valid(map, cnt)) {   // 기저조건. map의 원소가 모두 같다면 같은 종이이므로 해당 종이의 개수를 1 추가하고 메서드반환.
            switch(map[0][0]) { // 종이의 종류를 파악한후
                case -1 :   {colorM1++; break;} // 종이가 -1이라면 colorM1을 1 증가
                case 0 :    {color0++;  break;} // 종이가 0이라면 color0을 1 증가
                case 1 :    {color1++;  break;} // 종이가 1이라면 color1을 1 증가
            }
            return;
        }
        // 기저조건에 해당되지 않을때, 아래 코드를 실행하여 분할하여 재귀
        int NextCnt=cnt/3;  // map의 크기가 1/3으로 줄어들기 때문에 cnt에 3을 나눔

        for(int i=0; i<3; i++) {    //  맵을 분할하고 재귀를 하귀위한 반복문
            for(int j=0; j<3; j++) {
                int[][] divMap = new int[NextCnt][NextCnt];   // 분할할 종이를 저장하기 위한 배열
                for(int k=0; k<NextCnt; k++) divMap[k]=Arrays.copyOfRange(map[i*NextCnt+k], j*NextCnt, (j+1)*NextCnt);
                dfs(divMap, NextCnt);   // 분할한 맵을 넣어서 재귀
            }
        }
    }

    // 기저조건에 해당하는지 확인하는 용도의 메서드.
    // 종이의 숫자를 비교하기 위한 메서드. 종이의 숫자가 모두 같다면 true를, 하나라도 다르다면 false를 반환함.
    public static boolean valid(int[][] map, int cnt) {
        int init=map[0][0];
        for(int i=0; i<cnt; i++)
            for(int j=0; j<cnt; j++)
                if(init != map[i][j]) return false;
        return true;
    }
}
