package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                // 임도연파 
                if (st.nextToken().equals("Y")) {
                    arr[i][j] = 1;
                } else {
                    // 이다솜파
                    arr[i][j] = 0;
                }
            }
        }
        
        // dfs나 bfs 뭐시기
        // 탐색하다가 끊고 돌아오기
        // 가다가 임도연파의 수가 4를 넘으면 그방향으로 가지않음
        // 임의의 카운트가 7만큼 됐으면 합이 4를 넘지 않았다면 결과값 ++?
        // 너무 피곤해서 못하겠어요
        
    }
}
