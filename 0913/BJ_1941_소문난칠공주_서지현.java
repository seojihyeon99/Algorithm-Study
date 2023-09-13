/**
 * [아이디어]
 * 조합 + 백트래킹 + BFS 문제
 * 
 * 입력받은 5*5인 2차원 배열을 -> comb 구하기 쉽게 크기가 25인 1차원 배열에 저장함
 * 이때 'Y'(임도연파)이면 0으로 저장하고, 'S'(이다솜파)이면 1로 저장함 
 * => 나중에 뽑힌 인덱스에 해당하는 숫자들의 합을 구하면 = '이다솜' 파의 수임 => 백트래킹(4보다 작을때)함!!
 * 
 * 일단 조합으로 7명을 뽑고, 백트래킹으로 4명보다 작을때에 대해서 실행하지 않고,
 * 그 후 bfs를 이용하여 연결되어 있는지 체크해줌(시작 정점 큐에 넣고, 1개씩 꺼내면서 방문체크하고 인접하고 뽑힌 정점들 큐에 넣음..을 반복) 
 * 
 * [메모리]
 * 95512KB
 * [시간]
 * 340ms
 */
package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1941_소문난칠공주_서지현 {
	static int[] princess = new int[25]; // 어디파(S/Y)인지 저장
	static int[] numbers = new int[7]; // 선택된 학생의 인덱스 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int r=0; r<5; r++) {
			String s = br.readLine();
			for(int c=0; c<5; c++) {
				if(s.charAt(c) == 'Y') // 임도연파(0)
					princess[r*5 + c] = 0;
				else // 이다솜파(1)
					princess[r*5 + c] = 1;
			}
		}
		
		comb(0, 0); // 7가지 조합을 구함
		
		System.out.println(total);
	}
	
	static int total; // 7공주 결성할 수 있는 경우의 수
	static void comb(int count, int start) {
		// 기저부분
		if(count == 7) { // 7명 선택 완료했다면
			// 해당 7명중에 이다솜파(1)가 4명 보다 작으면 할필요 x
			if(countPrincess() < 4) {
				return;
			}
			
			// 해당 7명중에 이다솜파(1)가 4명 이상이면 => 이 7명이 모두 연결되어있는지 체크
			int cnt = 0; // 방문한 정점의 수
			boolean[] visited = new boolean[25]; // 해당 정점 방문여부
			
			Queue<Integer> queue = new ArrayDeque<>();
			queue.offer(numbers[0]); // 큐에 첫번째 원소 넣고
			cnt++; // 방문한 정점수 1 증가
			visited[numbers[0]] = true; // 방문체크
			
			while(!queue.isEmpty()) { // 큐가 빌때까지 반복
				int cur = queue.poll(); // 큐에서 1개 꺼내서
				
				int top = cur - 5; // 꺼낸 정점 기준 '위'부분 인덱스
				// '위'부분이 배열 범위 넘지않고, 방문하지 않았고, 뽑힌 7명중에 존재한다면
				if(top >= 0 && !visited[top] && isExist(top)) {
					queue.offer(top); // 큐에 넣고
					cnt++; // 방문한 정점수 1 증가
					visited[top] = true; // 방문체크
				}
				
				int bottom = cur + 5; // 꺼낸 정점 기준 '아래'부분 인덱스
				// '아래'부분이 배열 범위 넘지않고, 방문하지 않았고, 뽑힌 7명중에 존재한다면
				if(bottom < 25 && !visited[bottom] && isExist(bottom)) {
					queue.offer(bottom); // 큐에 넣고
					cnt++; // 방문한 정점수 1 증가
					visited[bottom] = true; // 방문체크
				}
				
				int left = cur - 1; // 꺼낸 정점 기준 '왼쪽'부분 인덱스
				// '왼쪽'부분이 배열 범위 넘지않고, 방문하지 않았고, 뽑힌 7명중에 존재한다면
				if(left >=0 && left%5 != 4 && !visited[left] && isExist(left)) {
					queue.offer(left); // 큐에 넣고
					cnt++; // 방문한 정점수 1 증가
					visited[left] = true; // 방문체크
				}
				
				int right = cur + 1; // 꺼낸 정점 기준 '오른쪽'부분 인덱스
				// '오른쪽'부분이 배열 범위 넘지않고, 방문하지 않았고, 뽑힌 7명중에 존재한다면
				if(right < 25 && right%5 != 0 && !visited[right] && isExist(right)) {
					queue.offer(right); // 큐에 넣고
					cnt++; // 방문한 정점수 1 증가
					visited[right] = true; // 방문체크
				}
			}
			
			// 연결되어있다면(방문한 정점수 7이라면)
			if(cnt == 7) total++; // 7공주 결성 가능!! => 총 경우의 수 1 증가
			
			return;
		}
		
		// 조합을 만들어주는 부분
		for(int i=start; i<25; i++) {
			numbers[count] = i; // 현재 count에 선택된 학생의 인덱스 저장
			comb(count+1, i+1); // 다음 조합 찾으러 떠남~(count 1 증가, start 인덱스 1 증가)
		}
	}
	
	// 해당 인덱스(n)가 뽑힌 7명중에 존재하는지
	static boolean isExist(int n) {
		for(int i=0; i<7; i++) {
			if(numbers[i] == n) return true; // 뽑힌 7명중에 존재한다면
		}
		return false; // 뽑힌 7명중에 존재하지않으면
	}
	
	// 뽑힌 학생들 중에서 '이다솜'파의 개수를 구함('이다솜'파는 1로, '임도연'파는 0으로 princess에 저장되어있음)
	static int countPrincess() {
		int sum = 0;
		for(int i=0; i<7; i++) {
			sum += princess[numbers[i]];
		}
		return sum;
	}
}
