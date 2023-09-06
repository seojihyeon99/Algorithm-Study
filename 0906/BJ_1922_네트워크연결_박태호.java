package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * @author 박태호
 * <pre>
 * 46708KB, 496ms
 * 크루스칼알고리즘 사용.
 * 
 * </pre>
 */
public class BJ_1922_네트워크연결_박태호 {
	// 간선 생성, 정렬: 가중치 오름차순.
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int val;

		public Edge(int from, int to, int val) {
			this.from = from;
			this.to = to;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) {
			// 오름차순 정렬.
			return Integer.compare(this.val, o.val);
		}

	}
	// 정점, 간선, 간선배열, root정점을 기록할 배열
	static int V, E;
	static Edge[] edgeArr;
	static int[] parents;
	// 초기는 모든 정점이 루트이다.
	public static void make() {
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	// rootV를 찾아옴.
	public static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}
	// b의 부모를 a부모로, 포함시킨다.
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) // 두 부모가 같다면 즉, 이미 연결된 관계라면 무시한다.
			return false;

		parents[bRoot] = aRoot; // 부모 합치기
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int from, to, val;
		V = Integer.parseInt(bf.readLine());
		E = Integer.parseInt(bf.readLine());

		// 엣지 배열 선언
		edgeArr = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			val = Integer.parseInt(st.nextToken());
			edgeArr[i] = new Edge(from, to, val);
		}

		// 간선 리스트 정렬
		Arrays.sort(edgeArr);

		// 정점 만들기
		make();

		// 비용이 작은 순으로 꺼내며 서로소 집합 관계라면 연결하는 방식으로 풀이한다.
		// 비용이 작더라도 이미 연결되어 있는상태(parent[from]==parent[to])인 경우 무시한다.
		long result = 0;
		int cnt = 0;
		for (Edge edge : edgeArr) {
			if (union(edge.from, edge.to)) {
				cnt++;
				result += edge.val;
				if (cnt == V - 1) // 모든 정점을 돌아봄.
					break;
			}
		}
		sb.append(result + "\n");
		System.out.println(sb);
	}

}
