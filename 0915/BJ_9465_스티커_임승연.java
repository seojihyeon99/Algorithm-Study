import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 123424 kb , 768 ms
 *
 * 예제를 보고 때려 맞춘 느낌인데 ,,,
 * dp[i][j] = (i,j) 칸의 스티커를 사용할 때의 최대 점수
 * n>=3 부터는 대각선 앞에 위치한 스티커 뿐만 아니라, 한 칸 앞까지 비교를 해주어야 한다
 */
public class Main {

    static int[][] dp;
    static int[][] input;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        int n;
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<t;i++){ // t회 반복

            n=Integer.parseInt(br.readLine()); // 스티커의 수
            dp=new int[2][n];
            input=new int[2][n];

            for(int j=0;j<2;j++){
                st=new StringTokenizer(br.readLine()," ");
                for(int k=0;k<n;k++){
                    input[j][k]=Integer.parseInt(st.nextToken());
                }
            } // 입력 받기 완료

            dp[0][0]=input[0][0];
            dp[1][0]=input[1][0];
            if(n>=2){
                dp[0][1]=dp[1][0]+input[0][1];
                dp[1][1]=dp[0][0]+input[1][1];
            }
            for(int j=2;j<n;j++){
                dp[0][j]=Math.max(dp[1][j-1],dp[1][j-2])+input[0][j];
                dp[1][j]=Math.max(dp[0][j-1],dp[0][j-2])+input[1][j];
            }
            sb.append(Math.max(dp[0][n-1],dp[1][n-1])+"\n");

        } // tc 반복
        System.out.println(sb);
    }

}
