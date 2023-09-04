package BJ;
/**
 * [아이디어]
 * 선수과목 조건을 반드시 지켜야만 한다 => '위상정렬' 생각
 * 매 반복마다 indegree가 0인 애들 정점 처리 + 그 정점에 인접한 정점들의 indegree 1 감소
 * 
 * 최대 정점수 : n(n-1)/2 = 1000*999/2 = 약 499,500 
 * 최대 간선수 : m = 500,000
 * => 최대 정점수 대비 최대 간선수가 거의 비슷 => '인접행렬' 생각
 * 
 * [메모리]
 * 128064KB
 * [시간]
 * 620ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_14567_선수과목_서지현 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 과목의 수
		int m = Integer.parseInt(st.nextToken()); // 선수 조건의 수
		
		int[][] adjMatrix = new int[n+1][n+1]; // 인접행렬(in -> out 간선 정보 저장). 인덱스 0은 쓰지 x
		
		int[] subject = new int[n+1]; // 각 정점(과목)의 indegree. 인덱스 0은 쓰지 x
		int[] result = new int[n+1]; // 각 정점(과목)의 방문 순서(학기)
		boolean[] visited = new boolean[n+1]; // 각 정점(과목)의 방문 여부
		
		// 간선수(선수 조건의 수)만큼 반복
		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			adjMatrix[left][right] = 1;
			subject[right]++; // indegree 증가
		}
		
		int idx = 1; // 현재 학기
		int count = 0; // 방문한 정점수
		while(true) {
			// indegree가 0인 애들의 현재 학기 저장
			for(int i=1; i<=n; i++) {
				if(subject[i] == 0 && !visited[i]) { // indegree 0이고, 방문하지 않았으면
					result[i] = idx; // 현재 학기 저장
					count++; // 방문한 정점수 1 증가
				}
			}
			
			if(count == n) break; // 모든 정점 다 방문했다면 => 종료
			
			// indegree가 0인 애들의 인접 정점의 차수 1 감소
			int[] temp = new int[n+1]; // 임시 배열에 복사
			temp = Arrays.copyOf(subject, n+1);
			for(int i=1; i<=n; i++) { // 각 정점을 순회하며
				if(subject[i] == 0 && !visited[i]) { // 해당 정점의 indegree가 0이고, 방문하지 않았다면
					visited[i] = true; // 방문처리
					for(int j=1; j<=n; j++) { // 인접 정점들 차수 1 감소시킴
						if(adjMatrix[i][j] == 1) { // 인접해있다면
							temp[j]--; // 해당 정점의 차수 1 감소
						}
					}
				}
			}
			subject = Arrays.copyOf(temp, n+1); // 다시 원배열에 복사
			
			idx++; // 학기 수 1 증가
		}
		
		// 출력
		for(int i=1; i<=n; i++) {
			sb.append(result[i] + " ");
		}
		System.out.println(sb);
	}
	
}
