import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 이분탐색
 * sum을 long이 아닌 int 형으로 지정해줘서 계속 틀림 범위가 클때에는 항상 생각해야할 것 같다
 * min, max 값을 어떤 값으로 할 지 결정하고 , 필요에 따라 정렬하기
 *
 * 167816 kb, 492 ms
 */


public class bj_2805 {

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int min,max,mid;
        min=0;
        max=0;
        long sum=0;
        int[] arr=new int[N];
        st=new StringTokenizer(br.readLine()," ");

        for(int i=0;i<N;i++) { // 입력 받기, 최댓값 구하기
            arr[i]=Integer.parseInt(st.nextToken());
            if(arr[i]>max) max=arr[i];
        }

        while(true){

            if(min>max) break; // 탈출

            sum=0;
            mid=(min+max)/2;

            for(int i=0;i<N;i++){
                if(arr[i]>mid) sum+=arr[i]-mid;
            }
            if(sum>=M){ // 더 자르기
                min=mid+1;
            }
            else{ // 덜 자르기
                max=mid-1;
            }

        } // while

        System.out.println(max);
    }
}
