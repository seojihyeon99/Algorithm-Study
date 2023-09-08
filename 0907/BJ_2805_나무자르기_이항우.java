import java.io.*;
import java.util.*;

/**
 * BJ_2805_나무자르기_이항우.java
 * 
 * 메모리: 167696 KB
 * 시간: 480 ms
 * 
 * 이분탐색을 한다
 * start, mid, end로 나눠서 첫 mid(start + end) / 2와 원하는 값의 크기비교에 따라 인덱스들을 조절한다.
 * mid보다 클 경우: start를 mid+1로
 * mid보다 작을 경우 end를 mid-1로 설정해서 탐색 횟수를 줄인다
 * 
 * 
 * @author 이항우
 *
 */
public class BJ_2805 {

	static int treeNum, requireTreeLen;	// 나무갯수, 필요한 최소 나무 길이
	
	static int[] treeHeight;	// 나무들 높이 입력 배열
	static int maxHeight;		// 현재 나무들 중 가장 높은 나무
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		treeNum = Integer.parseInt(st.nextToken());
		requireTreeLen = Integer.parseInt(st.nextToken());
		treeHeight = new int[treeNum];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < treeNum; i++) {
			// 입력 받으면서 가장 높은 나무 설정
			treeHeight[i] = Integer.parseInt(st.nextToken());
			if(treeHeight[i] > maxHeight) maxHeight = treeHeight[i];
		}
		
		System.out.println(getCurrHeight());
	}
	
	static int getCurrHeight() {
		int cutterHeight = 0;	// 절단기 높이
		int start = 0;
		int end = maxHeight;
		int mid;
		long temp;	// 잘라낸 나무들 길이
		
		// 계속된 이분탐색으로 start가 end보다 크거나 같아지는 순간 종료
		while(start <= end) {
			temp = 0;
			
			// 원하는 값이 mid기준으로 크거나 같아질 때마다 start와 end가 변화하며
	        // mid값을 새로 설정 해 준다.
			mid = (start + end) / 2;
			
			// 잘라낸 나무의 총 길이 temp에 저장
			for(int i = 0; i < treeNum; i++) {
				if(treeHeight[i] > mid) {
					temp += treeHeight[i] - mid;
				}
			}
			
			// 잘라낸 나무의 길이가 원하는 길이보다 작을 경우
	        // end 재설정 해서 while문 상단에서 mid를 조정 해 절단기 높이를 낮춘다.
			if(temp < requireTreeLen) {
				end = mid - 1;
			}
			// 크거나 같을 경우 절단기 높이로 mid를 설정
	        // (최소한 requireTreeLen만큼 가져가야 하므로 클 경우에도 cutterHeight에 설정 해 준다.)
	        // 이후 start를 재설정 해서 while문 상단에서 mid를 조정 해 절단기 높이를 높인다.
	        // 같을 경우 바로 끝내지 않는 이유는 절단기 높이가 최대일 경우를 구해야 하기 때문이다.
			else {
				cutterHeight = mid;
				start = mid + 1;
			}
		}
		
		return cutterHeight;
	}
	
}
