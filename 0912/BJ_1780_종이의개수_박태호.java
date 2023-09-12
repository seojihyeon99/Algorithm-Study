import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 박태호
 * 	314588KB 856ms
 * 	분할정복
 * 
 * 	재귀에서 반복문을 돌면서 모두 같은 수인지 확인하고
 * 	같은 수가 아니면 재귀호출(N/3, start) 크기, 시작위치
 *	
 */
public class BJ_1780_종이의개수_박태호 {
	static int paper[][],N,minus,zero,plus;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N =Integer.parseInt(bf.readLine());
		paper = new int [N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sol(N,0,0);// 크기, col,row
		// 답 출력.
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(plus);
	}
	private static void sol(int size, int startCol, int startRow) {
		// 임시로 값을 하나 정한다. 이 값과 다르면 잘라야한다.
		int value = paper[startCol][startRow]; 
		boolean flag=false; // 모두 같은 값인지 확인용.

		if(size==1) { // 크기가 1이면 값 갱신하러간다.
			cntf(value);
			return;
		}
		for (int i = startCol; i < startCol+size; i++) {
			for (int j = startRow ; j < startRow+size; j++) {
				if(paper[i][j]!=value) { // 다른값이 발생함.
					flag=true;
				}
			}
		}
		if(flag) { // 다른 값을 발견한 상황
			// 9개의 종이로 자르니까 N/3하는게 맞는듯?
			// 시작은 넘어온 시작위치, 시작위치에서 크기만큼 봐야한다, 3등분 할거라 
			for (int si = startCol; si < startCol+size; si+=(size/3)) {
				for (int sj = startRow; sj < startRow+size; sj+=(size/3)) {
					sol(size/3,si,sj);
				}
			}
			
		}else { // 모두 같은 상황
		cntf(value);
			
		}
		
	}
	private static void cntf(int value) {
		// 전부 같은 종류의 종이인 경우 여기로온다.
		switch (value) {
		case -1:
			minus++;
			break;
		case 0:
			zero++;
			break;
		case 1:
			plus++;
			break;
		}
	}

}
