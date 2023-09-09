import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_18320_특정거리의도시찾기
 * 286,188Kb	1120ms
 * ----- ----- -----
 * 도시X로부터 출발하여 도달할 수 있는 최단거리가 X인 도시 찾기
 * 가중치 없음 -> BFS
 * </pre>
 * @author 김신영
 *
 */
public class BJ_18320_특정거리의도시찾기_김신영 {
	
	static int N, M, K, X;
	static List<Integer>[] list;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 도시 개주
		M = Integer.parseInt(st.nextToken());	// 도로 개수
		K = Integer.parseInt(st.nextToken());	// 거리 정보
		X = Integer.parseInt(st.nextToken());	// 출발 도시 번호

		// 인접리스트 초기화
		list = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 인접리스트 생성
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
		}
		
		
		// BFS 탐색
		List<Integer> answer = new ArrayList<>();
		boolean[] isVisited = new boolean[N+1];
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.add(X);
		isVisited[X] = true;
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int i = 0; i < size; i++) {
				int curr = queue.poll();
				
				if(cnt == K) {
					answer.add(curr);
				}
				
				for(int n: list[curr]) {
					if(!isVisited[n]) {
						isVisited[n] = true;
						queue.add(n);
					}
				}
			}
			
			// 거리 K까지 검사 후 종료
			if(cnt == K) {
				break;
			}
			
			cnt++;
		}
		
		// 정답 생성
		// 최단거리 K 없으면 -1 & 있으면 정렬 후 출력
		StringBuilder sb = new StringBuilder();
		if(answer.size() == 0) {
			System.out.println(-1);
		} else {
			Collections.sort(answer);
			for(int i : answer) {
				sb.append(i).append("\n");
			}
			System.out.println(sb.toString());
		}
		
	}

}
