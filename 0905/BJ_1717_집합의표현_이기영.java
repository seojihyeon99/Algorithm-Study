package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * BJ 1717 집합의 표현
     *
     * union - find 기법으로 풀어봤습니다.
     *
     * 메모리 : 58352 KB
     * 시간 : 1456ms
     *
     */

    static int N, M;
    static int[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 숫자를 저장할 배열. 1부터시작하므로 +1
        number = new int[N+1];

        // 초기값을 넣어주고
        for (int i = 0; i < number.length; i++) {
            number[i] = i;
        }

        for (int i = 0; i < M; i++) {
            // 값을입력받는데?
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 첫 값이 0이면 union
            if(num == 0) {
                union(a, b);
            } else {    // 아니면 비교해봄
                if(find(a) == find(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }

    }

    // 각 값을 find로 연결된 값으로 바꿔주고
    public static void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        // 같으면 바꿀게 없음
        if (a == b) return;

        // 다르다면 한쪽값을 나머지값으로 같게 만들어줌
        // number[a] = b; 이어도 상관없을거같음..
        number[b] = a;
    }

    // find
    public static int find(int x) {
        // 입력받은 값이 그대로라면 그냥 리턴
        if(x == number[x]) return x;

        // 아니면 연결된 값을 갱신해서 리턴해줌
        return number[x] = find(number[x]);
    }
}



