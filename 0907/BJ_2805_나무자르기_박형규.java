import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * M미터의 나무가 필요한 상근이. 한줄에 연속해있는 나무들이 있을때, 절단기의 높이 H를 지정하면 H보다 위에 있는 부분이 잘린다.
 * 이때 M미터의 나무를 집에 가져가기 위해 절단기에 설정할 수 있는 높이의 최댓값을 구하라.
 * 
 * 나무의 높이의 합은 M보다 크거나 같으므로 상근이는 항상 필요한 길이만큼의 나무를 가져갈 수 있음.
 * 
 * => 최소높이 low와 최대높이 high의 중간값인 mid로 높이를 설정하고,
 * 가져가는 나무의 미터 result가 M보다 작으면 high를 mid-1로 바꾸고 반복한다.
 * 가져가는 나무의 미터 result가 M보다 크거나 같으면 나무 높이의 최댓값을 큰값으로 갱신하고 low를 mid+1로 바꾸고 반복한다.
 * 
 * 이분 탐색을 이용하여 풀이 가능한 문제
 * </pre>
 * @author 박형규
 * 메모리 119,224 KB
 * 시간 520 ms
 */
public class BJ_2805_나무자르기_박형규 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 단위로 문자열 분리
		
		int N = Integer.parseInt(st.nextToken()); // 나무의 수 (1 ~ 1,000,000)
		int M = Integer.parseInt(st.nextToken()); // 상근이가 집으로 가져가려고 하는 나무의 길이(1 ~ 2,000,000,000)
		
		int[] tree = new int[N]; // N개의 나무의 높이를 저장할 1차원 배열 선언
		st = new StringTokenizer(br.readLine()); // 나무의 높이가 공백으로 분리되어 주어짐
		
		int low = 1, high = 1; // 이진탐색 시작, 끝값 설정
		
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			high = Math.max(high, tree[i]);
		}
		
		while (low <= high) {
			int mid = low + (high - low) / 2;
			
			long result = 0; // 상근이가 가져갈 수 있는 나무의 총 길이, 오버플로우 방지 위해 long타입으로 선언
			for (int i = 0; i < N; i++) {
				if (tree[i] > mid) {
					result += tree[i] - mid; // 가져갈 수 있는 나무의 길이 누적
				}
			}
			
			if (result < M) { // 가져갈 수 있는 나무의 총 길이가 상근이가 집으로 가져갈려고 하는 나무의 길이보다 작은 경우
				high = mid - 1; // 끝값 재설정
			} else { // 가져갈 수 있는 나무의 총 길이가 상근이가 집으로 가져갈려고 하는 나무의 길이보다 크거나 같은 경우
				low = mid + 1; // 시작값 재설정
			}
		}
		
		System.out.println(high); // 결과 출력
	}


}
