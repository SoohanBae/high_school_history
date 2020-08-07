# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'g:\what_trash_project\python module\design\basic.ui'
#
# Created by: PyQt5 UI code generator 5.11.2
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(1920, 1080)
        MainWindow.setStyleSheet("background-color: black;")
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setStyleSheet("")
        self.centralwidget.setObjectName("centralwidget")
        self.trash_menu = QtWidgets.QLabel(self.centralwidget)
        self.trash_menu.setGeometry(QtCore.QRect(475, 240, 970, 721))
        font = QtGui.QFont()
        font.setFamily("배달의민족 도현")
        font.setPointSize(90)
        self.trash_menu.setFont(font)
        self.trash_menu.setMouseTracking(True)
        self.trash_menu.setStyleSheet("color : rgb(255,255,255)\n"
"")
        self.trash_menu.setTextFormat(QtCore.Qt.AutoText)
        self.trash_menu.setAlignment(QtCore.Qt.AlignCenter)
        self.trash_menu.setObjectName("trash_menu")
        self.trash_title = QtWidgets.QLabel(self.centralwidget)
        self.trash_title.setGeometry(QtCore.QRect(550, 130, 800, 101))
        font = QtGui.QFont()
        font.setFamily("서울남산체 EB")
        font.setPointSize(45)
        self.trash_title.setFont(font)
        self.trash_title.setStyleSheet("color : rgb(217,217,217)\n"
"")
        self.trash_title.setAlignment(QtCore.Qt.AlignCenter)
        self.trash_title.setObjectName("trash_title")
        self.trash_day = QtWidgets.QLabel(self.centralwidget)
        self.trash_day.setGeometry(QtCore.QRect(1470, 1010, 441, 41))
        font = QtGui.QFont()
        font.setFamily("서울남산체 B")
        font.setPointSize(28)
        self.trash_day.setFont(font)
        self.trash_day.setStyleSheet("color : rgb(191,191,191)\n"
"")
        self.trash_day.setAlignment(QtCore.Qt.AlignRight|QtCore.Qt.AlignTrailing|QtCore.Qt.AlignVCenter)
        self.trash_day.setObjectName("trash_day")
        self.trash_school = QtWidgets.QLabel(self.centralwidget)
        self.trash_school.setGeometry(QtCore.QRect(1460, 960, 441, 41))
        font = QtGui.QFont()
        font.setFamily("서울남산체 B")
        font.setPointSize(28)
        self.trash_school.setFont(font)
        self.trash_school.setStyleSheet("color : rgb(191,191,191)\n"
"")
        self.trash_school.setAlignment(QtCore.Qt.AlignRight|QtCore.Qt.AlignTrailing|QtCore.Qt.AlignVCenter)
        self.trash_school.setObjectName("trash_school")
        MainWindow.setCentralWidget(self.centralwidget)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.trash_menu.setText(_translate("MainWindow", "<html><head/><body><p>달걀껍질</p><p>닭 뼈</p><p>양파껍질</p></body></html>"))
        self.trash_title.setText(_translate("MainWindow", "-일반쓰레기-"))
        self.trash_day.setText(_translate("MainWindow", "YYYY년 MM월 DD일(조식)"))
        self.trash_school.setText(_translate("MainWindow", "대구소프트웨어고등학교"))

