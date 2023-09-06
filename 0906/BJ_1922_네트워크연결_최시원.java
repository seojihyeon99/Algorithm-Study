package algo0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 최소신장트리를 구하는 문제이다. 인접행렬에 프림 알고리즘을 사용하였다
 * 메모리 46304kb 시간 424ms
 * @author 최시원
 *
 */
public class BJ_1922_네트워크연결_최시원 {

	static int N, M, adjMatrix[][];
	public static void main(String[] args) throws IOException {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adjMatrix = new int[N+1][N+1];

		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from, to, weight;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			adjMatrix[from][to] = weight;
			adjMatrix[to][from] = weight;
		}
		//입력끝
		
		//방문여부체크
		boolean[] visited = new boolean[N+1];
		int[] minEdge = new int[N+1];//최소값 간선
		Arrays.fill(minEdge, Integer.MAX_VALUE); // 최소값 갱신을 위함
		minEdge[0] = 0; 
		minEdge[1] = 0;
		// 임의의 0정점을 트리 구성의 시작으로 하기 위해 세팅
		int result = 0;
		int min =0, minVertex;
		
//		for(int i=1; i<=N; i++) {
//			for(int j=1; j<=N; j++) {
//				System.out.print(adjMatrix[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		for(int c=1; c<=N; c++) {
			minVertex = -1;
			min = Integer.MAX_VALUE;
			// 방문하지 않은 정점 중 최소간선비용의 정점을 선택
			for(int i=1; i<=N; i++) {
				if(!visited[i] && min>minEdge[i]) {
					minVertex = i;
					min = minEdge[i];
				}
			}
			
			//방문정점에 추가
			visited[minVertex] = true;//방문처리
//			System.out.println(minVertex);
			result += min;//신장트리 비용 누적
			
			//트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선비용 고려하기
			for (int i = 1; i <= N; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}
		
		//해답 출력
		System.out.println(result);
		
		
	}

}
