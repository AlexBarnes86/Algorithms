import sys
import random

def strGrid(grid):
    st = ""
    for row in grid:
        st += " ".join(map(str, row)) + "\n"
    return st

def lcs(A, B):
    #Construct the LCS grid table
    grid = [[0 for b in range(len(B) + 2)] for a in range(len(A) + 2)]

    for i in range(2, len(A)+2):
        grid[i][0] = A[i-2]

    for i in range(2, len(B)+2):
        grid[0][i] = B[i-2]

    row = 0
    col = 0
    for i, b in enumerate(B):
        for j, a in enumerate(A):
            row = 2 + j
            col = 2 + i

            if a == b:
                grid[row][col] = 1 + grid[row-1][col-1]
            else:
                grid[row][col] = max(grid[row-1][col], grid[row][col-1])

    #print(strGrid(grid))

    #Work backwards to find the LCS String from the grid
    row = min(len(grid)-1, row)
    col = min(len(grid[row])-1, col)
    i = grid[row][col]
    st = ""
    while i > 0 and row > 0 and col > 0:
#        try:
#        if grid[row-1][col-1] == 0 and grid[row-1][col] == 0 and grid[row][col-1] == 0:
#            break
        if i == grid[row-1][col-1] and grid[row-1][col] == grid[row][col-1]:
            row -= 1
            col -= 1
        elif grid[row-1][col] > grid[row][col-1]:
            row -= 1
        elif grid[row][col-1] > grid[row-1][col-1]:
            col -= 1
        elif i == grid[row-1][col-1] + 1:
            st = grid[row][0] + st
            i -= 1
#        except Exception as e:
#            print(e)
#            print(i, row, col)
#            break

    return st
            
def main():
    with open("scrabble_dictionary.txt", "r") as f:
        words = f.readlines()

    total = int((len(words)**2 + len(words))/2)
    ct = 0
    print("Total words to check", total)
    longest = None

    for i in range(len(words)):
        for j in range(i+1, len(words)):
            ct += 1

            A = words[i].strip()
            B = words[j].strip()

            #Don't really care to check short words since we know the longest substring will be much larger anyways
            if(len(A) <= 8 or len(B) <= 8):
                continue

            #We don't care about words that are prefixes of other words (e.g. pluralized forms)
            if(A in B or B in A):
                continue
            soln = lcs(A, B)
            #print("LCS between {} and {} is {}".format(A, B, soln))

            if longest == None or len(soln) > len(longest[2]):
                longest = (A, B, soln)
                print("Longest so far ({}) : {}".format(ct, longest))
    print("Longest:", longest)

if __name__ == "__main__": main()

