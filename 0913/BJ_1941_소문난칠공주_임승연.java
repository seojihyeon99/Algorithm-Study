import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 아이디어
 * 25C7 이 480000 정도
 * 자리부터 뽑은 다음 인접해 있는지 확인하고
 * 이다솜파 4명 이상인지 확인
 *
 * <인접 확인>
 * 뽑은 자리를 그래프에 1로 나타내고 나머지는 0으로 ->
 * 뽑은 어느 한 점에서 탐색 시작 -> 종료됐을 때 총 7칸을 탐색했는지 확인
 *
 * 292128 kb, 516 ms
 */
public class 소문난칠공주 {

    static char[][] graph;
    static int[][] tempGraph;
    static int[] comb;
    static boolean[] visited;
    static int cnt; // 가능한 경우의 수

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        graph=new char[5][5];
        tempGraph=new int[5][5];
        comb=new int[7];
        String line;

        for(int i=0;i<5;i++){
            line=br.readLine();
            for(int j=0;j<5;j++) graph[i][j]=line.charAt(j);
        } // 그래프 생성

        visited=new boolean[25]; // 조합에 사용
        combination(0,0);
        System.out.println(cnt);
    }

    static void combination(int curr, int start) { // 현재까지 뽑은 갯수와 시작 인덱스
        if (curr == 7) { // 7 개를 뽑았으면
            // 인접 확인
            // 임시 그래프 생성
            tempGraph=new int[5][5];
            for(int i=0;i<7;i++) {
                tempGraph[(int)comb[i]/5][comb[i]%5]=1;
            }
            if(!bfs((int)comb[0]/5,comb[0]%5)) return;
            // 연결 ㄴㄴ

            // 이다솜파 4명 이상 확인
            if(check()) cnt+=1;

            return;
        }

        for (int i = start; i < 25; i++) { // 시작 인덱스부터 끝까지 순회
            if (visited[i])
                continue;
            visited[i] = true;
            comb[curr] = i;
            combination(curr + 1, i + 1); // 다음 요소를 뽑기 위해 i + 1 전달
            visited[i] = false;
        }
    }

    static boolean bfs(int x,int y){ // 인접한지 확인
        Deque<int[]> queue=new ArrayDeque<>();
        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1};
        boolean[][] vsted=new boolean[5][5];
        int count=0;

        vsted[x][y]=true;
        queue.addLast(new int[]{x,y}); // 시작점 담기
        count+=1;

        while(!queue.isEmpty()){
            int[] pop=queue.removeFirst();
            int X=pop[0];
            int Y=pop[1];

            for(int i=0;i<4;i++){
                int xx=X+dx[i];
                int yy=Y+dy[i];

                if(xx<0 || xx>=5 || yy<0 || yy>=5) continue; // 영역 밖
                if(vsted[xx][yy]) continue; // 이미 방문
                if(tempGraph[xx][yy]==0) continue; // 갈 수 없는 점

                queue.addLast(new int[]{xx,yy});
                vsted[xx][yy]=true;
                count+=1;
            }
        }

        return count==7;
    }

    static boolean check(){ // 다솜이 4명 이상인지 확인
        int count=0;
        // comb, graph 같이 비교
        for(int i=0;i<7;i++){
            int x=(int)comb[i]/5;
            int y=comb[i]%5;

            if(graph[x][y]=='S'){
                count+=1;
            }
        }
        return count>=4;
    }
}
