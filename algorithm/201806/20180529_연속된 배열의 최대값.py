'''
매일 프로그래밍 문제

정수 배열(int array)가 주어지면 가장 큰 이어지는 원소들의 합을 구하시오. 단, 시간복잡도는 O(n).

예제}
Input: [-1, 3, -1, 5]
Output: 7 // 3 + (-1) + 5

Input: [-5, -3, -1]
Output: -1 // -1

Input: [2, 4, -2, -3, 8]
Output: 9 // 2 + 4 + (-2) + (-3) + 8

'''

#[-1, 3, 4, -2, 8]


import re
n = list(map(int,re.sub('[\[\]]','',input()).split(",")))

c = 0
sum = n[0]

for i in range(1, len(n)):
    t = 0
    if(c + 1 == i):
        t = n[i]

    sum = max(sum + t,n[i])
    c = i


print("max =", sum)