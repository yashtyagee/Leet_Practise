class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum=0;
        long cSum=0;
        int l=0;
        HashSet<Integer> set=new HashSet<>();
        for(int r=0;r<nums.length;r++){
            while(set.contains(nums[r])){
                set.remove(nums[l]);
                cSum-=nums[l];
                l++;
            }
            set.add(nums[r]);
            cSum+=nums[r];
            if(r-l+1>k){
                set.remove(nums[l]);
                cSum-=nums[l];
                l++;

            }
            if(r-l+1==k){
                maxSum=Math.max(maxSum,cSum);
            }
        }
        return maxSum;
    }
}