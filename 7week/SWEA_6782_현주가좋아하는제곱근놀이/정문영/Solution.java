import java.io.*;

public class Solution {
<<<<<<< HEAD
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= tc; testcase++) {
			long N = Long.parseLong(br.readLine());
			
			int answer = find(0, N);
			System.out.println("#" + testcase + " " + answer);
		}
	}
	
	static int find(int cnt, long num) {
		if (num == 2) {
			return cnt;
		}
		
		long natural = (long) Math.sqrt(num);
		if (natural*natural != num) {
			if (num != (natural+1)*(natural+1)) {	// 제곱이 될 때까지 1 더하기
				cnt += ((natural+1)*(natural+1) - num);
				num += ((natural+1)*(natural+1) - num);
			}
		}
		
		return find(cnt + 1, (long) Math.sqrt(num));
	}
=======
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= tc; testcase++) {
            long N = Long.parseLong(br.readLine());

            int answer = find(0, N);
            System.out.println("#" + testcase + " " + answer);
        }
    }

    static int find(int cnt, long num) {
        if (num == 2) {
            return cnt;
        }

        long natural = (long) Math.sqrt(num);
        if (natural * natural != num) {
            if (num != (natural + 1) * (natural + 1)) { // 제곱이 될 때까지 1 더하기
                cnt += ((natural + 1) * (natural + 1) - num);
                num += ((natural + 1) * (natural + 1) - num);
            }
        }

        return find(cnt + 1, (long) Math.sqrt(num));
    }
>>>>>>> d81f62ac1ad86d47f277bcf2541bac70ccd3b16e
}
