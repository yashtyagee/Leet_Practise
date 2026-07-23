class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n=nums.length;
        int mx=0;
        if(n<=2)return n;
        for(int num:nums){
            mx=Math.max(num,mx);
        }
        int msb=0;
        while((1<<msb)<=mx){
            msb++;
        }
        return (1<<msb);
        
    }
}