package com.ssafy.sejin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1922. 네트워크 연결
 * 프림 알고리즘 복습하기 딱좋았어요 ~!
 * 
 * 메모리 : 44,788KB, 시간 : 364ms
 * @author 세진
 *
 */
public class BJ_1922_네트워크연결_천세진 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] adjustMatrix = new int[n + 1][n + 1];
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjustMatrix[from][to] = weight;
			adjustMatrix[to][from] = weight;
		}
		
		boolean[] visited = new boolean[n + 1];
		int[] minEdge = new int[n + 1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1[1], o2[1]);
			}
			
		});
		
		// 임의 정점에서 시작
		minEdge[1] = 0;
		queue.offer(new int[] { 1, 0 });
		
		int result = 0; // MST의 비용
		int visitCount = 0;
		
		while (!queue.isEmpty()) {
			
			int[] current = queue.poll();
			
			if (visited[current[0]]) continue;
			
			visited[current[0]] = true;
			result += current[1];
			
			if (++visitCount == n) break;
			
			for (int i = 1; i <= n; i++) {
				if (!visited[i] && adjustMatrix[current[0]][i] != 0 && minEdge[i] > adjustMatrix[current[0]][i]) {
					minEdge[i] = adjustMatrix[current[0]][i];
					queue.offer(new int[] { i, minEdge[i] });
				}
			}
			
		}
		
		
		System.out.println(result);
		
	}

}
