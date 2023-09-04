package com.ssafy.sejin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 14567. 선수과목 (Prerequisite)
 * 위상 정렬 개념을 적용해서 선수되어야 하는 과목의 간선들 + 해당 정점의 차수 (indegree)를 저장
 * indegree 값이 0인 정점들을 queue에 넣고, 하나씩 빼면서 연결 리스트에 저장되어 있는 다음 노드의 차수를 1씩 빼주면서 계산하였음.
 * 위상정렬 까먹을때 쯤 구현해봐서 앞으로도 생생하게 기억날 것 같다~!
 * 메모리 : 132,136KB, 시간 : 648ms
 * @author 세진
 *
 */
public class BJ_14567_선수과목_천세진 {
	
	public static void main(String[] args) throws Exception {
		
		// 입출력 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// 공백으로 문자열 구분해서 n, m에 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 이 과목을 들어야 들을 수 있는 과목들의 리스트를 저장할 prerequisites 2차원 ArrayList 선언
		ArrayList<ArrayList<Integer>> prerequisites = new ArrayList<>();
		
		// 차수를 저장할 indegrees 배열 선언 (1 ~ n 번쨰 인덱스까지 사용)
		int[] indegrees = new int[n + 1];
		
		// 2차원 ArrayList 초기화
		for (int i = 0; i <= n; i++) {
			prerequisites.add(new ArrayList<>());
		}
		
		// 선수과목 개수 m만큼 반복
		for (int i = 0; i < m; i++) {
			
			// 공백으로 문자열 구분해서 pre, post에 저장
			st = new StringTokenizer(br.readLine());
			int pre = Integer.parseInt(st.nextToken());
			int post = Integer.parseInt(st.nextToken());
			
			// 리스트의 pre번째 인덱스에 post 값을 add
			prerequisites.get(pre).add(post);
			
			// post의 차수 1 증가
			indegrees[post]++;
		}
		
		// 해당 과목을 들어야 할 최소 학기 수 배열을 저장
		int[] minArr = new int[n + 1];
		
		// 위상정렬 시 사용할 queue 생성
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		// 초기에 차수가 0인 정점들을 queue에 정점, 학기 를 짝지어서 집어넣고, 학기수 배열을 1로 선언 (선수과목이 없어 1학기만에 바로 들을 수 있음)
		for (int i = 1; i <= n; i++) {
			if (indegrees[i] == 0) {
				queue.offerLast(new int[] { i, 1 });
				minArr[i] = 1;
			}
		}
		
		// queue가 빌 떄까지
		while (!queue.isEmpty()) {
			
			// 제일 앞의 값 꺼내오기
			int[] current = queue.pollFirst();
			
			// 꺼내온 값의 선수과목 리스트를 보며 반복
			for (int post : prerequisites.get(current[0])) {
				// 다음 과목의 차수 1 감소시키기
				indegrees[post]--;
				// 감소시킨 차수가 0이라면
				if (indegrees[post] == 0) {
					// 정점, 현재 정점의 학기 + 1 값을 배열로 묶어 queue에 넣기
					queue.offerLast(new int[] { post, current[1] + 1 });
					// 최소값 배열에 값을 현재 + 1 로 update
					minArr[post] = current[1] + 1;
				}
			}
			
		}
		
		// 최소값 배열 하나씩 정답 문자열에 넣기
		for (int i = 1; i <= n; i++) {
			answer.append(String.format("%d ", minArr[i]));
		}
		
		// 정답 출력
		System.out.println(answer.toString().trim());
		
	}
	
}
