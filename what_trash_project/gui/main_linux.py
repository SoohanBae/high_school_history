import sys
from time import sleep
import threading
import pymysql
import json
import re


from parsers import *

from Ui_basic2 import *
    


from gpiozero import Button

b_right = Button(26)
b_down = Button(19)
b_left = Button(12)
b_center = Button(6)
b_up = Button(5)

jojungsuk = -1
jojungsuks = [datetime.datetime.now() for i in range(3)]

def change_data(school_name = None, school_code = None, location = None, breakfast= None, lunch= None, dinner= None):

    jstring = open("data.json","r",encoding='utf-8').read()
    dicts = json.loads(jstring)

    print(dicts)

    data = [school_name,school_code,breakfast,lunch,dinner]
    data_name = ['school_name','school_code','breakfast','lunch','dinner']
    loc_dic = {
        '서울특별시' : 'sen',
        '부산광역시' : 'pen',
        '대구광역시' : 'dge',
        '인천광역시' : 'ice',
        '광주광역시' : 'gen',
        '대전광역시' : 'dje',
        '울산광역시' : 'use',
        '세종특별자치시' : 'sje',
        '경기도' : 'goe',
        '강원도' : 'kwe',
        '충청북도' : 'cbe',
        '충청남도' : 'cne',
        '전라북도' : 'jbe',
        '전라남도' : 'jne',
        '경상북도' : 'kbe',
        '경상남도' : 'gne',
        '제주특별자치도' : 'jje'
    }

    if location != None:
        dicts['location'] = loc_dic[location]

    for i in range(len(data)):
        if data[i] != None:
            dicts[data_name[i]] = data[i]

    

    
    jstring2 = json.dumps(dicts,indent=4)
    f = open("data.json","w",encoding='utf-8')
    f.write(jstring2)
    f.close()

def get_data(name):
    jstring = open("data.json","r",encoding='utf-8').read()
    dicts = json.loads(jstring)

    return dicts[name]

def kit2_screen(rows,title,index = 0):
    
    set2_list = [ui.l1,ui.l2,ui.l3,ui.l4,ui.l5,ui.l6,ui.l7,ui.l8,ui.l9,ui.l10,ui.l11,ui.l12,ui.l13,ui.l14,ui.l15]
    set2_back_list = [ui._1_1,ui._1_2,ui._1_3,ui._1_4,ui._1_5,ui._1_6,ui._1_7,ui._1_8,ui._1_9,ui._1_10,ui._1_11,ui._1_12,ui._1_13,ui._1_14,ui._1_15]
    set2_chk_list = [ui.c_1,ui.c_2,ui.c_3,ui.c_4,ui.c_5,ui.c_6,ui.c_7,ui.c_8,ui.c_9,ui.c_10,ui.c_11,ui.c_12,ui.c_13,ui.c_14,ui.c_15]
    ui.set_kit_1_title.setText(title)
    ui.set_kit_1_title.setStyleSheet('color : rgb(255,255,255);\nbackground-color: rgb(0,139,188);\n')
    sleep(0.01)
    for i in range(0,len(set2_list)):
        set2_list[i].setStyleSheet('color : rgb(255,255,255);')
        set2_back_list[i].setStyleSheet('')
        set2_chk_list[i].setVisible(False)
        set2_list[i].setText('')


    for i in range(0,min(len(set2_list),len(rows))):
        set2_list[i].setText(rows[i][0])
        set2_chk_list[i].setVisible(True)

    set2_back_list[index].setStyleSheet('background-color: rgb(0,161,218);')
    
    
    ui.tabWidget.setCurrentIndex(1)
    sleep(0.5)

    while(True):
        if b_left.is_pressed:
            return -1
        elif b_right.is_pressed or b_center.is_pressed:
            return index
        elif b_down.is_pressed:

            index = min(index + 1, len(rows)-1)
            set2_back_list[index-1].setStyleSheet('')
            set2_back_list[index].setStyleSheet('background-color: rgb(0,161,218);')
        elif b_up.is_pressed:
            index = max(index - 1, 0)
            set2_back_list[index+1].setStyleSheet('')
            set2_back_list[index].setStyleSheet('background-color: rgb(0,161,218);')


        sleep(0.1)



