import java.io.*;
import java.util.*;

public class Solution {
	static int N, cnt;
	static double answer;
	static int[] parent;
	static long[][] island;
	static double[][] ranges;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= tc; testcase++) {
			N = Integer.parseInt(br.readLine());
			island = new long[N][2];
			
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					island[j][i] = Long.parseLong(st.nextToken());
				}
			}
			
			double E = Double.parseDouble(br.readLine());
			
			
			parent = new int[N];
			for (int i = 0; i < N; i++) 
				parent[i] = i;
			
			ranges = new double[N *(N-1)/2][3];	// 섬1 인덱스, 섬2 인덱스, 거리
			calculateCost();
			
			Arrays.sort(ranges, (a, b) -> Double.compare(a[2], b[2]));
			
			cnt = 0;
			answer = 0.0;
			for (int i = 0; i < ranges.length; i++) {
				if (cnt == N-1)
					break;
				
				union((int) ranges[i][0], (int) ranges[i][1], ranges[i][2]);
			}
			
			answer *= E;
			System.out.println("#" + testcase + " " + Math.round(answer));	// 최소 환경 부담금을 소수 첫째 자리에서 반올림하여 정수 형태로 출력
		}
	}
	
	static void calculateCost() {
		int idx = 0;
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				ranges[idx][0] = i;
				ranges[idx][1] = j;
				ranges[idx][2] = Math.sqrt(Math.pow(island[i][0] - island[j][0], 2) + Math.pow(island[i][1] - island[j][1], 2));
				
				idx += 1;
			}
		}
	}
	
	static void union(int a, int b, double r) {
		int parent_a = find(a);
		int parent_b = find(b);
		
		if (parent_a != parent_b) {
			parent[parent_b] = parent_a;
			answer += Math.pow(r, 2);
			cnt += 1;	// 연결 개수 추가
		}
			
	}
	
	static int find(int idx) {
		if (parent[idx] == idx) 
			return idx;
		
		return parent[idx] = find(parent[idx]);
	}
}