import pymysql
 
# MySQL Connection 연결
conn = pymysql.connect(host='localhost', user='root', password='1234', db='korea_school', charset='utf8')
 
# Connection 으로부터 Cursor 생성
curs = conn.cursor()
 
# SQL문 실행
sql = "select * from school_3"
curs.execute(sql)
 
# 데이타 Fetch
rows = curs.fetchall()
print(rows[400][0])     # 전체 rows

 
# Connection 닫기
conn.close()
