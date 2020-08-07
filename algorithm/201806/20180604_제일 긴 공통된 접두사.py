
'''
안녕하세요, 매일프로그래밍 이번 주 문제입니다.

문자열 배열(string array)이 주어지면, 제일 긴 공통된 접두사(prefix)의 길이를 찾으시오.


Input: ["apple", "apps", "ape"]

Output: 2 // “ap”



Input: ["dog", "dogs", "doge"]

Output: 3 // “dog”


'''

import re

a = list(re.sub('[\[\]"]','',input()).split(', '))
m = min(len(x) for x in a)

c = 1

for j in range(m):
    b = a[0][j]
        
    for i in range(1,len(a)):
        #print(a[i][:j])
        if b != a[i][j]:
            print(a[i][:j])
            c = 0
            break

    if c == 0:
        break

if c == 1:
    print(a[0][:m])