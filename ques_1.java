class Solution {
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long sum = 0;
        for (int i=n-2; i>=n/3; i-=2) {
            sum += nums[i];
        }
        return sum;
    }
}
