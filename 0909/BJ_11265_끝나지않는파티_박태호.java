package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author 박태호 
 * <pre>
 * 25644KB 344ms
 * 플로이드 워셜
 * 모든 정점에서의 최단거리를 찾는거
 * 처음에는 dfs으로 도전했다가 시초나고 빽트래킹 가지치기 조건도 생각안나서 힘들었는데
 * 다익스트라 혹은 플로이드워셜을 풀 수 있는 문제였다.
 * </pre>		
 * 
 *
 */
public class BJ_11265_끝나지않는파티_박태호 {
	static int n,m,time[][];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		time = new int[n][n];
		// 입력받기.
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < n; j++) {
				time[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		// 플로이드 워셜
		// i->j 바로 가는 시간, i->k->j k를 경유해서 j으로 가는 시간 중 빠른시간을 택한다.
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					time[i][j] = Math.min(time[i][j], time[i][k]+time[k][j]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			if(time[from-1][to-1]<=t) {
				sb.append("Enjoy other party\n");
			}else {
				sb.append("Stay here\n");
			}
		}
		System.out.println(sb);
		
	}
}
