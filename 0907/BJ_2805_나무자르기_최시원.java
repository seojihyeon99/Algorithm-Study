package algo0907;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 이진탐색을 변형하여 적용할 수 있는지 묻는 문제이다
 * 특정 높이에서의 나무 길이 수를 구하고, 문제에서 요구한 M과 비교하여
 * 더 크거나 작으면 이진탐색을 진행한다. 꼭 같다면 해당 값을 return
 * 또 start > end인 조건이라면 역시 return하여 해답을 구한다
 * 
 * 메모리 119356kb 시간 528ms
 * @author 최시원
 *
 */
public class BJ_2805_나무자르기_최시원 {

	static int N, M, trees[], maxHeight;
	public static void main(String[] args) throws IOException {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		//가장 높은 나무의 높이 구하기
		maxHeight=0;
		
		//나무 높이 배열
		trees = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			if(trees[i] > maxHeight) maxHeight = trees[i];  //가장 높은 나무 구하기
			
		}
		
		//이진탐색 수행
		System.out.println(binSer());
		
		
	}
	
	private static int binSer() {
		//start, end, mid, 현재 mid에서 얻는 나무 총 길이를 저장하는 nowTree
		int start = 0;
		int end = maxHeight;
		int mid = 0;
		long nowTree = 0;
		
		while(start < end) {
			
			//mid 구하기
			mid = (start+end) / 2;
			//treeCut은 주어진 mid를 기준으로 나무의 총 길이를 구한다
			nowTree = treeCut(mid);
//			System.out.println(nowTree+" "+mid);
			
			//이것이 M보다 작다면 이진탐색 범위 전반부로 줄이기
			if(nowTree < M) {
				end = mid;
			}
			else {//아니라면 이진탐색 범위 후반부로 줄이기
				start = mid+1;
			}
		}
		
		//start - 1 return
		return start-1;
	}
	
	
	//자른 나무의 총 길이를 구하는 함수
	private static long treeCut(int mid) {
		long sum=0;
		//trees 배열 전체를 탐색하며
		for(int i=0; i<N; i++) {
			//자를 수 있는 나무라면
			if(trees[i] - mid > 0) {
				//잘라서 sum에 더한다
				sum += (trees[i] - mid);
			}
		}
		//sum return
		return sum;
	}

}
