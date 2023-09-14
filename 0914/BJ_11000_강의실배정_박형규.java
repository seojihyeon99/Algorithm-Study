import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * N개의 수업을 강의 시작 시간을 기준으로 오름차순 정렬. 시작시간이 동일한 경우 강의 종료시간을 기준으로 오름차순 정렬
 * 우선순위큐에 현재 사용중인 강의실의 수업이 끝나는 시간을 저장한다.
 * 정렬된 N개의 수업을 순차탐색하면서 현재 수업의 시작시간이 우선순위큐의 루트값보다 작은 경우에는 해당 강의실에서 수업을 진행할 수 없으므로 강의실의 개수 1개 추가
 * 현재 수업의 시작시간이 우선순위큐의 루트값보다 크거나 같은 경우 해당 강의실에서 강의가 진행가능하므로 강의실 종료시간을 갱신
 * </pre>
 * @author 박형규
 * 메모리 68,836 KB
 * 시간 636 ms
 */
public class BJ_11000_강의실배정_박형규 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 수업의 개수
		
		int answer = 1; // 강의실의 개수
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a - b; // 강의 종료시간이 가장 빠른 순으로 삽입, 추출됨
			}
		});
		pq.offer(0); // 초기값 세팅
		
		int[][] arr = new int[N][2]; // N개의 강의들의 시작시간과 종료시간 저장할 2차원 배열 선언 및 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // 수업 시작 시간
			arr[i][1] = Integer.parseInt(st.nextToken()); // 수업 종료 시간
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) return a[1] - b[1]; // 강의 시작 시간이 같은 경우 종료 시간이 빠른 순으로 정렬
				return a[0] - b[0]; // 강의 시작 시간이 빠른 순으로 정렬
			}
		});
		
		// 우선순위큐의 루트노드값은 강의실들 중에서 가장 강의가 빨리 끝나는 강의실이다.
		for (int i = 0; i < N; i++) { // N개의 강의 순차 탐색
			if (pq.peek() > arr[i][0]) { // 현재 강의 시작 시간이 강의가 가장빨리끝나는 강의실의 종료시간보다 작은 경우
				// 해당 강의실에서 연속해서 강의진행불가하므로 새로운 강의실을 배정
				answer++; // 필요한 강의실의 개수 1 증가
			} else { // 현재 강의시작시간이 강의가 가장빨리끝나는 강의실의 종료시간보다 크거나 같은경우
				// 해당 강의실에서 연속해서 강의진행가능함
				pq.poll(); // 루트노드 추출
			}
			pq.offer(arr[i][1]); // 현재 강의 종료시간 우선순위큐에 삽입
		}
		
		System.out.println(answer); // 강의실의 개수 출력
	}

}