def kit1_screen(rows,title,index = 0):
    
    set2_list = [ui.l21,ui.l22,ui.l23,ui.l24,ui.l25,ui.l26,ui.l27,ui.l28,ui.l29,ui.l210,ui.l211,ui.l212,ui.l213,ui.l214]
    set2_back_list = [ui._2_1,ui._2_2,ui._2_3,ui._2_4,ui._2_5,ui._2_6,ui._2_7,ui._2_8,ui._2_9,ui._2_10,ui._2_11,ui._2_12,ui._2_13,ui._2_14]
    
    ui.set_kit_2_title.setText(title)
    ui.set_kit_2_title.setStyleSheet('color : rgb(255,255,255);\nbackground-color: rgb(0,139,188);\n')
    sleep(0.01)
    sci = min(len(set2_list)-1,index)
    arange =  max(0,index - len(set2_list)+1)

    
    for i in range(0,len(set2_list)):
        set2_list[i].setStyleSheet('color : rgb(255,255,255);')
        set2_back_list[i].setStyleSheet('')
        set2_list[i].setText(rows[i+arange][0])
    set2_back_list[sci].setStyleSheet('background-color: rgb(0,161,218);')
    
    
    ui.tabWidget.setCurrentIndex(2)
    sleep(0.5)

    while(True):
        if b_left.is_pressed:
            return -1
        elif b_right.is_pressed or b_center.is_pressed:
            return index
        elif b_down.is_pressed:

            if sci == len(set2_list) - 1:
                arange = min(arange + 1,len(rows)-len(set2_list))
                for i in range(len(set2_list)):
                    set2_list[i].setText(rows[arange+i][0])

            index = min(index+1, len(rows)-1)
            sci = min(sci+1, len(set2_list) - 1)
            set2_back_list[sci-1].setStyleSheet('')
            set2_back_list[sci].setStyleSheet('background-color: rgb(0,161,218);')
            
            
            
        elif b_up.is_pressed:
            if sci == 0:
                arange = max(arange-1, 0)
                for i in range(len(set2_list)):
                    set2_list[i].setText(rows[arange+i][0])

            index = max(index-1, 0)
            sci = max(sci-1, 0)
            set2_back_list[sci+1].setStyleSheet('')
            set2_back_list[sci].setStyleSheet('background-color: rgb(0,161,218);')


        sleep(0.1)

#ctedit
def set9_screen():
    ui.tabWidget.setCurrentIndex(6)
    sleep(0.5)
    while(True):
        if b_left.is_pressed or b_right.is_pressed or b_up.is_pressed or b_down.is_pressed or b_center.is_pressed:
            print("event")
            
            break
        sleep(0.1)
    ui.tabWidget.setCurrentIndex(0)


