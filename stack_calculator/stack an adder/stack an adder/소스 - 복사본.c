#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<math.h>

typedef struct postfix {
	double n;    //연산자, 숫자
	short set;  //연산자 = 0, 숫자 = 1
}Postfix;


int ChangePostfix(char *s, Postfix *num) {
	
	char stack[100] = { 0, };
	int i=0, j=0, Psp=0, Ssp=0;
	double n = 0; //숫자 저장
	int t = 0, tt = 0; //t가0 이면 정수 1이면 소수    tt가 0이면 양수 tt가 1이면 음수
	double d = 1; //소수점 아래의 점을 표현하기 위한 값
	int realminus = 1; //
	for (i = 0; s[i] != 0; i++) {
		if ('0' <= s[i] && s[i] <= '9') {
			if (s[i - 1] == ')') {
				if (Ssp != 0) {
					for (j = Ssp - 1; j >= 0 && stack[j] != '(' && stack[j] != '+' && stack[j] != '-'; j--) {
						num[Psp].n = stack[j];
						num[Psp].set = 0;
						Psp++;
						stack[j] = 0;
					}
					j++;
					Ssp = j;
				}
				stack[Ssp++] = '*';
			}
			
			if (t == 0) {
				n *= 10;
				n += s[i] - 48;
			}
			else {
				d *= 0.1;
				n += d*(s[i] - 48);
			}
		}
		else if (s[i] == '.') {
			t = 1;
		}
		else if (s[i] == '-' && (i == 0 || s[i - 1] == '(' || s[i - 1] == '+' || s[i - 1] == '-' || s[i - 1] == '*' || s[i - 1] == '/' || s[i - 1] == '^' || s[i - 1] == 6 || s[i - 1] == '%')) {
			tt = 1;
		}
		else {
			if (i != 0) {
				if (s[i - 1] != '(' && s[i - 1] != ')' && s[i - 1] != '+' && s[i - 1] != '-' && s[i - 1] != '*' && s[i - 1] != '/' && s[i - 1] != '^' && s[i - 1] != 6 && s[i - 1] != '%') {
					if (tt == 1 ) {
						if (s[i] == '^') {
							s[i] = 6;
						}
						else
							n *= -1;
						tt = 0;
					}
					
					num[Psp].n = n;
					num[Psp].set = 1;
					Psp += 1;
					n = 0;
					d = 1;
					t = 0;
					
				}
			}

			if (s[i] == ')') {
			
				for (j = Ssp - 1; stack[j] != '('; j--) {
					num[Psp].n = stack[j];
					num[Psp].set = 0;
					Psp++;
					stack[j] = 0;
				}
				Ssp = j;
				stack[j] = 0;
				if (Ssp >= 1) {
					if (stack[Ssp - 1] == '-' && (Ssp == 1 || stack[Ssp - 2] == '(' || stack[Ssp - 2] == '+' || stack[Ssp - 2] == '-' || stack[Ssp - 2] == '*' || stack[Ssp - 2] == '/' || stack[Ssp - 2] == '^' || stack[Ssp - 2] == 6 || stack[Ssp - 2] == '%')) {
						num[Psp].n = -1;
						num[Psp].set = 1;
						Psp++;
						num[Psp].n = '*';
						num[Psp].set = 0;
						Psp++;
						stack[Ssp - 1] = 0;
						Ssp--;
					}
				}
				
			}
			else if (s[i] == '(') {

				/*if (i > 1) {
					if (num[Psp - 1].n < 0 && '0' <= s[i - 1] && s[i - 1] <= '9')
						num[Psp - 1].n *= -1;
				}*/
					
					
				if (s[i - 1] == ')' || ('0' <= s[i-1] && s[i-1] <= '9')) {
					if (Ssp != 0) {
						for (j = Ssp - 1; j >= 0 && stack[j] != '(' && stack[j] != '+' && stack[j] != '-'; j--) {
							num[Psp].n = stack[j];
							num[Psp].set = 0;
							Psp++;
							stack[j] = 0;
						}
						j++;
						Ssp = j;
						
					}
					stack[Ssp++] = '*';
				}
				
				if (i != 0) {
					if (s[i - 1] == '-') {
						tt = 0;
						stack[Ssp++] = '-';
					}
				}

				stack[Ssp++] = s[i];

			}
			else if (s[i] == '^' || s[i] == 6) {
				if (Ssp != 0) {
					for (j = Ssp - 1; j >= 0 && stack[j] != '(' && stack[j] != '+' && stack[j] != '-' && stack[j] != '*' && stack[j] != '/' && stack[j] != '^' && stack[j] != '%'; j--) {
						num[Psp].n = stack[j];
						num[Psp].set = 0;
						Psp++;
						stack[j] = 0;
					}
					j++;
					Ssp = j;
				}
				stack[Ssp++] = s[i];

			}
			else if (s[i] == '*' || s[i] == '/' || s[i] == '%') {


				if (Ssp != 0) {
					for (j = Ssp - 1; j >= 0 && stack[j] != '(' && stack[j] != '+' && stack[j] != '-'; j--) {
						num[Psp].n = stack[j];
						num[Psp].set = 0;
						Psp++;
						stack[j] = 0;
					}
					j++;
					Ssp = j;
				}
				stack[Ssp++] = s[i];
				//stack[Ssp++] = 'n';
			}
			else if (s[i] == '+' || s[i] == '-') {

				if (Ssp != 0) {
					for (j = Ssp - 1; j >= 0 && stack[j] != '('; j--) {
						num[Psp].n = stack[j];
						num[Psp].set = 0;
						Psp++;
						stack[j] = 0;
					}
					j++;
					Ssp = j;
				}


				stack[Ssp++] = s[i];

			}
		}
	}
	
	if (i != 0) {
		if (s[i - 1] != '(' && s[i - 1] != ')' && s[i - 1] != '+' && s[i - 1] != '-' && s[i - 1] != '*' && s[i - 1] != '/' && s[i - 1] != '^' && s[i - 1] != '%') {
			if (tt == 1) {
				if (s[i] == '^') {
					s[i] = 6;
				}
				else
					n *= -1;
				tt = 0;
			}

			num[Psp].n = n;
			num[Psp].set = 1;
			Psp += 1;
			n = 0;
			d = 1;
			t = 0;

		}
	}

	for (j = Ssp-1; j >= 0; j--) {
		num[Psp].n = stack[j];
		num[Psp].set = 0;
		Psp++;
		stack[j] = 0;
	}
	return Psp;
}

