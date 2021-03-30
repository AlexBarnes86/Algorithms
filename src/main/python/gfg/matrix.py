#!/bin/python

def squareMatrixMultiply(A, B):
    C = [[0 for x in range(len(A[0]))] for y in range(len(B))]
    for i in range(0, len(A[0])):
        for j in range(0, len(B)):
            for k in range(0, len(B)):
                C[i][j] += A[i][k] * B[k][j]
    return C

A = [[1, 2, 3],[4, 5, 6],[7,8,9]];
B = [[1, 2, 3],[4, 5, 6],[7,8,9]];
C = squareMatrixMultiply(A, B)
print(C)
