/*-------------------------------------
c���� ����ã�� ���α׷� ����� ������Ʈ
������ : �����   �Ҽ� : �뱸����Ʈ�������б�
������Ʈ �������� : 5�� 17��   «���� �ð����� ƴƴ�� �ڵ�

�� �ڵ忡 ���� ���۱��� ����� ���� ������ ���� ����� ���ؾ� �մϴ�.

������� 4.0

--��ġ��Ʈ--
5�� 17�� 1.0 -- �⺻���� Ʋ �ϼ�
5�� 18�� 1.1 -- 0Ŭ���� �ֺ��� �ִ� 0���� ���� ����ϰ� ����(����Լ� ���)
5�� 18�� 1.2 -- �ΰ���� �߰��� ���â ����
5�� 19�� 1.3 -- �� �߰��� �ܼ�â �߰��� ���α׷� ����ȭ
5�� 21�� 1.4 -- ������ ������ ������ ȭ�� ����
5�� 22�� 1.5 -- ���ڼ� üũ��� �߰� ���׼���
5�� 27�� 2.0 -- ���콺 Ŭ�� �߰�
5�� 31�� 3.0 -- ���콺 ��� �߰� �� ������ ����, �ð���� �߰�
6�� 7��  3.1 -- ���콺 ��� �߰� �� �ε�ȭ�� ������ ����
6�� 9��  4.0 -- ���� ������ ��� �߰� �� ���� �߰� (������)
----------------------------------------*/
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <conio.h>
#include <mmsystem.h>
#pragma comment(lib, "winmm.lib")
#include <time.h>

#define COL         GetStdHandle(STD_OUTPUT_HANDLE)
#define BAE         SetConsoleTextAttribute(COL, 0x0008);
#define BAISE       SetConsoleTextAttribute(COL, 0x0007);
#define BLUE_1      SetConsoleTextAttribute(COL, 0x0009);
#define GREEN_2     SetConsoleTextAttribute(COL, 0x000a);
#define RED_3       SetConsoleTextAttribute(COL, 0x000c);
#define PURPLE_4    SetConsoleTextAttribute(COL, 0x000d);
#define SU_5        SetConsoleTextAttribute(COL, 0x000b);
#define SU_6        SetConsoleTextAttribute(COL, 0x0002);
#define SU_7        SetConsoleTextAttribute(COL, 0x0004);
#define SU_8        SetConsoleTextAttribute(COL, 0x0007);
#define SU_9        SetConsoleTextAttribute(COL, 0x000e);
#define SU_10       SetConsoleTextAttribute(COL, 0x0006);

INPUT_RECORD rec;
DWORD dwNOER;

HANDLE COUT = 0;    // �ܼ� ��� ��ġ
HANDLE CIN = 0;        // �ܼ� �Է� ��ġ

int map[31][31];
int mmap[31][31];
int ga, se, gae;

int gascan() {

	int ga;

	printf("�� : ");
	while (1) {
		scanf("%d", &ga);
		if (5 <= ga && ga <= 25)
			break;
		printf("5�̻� 25���Ϸ� �Է����ּ��� : ");
	}

	return ga;
}

int sescan() {

	int se;

	printf("�� : ");
	while (1) {
		scanf("%d", &se);
		if (5 <= se && se <= 25)
			break;
		printf("5�̻� 25���Ϸ� �Է����ּ��� : ");
	}

	return se;
}

int gaescan(int se, int ga) {

	int gae;

	printf("���ڼ� :");
	while (1) {
		scanf("%d", &gae);
		if (ga*se / 20 <= gae && gae <= ga*se / 3)
			break;
		printf("%d�̻� %d���Ϸ� �Է����ּ��� : ", ga*se / 20, ga*se / 3);
	}

	return gae;
}

int loading() {

	system("title ������� ù��° ���Ӹ���� ������Ʈ");
	system("mode con: cols=60 lines=25");

	char n = 0,a=0;
	char query[50] = { 0, };

	BAE printf("����ã�� 4.0\n\n");

	printf("������������������������  ������������������������\n");
	printf("��                    ��  ��                    ��\n");
	printf("��       1.�ʱ�       ��  ��       2.�߱�       ��\n");
	printf("��       8 �� 8       ��  ��      10 �� 10      ��\n");
	printf("��      ����10��      ��  ��      ����40��      ��\n");
	printf("��                    ��  ��                    ��\n");
	printf("������������������������  ������������������������\n");
	printf("\n");
	printf("������������������������  ������������������������\n");
	printf("��                    ��  ��                    ��\n");
	printf("��       3.���       ��  ��      4.�����      ��\n");
	printf("��      30 �� 16      ��  ��      ��������      ��\n");
	printf("��      ����99��      ��  ��                    ��\n");
	printf("��                    ��  ��                    ��\n");
	printf("������������������������  ������������������������\n");
	printf("������ �����\n");

	printf("���Ͻô� ��带 �Է��� �ּ���\n");

	while (1) {
		n=_getch();
		if (n == '1' || n == '2' || n == '3' || n == '4')
			break;
		
	}
	
	
	if (n == '1') { ga = 8; se = 8; gae = 10; system("mode con: cols=26 lines=10"); }
	else if (n == '2') { ga = 16; se = 16; gae = 40; system("mode con: cols=42 lines=18"); }
	else if (n == '3') { ga = 30; se = 16; gae = 99; system("mode con: cols=70 lines=18"); }
	else {
		ga = gascan();
		se = sescan();
		gae = gaescan(se, ga);
		sprintf(query, "mode con: cols=%d lines=%d", ga * 2 + 10, se + 2);
		system(query);
	}

	a = n - '0';

	return a;
}

