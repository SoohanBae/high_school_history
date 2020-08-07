#define _CRT_SECURE_NO_WARNINGS

#include <opencv2/opencv.hpp>
#include <iostream>
#include <string>
#include <vector>
#include <time.h>
#include <stdio.h> 
#include <sqlite3.h>
#include <deque>
#include <math.h>
using namespace cv;
using namespace std; 

vector<Scalar> Colors;
Mat draw;
Mat HSV;
Mat frame;
Mat Bindraw;
Mat UI;
Mat TempBin = Mat(480, 640, CV_8UC1, Scalar(0));
int prex, prey;
int Tswitch = 0;
bool F_flag = false;
int fx, fy;
bool S_flag = false;
Rect folow;

bool button = true;
bool palette_sw = false;
bool eraser_sw = false;

string filename;

int Linethickness = 2;
int ColorIndex = 0;

Scalar White = Scalar(255, 255, 255);
Scalar Black = Scalar(0, 0, 0);
Scalar Blue = Scalar(255, 0, 0);
Scalar Skyblue = Scalar(255, 255, 0);
Scalar Red = Scalar(0, 0, 255);
Scalar Pink = Scalar(255, 0, 255);
Scalar Green = Scalar(0, 255, 0);
Scalar Lightgreen = Scalar(0, 255, 255);
Scalar Eraser = Scalar(0, 0, 0);

deque<Mat> im;
Mat image2;
int idx = 0;

void LinesDraw(int x,int y){
	//cout << "x = " << x << endl;
	//cout << "y = " << y << endl;
	//cout << endl;
	
	if (true){//(Tswitch == 1) {
		line(draw, Point(prex, prey), Point(x, y), Colors.at(ColorIndex), Linethickness);
		line(Bindraw, Point(prex, prey), Point(x, y), 0, Linethickness);
		prex = x;
		prey = y;
	}
}
void Erase(int x, int y) {
	//cout << "x = " << x << endl;
	//cout << "y = " << y << endl;
	//cout << endl;

	if (true) {//(Tswitch == 1) {
		line(draw, Point(prex, prey), Point(x, y), 0, Linethickness*2);
		line(Bindraw, Point(prex, prey), Point(x, y), Scalar(255,255,255), Linethickness*2);
		prex = x;
		prey = y;
	}
}
/*
void onChange(int value,void* ) {
	Linethickness = value;
}
*/
static void getThreshold(int, void*){}

void greengreen() { //green 이진화
	TempBin = Scalar(0);
	int j;
	for (int i = 0; i < 470; i++) {
		for (j = 0; j < 630; j++) {
			if (HSV.at<Vec3b>(i, j)[0] >= 60 && HSV.at<Vec3b>(i, j)[0] <= 90 && HSV.at<Vec3b>(i, j)[1] >= 80 && HSV.at<Vec3b>(i, j)[2] >= 40) {
				TempBin.at<uchar>(i, j) = 255;
			}
		}
	}
}

void getGreen(){// 전체 그린 찾ㄱ기
	int th = 0;
	//TempBin = Scalar(0);
	int j = 0;
	for (int i = 0; i < 480; i++) {
		for (j = 0; j < 640; j++) {
			if (HSV.at<Vec3b>(i, j)[0] >= 70 && HSV.at<Vec3b>(i, j)[0] <= 85 && HSV.at<Vec3b>(i, j)[1] >= 100 && HSV.at<Vec3b>(i, j)[2] >= 90){
				//TempBin.at<uchar>(i, j) = 255;
				th++;
			}
			else {
				th = 0;
			}
			if (th > 6) {
				cout << "found green in" << j <<","<< i << endl;
				//circle(frame,Point(j,i),5,Scalar(0,255,0),2);
				F_flag = true;
				fx = j;
				fy = i;

				prex = fx;
				prey = fy;
				return ;
			}
		}
	}
	F_flag = false;
	//frame.at<Vec3b>;
}

