# DSA Lab Codes — VIT-AP CSE2001

All 22 verified Java solutions from the DSA Lab FAT prep (Labs 1–7, 9–12), extracted from
`DSA-Lab-FAT-Prep-Questions-Answers.pdf`. Every solution was compiled and run against the
platform's sample inputs (49/49 passed). Each file's `public class` matches its filename.

## Labs

| Lab | Topic | Folder | Q1 | Q2 |
|-----|-------|--------|----|----|
| 1 | Arrays | `array/` | InterleaveHalves.java — interleave the two halves of an array | DeleteRowColumn.java — delete the row and column containing a value |
| 2 | Stack & Its Applications | `stack/` | PostfixEvaluator.java — evaluate a postfix expression with a stack | ReverseTopK.java — reverse the top K elements of a stack |
| 3 | Queues | `queue/` | ArrayQueue.java — array queue: sum and single-size groups left | DequeOperations.java — deque: front×rear product and count divisible by 5 |
| 4 | Linked List | `linked-list/` | DoublyLinkedListPalindrome.java — doubly linked list palindrome (mirrored pattern) check | CircularLinkedListMiddle.java — middle element of a circular linked list |
| 5 | Binary Trees & BST | `binary-trees/` | BSTMinMax.java — BST minimum and maximum | LeafNodeCount.java — count leaf nodes of a level-order binary tree |
| 6 | BST Traversals, AVL Trees | `avl-trees/` | AVLDeletion.java — AVL tree deletion with rebalancing, then inorder | BSTPreorder.java — BST preorder traversal of characters |
| 7 | Sorting | `sorting/` | QuickSortMultiplesOfThree.java — quick sort only the multiples of 3 | AlternatingSelectionSort.java — alternating-direction selection sort + parking efficiency score |
| 9 | Search Techniques | `searching/` | SumOfPreviousElements.java — element equal to sum of all previous elements | ClosestPairDifference.java — pair with difference closest to k (binary search) |
| 10 | Hashing | `hashing/` | LinearProbingRehash.java — hash table with linear probing and rehashing | AnagramGrouping.java — group anagrams with a hash table and separate chaining |
| 11 | Graphs | `graphs/` | DirectedCycleDetection.java — detect a directed cycle and print the cycle path | AdjacencyList.java — build an adjacency list, remove one edge, print it |
| 12 | Graph Algorithms | `graph-algorithms/` | DijkstraShortestPath.java — Dijkstra's shortest paths from a source | PrimsMST.java — Prim's MST from an adjacency matrix |

## Compile & run

```bash
cd array
javac InterleaveHalves.java
echo -e "4\n8 4 7 3" | java InterleaveHalves
# → 8 7 4 3
```

> Lab 8 is outside the FAT syllabus and is intentionally not included.
