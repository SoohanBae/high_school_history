# your code goes here


'''
. 


11 ============
안녕하세요, 매일프로그래밍 이번주 문제입니다.

길이가 같은 두 문자열(string) A 와 B가 주어지면, A 가 B 로 1:1 암호화 가능한지 찾으시오.


예제)

Input: “EGG”, “FOO”

Output: True // E->F, G->O


Input:  “ABBCD”, “APPLE”

Output: True // A->A, B->P, C->L, D->E


Input: : “AAB”, “FOO”

Output: False



'''


import re

arr = list(re.sub('[\[\]"]','',input()).split(', '))


d = { }
c=0
ch = 0
for i in arr[0]:
    if i in d:
        if d[i] != arr[1][c]:
            print('False')
            ch=1
            break
    else:
        d[i] = arr[1][c]
    c+=1
 
if ch==0:
    print('True')