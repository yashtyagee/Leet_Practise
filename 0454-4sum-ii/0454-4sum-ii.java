class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer,Integer>map=new HashMap<>();
        int count=0;
        for(int num:nums1){
            for(int num1:nums2){
                int sum=num+num1;
                map.put(sum,map.getOrDefault(sum,0)+1);
            }

        }
        for(int num:nums3){
            for(int num1:nums4){
                int sum=-(num+num1);
                count+=map.getOrDefault(sum,0);
            }
    
        }
        return count;
    }

}