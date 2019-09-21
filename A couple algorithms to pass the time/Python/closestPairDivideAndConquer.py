import random

class Points:
    def __init__(self,x,y):
        self.x = x
        self.y = y
    def getY(self):
        return self.y
    def getX(self):
        return self.x
    
    
def main():
    l=[]
    for i in range(10000):
        l.append(Points(random.randrange(0,10000),random.randrange(0,10000)))
    l2 = getClosestPair(l)
    print("The distance between the two points is " + str())
    print("\n The closest pair is " + str(l2[0].getX) +str(l2[0].getY) + str(l2[1].getX) + str(l2[1].getY))
    