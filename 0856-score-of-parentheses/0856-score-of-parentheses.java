class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(0);
            } 
            else {
                int inside = stack.pop();
                int previous = stack.pop();

                if (inside == 0) {
                    stack.push(previous + 1);
                } 
                else {
                    stack.push(previous + 2 * inside);
                }
            }
        }

        return stack.pop();
    }
}