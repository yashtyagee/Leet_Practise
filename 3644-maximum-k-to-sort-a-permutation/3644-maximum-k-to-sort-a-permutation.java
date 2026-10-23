class Solution {
    public int sortPermutation(int[] nums) {
        int n=nums.length;
        boolean sorted=true;
        for(int i=0;i<n;i++){
            if(nums[i]!=i){
                sorted=false;
                break;
            }
        }
        if(sorted) return 0;
        int total=-1;
        for(int i=0;i<n;i++){
            if(nums[i]!=i){
                int curr=i & nums[i];
                if(total==-1) total =curr;
                else total&=curr;
            }
        }
        return total==-1?0:total;
        
    }
}