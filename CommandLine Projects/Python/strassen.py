# -*- coding: utf-8 -*-


def split( c, d, iB, jB):
    i1=0
    i2 = iB
    while(i1 < len(d)):
        j1=0
        j2=jB
        while(j1 < len(d)):
            d[i1][j1] = c[i2][j2]
            j1 = j1 + 1
            j2 = j2 + 1
        i1 = i1 + 1
        i2 = i2 + 1

def join( c, d, iB, jB):
    i1=0
    i2 = iB
    while(i1 < len(c)):
        j1=0
        j2=jB
        while(j1 < len(c)):
            d[i2][j2]=c[i1][j1]
            j1 = j1 + 1
            j2 = j2 + 1
        i1 = i1 + 1
        i2 = i2 + 1
    
	    

def add(a,b):
    n = len(a)
    c = [[0 for j in range(n)] for p in range(n)]
    for i in range(n):
        for d in range(n):
            c[i][d] = a[i][d] + b[i][d]
    return c

def sub(a,b):
    n = len(a)
    c = [[0 for j in range(n)] for p in range(n)]
    for i in range(n):
        for d in range(n):
            c[i][d] = a[i][d] - b[i][d]
    return c
    
    
    
def multiply(a,b):
    n = len(a)
    r = [[0 for j in range(n)] for p in range(n)]
    if n==1:
        r[0][0] = a[0][0]*b[0][0]
    else:
        a00 = [[0 for j in range(n)] for p in range(int(n/2))]
        a01 = [[0 for j in range(n)] for p in range(int(n/2))]
        a10 = [[0 for j in range(n)] for p in range(int(n/2))]
        a11 = [[0 for j in range(n)] for p in range(int(n/2))]
        
        b00 = [[0 for j in range(n)] for p in range(int(n/2))]
        b01 = [[0 for j in range(n)] for p in range(int(n/2))]
        b10 = [[0 for j in range(n)] for p in range(int(n/2))]
        b11 = [[0 for j in range(n)] for p in range(int(n/2))]
        
        split(a, a00, 0, 0)
        split(a, a01, 0, int(n/2))
        split(a, a10, int(n/2), 0)
        split(a, a11, int(n/2), int(n/2))
        
        split(b, b00, 0, 0)
        split(b, b01, 0, int(n/2))
        split(b, b10, int(n/2), 0)
        split(b, b11, int(n/2), int(n/2))
        
        m1 = multiply(add(a00,a11), add(b00, b11))
        m2 = multiply(add(a10,a11), b00)
        m3 = multiply(a00,sub(b01, b11))
        m4 = multiply(a11, sub(b10, b00))
        m5 = multiply(add(a00, a01), b11)
        m6 = multiply(sub(a10, a00), add(b00, b01))
        m7 = multiply(sub(a01, a11), add(b10, b11))
        
        r00 = add(add(m1, sub(m4, m5)), m7)
        r01 = add(m3, m5)
        r10 = add(m2, m4)
        r11 = add(add(m1,sub(m3,m2)), m6)
        
        join(r00, r, 0, 0)
        join(r01, r, 0, int(n/2))
        join(r10, r, int(n/2), 0)
        join(r11, r, int(n/2), int(n/2))
    return r

def main():
    a = [[1, 2, 3, 4], [3, 4, 6, 7], [3, 7, 6, 7], [3, 2, 4, 8]]
    b = [[2, 1, 4, 3], [6, 8, 2, 3], [3, 4, 6, 7], [1, 4, 8, 3]]
    
    c = multiply(a,b)
    print(c)
    
main()


