void roigetGreen() { // 영역 그린 찾기
	int ran = 50;
	//rectangle(frame, Rect(fx - ran, fy - ran, ran*2, ran*2), Scalar(0, 255, 0), 1);
	if (fy - ran < 0)
		fy = ran;
	if (fx - ran < 0)
		fx = ran;
	if (fy + ran > frame.rows) {
		fy = frame.rows - ran ;
	}	
	if (fx + ran > frame.cols) {
		fx = frame.cols - ran;
	}

	int th = 0;
	//TempBin = Scalar(0);
	int j = fx - ran;
	for (int i = fy - ran; i < fy+ran; i++) {
		for (j = 0; j < fx+ran; j++) {
			if (HSV.at<Vec3b>(i, j)[0] >= 55 && HSV.at<Vec3b>(i, j)[0] <= 89 && HSV.at<Vec3b>(i, j)[1] >= 100 && HSV.at<Vec3b>(i, j)[2] >= 70) {
				//TempBin.at<uchar>(i, j) = 255;
				th++;
			}
			else {
				th = 0;
			}
			if (th > 5) {
				fx = j;
				fy = i;
				//circle(frame, Point(j, i), Linethickness, Scalar(0, 255, 0), 2);
				return;
			}
		}
	}
	F_flag = false;
	cout << "lost green "<< endl;
	cout << " " << endl;

	button = false;
	//frame.at<Vec3b>;
}
void roigetRed() { // 영역 RED 찾기
	int ran = 25;
	//rectangle(frame, Rect(fx - ran, fy - ran, ran * 2, ran * 2), Scalar(0, 0, 255), 1);
	if (fy - ran < 0)
		fy = ran;
	if (fx - ran < 0)
		fx = ran;
	if (fy + ran > frame.rows) {
		fy = frame.rows - ran;
	}
	if (fx + ran > frame.cols) {
		fx = frame.cols - ran;
	}
	int th = 0;
	//TempBin = Scalar(0);
	int j = fx - ran;
	for (int i = fy - ran; i < fy + ran; i++) {
		for (j = fx - ran; j < fx + ran; j++) {
			if (HSV.at<Vec3b>(i, j)[0] >= 175 && HSV.at<Vec3b>(i, j)[0] <= 182 && HSV.at<Vec3b>(i, j)[1] >= 110 && HSV.at<Vec3b>(i, j)[2] >= 40) {
				//TempBin.at<uchar>(i, j) = 255;
				th++;
			}
			else {
				th = 0;
			}
			if (th > 5) {
				cout << "found red" << endl;
				circle(frame, Point(j, i), 5, Scalar(0, 0, 255), 2);
				S_flag = true;
				Tswitch = 0;
				return;
			}
		}
	}
	if(S_flag)
		getGreen();
	S_flag = false;
}

void Drawing() {
	LinesDraw(fx, fy);
}

void Ico_init(Mat bin_) {
	for (int i = 0; i < bin_.rows; i++) {
		for (int j = 0; j < bin_.cols; j++) {
			if (bin_.at<Vec3b>(j, i)[0] <= 10&& bin_.at<Vec3b>(j, i)[1] <= 10&&bin_.at<Vec3b>(j, i)[2] <= 10) {
				bin_.at<Vec3b>(j, i) = Vec3b(255, 255, 255);
			}else {
				bin_.at<Vec3b>(j, i) = Vec3b(0,0,0);
			}
		}
	}

}

static int callback(void *data, int argc, char **argv, char **azColName) {
	int i;



	for (i = 0; i<argc; i++) {

		printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
		if (i == 1) {
			char tmp[256];
			sprintf(tmp, "C:\\VoidPainter\\%s.jpg", argv[i]);
			cout << tmp << endl;
			Mat image = imread(tmp, IMREAD_COLOR);
			CV_Assert(image.data);
			im.push_back(image);

			//imshow(argv[i], image);
		}
	}

	printf("\n");
	return 0;
}

int insertDB(const char *name) {
	sqlite3 *db;

	char *zErrMsg = 0;
	int rc;


	rc = sqlite3_open("data.db", &db);
	if (rc) {
		fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
		sqlite3_close(db);
		cout << "파일 미 오픈";
		return -1;
	}


	rc = sqlite3_exec(db, "CREATE TABLE 'images' ( 'No' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'filePath' TEXT NOT NULL UNIQUE); ", callback, 0, &zErrMsg);

	if (rc != SQLITE_OK) {
		fprintf(stderr, "SQL error: %s\n", zErrMsg);
		cout << "예상되는 에러" << endl;
	}

	char tmp[256];
	sprintf(tmp, "INSERT INTO images(filePath) VALUES('%s'); ", name);
	rc = sqlite3_exec(db, tmp, callback, 0, &zErrMsg);
	if (rc != SQLITE_OK) {
		fprintf(stderr, "SQL error: %s\n", zErrMsg);
		cout << "중복이미지" << endl;
		return 0;
	}

	sqlite3_close(db);

	return 1;


}


