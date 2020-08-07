'''

안녕하세요, 매일프로그래밍 이번주 문제입니다.

정수로된 배열이 주어지면, 각 원소가 자신을 뺀 나머지 원소들의 곱셈이 되게하라.

단, 나누기 사용 금지, O(n) 시간복잡도


예제)

input: [1, 2, 3, 4, 5]

output: [120, 60, 40, 30, 24]
#[1, 2, 3, 4, 5]
'''


import re
n = list(map(int,re.sub('[\[\]]','',input()).split(",")))
a = list(1 for i in range(len(n)))
b = list(1 for i in range(len(n)))

nu = 1
for i in range(len(n)):
    a[i] = nu
    nu *= n[i]
#print(a)

nu = 1
for i in range(len(n)-1,-1,-1):
    b[i] = nu
    nu *= n[i]

#print(b)

for i in range(len(n)):
    print(a[i]*b[i],end=' ')

    