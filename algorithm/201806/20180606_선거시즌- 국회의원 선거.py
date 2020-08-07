#https://www.acmicpc.net/short/status/1417
#백준 1417번 문제 - 국회의원 선거
n = int(input())
arr = [0 for i in range(n)]

for i in range(n):
    arr[i] = int(input())

c = 0
while not(max(arr) == arr[0] and arr.count(max(arr)) == 1):
    i = arr.index(max(arr),1,n) 
    arr[0]+=1
    arr[i]-=1
    c+=1
    print(arr)

print(c)

'''
3
9
13
13
'''