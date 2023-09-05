import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * union find 복습할 수 있어서 좋았다!
 *
 * 64348 kb, 500 ms
 *
 * @author 임승연
 */
public class Main {
    static int n,m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String[] temp=br.readLine().split(" ");
        n=Integer.parseInt(temp[0]);
        m=Integer.parseInt(temp[1]);
        int op; // 연산
        int a,b;
        StringBuilder sb=new StringBuilder();

        parent=new int[n+1];
        for(int i=0;i<=n;i++) parent[i]=i; // 집합 초기화

        for(int i=0;i<m;i++) {
            temp=br.readLine().split(" ");
            op=Integer.parseInt(temp[0]);
            a=Integer.parseInt(temp[1]);
            b=Integer.parseInt(temp[2]);

            if(op==0) union(a,b); // 집합 합치기
            else { // 같은 집합인지 확인
                if(find(a)==find(b)) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }

    static int find(int x) { // 루트노드 찾기
        if(x==parent[x]) return x;
        return parent[x]=find(parent[x]);
    }
    static void union(int a,int b) { // 합치기
        a=find(a);
        b=find(b);

        if(a!=b) {
            parent[b]=a;
        }
    }
}
