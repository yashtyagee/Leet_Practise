class Solution {
    int digitSum(int x){
        int s=0;
        while(x>0){ s+=x%10; x/=10; }
        return s;
    }
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] sorted = new Integer[n];
        for(int i=0;i<n;i++) sorted[i]=nums[i];
        
        Arrays.sort(sorted, (a,b)->{
            int da = digitSum(a), db = digitSum(b);
            if(da!=db) return da-db;
            return a-b;
        });
        Map<Integer,Integer> pos = new HashMap<>();
        for(int i=0;i<n;i++) pos.put(nums[i], i);

        boolean[] vis = new boolean[n];
        int swaps = 0;

        for(int i=0;i<n;i++){
            if(vis[i] || nums[i]==sorted[i]) continue;
            int cycle = 0;
            int j = i;
            while(!vis[j]){
                vis[j]=true;
                int val = sorted[j];
                j = pos.get(val);
                cycle++;
            }
            if(cycle>0) swaps += cycle-1;
        }
        return swaps;
    }
}