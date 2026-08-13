class Solution{
    public int maximalRectangle(char[][] matrix){
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return 0;
        }
        int cols=matrix[0].length;
        int[] heights=new int[cols];
        int maxArea=0;

        for (char[] row:matrix){
            for(int col=0;col<cols;col++){
                if(row[col]=='1'){
                    heights[col]++;
                }
                else{
                    heights[col]=0;
                }
            }
            maxArea=Math.max(maxArea,largestRectangleArea(heights));
        }
        return maxArea;
    }
    private int largestRectangleArea(int[] heights){
        Deque<Integer>stack=new ArrayDeque<>();
        int maxArea=0;
        int n=heights.length;
        for(int i=0;i<= n;i++){
            int currentHeight=(i==n)?0:heights[i];
            while(!stack.isEmpty()&&currentHeight<heights[stack.peek()]){
                int height=heights[stack.pop()];
                int width=stack.isEmpty()?i:i-stack.peek()-1;
                maxArea=Math.max(maxArea,height*width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}