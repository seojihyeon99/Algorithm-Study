package algo0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 재귀를 활용하여 해결했다
 * 잘린 종이에 쓰인 숫자가 전부 같다면 재귀의 종료조건으로 생각하고, 쓰인 숫자에 해당하는 변수에 1을 더했다
 * 아니라면 종이를 똑같은 크기로 9분할하여 재귀를 지속한다
 * 메모리 310260kb 시간 928ms
 * @author 최시원
 *
 */
public class BJ_1780_종이의개수_최시원 {
	static int N, paper[][], minus, zero, one;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		paper = new int[N][N];
		minus = 0;
		zero = 0;
		one = 0;
		
		for(int i =0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		paperCut(0,0,N);
		
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(one);
		
	}
	
	private static void paperCut(int x, int y, int n) {
		int indexFix = isPaper(x,y,n);
		if(indexFix != 2) {
			if(indexFix == 1) {
				one++;
			}else if(indexFix == 0) {
				zero++;
			}else {
				minus++;
			}
		}
		else {
			for(int i=x; i<x+n; i+=n/3) {
				for(int j=y; j<y+n; j+=n/3) {
					paperCut(i, j, n/3);
				}
			}
		}

	}
	
	private static int isPaper(int x, int y, int n) {
		int nowSquare = paper[x][y];
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(nowSquare != paper[i][j]) return 2;
			}
		}
		
		return nowSquare;
	}

}
