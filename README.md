# Text Editor
> A learning project on implemening text editor features with data structures such as Linkedlist, Tree, Trie, HashSet in Java.

## Features
List the ready features here:
- Flesch Readability Score Calculation
- Markov Text Generation
- Spelling Suggestion
- Word Auto-Completion
- Word Path Search


## Setup
1. Download TextEditor.jar, run.bat, and the data directory
2. Run run.bat


## Usage
Flesch Readability Score Calculation
- Higher the flesch index, higher the readability
- Flesch index of a piece of text is calculated base on:
  - Ratio of the number of words to the number sentences
  - Ratio of the number of syllables to the number of words

Markov Text Generation
- Generate text which resembles the source text
- For instance, the generator can produce text which resembles lyrics that is in some way repetitive

Spelling Suggestion / Word Auto-Completion
- Suggest nearby words of the mispelled or incompleted word in the dictionary

Word Path Search
- Search the word path (edit distance) between two words
- For instance, the word path of "time" to "main" could be: time -> mime -> mine -> maine -> main

##  Navigate through code
1. Source code can be found in the directory `source_code\src`.
2. Codes for each features can be found in below list of files correspondingly:
>
| Feature | File |
| :---: | :---: |
| Flesch Readability Score Calculation | `document\EfficientDocument.java` |
| Markov Text Generation | `textgen\MarkovTextGeneratorLoL.java` |
| Spelling Suggestion | `spelling\NearbyWords.java` |
| Word Auto-Completion | `spelling\AutoCompleteDictionaryTrie.java` |
| Word Path Search | `spelling\WPTree.java`|
