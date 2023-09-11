/**
 * [아이디어]
 * 모든 집을 연결(신장트리)하는 가장 최소 간선 비용은 => MST
 * 이렇게 만든 MST 중에서 가장 큰 간선 비용을 빼주면 => 마을 자동으로 2개로 분리되면서, 유지비의 합 최소!!
 *  
 * kruscal 알고리즘을 사용하여 해결하였음(간선 정렬 + union-find가 다함)
 * 
 * 처음에 55%에서 계속 틀림
 * n=2이고, m=1이면 반복문이 딱 1번만 돌아서 lastCost를 빼주지 못했음 ㅠㅠ
 * => if(count == n-1)을 체크하는 부분을 뒤로 이동시킴
 * ex) 정답 0 나와야
 * 2 1
 * 1 2 3
 * 
 * ※ 간선리스트를 정렬하는게 아닌, PriorityQueue에 넣으면 시간 완전 많이 줄어듦!!(2500ms->1236ms)
 * 
 * [시간]
 * 1236ms
 * [메모리]
 * 321068KB
 */
package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 간선 정보와 관련된 클래스
class Edge{
	int left; // in
	int right; // out
	int weight; // 가중치
	
	public Edge(int left, int right, int weight) {
		super();
		this.left = left;
		this.right = right;
		this.weight = weight;
	}
}

public class BJ_1647_도시분할계획_서지현 {
	static int n; // 집의 개수
	static int m; // 길의 개수
	static int[] parents; // 각 정점이 가리키는 부모노드(혹은 대표자)
	
	// 단위 서로소 집합 만듦
	static void makeSet() {
		parents = new int[n];
		for(int i=0; i<n; i++) { // 부모가 자기 자신을 가리키게 함
			parents[i] = i;
		}
	}
	
	// 각 정점이 속한 집합의 대표자 찾음
	static int find(int v) {
		// 해당 정점이 대표자라면 => return!
		if(parents[v] == v) return v;
		
		// 해당 정점이 대표자가 아니라면 => 부모에 대해서 대표자 찾기 반복수행 (이때 path compression 적용)
		return parents[v] = find(parents[v]); 
	}
	
	// 원소 a가 속한 집합과 원소 b가 속한 집합을 합침(편의상 b를 -> a집합에)
	static boolean union(int a, int b) {
		int aRoot = find(a); // a가 속한 집합의 대표자
		int bRoot = find(b); // b가 속한 집합의 대표자
		
		// 이미 두 원소가 같은 집합에 있다면 => false 반환!
		if(aRoot == bRoot) return false; 
		
		// 두 원소가 다른 집합에 있다면
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 집의 개수
		m = Integer.parseInt(st.nextToken()); // 길의 개수
		
		Edge[] edgeList = new Edge[m]; // 간선 리스트
		// 길의 개수만큼 반복
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken())-1;	
			int right = Integer.parseInt(st.nextToken())-1;	
			int weight = Integer.parseInt(st.nextToken());	
			edgeList[i] = new Edge(left, right, weight);
		}
		
		// 간선을 오름차순으로 정렬 => kruscal 알고리즘 핵심
		Arrays.sort(edgeList, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.weight-o2.weight;
			}
		});
		
		makeSet(); // 서로소 집합 생성

		int count = 0; // 선택한 간선의 수
		int total = 0; // 총 비용
		int lastCost = 0; // 마지막 간선 비용(신장트리 완성시키면 제일 최대일것 => 이것을 제거!!)
		for(int i=0; i<m; i++) {
			Edge cur = edgeList[i];
			if(union(cur.left, cur.right)) { // 두 정점 다른 집합에 있다면 => union!!
				total += cur.weight; // 총 비용에 현재 간선의 비용을 더함
				count++; // 선택된 간선의 수 1 증가
				lastCost = cur.weight; // 마지막 간선 비용을 업데이트
			}
			if(count == n-1) { // n-1개의 간선이 선택되었다면 => 신장트리 완성!!
				total -= lastCost; // 간선들 중 가장 비용 큰 간선을 제거 => 자동으로 마을 2개로 분리됨
				break;
			}
		}
		
		System.out.println(total);
	}
	
}
