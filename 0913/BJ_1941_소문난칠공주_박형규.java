import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * <pre>
 * nextCombination과 너비우선탐색을 이용해서 풀었다.
 * </pre>
 * @author 박형규
 * 메모리 110,532 KB
 * 시간 644 ms
 *
 */
public class BJ_1941_소문난칠공주_박형규 {

	static int answer = 0; // 소문난 칠공주를 결성할 수 있는 모든 경우의 수
	static char[][] board = new char[5][]; // 여학생반의 자리 배치도
	static boolean[][] visited = new boolean[5][5]; // 방문체크배열
	static boolean[][] status = new boolean[5][5]; // 7명의 배치도
	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		
		for (int i = 0; i < 5; i++) {
			board[i] = br.readLine().toCharArray(); // 입력값 저장
		}
		
		int[] p = new int[25]; // 25개의 좌표중 7개를 선택한 결과를 저장할 배열
		for (int i = 24; i > 17; i--) {
			p[i] = 1; // 끝에 7개 선택
		}
		
		do {
			int[] target = new int[7];
			int idx = 0;
			for (int i = 0; i < 25; i++) {
				if (p[i] == 1) { // 해당 좌표를 선택한 경우
					target[idx++] = i;
					int r = i / 5;
					int c = i % 5;
					status[r][c] = true; // 상태 true로 변경
				}
			}
			
			
			if (bfs(target[0])) { // 7개가 서로 연결되어있고, 이다솜파가 우위를 점한 경우
				answer++; // 경우의수 1증가
			}
			
			for (int i = 0; i < 5; i++) {
				Arrays.fill(status[i], false); // 7명배치도 초기화
				Arrays.fill(visited[i], false); // 방문배열 초기화
			}
		} while (nextCombination(p));
		
		System.out.println(answer); // 소문난 칠공주를 결성할 수 있는 모든 경우의 수 출력
	}

	// 너비우선탐색
	private static boolean bfs(int start) {
		Queue<int[]> q = new ArrayDeque<>();
		int startR = start / 5;
		int startC = start % 5;
		q.offer(new int[] {startR, startC});
		visited[startR][startC] = true;
		int count = 1;
		int yCnt = board[startR][startC] == 'Y' ? 1 : 0;
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int r = info[0], c = info[1];
			
			if (7 - yCnt < 4) {
				return false;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && status[nr][nc] && !visited[nr][nc]) {
					visited[nr][nc] = true;
					count++;
					if (board[nr][nc] == 'Y')
						yCnt++;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		
		return count == 7;
	}

	// 현재 조합의 상태에서 다음 조합을 구하는 메서드
	private static boolean nextCombination(int[] p) {
		
		int N = p.length;
		int i = N - 1;
		
		while (i > 0 && p[i - 1] >= p[i]) i--;
		
		if (i == 0) return false;
		
		int j = N - 1;
		
		while (p[i - 1] >= p[j]) j--;
		
		swap(p, i - 1, j);
		
		int k = N - 1;
		
		while (i < k) {
			swap(p, i++, k--);
		}
		
		return true;
	}

	// 스왑
	private static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}

}
