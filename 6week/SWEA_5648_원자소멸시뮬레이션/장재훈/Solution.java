import java.io.*;
import java.util.*;

public class Solution {

    // 상 하 좌 우
    public static int[] dr = {1, -1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());

            // 원자들을 저장할 배열
            Atom[] atoms = new Atom[N];

            for (int i = 0; i < N; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                // 0.5초 단위의 충돌을 처리하기 위해 좌표 2배
                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;

                int dir = Integer.parseInt(st.nextToken()); // 원자의 방향
                int energy = Integer.parseInt(st.nextToken()); // 원자의 자체 에너지

                atoms[i] = new Atom(x, y, dir, energy);
            }

            int totalenergy = 0;

            // 현재 생존한 원자 수
            int aliveCnt = N;

            // 모든 원자가 소멸하거나 범위를 벗어날 때까지 반복
            while (aliveCnt > 0) {

                // 현재 시간에 좌표별 원자 개수를 저장
                HashMap<Long, Integer> map = new HashMap<>();

                // 1. 모든 원자 이동
                for (int i = 0; i < N; i++) {

                    Atom a = atoms[i];

                    // 이미 소멸한 원자는 제외
                    if (!a.alive) continue;

                    // 0.5초 이동
                    a.x += dc[a.dir];
                    a.y += dr[a.dir];

                    // 범위를 벗어난 원자 제거
                    if (a.x < -2000 || a.x > 2000 ||
                        a.y < -2000 || a.y > 2000) {

                        a.alive = false;
                        aliveCnt--;

                        continue;
                    }

                    // 현재 원자가 존재하는 좌표
                    long key = getKey(a.x, a.y);

                    // 해당 좌표의 원자 개수 증가
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }

                // 2. 충돌 검사 및 에너지 누적
                for (int i = 0; i < N; i++) {

                    Atom a = atoms[i];

                    if (!a.alive) continue;

                    long key = getKey(a.x, a.y);

                    // 동일한 좌표에 원자가 2개 이상 존재하면 충돌
                    if (map.get(key) >= 2) {

                        totalenergy += a.energy;

                        a.alive = false;
                        aliveCnt--;
                    }
                }
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(totalenergy)
              .append("\n");
        }

        System.out.print(sb);
    }

    // x, y 좌표를 하나의 long 값으로 변환
    public static long getKey(int x, int y) {

        return ((long) x << 32) | (y & 0xffffffffL);
    }

    // 원자 클래스
    public static class Atom {

        int x;
        int y;
        int dir;
        int energy;

        boolean alive;

        public Atom(int x, int y, int dir, int energy) {

            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;

            this.alive = true;
        }
    }
}