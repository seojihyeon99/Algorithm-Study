import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 미완성
 */

public class BJ_1941_소문난칠공주_김신영 {

	static int answer = 0, limit = 0;
	static char[][] arr = new char[5][5];
	static boolean[][] isVisited = new boolean[5][5];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 5; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		// 이다솜파가 우위 점해야한다. -> S가 4 이상
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				isVisited[i][j] = true;
				boolean[][] visited = new boolean[5][5];
				visited[i][j] = true;
				if (arr[i][j] == 'Y') {
					dfs(i, j, 1, 0, 1, visited);
				} else {
					dfs(i, j, 1, 1, 0, visited);
				}
			}
		}

		System.out.println(answer);
	}
}
