import java.util.*;
import java.io.*;

/**
 * BJ_11265_끝나지않는파티_이항우.java
 * 메모리: 25484 KB
 * 시간: 360 ms
 * 
 * 손님이 원하는 파티장까지 가는데 다음 파티가 열리기 전까지 도착할 수 있는지 찾아야 한다.
 * 어떤 한 파티장에서 다음 파티장까지 가는 최단 경로를 미리 다 저장 해 두고,
 * 손님이 원하는 출발지와 도착지, 다음 파티 열리는 시간을 확인해서 비교하고 결과를 출력한다.
 * 플로이드 워셜 알고리즘을 사용했는데, 다익스트라로도 해보고 싶다.
 * 
 * @author 이항우
 *
 */
public class BJ_11265 {

	static int size, customerNum;	// 파티장 크기, 손님 수
	static int[][] party;	// 파티장으로 이동하는 시간
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력용
		StringBuilder sb = new StringBuilder();	// 문자열 만들기용
		StringTokenizer st = new StringTokenizer(br.readLine());	// 파티장 크기, 손님 수 입력
		
		size = Integer.parseInt(st.nextToken());		// 파티장 크기
		customerNum = Integer.parseInt(st.nextToken());	// 손님 수
		party = new int[size+1][size+1];	// 0번 파티장 없음
		
		// 파티장 연결 입력
		for(int i = 1; i <= size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= size; j++) {
				party[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 파티장 2차원 배열에 각각으로 가는 최단거리 넣기
		getTakeTime();
		
		// 손님 요청 입력받기
		for(int i = 0; i < customerNum; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());		// 출발 파티장
			int dest = Integer.parseInt(st.nextToken());	// 도착 파티장
			int time = Integer.parseInt(st.nextToken());	// 다음 파티가 열리는데 걸리는 시간
			
			// 만들어진 party를 보고 조건에 맞게 출력
			if(party[src][dest] <= time) {
				sb.append("Enjoy other party\n");
			}
			else {
				sb.append("Stay here\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	// 플로이드 워셜
	static void getTakeTime() {
		// k는 경유지, i는 출발지, j는 도착지
		for(int k = 1; k <= size; k++) {
			for(int i = 1; i <= size; i++) {
				for(int j = 1; j <= size; j++) {
					// i에서 j까지 가는 데 바로 가는 경우와 k를 경유해서 가는 경우의 최소를 찾아서 설정한다.
					party[i][j] = Integer.min(party[i][j], party[i][k] + party[k][j]);
				}
			}
		}
		
	}

}
