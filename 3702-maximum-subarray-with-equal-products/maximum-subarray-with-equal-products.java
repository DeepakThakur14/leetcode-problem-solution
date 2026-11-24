class Solution {

    public int maxLength(int[] nums) {

        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {

            long product = 1;
            int gcd = 0;
            int lcm = 1;

            for (int j = i; j < n; j++) {

                product *= nums[j];

                gcd = findGCD(gcd, nums[j]);

                lcm = (lcm / findGCD(lcm, nums[j])) * nums[j];

                if (product == (long) gcd * lcm) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }

                // Product cannot decrease.
                if (product > 25200) {
                    break;
                }
            }
        }

        return maxLen;
    }

    private int findGCD(int a, int b) {

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}