package BJ;

/**
 * 289220KB  1332ms
 * @author 박태호
 * bfs 풀이
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_18352_특정거리의도시찾기_박태호 {
	static int N, M, K, X, dis[];
	static List<Integer>[] adjList;
	static boolean v[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		dis = new int[N + 1];
		v = new boolean[N + 1];
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
//		System.out.println("인접리스트 : "+Arrays.toString(adjList) );
		v[X] = true;
		bfs();
		for (int i = 1; i < v.length; i++) {
			if (dis[i] == K) {
				sb.append(i + "\n");
			}
		}
		if (sb.length() == 0) {
			System.out.println(-1);
		} else {
			System.out.println(sb);

		}
	}

	private static void bfs() {
		Queue<Integer> que = new LinkedList<Integer>();
		que.offer(X);
		int cur = 0;
		while (!que.isEmpty()) {
			cur = que.poll();
			for (Integer c : adjList[cur]) {
				if (!v[c]) {
					v[c] = true;
					dis[c] = dis[cur] + 1;
					que.offer(c);
				}
			}

		}
	}

}