#eat_trash
def set8_screen(index = 0):
    

    set2_list = [ui.l_3_1,ui.l_3_2,ui.l_3_3,ui.l_3_4,ui.l_3_5,ui.l_3_6,ui.l_3_7,ui.l_3_8,ui.l_3_9,ui.l_3_10,ui.l_3_11,ui.l_3_12,ui.l_3_13,ui.l_3_14,ui.l_3_15]
    set2_back_list = [ui._3_1,ui._3_2,ui._3_3,ui._3_4,ui._3_5,ui._3_6,ui._3_7,ui._3_8,ui._3_9,ui._3_10,ui._3_11,ui._3_12,ui._3_13,ui._3_14,ui._3_15]
    set2_chack_list = [ui.l2_3_1,ui.l2_3_2,ui.l2_3_3,ui.l2_3_4,ui.l2_3_5,ui.l2_3_6,ui.l2_3_7,ui.l2_3_8,ui.l2_3_9,ui.l2_3_10,ui.l2_3_11,ui.l2_3_12,ui.l2_3_13,ui.l2_3_14,ui.l2_3_15]
    

    rows = set2_text_set()
    print(rows)

    
    for i in range(0,len(set2_list)):
        set2_list[i].setStyleSheet('color : rgb(255,255,255);')
        set2_back_list[i].setStyleSheet('')
        set2_list[i].setText('')
        set2_chack_list[i].setVisible(False)

    for i in range(0,min(len(set2_list),len(rows))):
        set2_list[i].setText(rows[i])
        set2_chack_list[i].setVisible(True)

    set2_back_list[index].setStyleSheet('background-color: rgb(0,161,218);')
    
    
    ui.tabWidget.setCurrentIndex(5)
    sleep(0.5)

    while(True):
        if b_left.is_pressed:
            break
        elif b_right.is_pressed or b_center.is_pressed:
            
            curs.execute("select distinct trashName from menu;")
            row2 = curs.fetchall()
            row3 = list(row2)
            print(row3)
            row3.insert(0,['없음'])
            
            ina = kit2_screen(row3,'쓰레기 설정')
            ui.tabWidget.setCurrentIndex(5)

            if ina >= 0:

                rows[index] = rows[index].split(' - ')[0]
                curs.execute("delete from menu where menuName = '%s';" % (rows[index]))
                
                if ina > 0:
                    print("insert into menu values('0', %s', '%s');" % (rows[index], row3[ina][0]))
                    curs.execute("insert into menu values('0', '%s', '%s');" % (rows[index], row3[ina][0]))

                conn.commit()

                rows = set2_text_set()
                for i in range(0,min(len(set2_list),len(rows))):
                    set2_list[i].setText(rows[i])
                    set2_chack_list[i].setVisible(True)


        elif b_down.is_pressed:

            index = min(index + 1, len(rows)-1)
            set2_back_list[index-1].setStyleSheet('')
            set2_back_list[index].setStyleSheet('background-color: rgb(0,161,218);')
        elif b_up.is_pressed:
            index = max(index - 1, 0)
            set2_back_list[index+1].setStyleSheet('')
            set2_back_list[index].setStyleSheet('background-color: rgb(0,161,218);')


        sleep(0.1)

    ui.tabWidget.setCurrentIndex(0)
    
def set2_text_set():

    rows = re.sub('[^ \u3131-\u3163\uac00-\ud7a3()]+','<br/>',get_diet(jojungsuk,get_data("school_code"),4,get_data('location'),datetime.datetime.now())).split('<br/>')[:-1] #change plese
    curs.execute("select * from menu;")
    row2 = curs.fetchall()
    
    s = ''

    for i in range(len(rows)):
        for j in range(len(row2)):
            if rows[i] == row2[j][1]:
                rows[i] = rows[i] + ' - ' + row2[j][2]
                s += row2[j][2] + '\n'

    if len(s) == 0:
        s = '없음'

    ui.trash_menu.setText(s)

    return rows

#eat time
def set7_screen(index):
    

    st = ['breakfast','lunch','dinner']
    et = ['조식','중식','석식']
    
    tmp = get_data(st[index]).split(':')
    m = int(tmp[0])
    s = int(tmp[1])
    ui.set_time_change_value.setText('%02d:%02d'%(m,s))
    ui.set_time_change_title.setText(et[index]+"종료시간")

    ui.tabWidget.setCurrentIndex(4)
    sleep(0.5)



    while(True):
        if b_center.is_pressed:
            if index == 0:
                change_data(breakfast='%02d:%02d'%(m,s))
            elif index == 1:
                change_data(lunch='%02d:%02d'%(m,s))
            elif index == 2:
                change_data(dinner='%02d:%02d'%(m,s))

            change_jojungsuk()
            break
        elif b_left.is_pressed:
            s = (s+59)%60
            ui.set_time_change_value.setText('%02d:%02d'%(m,s))
        elif b_right.is_pressed:
            s = (s+1)%60
            ui.set_time_change_value.setText('%02d:%02d'%(m,s))
        elif b_up.is_pressed:
            m = (m+23)%24
            ui.set_time_change_value.setText('%02d:%02d'%(m,s))
        elif b_down.is_pressed:
            m = (m+1)%24
            ui.set_time_change_value.setText('%02d:%02d'%(m,s))
            
        sleep(0.1)


    ui.tabWidget.setCurrentIndex(3)
   



