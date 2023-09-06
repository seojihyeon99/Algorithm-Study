package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 류지원
 * 메모리 : 113756 kb
 * 시간 : 2042ms
 * 풀이방법 : 크루스칼 알고리즘 기법을 사용하였다.
 * 간선 클래스를 만들고, 간선을 간선 정보를 입력받아 가중치순으로 오름차순 한 뒤에
 * 가중치가 낮은 간선의 노드부터 union메서드에 넣었다.
 * 만약 union 메서드에서 두 개의 노드가 합쳐질 수 있다면 true가 나올 것이다.
 * 그러면 두개의 노드가 하나의 집합이 된다.
 * 그러면 그리고 간선의 가중치를 더하는 식으로 최소 신장 트리 형태로 가중치를 더할 수 있다.
 */

public class D4_03124_최소스패닝트리_류지원 {
	static class Edge implements Comparable<Edge>{	// 간선의 정보를 저장할 클래스
		int from, to, weight;	// 간선의 노드 2개와 가중치를 저장할 변수
		public Edge(int from, int to, int weight) {	// 간선 클래스의 생성자
			this.from = from;
			this.to=to;
			this.weight=weight;
		}
		@Override
		public int compareTo(Edge o) {	// sort시에 weight를 기준으로 정렬하기 위한 재정의 메서드
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int V, E;			// 정점 개수, 간선 개수,
	static int[] parents;		// 정점을 집합의 원소들로 보았을 때 대표원소가 무엇인지 나타내기 위한 부모를 나타내주기 위한 배열
	static Edge[] EdgeList;		// 간선 객체의 리스트
	
	
	static void make() {		// 집합에서 원소들을 부모배열을 만들어 주는 메서드.
		parents=new int[V];
		for(int i=0; i<V; i++) parents[i]=i;
	}
	
	static int find(int a) {	// 원소가 속한 집합의 대표자를 출력해주는 메서드. 어떤 집합에 속해있는지 알 수 있음.
		if(parents[a]==a) return a;
		return parents[a]=find(parents[a]);	// 최적화.
	}
	
	static boolean union(int a, int b) {	// 합집합 메서드. 2개의 집합을 합침.
		int aRoot=find(a), bRoot=find(b);	// 각 2개의 원소의 대표자를 추출
		if(aRoot==bRoot) return false;		// 2개의 대표자가 같다면 같은 집합이므로 합집합 하지 않고 false를 반환
		parents[bRoot]=aRoot;				// 만약 위에서 반환되지 않았다면, bRoot의 대표자를 aRoot로 함.
		return true;						// 합집합이 완료디었으므로 true 반환.
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer ST;
		
		int T=Integer.parseInt(br.readLine());
		for(int t=1; t<T+1; t++) {
			ST=new StringTokenizer(br.readLine());
			V=Integer.parseInt(ST.nextToken()); E=Integer.parseInt(ST.nextToken());
			EdgeList=new Edge[E];
			for(int i=0; i<E; i++) {
				ST=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(ST.nextToken())-1;
				int to=Integer.parseInt(ST.nextToken())-1;
				int weight=Integer.parseInt(ST.nextToken());
				EdgeList[i]=new Edge(from, to, weight);	// 간선객체를 생성자로 생성함과 동시에 간선리스트에 값 할당
			}
			
			Arrays.sort(EdgeList);	// EdgeList를 정렬함. Edge클래스에서 compateTo를 weight의 오름차순으로 재정의 했기때문에, weight를 오름차순으로 정렬함
			
			make();	// 부모배열 생성
			
			long result=0;		// 가중치를 더한 값을 result 에 저장
			int count=0;		// 간선의 개수를 세기 위함
			
			for(Edge edge:EdgeList) {	// EdgeList에서 가장 weight가 적은것부터 Edge 클래스를 추출
				if(union(edge.from, edge.to)) {	// edge의 from 노드와 to 노드가 다른 집합이면 합집합을 하고, 아래 조건 수행
					result+=edge.weight;		// result에 edge.weight를 더함
					if(++count==V-1) break;		// count 1증가 및 V-1개수까지 더했는지 확인. 조건을 달성했다면 반복문 종료
				}
			}
			System.out.println("#" + t + " " + result);	// result를 출력
		}

	}

}
