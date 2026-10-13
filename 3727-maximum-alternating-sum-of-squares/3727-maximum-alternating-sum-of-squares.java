import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        // abs ke hisaab se sort karo
        Integer[] a = new Integer[nums.length];
        for(int i=0;i<nums.length;i++) a[i]=nums[i];
        Arrays.sort(a, (x,y) -> Integer.compare(Math.abs(x), Math.abs(y)));

        int n = a.length;
        int l = 0, r = n-1;
        long score = 0;

        for(int i=0;i<n;i++){
            long val;
            if(i % 2 == 0){
                // even -> sabse bada chahiye
                val = a[r--];
            } else {
                // odd -> sabse chhota chahiye
                val = a[l++];
            }
            long sq = val * val;
            if(i % 2 == 0) score += sq;
            else score -= sq;
        }
        return score;
    }
}