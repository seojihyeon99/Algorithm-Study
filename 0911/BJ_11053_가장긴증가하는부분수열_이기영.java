package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * BJ 11053 가장긴 증가하는 부분 수열
     * 
     * DP 문제입니다. for문 한번에 집착해서 고전했던 문제입니다.
     * 
     * 메모리 : 14612KB
     * 시간 : 148 ms
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];

        // 배열에 입력받고
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        int result = 0;
        // 전체 탐색하면서
        for(int i=0; i < N; i++) {
            // dp를 채워나가는데,
            for(int j=0; j < i; j++) {
                // 지금인덱스에 해당하는 값이 이전값들보다 큰 경우에는 
                if (arr[i] > arr[j]) {
                    // max 값으로 dp에 넣어줌
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            // max값을 매번 갱신해주고,
            result = Math.max(result, dp[i]);
        }

        // 출력
        System.out.println(result);
    }
}
