import java.util.*;

// Xiaoyu Zhou
// 02/06/2021
// CSE143
// Section: AP
// TA: Hitesh Boinpally
// Assignment #6
// This program will uses a dictionary to find all combinations of words that contains 
// the same letters as a given phrase.

public class AnagramSolver {
    private Map<String, LetterInventory> lettersOfWord;
    private List<String> dict;

    // Post: This method will construct a anagram solver by using the given dictionary.
    //       You may assume that the dictionary is not empty and it doesn't contain
    //       empty words or duplicated words. The given dictionary will not be changed.
    // Parameter: List<String> dictionary - the given dictionary to search anagrams
    public AnagramSolver(List<String> dictionary) {
        this.lettersOfWord = new HashMap<String, LetterInventory>();
        this.dict = dictionary;
        for (String word : dictionary) {
            LetterInventory letter = new LetterInventory(word);
            this.lettersOfWord.put(word, letter);
        }
    }

    // Pre: This metod will throw an IllegalArgumentException when the given max is less than 0
    // Post: This method will print all the combinations of words from the given dictionary which
    //       are anagrams(words of same character in different order) of the given text. 
    //       There should be printed at most the given max of
    //       combinations or print all the combinations if the given max is zero.
    public void print(String text, int max) {
        if (max < 0) {
            throw new IllegalArgumentException();
        }

        LetterInventory lettersOfText = new LetterInventory(text);
        List<String> shortDict = new LinkedList<String>();
        for (String word : dict) {
            if (lettersOfText.subtract(lettersOfWord.get(word)) != null) {
                shortDict.add(word);
            }
        }

        print(shortDict, lettersOfText, new LinkedList<String>(), max);
    }

    // Post: This method will help to check and print all the possibilities of combinations of
    //       anagrams choosed from the given dictionary compared to given word
    //       The number of combinations of words must be less than the given max number or
    //       find all the combination if the given max is 0. 
    //       The anagrams in the combinations should have the exactly number of each character
    //       as the give word.
    // Parameter:
    //      List<String> shortDict - the given dictionary and each word has at least one anagram
    //      LetterInventory lettersOfText - the given text which want the dictionary campare to 
    //      List<String> soFar - the combinations we have so far.
    //      int max - the given max number of combination the method want to print, or print 
    //                all combination if the given max is 0
    private void print(List<String> shortDict, LetterInventory lettersOfText,
                               List<String> soFar, int max) {
        if (lettersOfText.isEmpty()) {
            System.out.println(soFar);
        } else if (max == 0 || soFar.size() < max) {
            for (String word : shortDict) {
                if (lettersOfText.subtract(lettersOfWord.get(word)) != null) {
                    soFar.add(word);
                    print(shortDict, lettersOfText.subtract(lettersOfWord.get(word)), 
                                   soFar, max);
                    soFar.remove(soFar.size() - 1);
                }
            }
        }
    }
}
