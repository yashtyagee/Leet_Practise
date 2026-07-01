class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] add=new int[2*n];
        int k=0;
        for(int i=0;i<n;i++){
            add[k++]=nums[i];
            add[k++]=nums[i+n];
        }
        return add;
        
    }
}