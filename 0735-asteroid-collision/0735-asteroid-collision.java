class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer>stack=new Stack<>();
        for (int ast : asteroids) {
            boolean exploded = false;
            while(!stack.isEmpty()&&stack.peek()>0&&ast<0){
                if(stack.peek()<-ast){
                    stack.pop();
                    continue;
                }
                else if(stack.peek()==-ast){
                    stack.pop();
                }
                exploded = true;
                break;
            }
            if (!exploded){
                stack.push(ast);
            }
        }
        int[] result=new int[stack.size()];
        for (int i=stack.size()-1;i>=0;i--){
            result[i]=stack.pop();
        }
        return result;
    }
}