import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1780_종이의개수
 * 318,384KB	852ms
 * ----- ----- -----
 * 분할정복 이용
 * 현재 영역이 모두 같은 숫자로 이루어져있는지 확인 -> 아니면 9개 구간으로 나누기
 * </pre>
 * @author 김신영
 *
 */

public class BJ_1780_종이의개수_김신영 {
	
	static int N;
	static int[] answer;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 배열 입력
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = new int[3];
		dfs(0,0,N);
		
		// 결과 출력 순서, 줄바꿈
		// -1로만 채워진 종이 개수, 0으로만 채워진 종이 개수, 1로만 채워진 종이 개수
		for(int i : answer) {
			System.out.println(i);
		}
	}
	
	// 3/3 나누기
	public static void dfs(int startX, int startY, int size) {
		if(size == 0) {
			return;
		}
		
		int pre = arr[startX][startY];
		for(int i = startX; i < startX + size; i++) {
			for(int j = startY; j < startY+size; j++) {
				if(arr[i][j] != pre) {
					// 모두 같은 숫자가 아니면 9개 부분으로 나누어 다시 확인한다.
					int nextSize = size/3;
					dfs(startX, startY, nextSize);
					dfs(startX, startY + nextSize, nextSize);
					dfs(startX, startY + nextSize * 2, nextSize);
					
					dfs(startX+nextSize, startY, nextSize);
					dfs(startX+nextSize, startY + nextSize, nextSize);
					dfs(startX+nextSize, startY + nextSize * 2, nextSize);
					
					dfs(startX+nextSize*2, startY, nextSize);
					dfs(startX+nextSize*2, startY + nextSize, nextSize);
					dfs(startX+nextSize*2, startY + nextSize * 2, nextSize);	
					return;
				}
			}
		}
		
		// 여기까지 오면 모두 같은 숫자 -> 개수 카운트
		answer[pre+1]++;
		
		return;
	}

}
