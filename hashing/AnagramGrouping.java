  import java.util.Scanner;

  // One anagram group: the words in it, and their shared canonical signature
  // (the letters of any word in the group, sorted alphabetically).
  class Group {
      String[] words = new String[15];   // at most n words, n <= 15
      int wordCount = 0;
      char[] signature;                  // sorted letters, e.g. "eat" -> "aet"
      int sigLen = 0;
  }

  public class AnagramGrouping {

      // Sort the letters of a word alphabetically (simple bubble sort).
      // Returns the signature as a char array.
      static char[] signatureOf(String word) {
          char[] letters = word.toCharArray();
          for (int i = 0; i < letters.length - 1; i++) {
              for (int j = 0; j < letters.length - 1 - i; j++) {
                  if (letters[j] > letters[j + 1]) {
                      char temp = letters[j];
               letters[j] = letters[j + 1];
               letters[j + 1] = temp;
           }
       }
    }
    return letters;
}

// Compare two signatures char by char (no library equality call).
static boolean sameSignature(char[] a, char[] b) {
    if (a.length != b.length) {
        return false;
    }
    for (int i = 0; i < a.length; i++) {
        if (a[i] != b[i]) {
            return false;
        }
    }
    return true;
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String[] words = new String[n];
    for (int i = 0; i < n; i++) {
        words[i] = sc.next();
    }
    sc.close();

    int tableSize = 50;

    // Separate chaining, built by hand: each bucket holds a small fixed
    // array of the groups that hashed into it, in creation order.
    Group[][] chains = new Group[tableSize][15];
    int[] chainCount = new int[tableSize];

    // All groups in creation order (order of first appearance), for printing.
    Group[] allGroups = new Group[15];
    int groupCount = 0;

    for (int i = 0; i < n; i++) {
        String word = words[i];

       // Hash = (sum of ASCII codes of the word) % 50.
       int sum = 0;
       for (int j = 0; j < word.length(); j++) {
           sum += word.charAt(j);
       }
       int bucket = sum % tableSize;

       char[] sig = signatureOf(word);

       // Search this bucket's chain for a group with the same signature.
       Group found = null;
       for (int j = 0; j < chainCount[bucket]; j++) {
           if (sameSignature(chains[bucket][j].signature, sig)) {
               found = chains[bucket][j];
               break;
                    }
              }

              if (found == null) {
                  // No matching group: create a new one in this bucket's chain.
                  found = new Group();
                  found.signature = sig;
                  found.sigLen = sig.length;
                  chains[bucket][chainCount[bucket]] = found;
                  chainCount[bucket]++;
                  allGroups[groupCount] = found;
                  groupCount++;
              }

              // Add the word to the group, in insertion order.
              found.words[found.wordCount] = word;
              found.wordCount++;
          }

          // Print groups in creation order: "Group k: word1, word2, ..."
          for (int g = 0; g < groupCount; g++) {
              System.out.print("Group " + (g + 1) + ": ");
              for (int w = 0; w < allGroups[g].wordCount; w++) {
                  if (w > 0) {
                      System.out.print(", ");
                  }
                  System.out.print(allGroups[g].words[w]);
              }
              System.out.println();
          }
      }
  }
