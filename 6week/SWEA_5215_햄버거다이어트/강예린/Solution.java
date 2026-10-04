import java.io.*;
public class Solution {
	
	static void makeBurger(int idx, int calSum, int valSum) {
		if(idx==n) {
			if(calSum<= k) {
				answer= Math.max(answer, valSum);
			}
			return;
		}
		
		makeBurger(idx+1, calSum+ cals[idx],valSum+values[idx]);
		makeBurger(idx+1, calSum,valSum);
	}
	
	static int[] cals;
	static int[] values;
	static int n;
	static int k;
	static int answer;
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T= Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++){
			answer=0;
			
			String[] s= br.readLine().split(" ");
			
			n= Integer.parseInt(s[0]);
			k= Integer.parseInt(s[1]);
			
			cals= new int [n];
			values= new int [n];
			
			for(int i=0; i<n; i++) {
				s= br.readLine().split(" ");
				values[i]= Integer.parseInt(s[0]);
				cals[i]= Integer.parseInt(s[1]);
			}
			
			makeBurger(0, 0, 0);

		
			bw.write("#"+test_case+" "+answer);
            bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
