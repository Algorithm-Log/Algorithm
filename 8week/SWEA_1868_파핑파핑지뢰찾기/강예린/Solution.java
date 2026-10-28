import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    private static char[][] map;
    private static int[][] dp;
    private static int[] dx= {0,0,-1,1,-1,1,-1,1}; // 상하좌우랑 대각선들
    private static int[] dy= {-1,1,0,0,-1,1,1,-1};
    private static int cnt;

    private static boolean inside(int x, int y){
        return x>=0 && y>=0 && x< map.length && y< map.length;
    }

    private static int cntmine(int x, int y) {
        int cnt=0;
        int nx;
        int ny;

        for (int i = 0; i < dx.length; i++) {
            nx= x+ dx[i];
            ny= y+ dy[i];

            if (inside(nx,ny) && map[nx][ny]=='*') cnt++;
        }

        return cnt;
    }

    private static void bfs(int x, int y){
        cnt ++;
        Deque<int[]> zeros= new ArrayDeque<>();
        zeros.offerLast(new int[]{x,y});
        map[x][y]= '0';

        // 0 찾아서 연쇄적으로 숫자 채우기
        while (!zeros.isEmpty()){

            // 무조건 하나 꺼냄
            int[] zero= zeros.pollFirst();

            // 이 0의 근처 지뢰 개수 보여줌
            for (int i = 0; i < dx.length; i++) {
                int nx= zero[0]+ dx[i];
                int ny= zero[1]+ dy[i];

                if (inside(nx, ny) && map[nx][ny] == '.') {
                    map[nx][ny]= (char) ('0'+ dp[nx][ny]);
                    if (dp[nx][ny]==0) zeros.offerLast(new int[]{nx,ny});
                }

            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T= Integer.parseInt(br.readLine());
        StringBuilder sb= new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++) {
            int N= Integer.parseInt(br.readLine());
            map= new char[N][N];
            dp = new int[N][N];
            cnt=0;

            for (int i = 0; i < N; i++) map[i]= br.readLine().toCharArray();

            // dp 배열 생성
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j]=='.') dp[i][j]= cntmine(i,j);
                    else dp[i][j]= -1; // 지뢰는 -1
                }
            }

            // 연쇄 터트리는거부터
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dp[i][j]==0 && map[i][j]=='.') bfs(i,j);
                }
            }


            for (int i = 0; i < N; i++) {
                // 0으로 연쇄적으로 터지지 않는 애들은 다 클릭 해줘야함
                for (int j = 0; j < N; j++) if (map[i][j]=='.') cnt++;
            }

            sb.append("#").append(test_case).append(" ").append(cnt).append("\n");

        } // end of testcases

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    } // end of main
}// end of class
