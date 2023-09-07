import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ_2805_나무자르기_김신영
 * 168,016KB	488ms
 * ----- ----- -----
 * 이분탑색
 * 나무 높이가 부족하면 -> 나무 자르는 높이 낮춰서 더 많이 자르기
 * 나무 높이가 더 많으면 -> 나무 자르는 높이 높여서 더 적게 자르기
 * </pre>
 * @author 김신영
 *
 */

public class BJ_2805_나무자르기_김신영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 나무 수
		int m = Integer.parseInt(st.nextToken()); // 필요한 나무 미터
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 나무 높이 최소, 최대값으로 설정
		int low = 0;
		int high = 1000000000;
		
		while(high >= low) {
			int mid = (low + high) / 2;
			
			long sum = 0;
			for(int i = 0; i < n; i++) {
				if(arr[i] > mid) {
					sum += arr[i] - mid;
				}
			}
			
			if(sum < m) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		
		
		System.out.println(high);
	}
}