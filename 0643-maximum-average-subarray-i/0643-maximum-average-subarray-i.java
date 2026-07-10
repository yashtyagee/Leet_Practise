class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double a=0.0;
        int l=0;
        long sum=0;

        for(int r=0;r<k;r++){
            sum+=nums[r];
            
        }
        long max=sum;
        for(int i=k;i<nums.length;i++){
            sum=sum-nums[i-k]+nums[i];
            max=Math.max(max,sum);

        }
        return(double)max/k;
        
        
    }
}