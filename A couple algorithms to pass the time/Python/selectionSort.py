import random

def makeArray():
    list1 = []
    for x in range(100):
        list1.append(random.randint(1, 1000))
    return list1

def selectSmallest(arr):
    smallestValue = arr[0]
    smallestIndex = 0
    for x in range(1, len(arr)):
        if(arr[x] < smallestValue):
            smallestValue  = arr[x] 
            smallestIndex = x
    
    return smallestIndex

def selectionSort(arr):
    newList = []
    for x in range(len(arr)):
        smallestIndex = selectSmallest(arr)
        newList.append(arr.pop(smallestIndex))
    return newList

def menu():
    arr = makeArray()
    for x in range(100):
        print(str(arr[x]) + "  " + str(x))
    newArray = selectionSort(arr) 
    print("\n\n\n\n")
    for x in range(100):
        print(str(newArray[x]) + "  " + str(x))
        
        
        
        
menu()
