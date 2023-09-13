import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 정점의 수가 최대 500개 이므로 O(N^3)의 시간복잡도를 가지는 플로이드-워셜 알고리즘 사용이 가능
 * 모든 정점들 간의 최단경로를 구해야하므로 다익스트라보단 플로이드워셜쓰는게 나음
 * </pre>
 * @author 박형규
 * 메모리 26,392 KB
 * 시간 476 ms
 */
public class BJ_11265_끝나지않는파티_박형규 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫번째줄 입력
		int N = Integer.parseInt(st.nextToken()); // 파티장의 크기(5~500)
		int M = Integer.parseInt(st.nextToken()); // 서비스를 요청한 손님의 수(1~10000)
		
		int[][] adjMatrix = new int[N + 1][N + 1]; // 파티장이 1번부터 시작하므로 (N+1)*(N+1) 크기의 인접 행렬 생성
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken()); // i번 파티장에서 j번 파티장으로 직접적으로 연결된 도로를 통해 이동하는 시간
			}
		}
		
		// 플로이드 워셜 알고리즘
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 서비스를 요청한 손님이 위치한 파티장의 번호
			int B = Integer.parseInt(st.nextToken()); // 다음 파티가 열리는 파티장의 번호
			int C = Integer.parseInt(st.nextToken()); // 지금으로부터 다음 파티가 열리는데 걸리는 시간
			if (adjMatrix[A][B] <= C) {
				sb.append("Enjoy other party\n"); // 서비스를 요청한 손님이 시간내에 다른 파티장에 도착할 수 있는 경우
			} else {
				sb.append("Stay here\n"); // 시간내에 도착할수 없는 경우
			}
		}
		System.out.print(sb);
	}

}
