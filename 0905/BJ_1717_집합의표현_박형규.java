import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 서로소 집합과 유니온 파인드를 사용하여 풀이 가능한 문제
 * </pre>
 * @author 박형규
 * 메모리 50,104 KB
 * 시간 472 ms
 */
public class BJ_1717_집합의표현_박형규 {

	static int n, parent[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 단위 문자열 분리
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		n = Integer.parseInt(st.nextToken()); // 초기 집합의 개수({0}~{n}까지 n+1개 존재)
		int m = Integer.parseInt(st.nextToken()); // 연산의 개수
		
		parent = new int[n + 1]; // 유니온 파인드
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cmd = Integer.parseInt(st.nextToken()); // 명령어
			int a = Integer.parseInt(st.nextToken()); // a가 포함되어 있는 집합
			int b = Integer.parseInt(st.nextToken()); // b가 포함되어 있는 집합
			
			if (cmd == 0) { // 명령어가 0인 경우
				union(a, b); // a가 포함되어 있는 집합과 b가 포함되어 있는 집합을 합침
			} else { // 명령어가 1인 경우
				if (find(a) == find(b)) { // a와 b가 같은 집합에 포함되어 있다면
					sb.append("YES\n"); // YES를 한줄에 출력
				} else { // a와 b가 서로 다른 집합에 속한다면
					sb.append("NO\n"); // No를 한줄에 출력
				}
			}
		}
		
		System.out.print(sb); // 입력 수행결과 한번에 출력
	}

	private static boolean union(int a, int b) { // 노드 a가 속한 집합과 노드 b가 속한 집합을 하나의 집합으로 통합
		
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA == rootB) return false;
		
		parent[rootB] = rootA;
		return true;
	}

	private static int find(int x) { // 노드 x의 루트 노드 탐색
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

}
