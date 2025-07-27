# Weekly-Contest-460
# 🧮 Maximum Median Sum of Subsequences of Size 3

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

### 🔍 Example 2:
**Input:**  
`nums = [1,1,10,10,10,10]`  
**Output:**  
`20`  

**Explanation:**  
- Step 1: Select `[1, 10, 10]` → median = 10  
- Step 2: Remaining `[1, 10, 10]` → median = 10  
- Total sum = `10 + 10 = 20`

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
