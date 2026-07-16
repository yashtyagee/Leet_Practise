class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n=nums.length;
        int[]a=new int[n];
        int pos=0;
        int neg=1;
        for(int num:nums){
            if(num>0){
                a[pos]=num;
                pos+=2;
            }
            else{
                a[neg]=num;
                neg+=2;
            }
        }
        return a;

        
    }
}