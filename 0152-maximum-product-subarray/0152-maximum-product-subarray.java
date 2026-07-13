class Solution {
    public int maxProduct(int[] nums) {
        int n=nums.length;
        int maxpr=Integer.MIN_VALUE;
        int prefix=1;
        int suffix=1;
        for(int i=0;i<nums.length;i++){
            if(prefix==0) prefix=1;
            if(suffix==0) suffix=1;
            prefix*=nums[i];
            suffix*=nums[n-1-i];
            maxpr=Math.max(maxpr,Math.max(prefix,suffix));
        }
        return maxpr;
        
    }
}