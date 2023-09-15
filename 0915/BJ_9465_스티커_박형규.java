import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * arr[i][j] 기준
 * (1) i==0인 경우, arr[i+1][j-2]와 arr[i+1][j-1]중 최대값을 누적하여 더한다.
 * (2) i==1인 경우, arr[i-1][j-2]와 arr[i-1][j-1]중 최대값을 누적하여 더한다.
 * => arr[0][n]과 arr[1][n]중 최대값을 출력한다.
 * 
 * (1)과 (2)를 점화식으로 표현하면 arr[i][j] += max(arr[(i+1)%2][j-2], arr[(i+1)%2][j-1]) 이다.
 * 다이나믹 프로그래밍으로 풀이 가능
 * </pre>
 * @author 박형규
 * 메모리 105,640 KB
 * 시간 708 ms
 *
 */
public class BJ_9465_스티커_박형규 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine()); // 가로길이(1 ~ 100000)
			int[][] arr = new int[2][n + 1]; // 2*n개의 스티커의 점수를 저장할 2차원배열
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()); // 위치에 해당하는 스티커의 점수 저장
				}
			}
			
			// 점화식
			for (int j = 2; j <= n; j++) {
				for (int i = 0; i < 2; i++) {
					arr[i][j] += Math.max(arr[(i + 1) % 2][j - 2], arr[(i + 1) % 2][j - 1]);
				}
			}
			
			sb.append(Math.max(arr[0][n], arr[1][n])).append("\n"); // 스티커 점수의 최대값 스트링빌더에 삽입
		}
		
		System.out.print(sb); // 모든 테스트케이스 수행 결과 출력
	}

}
