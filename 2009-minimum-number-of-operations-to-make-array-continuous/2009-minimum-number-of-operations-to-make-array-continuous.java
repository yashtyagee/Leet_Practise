class Solution {
    public int minOperations(int[] nums) {
        int n=nums.length;
        int[] uniqueNums=Arrays.stream(nums).distinct().sorted().toArray();
        int m=0;
        int left=0;
        for(int right=0;right<uniqueNums.length;right++){
            while(uniqueNums[right]-uniqueNums[left]>=n){
                left++;
            }
            int currentsize=right-left+1;
            m=Math.max(m,currentsize);
        }
        return n-m;
    }
}