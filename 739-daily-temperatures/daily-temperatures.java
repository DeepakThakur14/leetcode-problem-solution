class Solution {
    public int[] dailyTemperatures(int[] arr) {
        int[] a = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<arr.length; i++){
            while(!stack.isEmpty()&& arr[i]> arr[stack.peek()]){
                a[stack.peek()] = i-stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return a;
    }
}