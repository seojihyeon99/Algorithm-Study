package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * @author 류지원
 * 메모리 : 126884kb, 시간 : 596ms
 * 풀이방법 :
 * 위상정렬로 풀었다.
 * 진입차수가 0인 노드 먼저 큐에 넣어서 돌리면서 진출차수에 해당하는 노드들의 진입차수 수를 줄여나갔다.
 * 그리고 진입차수가 0이 되는 노드들이 생기면 다시 큐에 추가하는 방식의 위상정렬을 푸는 방식을 썼다.
 */

public class BJ_14567_선수과목_류지원 {
    static class Node{  // 인접리스트. 노드와 그 노드가 향하는 정점을 저장하는 클래스.
        int vertex; Node next;
        public Node(int vertex, Node next) {
            this.next = next;
            this.vertex = vertex;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer ST=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(ST.nextToken());  int M=Integer.parseInt(ST.nextToken());
        Node adjList[] = new Node[N];   // Node를 N개 생성
        for(int i=0; i<M; i++) {        // adjList 배열의 각 Node에 간선정보 입력하는 반복문
            ST = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(ST.nextToken()) - 1;
            int to = Integer.parseInt(ST.nextToken()) - 1;
            adjList[from] = new Node(to, adjList[from]);
        }
        int[] inDegreeArr=new int[N];   // 각 노드의 진입차수를 저장할 배열
        for(int j=0; j<N; j++) {         // 인접리스트로부터 각 노드의 진출차수를 파악하여 진입받는 노드의 진입차수를 카운팅함
            for (Node temp = adjList[j]; temp != null; temp = temp.next) {
                inDegreeArr[temp.vertex]++;
            }
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<N; i++) if(inDegreeArr[i]==0) queue.offer(i);
        // 2. queue에서 순차적으로 노드를 빼면서, 해당 노드와 다른 노드가 이어져있다면 진입받는 노드는 진입차수에서 1을 빼기.
        int cnt=1;
        int[] answer = new int[N];
        while(!queue.isEmpty()) {
            int size=queue.size();
            while(size>0) {
                int current = queue.poll();     // queue에서 노드번호 빼기.
                answer[current]=cnt;
                for (Node temp = adjList[current]; temp != null; temp = temp.next) {     // 해당 노드의 진출차수 탐색하는 배열
                    inDegreeArr[temp.vertex] -= 1;            // 진출차수는 곧 받는 쪽의 진입차수가 되므로 진입차수 -1 하기
                    if (inDegreeArr[temp.vertex] == 0) queue.offer(temp.vertex);       // 진입차수가 0이라면 queue에 추가
                }
                size-=1;
            }
            cnt++;
        }
        for(int ans : answer) System.out.print(ans+" ");
    }
}
