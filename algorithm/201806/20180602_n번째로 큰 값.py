'''
13 ======
안녕하세요, 매일프로그래밍 이번주 문제입니다.

정수 배열(int array)과 정수 N이 주어지면, N번째로 큰 배열 원소를 찾으시오.


예제)

Input: [-1, 3, -1, 5, 4], 2

Output: 4


Input: [2, 4, -2, -3, 8], 1

Output: 8


Input: [-5, -3, 1], 3

Output: -5
'''


import re
arr = list(map(int,re.sub('[\[\]]','',input()).split(",")))

n = arr[-1]
del(arr[-1])

arr = sorted(arr)
print(arr)
print(arr[len(arr)-n])