#백준 11637번 문제 - 인기투표
#https://www.acmicpc.net/problem/11637

T = int(input())

outstring = ["majority winner", "minority winner", "no winner"]

output = [[0 for i in range(2)] for j in range(T)]
for t in range(T):
    n = int(input())
    arr = [0 for i in range(n)]
    for i in range(n):
        arr[i] = int(input())
    
    if(arr.count(max(arr)) >= 2):
        output[t][0] = 2
        output[t][1] = 0
    elif(max(arr) <= sum(arr)/2):
        output[t][0] = 1
        output[t][1] = arr.index(max(arr)) + 1
    else:
        output[t][0] = 0
        output[t][1] = arr.index(max(arr)) + 1

for i in range(T):
    if(output[i][1] != 0):
        print(outstring[output[i][0]], output[i][1])
    else:
        print(outstring[output[i][0]])