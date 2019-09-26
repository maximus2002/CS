import collections
import math

############################################################
# Problem 3a

def findAlphabeticallyLastWord(text):
    """
    Given a string |text|, return the word in |text| that comes last
    alphabetically (that is, the word that would appear last in a dictionary).
    A word is defined by a maximal sequence of characters without whitespaces.
    You might find max() and list comprehensions handy here.
    """
    # BEGIN_YOUR_CODE (our solution is 1 line of code, but don't worry if you deviate from this)
    return sorted(text.split(" "))[-1]
    # END_YOUR_CODE

############################################################
# Problem 3b

def euclideanDistance(loc1, loc2):
    """
    Return the Euclidean distance between two locations, where the locations
    are pairs of numbers (e.g., (3, 5)).
    """
    # BEGIN_YOUR_CODE (our solution is 1 line of code, but don't worry if you deviate from this)
    return ((loc2[0]-loc1[0])**2+(loc2[1]-loc1[1])**2)**0.5
    # END_YOUR_CODE

############################################################
# Problem 3c

def mutateSentences(sentence):
    """
    Given a sentence (sequence of words), return a list of all "similar"
    sentences.
    We define a sentence to be similar to the original sentence if
      - it as the same number of words, and
      - each pair of adjacent words in the new sentence also occurs in the original sentence
        (the words within each pair should appear in the same order in the output sentence
         as they did in the orignal sentence.)
    Notes:
      - The order of the sentences you output doesn't matter.
      - You must not output duplicates.
      - Your generated sentence can use a word in the original sentence more than
        once.
    Example:
      - Input: 'the cat and the mouse'
      - Output: ['and the cat and the', 'the cat and the mouse', 'the cat and the cat', 'cat and the cat and']
                (reordered versions of this list are allowed)
    """
    # BEGIN_YOUR_CODE (our solution is 20 lines of code, but don't worry if you deviate from this)
    
    # split the sentence
    sentenceSplit=sentence.split()
    # make a pair of words in dictionary key
    wordDict={}
    for i in range(0,len(sentenceSplit)-1):
        wordDict[(sentenceSplit[i],sentenceSplit[i+1])]=True
    # rearrange the array for key in dictionary
    result={}
    def rearrange(array):
        if len(array)==len(sentenceSplit):
            result[tuple(array)]=True
            return
        else:
            lastItem=array[len(array)-1]
            newArray=[]
            for key in wordDict:
                if lastItem == key[0]: # match last word with next word in key
                    nextItem = key[1]
                    oldArray=list(array)
                    oldArray.append(nextItem)
                    newArray.append(oldArray)
            for arr in newArray:
                rearrange(arr) # recuse the array to match  
    # loop though all word in the sentence
    for word in sentenceSplit:
        wordArray=[word]
        rearrange(wordArray)
    # return the results in the list
    resultList=[]
    for x in result:
        s=" ".join(x)
        #print s
        resultList.append(s)
    return resultList
    # END_YOUR_CODE

############################################################
# Problem 3d

def sparseVectorDotProduct(v1, v2):
    """
    Given two sparse vectors |v1| and |v2|, each represented as collections.defaultdict(float), return
    their dot product.
    You might find it useful to use sum() and a list comprehension.
    This function will be useful later for linear classifiers.
    """
    # BEGIN_YOUR_CODE (our solution is 4 lines of code, but don't worry if you deviate from this)
    dot_product = 0
    for elem in v1:
        dot_product += v1[elem]*v2[elem]
    return dot_product
    # END_YOUR_CODE

############################################################
# Problem 3e

def incrementSparseVector(v1, scale, v2):
    """
    Given two sparse vectors |v1| and |v2|, perform v1 += scale * v2.
    This function will be useful later for linear classifiers.
    """
    # BEGIN_YOUR_CODE (our solution is 2 lines of code, but don't worry if you deviate from this)
    for elem in v2:
        v1[elem] += scale*v2[elem]
    return v1
    # END_YOUR_CODE

############################################################
# Problem 3f

def findSingletonWords(text):
    """
    Splits the string |text| by whitespace and returns the set of words that
    occur exactly once.
    You might find it useful to use collections.defaultdict(int).
    """
    # BEGIN_YOUR_CODE (our solution is 4 lines of code, but don't worry if you deviate from this)
    list_text = []
    new_list  = []
    for word in text.split(" "):
        list_text.append(word)
    #print list_text
    for i in range(0,len(list_text)):
        for j in range(i+1,len(list_text)):
            if list_text[i] == list_text[j]:
                new_list.append(list_text[i])
    return set(list_text) - set(new_list)
    # END_YOUR_CODE

############################################################
# Problem 3g

def computeLongestPalindromeLength(text):
    """
    A palindrome is a string that is equal to its reverse (e.g., 'ana').
    Compute the length of the longest palindrome that can be obtained by deleting
    letters from |text|.
    For example: the longest palindrome in 'animal' is 'ama'.
    Your algorithm should run in O(len(text)^2) time.
    You should first define a recurrence before you start coding.
    """
    # BEGIN_YOUR_CODE (our solution is 19 lines of code, but don't worry if you deviate from this)
    # Check empty or single letter
    if len(text) == 0:
        return 0
    if len(text) == 1:
        return 1
    # Make a diagnolized array list
    P=list()
    for i in range(0, len(text)):
        P1=list()
        for j in range(0, len(text)):
            P1.append(0)
        P.append(P1)
    for n in range(0, len(text)):
        P[n][n] = 1
    # Compare each letter if it is Palindrome, if so, add 2, if not, give max on the diagnal number
    for n1 in range(2, len(text)+1):
        for i1 in range(0, len(text)-n1+1):
            j1 = i1+n1-1
            if text[i1] <> text[j1]:
                P[i1][j1] = max(P[i1][j1-1], P[i1+1][j1])
            else:
                P[i1][j1] = P[i1+1][j1-1] + 2
    return P[0][len(text)-1]

    # END_YOUR_CODE
