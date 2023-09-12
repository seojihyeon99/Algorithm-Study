package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * BJ 1780 종이의 개수
     *
     * 분할정복으로 풀이하였습니다.
     * 예전에 배웠던 분할정복이랑 비슷했지만,
     * sum값을 조금 다르게 처리하는부분과 9등분된다는 부분이 조금 달랐습니다.
     *
     * 메모리 : 315,896 KB
     * 시간 : 1292ms
     *
     */

    static int N, zero, minus, plus;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //초기화
        N = Integer.parseInt(br.readLine());
        // 각 영역 카운트
        zero = 0;
        minus = 0;
        plus = 0;
        arr = new int[N][N];

        // 값을 넣어주고
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 함수실행후, 출력
        solve(0, 0, N);
        System.out.println(minus);
        System.out.println(zero);
        System.out.println(plus);

    }

    public static void solve(int x, int y, int size) {
        // 각 자리의 합
        int sum0 = 0;   // 0
        int sum1 = 0;   // 1
        int sum_1 = 0;  // -1
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] == 1) {
                    sum1 ++;
                } else if (arr[i][j] == 0) {
                    sum0 ++;
                } else if (arr[i][j] == -1) {
                    sum_1 ++;
                }
            }
        }

        // 각경우에 해당하면, 그 지역이 뭐가됐건 통일됐다는 뜻
        if (sum0 == size * size) {
            zero++;
        } else if (sum1 == size * size) {
            plus++;
        } else if (sum_1 == size * size) {
            minus++;
        } else {
            // 지역에 불순물이 있으면 더 쪼개서 탐색
            // 9등분이므로 3만큼 쪼갬
            int newSize = size / 3;
            
            // 9등분한 값과 범위를 넣어서 재귀적으로 풀이
            solve(x, y, newSize);
            solve(x, y + newSize, newSize);
            solve(x, y + 2 * newSize, newSize);
            solve(x + newSize, y, newSize);
            solve(x + newSize, y + newSize, newSize);
            solve(x + newSize, y + 2 * newSize, newSize);
            solve(x + 2 * newSize, y, newSize);
            solve(x + 2 * newSize, y + newSize, newSize);
            solve(x + 2 * newSize, y + 2 * newSize, newSize);
        }
    }

}
