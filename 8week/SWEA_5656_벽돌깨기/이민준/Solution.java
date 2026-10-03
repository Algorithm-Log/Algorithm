package swea.test.벽돌깨기;

import java.io.*;
import java.util.*;

public class Solution {
	static int n, w, h, max;
	static char[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 구슬 개수
			w = Integer.parseInt(st.nextToken()); // 가로 길이
			h = Integer.parseInt(st.nextToken()); // 세로 길이
			map = new char[w][h];				  // map
			
			// 입력
			for (int i = 0; i < w; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < h; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			}
			
			max = 0;
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					if(map[i][j] == '0') continue;
					dfs(i, j, 0);
				}
			}
			
		}
	}
	
	// 좌표(r,c)와 구슬 사용 개수(0부터 시작)
	private static void dfs(int r, int c, int cnt) {
		if(cnt == n) { // 0부터 시작해서 ==으로 기저조건 진행
			if(max < cnt) { // max 값 비교
				max = cnt;
			}
			
			return;
		}
		
		// 벽돌 깨트리기
		brokenBricks(r,c);
		
		// 순차적으로 어디로 갈 지 다시 선정
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if(map[i][j] == '0') continue;
				dfs(i, j, cnt + 1);
			}
		}
		
		// 벽돌 되돌리기
	}

	// 벽돌 안의 숫자만큼 깨트리는 메서드.
	private static void brokenBricks(int r, int c) {
		
	}

}
