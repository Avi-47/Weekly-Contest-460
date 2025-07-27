# Weekly-Contest-460
# 🧮 Q1. Maximum Median Sum of Subsequences of Size 3

![LeetCode](https://img.shields.io/badge/LeetCode-Solved-brightgreen)
![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)
![Points](https://img.shields.io/badge/Points-4-Blue)

🔗 [View on LeetCode](https://leetcode.com/problems/maximum-median-sum-of-subsequences-of-size-3)

---

## 📝 Problem Description

You are given an integer array `nums` with a length divisible by 3.

You want to make the array empty in steps. In each step, you can select any **three elements** from the array, compute their **median**, and remove the selected elements from the array.

The **median** of an odd-length sequence is the **middle element** of the sequence when sorted in **non-decreasing** order.

Return the **maximum possible sum of the medians** computed from the selected elements.

---

### 🔍 Example 1:
**Input:**  
`nums = [2,1,3,2,1,3]`  
**Output:**  
`5`  

**Explanation:**  
- Step 1: Select `[3, 1, 3]` → median = 3  
- Step 2: Remaining `[2, 1, 2]` → median = 2  
- Total sum = `3 + 2 = 5`
---

## 📚 Constraints
- `1 <= nums.length <= 5 * 10⁵`  
- `nums.length % 3 == 0`  
- `1 <= nums[i] <= 10⁹`

---

## 🚀 Solution Idea

- Sort the array in non-decreasing order.
- To maximize the sum of medians:
  - Pick the **second-largest** of each triplet from the end.
  - Skip 2 elements at a time (largest, then middle is counted, then smallest).
- Use a loop from index `n-2` down to `n/3`, stepping by `-2`.
- Accumulate the values at those positions — these are the medians.

---

## ✅ Code (Java)

```java
class Solution {
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long sum = 0;
        for (int i = n - 2; i >= n / 3; i -= 2) {
            sum += nums[i];
        }
        return sum;
    }
}
```

# 🧩 Q2. Maximum Number of Subsequences After One Inserting

[🔗 LeetCode Problem](https://leetcode.com/problems/maximum-number-of-subsequences-after-one-inserting/)  
![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)
![Status](https://img.shields.io/badge/Status-Solved-brightgreen.svg)
![Language](https://img.shields.io/badge/Language-Java-blue.svg)

---

## 📌 Problem Statement

You are given a string `s` consisting of uppercase English letters.

You are allowed to insert **at most one uppercase English letter** at **any position** (including the beginning or end) of the string.

Return the **maximum number of "LCT" subsequences** that can be formed in the resulting string after at most one insertion.

A **subsequence** is a non-empty string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.

---

## 🔍 Examples

### Example 1:
**Input:** `s = "LMCT"`  
**Output:** `2`  
**Explanation:**  
Insert `'L'` at the beginning → `"LLMCT"`  
You get two `"LCT"` subsequences:
- L(0), C(3), T(4)
- L(1), C(3), T(4)

---
## 🚀 Solution Idea

We use a **greedy + prefix count** approach to track possible `LCT` subsequences and simulate all three possible insertions (`L`, `C`, `T`) to find which gives the best boost.

### Key Observations:
- `"LCT"` subsequence requires `'L'` before `'C'` before `'T'`.
- Maintain prefix counts:
  - `countL` = number of `'L'` seen so far
  - `countLC` = number of `L-C` subsequences
  - `countLCT` = number of `LCT` subsequences
- Simulate inserting one of:
  - `'L'`: boosts `C-T` subsequences
  - `'C'`: boosts `L-T` subsequences (`countL * remainingT`)
  - `'T'`: boosts `L-C` subsequences (`countLC`)

---

## ✅ Java Code

```java
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

            bestInsertC = Math.max(bestInsertC, countL * remainingT); // Insert C here
            bestInsertT = Math.max(bestInsertT, countLC);             // Insert T here
        }

        // Simulate inserting L: countC * countT
        long countC = 0, boostByL = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'C') countC++;
            else if (ch == 'T') boostByL += countC;
        }

        long result = countLCT;
        result = Math.max(result, countLCT + boostByL);      // Insert L
        result = Math.max(result, countLCT + bestInsertC);   // Insert C
        result = Math.max(result, countLCT + bestInsertT);   // Insert T

        return result;
    }
}
