class Solution {
    public int sumCounts(List<Integer> nums) {
        int m=nums.size();
        int ans=0;
        for(int i=0;i<m;i++){
            Set<Integer> set=new HashSet<>();
            for(int j=i;j<m;j++){
                set.add(nums.get(j));
                int cnt=set.size();
                ans+=cnt*cnt;
            }
        }
        return ans;
    }
}