class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        int maxVal = Arrays.stream(nums).max().getAsInt();

        boolean[] isPrime = new boolean[maxVal + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= maxVal; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= maxVal; j += i)
                    isPrime[j] = false;
            }
        }

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
            while(sz-->0) {
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
                    List<Integer> teleportTargets = primeToIndices.getOrDefault(val, Collections.emptyList());
                    for (int j : teleportTargets) {
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
