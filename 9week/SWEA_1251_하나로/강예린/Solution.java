import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int T= Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N= Integer.parseInt(br.readLine());
            int [] pointsX= new int[N];
            int [] pointsY= new int[N];
            int[] dist= new int[N];
            boolean[] visited= new boolean[N];
            PriorityQueue<double[]> pq= new PriorityQueue<>(Comparator.comparingDouble(o -> o[0]));

            Arrays.fill(dist, Integer.MAX_VALUE);

            st= new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) pointsX[i]= Integer.parseInt(st.nextToken());

            st= new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) pointsY[i]= Integer.parseInt(st.nextToken());

            double E= Double.parseDouble(br.readLine());

            // 시작은 무조건 0 노드에서 하기
            pq.add(new double[]{0,0});
            int complete= 0;
            double costs= 0;

            while(complete < N && !pq.isEmpty()){

                // 하나씩 꺼내면서 방문
                double[] now= pq.poll();
                int point= (int) now[1];

                // 방문한 정점이면 이 간선을 버림
                if (visited[point]) continue;

                // 방문안해으면 방문하고 cost 더해주고 완료된 점 더해주기
                visited[point]= true;
                costs += now[0];

                complete++;

                // 현재에서 방문 안한 다른 모든 정점으로까지의 cost 를 PQ에 넣기
                for (int i = 0; i < N; i++) {
                    if(visited[i]) continue;
                    double cost= E * (Math.pow(pointsX[point]-pointsX[i],2) + Math.pow(pointsY[point]-pointsY[i],2));
                    pq.offer(new double[] {cost,i});
                }

            }
            sb.append("#").append(test_case).append(" ").append(Math.round(costs)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    } //end of main
} // end of class