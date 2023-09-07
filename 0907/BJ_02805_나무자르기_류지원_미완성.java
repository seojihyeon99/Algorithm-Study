package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02805_나무자르기_류지원_미완성 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer ST = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(ST.nextToken()), M=Integer.parseInt(ST.nextToken());
        int[] trees=new int[N];
        ST=new StringTokenizer(br.readLine());
        int MaxLength=0;
        for(int i=0; i<N; i++) {
            trees[i]=Integer.parseInt(ST.nextToken());
            MaxLength=Math.max(MaxLength, trees[i]);
        }


        for(int i=0; i<MaxLength; i++) {
            int sum=0;
            for(int tree : trees) {
                if(i<tree) sum += tree - i;
            }
            if(sum<=M) {
                System.out.println(i = (i==0) ? i : i-1);
                break;
            }
        }
    }
}
