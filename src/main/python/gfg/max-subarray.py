#!/bin/python

import math
import random

def findMaxCrossingSubarray(A, low, mid, high):
    leftSum = 0
    sum = 0
    maxLeft = mid
    for i in range(mid, low-1, -1):
        sum += A[i]
        if(sum >= leftSum):
            leftSum = sum
            maxLeft = i

    rightSum = 0
    sum = 0
    maxRight = mid
    for i in range(mid+1, high+1):
        sum += A[i]
        if(sum >= rightSum):
            rightSum = sum
            maxRight = i

    return (maxLeft, maxRight, leftSum+rightSum)

def findMaxSubarray(A, low, high):
    if high == low:
        return (low, high, A[low])
    else:
        mid = math.floor((low+high)/2)
        leftLow, leftHigh, leftSum = findMaxSubarray(A, low, mid)
        rightLow, rightHigh, rightSum = findMaxSubarray(A, mid+1, high)
        crossLow, crossHigh, crossSum = findMaxCrossingSubarray(A, low, mid, high)

        if(leftSum >= rightSum and leftSum >= crossSum):
            return (leftLow, leftHigh, leftSum)
        elif(rightSum >= leftSum and rightSum >= crossSum):
            return (rightLow, rightHigh, rightSum)
        else:
            return (crossLow, crossHigh, crossSum)

#4.1-5, while iterating through the array we can potentially encounter a new maximum sub array every time we switch from a negative to positive value
def kadane(A):
    maxLeft = 0
    maxRight = 0
    maxSum = float('-inf')
    sum = 0
    curLeft = 0
    curSum = 0

    for i in range(0, len(A)):
        sum += A[i]
        if(sum >= maxSum):
            maxLeft = curLeft
            maxRight = i
            maxSum = sum
        if(sum < 0):
            curLeft = i
            sum = 0

    return (maxLeft, maxRight, maxSum)

#A= [0 , 1,  2, 3, 4,  5,  6, 7, 8, 9,10,11, 12,13,14,15]
A = [13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7]

print(list(range(0, len(A))))
print(A)

for i in range(0, len(A)):
    maxCross = findMaxCrossingSubarray(A, 0, i, len(A)-1)
    print("Max cross at", i, ":", maxCross)

maxSub = findMaxSubarray(A, 0, len(A)-1)
print("MaxSub:", maxSub)

maxSub = findMaxSubarray(A, 0, len(A)-1)
print("Kadane maxSub:", maxSub)

#for i in range(0, 100):
#    A = [random.randint(-100, 100) for r in range(20)]
#
#    print(A)
#    maxSub = findMaxSubarray(A, 0, len(A)-1)
#    print("MaxSub:", maxSub)
#
#    kmax = findMaxSubarray(A, 0, len(A)-1)
#    print("Kadane maxSub:", kmax)
#    print(maxSub == kmax)

