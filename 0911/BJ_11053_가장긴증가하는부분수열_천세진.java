package com.ssafy.sejin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP로 접근하여 해결
 * 일단 DP배열을 1로 초기화 하고, 2중 for문을 돌면서 만약 j번째 요소가 현재 i번째 요소보다 작고 / dp[j] + 1 한 값이 현재 dp[i]보다 크다면 갱신
 * 이렇게 갱신된 값들 중 최댓값을 정답으로 도출
 * 
 * 메모리 : 12,172KB, 시간 : 100ms
 * @author 세진
 *
 */
public class BJ_11053_가장긴증가하는부분수열_천세진 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] dp = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}
		
		
		
		int max = Integer.MIN_VALUE;
		
//		dp[0] = 1;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
			
			max = Math.max(dp[i], max);
		}
		
		System.out.println(max);
		
		
	}

}
