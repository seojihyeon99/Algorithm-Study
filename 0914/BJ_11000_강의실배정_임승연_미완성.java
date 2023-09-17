import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 강의실 배정
 * 시간초과 ㅠ ㅠ 
 */
public class Main {

    static ArrayList<ArrayList<int[]>> classes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] input=new int[2];
        int[] temp=new int[2];
        classes=new ArrayList<>();

        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]) return Integer.compare(o1[0],o2[0]);
                return Integer.compare(o1[1],o2[1]);
            }
        });

        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            pq.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        } // 입력 받아 pq 에 삽입


        // pq 에는 시작 시간 오름차순으로 정렬되어 있음
        while(!pq.isEmpty()){
            input=pq.poll();
            boolean flag=false;

            for(int i=0;i<classes.size();i++){
                temp=classes.get(i).get(0); // 더 일찍 시작한 수업

                if(temp[1]<=input[0]) {
                    classes.get(i).clear();
                    classes.get(i).add(new int[]{input[0],input[1]});
                    flag=true;
                    break;
                } // 넣을 수 있다면?
            }
            if(!flag){ // 강의실 추가
                classes.add(new ArrayList<>());
                classes.get(classes.size()-1).add(new int[]{input[0],input[1]});
//                System.out.println("size: "+classes.size());
            }
        }

        System.out.println(classes.size());
    }

}
