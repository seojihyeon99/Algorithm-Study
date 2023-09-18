package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @author 박태호
 * 처음에 해쉬맵을 쓰려했는데 쓰는법 몰라서 알아보다가 TreeMap을 알게되어 사용했는데
 * 트리맵은 key를 기준으로 자동 오름차순 정렬이되기때문에 
 * 이 문제에 찰떡이라고 생각해서 사용했고
 * subString사용법 몰랐는데 이거쓰면 그냥 설정한 인덱스부터 딱 잘라버리는거같음
 * 
 *
 */
public class BJ_20291_파일정리_박태호 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		Map<String, Integer> map = new TreeMap<>();
		
		for (int i = 0; i < N; i++) {
			String in = bf.readLine();
			String result = in.substring(in.indexOf(".")+1);
			if(map.containsKey(result)) {
				int tmp = map.get(result);
				map.replace(result, ++tmp);
			}else {
				map.put(result, 1);
			}
		}
		for (String key : map.keySet()) {
			System.out.println(key+" "+map.get(key));
		}
		
		
	}
}
