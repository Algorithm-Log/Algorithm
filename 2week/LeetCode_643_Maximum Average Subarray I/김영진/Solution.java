class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int answer = Integer.MIN_VALUE;
        int len = nums.length;
        int lt = 0;
        int rt = k - 1;
        int sum = 0;

        while(rt == len){
            for(int i = lt; i < rt; i++){
                sum += nums[i];
            }
            answer = Math.max(answer, sum);
            rt++;
        }
        return (double)answer / k;
    }
}