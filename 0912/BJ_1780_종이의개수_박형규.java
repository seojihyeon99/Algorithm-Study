import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 분할정복 알고리즘 문제
 * </pre>
 * @author 박형규
 * 메모리 315,536 KB
 * 시간 1304 ms
 */
public class BJ_1780_종이의개수_박형규 {

	static int N, matrix[][];
	static int cnt1, cnt2, cnt3; // -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringTokenizer st;
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		
		N = Integer.parseInt(br.readLine()); // 행렬의 가로 세로 길이(1~ 3^7, 3^k꼴)
		
		matrix = new int[N][N]; // N*N 크기의 행렬
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dc(0, 0, N); // 분할정복 수행
		
		sb.append(cnt1).append("\n").append(cnt2).append("\n").append(cnt3).append("\n");
		System.out.print(sb); // 결과 출력
	}

	private static void dc(int r, int c, int size) {
		int x = 0, y = 0, z = 0; // -1, 0, 1의 개수
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (matrix[i][j] == -1) x++;
				else if (matrix[i][j] == 0) y++;
				else z++;
			}
		}
		
		if (x == size * size) { // 전부다 -1로 채워진 경우
			cnt1++;
			return;
		}
		
		if (y == size * size) { // 전부다 0으로 채워진 경우
			cnt2++;
			return;
		}
		
		if (z == size * size) { // 전부다 1로 채워진 경우
			cnt3++;
			return;
		}
		
		// 위의 세가지 경우에 해당하지 않는 경우 종이를 9개로 잘라서 분할정복 수행
		int third = size / 3;
		
		dc(r, c, third); 
		dc(r, c + third, third);
		dc(r, c + third * 2, third);
		dc(r + third, c, third);
		dc(r + third, c + third, third);
		dc(r + third, c + third * 2, third);
		dc(r + third * 2, c, third);
		dc(r + third * 2, c + third, third);
		dc(r + third * 2, c + third * 2, third);
	}

}
