import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

    private static boolean inside(int x, int y, int n){
        return x>=0 && y>=0 && x< n && y< n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T= Integer.parseInt(br.readLine());
        StringBuilder sb= new StringBuilder();
        int[] dx= {0, 0, -1, 1};
        int[] dy= {-1, 1, 0, 0};


        for(int test_case = 1; test_case <= T; test_case++) {
            int n= Integer.parseInt(br.readLine());
            int[][] map= new int[n][n];
            int[][] dist= new int[n][n];
//            boolean[][] visited= new boolean[n][n];


            for (int i = 0; i < n; i++) {
                String s= br.readLine();
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                for (int j = 0; j < n; j++) {
                    map[i][j]= s.charAt(j)-'0';
                }
            }

            dist[0][0]= 0;

            // 배열 요소를 차례로 x, y, cost로 사용
            PriorityQueue<int[]> pq= new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
            pq.offer(new int[]{0,0,0});
            int x=0, y=0;

            while (!pq.isEmpty() && (x!= n-1 || y!= n-1)){
                int[] edge= pq.poll();
                x= edge[0];
                y= edge[1];

                for (int i = 0; i < dx.length; i++) {
                    int nx= x+ dx[i];
                    int ny= y+ dy[i];

                    if (!inside(nx,ny,n)) continue;

                    int newCost= dist[x][y]+ map[nx][ny];
                    if (dist[nx][ny]> newCost) {
                        dist[nx][ny]= newCost;
                        pq.add(new int[] {nx, ny, newCost});
                    }

                }

            }



            sb.append("#").append(test_case).append(" ").append(dist[n-1][n-1]).append("\n");

        } // end of testcases

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    } // end of main
}// end of class