void mapcreate() {

	int i, j;
	int su = 0, gat = 0, set = 0, gaet = 0;

	for (i = 0; i < se; i++) { //�迭 �ʱ�ȭ
		for (j = 0; j < ga; j++) {
			map[i][j] = 0;
			mmap[i][j] = 0;
		}
	}

	gaet = gae;

	for (i = 0; i < gaet; i++) {  //���ڻ���
		gat = rand() % ga;
		set = rand() % se;
		su = 0;

		if (map[set][gat] == 9) {
			gaet++;
			continue;
		}
		map[set][gat] = 9;
	}

	for (i = 0; i < se; i++) {   //�����ֺ� ���� ���
		for (j = 0; j < ga; j++) {
			if (map[i][j] == 9)
				continue;
			if (map[i + 1][j] == 9 && i != se - 1)
				su++;
			if (map[i - 1][j] == 9 && i != 0)
				su++;
			if (map[i][j + 1] == 9 && j != ga - 1)
				su++;
			if (map[i][j - 1] == 9 && j != 0)
				su++;
			if (map[i + 1][j + 1] == 9 && i != se - 1 && j != ga - 1)
				su++;
			if (map[i + 1][j - 1] == 9 && i != se - 1 && j != 0)
				su++;
			if (map[i - 1][j + 1] == 9 && i != 0 && j != ga - 1)
				su++;
			if (map[i - 1][j - 1] == 9 && i != 0 && j != 0)
				su++;
			map[i][j] = su;
			su = 0;
		}
	}
}

void all_0(int lse, int lga) { //0�� �� ���� ����ϱ����� ����Լ�

	int i, j;
	for (i = -1; i <= 1; i++) {
		for (j = -1; j <= 1; j++) {
			if (!((lse == se -1 && i==1) || (lse == 0 && i==-1) || (lga == ga - 1 && j == 1) || (lga == 0 && j == -1))) { //���� ����
				if (map[lse + i][lga + j] >= 0 && map[lse + i][lga + j] <= 8 && mmap[lse + i][lga + j] == 0) { //���� �����̸� ���
					mmap[lse + i][lga + j] = 1;

					if (map[lse + i][lga + j] == 0)
						all_0(lse + i, lga + j);
				}
			}
		}
	}
	/*
	if (map[lse + 1][lga] >= 0 && map[lse + 1][lga] <= 8 && lse != se - 1 && mmap[lse + 1][lga] == 0) {
		mmap[lse + 1][lga] = 1;

		if (map[lse + 1][lga] == 0)
			all_0(lse + 1, lga);
	}

	if (map[lse - 1][lga] >= 0 && map[lse - 1][lga] <= 8 && lse != 0 && mmap[lse - 1][lga] == 0) {
		mmap[lse - 1][lga] = 1;

		if (map[lse - 1][lga] == 0)
			all_0(lse - 1, lga);
	}

	if (map[lse][lga + 1] >= 0 && map[lse][lga + 1] <= 8 && lga != ga - 1 && mmap[lse][lga + 1] == 0) {
		mmap[lse][lga + 1] = 1;

		if (map[lse][lga + 1] == 0)
			all_0(lse, lga + 1);
	}

	if (map[lse][lga - 1] >= 0 && map[lse][lga - 1] <= 8 && lga != 0 && mmap[lse][lga - 1] == 0) {
		mmap[lse][lga - 1] = 1;

		if (map[lse][lga - 1] == 0)
			all_0(lse, lga - 1);
	}

	if (map[lse + 1][lga + 1] >= 0 && map[lse + 1][lga + 1] <= 8 && mmap[lse + 1][lga + 1] == 0 && lga != ga - 1 && lse != se - 1){
		mmap[lse + 1][lga + 1] = 1;

		if (map[lse + 1][lga + 1] == 0)
			all_0(lse + 1, lga + 1);
	}

	if (map[lse + 1][lga - 1] >= 0 && map[lse + 1][lga - 1] <= 8 && mmap[lse + 1][lga - 1] == 0 && lga != 0 && lse != se - 1) {
		mmap[lse + 1][lga - 1] = 1;

		if (map[lse + 1][lga - 1] == 0)
			all_0(lse + 1, lga - 1);
	}

	if (map[lse - 1][lga + 1] >= 0 && map[lse - 1][lga + 1] <= 8 && mmap[lse - 1][lga + 1] == 0 && lga != ga - 1 && lse != 0) {
		mmap[lse - 1][lga + 1] = 1;

		if (map[lse - 1][lga + 1] == 0)
			all_0(lse -1 , lga + 1);
	}

	if (map[lse - 1][lga - 1] >= 0 && map[lse - 1][lga - 1] <= 8 && mmap[lse - 1][lga - 1] == 0 && lga != 0 && lse != 0) {
		mmap[lse - 1][lga - 1] = 1;

		if (map[lse - 1][lga - 1] == 0)
			all_0(lse - 1, lga - 1);
	}*/
}

