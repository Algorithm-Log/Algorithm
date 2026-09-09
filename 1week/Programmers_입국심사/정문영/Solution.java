import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long left = 0;
		long right = (long)times[0] * n;
		long answer = right;
		long mid = 0;
		long cnt = 0;
		
		while (left <= right) {
			mid = (left+right) / 2;
			
			for (int i=0; i<times.length; i++) {
				cnt += mid/times[i];
			}
			
			if (cnt < n) {
				left = mid + 1;
			} else {
				right = mid - 1;
				answer = Math.min(answer, mid);
			}
			
			cnt = 0;
		}
		
		return answer;
    }
}