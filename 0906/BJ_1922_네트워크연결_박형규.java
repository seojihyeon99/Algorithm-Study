import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 무방향 연결 그래프들의 모든 노드들을 연결해야하므로
 * 최소 신장 트리를 이용해 문제를 풀면 될것 같다.
 * => 크루스칼 알고리즘 사용(유니온파인드)
 * </pre>
 * @author 박형규
 * 메모리 51,240 KB
 * 시간 584 ms
 */
public class BJ_1922_네트워크연결_박형규 {

	static int N, parent[]; // 서로소 집합에서 각 원소가 속한 집합의 루트노드를 저장할 배열
	static List<int[]> edges = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 컴퓨터의 수(1~1000)
		int M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수(1~100000)
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // a컴퓨터
			int b = Integer.parseInt(st.nextToken()); // b컴퓨터
			int c = Integer.parseInt(st.nextToken()); // a컴퓨터와 b컴퓨터를 연결하는데 드는 비용(1~10000)
			edges.add(new int[] {c, a, b}); // 두 정점을 잇는 간선의 가중치, 정점a, 정점b
		}
		
		edges.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0]; // 가중치 오름차순 정렬
			}
		});
		
		int answer = 0; // 모든 컴퓨터를 연결하는데 필요한 최소비용을 저장할 변수
		int cnt = 0; // 사용한 간선의 개수
		for (int[] edge : edges) {
			int weight = edge[0]; // 가중치
			int a = edge[1]; // 정점 a
			int b = edge[2]; // 정점 b
			if (union(a, b)) { // 두 정점 a,b가 다른 집합에 속해있어서 서로 합치게 된 경우
				answer += weight; // 가중치 누적
				if (++cnt == N - 1) { // 간선이 정점-1 과 같아진 순간 최소신장트리가 완성되었으므로 중단
					break;
				}
			}
		}
		
		System.out.println(answer); // 결과 출력
	}

	private static boolean union(int a, int b) {
		
		int rootA = find(a); // a가 속한 집합의 루트노드
		int rootB = find(b); // b가 속한 집합의 루트노드
		
		if (rootA == rootB) return false; // 두 루트노드가 서로 같다면 false 리턴
		parent[rootB] = rootA; // b가 속한 집합을 a가 속한 집합에 통합
		return true;
	}

	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

}
