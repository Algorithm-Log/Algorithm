import java.io.*;
import java.util.*;
 
public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] land;
    static boolean[][] visited;
    // 파이프의 각 방향이 뚫려있을 때, 연결할 수 있는 파이프들
    static int[] up = {1,2,5,6};
    static int[] down = {1,2,4,7};
    static int[] left = {1,3,4,5};
    static int[] right = {1,3,6,7};
    static int N;
    static int M;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken())+1;
            M = Integer.parseInt(st.nextToken())+1;
            int R = Integer.parseInt(st.nextToken())+1; // 시작 위치 x
            int C = Integer.parseInt(st.nextToken())+1; // 시작 위치 y
            int L = Integer.parseInt(st.nextToken()); // 소요 시간
             
            land = new int[N][M]; // 테두리를 한바퀴 두름
            visited = new boolean[N][M]; // 해당 파이프 방문 여부 체크
            // 땅 생성
            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < M; j++) {
                    land[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            int res = bfs(L, R, C);
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.print(sb.toString());
    }
     
    public static int bfs(int breadth, int x, int y) {
        int res = 0;
        Queue<Node> q = new ArrayDeque<>();
        // 시작 파이프 집어 넣음
        q.offer(new Node(x, y, land[x][y]));
        visited[x][y] = true;
        res += 1;
        int time = 1;
         
        while(!q.isEmpty()) {
            // 목표만큼 너비 탐색 했으면 종료
            if (time >= breadth) break;
             
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Node n = q.poll();
                // 현재 노드의 사방면을 조사
                for (int i = 0; i < 4; i++) {
                    // 지금 노드의 파이프와 사방면 각각의 파이프가 매칭이 가능한지
                    int nr = n.x+dr[i];
                    int nc = n.y+dc[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }
                    // 연결된 위치의 파이프
                    int nextPipe = land[nr][nc];
                    if (isConnected(n.pipe, nextPipe, i) && !visited[nr][nc]) {
                        q.offer(new Node(nr, nc, land[nr][nc]));
                        visited[nr][nc] = true;
                        res+=1;
                    }
                }   
            }
            time++;
        }
         
        return res;
    }
     
    public static boolean isConnected(int pipeA, int pipeB, int direct) {
        if (pipeB == 0) return false;
         
        if (pipeA == 1) {
            // 상
            if (direct == 0) {
                for (int i : up) {
                    if (i == pipeB) return true;
                }
            }
            // 하
            else if (direct == 1) {
                for (int i : down) {
                    if (i == pipeB) return true;
                }
            }
            // 좌
            else if (direct == 2) {
                for (int i : left) {
                    if (i == pipeB) return true;
                }
            }
            // 우
            else {
                for (int i : right) {
                    if (i == pipeB) return true;
                }
            }
        }
        else if (pipeA == 2) {
            // 상
            if (direct == 0) {
                for (int i : up) {
                    if (i == pipeB) return true;
                }
            }
            // 하
            if (direct == 1) {
                for (int i : down) {
                    if (i == pipeB) return true;
                }
            }
            return false;
        }
        else if (pipeA == 3) {
            // 좌
            if (direct == 2) {
                for (int i : left) {
                    if (i == pipeB) return true;
                }
            }
            // 우
            if (direct == 3) {
                for (int i : right) {
                    if (i == pipeB) return true;
                }
            }
            return false;
        }
        else if (pipeA == 4) {
            // 상
            if (direct == 0) {
                for (int i : up) {
                    if (i == pipeB) return true;
                }
            }
            // 우
            if (direct == 3) {
                for (int i : right) {
                    if (i == pipeB) return true;
                }
            }
            return false;
        }
        else if (pipeA == 5) {
            // 하
            if (direct == 1) {
                for (int i : down) {
                    if (i == pipeB) return true;
                }
            }
            // 우
            if (direct == 3) {
                for (int i : right) {
                    if (i == pipeB) return true;
                }
            }
            return false;
        }
        else if (pipeA == 6) {
            // 하
            if (direct == 1) {
                for (int i : down) {
                    if (i == pipeB) return true;
                }
            }
            // 좌
            if (direct == 2) {
                for (int i : left) {
                    if (i == pipeB) return true;
                }
            }
            return false;
        }
        else if (pipeA == 7) {
            // 상
            if (direct == 0) {
                for (int i : up) {
                    if (i == pipeB) return true;
                }
            }
            // 좌
            if (direct == 2) {
                for (int i : left) {
                    if (i == pipeB) return true;
                }
            }
            return false;
        }
        return false;
    }
     
    public static class Node {
        int x;
        int y;
        int pipe;
         
        public Node(int x, int y, int pipe) {
            this.x = x;
            this.y = y;
            this.pipe = pipe;
        }
    }
}