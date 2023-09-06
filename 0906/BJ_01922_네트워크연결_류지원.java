package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 류지원
 * 메모리 : 45788 KB, 시간 472ms;
 * 풀이방법;
 * 크루스칼 알고리즘으로 풀이하였다;
 * 간선정보를 담은 클래스를 생성하여
 * 가중치를 기준으로 오름차순으로 정렬한 후,
 * 가장 가장치가 낮은것부터 추가하면서, 사이클이 발생하지 않게 모든 노드가 선택될떄까지 반복하였다.
 */

public class BJ_01922_네트워크연결_류지원 {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static Edge[] edgeList;
    static int N,M;
    static int[] parents;
    static void make() {
        parents = new int[N+1];
        for(int i=1; i<N+1; i++) parents[i]=i;
    }
    static int find(int a) {
        if(parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }
    static boolean union(int a, int b) {
        int aRoot=find(a);
        int bRoot=find(b);
        if(aRoot==bRoot) return false;
        parents[bRoot]=aRoot;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer ST;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edgeList = new Edge[M];
        for(int i=0; i<M; i++) {
            ST=new StringTokenizer(br.readLine());
            int from=Integer.parseInt(ST.nextToken());
            int to=Integer.parseInt(ST.nextToken());
            int weight=Integer.parseInt(ST.nextToken());
            edgeList[i]=new Edge(from, to, weight);
        }

        Arrays.sort(edgeList);

        make();

        int result=0;
        int count=0;
        for(Edge edge : edgeList) {
            if(union(edge.from, edge.to)) {
                result+=edge.weight;
                if(++count==N-1) break;
            }
        }
        System.out.println(result);
    }
}
