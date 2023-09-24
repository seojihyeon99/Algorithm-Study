import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 플로이드 워셜
 * 26180 kb, 336 ms
 */
public class Main {

    static int[][] graph;
    static int[][] dist;
    static int n,m;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        StringBuilder sb=new StringBuilder();
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        graph=new int[n][n];
        dist=new int[n][n];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<n;j++){
                graph[i][j]=Integer.parseInt(st.nextToken());
            }
        } // 그래프 생성


        for(int k=0;k<n;k++){ // 거쳐가는 정점
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    graph[i][j]=Math.min(graph[i][k]+graph[k][j],graph[i][j]);
                }
            }
        }

        int s,e,w;
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine()," ");
            s=Integer.parseInt(st.nextToken())-1;
            e=Integer.parseInt(st.nextToken())-1;
            w=Integer.parseInt(st.nextToken());
            if(graph[s][e]<=w){
                sb.append("Enjoy other party\n");
            }
            else{
                sb.append("Stay here\n");
            }
        }
        System.out.println(sb.toString());
    }

}
