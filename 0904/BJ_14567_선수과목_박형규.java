import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 어떤과목들은 선수과목이 있어 해당되는 모든 과목을 먼저 이수해야만 해당 과목을 이수할 수 있게 되어 있다.
 * => 위상정렬 개념이 생각남
 * 
 * 위상정렬을 이용해 풀면 될것 같다
 * </pre>
 * @author 박형규
 * 메모리 128,572 KB
 * 시간 644 ms
 */
public class BJ_14567_선수과목_박형규 {

	static int N; // 과목의 수
	static List<Integer>[] graph; // 인접 리스트
	static int[] inDegree, result; // 진입차수 배열, 출력결과저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫번째줄 공백기준 분리
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		N = Integer.parseInt(st.nextToken()); // 과목의 수 (1~1000)
		int M = Integer.parseInt(st.nextToken()); // 선수 조건의 수 (0~500000)
		
		inDegree = new int[N + 1]; // 진입차수배열 초기화
		result = new int[N + 1]; // 출력결과저장 배열 초기화
		graph = new List[N + 1]; // 인접리스트 배열 초기화

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>(); // 인접리스트 각각 ArrayList로 초기화
		}
		
		int A, B;
		for (int i = 0; i < M; i++) { // M개의 선수과목 조건
			st = new StringTokenizer(br.readLine()); // 정수 A B형태로 주어짐
			// A번 과목이 B번 과목의 선수 과목(1 <= A < B <= N)
			A = Integer.parseInt(st.nextToken()); // A번 과목
			B = Integer.parseInt(st.nextToken()); // B번 과목
			graph[A].add(B); // A -> B로 가는 간선 추가
			inDegree[B]++; // B의 진입차수 1증가
		}
		
		topologySort(); // 위상정렬
		
		for (int i = 1; i <= N; i++) { // 1번과목부터 N번과목까지 탐색
			sb.append(result[i]).append(" "); // 이수가능한 최소학기 스트링빌더에 삽입
		}
		
		System.out.println(sb); // 출력
	}

	/**
	 * 위상정렬(BFS) 알고리즘
	 */
	private static void topologySort() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				q.offer(i); // 진입차수가 0인 과목들 추가
				result[i] = 1; // 1학기만에 이수 가능함
			}
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll(); // 현재 과목 번호
			
			for (int next : graph[cur]) { // 인접 노드 탐색
				inDegree[next]--; // 진입 차수 1 감소
				result[next] = Math.max(result[next], result[cur] + 1); // next로 들어오는 과목들중 수료학기+1이 가장 큰값으로 세팅해줌
				
				if (inDegree[next] == 0) { // 진입 차수가 0인 경우
					q.offer(next); // 큐에 삽입
				}
			}
		}
		
	}

}
