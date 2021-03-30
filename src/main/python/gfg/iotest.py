#!/bin/python

if __name__ == '__main__':
    # Reading the first line and converting it to an integer
    num_entries = int(input())

    # Running a for loop to read each of the following lines
    for i in range(num_entries):
        current_line = input()
        # Getting the two values separated by space in variables `a` and `b`
        a, b = current_line.split()
        a, b = int(a), float(b)
        print(a, b)
