class Solution {
    public int maxArea(int[] height) {
        int l=0;
        int r=height.length-1;
        int max=0;
        while(l<r){
            int min=Math.min(height[l],height[r]);
            int width=r-l;
            max=Math.max(max,min*width);
            if(height[l]<height[r]){
                l++;
            }
            else{
                r--;
            }
            
        }
        return max;
        
        
    }
}