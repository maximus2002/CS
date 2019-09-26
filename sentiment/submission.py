#!/usr/bin/python

import random
import collections
import math
import sys
from util import *

############################################################
# Problem 3: binary classification
############################################################

############################################################
# Problem 3a: feature extraction

def extractWordFeatures(x):
    """
    Extract word features for a string x. Words are delimited by
    whitespace characters only.
    @param string x: 
    @return dict: feature vector representation of x.
    Example: "I am what I am" --> {'I': 2, 'am': 2, 'what': 1}
    """
    # BEGIN_YOUR_CODE (our solution is 4 lines of code, but don't worry if you deviate from this)
    items = [s for s in x.split() if s]
    dd = {}
    for item in items:
        dd[item] = dd.get(item,0)+1
    return dd
    # END_YOUR_CODE

############################################################
# Problem 3b: stochastic gradient descent

def learnPredictor(trainExamples, testExamples, featureExtractor, numIters, eta):
    '''
    Given |trainExamples| and |testExamples| (each one is a list of (x,y)
    pairs), a |featureExtractor| to apply to x, and the number of iterations to
    train |numIters|, the step size |eta|, return the weight vector (sparse
    feature vector) learned.

    You should implement stochastic gradient descent.

    Note: only use the trainExamples for training!
    You should call evaluatePredictor() on both trainExamples and testExamples
    to see how you're doing as you learn after each iteration.
    '''
    weights = {}  # feature => weight
    # BEGIN_YOUR_CODE (our solution is 12 lines of code, but don't worry if you deviate from this)
    #def featureExtractor(x):
        #phi = defaultdict(float)
        #tokens = x.split()
        #return phi
    #featureExtractor = extractCharacterFeatures(3)
    def sF(weights, i):
        x, y = trainExamples[i]
        phi = featureExtractor(x)
        return 1-dotProduct(phi, weights)*y # 1 - margin

    def sdF(weights, i):
        x, y = trainExamples[i]
        phi = featureExtractor(x)
        if sF(weights, i) < 0: # If 0 > 1 - w*phi(x)*y, then 0
            return {}
        else:                  # Else -phi(x)*y
            dF = {}
            for k,v in phi.items():
                dF[k] = dF.get(k,0) - v*y
            #print dF
            return dF

    def stochasticGradientDescent(sdF, numIters, eta):
        #num_updates = 0
        for t in range(numIters):
            for i in range(len(trainExamples)):
                #value = sF(weights, i)
                gradient = sdF(weights, i)
                #num_updates += 1
                for k,v in gradient.items(): #weights = weights - eta*gradient
                    weights[k] = weights.get(k,0) - v*eta

    stochasticGradientDescent(sdF, numIters, eta)
    
    #weights = submission.learnPredictor(trainExamples, testExamples, featureExtractor)
    #util.outputWeights(weights, 'weights')
    #util.outputErrorAnalysis(testExamples, featureEtractor, weights, 'error-analysis')

    #testExamples = util.readExamples('names.test')
    #predictor = lambad x:1 if util.dotproduct(featureExtractor(x), weights) >0 else -1
    #print 'test error = ', util.evaluatePredictor(testExamples, predictor)
    # END_YOUR_CODE
    return weights

############################################################
# Problem 3c: generate test case

def generateDataset(numExamples, weights):
    '''
    Return a set of examples (phi(x), y) randomly which are classified correctly by
    |weights|.
    '''
    random.seed(42)
    # Return a single example (phi(x), y).
    # phi(x) should be a dict whose keys are a subset of the keys in weights
    # and values can be anything (randomize!) with a nonzero score under the given weight vector.
    # y should be 1 or -1 as classified by the weight vector.
    def generateExample():
        # BEGIN_YOUR_CODE (our solution is 2 lines of code, but don't worry if you deviate from this)
        phi = {random.choice(weights.keys()) : random.random()-1 for i in range(len(weights))}
        y = 1 if dotProduct(phi,weights) > 0 else -1
        return (phi, y)
    return [generateExample() for _ in range(numExamples)]

############################################################
# Problem 3e: character features

def extractCharacterFeatures(n):
    '''
    Return a function that takes a string |x| and returns a sparse feature
    vector consisting of all n-grams of |x| without spaces.
    EXAMPLE: (n = 3) "I like tacos" --> {'Ili': 1, 'lik': 1, 'ike': 1, ...
    You may assume that n >= 1.
    '''
    def extract(x):
        # BEGIN_YOUR_CODE (our solution is 6 lines of code, but don't worry if you deviate from this)
        #listWord = []
        x=x.replace("\t", "")
        string = ''.join(x.split())
        items = [string[i:i+n] for i in range(0, len(string)-n+1)]
        extract = {}
        for item in items:
            extract[item] = extract.get(item,0)+1
        return extract
        # END_YOUR_CODE
    return extract

############################################################
# Problem 4: k-means
############################################################


def kmeans(examples, K, maxIters):
    '''
    examples: list of examples, each example is a string-to-double dict representing a sparse vector.
    K: number of desired clusters. Assume that 0 < K <= |examples|.
    maxIters: maximum number of iterations to run (you should terminate early if the algorithm converges).
    Return: (length K list of cluster centroids,
            list of assignments (i.e. if examples[i] belongs to centers[j], then assignments[i] = j)
            final reconstruction loss)
    '''
    # BEGIN_YOUR_CODE (our solution is 32 lines of code, but don't worry if you deviate from this)
    random.seed(100)
    exampleLen = [dotProduct(examples[i],examples[i]) for i in range(len(examples))]
    #Transform square norm 
    def squareDistance(dd1,dd2,dd1Ind,dd2Ind):
        return exampleLen[dd1Ind] - 2*dotProduct(dd1,dd2) + centroidLen[dd2Ind]
    # K-means loss function
    def loss():
        loss = 0
        for i in range(len(examples)):
            loss += squareDistance(examples[i],centroids[assignments[i]],i,assignments[i])
	return loss
    # Calculate centroids
    centroids = []
    centroidLen = []
    for i in range(K):
        randomCentroid = random.choice(examples)
        while randomCentroid in centroids:
            randomCentroid = random.choice(examples)
        centroids.append(randomCentroid)
        centroidLen.append(dotProduct(randomCentroid,randomCentroid))
        
    assignments = [0] * len(examples)
    # Loop all Iterations
    for i in range(maxIters):
        oldCentroids = list(centroids)
        clusters=[] 
        for j in range(K):
            clusters.append([])
        squareDistancenorms=[] 
        for j in range(len(examples)):
            exampleSquareDistance=[]
            for k in range(len(centroids)):
                exampleSquareDistance.append(squareDistance(examples[j],centroids[k],j,k))
            squareDistancenorms.append(exampleSquareDistance)
        for j in range(len(squareDistancenorms)):
            assignCentroid = squareDistancenorms[j].index(min(squareDistancenorms[j]))
            assignments[j] = assignCentroid
            clusters[assignCentroid].append(examples[j])
        for j in range(len(centroids)):
            newCentroid = {}
            points = clusters[j]
            for p in points:
                for k,v in p.items():
                    if k in newCentroid:
                        newCentroid[k]+=v
                    else:
                        newCentroid[k]= v
            # Average points
            for k in newCentroid: 
                newCentroid[k] = newCentroid[k]/float(len(points))
            centroids[j] = newCentroid
            centroidLen[j] = dotProduct(newCentroid, newCentroid)
        # Early converge
        if oldCentroids == centroids:
            break
    return (centroids,assignments,loss())
    # END_YOUR_CODE
