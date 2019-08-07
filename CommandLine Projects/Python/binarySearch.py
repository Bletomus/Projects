
def takeInput():
    x = int(input("Please enter in the number to be search from 0 to 10000: "))
    return x
def simpleSearch(x,l):
    index = 0
    done = True 
    steps = 0
    print("Simple Search:\n")
    while(done):
        steps = steps + 1
        
        try:        
            if x == l[index]:
                print("Done!\n")
                print("Steps done :" + str(steps) + "\n")
                return
            else:
                index = index + 1
        except:
            print("The number is not in the list")
            print("Steps done :" + str(steps) + "\n")
            return
    
def binarySearch(x,l): 
    index = int(len(l) / 2)
    steps = 0
    
    if x == l[index]:
        print("Done!\n")
        return 1
    elif len(l) ==  1:
        print("Not found")
        return 1
    elif x > l[index]:
        steps = 1 + binarySearch(x,l[index:])
    elif x < l[index]:
        steps = 1 + binarySearch(x,l[:index])
        
    return steps
    
def searchComparison(x):
    l = [i for i in range(10001)]
    simpleSearch(x,l)
    print("Binary Search:\n")
    steps = binarySearch(x,l)
    print("Steps done :" + str(steps) + "\n")
    
    
    
searchComparison(takeInput())


        
    
    
