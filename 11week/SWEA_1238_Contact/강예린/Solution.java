import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        StringBuilder sb= new StringBuilder();

        for (int test_case = 1; test_case <= 10; test_case++) {
            st= new StringTokenizer(br.readLine());
            int m= Integer.parseInt(st.nextToken());
            int start= Integer.parseInt(st.nextToken());
            List<Integer>[] edges= new List[101];

            for (int i = 0; i < edges.length; i++) {
                edges[i]= new ArrayList<>();
            }

            st= new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()) {
                int from= Integer.parseInt(st.nextToken());
                int to= Integer.parseInt(st.nextToken());

                edges[from].add(to);
            }

            Deque<Integer> queue= new ArrayDeque<>();
            boolean[] visited= new boolean[101];

            visited[start]= true;
            queue.offer(start);
            int max = start;

            while (!queue.isEmpty()){
                max= Integer.MIN_VALUE;

                // queue 사이즈 변하기 때문에 size로 빼고 해야함
                int size= queue.size();

                // 지금 depth의 최대값구하기
                for (int i=0; i< size; i++){
                    int now= queue.poll();
                    max= Math.max(now, max);
                    for (int next: edges[now]){
                        if (!visited[next]){
                            visited[next]= true;
                            queue.offer(next);
                        }
                    }
                }


            }

            sb.append("#").append(test_case).append(" ").append(max).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    } //end of main
} // end of class
