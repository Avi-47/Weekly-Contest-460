class Solution {
    public long numOfSubsequences(String s) {
        long countL = 0, countLC = 0, countLCT = 0;
        long totalT = 0;

        for (char ch : s.toCharArray()) {
            if (ch == 'T') totalT++;
        }

        long bestInsertC = 0;
        long bestInsertT = 0;
        long remainingT = totalT;

        for (char ch : s.toCharArray()) {
            if (ch == 'L') {
                countL++;
            } else if (ch == 'C') {
                countLC += countL;
            } else if (ch == 'T') {
                countLCT += countLC;
                remainingT--; 
            }

            bestInsertC = Math.max(bestInsertC, countL * remainingT);

            bestInsertT = Math.max(bestInsertT, countLC);
        }

        long countC = 0, boostByL = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'C') countC++;
            else if (ch == 'T') boostByL += countC;
        }

        long result = countLCT;
        result = Math.max(result, countLCT + boostByL);      
        result = Math.max(result, countLCT + bestInsertC);   
        result = Math.max(result, countLCT + bestInsertT);   

        return result;
    }
}
