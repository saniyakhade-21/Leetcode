class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1000000007L;

        // dp = number of distinct subsequences including empty subsequence
        long dp = 1;

        // last[c] stores the value of dp before the previous occurrence of c
        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            long newDp = (2 * dp % MOD - last[index] + MOD) % MOD;

            last[index] = dp;
            dp = newDp;
        }

        // Remove the empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}
