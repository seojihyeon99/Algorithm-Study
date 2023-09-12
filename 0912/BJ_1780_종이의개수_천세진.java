package com.ssafy.sejin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1780. 종이의 개수
 * input 범위가 상당히 크고, 분할하여 수행하는 작업이 똑같으므로 Divide-and-Conquer 방식으로 구현하였음
 * 또한, n = 1 이거나 처음부터 모든 값이 똑같은 예외를 처리해주니 통과했다~!
 * 메모리 : 316,892KB, 시간 : 768ms
 * @author user
 *
 */
public class BJ_1780_종이의개수_천세진 {
	
	static int[][] map;
	static int[] count;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		count = new int[3];
		
		// 처음부터 모두 똑같은 종이인지 체크
		int sameCheck = 0;
		boolean isAllSame = true;
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 처음 값 넣기
				if (i == 0 && j == 0) sameCheck = map[i][j];
				
				// 만약 지금까지 값이 다 똑같았고 지금 map에 들어온 값과 처음 값이 다르다면 false로 체킹 후 해당 조건을 다시 검사하지 않음
				if (isAllSame && map[i][j] != sameCheck) isAllSame = false;
				
			}
		}
		
		// n이 1이거나 모두 똑같다면 count 해당 값으로 갱신하고 메서드 들어가지 않음
		if (n == 1 || isAllSame) {
			count[map[0][0] + 1] += 1;
		} else {
			// 메서드 수행
			divideAndConquer(0, 0, n);
		}
		
		// 정답 출력
		System.out.printf("%d\n%d\n%d\n", count[0], count[1], count[2]);
		
		
		
	}
	
	/**
	 * 시작행, 시작열부터 size만큼의 배열에 대해 9등분해서 탐색하고, 만약 값이 다르다면 그 영역에 대해 재귀호출
	 * @param rowStart 시작 행 좌표
	 * @param colStart 시작 열 좌표
	 * @param size 배열의 사이즈
	 */
	private static void divideAndConquer(int rowStart, int colStart, int size) {
		
		int delta = size / 3;
		int row = rowStart;
		int col = colStart;
		
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				int startValue = map[row][col];
				boolean allSame = true;
				
				for (int i = row; i < row + delta; i++) {
					for (int j = col; j < col + delta; j++) {
						if (map[i][j] != startValue) {
							allSame = false;
							break;
						}
					}
				}
				
				if (!allSame) {
					divideAndConquer(row, col, delta);
				} else {
//					System.out.printf("(%d, %d) = %d : %d\n", row, col, startValue, delta * delta);
					count[startValue + 1] += 1;
				}
				col += delta;
			}
			row += delta;
			col = colStart;
		}
		
	}

}
