'''
그냥 떠올라서 푼 문제
3 6 9 게임을 할때 처럼 1부터 1000까지 출력하세요 (String 연산 금지)

'''


max = 1000

for i in range(1,max+1,1):
        c = True
        n = i

        while n >= 1:

                if n%10%3 == 0 and n%10 !=0:
                        c=False;
                        print("짝",end="")

                n = n//10
        


        if c:
                print(i,end='')
        
        print()