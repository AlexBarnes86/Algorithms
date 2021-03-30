#!/bin/python

from os import sys
import math
import random

def insertionSort(ary):
    for j in range(2, len(ary)):
        key = ary[j]
        i = j - 1;
        while i >= 0 and ary[i] > key:
            ary[i+1] = ary[i]
            i -= 1
        ary[i+1] = key

def merge(A, p, q, r):
    n1 = q-p+2
    n2 = r-q+1
    L = [None] * n1
    R = [None] * n2
    for i in range(0, n1-1):
        L[i] = A[p+i]
    for j in range(0, n2-1):
        R[j] = A[q+j+1]
    #print("Merging: %s with p: %d, q: %d, r: %d" % (str(A), p, q, r))
    #print("L: ", L)
    #print("R: ", R)
    L[n1-1] = sys.maxsize
    R[n2-1] = sys.maxsize
    i = 0
    j = 0
    for k in range(p, r+1):
        if(L[i] <= R[j]):
            A[k] = L[i]
            i += 1
        else:
            A[k] = R[j]
            j += 1
    #print("Merged: ", A)
    return A

def mergeSortUtil(A, p, r):
    if(p < r):
        q = math.floor((p+r)/2)
        mergeSortUtil(A, p, q)
        mergeSortUtil(A, q+1, r)
        return merge(A, p, q, r)
    return A

def mergeSort(A):
    return mergeSortUtil(A, 0, len(A)-1)

def partition(A, p, r):
    #print("Partition: {}, p: {}, r: {}".format(A, p, r))
    x = A[r]
    i = p-1
    for j in range(p, r):
        if A[j] <= x:
            i += 1
            A[i], A[j] = A[j], A[i]
    A[i+1], A[r] = A[r], A[i+1]
    #print("x:", x, ", i:", i)
    #print("After partition:", A)
    return i+1

def quickSortUtil(A, p, r):
    if p < r:
        q = partition(A, p, r)
        quickSortUtil(A, p, q-1)
        quickSortUtil(A, q+1, r)

def quickSort(A):
    quickSortUtil(A, 0, len(A)-1)
    return A

def randomizedPartition(A, p, r):
    i = random.randint(p, r)
    A[r], A[i] = A[i], A[r]
    return partition(A, p, r)

def randomizedQuickSortUtil(A, p, r):
    if p < r:
        q = randomizedPartition(A, p, r)
        randomizedQuickSortUtil(A, p, q-1)
        randomizedQuickSortUtil(A, q+1, r)

def randomizedQuickSort(A):
    randomizedQuickSortUtil(A, 0, len(A)-1)
    return A

def countingSort(A, k):
    k += 1
    C = [0] * k
    B = [0] * len(A)

    for i in range(0, len(A)):
        C[A[i]] += 1

    for j in range(1, len(C)):
        C[j] = C[j-1] + C[j]

    for k in range(len(A)-1, -1, -1):
        B[C[A[k]]-1] = A[k]
        C[A[k]] -= 1
    return B

def positionalCountingSort(A, pos):
    C = [0] * 10
    B = [0] * len(A)

    for i in range(0, len(A)):
        val = int(A[i]/math.pow(10, pos)) % 10
        C[val] += 1

    for j in range(1, len(C)):
        C[j] = C[j-1] + C[j]

    for k in range(len(A)-1, -1, -1):
        val = int(A[k]/math.pow(10, pos)) % 10
        B[C[val]-1] = A[k]
        C[val] -= 1
    return B

def radixSortUtil(A, d):
    for i in range(0, d):
        A = positionalCountingSort(A, i)
    return A

def radixSort(A):
    maxDigits = 0
    for i in range(0, len(A)):
        digits = len(str(A[i]))
        if digits > maxDigits:
            maxDigits = digits
    return radixSortUtil(A, maxDigits)

def main():
    ary = [329, 457, 657, 839, 1200, 436, 720, 355]
    print(ary)
    print(radixSort(ary))
#    maxValue = 100
#    for i in range(0, 100):
#        ary = random.sample(range(1, maxValue), random.randint(8, 8))
#        random.shuffle(ary)
#        print("Before: {}".format(ary))
#        #srt = randomizedQuickSort(ary)
#        srt = countingSort(ary, maxValue)
#        print("After: {}".format(srt))
#        print("---")
#        #print("Before: %s After: %s" % (orig, mergeSort(ary)))

if __name__ == "__main__":
    main()