double Calculation(Postfix *num, int cnt) {
	
	double stack[100] = { 0, };
	int i,sp=0;
	double n1=0, n2=0;

	for (i = 0; i < cnt; i++) {
		if (num[i].set == 1)
			stack[sp++] = num[i].n;
		else if (num[i].set == 0) {
			sp--;
			n2 = stack[sp];
			stack[sp--] = 0;
			n1 = stack[sp];

			printf("\n%.2lf %c %.2lf = ", n1, (int)num[i].n, n2);
			if (num[i].n == '*') {
				stack[sp] = n1*n2;
				printf("%.2lf", n1*n2);
			}
			else if (num[i].n == '/'){
				stack[sp] = n1 / n2;
				printf("%.2lf", n1/n2);
			}
			else if (num[i].n == '+') {
				stack[sp] = n1 + n2;
				printf("%.2lf", n1+n2);
			}
			else if (num[i].n == '-') {
				stack[sp] = n1 - n2;
				printf("%.2lf", n1-n2);
			}
			else if (num[i].n == '^') {
				stack[sp] = pow(n1,n2);
				printf("%.2lf", pow(n1,n2));
			}
			else if (num[i].n == 6) {
				stack[sp] = pow(n1, n2) * -1;
				printf("%.2lf", pow(n1, n2) * -1);
			}
			else if (num[i].n == '%') {
				stack[sp] = (int)n1 % (int)n2;
				printf("%d", (int)n1 % (int)n2);
			}

			sp++;
		}
	}
	return stack[sp-1];
}

int main() {

	char s[1000] = { 0, };
	Postfix num[100] = { 0, };
	int cnt = 0;
	int i;

	scanf("%s", s);
	cnt = ChangePostfix(s, num);
	
	
	for (i = 0; i < cnt; i++) {
		if (num[i].set == 1)
			printf("%.2lf ", num[i].n);
		else
			printf("%c ", (int)num[i].n);
	}
	printf("\n\n%.2lf\n", Calculation(num, cnt));

	return 0;
}