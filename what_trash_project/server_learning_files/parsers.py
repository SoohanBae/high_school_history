#2018.03.27

import requests, re
from bs4 import BeautifulSoup
import datetime

def get_diet(schMmealScCode, schulCode, schulCrseScCode,locationCode, g_time):
    #int 1조식2중식3석식
    


    schYmd = g_time.strftime('%Y.%m.%d')
    num = (g_time.weekday()+1) % 7  #int 요청할 날짜의 요일 0월 1화 2수 3목 4금 5토 6일 파싱한 데이터의 배열이 일요일부터 시작되므로 1을 더해줍니다.

    URL = (
            "http://stu.%s.go.kr/sts_sci_md01_001.do?" % (locationCode) + 
            "schulCode=%s" % (schulCode) + 
            "&schulCrseScCode=%d" % (schulCrseScCode) + 
            "&schulKndScCode=%02d" % (schulCrseScCode) + 
            "&schMmealScCode=%d&schYmd=%s" % (schMmealScCode, schYmd)
        )
    #http://stu.AAA.go.kr/ 관할 교육청 주소 확인해주세요.
    #schulCode= 학교고유코드
    #schulCrseScCode= 1유치원2초등학교3중학교4고등학교
    #schulKndScCode= 01유치원02초등학교03중학교04고등학교

    #기존 get_html 함수부분을 옮겨왔습니다.
    try:
        html = ""
        resp = requests.get(URL)
        if resp.status_code == 200 : #사이트가 정상적으로 응답할 경우
            html = resp.text
        soup = BeautifulSoup(html, 'html.parser')
        element_data = soup.find_all("tr")
        element_data = element_data[2].find_all('td')
    
        element = str(element_data[num])

        #filter
        element_filter = ['[', ']', '<td class="textC last">', '<td class="textC">', '</td>', '&amp;', '(h)', '.']
        for element_string in element_filter :
            element = element.replace(element_string, '')
        
        #모든 공백 삭제
        element = re.sub(r"\d", "", element)

    #급식이 없을 경우
    except:
        element = "" # 공백 반환
    return element


if __name__ == '__main__':
    a = '1234'
    meal1 = get_diet(2,"D100000282",4,"dge",datetime.datetime(2016,3,4))
    meal2 = get_diet(3,"D100000282",4,"dge",datetime.datetime(2018,10,30))
    print(datetime.datetime(2018,10,30) + datetime.timedelta(days=1))
    print(meal1)
    print(meal2)
