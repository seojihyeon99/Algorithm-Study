import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 수업때 많이 풀어봐서 그런지 분할정복이 바로 떠올랐다
 * 알고리즘 수업 최고?
 * 315196 kb, 784 ms
 * 
 */
public class 종이 {

	static int[][] arr; 
	static int[] counts;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		arr=new int[N][N];
		StringTokenizer st;
		counts=new int[3]; // 개수 카운트 배열 -> -1,0,1 의 개수 저장 
		
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		} // 배열 생성 
		
		divide(0,0,N);
		for(int i=0;i<3;i++) System.out.println(counts[i]);

	}
	
	static void divide(int x,int y,int size) { // 분할정복 
		
		if(size==1) { // 기저조건 : 크기가 1*1 
			if(arr[x][y]==-1) counts[0]+=1;
			else if(arr[x][y]==0) counts[1]+=1;
			else counts[2]+=1;
			return;
		}
		if(check(x,y,size)) { // 한가지 숫자로 이루어져 있다면 true 반환하는 메서드 
			return; 
		}
		
		for(int i=0;i<3;i++) { // 9칸으로 나누어 정복 
			for(int j=0;j<3;j++) {
				divide(x+i*size/3,y+j*size/3,size/3);
			}
		}

	}
	
	static boolean check(int x,int y,int size) {
		int n=arr[x][y];
		boolean flag=true;
		
		// 한가지 숫자로 이루어져 있는지 확인 
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				if(arr[i][j]!=n) {
					flag=false;
					break;
				}
			}
			if(!flag) break;
		}
		
		if(flag) { // 한가지 숫자로 이루어져 있을 때 
			if(n==-1) counts[0]+=1;
			else if(n==0) counts[1]+=1;
			else counts[2]+=1;
		}
		return flag;
	}

}
