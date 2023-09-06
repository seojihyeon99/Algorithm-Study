package com.example.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /**
     * BJ 1922 네트워크 연결
     *
     * 최소신장트리를 이용하는 문제입니다.
     * 개념을 그대로 적용한 문제이므로, 알고가면 좋을것 같습니다.
     *
     * 메모리 : 50272 kb
     * 시간 : 472 ms
     *
     */

    static int N, M;
    static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Node> q = new PriorityQueue<>();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        // 몇개 비교했는지
        int count = 0;
        // 결과값
        int result = 0;
        // 방문배열
        boolean[] visited = new boolean[N];
        // 각 노드의 최소값을 저장해둘 배열
        int[] minvalue = new int[N];
        // 가장 작은 큰값으로 초기화 해줘야 비교가 가능
        Arrays.fill(minvalue, Integer.MAX_VALUE);

        list = new ArrayList[N];
        // 인접리스트로 초기화
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());

            // 양방향 그래프이므로 이렇게 양방향으로 값을 넣어줌
            list[from].add(new Node(to, weight));
            list[to].add(new Node(from, weight));
        }

        // 가장 처음 노드에 연결된 노드를 전부 q에 담아줌
        for (Node node : list[0]) {
            // 해당 노드의 가중치로 최소값 갱신해줌
            minvalue[node.vertex] = node.weight;
            q.add(node);
        }

        // 처음 노드는 방문처리 한 상태로 시작
        visited[0] = true;


        while (count < N-1) {
            Node now = q.poll();
            // 방문했으면 continue
            if(visited[now.vertex]) continue;

            // 방문처리해주고
            visited[now.vertex] = true;
            // 값을 더해줌
            result += now.weight;
            // 비교했으니 ++
            count++;

            // 연결된 노드전부 확인
            for (Node node : list[now.vertex]) {
                // 방문했으면 continue
                if (visited[node.vertex]) continue;

                // 현재 책정되어있는 최소값보다 가중치가 낮을경우에
                if (minvalue[node.vertex] > node.weight) {
                    // 최소값으로 갱신해주고
                    minvalue[node.vertex] = node.weight;
                    // q에 넣어줌
                    q.add(node);

                    // 참고로 우선순위 Queue에 넣었기때문에, 값이 정렬되어 있으므로
                    // 향후에 더 방문했던 놈들보다 더 작은 가중치를 만날일이
                    // 없을것이라고 여겨짐
                }

            }
        }

        // 결과
        System.out.println(result);
    }

    // 우선순위 Queue에 넣기 때문에, 무엇을 우선시해서 정렬할건지 정해야하므로
    // Comparable을 가져와서 Override 해줌
    static class Node implements Comparable<Node>{
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            // 가중치로 비교할거임.
            return Integer.compare(this.weight, node.weight);
        }
    }
}



