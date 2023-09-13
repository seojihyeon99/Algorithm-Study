import java.util.*;
import java.io.*;

/**
 * BJ_1647_도시분할계획_이항우.java
 * 메모리: 357400 KB
 * 시간: 2296 ms
 * 
 * "마을을 분할할 때는 각 분리된 마을 안에 집들이 서로 연결되도록 분할해야 한다"
 * -> 모든 마을은 어떻게든 갈 수 있다 -> 최소 비용으로 모든 마을을 연결할 수 있으면 좋겠다 -> MST
 * 
 * MST를 만들고 난 다음 마을을 2개로 분할해야 하는데 어떡할까??
 * -> MST를 만들고 난 다음 연결된 간선 중 가장 큰 비용 간선 하나를 제거하면 마을 2개로 분리 되겠다.
 * -> 그러면 분리된 두 마을은 각각 최소비용으로 연결되어있다.
 * 
 * MST를 만들 때 PRIM알고리즘 사용
 * 그래프는 간선리스트로 만들었고, 프림은 우선순위 큐를 활용해서 구현했다.
 * 계속 구현하다 보니까 크루스칼은 정렬해서 가중치 작은 애들 연결 되면 연결,
 * 프림은 우선순위 큐로 어차피 정렬된거 똑같이 작은애들부터 빼서 연결한 애의 인접한 애들을 다시 다 넣어서 반복
 * 이런 느낌이 들어서 생각보다 크루스칼이랑 비슷하게 구현 됐다.
 * 
 * @author 이항우
 *
 */
public class BJ_1647 {

	static int houseNum, roadNum;
	// 간선 리스트
	static List<Edge>[] graph;	// []로부터 .vertex까지 도달하는 가중치가 weight
	
	static class Edge implements Comparable<Edge> {
		int vertex;	// 도착하는 정점 번호
		int weight;	// 가중치
		
		Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			// 음수는 없어서 그냥 빼는걸로
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		houseNum = Integer.parseInt(st.nextToken());
		roadNum = Integer.parseInt(st.nextToken());
		// 인접리스트 만들기
		graph = new ArrayList[houseNum+1];
		for(int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		
		// 도로 정보 입력받기
		for(int i = 1; i <= roadNum; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 양방향이니까 이렇게
			graph[src].add(new Edge(dest, weight));
			graph[dest].add(new Edge(src, weight));
		}
		
		// 프림 호출
		System.out.println(prim());
	}
	
	static int prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		boolean[] visited = new boolean[houseNum+1];	// 1번 집 없음
		int res = 0;	// MST 만들어진 결과 가중치 합
		
		// 시작 정점 임의로 1로 잡아줬음, 얘 가중치는 0으로 해야한다.
		pq.offer(new Edge(1, 0));
		
		int max = -1;	// 프림을 통해 연결되는게 확정된 간선 가중치 중 가장 큰 값. 즉, 마지막에 끊어버릴 도로 가중치
		while(!pq.isEmpty()) {
			// 집 수만큼 연결했으면 break
			Edge currEdge = pq.poll();
			
			// pq에는 넣을때만 정렬이 되니까 그냥 업데이트된 값을 힙에 또 넣어버리면 된다.
			// 그래서 if(visited[현재 정점]) continue;를 해서 중복된거는 체크 안해준다. 
			if(visited[currEdge.vertex]) continue;
			
			// 방문처리 하고 가중치 누적시키고 연결된 가중치 최대값 구하기
			visited[currEdge.vertex] = true;
			res += currEdge.weight;
			if(max < currEdge.weight) max = currEdge.weight;
//			System.out.println(currNode.vertex + " " + currNode.weight);
			
			// 현재 정점과 인접한 애들 다 확인해서 방문 안했으면 pq에 넣기
			for(Edge nextEdge : graph[currEdge.vertex]) {
				if(!visited[nextEdge.vertex]) {
					pq.add(nextEdge);
				}
			}
		}
		
		// 만들어진 MST의 가중치 합에서 연결된 간선 중 가장 큰 간선 가중치를 뺀다 
		return res - max;
	}
}
