#!/bin/python

if __name__ == '__main__':
    try:
        # Running a while loop since we don't know how many lines to read.
        while True:
            current_line = input()
            # Getting the two values separated by space in variables `a` and `b`
            a, b = current_line.split()
            a, b = int(a), float(b)
            print(a, b)
    except EOFError:
        # There is nothing more to do when we reach End of File (EOF)
        pass
