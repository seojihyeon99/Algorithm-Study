package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * @author 박태호
 * <pre>
 * 11904KB 96ms
 * N이 1000이라 2중반복이 될거라 생각
 * 
 * A배열을 순회하면서 이전에 있던 값중 나보다 작은번호의 
 * 증가하는 부분수열+1과 나의 현재 증가하는 부분수열 중 큰 값을 내 증가부분수열로한다.
 * 
 * 점화식
 * if arr[i]>arr[j]:
 * 	dp[i] = max(dp[i],dp[j]+1)
 *   
 * 마지막으로 dp배열중 최대값을 찾아서 출력해낸다.
 * </pre>
 *
 */
public class BJ_11053_가장긴증가하는부분수열_박태호 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int A[],N, dp[] ;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		A=new int[N];
		dp=new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		Arrays.fill(dp, 1);
		for (int i = 0; i < A.length; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = i; j >= 0; j--) {
				if(A[i]>A[j]) {
					dp[i]=Math.max(dp[i], dp[j]+1);
				}
			}
		}
		int max=0;
		for (int len : dp) {
			if(max<len) max=len;
		}
		System.out.println(max);
	}

}
