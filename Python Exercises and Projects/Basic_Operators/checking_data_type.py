
def returnType(item):
    test = type(item)
    if test == int:
        return "Integer"
    elif test == float:
        return "Float"
    elif test == str:
        return "String"
    elif test == bool:
        return "Boolean"
    else:
        return "NULL"

if __name__ == '__main__':
    width = 115
    height = 12.0
    delimiter = '.'
    one = width/2
    two = width/2.0
    three = height/3
    four = 1+2*5
    five = delimiter * 5
    print("{:<15}".format("Item") +"\t" + "{:<15}".format("Type") +"\t" +"{:<15}".format("Equation") + "\t"+"{:<15}".format("Result"))
    print("{:<15}".format("One") +"\t" + "{:<15}".format(returnType(one)) +"\t"+"{:<15}".format("width/2") + "\t"+"{:<15}".format(str(one)))
    print("{:<15}".format("Two") +"\t" + "{:<15}".format(returnType(two)) +"\t" + "{:<15}".format("width/2.0") + "\t" + "{:<15}".format(str(two)))
    print("{:<15}".format("Three") +"\t" + "{:<15}".format(returnType(three)) +"\t" +"{:<15}".format("height/2") + "\t"+ "{:<15}".format(str(three)))
    print("{:<15}".format("Four") +"\t" + "{:<15}".format(returnType(four)) +"\t" +"{:<15}".format("1 + 2 * 5") + "\t"+ "{:<15}".format(str(four)))
    print("{:<15}".format("Five") +"\t" + "{:<15}".format(returnType(five)) +"\t" +"{:<15}".format("delimiter * 5") + "\t"+"{:<15}".format(str(five)))
    
    