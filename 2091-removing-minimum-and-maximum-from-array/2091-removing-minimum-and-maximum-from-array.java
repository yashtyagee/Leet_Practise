class Solution {
    public int minimumDeletions(int[] nums) {
        int n=nums.length;
        if(n<=2) return n;
        int minIdx=0;
        int maxIdx=0;
        for(int i=1;i<n;i++){
            if(nums[i]<nums[minIdx]){
                minIdx=i;
            }
            if(nums[i]>nums[maxIdx]){
                maxIdx=i;
            }
        }
        int left=Math.min(minIdx,maxIdx);
        int right=Math.max(minIdx,maxIdx);
        int o1=right+1;
        int o2=n-left;
        int o3=(left+1)+(n-right);
        return Math.min(o1,Math.min(o2,o3));
    }
}