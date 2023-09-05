package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 류지원
 * 메모리 53132KB, 시간 1836ms
 * 풀이방법 :
 * 싸피에서 배운 서로소집합의 연산방식을 사용하였다.
 */

public class BJ_01717_집합의표현_류지원 {
    static int[] parents;
    public static void make(int n) {
        parents = new int[n+1];
        for(int i=1; i<n+1; i++) parents[i]=i;
    }
    public static int find(int a) {
        if(a==parents[a]) return a;
        return parents[a]=find(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aBoss=find(a);
        int bBoss=find(b);
        if (aBoss==bBoss) return false;
        parents[bBoss]=aBoss;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer ST = new StringTokenizer(br.readLine());
        int n=Integer.parseInt(ST.nextToken()), m=Integer.parseInt(ST.nextToken());
        make(n);
        for(int i=0; i<m; i++) {
            ST=new StringTokenizer(br.readLine());
            int ctrl = Integer.parseInt(ST.nextToken()), a = Integer.parseInt(ST.nextToken()), b = Integer.parseInt(ST.nextToken());
            if(ctrl==0) union(a,b);                 // 합집합
            else if(ctrl==1) {                      // 같은집합인지 찾기
                if(find(a)==find(b)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
}
