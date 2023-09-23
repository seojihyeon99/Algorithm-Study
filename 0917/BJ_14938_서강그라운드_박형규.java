import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 플로이드 워셜 알고리즘을 통해 풀이 가능한 문제입니다.
 * </pre>
 * @author 박형규
 * 메모리 14,704 KB
 * 시간 152 ms
 *
 */
public class BJ_14938_서강그라운드_박형규 {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째줄 입력
		int n = Integer.parseInt(st.nextToken()); // 지역의 개수 (1 ~ 100)
		int m = Integer.parseInt(st.nextToken()); // 예은이의 수색범위 (1 ~ 15)
		int r = Integer.parseInt(st.nextToken()); // 길의 개수 (1 ~ 100)
		
		int[] item = new int[n + 1]; // 각 구역에 있는 아이템의 수를 저장할 1차원 배열
		int[][] adjMatrix = new int[n + 1][n + 1]; // 인접 행렬
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				adjMatrix[i][j] = 987654321;
			}
		}
		
		for (int i = 1; i <= n; i++) {
			adjMatrix[i][i] = 0;
		}
		
		st = new StringTokenizer(br.readLine()); // 둘째줄 입력
		for (int i = 1; i <= n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < r; i++) { // r개의 줄 입력
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 지역의 번호 a
			int b = Integer.parseInt(st.nextToken()); // 지역의 번호 b
			int l = Integer.parseInt(st.nextToken()); // 길의 길이 l
			
			adjMatrix[a][b] = adjMatrix[b][a] = l;
		}
		
		// 플로이드-워셜 알고리즘
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
		
		// 예은이가 얻을 수 있는 최대 아이템 개수 계산
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			int result = 0;
			for (int j = 1; j <= n; j++) {
				if (adjMatrix[i][j] <= m) {
					result += item[j];
				}
			}
			answer = Math.max(answer, result);
		}
		
		System.out.println(answer); // 결과 출력
	}

}
