import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 모든 정점을 시작점으로 둔 다익스트라 반복
 * 어떤 정점의 거리가 이미 m (수색범위) 을 넘어서면, 큐에 담을 필요 x 
 * 아직 헷갈려서 더 풀어봐야할듯!
 * 
 * 11736 kb, 88 ms	
 */
public class Main {

    static PriorityQueue<Edge> pq; // 간선 저장용
    static List<Edge>[] graph;
    static int n,m,r;
    static int[] distance; // 최단거리 저장
    static int Max=0; // 최대 아이템 개수 저장
    static int[] items; // 지역별 아이템 수

    static class Edge implements Comparable<Edge> {
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

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());

        // 아이템 수
        st=new StringTokenizer(br.readLine()," ");
        items=new int[n];
        for(int i=0;i<n;i++){
            items[i]=Integer.parseInt(st.nextToken());
        } // 아이템 수 저장

        int s,e,w;

        // 그래프 생성
        graph=new ArrayList[n];
        for(int i=0;i<n;i++) graph[i]=new ArrayList<>();

        for(int i=0;i<r;i++){
            st=new StringTokenizer(br.readLine()," ");
            s=Integer.parseInt(st.nextToken())-1;
            e=Integer.parseInt(st.nextToken())-1;
            w=Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e,w));
            graph[e].add(new Edge(s,w)); // 양방향 처리
        } // 그래프 생성 완료

        // 모든 정점을 시작점으로 두며 다익스트라 반복
        // 매 반복시, distance 값 초기화 , pq 초기화
        distance=new int[n];
        pq=new PriorityQueue<>();
        visited=new boolean[n];
        for(int i=0;i<n;i++){
            Arrays.fill(distance,Integer.MAX_VALUE);
            pq.clear();
            Arrays.fill(visited,false);
            dijkstra(i);
        }
        System.out.println(Max);

    }

    /**
     * 다익스트라 완료 후, Max 값 비교하여 저장하기
     * @param start : 시작점
     */
    static void dijkstra(int start){

        int tempTo,tempWeight;
        distance[start]=0; // 시작점 초기화

        for(int i=0;i<graph[start].size();i++){
            distance[graph[start].get(i).to]=graph[start].get(i).weight;
            pq.add(new Edge(graph[start].get(i).to,graph[start].get(i).weight));
        } // 시작점에 인접한 점들 큐에 add

        while(!pq.isEmpty()){
            Edge pop=pq.poll();

            // 지금 점에서 갈 수 있는 점들 탐색
            for(int i=0;i<graph[pop.to].size();i++){

                tempTo=graph[pop.to].get(i).to;
                tempWeight=graph[pop.to].get(i).weight;

                // 만약 현재 탐색 점을 거쳐서 가는 거리가 더 작다면 -> 갱신하고 큐에 넣기
                if(distance[tempTo]> distance[pop.to]+tempWeight){
                    distance[tempTo]=distance[pop.to]+tempWeight;
                    if(distance[tempTo]>=m) continue;
                    pq.add(new Edge(tempTo,distance[tempTo]));
                }
            }
        }

        // 아이템 수 count
        int cnt=0;
        for(int i=0;i<n;i++){
            if(distance[i]<=m) cnt+=items[i];
        }
        if(cnt>Max) Max=cnt;

    }

}
