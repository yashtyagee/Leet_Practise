class Solution {
    public int maxProfit(int[] prices) {
        int minprice=prices[0];
        int maxProfit=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]-minprice>maxProfit){
                maxProfit=prices[i]-minprice;
            }
            if(prices[i]<minprice){
                minprice=prices[i];
            }
        }
        return maxProfit;
        
    }
}