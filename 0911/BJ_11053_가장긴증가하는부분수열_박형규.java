import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * LIS를 구하는 문제인데 수열의 크기 N이 최대 1000이므로
 * O(N^2)의 시간복잡도로 풀이 가능한 DP로 풀수 있다.
 * </pre>
 * @author 박형규
 * 메모리 14,568 KB
 * 시간 148 ms
 */
public class BJ_11053_가장긴증가하는부분수열_박형규 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		
		int N = Integer.parseInt(br.readLine()); // 수열 A의 크기(1~1000)
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 수열 A를 이루고 있는 Ai가 공백을 기준으로 분리되어 주어짐
		
		int[] A = new int[N]; // 수열 A를 이루고 있는 Ai를 저장할 1차원 배열
		int[] dp = new int[N]; // dp 테이블
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken()); // Ai값 저장
		}
		dp[0] = 1; // dp 초기값 설정
		
		for (int i = 1; i < N; i++) {
			dp[i] = 1; // i번째 원소 기준으로 길이 1로 초기화
			for (int j = 0; j < i; j++) { // 0부터 i-1까지의 원소값 탐색
				if (A[j] < A[i]) { // j번째 원소값이 i번째 원소값보다 작은 경우
					dp[i] = Math.max(dp[i], dp[j] + 1); // j번째 dp값+1과 기존 dp값중 최대값으로 갱신ㄴ
				}
			}
		}
		
		int answer = dp[0]; // 가장 긴 증가하는 부분 수열의 길이
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, dp[i]); // 큰값으로 갱신
		}
		
		System.out.println(answer); // 가장 긴 증가하는 부분 수열의 길이 출력
	}

}
