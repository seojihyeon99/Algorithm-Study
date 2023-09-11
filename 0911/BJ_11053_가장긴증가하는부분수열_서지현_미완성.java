/*
* DP 너무 어렵네요... 아직 못풀었습니다ㅠㅠ 어떤 규칙이 있는지 모르겠네여ㅜㅜ흑
*/

package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11053_가장긴증가하는부분수열_서지현 {
	static int[] arr; // 입력받은 수열들
	static int n; // 수열의 크기
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 수열의 크기
		
		// 주어지는 수열들을 입력받음
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		longPermutation(n-1, 0, Integer.MAX_VALUE);
		
		System.out.println(max);
	}
	
	static int max;
	static void longPermutation(int idx, int count, int nextMax) { // n: 현재 수열의 길이, prevMax : 이전까지의 최댓값 
		if(idx == 0) {
			if(arr[idx] < nextMax) count++;
			
			if(max < count) max = count;
			return;
		}
		
		if(arr[idx] < nextMax) {
			longPermutation(idx-1, count+1, arr[idx]);			
		}

		longPermutation(idx-1, count, nextMax);	
	}
}
