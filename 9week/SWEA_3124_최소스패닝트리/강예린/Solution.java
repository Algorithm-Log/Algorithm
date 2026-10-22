import java.io.*;
import java.util.*;

public class Solution {
    private static int[] parent;
    private static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int T= Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            long answer= 0;
            st= new StringTokenizer(br.readLine());
            int V= Integer.parseInt(st.nextToken());
            int E= Integer.parseInt(st.nextToken());

            parent= new int[V+1];
            rank= new int[V+1];

            List<int[]> list= new ArrayList<>();

            for (int i = 1; i <= V; i++) parent[i]=i;

            for (int i = 0; i < E; i++) {
                st= new StringTokenizer(br.readLine());
                int[] edge= new int[3];
                edge[0]= Integer.parseInt(st.nextToken());
                edge[1]= Integer.parseInt(st.nextToken());
                edge[2]= Integer.parseInt(st.nextToken());
                list.add(edge);
            }

            list.sort(Comparator.comparingInt(o -> o[2]));

            // 선택한 간선의 개수
            int cnt= 0;
            int idx=0;
            while (cnt < V-1 && idx< E){
                int[] edge= list.get(idx);
                if (union(edge[0], edge[1])) {
                    cnt++;
                    answer+= edge[2];
                }

                idx++;
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    } //end of main


    private static boolean union(int v1, int v2) {
        int p1= find(v1);
        int p2= find(v2);

        if (p1== p2) return false;

        if (rank[p1]> rank[p2]) parent[p2]= p1;
        else if (rank[p1] < rank[p2]) parent[p1]= p2;
        else {
            parent[p1]= p2;
            rank[p2] ++;
        }
        return true;
    }

    private static int find(int v) {
        if (parent[v]== v) return v;

        // v가 루트 아니면 v의 부모의 부모를 v의 부모로 - 경로압축
        return parent[v]= find(parent[v]);
    }

} // end of class