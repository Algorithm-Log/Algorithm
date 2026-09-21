import java.io.*;
import java.util.*;

public class Solution {
	static int n,m,C,map[][];
	static int profit, pf[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			// 입력
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			profit = -1;
			// 일꾼 1
			for (int r = 0; r < n; r++) {
				for (int c = 0; c <= n-m; c++) {
					// 일꾼2
					for (int i = 0; i < n; i++) {
						for (int j = 0; j <= n-m; j++) {
							// 일꾼1이 선택한 자리와 겹치지 않게.
							if(r == i && j >= c && j < c+m) continue;
							if(r == i && j+m-1 >= c && j+m-1 < c+m) continue;
							
							pf = new int[2];
							// 일꾼1이 선택한 범위에서의 최댓값 구하기
							dfs(r, c, 0, 0, 0, 0);
							// 일꾼2가 선택한 범위에서의 최댓값 구하기
							dfs(i, j, 0, 0, 1, 0);
							// 더하기
							int sum = pf[0] + pf[1];
							
							// 최댓값 갱신
							if(sum > profit) {
								profit = sum;
							}
						}
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(profit).append('\n');
		}
		System.out.print(sb.toString());
	}
	private static void dfs(int r, int c, int sum, int pfSum, int idx, int depth) {
		// C를 넘었다면 return
		if(sum > C) return;
		
		// 최대값 갱신
		if(pfSum > pf[idx]) {
			pf[idx] = pfSum;
		}
		
		// 다 돌았다면 return
		if(depth == m) return;
		
		// 현재 위치 더한 경우
		dfs(r, c, sum + map[r][c+depth], pfSum + (map[r][c+depth]*map[r][c+depth]), idx, depth+1);
		
		// 현재 위치 더하지 않는 경우
		dfs(r, c, sum, pfSum, idx, depth+1);
	}
	
}
