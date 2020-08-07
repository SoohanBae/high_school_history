/*-------------------------------------
c언어로 지뢰찾기 프로그램 만들기 프로젝트
제작자 : 배수한   소속 : 대구소프트웨어고등학교
프로젝트 시작일자 : 5월 17일   짬나는 시간마다 틈틈히 코딩

본 코드에 대한 저작권은 배수한 에게 있으며 사용시 허락을 구해야 합니다.

현재버전 4.0

--패치노트--
5월 17일 1.0 -- 기본적인 틀 완성
5월 18일 1.1 -- 0클릭식 주변에 있는 0까지 같이 출력하게 설정(재귀함수 사용)
5월 18일 1.2 -- 부가기능 추가및 출력창 개선
5월 19일 1.3 -- 색 추가및 콘솔창 추가및 프로그램 최적화
5월 21일 1.4 -- 게임이 끝날시 나오는 화면 개선
5월 22일 1.5 -- 지뢰수 체크기능 추가 버그수정
5월 27일 2.0 -- 마우스 클릭 추가
5월 31일 3.0 -- 마우스 기능 추가 및 디자인 개선, 시간기능 추가
6월 7일  3.1 -- 마우스 기능 추가 및 로딩화면 디자인 개선
6월 9일  4.0 -- 게임 데이터 기능 추가 및 사운드 추가 (최종본)
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

HANDLE COUT = 0;    // 콘솔 출력 장치
HANDLE CIN = 0;        // 콘솔 입력 장치

int map[31][31];
int mmap[31][31];
int ga, se, gae;

int gascan() {

	int ga;

	printf("행 : ");
	while (1) {
		scanf("%d", &ga);
		if (5 <= ga && ga <= 25)
			break;
		printf("5이상 25이하로 입력해주세요 : ");
	}

	return ga;
}

int sescan() {

	int se;

	printf("열 : ");
	while (1) {
		scanf("%d", &se);
		if (5 <= se && se <= 25)
			break;
		printf("5이상 25이하로 입력해주세요 : ");
	}

	return se;
}

int gaescan(int se, int ga) {

	int gae;

	printf("지뢰수 :");
	while (1) {
		scanf("%d", &gae);
		if (ga*se / 20 <= gae && gae <= ga*se / 3)
			break;
		printf("%d이상 %d이하로 입력해주세요 : ", ga*se / 20, ga*se / 3);
	}

	return gae;
}

int loading() {

	system("title 배수한의 첫번째 게임만들기 프로젝트");
	system("mode con: cols=60 lines=25");

	char n = 0,a=0;
	char query[50] = { 0, };

	BAE printf("지뢰찾기 4.0\n\n");

	printf("┌──────────┐  ┌──────────┐\n");
	printf("│                    │  │                    │\n");
	printf("│       1.초급       │  │       2.중급       │\n");
	printf("│       8 × 8       │  │      10 × 10      │\n");
	printf("│      지뢰10개      │  │      지뢰40개      │\n");
	printf("│                    │  │                    │\n");
	printf("└──────────┘  └──────────┘\n");
	printf("\n");
	printf("┌──────────┐  ┌──────────┐\n");
	printf("│                    │  │                    │\n");
	printf("│       3.고급       │  │      4.사용자      │\n");
	printf("│      30 × 16      │  │      지정게임      │\n");
	printf("│      지뢰99개      │  │                    │\n");
	printf("│                    │  │                    │\n");
	printf("└──────────┘  └──────────┘\n");
	printf("만든이 배수한\n");

	printf("원하시는 모드를 입력해 주세요\n");

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

	for (i = 0; i < se; i++) { //배열 초기화
		for (j = 0; j < ga; j++) {
			map[i][j] = 0;
			mmap[i][j] = 0;
		}
	}

	gaet = gae;

	for (i = 0; i < gaet; i++) {  //지뢰생성
		gat = rand() % ga;
		set = rand() % se;
		su = 0;

		if (map[set][gat] == 9) {
			gaet++;
			continue;
		}
		map[set][gat] = 9;
	}

	for (i = 0; i < se; i++) {   //지뢰주변 숫자 출력
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

void all_0(int lse, int lga) { //0을 다 같이 출력하기위한 재귀함수

	int i, j;
	for (i = -1; i <= 1; i++) {
		for (j = -1; j <= 1; j++) {
			if (!((lse == se -1 && i==1) || (lse == 0 && i==-1) || (lga == ga - 1 && j == 1) || (lga == 0 && j == -1))) { //오류 방지
				if (map[lse + i][lga + j] >= 0 && map[lse + i][lga + j] <= 8 && mmap[lse + i][lga + j] == 0) { //맵이 숫자이면 출력
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

void printsu(int i, int j) {  //지뢰 수 출력

	switch (map[i][j]) {
	case 9: SU_9 printf("★"); break;
	case 0: BAISE printf("□"); break;
	case 1: BLUE_1 printf("①"); break;
	case 2: GREEN_2 printf("②"); break;
	case 3: RED_3 printf("③"); break;
	case 4: PURPLE_4 printf("④"); break;
	case 5: SU_5 printf("⑤"); break;
	case 6: SU_6 printf("⑥"); break;
	case 7: SU_7 printf("⑦"); break;
	case 8: SU_8 printf("⑧"); break;
	}
}

void printsu2(int i, int j) {

	switch (map[i][j]) {
	case 9: SU_9 printf("★"); break;
	case 0: BAE printf("□"); break;
	case 1: BLUE_1 printf("⑴"); break;
	case 2: GREEN_2 printf("⑵"); break;
	case 3: RED_3 printf("⑶"); break;
	case 4: PURPLE_4 printf("⑷"); break;
	case 5: SU_5 printf("⑸"); break;
	case 6: SU_6 printf("⑹"); break;
	case 7: SU_7 printf("⑺"); break;
	case 8: SU_8 printf("⑻"); break;
	}


}

void clickto(int i, int j, int mi) { //깃발 세워놓고 누를때 쓰는 부가기능

	int a, b;

	for (a = -1; a <= 1; a++) {
		for (b = -1; b <= 1; b++) {
			if (i + a != se && i + a != 0 && j + b != ga && j + b != 0) {

			}
		}
	}

	if (mmap[i + 1][j] == 2 && i != se - 1)  //깃발 수 확인
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

	if (mmap[i + 1][j] == 0 && i != se - 1 && mmap[i+1][j] == 0)  //변환
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

void gotoxy(int x, int y) {  //커서를 x, y로 이동시킨다

	COORD coord = { 0,0 };
	coord.X = x; coord.Y = y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void cho() {
	int i, j;
	for (i = 0; i < 31; i++) { //배열 초기화
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
	SU_7 printf("지뢰찾기 4.0\n");
	for (i = 0; i < se; i++) {   //출력
		for (j = 0; j < ga; j++) {
			if (mmap[i][j] == 0) {
				BAISE printf("□");
			}
			else if (mmap[i][j] == 1 && map[i][j] == 9 && i == lse && j == lga) {
				SU_9 printf("★");
			}
			else if (mmap[i][j] == 1 && map[i][j] == 9) {
				SU_10 printf("★");
			}
			else if (mmap[i][j] == 1)
				printsu(i, j);
			else if (mmap[i][j] == 2) {
				BAISE printf("¶");
			}
			else if (mmap[i][j] == 4) {
				BAISE printf("？");
			}

		}
		printf("\n");
	}

}

void gameend() {
	int i, j;

	gotoxy(0, 0);
	SU_7 printf("지뢰찾기 4.0\n");

	for (i = 0; i < se; i++) {
		for (j = 0; j < ga; j++) {
			if (map[i][j] == 9) {
				BAISE printf("¶");
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
	FillConsoleOutputCharacter(GetStdHandle(STD_OUTPUT_HANDLE), ' ', 80 * 25, Coor, &dw); // 콘솔창 화면을 지운다.

}

void timeclu() {
	struct tm *t;
	time_t timer; // 시간측정

	timer = time(NULL);    // 현재 시각을 초 단위로 얻기
	t = localtime(&timer); // 초 단위의 시간을 분리하여 구조체에 넣기

	printf("%2d/%2d\n", t->tm_mon + 1, t->tm_mday);
}

void bgmsound() {
	sndPlaySoundA("bgm.wav", SND_ASYNC | SND_LOOP);
}

void data(int mode, int second) {

	struct tm *t;
	time_t timer; // 시간측정

	timer = time(NULL);    // 현재 시각을 초 단위로 얻기
	t = localtime(&timer); // 초 단위의 시간을 분리하여 구조체에 넣기


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
	ReadConsoleInput(GetStdHandle(STD_INPUT_HANDLE), &rec, 1, &dwNOER); // 콘솔창 입력을 받아들임.

	if (rec.EventType == MOUSE_EVENT) {// 마우스 이벤트일 경우

		

		if (rec.Event.MouseEvent.dwButtonState & FROM_LEFT_1ST_BUTTON_PRESSED) { // 좌측 버튼이 클릭되었을 경우

			int mouse_x = rec.Event.MouseEvent.dwMousePosition.X; // X값 받아옴
			int mouse_y = rec.Event.MouseEvent.dwMousePosition.Y; // Y값 받아옴


			if (mouse_x < ga * 2 && mouse_y < se + 1 && 0<mouse_y) {
				*xx = mouse_x;
				*yy = mouse_y;
				*lr = 1;
				*mse = mouse_y;
				*mga = mouse_x;
				//printf("%d, %d\n", mouse_x, mouse_y); // 좌표를 출력한다.
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
		else if (rec.Event.MouseEvent.dwButtonState & RIGHTMOST_BUTTON_PRESSED) { // 우측 버튼이 클릭되었을 경우
			int mouse_x = rec.Event.MouseEvent.dwMousePosition.X; // X값 받아옴
			int mouse_y = rec.Event.MouseEvent.dwMousePosition.Y; // Y값 받아옴

			if (mouse_x < ga * 2 && mouse_y < se + 1 && 0<mouse_y) {
				*xx = mouse_x;
				*yy = mouse_y;
				*lr = 2;
				*mse = 200;
				*mga = 100;
				//printf("%d, %d\n", mouse_x, mouse_y); // 좌표를 출력한다.
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
			int mouse_x = rec.Event.MouseEvent.dwMousePosition.X; // X값 받아옴
			int mouse_y = rec.Event.MouseEvent.dwMousePosition.Y; // Y값 받아옴

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
	
	CONSOLE_CURSOR_INFO cursorInfo = { 0, }; // 커서 지우기
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
	// 마우스 활성화
	GetConsoleMode(CIN, &mode);
	SetConsoleMode(CIN, mode | ENABLE_MOUSE_INPUT);

	//mmap 의 0=가림 1=공개 2=깃발 4=물음표 map의 9=지뢰

	clearcon();
	mapcreate();

	clock_t firstclock, lastclock;

	firstclock = clock();
	
	while (1) {
		
		if (a != 0) { //goto문을 쓰지 않기위한 노력

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
		SU_7 printf("지뢰찾기 4.0\n");

		for (i = 0; i < se; i++) { //출력
			for (j = 0; j < ga; j++) {

				if (mmap[i][j] == 0 && i == mse && j == mga && lr == 3) {
					BAE printf("■");
				}
				else if (mmap[i][j] == 1 && map[i][j] == 0 && i == mse && j == mga && lr == 3) {
					BAE printf("□");
				}
				else if (mmap[i][j] == 1 && i == mse && j == mga && lr == 1 && keygam == 0) {
					printsu2(i, j);
				}
				else if (mmap[i][j] == 9) {
					SU_5 printf("□");
					mmap[i][j] = 0;
				}
				else if (mmap[i][j] == 0) {
					BAISE printf("■");
				}
				else if (mmap[i][j] == 1)
					printsu(i, j);
				else if (mmap[i][j] == 2) {
					BAISE printf("¶");
					gaec++;
				}
				else if (mmap[i][j] == 4) {
					BAISE printf("？");
				}

			}
			printf("\n");
		}
		gotoxy(ga * 2 + 4, 1); //깃발수 출력
		BAISE printf("¶");
		gotoxy(ga * 2 + 2, 2);
		if (gae <= gaec) {
			SU_9 printf("%2d／%d", gaec, gae);
		}
		else {
			SU_6 printf("%2d／%d", gaec, gae);
		}

		gotoxy(ga * 2 + 3, 4); //시간출력
		SU_10 printf("time");

		gotoxy(ga * 2 + 2, 5);

		times = (clock() - firstclock) / 1000;

		for (; times >= 60; times -= 60) {
			timem++;
		}

		SU_10
			timem < 10 ? printf("0%d", timem) : printf("%d", timem);
		printf("：");
		times < 10 ? printf("0%d", times) : printf("%d", times);

		gotoxy(ga * 2, se - 2); //기능 추가
		if (lr == 4) {
			BLUE_1 printf("→재시작←");
		}
		else{
			BAE printf("→재시작←");
		}	
		gotoxy(ga * 2, se - 1);
		if (lr == 5) {
			BLUE_1 printf("→새게임←");
		}
		else {
			BAE printf("→새게임←");
		}
		gotoxy(ga * 2, se);
		if (lr == 6) {
			BLUE_1 printf("→메뉴로←");
		}
		else {
			BAE printf("→메뉴로←");
		}

		for (i = 0; i < se; i++) { //게임클리어,오버 확인
			for (j = 0; j < ga; j++) {
				if (map[i][j] == 9 && mmap[i][j] == 1)
					endf++;
				if (map[i][j] != 9 && mmap[i][j] == 1)
					end++;

			}
		}
		if((ga*se - gae) == end) {  //게임종료 확인
			clearcon();
			gameend();
			lastclock = clock();
			gotoxy(ga * 2, 1);
			SU_9 printf(" clear!");
			gotoxy(ga * 2, 2);
			SU_10 printf("초 :s월/일");
			gotoxy(ga * 2, 3);
			PURPLE_4 printf("%4ds", (lastclock - firstclock) / 1000);
			timeclu();
			sndPlaySoundA("clear.wav", SND_ASYNC);
			data(mm, (lastclock - firstclock) / 1000);

			while (1) {
				CheckMouse(&lse, &lga, &lr, &mse, &mga);
				gotoxy(ga * 2, se - 1);
				if (lr == 5) {
					BLUE_1 printf("→새게임←");
				}
				else {
					BAE printf("→새게임←");
				}
				gotoxy(ga * 2, se);
				if (lr == 6) {
					BLUE_1 printf("→메뉴로←");
				}
				else {
					BAE printf("→메뉴로←");
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

			gotoxy(ga * 2 + 3, 4); //시간출력
			SU_10 printf("time");

			gotoxy(ga * 2 + 2, 5);

			SU_10
				timem < 10 ? printf("0%d", timem) : printf("%d", timem);
			printf("：");
			times < 10 ? printf("0%d", times) : printf("%d", times);

			sndPlaySoundA("ppp.wav", SND_ASYNC);

			while (1) {
				CheckMouse(&lse, &lga, &lr, &mse, &mga);

				gotoxy(ga * 2, se - 2); //기능 추가
				if (lr == 4) {
					BLUE_1 printf("→재시작←");
				}
				else {
					BAE printf("→재시작←");
				}
				gotoxy(ga * 2, se - 1);
				if (lr == 5) {
					BLUE_1 printf("→새게임←");
				}
				else {
					BAE printf("→새게임←");
				}
				gotoxy(ga * 2, se);
				if (lr == 6) {
					BLUE_1 printf("→메뉴로←");
				}
				else {
					BAE printf("→메뉴로←");
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
	printf("3초후 종료");
	Sleep(3000);
	return 0;

}
