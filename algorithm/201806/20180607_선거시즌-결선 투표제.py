#https://www.acmicpc.net/problem/9547
#백준 9547번 문제 - 대통령 선거 ( 외국 문제라 결선투표제로 만들어져 있음 )


T = int(input())
output =[[0 for i in range(2)] for j in range(T)]

for t in range(T):
    c, v = [int(s) for s in input().split(" ")] # c : 후보자 수,  v : 유권자 수

    data = [[0 for i in range(c)] for j in range(v)] # 데이터 저장공간
    _1vote = [ 0 for i in range(c)]

    for i in range(v):
        data[i] = [int(s) for s in input().split(" ")]

    for i in range(v):
        _1vote[data[i][0] - 1] += 1


    if max(_1vote) >= v/2:

        output[t][0] = _1vote.index(max(_1vote)) + 1
        output[t][1] = 1
        continue

    _2vote = [ 0 for i in range(c)]

    a = 0
    b = 0
    aa = -1
    bb = -1
    for i in range(c):
        if _1vote[i] > aa:
            b = a
            bb = aa
            a = i
            aa = _1vote[i]
        elif _1vote[i] > bb:
            b = i
            bb = _1vote[i]


    a+=1
    b+=1
    for i in range(v):
        for j in range(c):
            if data[i][j] == a or data[i][j] == b:
                _2vote[data[i][j] - 1] += 1
                break



    output[t][0] = _2vote.index(max(_2vote)) + 1
    output[t][1] = 2


for i in range(T):
    for j in range(2):
        print(output[i][j],end=' ')
   
    print()