class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a, b)->a[1]-b[1]);
        int removed=0;
        int prevEnd=Integer.MIN_VALUE;
        for(int[] curr:intervals){
            if(curr[0]>=prevEnd){
                prevEnd=curr[1];
            }
            else{
                removed++;
            }
        }
        return removed;
    }
}