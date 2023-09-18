package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    /**
     * BJ 20291  파일 정리
     *
     * 문자열을 이용해서 풀이하는 문제입니다.
     * TreeMap을 사용해서 정렬하였습니다.
     *
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new TreeMap<>();

        // 파일의 수만큼 실행
        for (int i = 0; i < N; i++) {
            // 한글자씩 뜯어서 temp에 저장
            String[] temp = br.readLine().split("");
            // 스트링 빌더 초기화
            StringBuilder str = new StringBuilder();
            // 확장자명을 담을 변수
            String temp2 = "";
            // .의 index를 저장하기 위한 변수
            int count = 0;

            // 단어 길이만큼 탐색하다가
            for (int j = 0; j < temp.length; j++) {
                // . 을 발견하면 index를 저장해줌
                if (temp[j].equals(".")) {
                    count = j+1;

                    // 해당 index부터 끝까지의 문자를 더해주고
                    for (int k = count; k < temp.length; k++) {
                        str.append(temp[k]);
                    }
                    // temp2에 저장
                    temp2 = str.toString();
                    break;
                }
            }

            // map에 존재하면
            if (map.containsKey(temp2)) {
                // 불러와서 값을 1 더해줌
                map.put(temp2, map.get(temp2) + 1);
            } else {
                // 아니면 새로 더해줌
                map.put(temp2, 1);
            }
        }

        // entrySet()을 사용하여 출력
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
