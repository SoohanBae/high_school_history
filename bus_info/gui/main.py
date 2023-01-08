import sys
from time import sleep
import threading
import re
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
import urllib.request
from urllib import parse
import requests
import xml.etree.ElementTree as ET
import math
from Ui_design import *


img_sleep = 15
data_sleep = 5
jamsiwho = 180


def img_thread():

    while(True):
        img_text = requests.get(
            "http://10.80.161.248:8080/Image_server/getlink.jsp").text.replace("\r", "").replace("\n", "")
        img_list = img_text.split("|")

        for i in range(0, (len(img_list)-1)):
            img_get(img_list[i])
            sleep(img_sleep)


def img_get(url):
    try:

        image = urllib.request.urlopen(url).read()
        pixmap = QtGui.QPixmap()
        pixmap.loadFromData(image)
        ui.imgPanel.setPixmap(pixmap)
        ui.imgPanel.setScaledContents(True)
    except:
        print("이미지 없네")


def get_location(nodeid, routeid, arrprev):

    url = urllib.parse.quote(
        "GnI6jLB1CE+8FW+gJ+JiXHGMbvYR0TSHxkKdJmVCX4z1/YDusIIIlgC5FjkUTf+I7dcju568zwerhryR5NaEzg==")

    data = requests.get("http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?serviceKey=" +
                        url+"&pageNo=1&numOfRows=100000&cityCode=38030&routeId="+routeid).text
    root = ET.fromstring(data)

    nodeord = -1

    for item in root.iter('item'):
        if(str(item.find('nodeid').text) == str(nodeid)):

            nodeord = int(item.find('nodeord').text)
            break
    for item in root.iter('item'):
        if(int(item.find('nodeord').text) == nodeord-arrprev):

            return str(item.find('nodenm').text) + "("+str(arrprev)+"전)"


def data_thread():
    while(True):
        data = requests.get(
            "http://openapi.tago.go.kr/openapi/service/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList?serviceKey=GnI6jLB1CE%2B8FW%2BgJ%2BJiXHGMbvYR0TSHxkKdJmVCX4z1%2FYDusIIIlgC5FjkUTf%2BI7dcju568zwerhryR5NaEzg%3D%3D&cityCode=38030&nodeId=JJB381227002&numOfRows=100000").text

        root = ET.fromstring(data)

        cnt = int(root.find('body').find('totalCount').text)
        na = math.ceil(cnt/6)

        if(na == 0):
            sleep(data_sleep)
            next

        cnt_list = []
        for i in range(na):
            cnt_list.append(cnt//na)
            if(i < cnt % (cnt//na)):
                cnt_list[i] += 1

        items = root.iter('item')

# ====================================
        i = 0
        j = 0

        no_list = []
        time_list = []
        nolo_list = []

        now_str = ""

        for item in root.iter('item'):
            t = int(item.find('arrtime').text)

            if(t <= jamsiwho):
                now_str += str(item.find('routeno').text) + "  "

        ui.now_num.setText(now_str)

        for item in items:
            i += 1

            no_list.append(item.find('routeno').text)
            t = int(item.find('arrtime').text)
            time_list.append(str(round(t/60))+"분")

            nolo_list.append(get_location(item.find('nodeid').text,
                                          item.find('routeid').text, int(item.find('arrprevstationcnt').text)))

            if i == cnt_list[j]:
                ui.bus_num.setText(
                    '<html><head/><body><p style="line-height: 1.15"><span>'+'<br>'.join(no_list)+'</span></p></body></html>')
                ui.bus_time.setText(
                    '<html><head/><body><p style="line-height: 1.15"><span>'+'<br>'.join(time_list)+'</span></p></body></html>')

                ui.bus_nolo.setText(
                    '<html><head/><body><p style="line-height: 2.57"><span>'+'<br>'.join(nolo_list)+'</span></p></body></html>')

                i = 0
                j += 1

                no_list = []
                time_list = []
                nolo_list = []

                sleep(data_sleep)


app = QtWidgets.QApplication(sys.argv)
MainWindow = QtWidgets.QMainWindow()
MainWindow.showFullScreen()
# global ui
ui = Ui_MainWindow()
ui.setupUi(MainWindow)

MainWindow.setCursor(QtCore.Qt.BlankCursor)


thread = threading.Thread(target=img_thread, args=())  # 쓰레드 생성
thread.daemon = True
thread.start()


thread = threading.Thread(target=data_thread, args=())  # 쓰레드 생성
thread.daemon = True
thread.start()

app.exec_()
