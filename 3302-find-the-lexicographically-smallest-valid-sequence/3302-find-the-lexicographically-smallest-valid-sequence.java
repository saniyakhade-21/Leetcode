class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] last = new int[m];
        java.util.Arrays.fill(last, -1);

        // last[j] = latest index in word1 matching word2[j]
        int i = n - 1, j = m - 1;
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j))
                last[j--] = i;
            i--;
        }

        int[] ans = new int[m];
        boolean used = false;
        i = 0;
        j = 0;

        while (i < n && j < m) {
            // Exact match -> always take it
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j++] = i;
            }

            // Use our one allowed mismatch
            else if (!used && (j == m - 1 || i < last[j + 1])) {
                ans[j++] = i;
                used = true;
            }

            i++;
        }

        return j == m ? ans : new int[0];
    }
}