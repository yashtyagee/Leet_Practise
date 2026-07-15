class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int l=0;
        int[]num3=new int[nums1.length];
        HashMap<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<nums2.length;i++){
            map.put(nums2[i],i);
        }
        int b=0;
        for(int i=0;i<nums1.length;i++){
            int a=map.get(nums1[i]);
            int next=-1;
            for(int j=a+1;j<nums2.length;j++){
                if(nums2[j]>nums1[i]){
                    next=nums2[j];
                    break;
                }
            }
            num3[i]=next;
        }
        return num3;
    }
}