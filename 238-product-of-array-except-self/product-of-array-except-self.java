class Solution {
    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int []ans = new int[nums.length];
        int left = 1;
        int right = 1;
        for(int i=nums.length-1; i>=0; i--){
            ans[i] = right;
            right= right*nums[i];            
        }
        for(int i=0; i<nums.length; i++){
            ans[i] = ans[i]*left;
            left = left*nums[i];
        }
        return ans;
    }
}