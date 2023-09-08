package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * BJ 18352 특정 거리의 도시 찾기
     *
     * 최단거리를 찾는 다익스트라 문제였습니다.
     * BFS로 풀었죠.
     *
     * 메모리 : 279172 KB
     * 시간 : 1176ms
     *
     */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // 인접리스트 초기화
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        // 값을 넣어줌
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
        }

        // 각 노드의 거리
        int[] distance = new int[N + 1];
        // -1로 초기화
        Arrays.fill(distance, -1);
        // 처음 시작 정점은 0
        distance[X] = 0;

        // 시작정점을 q에 넣어줌
        Queue<Integer> q = new LinkedList<>();
        q.offer(X);

        while (!q.isEmpty()) {
            int now = q.poll();

            // 꺼낸 now에 연결된 노드들 확인
            for(int next: list.get(now)) {
                // 방문안했다면
                if (distance[next] == -1) {
                    // 거리 갱신하고 q에 넣어줌
                    distance[next] = distance[now] + 1;
                    q.offer(next);
                }
            }
        }

        // 한번이라도 출력했는지 확인하기 위해
        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            // K와 같으면
            if (distance[i]==K) {
                // 출력 및 플래그 true
                System.out.println(i);
                flag = true;
            }
        }

        if(!flag) {
            System.out.println(-1);
        }

    }
}
