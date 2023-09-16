import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <pre>
 * 최소 신장 트리를 이용해 문제를 풀었다.
 * </pre>
 * @author 박형규
 * 메모리 174,572 KB
 * 시간 1752 ms
 *
 */
public class BJ_21924_도시건설_박형규 {

	static int parents[]; // 유니온 파인드에 이용할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫번째줄 입력 공백단위 분리
		
		int N = Integer.parseInt(st.nextToken()); // 건물의 개수(3~100000)
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수(2~min(N*(N-1)/2, 500000))
		
		parents = new int[N + 1]; // 선언 
		for (int i = 1; i <= N; i++) {
			parents[i] = i; // 초기화
		}
		
		// 두 정점을 잇는 간선 정보를 저장할 2차원 배열 선언
		int[][] edges = new int[M][3];
		
		long total = 0; // 모든 도로를 건설하는데 드는 비용
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken()); // 건물의 번호
			edges[i][1] = Integer.parseInt(st.nextToken()); // 건물의 번호
			edges[i][2] = Integer.parseInt(st.nextToken()); // 두 건물 사이 도로를 만들 때 드는 비용(1~1000000)
			total += edges[i][2];
		}
		
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[2] - b[2]; // 두 정점을 잇는 간선의 가중치 기준으로 오름차순 정렬
			}
		});
		
		int cnt = 0; // 현재까지 건설한 도로의 개수
		long sum = 0; // 현재까지 건설한 도로들을 만드는데 드는 총 비용
		for (int i = 0; i < M; i++) {
			int a = edges[i][0]; // 건물 a
			int b = edges[i][1]; // 건물 b
			int c = edges[i][2]; // 두 건물 사이 도로를 만들 때 드는 비용
			
			if (union(a, b)) { // 두 정점이 서로 다른집합이어서 합치는게 가능한 경우
				sum += c; // 비용 누적
				if (++cnt == N - 1) { // 도로개수1증가한뒤 도로개수가 N-1개가 된경우=> 최소신장트리완성된경우
					break; // 반복 중단
				}
			}
		}
		
		// 최소 신장 트리 간선의 개수가 N - 1개가 아니면 모든 건물이 연결될 수 없으므로 -1 출력, 그렇지않으면 절약한 금액 출력
		System.out.println(cnt == N - 1? total - sum : -1);
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA == rootB) return false;
		parents[rootB] = rootA;
		return true;
	}

	private static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]); // 경로 압축
	}

}
