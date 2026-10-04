class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } 
            else if (c == ')') {
                minOpen--;
                maxOpen--;
            } 
            else { // '*'
                minOpen--;  // '*' acts as ')'
                maxOpen++;  // '*' acts as '('
            }

            // Too many ')' even after using '*' as '('
            if (maxOpen < 0) {
                return false;
            }

            // Minimum cannot be negative
            minOpen = Math.max(minOpen, 0);
        }

        // If minimum possible unmatched '(' is 0,
        // a valid arrangement exists.
        return minOpen == 0;
    }
}