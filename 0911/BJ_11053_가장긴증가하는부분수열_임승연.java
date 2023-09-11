import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp[i]: i번째 수까지의 최장증가부분수열크기 저장
 * 메모리 12360 kb, 시간 96 ms
 */

public class bj_11053 {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[] arr=new int[n];
        int[] dp=new int[n];
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
        dp[0]=1;

        for(int i=1;i<n;i++){
            for(int j=i-1;j>=0;j--){ // 내 앞을 봄
                if(arr[j]<arr[i]){
                    if(dp[j]>=dp[i]) dp[i]=dp[j]+1; // 최장 길이 찾기
                }
            }
            if(dp[i]==0) dp[i]=1; // 나보다 작은 값이 없다면 1로 set
        }

        int max=0;
        for(int i=0;i<n;i++){
            if(dp[i]>max) max=dp[i];
        } // 최댓값 찾기
        System.out.println(max);
    }
}
