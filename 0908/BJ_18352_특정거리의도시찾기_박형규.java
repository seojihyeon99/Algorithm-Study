import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 출발도시의 번호X가 주어지므로 한정점을 기준으로 나머지 정점까지의 최단거리를 구할 수 있는 다익스트라 알고리즘 이용했다.
 * </pre>
 * @author 박형규
 * 메모리 276,360 KB
 * 시간 1,372 ms
 *
 */
public class BJ_18352_특정거리의도시찾기_박형규 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 단위로 문자열 분리
		
		int N = Integer.parseInt(st.nextToken()); // 도시의 개수(2~300000)
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수(1~1000000)
		int K = Integer.parseInt(st.nextToken()); // 거리 정보(1~300000)
		int X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호(1~N)
		
		List<Integer>[] graph = new List[N + 1]; // 인접 리스트 
		 
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 도시 A
			int B = Integer.parseInt(st.nextToken()); // 도시 B
			graph[A].add(B);
		}
		
		int[] distance = new int[N + 1]; // 각 정점의 최단거리 저장할 1차원 배열
		Arrays.fill(distance, Integer.MAX_VALUE); // 최단 거리 무한대로 초기화
		
		distance[X] = 0; // 출발 도시의 최단 거리는 0
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0]; // 거리 오름차순 정렬
			}
		});
		pq.offer(new int[] {distance[X], X});
		
		// 반복 조건
		while (!pq.isEmpty()) {
			int[] info = pq.poll();
			int dist = info[0], cur = info[1];
			
			if (dist > distance[cur]) { // 이미 최단거리이면 skip
				continue;
			}
			
			for (int next : graph[cur]) { // 인접 정점 탐색
				if (distance[next] > distance[cur] + 1) { // 현재 정점에서 가는게 더 최단이면
					distance[next] = distance[cur] + 1; // 인접 정점의 최단거리 갱신
					pq.offer(new int[] {distance[next], next}); // 우선순위큐  삽입
				}
			}
		}
		
		int count = 0; // 최단거리가 K인 도시의 개수
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		for (int i = 1; i <= N; i++) {
			if (distance[i] == K) { // i번 도시의 최단거리가 K인 경우
				count++; // 도시의 개수 1증가
				sb.append(i).append("\n"); // 스트링빌더에 결과 삽입
			}
		}
		
		System.out.println(count > 0 ? sb.toString() : -1); // 최단 거리가 K인 도시가 존재하지 않는 경우 -1출력, 그외에는 도시의 번호를 오름차순으로 출력
	}

}
