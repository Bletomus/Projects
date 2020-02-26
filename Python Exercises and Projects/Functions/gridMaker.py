'The purpose of this program is to print a grid with each square of equal value'

def makeBars(size):
    string = (("|"+ " " * (size * 2 - 1)) * 2 + "|\n") *size
    return string

def makeCrossLine(size):
    doubled = size * 2

    countj = 0
    l = []
    while(countj < 2):
        l.append("+")
        counti = 1
        while(counti < doubled):
            if doubled % 2 == 1:
                l.append(" ")
            else:
                l.append("-")
            counti = counti + 1
        countj = countj + 1
    l.append("+")
        
    return "".join(l)

def makeGrid(size):
    string = makeCrossLine(size) + "\n"+ makeBars(size) + makeCrossLine(size) + "\n"+ makeBars(size) + makeCrossLine(size) 
    return string
if __name__ == '__main__':
    string = ""
    flag = True
    while(flag):
        size = input("Please enter in the size of each \n")
        if not int(size) <1:
            break
    
    string = makeGrid(int(size))
    
    print(string)