'The purpose of this function is to display the string that has been entered by the user and display it such that the last \
value of the string is on the seventh column of the display panel'

def rightJustify(a =""):
    sizeOfA = len(a)
    maxSize = 70
    spaces = maxSize - sizeOfA
    string = (" " * spaces) + a
    
    return string

if __name__ == '__main__':
    a = input("Please enter in any string\n")
    string = rightJustify(a)
    print(string)
    print("Length of the new string is " + str(len(string)))
    