def set6_screen():
   
    
    set1_list = [ui.set_eat_1, ui.set_eat_2, ui.set_eat_3]
    for i in set1_list:
        i.setStyleSheet('')
    
    set1_list[0].setStyleSheet('background-color: rgb(0,161,218);')
    
    index = 0
    
    ui.tabWidget.setCurrentIndex(3)
    sleep(0.5)
    while(True):
        if b_left.is_pressed:
            break
        elif b_right.is_pressed or b_center.is_pressed:
                
            set7_screen(index)
        elif b_down.is_pressed:
            index = min(index+1, len(set1_list)-1)
            set1_list[index-1].setStyleSheet('')
            set1_list[index].setStyleSheet('background-color: rgb(0,161,218);')
        elif b_up.is_pressed:
            index = max(index-1, 0)
            set1_list[index+1].setStyleSheet('')
            set1_list[index].setStyleSheet('background-color: rgb(0,161,218);')
        

        sleep(0.1)

    ui.tabWidget.setCurrentIndex(0)

def set2_screen():
    
    
    dan_1i = 0
    dan_2i = 0
    dan_3i = 0 
    dan_4i = 0

    while(True):
        curs.execute("select distinct location_1 from school_3 order by location_1;")
        dan_1s = curs.fetchall()
        dan_1i = kit1_screen(dan_1s,'시/도',dan_1i)
        
        
        if dan_1i == -1:
            break
        
        dan_2i = 0
        while(True):
            
            curs.execute("select distinct location_2 from school_3 where location_1 = '"+dan_1s[dan_1i][0]+"' order by location_2 ;")
            dan_2s = curs.fetchall()
            if len(dan_2s) <= 15:     
                dan_2i = kit2_screen(dan_2s,'시/군/구',dan_2i)
            else:
                dan_2i = kit1_screen(dan_2s,'시/군/구',dan_2i)
            
            
            
            if dan_2i == -1:
                break

            dan_3i = 0
            while(True):
            
                dan_3s = [["초등학교"],["중학교"],["고등학교"],["특수학교/각종학교/기타"]]
                     
                dan_3i = kit2_screen(dan_3s,'학교 분류',dan_3i)
                
                
                
                if dan_3i == -1:
                    break


                dan_4i = 0
                while(True):
                    if dan_3i == 3:
                        curs.execute("select distinct school_name,school_code from school_3 where location_1 = '"+dan_1s[dan_1i][0]+"' and location_2 = '"+dan_2s[dan_2i][0]+"' and school_divi not in(2,3,4) order by school_divi ;")
                    else:
                        curs.execute("select distinct school_name,school_code from school_3 where location_1 = '"+dan_1s[dan_1i][0]+"' and location_2 = '"+dan_2s[dan_2i][0]+"' and school_divi = '"+str(dan_3i+2)+"' order by school_divi ;")
                    
                    print("select distinct school_name,school_code from school_3 where location_1 = '"+dan_1s[dan_1i][0]+"' and location_2 = '"+dan_2s[dan_2i][0]+"' and school_divi = '"+str(dan_3i+2)+"' order by school_divi ;")
                    
                    dan_4s = curs.fetchall()
                    if len(dan_4s) <= 15:     
                        dan_4i = kit2_screen(dan_4s,'학교선택',dan_4i)
                    else:
                        dan_4i = kit1_screen(dan_4s,'학교선택',dan_4i)
                    

                    if dan_4i == -1:
                        break

                    ui.tabWidget.setCurrentIndex(0)
                    change_data(school_name = dan_4s[dan_4i][0],school_code = dan_4s[dan_4i][1],location= dan_1s[dan_1i][0])
                    ui.trash_school.setText(dan_4s[dan_4i][0])

                    next_meal()
                    now_meal()
                    return 

                    #--------------------------------------------------------event process


    

    ui.tabWidget.setCurrentIndex(0)


#set main
def set1_screen():
    
    
    set1_list = [ui.set_main_1,ui.set_main_2, ui.set_main_3, ui.set_main_4]
    for i in set1_list:
        i.setStyleSheet('')
    
    set1_list[0].setStyleSheet('background-color: rgb(0,161,218);')
    
    index = 0


    ui.tabWidget.setCurrentIndex(0)
    ui.tabWidget.setVisible(True)
    sleep(0.5)
    while(True):
        if b_left.is_pressed:
            break
        elif b_right.is_pressed or b_center.is_pressed:
            if index == 0:
                set2_screen()
            elif index == 1:
                set6_screen()
            elif index == 2:
                set8_screen()
            elif index == 3:
                set9_screen()
        elif b_down.is_pressed:
            index = min(index+1, len(set1_list)-1)
            set1_list[index-1].setStyleSheet('')
            set1_list[index].setStyleSheet('background-color: rgb(0,161,218);')
        elif b_up.is_pressed:
            index = max(index-1, 0)
            set1_list[index+1].setStyleSheet('')
            set1_list[index].setStyleSheet('background-color: rgb(0,161,218);')
        

        sleep(0.1)

    ui.tabWidget.setVisible(False)
    

