from parsers import *
import re

MAX_DATE = 880 #n일의 데이터 추출

s = ''
data = [0 for i in range(MAX_DATE * 3)]
nowdate = datetime.datetime(2016,6,1)

for i in range(MAX_DATE):
    for j in range(1,4):
        data[i*3+j-1] = get_diet(j,"D100000282",4,"dge",nowdate)
    nowdate = nowdate + datetime.timedelta(days=1)
    print(i)

s = ''.join(data)
s = s.replace(' ','')
#줄 바꿈 처리
s = s.replace('<br/>', '\n')

f = open('data800.txt','w')
f.write(s)
f.close()
print(s)