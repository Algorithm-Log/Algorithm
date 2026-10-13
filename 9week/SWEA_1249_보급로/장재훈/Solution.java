import java.io.*;
import java.util.*;
 
public class Solution {
    static int[][] map;
    static int N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] dist;
    static final int INF = Integer.MAX_VALUE;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
             
            for (int i = 0; i < N; i++) {
                char[] list = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    map[i][j] = list[j] - '0';
                }
            }// end of map 초기화
             
            // 가중치를 계산해서 가장 적은 것들만 선택해서 최종적으로 도달하기 위해 메모하는 큐
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[2], b[2])
            );
             
            // 위치별 최소 누적 비용 저장
            dist = new int[N][N];
            for (int r = 0; r < N; r++) {
                Arrays.fill(dist[r], INF);
            }
             
            dist[0][0] = 0; // 시작위치 초기화
             
            // 시작 값 집어넣음
            pq.offer(new int[]{0, 0, 0});
             
            while(!pq.isEmpty()) {
                int[] vertex = pq.poll();
                int x = vertex[0];
                int y = vertex[1];
                int weight = vertex[2];
                if (weight > dist[x][y]) continue; // 해당 위치의 값이 최소인지 먼저 확인
                 
                for (int d = 0; d < 4; d++) {
                    int nr = x + dr[d];
                    int nc = y + dc[d];
                     
                    if (!isinRange(nr, nc)) continue; // 범위 내인지 확인
                     
                    // 누적 값 업데이트
                    if (dist[nr][nc] > dist[x][y] + map[nr][nc]) {
                        dist[nr][nc] = dist[x][y] + map[nr][nc];
                        pq.offer(new int[]{nr, nc, dist[nr][nc]}); // pq에 저장되는 값들은 다음 위치와 다음 위치의 최소 거리!
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(dist[N-1][N-1]).append("\n");
        } // end of tc
        System.out.print(sb.toString());
    }// end of main
     
    static boolean isinRange(int r, int c) {
        return (r >= 0 && r < N && c >= 0 && c < N);
    }
}