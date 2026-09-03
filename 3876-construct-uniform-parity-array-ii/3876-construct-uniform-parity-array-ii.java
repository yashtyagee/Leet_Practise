class Solution {
    public boolean uniformArray(int[] nums1) {
        int mine=Integer.MAX_VALUE;
        int mino=Integer.MAX_VALUE;
        int e=0;
        int o=0;
        for(int num:nums1){
            if((num & 1)==0){
                e++;
                mine=Math.min(mine,num);
            }
            else{
                o++;
                mino=Math.min(mino,num);
            }
        }
        return e==0||o==0|| mino<mine;
    }
}