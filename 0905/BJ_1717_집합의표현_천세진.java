package com.ssafy.sejin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1717. 집합의 표현
 * Union-find 를 구현하여 해결하였음.
 * 시간을 줄이기 위해 Path-compression 적용하였음 !
 * 유니온 파인드 로직을 복습하기에 너무 좋은 문제였다
 * 메모리 : 54,636KB, 시간 : 516ms
 * @author 세진
 *
 */
public class BJ_1717_집합의표현_천세진 {
	
	// 해당 원소의 대표원소 값을 저장할 parent 배열
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// n, m 공백으로 구분받아 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 주어진 n만큼의 인덱스를 가지는 배열 초기화
		parent = new int[n + 1];
		
		// 최초에 서로소 집합으로, 집합의 대표 원소가 다 자기 자신을 가리키게 초기화함
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		// 명령어 개수 m만큼 반복
		for (int i = 0; i < m; i++) {
			
			// 공백으로 구분
			st = new StringTokenizer(br.readLine());
			
			// 0이면 합집합 연산 , 1이면 같은 집합인지 확인하기
			if (Integer.parseInt(st.nextToken()) == 0) {
				// 구현한 union 메서드 호출
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} else {
				// find메서드로 두 개의 대표 원소 값을 찾아와 같으면 YES, 다르면 NO를 문자열에 추가
				int aFind = find(Integer.parseInt(st.nextToken()));
				int bFind = find(Integer.parseInt(st.nextToken()));
				if (aFind == bFind) answer.append("YES\n");
				else answer.append("NO\n");
			}
			
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}
	
	/**
	 * 해당 집합의 대표원소 값을 찾는 메서드 - Path compression으로 대표원소에 한 번에 접근할 수 있게 구현
	 * @param a 대표원소 값을 찾을 집합의 원소
	 * @return 해당 원소 집합의 대표원소
	 */
	private static int find(int a) {
		if (a == parent[a]) return a;
		else return parent[a] = find(parent[a]);
	}
	
	/**
	 * 두 원소 a, b가 속한 집합의 합집합 연산
	 * @param a 원소 1
	 * @param b 원소 2
	 * @return 이미 합집합 연산을 수행할 수 없다면 false, 성공적으로 합집합 연산을 수행하였으면 true
	 */
	private static boolean union(int a, int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 대표 원소가 같다면 같은 집합
		if (aRoot == bRoot) return false;
		
		// b를 a에 합치기
		parent[bRoot] = aRoot;
		
		return true;
	}

}
