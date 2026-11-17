import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[][] houses;
	public static void main(String[] args) throws Exception {
		// 모든 집의 좌표 및 허용 거리 입력을 받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int answer = Integer.MAX_VALUE;
            // 집 개수
            N = Integer.parseInt(br.readLine());
            houses = new int [N][3];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                houses[i][0] = Integer.parseInt(st.nextToken());
                houses[i][1] = Integer.parseInt(st.nextToken());
                houses[i][2] = Integer.parseInt(st.nextToken());
            }
            // 충전소 설치 가능 위치 리스트
            List<int[]> elecChargers = new ArrayList<>();
            
            // 충전소 설치 가능 위치 리스트 저장
            for (int r = -15; r <= 15; r++) {
                for (int c = -15; c <= 15; c++) {
                    if (!canInstall(r, c)) continue;
            
                    elecChargers.add(new int[]{r, c});
                }
            }
            
            // 충전소 1개인 경우
            for (int e = 0; e < elecChargers.size(); e++) {
                int[] elecCharger = elecChargers.get(e);
                int r = elecCharger[0];
                int c = elecCharger[1];
                boolean inDistance = true;
                // 매 충전소 위치마다 거리 계산
                int distance = 0;
                for (int i = 0; i < N; i++) {
                    if (!isInDistance(r, c, houses[i][0], houses[i][1], houses[i][2])) {
                        inDistance = false;
                        break;
                    }
                    distance += Math.abs(r - houses[i][0]) + Math.abs(c - houses[i][1]);
                    
                }
                if (inDistance) answer = Math.min(distance, answer);
            } // end of 충전소 1개
            
            // 충전소 1개로 연결 가능하다면 값 출력하고 다음으로.
            if (answer != Integer.MAX_VALUE) {
                sb.append("#").append(tc).append(" ").append(answer).append("\n");
                continue;
            }
            
            // 충전소 2개
            for (int i = 0; i < elecChargers.size(); i++) {
                int[] elecCharger1 = elecChargers.get(i);
                for (int j = i+1; j < elecChargers.size(); j++) {
                    int[] elecCharger2 = elecChargers.get(j);
                    boolean inDistance = true;
                    int distance = 0;
                    for (int k = 0; k < N; k++) {
                        // 지금 충전소 위치에 집이 없는지 확인
                        if (!isInDistance(elecCharger1[0], elecCharger1[1], houses[k][0], houses[k][1], houses[k][2])
                            &&
                            !isInDistance(elecCharger2[0], elecCharger2[1], houses[k][0], houses[k][1], houses[k][2])
                            ) {
                            inDistance = false;
                            break;
                        }
                        distance += Math.min(
                            Math.abs(elecCharger1[0] - houses[k][0]) + Math.abs(elecCharger1[1] - houses[k][1])
                            ,
                            Math.abs(elecCharger2[0] - houses[k][0]) + Math.abs(elecCharger2[1] - houses[k][1])
                            );
                        
                    }
                    if (inDistance) answer = Math.min(distance, answer);
                }
            }
            
            if (answer == Integer.MAX_VALUE) answer = 1;
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb.toString());
	}
	
	// 충전소 거리 내에 집이 있는지 확인
	static boolean isInDistance(int r1, int c1, int r2, int c2, int d) {
        return (Math.abs(r1 - r2) + Math.abs(c1 - c2)) <= d;
    }
    
    // 충전소 설치 가능한지 확인
    static boolean canInstall(int r, int c) {
        for (int i = 0; i < N; i++) {
            if (houses[i][0] == r && houses[i][1] == c) {
                return false;
            }
        }
        return true;
    }
}