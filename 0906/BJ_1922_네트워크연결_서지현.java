/**
 * [아이디어]
 * 모든 컴퓨터가 연결이 되어 있어야 한다, 컴퓨터를 연결하는 비용을 최소 => MST생각!!
 * 아직 MST를 구하는데 kruscal 알고리즘밖에 몰라서 kruscal로 품 -> 주말에 prim 공부해야겠당~
 * 
 * Kruscal 알고리즘
 * 1) 간선 중심 => '간선리스트'로 표현
 * 2) 시작전에 간선 정렬
 * 3) union-find 할 수 있다면(즉 두 정점이 서로 다른 집합이라면) union함
 * 4) 선택된 간선수가 n(정점수)-1이 되면 신장트리 완성
 * 
 * comparable 인터페이스 상속받아서 compareTo 메소드 재정의해주어 Edge들 정렬했음 -> 주말에 compare 재정의 하는법도 공부해야겠당~
 * 
 * [메모리]
 * 48200KB
 * [시간]
 * 580ms
 */
package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1922_네트워크연결_서지현 {
	static Edge[] edgeList; // 간선 리스트
	static int n; // 컴퓨터의 수
	static int m; // 연결 가능한 선(간선)의 수
	static int[] parents; // 정점의 부모 노드 저장
	static int total; // 최소 비용
	
	static class Edge implements Comparable<Edge>{ // Edge의 cost에 따른 정렬을 위해 Comparable 인터페이스 구현
		int left;
		int right;
		int cost; // 가중치
		
		public Edge(int left, int right, int cost) {
			super();
			this.left = left;
			this.right = right;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) { // Edge의 cost에 따른 정렬을 위해 compareTo 메서드 재정의
			return this.cost - o.cost;
		}
	}
	
	// 단위 서로소 집합 생성
	static void makeSet() {
		parents = new int[n];
		for(int i=0; i<n; i++) {
			parents[i] = i;
		}
	}
	
	// 해당 집합의 대표자를 찾음
	static int find(int v) {
		if(parents[v] == v) return v; // 대표자가 자기 자신이라면 -> return
		
		return parents[v] = find(parents[v]); // 대표자가 자기 자신이아니라면 다시 재귀 반복. path compression 적용
	}
	
	// b집합을 a집합에 합침
	static boolean union(int a, int b) {
		int aRoot = find(a); // a 집합의 대표자
		int bRoot = find(b); // b 집합의 대표자
		
		if(aRoot == bRoot) return false; // 이미 같은 집합에 속했다면 => false 리턴
		
		// 두개 다른 집합에 있다면, b 집합의 대표자를 a 집합의 대표자 가리키게 하고 => true 리턴
		parents[bRoot] = aRoot; 
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		m = Integer.parseInt(br.readLine()); // 연결 가능한 선(간선)의 수
		
		parents = new int[n]; // 정점의 부모 노드 저장
		makeSet(); // 단위 서로소 집합 생성
		
		// 간선 리스트 선언
		edgeList = new Edge[m];

		// 간선수만큼 반복
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken())-1; // 정점1
			int right = Integer.parseInt(st.nextToken())-1; // 정점2
			int cost = Integer.parseInt(st.nextToken()); // 가중치
			edgeList[i] = new Edge(left, right, cost); // 간선리스트에 객체 생성하여 넣어줌
		}
		
		Arrays.sort(edgeList); // 간선 가중치 오름차순 정렬 => kruscal의 핵심!!
		
		int count = 0; // 선택된 간선수
		// 모든 간선을 순회하면서
		for(int i=0; i<m; i++) {
			Edge cur = edgeList[i];
			if(union(cur.left, cur.right)){ // union 할수 있다면
				total += cur.cost;
				count++;
			}
			if(count == n-1) break; // count가 n-1이 되면 신장트리 완성!! => break;
		}
		
		System.out.println(total);
	}
	
}
