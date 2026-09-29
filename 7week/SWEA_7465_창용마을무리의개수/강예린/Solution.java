import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    private static int [] p;
    private static void union(int a, int b){
        int rootA= find(a);
        int rootB= find(b);

        if(rootA != rootB) p[rootA]= rootB;

    }

    private static int find(int node) {
        if (p[node]== node) return node;
        return p[node]= find(p[node]);
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int T= Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            st= new StringTokenizer(br.readLine());
            int N= Integer.parseInt(st.nextToken());
            int M= Integer.parseInt(st.nextToken());

            p= new int[N+1];
            for (int i = 1; i <= N; i++) p[i]= i;

            for (int i = 0; i < M; i++) {
                st= new StringTokenizer(br.readLine());
                int a=  Integer.parseInt(st.nextToken());
                int b=  Integer.parseInt(st.nextToken());

                union(a,b);
            }
            int cnt=0;
            for (int i = 1; i <= N; i++) if (i== p[i]) cnt++;

            sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}

