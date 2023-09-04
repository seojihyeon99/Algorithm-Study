package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /**
     * BJ 14567 선수과목
     *
     * 위상정렬로 풀이한 문제입니다.
     * 위상정렬을 처음 공부한다면 좋은 예제라고 생각하며,
     * 모르겠다면 통째로 외우는것도 좋을것같습니다.
     * 
     * 메모리 : 126256 kb
     * 시간 : 656 ms
     *
     */

    static int N, M;
    static int[] degree;
    static Node[] node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 각 노드의 연결 차수를 저장할 배열
        degree = new int[N+1];
        // 노드를 저장할 노드 배열
        node = new Node[N+1];

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // 입력받은 값을 배열에 넣어주고
            node[from] = new Node(to, node[from]);
            // 차수를 늘려줌
            degree[to]++;
        }
        int[] answer = topology();

        for (int i = 1; i <= N; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    private static int[] topology() {
        int[] arr = new int[N+1];
        Queue<Integer[]> q = new LinkedList<>();

        // 우선 차수가 0이라면 가장 먼저 제거해야 할 대상
        for (int i = 1; i < degree.length; i++) {
            if (degree[i] == 0) {
                // 해당 index와 초기값 1을 넣어줌
                q.offer(new Integer[] {i, 1});
            }
        }

        while(!q.isEmpty()) {
            // 꺼내면서
            Integer[] now = q.poll();
            // 해당 배열 인덱스에 정답을 넣어줌
            arr[now[0]] = now[1];

            // 해당하는 노드 하나를 가지고 비교할건데
            for(Node temp=node[now[0]]; temp != null; temp = temp.next) {
                // 만약 남은 차수가 1이라면 제거 대상이므로
                if (degree[temp.now] == 1) {
                    // Queue에 추가해주면서 counting 해주고
                    q.offer(new Integer[]{temp.now, now[1] + 1});
                }
                // 해당 차수를 하나 감소시켜줌
                degree[temp.now]--;
            }
        }
        // 리턴
        return arr;
    }



    static class Node {
        int now;
        Node next;

        public Node(int now, Node next) {
            super();
            this.now = now;
            this.next = next;
        }
    }
}