int getDB() {
	sqlite3 *db;

	char *zErrMsg = 0;
	int rc;


	rc = sqlite3_open("data.db", &db);
	if (rc) {
		fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
		sqlite3_close(db);
		cout << "파일 미 오픈";
		return -1;
	}
	const char* data = "Callback function called";
	rc = sqlite3_exec(db, "SELECT * from images", callback, (void*)data, &zErrMsg);
	if (rc != SQLITE_OK) {
		fprintf(stderr, "SQL error: %s\n", zErrMsg);
		cout << "error" << endl;
		return 0;
	}
	sqlite3_close(db);
	return 1;
}

void onImage() {
	for (int i = 0; i < im[idx].rows; i++) {
		for (int j = 0; j < im[idx].cols; j++) {
			for (int k = 0; k < 3; k++) {
				image2.at<Vec3b>(i + 230, j + 320)[k] = im[idx].at<Vec3b>(i, j)[k];
			}
		}
	}
	return;
}

void onMouse(int event, int x, int y, int flags, void *parameter) {


	if (event == EVENT_LBUTTONDOWN) {
		cout << x << ", " << y;
		if (x >= 100 && x <= 315 && y >= 360 && y <= 600) {
			idx = max(0, idx - 1);
			onImage();
			cout << "왼" << endl;
		}
		if (x >= 970 && x <= 1195 && y >= 360 && y <= 600) {
			int a = im.size();
			idx = min(a, idx + 1);
			onImage();
			cout << "오" << endl;
		}
		cout << "마우스 왼쪽 누르기" << endl;




		imshow("이미지 미리보기", image2);
	}
}

int getImage() {



	for (int i = 0; i < im[0].rows; i++) {
		for (int j = 0; j < im[0].cols; j++) {
			for (int k = 0; k < 3; k++) {
				image2.at<Vec3b>(i + 230, j + 320)[k] = im[0].at<Vec3b>(i, j)[k];
			}
		}
	}
	imshow("이미지 미리보기", image2);
	setMouseCallback("이미지 미리보기", onMouse, 0);


	return 0;

}

void palette_on() {
	
	if (button == false) {
		/*
		frame = (frame & Bindraw) + draw;

		cout << "Palette" << endl;
		button = true;
		cout << "저장할 사진 이름 입력"<< endl ;
		cin >> filename;
		int t = insertDB(filename.c_str());
		if (t == -1) {
			cout << "error";
		}
		else if (t == 0) {
			cout << "중복된 이미지";
		}
		else if (t == 1) {
			cout << "정상작동";
			imwrite("C:\\VoidPainter\\"+filename+".jpg",frame);
		};

		*/
		palette_sw = true;
		cout << "palette" << endl;
	}
}
void no_on() {
	if (button == false) {
		cout << "no" << endl;
		palette_sw = false;
		eraser_sw = false;
	}
}
void blue_on() {
	if (button == false) {
		cout << "blue1" << endl;
		ColorIndex = 0;
		palette_sw = false;
	}
}
void green_on() {
	if (button == false) {
		cout << "green2" << endl;
		ColorIndex = 1;
		palette_sw = false;
	}
}
void red_on() {
	if (button == false) {
		cout << "red3" << endl;
		ColorIndex = 2;
		palette_sw = false;
	}
}
void cyan_on() {
	if (button == false) {
		cout << "cyan1" << endl;
		ColorIndex = 3;
		palette_sw = false;
	}
}
void yellow_on() {
	if (button == false) {
		cout << "yellow2" << endl;
		ColorIndex = 4;
		palette_sw = false;
	}
}
void pink_on() {
	if (button == false) {
		cout << "pink3" << endl;
		ColorIndex = 5;
		palette_sw = false;
	}
}
void white_on() {
	if (button == false) {
		cout << "black7" << endl;
		ColorIndex = 6;
		palette_sw = false;
	}
}
void black_on() {
	if (button == false) {
		cout << "white8" << endl;
		ColorIndex = 7;
		palette_sw = false;
	}
}
void eraser_on() {
	if (button == false) {
		cout << "ERASER" << endl;
		//ColorIndex = 8;
		eraser_sw = true;
	}
}


