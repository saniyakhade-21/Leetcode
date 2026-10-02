class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, String s,
                           int open, int close, int n) {

        // Complete valid combination
        if (s.length() == 2 * n) {
            ans.add(s);
            return;
        }

        // Add opening bracket
        if (open < n) {
            backtrack(ans, s + "(", open + 1, close, n);
        }

        // Add closing bracket only when valid
        if (close < open) {
            backtrack(ans, s + ")", open, close + 1, n);
        }
    }
}