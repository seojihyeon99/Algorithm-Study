import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1922_네트워크연결 https://www.acmicpc.net/problem/1922
 * 	49,520KB	400ms
 * ----- ----- -----
 * 최소신장트리만들기
 * 노드 최대 1,000개 -> 간선 최대 100,000개이므로 프림 알고리즘 이용
 * 1000 * 1000 = 1,000,000
 * </pre>
 * @author 김신영
 *
 */

public class BJ_1922_네트워크연결_김신영 {
	
	static int N, M;
	static List<Node>[] list;
	static int[] minWeight;
	static boolean[] isVisited;

	static class Node implements Comparable<Node>{
		int no, weight;

		public Node(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// 인접리스트 초기화
		list = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 인접리스트 생성
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, weight));
			list[to].add(new Node(from, weight));
		}
		
		// 초기화
		isVisited = new boolean[N];
		minWeight = new int[N];
		Arrays.fill(minWeight, Integer.MAX_VALUE);
		
		// 우선순위큐 사용, 0번노드 방문처리 및 0번노드와 연결된 노드 큐에 넣기
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(Node n : list[0]) {
			pq.add(n);
		}
		isVisited[0] = true;
		minWeight[0] = 0;
		
		int cnt = 1;
		int sum = 0;
		while(cnt < N) {
			Node curr = pq.poll();
			if(isVisited[curr.no]) continue;

			// 현재 노드(curr) 방문처리, 가중치더하기, 처리한 노드 개수 카운트
			isVisited[curr.no] = true;
			sum += curr.weight;
			cnt++;
			
			for(Node n : list[curr.no]) {
				// 이미 방문한 정점이면 넘어감
				if(isVisited[n.no]) continue;
				// 작은 가중치 갱신, 작은값만 큐에 넣기
				if(n.weight < minWeight[n.no]) {
					minWeight[n.no] = n.weight;
					pq.add(n);
				}
			}
		}
		
		System.out.println(sum);
		
	}

}
