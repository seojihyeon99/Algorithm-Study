import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_14567_선수과목 https://www.acmicpc.net/problem/14567
 * 131,516KB	620ms
 * ----- ----- -----
 * 위상정렬
 * 진입차수가 0인 노드 먼저 처리 -> 0인 노드에서 들아가는 간선 제거 -> 진입차수 0인 노드 처리
 * </pre>
 * @author 김신영
 *
 */

public class BJ_14567_선수과목_김신영 {

	static int N, M;
	static List<Integer>[] list;
	static int[] indegree, answer;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		// 인접리스트 만들기
		indegree = new int[N + 1];
		answer = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int pre = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			
			// 인접리스트 (유향), 진입차수 세기
			list[pre].add(next);
			indegree[next]++;
		}
		
		// 진입차수가 0인 노드 큐에 넣기
		visited = new boolean[N+1];
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				visited[i] = true;
				queue.add(i);
			}
		}

		int cnt = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int s = 0; s < size; s++) {
				int curr = queue.poll();
				answer[curr] = cnt;
				
				// 진입차수 빼기
				for (int i : list[curr]) {
					indegree[i]--;
				}
				
				// 진입차수가 0이 된 노드 큐에 넣기
				for(int i = 1; i <= N; i++) {
					if (indegree[i] == 0 && !visited[i]) {
						visited[i] = true;
						queue.add(i);
					}
				}
			}
			
			// 학기 카운트
			cnt++;
		}
		
		for(int i = 1; i <= N; i++) {
			System.out.print(answer[i] + " ");
		}
	}
}
