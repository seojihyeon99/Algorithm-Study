package BJ;

/**
 * @author 박태호
 * 289916KB 1344ms BFS
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, X, v[];
	static List<Integer>[] adjList;

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		v = new int[N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();
		}
		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
		}
		K++;
		v[X]=1;
		bfs();
		for (int i = 1; i < v.length; i++) {
			if(v[i]==K) {
				sb.append(i+"\n");
			}
		}
		if(sb.length()==0) {
			System.out.println(-1);
		}else{
			System.out.println(sb);
			
		};
	}

	private static void bfs() {
		Queue<Integer> que = new LinkedList<Integer>();
		que.offer(X);
		int cur = 0;
		while (!que.isEmpty()) {
			cur = que.poll();
			for (Integer c : adjList[cur]) {
				if(v[c]==0) {
					v[c] = v[cur] + 1;
					 
						que.offer(c);
					 
				}
			}

		}
	}

}
