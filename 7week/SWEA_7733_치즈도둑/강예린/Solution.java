import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    private static int[][] map;
    private static int[] dx= {0,0,1,-1};
    private static int[] dy= {-1,1,0,0};
    private static boolean[][] visited;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int T= Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            int answer= 1;
            N= Integer.parseInt(br.readLine());
            map= new int[N][N];
            int max= Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                st= new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j]= Integer.parseInt(st.nextToken());
                    max= Math.max(max, map[i][j]);
                }
            }

            for (int k = 1; k <= max; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        map[i][j] --;
                    }
                }

                answer= Math.max(answer, cntGroup());
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    } //end of main

    private static int cntGroup() {
        visited= new boolean[N][N];
        int cnt=0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j]>0){
                    cnt++;
                    dfs(i,j);
                }
            }
        }

        return cnt;
    }

    private static void dfs(int i, int j) {
        for (int k = 0; k < dx.length; k++) {
            int x= i+dx[k];
            int y= j+dy[k];

            if (inside(x,y) && !visited[x][y]&& map[x][y]>0) {
                visited[x][y]= true;
                dfs(x,y);
            }
        }
    }

    private static boolean inside(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

} // end of class
