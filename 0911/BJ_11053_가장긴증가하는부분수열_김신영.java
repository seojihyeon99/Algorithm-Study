import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_11053_가장긴증가하는부분수열
 * 12,136KB	104ms
 * ----- ----- -----
 * dp 최장부분증가수열
 * 조건이 헷갈림..
 * </pre>
 * @author 김신영
 *
 */

public class BJ_11053_가장긴증가하는부분수열_김신영 {

	static int N, max = 0;;
	static int[] arr, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N];	// i번째 인덱스에서 끝나는 최장수열
		for(int i = 0; i < N; i++) {
			dp[i] = 1;	// 길이는 1부터 시작
			for(int j = 0; j < i; j++) {
				// i보다 j가 더 작으면 갱신 
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			// 최댓값 갱신
			max = Math.max(max, dp[i]);
		}
		

		System.out.println(max);

	}

}
