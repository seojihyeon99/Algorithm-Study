package algo0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author user
 *
 */
public class BJ_14567_선수과목_최시원 {
	static class Node{
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + "]";
		}
	}
	static int N,M;
	static Node[] adjList;
	static int[] inDegree;
	static int result[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new Node[N+1];
		inDegree = new int[N+1];
		result = new int[N+1];
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to,adjList[from]);
			++inDegree[to];
		}
		
		topologySort();
		StringBuilder sb = new StringBuilder();		
		for(int a : result) {
			if(a!=0) {
				
				sb.append(a).append(" ");
			}
		}
		System.out.println(sb);

	}
	
	private static void topologySort() {
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 1; i <=N; ++i) {
			if(inDegree[i]==0) {
				queue.offer(i);
				result[i] = 1;
			}
		}
		 
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(Node temp=adjList[cur];temp != null;temp = temp.next) {
				result[temp.vertex] = result[cur] + 1;
				if(--inDegree[temp.vertex]==0) queue.offer(temp.vertex);
			}
		}
	}

}
