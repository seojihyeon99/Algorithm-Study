/**
 * [아이디어]
 * 분할정복 문제
 * 
 * [메모리]
 * 316184KB
 * [시간]
 * 828ms
 */
package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1780_종이의개수_서지현 {
	static int n; // 행렬 한변의 길이
	static int[][] arr; // 종이의 각 칸의 숫자(-1,0,1)를 저장
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 행렬 한변의 길이
		
		// 종이의 각 칸의 숫자를 입력받아 저장
		arr = new int[n][n];
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		paper(0, 0, n); // 같은 숫자들만 적힐때까지 종이 자르는 함수 
		
		// -1, 0, 1로만 채워진 종이의 개수 출력
		for(int i=0; i<3; i++) {
			System.out.println(count[i]);
		}
	}
	
	static int[] count = new int[3]; // -1,0,1의 개수 저장
	static void paper(int lefttopx, int lefttopy, int size) { // 좌상단 x, 좌상단 y, 현재 보는 종이의 한변의 길이
		if(size == 1) { // 크기가 1이라면
			if(arr[lefttopx][lefttopy] == -1) count[0]++;
			else if(arr[lefttopx][lefttopy] == 0) count[1]++;
			else if(arr[lefttopx][lefttopy] == 1) count[2]++;
			return;
		}
		
		int start = arr[lefttopx][lefttopy]; // 현재 종이의 좌상단 숫자
		boolean isDifferent = false; // 현재 종이의 숫자가 모두 같은지
		// 현재 종이에 적힌 숫자들을 모두 순회하면서
		for(int r=lefttopx; r<lefttopx + size; r++) {
			for(int c=lefttopy; c<lefttopy + size; c++) {
				if(start != arr[r][c]) { // 처음 좌상단의 숫자와 같지 않으면
					isDifferent = true; // 같지 않다는 flag를 true로 함
					break;
				}
			}
			if(isDifferent) break;
		}
		
		// 현재 종이에 적힌 숫자가 모두 같지 않다면
		if(isDifferent) {
			// 다시 같은 크기의 종이 9개로 자름(크기는 1/3로 줄어듦)
			for(int r=0; r<3; r++) {
				for(int c=0; c<3; c++) {
					paper(lefttopx + size/3*r, lefttopy + size/3*c, size/3);					
				}
			}			
		}
		// 현재 종이에 적힌 숫자가 모두 같다면(-1, 0, 1 섞여있다면)
		else {
			// 모두 -1로만 이루어져 있을경우
			if(start == -1) count[0]++;
			// 모두 0으로만 이루어져 있을경우
			else if(start == 0) count[1]++;
			// 모두 1로만 이루어져 있을경우
			else if(start == 1) count[2]++;
			
			return;
		}
	}
}