void printsu(int i, int j) {  //���� �� ���

	switch (map[i][j]) {
	case 9: SU_9 printf("��"); break;
	case 0: BAISE printf("��"); break;
	case 1: BLUE_1 printf("��"); break;
	case 2: GREEN_2 printf("��"); break;
	case 3: RED_3 printf("��"); break;
	case 4: PURPLE_4 printf("��"); break;
	case 5: SU_5 printf("��"); break;
	case 6: SU_6 printf("��"); break;
	case 7: SU_7 printf("��"); break;
	case 8: SU_8 printf("��"); break;
	}
}

void printsu2(int i, int j) {

	switch (map[i][j]) {
	case 9: SU_9 printf("��"); break;
	case 0: BAE printf("��"); break;
	case 1: BLUE_1 printf("��"); break;
	case 2: GREEN_2 printf("��"); break;
	case 3: RED_3 printf("��"); break;
	case 4: PURPLE_4 printf("��"); break;
	case 5: SU_5 printf("��"); break;
	case 6: SU_6 printf("��"); break;
	case 7: SU_7 printf("��"); break;
	case 8: SU_8 printf("��"); break;
	}


}

void clickto(int i, int j, int mi) { //��� �������� ������ ���� �ΰ����

	int a, b;

	for (a = -1; a <= 1; a++) {
		for (b = -1; b <= 1; b++) {
			if (i + a != se && i + a != 0 && j + b != ga && j + b != 0) {

			}
		}
	}

	if (mmap[i + 1][j] == 2 && i != se - 1)  //��� �� Ȯ��
		mi--;
	if (mmap[i - 1][j] == 2 && i != 0)
		mi--;
	if (mmap[i][j + 1] == 2 && j != ga - 1)
		mi--;
	if (mmap[i][j - 1] == 2 && j != 0)
		mi--;
	if (mmap[i + 1][j + 1] == 2 && i != se - 1 && j != ga - 1)
		mi--;
	if (mmap[i + 1][j - 1] == 2 && i != se - 1 && j != 0)
		mi--;
	if (mmap[i - 1][j + 1] == 2 && i != 0 && j != ga - 1)
		mi--;
	if (mmap[i - 1][j - 1] == 2 && i != 0 && j != 0)
		mi--;

	if (mi != 0)
		return;

	if (mmap[i + 1][j] == 0 && i != se - 1) {//1
		mmap[i + 1][j] = 1;

		if (map[i + 1][j] == 0)
			all_0(i + 1, j);
	}
	if (mmap[i - 1][j] == 0 && i != 0) {//2
		mmap[i - 1][j] = 1;

		if (map[i - 1][j] == 0)
			all_0(i - 1, j);
	}
	if (mmap[i][j + 1] == 0 && j != ga - 1) {//3
		mmap[i][j + 1] = 1;

		if (map[i][j + 1] == 0)
			all_0(i, j + 1);
	}
	if (mmap[i][j - 1] == 0 && j != 0) {//4
		mmap[i][j - 1] = 1;

		if (map[i][j - 1] == 0)
			all_0(i, j - 1);
	}
	if (mmap[i + 1][j + 1] == 0 && i != se - 1 && j != ga - 1) {//5
		mmap[i + 1][j + 1] = 1;

		if (map[i + 1][j + 1] == 0)
			all_0(i + 1, j + 1);
	}
	if (mmap[i + 1][j - 1] == 0 && i != se - 1 && j != 0) {//6
		mmap[i + 1][j - 1] = 1;

		if (map[i + 1][j - 1] == 0)
			all_0(i + 1, j - 1);
	}
	if (mmap[i - 1][j + 1] == 0 && i != 0 && j != ga - 1) {//7
		mmap[i - 1][j + 1] = 1;

		if (map[i - 1][j + 1] == 0)
			all_0(i - 1, j + 1);
	}
	if (mmap[i - 1][j - 1] == 0 && i != 0 && j != 0) {//8
		mmap[i - 1][j - 1] = 1;

		if (map[i - 1][j - 1] == 0)
			all_0(i - 1, j - 1);
	}


}

