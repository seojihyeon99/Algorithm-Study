package algo0905;

import java.util.*;
import java.io.*;
	
/**
 * BJ_1717_집합의표현_이항우.java
 * 메모리: 48460 KB
 * 시간: 360 ms
 * 
 * union-find를 한다.
 * 두 원소 a, b를 합집합 연산 할 때는, b의 루트노드의 부모를 a의 루트로 바꿔버린다.
 * 두 원소가 같은 집합인지 확인 할 때는 루트노드(부모가 자기 자신)가 나올 때까지 거슬로 올라가며, 그 경로에 있는 모든 원소들의 직속 부모를
 * 해당 루트노드로 바꿔서 경로압축을 실시한다.
 * 이후 두 원소의 루트노드가 같다면 YES, 아니면 NO
 * 
 * @author 이항우
 *
 */
public class BJ_1717 {
	
	static int N, M;		// 집합의 수, 연산 갯수
	static int[] parent;	// 각 집합의 부모
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력용
		StringBuilder sb = new StringBuilder();	// 출력 문자열 만들기용
		StringTokenizer st = new StringTokenizer(br.readLine());	// 문자열 자르기, 한 줄 입력
		
		N = Integer.parseInt(st.nextToken());	// N
		M = Integer.parseInt(st.nextToken());	// M
		makeSet();	// 모두 각자의 부모가 자기 자신이 되게 함
		
		// 연산 수만큼 반복
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());		// 한 줄 입력
			int com = Integer.parseInt(st.nextToken());		// 연산
			int num1 = Integer.parseInt(st.nextToken());	// 집합 a
			int num2 = Integer.parseInt(st.nextToken());	// 집합 b
			if(com == 0) {	// 합집합 연산
				sum(num1, num2);
			}
			else {			// 같은 집합인지 확인하는 연산
				sb.append(isSameParent(num1, num2) ? "YES\n" : "NO\n");
			}
		}
		
		// 결과 출력
		System.out.println(sb.toString());
	}
	
	// 초기에 각 집합의 부모가 자기 자신이 되게 함
	static void makeSet() {
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	// number의 루트노드 찾기, 경로압축 포함
	static int find(int number) {
		// 루트노드를 찾을 때까지 거슬러 올라가서 루트 리턴
		if(parent[number] == number) {
			return number;
		}
		
		// 경로압축을 하며 루트를 찾는다
		// 루트를 찾으며 만나는 모든 노드들은 모두 루트를 바라보게 한다
		return parent[number] = find(parent[number]);
	}
	
	// num1과 num2의 합집합 연산
	static void sum(int num1, int num2) {
		// num2의 루트노드의 부모가 num1의 루트노드가 되게 함
		// 즉, num2 집합이 num1에 합쳐지는 느낌
		parent[find(num2)] = find(num1); 
	}
	
	// num1과 num2가 같은 집합에 속하는지 확인
	// 루트노드가 같은지 확인하면 된다.
	static boolean isSameParent(int num1, int num2) {
		int num1Root = find(num1);	// num1루트 찾기
		int num2Root = find(num2);	// num2루트 찾기
		
		// 둘의 루트가 같다면 true 리턴
		if(num1Root == num2Root) {
			return true;
		}
		
		// 다르다면 false 리턴
		return false;
	}
	
}
