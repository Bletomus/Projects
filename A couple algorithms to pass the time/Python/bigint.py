# -*- coding: utf-8 -*-


import re

#function for multiplying two strings of numbers together
def mut(x,y):
    negative = ['']
    if (x.startswith('-') and y.startswith('-')) or (not(x.startswith('-')) and not(y.startswith('-'))) :
        a = list(x.replace('-',''))
        b = list(y.replace('-',''))
    elif (x.startswith('-') and not(y.startswith('-')) or not(x.startswith('-')) and y.startswith('-')) :
        a = list(x.replace('-',''))
        b = list(y.replace('-',''))
        negative[0] = '-'
        
    if(len(a) == 1 and len(b) == 1):
        tmp = int(a[0]) * int(b[0])
        if tmp == 0:
            return str(tmp)
        else:
            return negative[0] + str(tmp)
    else:
        if len(a) == 1:
            c = '0'
            d = a[0]
        else:
            if len(a) % 2 != 0:
                a.insert(0,'0')
            ya = int(len(a) / 2)
            tem1 = a[0:ya]
            c = ''.join(tem1)
            tem1 = a[ya:]
            d = ''.join(tem1)
            
        if len(b) == 1:
            e = '0'
            f = b[0]
        else:
            if len(b) % 2 != 0:
                b.insert(0,'0')
            e = ''.join(b[0:int(len(b) / 2)])
            f = ''.join(b[int(len(b) / 2):])
        if len(a) >= len(b):
            n = len(a)
        else:
            n = len(b)
        
        ce = mut(c,e)
        df = mut(d,f)
        t1 = mut(sub(c,d),sub(f,e))
        t2 = add(add(t1,ce),df)
        t3 = add(add(Power10(ce, n), Power10(t2, int(n / 2))), df)
        t5 = re.sub("^0+",'',t3)
        if t5 == '':
            return '0'
        else:
            return negative[0] + t5
        
#function for adding two strings of digits together
def add(x,y):
    if x.startswith('-') and not(y.startswith('-')):
        return sub(y,x.replace('-',''))
    elif not(x.startswith('-')) and y.startswith('-'):
        return sub(x,y.replace('-',''))
    elif (x.startswith('-') and y.startswith('-')):
        return '-' + add(x.replace('-',''),y.replace('-',''))
    a = list(x)
    b = list(y)
    
    if len(a) > len(b):
        b = list(formate(b,len(x),'0'))
    else:
        a = list(formate(a,len(y),'0'))
    
    i = len(a) - 1
    sum2 = [0 for j in range(len(a) + 1)]
    while i >= 0:
        tmpsum = int(a[i]) + int(b[i]) + sum2[i + 1]
        if tmpsum >= 10:
            sum2[i + 1] = tmpsum - 10
            sum2[i] = 1
        else:
            sum2[i + 1] = tmpsum
        i = i - 1
    
    if sum2[0] == 1:
        sum1 = list(map(str,sum2))
        return ''.join(sum1)
    else:
        sum1 = list(map(str,sum2))
        fin = ''.join(sum1)
        return fin[1:]
#function for subtracting two strings of digits
def sub(x,y):
    a = list(x)
    b = list(y)
    flag = checkBigger(x,y)
    if flag == 0:
        return '0'
    elif flag == -1:
        tmp = b;
        b = a;
        a = tmp;
    
    b = formate(b, len(a),'0')
    diff = [0 for j in range(len(a))]
    i = len(a) - 1
    while i >= 0:
        tmpdiff = int(a[i]) - int(b[i]) + diff[i]
        if tmpdiff < 0:
            tmpdiff = tmpdiff + 10
            diff[i - 1] = -1
        diff[i] = tmpdiff
        i = i - 1
        
    diff1 = list(map(str,diff))
    diff3 = ''.join(diff1)
    diff5 = re.sub("^0+", "", diff3)
    #diff3.replace("^0+", "")
    #set(s1.split(' ')
    if diff5 == '':
        return "0"

    if flag == -1:
        diff5 = "-" + diff5
    return diff5
#this function checks the bigger value between two values 
def checkBigger(x,y):
    if len(x) > len(y):
        return 1
    elif len(x) < len(y):
        return -1
    else:
        for i in range(len(x)):
            if(x[i] > y[i]):
                return 1
            elif x[i] < y[i]:
                return -1
        return 0
#this function will create a list that stores the same number of digits including zeros to the value being its being added to
def formate(b,val,zero):
    c = list(b)
    val = val - len(b)
    for i in range(val):
        c.insert(0,zero)
    
    return c
#multiplies a value by a certain power
def Power10(num,n):
    numl = list(num)
    for i in range(n):
        numl.append('0')
    return ''.join(numl)

def main():
    x = "-10" 
    y = "10"
    j = mut(x, y)
    z = mut("93859048059849086850986804750894758903278473894578397598475984784857487584758094875890475984955624146039530798877974", "224343444859408590475847538946")
    print(j)
    print(z)
    
main();
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    