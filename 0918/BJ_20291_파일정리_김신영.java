import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * BJ_20291_파일정리 https://www.acmicpc.net/problem/20291
 * 80,952KB	680ms
 * ----- ----- -----
 * 문자열
 * map을 사용하여 풀이
 * </pre>
 * @author 김신영
 *
 */

public class BJ_20291_파일정리_김신영 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			int idx = str.lastIndexOf(".") + 1;
			String tmp = str.substring(idx);
			
			if(map.containsKey(tmp)) {
				map.replace(tmp, map.get(tmp)+1);
			} else {
				map.put(tmp, 1);
			}
		}
		
		List<String> list = new ArrayList<>(map.keySet());
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for(String s : list) {
			sb.append(s).append(" ").append(map.get(s)).append("\n");
		}
		System.out.println(sb.toString());
	}
	
}
