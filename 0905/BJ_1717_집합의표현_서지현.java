/**
 * [아이디어]
 * 서로소 집합(Disjoint set)
 * - makeSet : 단위 서로소 집합 생성(부모를 자기 자신가리키게)
 * - find : 해당 집합의 대표자 찾음(부모가 자기 자신이면 해당 집합의 대표자임)
 * - union : 두 집합을 합침(b 집합의 대표자가 -> a 집합의 대표자를 가리키게 함)
 * 
 * + 최악의 경우의 시간 줄이기 위해, 경로압축(path compression) 사용함
 * 
 * [메모리]
 * 47352KB
 * [시간]
 * 460ms
 */
package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1717_집합의표현_서지현 {
	static int n; // 원소수
	static int m; // 연산 개수
	static int[] parents; // 해당 원소의 부모 저장
	
	// 단위 집합 생성
	static void makeSet(){
		parents = new int[n+1];
		// 모든 원소의 부모를 자기 자신으로 만듦
		for(int i=0; i<=n; i++) {
			parents[i] = i;
		}
	}
	
	// 집합의 대표자 찾기
	static int find(int a) {
		if(parents[a] == a) return a; // 대표자라면 => 바로 return
		
		// 대표자가 아니라면 => 대표자 찾기위해 다시 재귀함수(부모를 매개변수로) 호출. 이때 경로 압축(path compression)해줌
		return parents[a] = find(parents[a]); 
	}
	
	// 집합 합치기(임의로 b를 a에 합침)
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false; // 이미 같은 집합에 속해있다면 => false 반환
		
		parents[bRoot] = aRoot; // b집합의 대표자를 a집합의 대표자를 가리키게함
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 원소수
		m = Integer.parseInt(st.nextToken()); // 연산 개수
		
		// 초기에 n+1개 집합({0}~{n}) 생성
		makeSet();
		
		// 주어진 연산 개수만큼 반복
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken()); // 연산(합집합 : 0, 같은 집합인지 확인 : 1)
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			// 같은 집합인지 확인하는 연산이라면
			if(op == 1) {
				if(find(left) == find(right)) { // 같은 집합이라면
					sb.append("YES\n");
				}
				else { // 다른 집합이라면
					sb.append("NO\n");
				}
			}
			// 합집합 연산이라면
			else {
				union(left,right);
			}
		}
		
		System.out.println(sb);
	}
}
