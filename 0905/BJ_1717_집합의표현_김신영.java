import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1717_집합의표현  https://www.acmicpc.net/problem/1717
 * 50832KB	516ms
 * ----- ----- -----
 * union find 활용
 * </pre>
 * @author SSAFY
 *
 */

public class BJ_1717_집합의표현_김신영 {

	static int n, m;
	static int[] parents;
	
	public static int find(int a) {
		if(parents[a] == a) {
			return a;
		}
		
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if(pa == pb) {
			return false;
		}
		
		parents[pb] = pa;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 루트노드 초기화
		parents = new int[n+1];
		for(int i = 0; i <= n; i++) {
			parents[i] = i;
		}
		
		// 합집합 0 a b
		// 확인 1 a b
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 0이면 합집합 만들기
			if(order.equals("0")) {
				union(a, b);
			} 
			// 1이면 같은 집합인지 확인
			else {
				if(find(a) == find(b)) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			}
			
		}
		
		System.out.println(sb.toString());

	}

}