int main(){
	int value = 2;
	Colors.push_back(Blue);
	Colors.push_back(Green);
	Colors.push_back(Red);
	Colors.push_back(Skyblue);
	Colors.push_back(Lightgreen);
	Colors.push_back(Pink);
	Colors.push_back(White);
	Colors.push_back(Black);
	Colors.push_back(Eraser);
	
	VideoCapture video(0);
	video >> frame;

	namedWindow("input", WINDOW_AUTOSIZE);

	resize(frame, draw, Size(frame.cols, frame.rows));
	draw = Scalar(0, 0, 0);
	resize(draw, Bindraw, Size(frame.cols, frame.rows));
	Bindraw = Scalar(255,255,255);

	
	createTrackbar("thickness", "input", &value, 20, getThreshold);
	setTrackbarPos("thickness", "input", 2);	
	/*
	createTrackbar("color", "input", &value, Colors.size()-1, getThreshold);
	setTrackbarPos("color", "input", 0);
	*/
	cvtColor(frame, HSV, COLOR_BGR2HSV);

	Rect palette_rec = Rect(10, 10, 60, 60);
	Mat palette_ico = imread("C:\\opcv\\brush.jpg");
	Mat bin_palette;
	Mat palette_roi;
	resize(palette_ico, bin_palette, Size(palette_ico.cols, palette_ico.rows));
	Ico_init(bin_palette);

	Rect eraser_rec = Rect(10, 100, 60, 60);
	Mat eraser_ico = imread("C:\\opcv\\ERASER.jpg");
	Mat bin_eraser;
	Mat eraser_roi;
	resize(eraser_ico, bin_eraser, Size(60, 60));
	Ico_init(bin_eraser);

	Rect no_rec = Rect(10, 10, 60, 60);
	Mat no_ico = imread("C:\\opcv\\can.jpg");
	Mat bin_no;
	Mat no_roi;
	resize(no_ico, bin_no, Size(60, 60));
	Ico_init(bin_no);
	
	Rect blue_rec = Rect(80, 10, 60, 60);
	Mat blue_ico = imread("C:\\opcv\\blue.jpg");
	Mat bin_blue;
	Mat blue_roi;
	resize(blue_ico, bin_blue, Size(60,60));
	Ico_init(bin_blue);	

	Rect green_rec = Rect(150, 10, 60, 60);
	Mat green_ico = imread("C:\\opcv\\green.jpg");
	Mat bin_green;
	Mat green_roi;
	resize(green_ico, bin_green, Size(60, 60));
	Ico_init(bin_green);	
	
	Rect red_rec = Rect(220, 10, 60, 60);
	Mat red_ico = imread("C:\\opcv\\red.jpg");
	Mat bin_red;
	Mat red_roi;
	resize(red_ico, bin_red, Size(60, 60));
	Ico_init(bin_red);

	Rect cyan_rec = Rect(290, 10, 60, 60);
	Mat cyan_ico = imread("C:\\opcv\\cyan.jpg");
	Mat bin_cyan;
	Mat cyan_roi;
	resize(cyan_ico, bin_cyan, Size(60, 60));
	Ico_init(bin_cyan);

	Rect yellow_rec = Rect(360, 10, 60, 60);
	Mat yellow_ico = imread("C:\\opcv\\yellow.jpg");
	Mat bin_yellow;
	Mat yellow_roi;
	resize(yellow_ico, bin_yellow, Size(60, 60));
	Ico_init(bin_yellow);

	Rect pink_rec = Rect(430, 10, 60, 60);
	Mat pink_ico = imread("C:\\opcv\\pink.jpg");
	Mat bin_pink;
	Mat pink_roi;
	resize(pink_ico, bin_pink, Size(60, 60));
	Ico_init(bin_pink);	
	
	Rect black_rec = Rect(500, 10, 60, 60);
	Mat black_ico = imread("C:\\opcv\\black.jpg");
	Mat bin_black;
	Mat black_roi;
	resize(black_ico, bin_black, Size(60, 60));
	Ico_init(bin_black);	
	
	Rect white_rec = Rect(570, 10, 60, 60);
	Mat white_ico = imread("C:\\opcv\\white.jpg");
	Mat bin_white;
	Mat white_roi;
	resize(white_ico, bin_white, Size(60, 60));
	Ico_init(bin_white);

	insertDB("test3");
	image2 = imread("C:\\opcv\\select.jpg", IMREAD_COLOR);
	CV_Assert(image2.data);
	getDB();
	getImage();

	button = false;

	while (true) {
		video >> frame;
		flip(frame, frame, 1);
		if (waitKey(50) == 27) {
			break;
		}
		cvtColor(frame, HSV, COLOR_BGR2HSV); 

		if (F_flag == true) {
			roigetGreen();
		}else {
			getGreen();
		}

		//cout << F_flag << endl;
		//greengreen();
		
		Linethickness = getTrackbarPos("thickness", "input");
		//ColorIndex = getTrackbarPos("color", "input");
		
		if (F_flag == true) {
			if (palette_rec.contains(Point(fx, fy)) && palette_sw == false && eraser_sw != true) {
				palette_on();
				button = true;
			}else {
				//palette_sw = false;
			}
			if (eraser_rec.contains(Point(fx, fy)) && eraser_sw == false && palette_sw !=true) {
				eraser_on();
				button = true;
			}
			if (eraser_sw == true) {
				if (no_rec.contains(Point(fx, fy))) {
					no_on();
					button = true;
				}
			}

			if (palette_sw == true) {
				if (no_rec.contains(Point(fx, fy))) {
					no_on();
					button = true;
				}
				if (blue_rec.contains(Point(fx, fy))) {
					blue_on();
					button = true;
				}
				if (green_rec.contains(Point(fx, fy))) {
					green_on();
					button = true;
				}
				if (red_rec.contains(Point(fx, fy))) {
					red_on();
					button = true;
				}
				if (cyan_rec.contains(Point(fx, fy))) {
					cyan_on();
					button = true;
				}
				if (yellow_rec.contains(Point(fx, fy))) {
					yellow_on();
					button = true;
				}
				if (pink_rec.contains(Point(fx, fy))) {
					pink_on();
					button = true;
				}
				if (white_rec.contains(Point(fx, fy))) {
					white_on();
					button = true;
				}
				if (black_rec.contains(Point(fx, fy))) {
					black_on();
					button = true;
				}/*
				if (blue_rec.contains(Point(fx, fy))) {
					blue_on();
					button = true;
				}
				*/
			}
		}

		if (button == false) {
			if (F_flag == true && S_flag == false) {
				if (eraser_sw == true) {
					Erase(fx, fy);
				}else{
					Drawing();
				}
			}
		}
		
		frame = (frame & Bindraw)+draw;

		/*
		imshow("in", palette_ico);
		imshow("inpt", bin_palette);
		*/
		if (palette_sw == false && eraser_sw == false) {
			palette_roi = frame(palette_rec);
			palette_roi = (palette_roi & bin_palette) + palette_ico;

			eraser_roi = frame(eraser_rec);
			eraser_roi = (eraser_roi & bin_eraser) + eraser_ico;
		}
		else {
			no_roi = frame(no_rec);
			no_roi = (no_roi & bin_no) + no_ico;

			if (palette_sw == true) {
				blue_roi = frame(blue_rec);
				blue_roi = (blue_roi & bin_blue) + blue_ico;

				green_roi = frame(green_rec);
				green_roi = (green_roi & bin_green) + green_ico;

				red_roi = frame(red_rec);
				red_roi = (red_roi & bin_red) + red_ico;

				cyan_roi = frame(cyan_rec);
				cyan_roi = (cyan_roi & bin_cyan) + cyan_ico;

				yellow_roi = frame(yellow_rec);
				yellow_roi = (yellow_roi & bin_yellow) + yellow_ico;

				pink_roi = frame(pink_rec);
				pink_roi = (pink_roi & bin_pink) + pink_ico;

				white_roi = frame(white_rec);
				white_roi = (white_roi & bin_white) + white_ico;

				black_roi = frame(black_rec);
				black_roi = (black_roi & bin_black) + black_ico;
			}
		}

		if (F_flag == true) { // 커서 그리기
			circle(frame, Point(fx, fy), Linethickness,Colors[ColorIndex], 2);
		}

		imshow("input", frame);
		//imshow("Bindraw", TempBin);
		//imshow("draw", draw);
	}

	return 0;
}