#!/bin/python

import math

#A = [4, 16, 10, 14, 7, 9, 3, 2, 8 ,1]
A = [4, 1, 3, 2, 16, 9, 10, 14, 8, 7]
heapSize = len(A)

def parent(i):
    return math.floor((i-1)/2)

def left(i):
    return 2*i+1

def right(i):
    return 2*i+2

def maxHeapify(i):
    global A
    l = left(i)
    r = right(i)

    if l < heapSize and A[l] > A[i]:
        largest = l
    else:
        largest = i

    if r < heapSize and A[r] > A[largest]:
        largest = r

    if largest != i:
        A[i], A[largest] = A[largest], A[i]
        maxHeapify(largest)

def buildMaxHeap():
    global A
    global heapSize
    heapSize = len(A)
    for i in range(math.floor(heapSize/2), -1, -1):
        maxHeapify(i)

def heapSort():
    global A
    global heapSize
    buildMaxHeap()
    for i in range(heapSize-1, 0, -1):
        A[0], A[heapSize-1] = A[heapSize-1], A[0]
        heapSize -= 1
        maxHeapify(0)

def heapMax():
    global A
    return A[0]

def heapExtractMax():
    global A
    global heapSize
    if heapSize < 1:
        raise Exception("heap underflow")
    m = A[0]
    A[0] = A[heapSize-1]
    heapSize -= 1
    A = A[:-1]
    maxHeapify(0)
    return m

def heapIncreaseKey(i, key):
    global A
    if key < A[i]:
        raise Exception("new key is smaller than current key")
    A[i] = key
    while i > 0 and A[parent(i)] < A[i]:
        A[i], A[parent(i)] = A[parent(i)], A[i]
        i = parent(i)

def maxHeapInsert(key):
    global A
    global heapSize
    A.append(float('-inf'))
    heapSize += 1
    heapIncreaseKey(heapSize-1, key)

print("Before:", A)
buildMaxHeap()
print("After:", A)
m = heapExtractMax()
print("Extracted max: {}, remaining: {}".format(m, A))
heapIncreaseKey(heapSize-1, 18)
print("Increasing last element to 18", A)
maxHeapInsert(17)
print("Inserting 17", A)

heapSort()
print("Sorted:", A)
