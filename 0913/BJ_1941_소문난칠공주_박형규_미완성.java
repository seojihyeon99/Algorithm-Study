import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * <pre>
 * 
 * </pre>
 * @author 박형규
 *
 */
public class BJ_1941_소문난칠공주_박형규_미완성 {

	static int answer = 0;
	static char[][] arr = new char[5][];
	static boolean[][] visited = new boolean[5][5];
	
	static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		
		for (int i = 0; i < 5; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, arr[i][j] == 'Y' ? 1 : 0);
				visited[i][j] = false;
			}
		}
		
		System.out.println(answer); // 소문난 칠공주를 결성할 수 있는 모든 경우의 수 출력
	}
	
	private static void dfs(int r, int c, int cnt, int Yim) {
		
		if (7 - Yim < 4) {
			return;
		}
		
		
		if (cnt == 7) {
			answer++;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visited[nr][nc])
				continue;
			
			visited[nr][nc] = true;
			dfs(r, c, cnt + 1, arr[nr][nc] == 'Y' ? Yim + 1 : Yim);
			visited[nr][nc] = false;
			
			visited[nr][nc] = true;
			dfs(nr, nc, cnt + 1, arr[nr][nc] == 'Y' ? Yim + 1 : Yim);
			visited[nr][nc] = false;
		}
		
	}
	
	

}
