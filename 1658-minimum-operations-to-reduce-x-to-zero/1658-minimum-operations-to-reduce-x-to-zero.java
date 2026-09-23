class Solution {
    public int minOperations(int[] nums, int x) {
        int total=0;
        for(int i:nums){
            total+=i;
        }
        int need=total-x;
        if(need<0) return -1;
        if(need==0) return nums.length;
        int left=0;
        int sum=0;
        int minlen=-1;
        int n=nums.length;
        for(int right=0;right<nums.length;right++){
            sum+=nums[right];
            while(sum>need && left<=right){
                sum-=nums[left];
                left++;
            }
            if(need==sum){
                int len=right-left+1;
                if(len>minlen) minlen=len;
            }
        }
        if(minlen==-1) return minlen;
        return n-minlen;
    }
}