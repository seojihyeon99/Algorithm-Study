import java.io.*;
import java.util.*;

/**
 * BJ_14567_선수과목_이항우.java
 * 메모리: 130108 KB
 * 시간: 528 ms
 * 
 * 먼저, 과목들간 관계를 표현 해야한다.
 * 과목들은 결국 가중치가 없는 방향 그래프로 표현할 수 있으므로, 인접리스트로 선수과목과 이후 수강 가능한 과목을 표현했다.
 * 이 인접리스트의 노드에는 현재 과목번호, 다음에 수강 가능한 과목 노드 멤버가 있다.
 * 배열로 각 과목들로 들어오는 indegree를 표시하는 배열을 만든다
 * 위상정렬을 위해 indegree가 0인 과목들부터 차례로 1학기부터 카운팅한다.
 * 꺼낸 과목이 가리키는 다음 과목들의 indegree를 하나씩 줄여가며 모든 과목을 이수 할 때까지(모든 과목의indegree가 0) 반복한다.
 * 결과가 담길 배열은 인덱스가 과목번호, 값이 몇학기째인지 나타낸다.
 * 
 * @author 이항우
 *
 */
public class BJ_14567 {

	static int subjNum, caseNum;	// 과목 수, 선수 조건의 수
	static Node[] adjList;			// 과목 인접 리스트
	static int[] indegree;			// indegree
	static int[] result;			// 결과 저장할 배열
	
	// 인접 리스트 위한 노드 클래스
	static class Node {
		int subject;	// 과목 번호
		Node next;		// 다음에 수강 가능한 과목
		public Node(int subject, Node next) {
			this.subject = subject;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력용
		StringBuilder sb = new StringBuilder();	// 출력 문자열 만들기용
		StringTokenizer st;	// 문자열 자르기용
		
		// 입력받기
		st = new StringTokenizer(br.readLine());	// 한 줄 입력
		subjNum = Integer.parseInt(st.nextToken());	// 과목 갯수
		caseNum = Integer.parseInt(st.nextToken());	// 선수 조건의 수
		adjList = new Node[subjNum+1];	// 인접리스트 생성(0번과목 존재x)
		indegree = new int[subjNum+1];	// indegree 체크용 배열 생성(0번과목 존재x)
		result = new int[subjNum+1];	// 결과 저장할 배열 생성(0번과목 존재 x)
		// 선수조건 입력
		for(int i = 0; i < caseNum; i++) {
			st = new StringTokenizer(br.readLine());		// 한 줄 입력
			int from = Integer.parseInt(st.nextToken());	// 현재과목
			int to = Integer.parseInt(st.nextToken());		// 현재를 들으면 들을 수 있는 다음 과목
			adjList[from] = new Node(to, adjList[from]);	// 인접리스트 만들기
			indegree[to]++;	// indegree 표시
		}
		
		int[] idx;		// indegree가 0인 과목번호 담을 배열
		int count = 1;	// 학기 카운트
		while(true) {
			idx = getZeroIndegreeIdxArr();	// indegree가 0인 과목번호 배열 얻어오기
			if(idx[0] == 0) break;			// 만약, 첫 인덱스의 과목번호가 0이면 모두 indegree가 0인것
			
			// idx의 크기만큼 확인(ArrayList 쓰면 더 나을듯?.?)
			for(int i = 0; i < subjNum; i++) {
				if(idx[i] == 0) break;	// indegree가 0인애들 확인 끝난 경우
				result[idx[i]] = count;	// indegree가 0인애 찾아가서 현재 학기 저장
				indegreeUpdate(idx[i]);	// 현재 과목 번호에 대해 얘가 가리키는 애들 indegree 업데이트시키기
			}
			
			count++;	// 학기 늘리기
		}
		
		// 결과 출력
		for(int i = 1; i <= subjNum; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	// currSubject가 가리키는 모든 애들의 indegree 하나씩 줄이기
	static void indegreeUpdate(int currSubject) {
		// 인접리스트에서 currSubject가 가리키는 애들 다 indegree 하나씩 줄이기
		for(Node next = adjList[currSubject]; next != null; next = next.next) {
			indegree[next.subject]--;
		}
	}
	
	// 모든 과목 중, 현재 수강하지 않았고(result에 값이 갱신되지 않아 0인 경우) indegree가 0인 애들을 배열 형태로 리턴
	static int[] getZeroIndegreeIdxArr() {
		int[] res = new int[subjNum];	// 메서드 설명 조건에 맞는 과목번호 담을 배열, 위에 적은것처럼 ArrayList 쓰면 더 나을듯
		int idx = 0;	// 인덱스 조절용
		// 모든 과목 체크
		for(int i = 1; i <= subjNum; i++) {
			if(result[i] == 0 && indegree[i] == 0) {	// 현재 수강하지 않았고, indegree가 0이라면
				res[idx++] = i;	// 리턴할 배열에 저장
			}
		}
		
		return res;	// 위에서 만들어진 배열 리턴
	}
}