def next_meal():
    print('next_meal_jojungsuk',jojungsuk)
    print(jojungsuks[0],jojungsuks[1],jojungsuks[2])

    a = 0
    if jojungsuk == 0:
        now = datetime.datetime.now() + datetime.timedelta(days= 1)
        a = 1
    else:    
        now = datetime.datetime.now() + datetime.timedelta(days= jojungsuk//3)

    ui.next_menu.setText(re.sub('[^ \u3131-\u3163\uac00-\ud7a3()]+','<br/>',get_diet((jojungsuk%3)+1+a,get_data("school_code"),4,get_data('location'),now)).replace('<br/>','\n'))

def now_meal():
    rows = re.sub('[^ \u3131-\u3163\uac00-\ud7a3()]+','<br/>',get_diet(jojungsuk,get_data("school_code"),4,get_data('location'),datetime.datetime.now())).split('<br/>')[:-1] #change plese
    curs.execute("select * from menu;")
    row2 = curs.fetchall()

    s = ''

    for i in range(len(rows)):
        for j in range(len(row2)):
            if rows[i] == row2[j][1]:
                s += row2[j][2] + '\n'

    
    if len(s) == 0:
        s = '없음'

    ui.trash_menu.setText(s)


def now_time_thread():
    now = datetime.datetime.now()
    ui.trash_day.setText(('%04d년 %02d월 %02d일 %02d:%02d') % (now.year, now.month, now.day, now.hour, now.minute) )
    aday = now.day

    while now.second != 59:
        now = datetime.datetime.now()
        sleep(1)

    while True:
        now = datetime.datetime.now()
        print('now_time : ',now.minute)
        ui.trash_day.setText(('%04d년 %02d월 %02d일 %02d:%02d') % (now.year, now.month, now.day, now.hour, now.minute) )
        chk_jojungsuk()
        if aday != now.day:
            aday = now.day
            change_jojungsuk()
        
        sleep(60)




def change_jojungsuk():
    global jojungsuks

    ts = ['breakfast','lunch','dinner']

    now = datetime.datetime.now()

    for i in range(3):
        
        h, m = get_data(ts[i]).split(':')
        print(h,m)
        jojungsuks[i] = now.replace(hour = int(h), minute = int(m))

       

    chk_jojungsuk()


def chk_jojungsuk():
    global jojungsuk

    now = datetime.datetime.now()

    for i in range(3):
        print(jojungsuks[i])
    
    print(datetime.datetime.now()) 

    for i in range(3):
        if jojungsuks[i] > now:
            if jojungsuk == i+1:
                return
             
            jojungsuk = i + 1
            print('jojungsuk = ', jojungsuk)
            now_meal()
            next_meal()
            return
    
    if jojungsuk != 0:
        jojungsuk = 0
        print('jojungsuk = ', jojungsuk)
        now_meal()
        next_meal()
    

#----------------------main------------------------------

def main_screen():
    ui.tabWidget.setVisible(False)
    sleep(0.5)
    while(True):
        if b_left.is_pressed or b_right.is_pressed or b_up.is_pressed or b_down.is_pressed or b_center.is_pressed:
            print("event")
            set1_screen()         
            sleep(0.5)
        sleep(0.1)



app = QtWidgets.QApplication(sys.argv)
MainWindow = QtWidgets.QMainWindow()
MainWindow.showFullScreen()
#global ui
ui = Ui_MainWindow()
ui.setupUi(MainWindow)



conn = pymysql.connect(host='localhost', user='root', password='1234', db='korea_school', charset='utf8')
curs = conn.cursor()


ui.trash_school.setText(get_data('school_name'))

change_jojungsuk()







thread = threading.Thread(target=main_screen, args=())  #쓰레드 생성
thread.daemon = True
thread.start()

thread = threading.Thread(target=now_time_thread, args=())  #쓰레드 생성
thread.daemon = True
thread.start()


    


app.exec_()
conn.close()
    
        
    
    

    