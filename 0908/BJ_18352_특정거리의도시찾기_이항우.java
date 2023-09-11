import java.io.*;
import java.util.*;

/**
 * BJ_18352_특정거리의도시찾기_이항우.java
 * 메모리: 263832 KB
 * 시간: 620 ms
 * 
 * 가중치가 없는 그래프 최단거리 문제 -> BFS
 * 인접리스트로 그래프 그리고 [도시번호, 시작도시에서 거리] 배열로 현재 탐색하는 도시의 거리에서 +1 하면서 결과를 찾았다.
 * 이어지는 도시에서 원하는 거리만큼의 도시를 찾으면 그 이후는 찾지 않아도 된다.
 * 
 * @author 이항우
 *
 */
public class BJ_18352 {

	// 도시 개수, 도로 개수, 거리 정보, 툴발 도시 번호
	static int cityNum, roadNum, targetDistance, startCity;
	
	// 결과 저장할 리스트
	static ArrayList<Integer> result = new ArrayList<Integer>();
	// 인접리스트
	static Node[] adjList;
	
	static class Node {
		int city;
		Node next;
		
		public Node(int city, Node next) {
			this.city = city;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력
		cityNum = Integer.parseInt(st.nextToken());
		roadNum = Integer.parseInt(st.nextToken());
		targetDistance= Integer.parseInt(st.nextToken());
		startCity = Integer.parseInt(st.nextToken());
		adjList = new Node[cityNum+1];	// 인접리스트 만들기
		
		// 인접리스트 만들기
		for(int i = 0; i < roadNum; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			adjList[start] = new Node(end, adjList[start]);
		}
		
		// bfs 도는데, 시작 도시 번호, 거리를 인자로 준다.
		bfs(new int[] {startCity, 0});
		
		// 결과가 0이면 -1출력
		if(result.size() == 0) {
			System.out.println(-1);
			return;
		}
		
		// 결과 출력
		Collections.sort(result);
		for(int t : result) {
			System.out.println(t);
		}
	}
	
	// bfs
	static void bfs(int[] root) {
		Queue<int[]> q = new ArrayDeque<int[]>();	// bfs용 큐, [도시번호, 거리]배열을 담는다.
		boolean[] visited = new boolean[cityNum + 1];	// 방문 배열
		
		q.offer(root);				// 큐에 넣고
		visited[root[0]] = true;	// 방문처리
		
		// 큐에 값이 있는동안 반복
		while(!q.isEmpty()) {
			int[] temp = q.poll();		// 큐에서 꺼내고
			int now = temp[0];			// 현재 도시 번호
			int distance = temp[1];		// 거리
			
			// 만약 원하는 거리만큼의 도시를 찾았다면
			if(distance == targetDistance) {
				// 리스트에 추가
				result.add(now);
				continue;
			}
			
			// 인접리스트 돌기
			for(Node nextCity = adjList[now]; nextCity != null; nextCity = nextCity.next) {
				if(!visited[nextCity.city]) {
					visited[nextCity.city] = true;
					q.offer(new int[] {nextCity.city, distance+1});
				}
			}
		}
	}

}
