import java.io.*;
import java.util.*;

/**
 * BJ_1922_네트워크연결_이항우.java
 * 메모리: 47888 KB
 * 시간: 592 ms
 * 
 * MST문제다.
 * 모든 컴퓨터를 연결하는 데 필요한 비용의 최소값을 구하면 된다.
 * 크루스칼과 프림이 있는데, 프림 너무 어려워서 크루스칼로 풀었다. 공부 더 해서 프림으로도 꼭 풀어봐야겠다.
 * 기본은 union find를 실시하면 되는데, 크루스칼의 중요한 점은 유니온 파인드 전에 가중치를 기준으로 정렬을 해야한다.
 * 왜냐하면 작은 가중치부터 union시키면서, 부모가 같으면 합치지 않고 넘어가며 합치기 때문이다.
 * 
 * @author 이항우
 *
 */
public class BJ_1922 {
	
	static int comNum, lineNum;	// 컴퓨터 수, 라인 수
	static Line[] lines;	// 라인 정보 담을 배열
	
	static int[] parent;	// 가리키는 부모, union find시 사용
	
	static class Line implements Comparable<Line>{
		int from, to, weight;
		public Line(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		// 가중치 기준으로 정렬 위해 오버라이드
		@Override
		public int compareTo(Line o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		comNum = Integer.parseInt(br.readLine());	// 컴퓨터 수
		lineNum = Integer.parseInt(br.readLine());	// 라인 수
		lines = new Line[lineNum];	// 배열 만들기
		
		// 컴퓨터 연결 정보 입력
		for(int i = 0; i < lineNum; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			lines[i] = new Line(from, to, weight);
		}
		// 정렬
		Arrays.sort(lines);
		// 자기의 부모가 자기자신이 되게 함
		makeSet();
		
		// 결과 구해서 출력
		System.out.println(getMSTWeight());
	}
	
	// 자기의 부모가 자기자신이 되게 함
	static void makeSet() {
		parent = new int[comNum+1];
		for(int i = 1; i <= comNum; i++) {
			parent[i] = i;
		}
	}
	
	// 파라미터 컴퓨터의 루트 찾기
	static int find(int com) {
		// 만약 파라미터의 부모가 자기자신이면 걔가 루트다
		if(parent[com] == com) {
			return com;
		}
		
		// 루트 아니면 계속 거슬러 올라가며 루트 찾기
		// 경로압축도 실시
		return parent[com] = find(parent[com]);
	}
	
	// 두 컴퓨터 합치기, 여기 코드상으로는 2번컴퓨터 무리가 1번컴퓨터 무리쪽으로 합쳐지는 느낌으로 보면 된다
	static boolean union(int com1, int com2) {
		int com1Root = find(com1);	// 1번컴 루트
		int com2Root = find(com2);	// 2번컴 루트
		
		// 둘의 루트 같으면 false리턴
		if(com1Root == com2Root) {
			return false;
		}
		
		// 둘 루트 다르면 합칠 수 있으므로 2번 루트 부모를 1번 루트로 설정한다.
		parent[com2Root] = com1Root;
		return true;
	}
	
	// MST를 찾아 최소비용 구하기
	static int getMSTWeight() {
		int sum = 0;	// 비용 합
		int cnt = 0;	// 연결한 컴퓨터 갯수
		
		// 정렬된 정보에서 비용 가장 작은애부터 확인
		for(Line temp : lines) {
			// 해당 연결 정보의 컴퓨터끼리 연결시도
			if(union(temp.from, temp.to)) {
				// 연결 성공시 연결하는 데 드는 비용 더하기
				sum += temp.weight;
				// 연결된거 카운트 해서 연결 다 했으면 끊기
				if(++cnt == comNum) {
					break;
				}
			}
		}
		
		// 결과 리턴
		return sum;
	}
}
