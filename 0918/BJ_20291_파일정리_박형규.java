import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <pre>
 * 파일을 확장자 별로 정리해서 몇 개씩 있는지 파악한다음, 확장자들을 사전 순으로 정렬해야한다.
 * </pre>
 * @author 박형규
 * 메모리 63,560 KB
 * 시간 848 ms
 *
 */
public class BJ_20291_파일정리_박형규 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 스트림
		StringBuilder sb = new StringBuilder(); // 출력 스트림
		int N = Integer.parseInt(br.readLine()); // 바탕화면에 있는 파일의 개수
		
		Map<String, Integer> map = new HashMap<>(); // 해시맵
		
		for (int i = 0; i < N; i++) { // N개의 줄에 바탕화면에 있는 파일의 이름이 주어짐
			StringTokenizer st = new StringTokenizer(br.readLine(), ".");
			st.nextToken();
			String extension = st.nextToken();
			
			map.put(extension, map.getOrDefault(extension, 0) + 1);
		}
		
		List<String> keys = new ArrayList<>(map.keySet()); // 확장자들
		Collections.sort(keys); // 사전순 정렬
		
		for (String key : keys) {
			sb.append(key).append(" ").append(map.get(key)).append("\n");
		}
		System.out.print(sb); // 출력
	}

}
