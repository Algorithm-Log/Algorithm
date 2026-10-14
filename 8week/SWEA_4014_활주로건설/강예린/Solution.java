import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    private static int[][] map;
    private static boolean[] used;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int T= Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            st= new StringTokenizer(br.readLine());
            int n= Integer.parseInt(st.nextToken());
            int x= Integer.parseInt(st.nextToken());

            map= new int[2*n][n];

            for (int i = 0; i < n; i++) {
                st= new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) map[i][j]= Integer.parseInt(st.nextToken());
            }

            // 세로도 가로로 봐서 코드 최적화
            for (int i = n; i < 2*n; i++) {
                for (int j = 0; j < n; j++) map[i][j]= map[j][i-n];
            }

            int cnt = getCnt(x);

            sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    } //end of main

    private static int getCnt(int x) {
        int n= map.length/2;
        int cnt=0;

        for (int i = 0; i < 2*n; i++) {
            int[] cur= map[i];
            used= new boolean[n];
            boolean makeRoad= true;
            for (int j = 0; j < n-1; j++) {
                int diff= Math.abs(cur[j]-cur[j+1]);

                // 평지는 패스
                if (diff==0) continue;

                // 높이 1 이상 패스
                if (diff>1) {
                    makeRoad= false;
                    break;
                }

                if(!canMakeRoad(i,j,cur[j]-cur[j+1], x)) {
                    makeRoad= false;
                    break;
                }

            }

            if(makeRoad) cnt++;
        }

        return cnt;
    }

    private static boolean canMakeRoad(int nx, int ny, int dy, int x) {
        if (dy==1) ny+= dy;

        if (!inside(ny)) return false;
        int now= map[nx][ny];

        for (int cnt = 0; cnt < x; cnt++) {
            if (!inside(ny) || used[ny]|| now != map[nx][ny]) {
                return false;
            }

            used[ny]= true;
            ny+= dy;
        }

        return true;
    }

    private static boolean inside(int y) {
        return y >= 0 && y < map[0].length;
    }


} // end of class