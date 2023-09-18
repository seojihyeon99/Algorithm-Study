import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * substring, map 사용 방법 안 쓰니까 또 잊었는데 다시 상기시켜서 좋았다 ~
 * 	126184 kb, 696 ms
 */
public class Main {
    static HashMap<String,Integer> map;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());

        StringTokenizer st;
        String line;
        StringBuilder sb=new StringBuilder();
        map=new HashMap<>();

        for(int i=0;i<t;i++){
            line=br.readLine();
            int index=line.indexOf('.'); // 확장자 시작 위치
            String extension=line.substring(index+1);

            if(map.containsKey(extension)){ // 이미 추가된 확장자면 값 + 1
                map.replace(extension,map.get(extension)+1);
            }
            else{ // 새로운 확장자면 추가
                map.put(extension,1);
            }
        } // 확장자 저장 완료

        String[] keys=map.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        for(int i=0;i<keys.length;i++){
            sb.append(keys[i]+" "+map.get(keys[i])+"\n");
        }

        System.out.println(sb);
    }

}
