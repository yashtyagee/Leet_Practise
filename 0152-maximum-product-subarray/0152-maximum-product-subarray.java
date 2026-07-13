class Solution {
    public int maxProduct(int[] nums) {
        int maxpr=Integer.MIN_VALUE;
        for(int left=0;left<nums.length;left++){
            int cp=1;
            for(int j=left;j<nums.length;j++){
                cp*=nums[j];
                maxpr=Math.max(maxpr,cp);

            }
        }
        return maxpr;
        
    }
}