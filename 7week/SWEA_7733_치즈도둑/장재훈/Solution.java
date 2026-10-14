import java.io.*;
import java.util.*;
 
public class Solution {
    static int[][] cheese;
    static boolean[][] visited;
    static int res;
    static int N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 치즈 배열 크기
 
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cheese = new int[N][N]; // 치즈 원본 생성
            int X = 0;
            res = 1;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    cheese[i][j] = Integer.parseInt(st.nextToken());
                    if (cheese[i][j] > X) X = cheese[i][j];
                }
            }
 
            int cnt = 1;
 
            while (cnt <= X) {
                visited = new boolean[N][N];
                int combine = 0;
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (cheese[r][c] > cnt && !visited[r][c]) {
                            // System.out.println("cheese[r][c] <= cnt && !visited[r][c]");
                            Node n = new Node(r, c);
                            bfs(n, cnt); // bfs돌리면서 visited 처리하고 다 돌면 카운트처리
                            combine++;
                        }
                    }
                }
                // 치즈덩어리 개수 비교
                if (res < combine) {
                    res = combine;
                    // System.out.println("res < combine");
                }
                cnt++;
            }
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        } // end of tc
        System.out.println(sb.toString());
    } // end of main
 
    public static class Node {
        int x;
        int y;
 
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
 
    static void bfs(Node n, int cnt) {
        // 방문을 기록할 노트
        Queue<Node> q = new ArrayDeque<>();
        // 시작 노드 집어 넣는다.
        q.offer(n);
        int r = n.x;
        int c = n.y;
        // 시작노드 방문처리
        visited[r][c] = true;
 
        while(!q.isEmpty()) {
            Node next = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = next.x+dr[i];
                int nc = next.y+dc[i];
                // 범위 가두기
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && cheese[nr][nc] > cnt && !visited[nr][nc]) {
                    q.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }
} // end of class