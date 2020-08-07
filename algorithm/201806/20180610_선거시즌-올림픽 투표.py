#백준 10040번 문제 - 투표
#https://www.acmicpc.net/problem/10040
n, m = input().split(" ")

n = int(n)
m = int(m)

a = []

for i in range(n):
    a.append(int(input()))

b = [0 for i in range(n)]

for i in range(m):
    t = int(input())
    for j in range(n):
        if a[j] <= t:
            b[j]+=1
            break

print(b.index(max(b))+1)

     