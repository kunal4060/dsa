 import java.util.Scanner;

 public class ClosestPairDifference {
     // Own absolute value - Math is banned in this question.
     static int abs(int x) {
         return x < 0 ? -x : x;
     }

      // Binary search: first position in sorted[0..n-1] whose value >= target.
      // Returns n if every value is smaller than target.
      static int lowerBound(int[] sorted, int n, int target) {
          int low = 0;
          int high = n; // high is exclusive
          while (low < high) {
              int mid = (low + high) / 2;
              if (sorted[mid] < target) {
                  low = mid + 1;
       } else {
           high = mid;
       }
    }
    return low;
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
        a[i] = sc.nextInt();
    }
    int k = sc.nextInt();

    if (n < 2) {
        return; // no pair exists
    }

    // Sorted COPY of the values with their original indices.
    // Own sort (insertion sort) - Arrays.sort is banned.
    int[] sortedVal = new int[n];
    int[] sortedIdx = new int[n];
    for (int i = 0; i < n; i++) {
        sortedVal[i] = a[i];
        sortedIdx[i] = i;
    }
    for (int i = 1; i < n; i++) {
        int v = sortedVal[i];
        int id = sortedIdx[i];
        int j = i - 1;
        while (j >= 0 && sortedVal[j] > v) {
            sortedVal[j + 1] = sortedVal[j];
            sortedIdx[j + 1] = sortedIdx[j];
            j--;
        }
        sortedVal[j + 1] = v;
        sortedIdx[j + 1] = id;
    }

    int bestCloseness = -1; // | |a[i]-a[j]| - k |, smaller is better
    int bestP = -1;         // smaller index of the best pair
    int bestQ = -1;         // larger index of the best pair

    for (int i = 0; i < n; i++) {
        // For a pair with difference exactly k we would need a partner
        // equal to a[i] + k or a[i] - k. Binary-search for both targets.
        int[] targets = {a[i] + k, a[i] - k};
        for (int t = 0; t < 2; t++) {
            int pos = lowerBound(sortedVal, n, targets[t]);
            // The value closest to the target sits at pos or just
            // before it; check a small window around pos so that
            // duplicate values and the element itself never hide
            // the true nearest neighbour.
            for (int p = pos - 2; p <= pos + 2; p++) {
                if (p < 0 || p >= n) {
                    continue;
                }
                             int j = sortedIdx[p];
                             if (j == i) {
                                 continue; // a pair needs two different elements
                             }
                             int diff = abs(a[i] - a[j]);
                             int closeness = abs(diff - k);
                             int first = i < j ? i : j;   // pair in original order
                             int second = i < j ? j : i;
                             if (bestCloseness == -1
                                     || closeness < bestCloseness
                                     || (closeness == bestCloseness
                                         && (first < bestP
                                             || (first == bestP && second < bestQ)))) {
                                 bestCloseness = closeness;
                                 bestP = first;
                                 bestQ = second;
                             }
                       }
                 }
             }

             System.out.println(a[bestP] + " " + a[bestQ]);
        }
  }