void clicktoo(int i, int j) {

	if (mmap[i + 1][j] == 0 && i != se - 1 && mmap[i+1][j] == 0)  //��ȯ
		mmap[i + 1][j] = 9;
	if (mmap[i - 1][j] == 0 && i != 0 && mmap[i - 1][j] == 0)
		mmap[i - 1][j] = 9;
	if (mmap[i][j + 1] == 0 && j != ga - 1 && mmap[i][j + 1] == 0)
		mmap[i][j + 1] = 9;
	if (mmap[i][j - 1] == 0 && j != 0 && mmap[i][j - 1] == 0)
		mmap[i][j - 1] = 9;
	if (mmap[i + 1][j + 1] == 0 && i != se - 1 && j != ga - 1 && mmap[i + 1][j + 1] == 0)
		mmap[i + 1][j + 1] = 9;
	if (mmap[i + 1][j - 1] == 0 && i != se - 1 && j != 0 && mmap[i + 1][j - 1] == 0)
		mmap[i + 1][j - 1] = 9;
	if (mmap[i - 1][j + 1] == 0 && i != 0 && j != ga - 1 && mmap[i - 1][j + 1] == 0)
		mmap[i - 1][j + 1] = 9;
	if (mmap[i - 1][j - 1] == 0 && i != 0 && j != 0 && mmap[i - 1][j - 1] == 0)
		mmap[i - 1][j - 1] = 9;

}

