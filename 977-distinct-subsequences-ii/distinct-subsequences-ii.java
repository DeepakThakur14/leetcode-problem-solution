class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1000000007;

        long[] end = new long[26];
        long total = 0;

        for (char ch : s.toCharArray()) {
            int index = ch - 'a';

            long newSubsequences = (total + 1) % MOD;

            total = (total + newSubsequences - end[index] + MOD) % MOD;

            end[index] = newSubsequences;
        }

        return (int) total;
    }
}