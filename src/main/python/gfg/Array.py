def rotate(arr, amt):
    copy = arr[:]
    for i, val in enumerate(arr):
        arr[i] = copy[(i+amt) % len(arr)]


mylist = [1, 2, 3, 4, 5, 6, 7]
print(mylist)
rotate(mylist, 2)
print(mylist)

