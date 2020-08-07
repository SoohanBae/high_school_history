'''
피보나치 배열은 0과 1로 시작하며, 다음 피보나치 수는 바로 앞의 두 피보나치 수의 합이 된다. 정수 N이 주어지면, N보다 작은 모든 짝수 피보나치 수의 합을 구하여라.

예제)
Input: N = 12
Output: 10 // 0, 1, 2, 3, 5, 8 중 짝수인 2 + 8 = 10.

'''

a = 0
b = 1
c = 1
s = 0
n = int(input())
print(0,end=' ')

while c < n:
    print(str(c),end=' ')
    if c % 2 == 0:
        s += c

    c = a+b
    a = b
    b = c

print("sum = "+str(s))
    