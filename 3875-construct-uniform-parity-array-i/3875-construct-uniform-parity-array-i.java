class Solution {
    public boolean uniformArray(int[] nums1) {
        int e=0;
        int o=0;
        int n=nums1.length;
        if(n==1) return true;
        for(int num:nums1){
            if(num%2==0) e++;
            else o++;


        }
        return true;
    }
}