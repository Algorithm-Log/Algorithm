import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= tc; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			int answer = 0;
			
			int[][] arr = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 벌꿀 합 honey 리스트에 저장
			List<int[]> honey = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n-m+1; j++) {
					int[] tempHoney = new int[m];
					
					for (int k = 0; k < m; k++) {
						tempHoney[k] = arr[i][j+k];
					}
					
					maxSum = 0;
					find(tempHoney, 0, 0, 0);
					honey.add(new int[] {i, j, maxSum});
				}
			}
			
			for (int i = 0; i < honey.size(); i++) {
				for (int j = i+1; j < honey.size(); j++) {
					// 겹치는 벌꿀이 있는지 확인
					if (honey.get(i)[0] == honey.get(j)[0]) {
						if (honey.get(i)[1] <= honey.get(j)[1] 
								&& honey.get(j)[1] <= honey.get(i)[1] + m -1) 
							continue;
							
					}
					answer = Math.max(answer, honey.get(i)[2] + honey.get(j)[2]);
				}
			}
			
			System.out.println("#" + testcase + " " + answer);
			
		}
	}
	
	static int c;
	static int maxSum;
	
	// 연속된 m개의 벌꿀 중 최대합 구하기
	public static void find(int[] arr, int sum, int squareSum, int path) {
		//종료 조건
		if (path == arr.length) {
			if (sum <= c) {	// 벌꿀의 합이 c 이하인 경우에만 최대값 반영
				maxSum = Math.max(maxSum, squareSum);
			} 
			return;
		}
		
		find(arr, sum+arr[path], squareSum + arr[path]*arr[path], path+1);	// 벌꿀 선택
		find(arr, sum, squareSum, path+1);	// 벌꿀 미선택
		
		return;
	}
}