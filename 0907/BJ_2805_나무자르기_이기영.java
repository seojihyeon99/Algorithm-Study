package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * BJ 2805 나무자르기 이기영
     *
     * 처음에는 정렬한뒤, 값을 하나씩 내리면서 결과를 구하도록
     * 구현하였는데, 시간초과 판정을 받고, 이분탐색으로 풀이 하였습니다.
     *
     * 메모리 : 119360 KB
     * 시간 : 560 ms
     *
     */


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 값들 초기화
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = -1;
        int min = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            // 가장 큰값을 저장해줌
            max = Math.max(arr[i], max);
        }

        // 이분탐색 시작
        while(min <= max) {
            // 톱의 길이
            int mid = (min+max) / 2;
            // 잘라진 나무의 합
            // int로 선언하면 오답이고 long으로 선언하면 정답
            // 개열받음
            long temp = 0;
            
            // 자른 나무의 길이를 더해서
            for (int i = 0; i < N; i++) {
                if (arr[i] > mid){
                    temp += arr[i] - mid;
                }
            }
            
            // 목표보다 크면
            if (temp>= M) {
                // 왼쪽 인덱스 이동
                min = mid+1;
            } else {    // 작으면
                // 오른쪽 인덱스 이동
                max = mid-1;
            }
        }

        // 정답출력
        System.out.println(max);

    }
}
