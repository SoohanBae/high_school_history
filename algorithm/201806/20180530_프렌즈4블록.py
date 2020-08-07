#카카오 신입 공채 1차 코딩테스트 문제 6
#http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/

#["CCBDE", "AAADE", "AAABF", "CCBBF"]
#["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"]	

import re

tmp = list(re.sub('[\[\]"]','',input()).split(', '))
a = list(list(tmp[i]) for i in range(len(tmp)))
# for i in a:
#     print(i)
# print()

b = [[0 for rows in range(len(a[0]))]for cols in range(len(a))]


c = True
cnt = 0

while(c):
    c = False

    #지울거 스캔
    for i in range(0,len(a)-1):
        for j in range(0,len(a[i])-1):
            if a[i][j] != '0' and a[i][j] == a[i+1][j] and a[i][j] == a[i][j+1] and a[i][j] == a[i+1][j+1]:
                b[i][j] = b[i+1][j] = b[i][j+1] = b[i+1][j+1] = 1

    #지우면서 카운트
    for i in range(0,len(a)):
        for j in range(0,len(a[i])):
            if b[i][j] == 1:
                b[i][j] = 0
                c = True
                a[i][j] = '0'
                cnt+=1
    
    #블럭 내리기
    for j in range(0,len(a[i])):
        for i in range(len(a)-1,0,-1):
            if a[i][j] == '0' and a[i-1][j] != '0':
                k=i
                while(a[k][j] == '0'):
                    a[k][j] = a[k-1][j]
                    a[k-1][j] = '0'
                    k = min(k+1, len(a)-1)

    # for i in a:
    #     print(i)
    # print()

print(cnt)




