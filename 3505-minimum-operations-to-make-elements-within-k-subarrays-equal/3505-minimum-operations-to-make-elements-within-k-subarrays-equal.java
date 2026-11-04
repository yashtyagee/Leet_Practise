import java.util.*;

class Solution {
    // ---------- Sliding Median with 2 TreeMaps ----------
    static class SlidingMedian {
        TreeMap<Integer, Integer> low = new TreeMap<>(); // <= median, max side
        TreeMap<Integer, Integer> high = new TreeMap<>(); // > median
        long sumLow = 0, sumHigh = 0;
        int sizeLow = 0, sizeHigh = 0;

        void addTo(TreeMap<Integer,Integer> m, int v){ m.put(v, m.getOrDefault(v,0)+1); }
        void removeFrom(TreeMap<Integer,Integer> m, int v){
            int c = m.get(v);
            if(c==1) m.remove(v); else m.put(v, c-1);
        }
        void add(int v){
            if(low.isEmpty() || v <= low.lastKey()){
                addTo(low, v); sumLow+=v; sizeLow++;
            } else {
                addTo(high, v); sumHigh+=v; sizeHigh++;
            }
            rebalance();
        }
        void remove(int v){
            if(low.containsKey(v)){
                removeFrom(low, v); sumLow-=v; sizeLow--;
            } else {
                removeFrom(high, v); sumHigh-=v; sizeHigh--;
            }
            rebalance();
        }
        void rebalance(){
            // low should have (total+1)/2
            int total = sizeLow + sizeHigh;
            int needLow = (total + 1) / 2;
            while(sizeLow > needLow){
                int mv = low.lastKey();
                removeFrom(low, mv); sumLow-=mv; sizeLow--;
                addTo(high, mv); sumHigh+=mv; sizeHigh++;
            }
            while(sizeLow < needLow && !high.isEmpty()){
                int mv = high.firstKey();
                removeFrom(high, mv); sumHigh-=mv; sizeHigh--;
                addTo(low, mv); sumLow+=mv; sizeLow++;
            }
            // order fix: max(low) <= min(high)
            if(!low.isEmpty() && !high.isEmpty() && low.lastKey() > high.firstKey()){
                int a = low.lastKey(), b = high.firstKey();
                removeFrom(low, a); sumLow-=a; sizeLow--;
                removeFrom(high, b); sumHigh-=b; sizeHigh--;
                addTo(low, b); sumLow+=b; sizeLow++;
                addTo(high, a); sumHigh+=a; sizeHigh++;
            }
        }
        long cost(){
            int median = low.lastKey();
            long leftCost = (long)median * sizeLow - sumLow;
            long rightCost = sumHigh - (long)median * sizeHigh;
            return leftCost + rightCost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n]; // cost[i] = window [i, i+x-1]
        Arrays.fill(cost, Long.MAX_VALUE);

        SlidingMedian sm = new SlidingMedian();
        for(int i=0;i<x;i++) sm.add(nums[i]);
        cost[0] = sm.cost();
        for(int i=1;i+x-1 < n;i++){
            sm.remove(nums[i-1]);
            sm.add(nums[i+x-1]);
            cost[i] = sm.cost();
        }

        // DP: dp[i][t] = min cost using first i elements (0..i-1) to get t windows
        long INF = (long)4e18;
        long[][] dp = new long[n+1][k+1];
        for(int i=0;i<=n;i++) Arrays.fill(dp[i], INF);
        dp[0][0]=0;
        for(int i=0;i<n;i++){
            for(int t=0;t<=k;t++){
                if(dp[i][t]==INF) continue;
                // skip nums[i]
                dp[i+1][t] = Math.min(dp[i+1][t], dp[i][t]);
                // take window starting at i
                if(t < k && i + x <= n){
                    dp[i+x][t+1] = Math.min(dp[i+x][t+1], dp[i][t] + cost[i]);
                }
            }
        }
        return dp[n][k];
    }
}