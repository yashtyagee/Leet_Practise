class Solution {
    public int maxSubArray(int[] nums) {
        int a=nums[0];
        int b=nums[0];
        for(int i=1;i<nums.length;i++){
            a=Math.max(nums[i],a+nums[i]);
            if(a>b){
                b=a;
            }
        }
        return b;
    }
}