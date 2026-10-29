class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> helperStack = new Stack<>();
        int[] nextGreaterElement = new int[nums2.length];
        for(int i=nums2.length-1; i>=0; i--){
            int element = nums2[i];
            while(!helperStack.isEmpty()&& helperStack.peek() <= element){
                helperStack.pop();
            }
            if(helperStack.isEmpty()){
                nextGreaterElement[i] = -1;
            }
            else{
                nextGreaterElement[i]= helperStack.peek();
            }
            helperStack.push(element);
        }
        int[] result = new int[nums1.length];
        for(int i=0; i<nums1.length; i++){
            for(int j=0; j<nums2.length; j++){
                if(nums1[i] == nums2[j]){
                    result[i] = nextGreaterElement[j];
                    break;
                }
                
            }
        }
        return result;
    }
}