public class Solution {
	public double findMaxAverage(int[] nums, int k) {
		int sum = 0;
    int cnt = 0;
    int max_sum = Integer.MIN_VALUE;
		
		
		for (int i=0; i<nums.length; i++) {
			sum += nums[i];
			cnt++;
			
			if (cnt >= k) {
				max_sum = Math.max(max_sum, sum);
				sum -= nums[i+1 - k];
				cnt--;
			}
		}
		
		return (double)max_sum / k; 
  }
}
