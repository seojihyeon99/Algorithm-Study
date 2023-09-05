import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 모든 정점을 최소 비용으로 연결하는 최소신장트리 문제
 * 간선이 최대 100,000 개 주어질 수 있다고 하여 prim 알고리즘으로 풀이
 *
 * 아무 정점이나 시작점으로 선택하고, 그 정점에 연결된 간선들을 우선순위 큐에 추가
 * 큐에서 pop한 간선이 아직 선택되지 않은 정점을 연결한다면,
 * 그 정점에 연결된 간선들을 큐에 추가하는 방식 .. 반복하다가 (정점-1)개만큼 간선이 선택되면 종료한다
 *
 * 66076 kb, 604 ms
 * @author 임승연
 */
public class bj_1922 {
    static int N;
    static ArrayList<Edge>[] graph;
    static boolean[] selected; // 선택된 정점인지 나타내는 배열
    static int sum; // 최소 비용 저장

    static class Edge implements Comparable<Edge>{ // 간선 정의
        int from,to,weight;

        public Edge(int from,int to,int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Edge o) { // 간선 가중치 오름차순
            return Integer.compare(this.weight,o.weight);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        int M=Integer.parseInt(br.readLine());
        String[] temp;

        graph=new ArrayList[N];
        selected=new boolean[N];
        for(int i=0;i<N;i++) graph[i]=new ArrayList<>();
        sum=0; // 초기화

        for(int i=0;i<M;i++){ // 그래프 생성
            temp=br.readLine().split(" ");
            int a=Integer.parseInt(temp[0])-1;
            int b=Integer.parseInt(temp[1])-1;
            int c=Integer.parseInt(temp[2]);
            graph[a].add(new Edge(a,b,c));
            graph[b].add(new Edge(b,a,c));
        }
        Prim();
        System.out.println(sum);
    }

    static void Prim(){

        PriorityQueue<Edge> pq=new PriorityQueue<>(); // 간선 저장할 pq

        int edgeCnt=0; // edgeCnt=N-1 이 될 때까지 수행

        // 0번 정점 선택
        selected[0]=true;
        for(int i=0;i<graph[0].size();i++) pq.add(graph[0].get(i)); // 0번 정점에 연결된 간선들 큐에 add

        while(edgeCnt<N-1){
            Edge pop=pq.poll();
            if(selected[pop.to]) continue; // 이미 선택된 정점들을 연결하는 간선이면 다른 간선 선택
            selected[pop.to]=true;
            sum+=pop.weight;
            edgeCnt++;

            for(int i=0;i<graph[pop.to].size();i++) { // 새로 선택된 정점에 연결된 간선들 pq 에 저장
                Edge tmp=graph[pop.to].get(i);
                if(selected[tmp.to]) continue;  // 이미 선택된 정점들을 잇는 간선이면 패스
                pq.add(tmp);
            }
        }
    }
}
