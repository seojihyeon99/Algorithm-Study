package com.ssafy.sejin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 백준 1941. 소문난 칠공주
 * 조합으로 25C7 넥퍼로 생성해서 BFS로 이어지는지 + 다솜이파 / 도연이파 카운팅
 * 이어지지 않거나, 도연이파 4명 이상이면 return (백트래킹)
 * DFS로 안 되길래 사실 철가방 했음 ㅜㅜ
 * 메모리 : 298,460KB, 시간 : 748ms (???)
 * @author 세진
 *
 */
public class BJ_1941_소문난칠공주_천세진 {
	
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	static int[][] map; // S : 0, Y : 1
	static int[] combArr;
	
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[5][5];
		combArr = new int[25];
		
		for (int i = 0; i < 5; i++) {
			String line = br.readLine();
			for (int j = 0; j < 5; j++) {
				char current = line.charAt(j);
				if (current == 'S') map[i][j] = 0;
				else map[i][j] = 1;
			}
		}
		
		for (int i = 1; i <= 7; i++) {
			combArr[25 - i] = 1;
		}
		
		do {
			
			int[] selected = new int[7];
			int idx = 0;
			
			for (int i = 0; i < 25; i++) {
				if (combArr[i] == 1) selected[idx++] = i;
				if (idx == 7) break;
			}
			
			if (isConnected(selected)) answer++;
			
		} while (nextPermutation(combArr, combArr.length));
		
		System.out.println(answer);
		
	}
	
	/**
	 * BFS로 탐색하며 주어진 배열 내에 있는 좌표들이 서로 이어지면서 다솜이파가 4명 이상인 조건을 만족하는지 체크
	 * @param arr 7개의 좌표 (0~25 범위 내)
	 * @return 조건 만족하면 true, 아니면 false
	 */
	private static boolean isConnected(int[] arr) {
		
		int[][] pos = new int[7][3];
		boolean[] visited = new boolean[7];
		int visitCount = 0;
		
		int[] areaCount = new int[2];
		
		for (int i = 0; i < arr.length; i++) {
			int row = arr[i] / 5;
			int col = arr[i] % 5;
			
			pos[i] = new int[] { row, col, map[row][col] };
		}
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		queue.offer(pos[0]);
		visited[0] = true;
		visitCount++;
		areaCount[pos[0][2]]++;
		
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextX = current[0] + dx[i];
				int nextY = current[1] + dy[i];
				
				if (nextX < 0 || nextX >= 5 || nextY < 0 || nextY >= 5) continue;
				
				for (int j = 0; j < 7; j++) {
					if (!visited[j] && pos[j][0] == nextX && pos[j][1] == nextY) {
						queue.offer(pos[j]);
						visited[j] = true;
						visitCount++;
						areaCount[pos[j][2]]++;
						
						if (areaCount[1] >= 4) return false;
						
					}
				}
				
			}
			
		}
		
		// 7개의 정점을 다 방문했고, 다솜이파가 4명 이상이라면 true, 아니면 false
		if (visitCount == 7 && areaCount[0] >= 4) {
//			System.out.println(Arrays.toString(arr) + " : " + visitCount + ", " + Arrays.toString(areaCount));
			return true;
		}
		else return false;
	}
	
	/**
	 * Next Permutation 알고리즘 구현 코드
	 * @param arr 생성할 배열
	 * @param size 배열의 크기
	 * @return 성공적으로 nextPermutation이 만들어졌으면 true, 더 이상 만들 수 있는 순열이 없으면 false
	 */
	private static boolean nextPermutation(int[] arr, int size) {
		
		int i = size - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) i--;
		
		if (i == 0) return false;
		
		int j = size - 1;
		while (arr[i - 1] >= arr[j]) j--;
		
		swap(arr, i - 1, j);
		
		j = size - 1;
		while (i < j) swap(arr, i++, j--);
		
		
		return true;
	}
	

	/**
	 * 배열 내의 i, j 번째 인덱스의 값을 바꾸는 함수
	 * @param arr 배열
	 * @param i 인덱스 1
	 * @param j 인덱스 2
	 */
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
