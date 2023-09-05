package algo0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Disjoint Set을 알고 있는지 묻는 문제이다
 * @author 최시원
 *
 */


public class BJ_1717_집합의표현_최시원 {
	
	//서로소집합의 부모 만들기
	private static void make() {
		parent = new int[N+1];
		for(int i=0; i<=N; i++) {
			parent[i] = i;
		}
	}
	
	//주어진 n의 부모 찾기
	private static int find(int n) {
		if(parent[n] == n) return parent[n];
		return parent[n] = find(parent[n]);
		
	}
	
	//주어진 a,b를 union(합집합) 하기
	private static boolean union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA == pB) return false;
		parent[pB] = pA;
		
		return true;
	}
	
	//서로소집합의 요소 갯수 N과 명령 수 M, 서로소 집합의 부모 배열
	static int N, M, parent[];
	public static void main(String[] args) throws IOException {
		//N,M 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//서로소집합 만들기
		make();
		
		//명령 입력받기
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			//명령이 0이면 서로 합집합 연산
			if(order == 0) {
				union(a,b);
			}
			//명령이 1이면 둘의 부모가 같은지 검증
			else if(order == 1) {
				int pA = find(a);
				int pB = find(b);
				//같지 않다면 No 출력
				if(pA!=pB) System.out.println("NO");
				//같다면 YES 출력
				else System.out.println("YES");
			}
		}
	}

}
