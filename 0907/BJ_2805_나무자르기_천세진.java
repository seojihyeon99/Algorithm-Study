package com.ssafy.sejin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2805. 나무 자르기
 * 억까 1. sum 범위 설정 (int -> long)
 * 메모리 : 167,824KB, 시간 : 472ms
 * @author 세진
 *
 */
public class BJ_2805_나무자르기_천세진 {
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] trees = new int[n];
        
        int maxTree = Integer.MIN_VALUE;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (trees[i] > maxTree) maxTree = trees[i];
        }
        
        int left = 1;
        int right = maxTree;
        int mid = 0;
        
        while (left <= right) {
            
            mid = (left + right) / 2;
            long current = 0;
            
            for (int i = 0; i < n; i++) {
                if (trees[i] < mid) continue;
                current += trees[i] - mid;
                if (current > m) break;
            }
            
            if (current > m) {
            	left = mid + 1;
            } else if (current < m) {
            	right = mid - 1;
            } else {
                System.out.println(mid);
                return;
            }
            
//            System.out.println(left + " " + right);
            
        }
        
        System.out.println(left - 1);
    }

}
