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
### 🚀 Solution Idea

- Count total subsequences of `"LCT"` without insertion:
  - Use 3 counters:  
    - `countL`: counts `'L'`s  
    - `countLC`: counts subsequences `"LC"`  
    - `countLCT`: counts full subsequences `"LCT"`
- Precompute:
  - `suffixT[i]`: number of `'T'`s in `s[i:]`
  - `suffixCT[i]`: number of `"CT"` subsequences in `s[i:]`
- Try inserting each of `'L'`, `'C'`, `'T'` at every position and compute max gain:
  - Insert `'L'` at position `i`: it can form `"LCT"` with `suffixCT[i]`  
  - Insert `'C'` at position `i`: it can form `"LCT"` with `prefixL * suffixT[i]`  
  - Insert `'T'` at position `i`: it can form `"LCT"` with `prefixLC`  

Take the max of these insertions and add it to the original count.
---

### ✅ Code (Java)

```java
class Solution {
    public long numOfSubsequences(String s) {
        int n = s.length();
        long countL = 0;
        long countLC = 0;
        long countLCT = 0;
        for(char c:s.toCharArray()){
            if(c=='L') countL++;
            else if(c=='C') countLC+=countL;
            else if(c=='T') countLCT+=countLC;
        }

        long[] suffixT = new long[n+1];
        long[] suffixCT = new long[n+1];
        for(int i=n-1;i>=0;i--){
            char ch = s.charAt(i);
            suffixT[i]=suffixT[i+1];
            suffixCT[i]=suffixCT[i+1];
            if(ch=='T') suffixT[i]++;
            if(ch=='C') suffixCT[i]+=suffixT[i];
        }

        long extra = 0;
        long prefixL=0;
        long prefixLC=0;
        for(int i=0;i<=n;i++){
            extra = Math.max(extra, suffixCT[i]);             // insert 'L'
            extra = Math.max(extra, prefixL * suffixT[i]);    // insert 'C'
            extra = Math.max(extra, prefixLC);                // insert 'T'

            if(i < n){
                char ch = s.charAt(i);
                if(ch=='L') prefixL++;
                if(ch=='C') prefixLC+=prefixL;
            }
        }

        return countLCT + extra;
    }
}

```
⏱️ Complexity Analysis
Time Complexity: O(n)
We make a single pass (and a few extra loops) over the string s.

Space Complexity: O(1)
We use only constant extra variables.

# 🌀 Q3. Minimum Jumps to Reach End via Prime Teleportation

[🔗 LeetCode Problem (Premium)](https://leetcode.com/problems/minimum-jumps-to-reach-end-via-prime-teleportation)  
![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)
![Status](https://img.shields.io/badge/Status-Attempted-orange)
![Language](https://img.shields.io/badge/Language-Java-blue.svg)

---

## 📌 Problem Statement

You are given an integer array `nums` of length `n`.  
You start at index `0`, and your goal is to reach index `n - 1`.

From any index `i`, you may perform one of the following operations:

- **Adjacent Step**: Jump to index `i + 1` or `i - 1` (if within bounds).
- **Prime Teleportation**: If `nums[i]` is a prime number `p`, teleport to any index `j ≠ i` such that `nums[j] % p == 0`.

Return the **minimum number of jumps** required to reach index `n - 1`.

---

## 🧪 Examples

### Example 1:

**Input:** `nums = [1,2,4,6]`  
**Output:** `2`  
**Explanation:**  
- Step 1: `0 → 1`  
- Step 2: `1 → 3` via teleportation (2 divides 6)

---

### Example 2:

**Input:** `nums = [2,3,4,7,9]`  
**Output:** `2`  
**Explanation:**  
- Step 1: `0 → 1`  
- Step 2: `1 → 4` via teleportation (3 divides 9)

---

### Example 3:

**Input:** `nums = [4,6,5,8]`  
**Output:** `3`  
**Explanation:**  
No teleportation possible, move linearly → `0 → 1 → 2 → 3`

---

## 🚀 Approach

We use **Breadth-First Search (BFS)** starting from index `0`.

### Key Concepts:

- Precompute **prime numbers** using Sieve of Eratosthenes.
- Build a map from prime `p` to a list of indices `i` such that `nums[i] % p == 0`.
- From each index:
  - Try `i-1`, `i+1`.
  - If `nums[i]` is prime and we haven't used prime `p`, teleport to all indices where `nums[j] % p == 0`.

---

## ✅ Java Code

```java
class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        int maxVal = Arrays.stream(nums).max().getAsInt();

        // Sieve of Eratosthenes
        boolean[] isPrime = new boolean[maxVal + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= maxVal; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= maxVal; j += i)
                    isPrime[j] = false;
            }
        }

        // Mapping primes to indices
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int p = 2; p <= Math.sqrt(nums[i]); p++) {
                if (nums[i] % p == 0) {
                    if (isPrime[p]) {
                        primeToIndices.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
                    }
                    int other = nums[i] / p;
                    if (other != p && isPrime[other]) {
                        primeToIndices.computeIfAbsent(other, k -> new ArrayList<>()).add(i);
                    }
                }
            }
            if (isPrime[nums[i]]) {
                primeToIndices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
            }
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int steps = 0;

        Set<Integer> usedPrimes = new HashSet<>();

        while (!queue.isEmpty()) {
            int sz = queue.size();
            while (sz-- > 0) {
                int idx = queue.poll();
                if (idx == n - 1) return steps;

                for (int next : new int[]{idx - 1, idx + 1}) {
                    if (next >= 0 && next < n && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }

                int val = nums[idx];
                if (isPrime[val] && !usedPrimes.contains(val)) {
                    usedPrimes.add(val);
                    for (int j : primeToIndices.getOrDefault(val, Collections.emptyList())) {
                        if (!visited[j]) {
                            visited[j] = true;
                            queue.offer(j);
                        }
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}
```
⏱️ Complexity Analysis
Time Complexity:
O(n * sqrt(maxA) + maxA log log maxA)
where n = nums.length and maxA = max(nums[i]).
(Prime factorization + sieve + BFS)

Space Complexity:
O(n + maxA) for sieve, visited array, and map of primes.
