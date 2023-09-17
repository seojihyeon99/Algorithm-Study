import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * prim 알고리즘 사용
 * 213824 kb, 1672 ms 
 * 
 * long 타입 ,, ..
 */
public class MST {

    static class Edge implements Comparable<Edge>{
        int to,weight;

        public Edge(int to,int weight){
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }


    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static long sum;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        graph=new ArrayList[N];
        visited=new boolean[N];
        int s,e,w;
        long total=0;
        sum=0; // mst 최종 비용 저장

        for(int i=0;i<N;i++) graph[i]=new ArrayList<>();

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            s=Integer.parseInt(st.nextToken())-1;
            e=Integer.parseInt(st.nextToken())-1;
            w=Integer.parseInt(st.nextToken());
            total+=w;
            graph[s].add(new Edge(e,w));
            graph[e].add(new Edge(s,w)); // 양방향
        } // 그래프 생성 완료

        if(prim()) System.out.println(total-sum);
        else System.out.println(-1);
    }

    static boolean prim(){

        PriorityQueue<Edge> pq=new PriorityQueue<>();
        visited[0]=true; // 0번 정점 방문 처리
        int edgeCnt=0; // 간선 개수 -> N-1 이 될 때까지
        Edge pop;

        for(int i=0;i<graph[0].size();i++){
            pq.add(graph[0].get(i));
        } // 0번 정점에 연결된 간선들 pq에 저장

        while(!pq.isEmpty()){
            pop=pq.poll();

            if(visited[pop.to]) continue; // 이미 포함된 정점들을 잇는 간선이면 pass

            edgeCnt+=1;
            sum+=pop.weight;
            visited[pop.to]=true;
            if(edgeCnt==N-1) break;

            for(int i=0;i<graph[pop.to].size();i++){
                if(!visited[graph[pop.to].get(i).to]){
                    pq.add(graph[pop.to].get(i));
                }
            }
        }

        return edgeCnt==N-1;
    }

}