void gotoxy(int x, int y) {  //Ŀ���� x, y�� �̵���Ų��

	COORD coord = { 0,0 };
	coord.X = x; coord.Y = y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void cho() {
	int i, j;
	for (i = 0; i < 31; i++) { //�迭 �ʱ�ȭ
		for (j = 0; j < 31; j++) {
			mmap[i][j] = 0;
		}
	}
}

void gameendf(int lse, int lga) {

	int i, j;

	for (i = 0; i < se; i++) {
		for (j = 0; j < ga; j++) {
			if (map[i][j] == 9)
				mmap[i][j] = 1;
		}

	}

	gotoxy(0, 0);
	SU_7 printf("����ã�� 4.0\n");
	for (i = 0; i < se; i++) {   //���
		for (j = 0; j < ga; j++) {
			if (mmap[i][j] == 0) {
				BAISE printf("��");
			}
			else if (mmap[i][j] == 1 && map[i][j] == 9 && i == lse && j == lga) {
				SU_9 printf("��");
			}
			else if (mmap[i][j] == 1 && map[i][j] == 9) {
				SU_10 printf("��");
			}
			else if (mmap[i][j] == 1)
				printsu(i, j);
			else if (mmap[i][j] == 2) {
				BAISE printf("��");
			}
			else if (mmap[i][j] == 4) {
				BAISE printf("��");
			}

		}
		printf("\n");
	}

}

void gameend() {
	int i, j;

	gotoxy(0, 0);
	SU_7 printf("����ã�� 4.0\n");

	for (i = 0; i < se; i++) {
		for (j = 0; j < ga; j++) {
			if (map[i][j] == 9) {
				BAISE printf("��");
			}
			else {
				printsu(i, j);
			}

		}
		printf("\n");
	}


}

void clearcon() {
	COORD Coor = { 0, 0 };
	DWORD dw;
	FillConsoleOutputCharacter(GetStdHandle(STD_OUTPUT_HANDLE), ' ', 80 * 25, Coor, &dw); // �ܼ�â ȭ���� �����.

}

void timeclu() {
	struct tm *t;
	time_t timer; // �ð�����

	timer = time(NULL);    // ���� �ð��� �� ������ ���
	t = localtime(&timer); // �� ������ �ð��� �и��Ͽ� ����ü�� �ֱ�

	printf("%2d/%2d\n", t->tm_mon + 1, t->tm_mday);
}

void bgmsound() {
	sndPlaySoundA("bgm.wav", SND_ASYNC | SND_LOOP);
}

void data(int mode, int second) {

	struct tm *t;
	time_t timer; // �ð�����

	timer = time(NULL);    // ���� �ð��� �� ������ ���
	t = localtime(&timer); // �� ������ �ð��� �и��Ͽ� ����ü�� �ֱ�


	char dataarr[9][30] = { 0, };
	int dataint[9] = { 0, };
	int datawal[9] = { 0, };
	int dataill[9] = { 0, };
	int i, j, tmp = 0, loca = 100;

	FILE *fp = fopen("data.txt", "r+");

	for (i = 0; i < 9; i++) {
		fscanf(fp, "%s", dataarr[i]);

		for (j = 0; dataarr[i][j] != '/'; j++) {
			tmp *= 10;
			tmp += dataarr[i][j] - '0';
		}
		dataint[i] = tmp;
		tmp = 0;

		for (j = j + 1; dataarr[i][j] != '/'; j++) {
			tmp *= 10;
			tmp += dataarr[i][j] - '0';
		}
		datawal[i] = tmp;
		tmp = 0;

		for (j = j + 1; dataarr[i][j] != 0; j++) {
			tmp *= 10;
			tmp += dataarr[i][j] - '0';
		}
		dataill[i] = tmp;
		tmp = 0;

	}


	if (mode == 1) {

		if (dataint[0] >= second) {
			dataint[2] = dataint[1];
			datawal[2] = datawal[1];
			dataill[2] = dataill[1];
			dataint[1] = dataint[0];
			datawal[1] = datawal[0];
			dataill[1] = dataill[0];
			dataint[0] = second;
			loca = 0;
			datawal[0] = t->tm_mon + 1;
			dataill[0] = t->tm_mday;
		}
		else if (dataint[1] >= second) {
			dataint[2] = dataint[1];
			datawal[2] = datawal[1];
			dataill[2] = dataill[1];
			dataint[1] = second;
			loca = 1;
			datawal[1] = t->tm_mon + 1;
			dataill[1]= t->tm_mday;
		}
		else if (dataint[2] >= second) {
			dataint[2] = second;

			loca = 2;
			datawal[2] = t->tm_mon + 1;
			dataill[2] = t->tm_mday;
		}

	}
	else if (mode == 2) {

		if (dataint[3] >= second) {
			dataint[5] = dataint[4];
			datawal[5] = datawal[4];
			dataill[5] = dataill[4];
			dataint[4] = dataint[3];
			datawal[4] = datawal[3];
			dataill[4] = dataill[3];
			dataint[3] = second;
			loca = 3;
			datawal[3] = t->tm_mon + 1;
			dataill[3] = t->tm_mday;
		}
		else if (dataint[1] >= second) {
			dataint[5] = dataint[4];
			datawal[5] = datawal[4];
			dataill[5] = dataill[4];
			dataint[4] = second;
			loca = 4;
			datawal[4] = t->tm_mon + 1;
			dataill[4] = t->tm_mday;
		}
		else if (dataint[5] >= second) {
			dataint[5] = second;
			loca = 5;
			datawal[5] = t->tm_mon + 1;
			dataill[5] = t->tm_mday;
		}

	}
	else if (mode == 3) {

		if (dataint[6] >= second) {
			dataint[8] = dataint[7];
			datawal[8] = datawal[7];
			dataill[8] = dataill[7];
			dataint[7] = dataint[6];
			datawal[7] = datawal[6];
			dataill[7] = dataill[6];
			dataint[6] = second;
			loca = 6;
			datawal[6] = t->tm_mon + 1;
			dataill[6] = t->tm_mday;
		}
		else if (dataint[7] >= second) {
			dataint[8] = dataint[7];
			datawal[8] = datawal[7];
			dataill[8] = dataill[7];
			dataint[7] = second;
			loca = 7;
			datawal[7] = t->tm_mon + 1;
			dataill[7] = t->tm_mday;
		}
		else if (dataint[8] >= second) {
			dataint[8] = second;
			loca = 8;
			datawal[8] = t->tm_mon + 1;
			dataill[8] = t->tm_mday;
		}

	}

	SU_8
	if (mode == 1) {
		gotoxy(ga * 2, 4);
		if (loca == 0) {
			PURPLE_4 printf("%4ds%2d/%2d", dataint[0], datawal[0], dataill[0]);
			SU_8
		}else if(9999!=dataint[0]) printf("%4ds%2d/%2d", dataint[0], datawal[0], dataill[0]);
		gotoxy(ga * 2, 5);
		if (loca == 1) {
			PURPLE_4 printf("%4ds%2d/%2d", dataint[1], datawal[1], dataill[1]);
			SU_8
		} else if (9999 != dataint[1]) printf("%4ds%2d/%2d", dataint[1], datawal[1], dataill[1]);
		gotoxy(ga * 2, 6);
		if (loca == 2) {
			PURPLE_4 printf("%4ds%2d/%2d", dataint[2], datawal[2], dataill[2]);
			SU_8
		}else if (9999 != dataint[2]) printf("%4ds%2d/%2d", dataint[2], datawal[2], dataill[2]);
	}
	else if (mode == 2){
		gotoxy(ga * 2, 4);
		if (loca == 3) {
			PURPLE_4 printf("%4ds%2d/%2d", dataint[3], datawal[3], dataill[3]);
			SU_8
		}else if (9999 != dataint[3]) printf("%4ds%2d/%2d", dataint[3], datawal[3], dataill[3]);
		gotoxy(ga * 2, 5);
		if (loca == 4) {
			PURPLE_4 printf("%4ds%2d/%2d", dataint[4], datawal[4], dataill[4]);
			SU_8
		}else if (9999 != dataint[4]) printf("%4ds%2d/%2d", dataint[4], datawal[4], dataill[4]);
		gotoxy(ga * 2, 6);
		if (loca == 5) {
			PURPLE_4 printf("%4ds%2d/%2d", dataint[5], datawal[5], dataill[5]);
			SU_8
		}else if (9999 != dataint[5]) printf("%4ds%2d/%2d", dataint[5], datawal[5], dataill[5]);
	}
	else if (mode == 3) {
		gotoxy(ga * 2, 4);
		if (loca == 6) {
			PURPLE_4 printf("%4ds%2d/%2d", dataint[6], datawal[6], dataill[6]);
			SU_8
		}else if (9999 != dataint[6]) printf("%4ds%2d/%2d", dataint[6], datawal[6], dataill[6]);
		gotoxy(ga * 2, 5);
		if (loca == 7) {
			PURPLE_4 printf("%4ds%2d/%2d", dataint[7], datawal[7], dataill[7]);
			SU_8
		}else if (9999 != dataint[7]) printf("%4ds%2d/%2d", dataint[7], datawal[7], dataill[7]);
		gotoxy(ga * 2, 6);
		if (loca == 8) {
			PURPLE_4 printf("%4ds%2d/%2d", dataint[8], datawal[8], dataill[8]);
			SU_8
		}else if (9999 != dataint[8]) printf("%4ds%2d/%2d", dataint[8], datawal[8], dataill[8]);

	}

	fclose(fp);
	FILE *fpc = fopen("data.txt", "w+");

	for (i = 0; i < 9; i++) {
		fprintf(fpc, "%d/%d/%d\n", dataint[i], datawal[i], dataill[i]);

	}


	fclose(fpc);
}

void CheckMouse(int *yy, int *xx, int *lr, int *mse, int *mga) {
	ReadConsoleInput(GetStdHandle(STD_INPUT_HANDLE), &rec, 1, &dwNOER); // �ܼ�â �Է��� �޾Ƶ���.

	if (rec.EventType == MOUSE_EVENT) {// ���콺 �̺�Ʈ�� ���

		

		if (rec.Event.MouseEvent.dwButtonState & FROM_LEFT_1ST_BUTTON_PRESSED) { // ���� ��ư�� Ŭ���Ǿ��� ���

			int mouse_x = rec.Event.MouseEvent.dwMousePosition.X; // X�� �޾ƿ�
			int mouse_y = rec.Event.MouseEvent.dwMousePosition.Y; // Y�� �޾ƿ�


			if (mouse_x < ga * 2 && mouse_y < se + 1 && 0<mouse_y) {
				*xx = mouse_x;
				*yy = mouse_y;
				*lr = 1;
				*mse = mouse_y;
				*mga = mouse_x;
				//printf("%d, %d\n", mouse_x, mouse_y); // ��ǥ�� ����Ѵ�.
			}
			else if (mouse_x >= ga * 2 && (ga * 2) + 10 >= mouse_x && mouse_y <= se && se - 2 <= mouse_y) {
				*mga = mouse_x;
				*mse = mouse_y;

				if (mouse_y == se - 2)
					*lr = 7;
				else  if (mouse_y == se - 1)
					*lr = 8;
				else if (mouse_y == se)
					*lr = 9;
				else
					*lr = 100;
			}
			else {
				*xx = 62;
				*yy = 32;
				*lr = 10;
				*mse = 200;
				*mga = 100;
			}
		}
		else if (rec.Event.MouseEvent.dwButtonState & RIGHTMOST_BUTTON_PRESSED) { // ���� ��ư�� Ŭ���Ǿ��� ���
			int mouse_x = rec.Event.MouseEvent.dwMousePosition.X; // X�� �޾ƿ�
			int mouse_y = rec.Event.MouseEvent.dwMousePosition.Y; // Y�� �޾ƿ�

			if (mouse_x < ga * 2 && mouse_y < se + 1 && 0<mouse_y) {
				*xx = mouse_x;
				*yy = mouse_y;
				*lr = 2;
				*mse = 200;
				*mga = 100;
				//printf("%d, %d\n", mouse_x, mouse_y); // ��ǥ�� ����Ѵ�.
			}
			else {
				*xx = 62;
				*yy = 32;
				*lr = 100;
				*mse = 200;
				*mga = 100;
			}
		}
		else {
			int mouse_x = rec.Event.MouseEvent.dwMousePosition.X; // X�� �޾ƿ�
			int mouse_y = rec.Event.MouseEvent.dwMousePosition.Y; // Y�� �޾ƿ�

			if (mouse_x < ga * 2 && 0 < mouse_y && mouse_y < se + 1) {
				*mga = mouse_x;
				*mse = mouse_y;
				*lr = 3;
			}else if(mouse_x >= ga * 2 && (ga * 2)+10 >= mouse_x && mouse_y <= se && se-2 <= mouse_y ){
				*mga = mouse_x;
				*mse = mouse_y;

				if (mouse_y == se - 2)
					*lr = 4;
				else  if (mouse_y == se - 1)
					*lr = 5;
				else if (mouse_y == se)
					*lr = 6;
				else
					*lr = 100;
			} 
			else {
				*mga = 200;
				*mse = 100;
				*lr = 10;
			}
		}
	}

}

int main() {
	int mode=0;
	bgmsound();


	srand((unsigned int)time(NULL));
	
	CONSOLE_CURSOR_INFO cursorInfo = { 0, }; // Ŀ�� �����
	cursorInfo.dwSize = 1;
	cursorInfo.bVisible = FALSE;
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cursorInfo);

	int lga = 0, lse = 0, lr = 0;
	int i, j;
	int end = 0, endf = 0, mi = 0, s = 0, gaec = 0, a = 0,keygam=0;
	int bang = 0;
	int mse = 31, mga = 31;
	int lrlrlr = 0,mm=0;
	long timem = 0, times = 0;
	
	
	mm = loading();
	
	CIN = GetStdHandle(STD_INPUT_HANDLE);
	// ���콺 Ȱ��ȭ
	GetConsoleMode(CIN, &mode);
	SetConsoleMode(CIN, mode | ENABLE_MOUSE_INPUT);

	//mmap �� 0=���� 1=���� 2=��� 4=����ǥ map�� 9=����

	clearcon();
	mapcreate();

	clock_t firstclock, lastclock;

	firstclock = clock();
	
	while (1) {
		
		if (a != 0) { //goto���� ���� �ʱ����� ���

			CheckMouse(&lse, &lga, &lr, &mse, &mga);
			lse = lse - 1;
			lga = lga / 2;
			mse = mse - 1;
			mga = mga / 2;

			if (lr == 7) {
				cho();
				lse = lga = 31;
				mse = mga = 100;
				lr = 100;
				firstclock = clock();
				a = 0;
				continue;
			}
			else if (lr == 8) {
				mapcreate();
				cho();
				lse = lga = 31;
				mse = mga = 100;
				lr = 100;
				firstclock = clock();
				a = 0;
				continue;
			}
			else if (lr == 9) {
				system("mode con: cols=60 lines=18");
				mm=loading();
				clearcon();
				mapcreate();
				cho();
				lse = lga = 31;
				mse = mga = 100;
				lr = 100;
				firstclock = clock();
				a = 0;
				lrlrlr = -1;
				CIN = GetStdHandle(STD_INPUT_HANDLE);

				GetConsoleMode(CIN, &mode);
				SetConsoleMode(CIN, mode | ENABLE_MOUSE_INPUT);
				continue;
			}
			else if (lr == 1 && mmap[lse][lga] == 1 && map[lse][lga] >= 0 && map[lse][lga] <= 8) {
				mi = map[lse][lga];
				clickto(lse, lga, mi);
				clicktoo(lse, lga);
			}
			else if (lr == 1 && mmap[lse][lga] == 0) {
				mmap[lse][lga] = 1;
				keygam++;

				if (map[lse][lga] == 0)
					all_0(lse, lga);
			}
			else if (lr == 2 && (mmap[lse][lga] == 0 || mmap[lse][lga] == 2 || mmap[lse][lga] == 4)) {

				if (gae == gaec && mmap[lse][lga] == 0)
					mmap[lse][lga] += 4;
				else
					mmap[lse][lga] += 2;

				if (mmap[lse][lga] == 6 || mmap[lse][lga] == 8)
					mmap[lse][lga] = 0;
			}



		}
		if (a == 0) a++;

		gaec = 0;
		gotoxy(0, 0);
		SU_7 printf("����ã�� 4.0\n");

		for (i = 0; i < se; i++) { //���
			for (j = 0; j < ga; j++) {

				if (mmap[i][j] == 0 && i == mse && j == mga && lr == 3) {
					BAE printf("��");
				}
				else if (mmap[i][j] == 1 && map[i][j] == 0 && i == mse && j == mga && lr == 3) {
					BAE printf("��");
				}
				else if (mmap[i][j] == 1 && i == mse && j == mga && lr == 1 && keygam == 0) {
					printsu2(i, j);
				}
				else if (mmap[i][j] == 9) {
					SU_5 printf("��");
					mmap[i][j] = 0;
				}
				else if (mmap[i][j] == 0) {
					BAISE printf("��");
				}
				else if (mmap[i][j] == 1)
					printsu(i, j);
				else if (mmap[i][j] == 2) {
					BAISE printf("��");
					gaec++;
				}
				else if (mmap[i][j] == 4) {
					BAISE printf("��");
				}

			}
			printf("\n");
		}
		gotoxy(ga * 2 + 4, 1); //��߼� ���
		BAISE printf("��");
		gotoxy(ga * 2 + 2, 2);
		if (gae <= gaec) {
			SU_9 printf("%2d��%d", gaec, gae);
		}
		else {
			SU_6 printf("%2d��%d", gaec, gae);
		}

		gotoxy(ga * 2 + 3, 4); //�ð����
		SU_10 printf("time");

		gotoxy(ga * 2 + 2, 5);

		times = (clock() - firstclock) / 1000;

		for (; times >= 60; times -= 60) {
			timem++;
		}

		SU_10
			timem < 10 ? printf("0%d", timem) : printf("%d", timem);
		printf("��");
		times < 10 ? printf("0%d", times) : printf("%d", times);

		gotoxy(ga * 2, se - 2); //��� �߰�
		if (lr == 4) {
			BLUE_1 printf("������ۡ�");
		}
		else{
			BAE printf("������ۡ�");
		}	
		gotoxy(ga * 2, se - 1);
		if (lr == 5) {
			BLUE_1 printf("������ӡ�");
		}
		else {
			BAE printf("������ӡ�");
		}
		gotoxy(ga * 2, se);
		if (lr == 6) {
			BLUE_1 printf("��޴��Ρ�");
		}
		else {
			BAE printf("��޴��Ρ�");
		}

		for (i = 0; i < se; i++) { //����Ŭ����,���� Ȯ��
			for (j = 0; j < ga; j++) {
				if (map[i][j] == 9 && mmap[i][j] == 1)
					endf++;
				if (map[i][j] != 9 && mmap[i][j] == 1)
					end++;

			}
		}
		if((ga*se - gae) == end) {  //�������� Ȯ��
			clearcon();
			gameend();
			lastclock = clock();
			gotoxy(ga * 2, 1);
			SU_9 printf(" clear!");
			gotoxy(ga * 2, 2);
			SU_10 printf("�� :s��/��");
			gotoxy(ga * 2, 3);
			PURPLE_4 printf("%4ds", (lastclock - firstclock) / 1000);
			timeclu();
			sndPlaySoundA("clear.wav", SND_ASYNC);
			data(mm, (lastclock - firstclock) / 1000);

			while (1) {
				CheckMouse(&lse, &lga, &lr, &mse, &mga);
				gotoxy(ga * 2, se - 1);
				if (lr == 5) {
					BLUE_1 printf("������ӡ�");
				}
				else {
					BAE printf("������ӡ�");
				}
				gotoxy(ga * 2, se);
				if (lr == 6) {
					BLUE_1 printf("��޴��Ρ�");
				}
				else {
					BAE printf("��޴��Ρ�");
				}
				
				if (lr == 8 || lr == 9) {
					bgmsound();
					break;
				}

			}
			clearcon();

			

			if (lr == 8) {
				mapcreate();
				cho();
				lse = lga = 31;
				mse = mga = 100;
				lr = 100;
				firstclock = clock();
				a = 0;		
			}
			else if (lr == 9){
				system("mode con: cols=60 lines=18");
				mm = loading();
				clearcon();
				mapcreate();
				cho();
				lse = lga = 31;
				mse = mga = 100;
				lr = 100;
				firstclock = clock();
				a = 0;
				lrlrlr = -1;

				CIN = GetStdHandle(STD_INPUT_HANDLE);

				GetConsoleMode(CIN, &mode);
				SetConsoleMode(CIN, mode | ENABLE_MOUSE_INPUT);
			}

		}
		else if (0 != endf) {
			clearcon();
			gameendf(lse, lga);
			lastclock = clock();
			gotoxy(ga * 2, 1);
			SU_9 printf(" gameover!");

			gotoxy(ga * 2 + 3, 4); //�ð����
			SU_10 printf("time");

			gotoxy(ga * 2 + 2, 5);

			SU_10
				timem < 10 ? printf("0%d", timem) : printf("%d", timem);
			printf("��");
			times < 10 ? printf("0%d", times) : printf("%d", times);

			sndPlaySoundA("ppp.wav", SND_ASYNC);

			while (1) {
				CheckMouse(&lse, &lga, &lr, &mse, &mga);

				gotoxy(ga * 2, se - 2); //��� �߰�
				if (lr == 4) {
					BLUE_1 printf("������ۡ�");
				}
				else {
					BAE printf("������ۡ�");
				}
				gotoxy(ga * 2, se - 1);
				if (lr == 5) {
					BLUE_1 printf("������ӡ�");
				}
				else {
					BAE printf("������ӡ�");
				}
				gotoxy(ga * 2, se);
				if (lr == 6) {
					BLUE_1 printf("��޴��Ρ�");
				}
				else {
					BAE printf("��޴��Ρ�");
				}

				if (lr == 7 || lr == 8 || lr == 9) {
					bgmsound();
					break;
				}
					

			}
			clearcon();

			if (lr == 7) {
				cho();
				lse = lga = 31;
				mse = mga = 100;
				lr = 100;
				firstclock = clock();
				a = 0;
			}
			else if (lr == 8) {
				mapcreate();
				cho();
				lse = lga = 31;
				mse = mga = 100;
				lr = 100;
				firstclock = clock();
				a = 0;
			}
			else if (lr == 9) {
				system("mode con: cols=60 lines=18");
				mm = loading();
				clearcon();
				mapcreate();
				cho();
				lse = lga = 31;
				mse = mga = 100;
				lr = 100;
				firstclock = clock();
				a = 0;
				lrlrlr = -1;

				CIN = GetStdHandle(STD_INPUT_HANDLE);

				GetConsoleMode(CIN, &mode);
				SetConsoleMode(CIN, mode | ENABLE_MOUSE_INPUT);
			}
		}
		end = 0;
		endf = 0;
		timem = 0;
		keygam = 0;

	}
	printf("3���� ����");
	Sleep(3000);
	return 0;

}
