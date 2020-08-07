#https://www.acmicpc.net/problem/2456
#백준 2456번 문제 - 나는 학급회장이다


data = [[0 for j in range(4)] for i in range(5)]


n = int(input())

for i in range(n):
    l = [int(s) for s in input().split(" ")]
    for j in range(1,4):
        data[4][j] += l[j-1]
        data[l[j-1]][j] +=1

data[1][0] = max(data[1]) + 1

j = data[4].index(min(data[4][1:4]))

for i in range(5):
    data[i][j] = 0

#print(data)
i = 4
while data[i].count(max(data[i])) >= 2:
    i-=1


print(data[i].index(max(data[i])),max(data[4]))

