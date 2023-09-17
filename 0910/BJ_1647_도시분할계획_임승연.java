import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * 328172 kb	1304 ms
 *
 * kruskal 알고리즘 사용하여 일단 mst 를 만들고, 가장 마지막에 추가된 간선의 가중치를 더해주지 않으면 된다 
 * ==>  가장 큰 값일 것이므로 
 *
 */
public class MST {

    static class Edge implements Comparable<Edge>{
        int from,to,weight;

        public Edge(int from,int to,int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }

    static PriorityQueue<Edge> pq;
    static long sum; // mst 비용 
    static int N; // 정점 수
    static int M; // 간선 수

    static int[] parent; // union find

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        int s,e,w;
        sum=0; // mst 최종 비용 저장
        pq=new PriorityQueue<>();
        parent=new int[N];

        for(int i=0;i<N;i++) parent[i]=i; // 자기 자신으로 초기화

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            s=Integer.parseInt(st.nextToken())-1;
            e=Integer.parseInt(st.nextToken())-1;
            w=Integer.parseInt(st.nextToken());
            pq.add(new Edge(s,e,w));
        } // 입력 완료

        kruskal();
        System.out.println(sum);
    }

    static void kruskal(){
        int edgeCnt=0;
        Edge pop;

        for(int i=0;i<M;i++){
            pop=pq.poll();
            if(find(pop.to)==find(pop.from)) continue;

            union(pop.to,pop.from);
            edgeCnt+=1;
            if(edgeCnt==N-1) break; // 간선을 다 뽑자마자 종료하면 마지막 선택된 간선은 포함되지 않으므로 그대로 출력하면 됨
            sum+=pop.weight;
        }
    }

    static int find(int x){
        if(x==parent[x]) return x;
        return parent[x]=find(parent[x]);
    } // 루트 노드 찾기

    static void union(int x,int y){
        x=find(x);
        y=find(y);

        if(x>y) parent[y]=x;
        else parent[x]=y; // 작은 수 -> 큰 수로 합치기
    }

}
