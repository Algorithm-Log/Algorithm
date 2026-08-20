class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int cnt = 0;
        int max_sum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (cnt >= k) {
                sum += nums[i] - nums[i - k]; 
            } else {
                sum += nums[i];
                cnt++;
            }
            
            if (cnt >= k) {
                max_sum = Math.max(max_sum, sum); 
            }
        }

        return (double) max_sum / k;
    }
}
