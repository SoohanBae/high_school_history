#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<math.h>

typedef struct postfix {
	double n;    //������, ����
	short set;  //������ = 0, ���� = 1
}Postfix;

int Checks(char *s, Postfix *nums) {
	
	if (s[1000] != 0) 		
		return -1; //�Է¹迭 �ʰ�


	int i,j;
	int t = 0, tt = 0; //t��0 �̸� ���� 1�̸� �Ҽ�    tt�� 0�̸� ��� tt�� 1�̸� ����
	double n = 0; //���� ����
	double d = 1; //�Ҽ��� �Ʒ��� ���� ǥ���ϱ� ���� ��
	int nsp = 0;
	int notminus = 0; //���̳ʽ��� ���� �ϴ��� �ƴ��� Ȯ��
	int Parentheses = 0; //��ȣ �˻�

	for (i = 0; s[i] != 0; i++) {              //��������
		if (s[i] == ' ') {
			for (j = i; s[j] != 0; j++) 
				s[j] = s[j + 1];
			s[j] = 0;
			i--;
		}
	}

	for (i = 0; s[i] != 0; i++) {
		if ('0' <= s[i] && s[i] <= '9') {

			if (t == 0) {           //t�� �Ҽ��� �������� �����κ����� �Ǵ��ϴ� ����
				n *= 10;			//���ڸ��� ���
				n += s[i] - 48;    //�ƽ�Ű �ڵ��̹Ƿ� 48�� ��
			}
			else {
				d *= 0.1;          //�Ҽ��Ʒ� ���
				n += d*(s[i] - 48);
			}
		}
		else if (s[i] == '.') {   //�Ҽ� �����̸� ���� ���� �ٲ�
			t = 1;
		}
		else if (s[i] == '-' && (i == 0 || s[i - 1] == '(' || s[i - 1] == '+' || s[i - 1] == '-' || s[i - 1] == '*' || s[i - 1] == '/' || s[i - 1] == '^' || s[i - 1] == '%')) {
			//'s[i - 1] == ')' �̸� ���⸦ ���ϹǷ� ����
			//�迭 s�� ������ ��� ���ڿ�

			if (s[i + 1] == '(' && tt == 1) {
				s[i] = '+';
				tt = 0;
			}
			else if (s[i + 1] == '(' || tt == 1)  //�տ� '('�̸� -1�� ������ ������ ���� tt ==1 �ϰ��� -�� �ߺ������� �Ǳ⿡ ���ó�� ���
				tt = 0;
			else
				tt = 1;
			//tt�� �������� ������� �������ִ� ��� Ű

		}
		else if ((s[i] == '*' || s[i] == '/' || s[i] == '%' || s[i] == '^') && ((i == 0 || s[i - 1] == '(' || s[i - 1] == '+' || s[i - 1] == '-' || s[i - 1] == '*' || s[i - 1] == '/' || s[i - 1] == '^' || s[i - 1] == '%') || !(('0' <= s[i+1] && s[i+1] <= '9') || s[i + 1] == '-' || s[i + 1] == '+'))) {
			return - 4;
		}
		else if (s[i] == '!' && (i == 0 || !(('0' <= s[i - 1] && s[i - 1] <= '9') || s[i - 1] == ')' || s[i - 1] == '!' || s[i-1] == 1) || ('0' <= s[i + 1] && s[i + 1] <= '9'))) {
			return -4;
		}
		else if (s[i] == '!' && i != 0 && s[i - 1] == 1) {
			s[i] = 1;
			nums[nsp].n = s[i];
			nums[nsp].set = 0;
			nums[nsp - 1].n = '!';

			nsp++;
		}
		else if (s[i] == '+' && (i == 0 || s[i - 1] == '(' || s[i - 1] == '+' || s[i - 1] == '-' || s[i - 1] == '*' || s[i - 1] == '/' || s[i - 1] == '^'|| s[i - 1] == '%')) {
		
		}
		
		else if (s[i] == '^' || s[i] == '*' || s[i] == '/' || s[i] == '%' || s[i] == '+' || s[i] == '-' || s[i] == ')' || s[i] == '(' || s[i] == '!') {
			if (i != 0) {
				if (s[i - 1] != '(' && s[i - 1] != ')' && s[i - 1] != '+' && s[i - 1] != '-' && s[i - 1] != '*' && s[i - 1] != '/' && s[i - 1] != '^' && s[i - 1] != '%' && s[i - 1] != '!') {
					
					if (tt != 0 && s[i] == '^') {
						s[i] = 6;
						tt = 0;
					}	
					else if (tt != 0 && s[i] == '!') {			
							s[i] = 1;
							tt = 0;
					}
					
					if (tt == 1)
						n *= -1;

					nums[nsp].n = n;
					nums[nsp].set = 1;
					nsp += 1;
					n = 0;
					d = 1;
					t = 0;
					tt = 0;
									
				}
			}

			

			if (s[i] == '(') {
				if (i != 0) {
					if ('0' <= s[i - 1] && s[i - 1] <= '9') {
						nums[nsp].n = '*';
						nums[nsp].set = 0;
						nsp++;
					}
					if (s[i - 1] == '-' && (i == 1 || s[i - 2] == '(' || s[i - 2] == '+' || s[i - 2] == '-' || s[i - 2] == '*' || s[i - 2] == '/' || s[i - 2] == '^' || s[i - 2] == 6 || s[i - 2] == '%')) {
						nums[nsp].n = -1;
						nums[nsp].set = 1;
						nsp++;
						nums[nsp].n = '*';
						nums[nsp].set = 0;
						nsp++;
					}
				}
				Parentheses++;
			}
			


			nums[nsp].n = s[i];
			nums[nsp].set = 0;
			nsp++;
			
			if (s[i] == ')') {
				if ('0' <= s[i + 1] && s[i + 1] <= '9') {
					nums[nsp].n = '*';
					nums[nsp].set = 0;
					nsp++;
				}
				Parentheses--;
				if (i != 0 && s[i - 1] == '(')
					return -2;

				if (i != 0 && (s[i - 1] == '+' || s[i - 1] == '-'))
					return -4;
			}

			if (Parentheses < 0)
				return -2; //�̻��� ��ȣ

		}
		else
			return -3; // �̻��� ����
	}
	if (Parentheses != 0)
		return -2; //�̻��Ѱ�ȣ

	if (i != 0) {
		if (s[i - 1] != '(' && s[i - 1] != ')' && s[i - 1] != '+' && s[i - 1] != '-' && s[i - 1] != '*' && s[i - 1] != '/' && s[i - 1] != '^' && s[i - 1] != '%' && s[i - 1] != '!' && s[i - 1] != 1) {
			

			if (tt == 1) 
				n *= -1;
			
			nums[nsp].n = n;
			nums[nsp].set = 1;
			nsp++;

		}
		
		if (s[i - 1] == '+' || s[i - 1] == '-')
			return -4;

	}

	
	return nsp;
}

int ChangePostfix(Postfix *nums, Postfix *num, int cnt) {
	
	char stack[100] = { 0, };
	int i=0, j=0, Psp=0, Ssp=0;

	for (i = 0; i < cnt; i++) {
		
		if (nums[i].set == 1) {
			num[Psp].n = nums[i].n;
			num[Psp].set = 1;
			Psp++;
		}
		else {
			if (nums[i].n == ')') {
			
				for (j = Ssp - 1; stack[j] != '('; j--) {
					num[Psp].n = stack[j];
					num[Psp].set = 0;
					Psp++;
					stack[j] = 0;
				}
				Ssp = j;
				stack[j] = 0;
				if (Ssp >= 1) {
					if (stack[Ssp - 1] == '-' && ( stack[Ssp - 2] == '(' || stack[Ssp - 2] == '+' || stack[Ssp - 2] == '-' || stack[Ssp - 2] == '*' || stack[Ssp - 2] == '/' || stack[Ssp - 2] == '^' || stack[Ssp - 2] == 6 || stack[Ssp - 2] == '%')) {
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
			else if (nums[i].n == '(') {
	
				if (nums[i - 1].n == ')' || ('0' <= nums[i-1].n && nums[i-1].n <= '9')) {
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
				
				stack[Ssp++] = (char)nums[i].n;

			}
			else if (nums[i].n == '!' || nums[i].n == 1) {
				if (Ssp != 0) {
					for (j = Ssp - 1; j >= 0 && stack[j] != '(' && stack[j] != '+' && stack[j] != '-' && stack[j] != '*' && stack[j] != '/' && stack[j] != '^' && stack[j] != 6 && stack[j] != '%'; j--) {
						num[Psp].n = stack[j];
						num[Psp].set = 0;
						Psp++;
						stack[j] = 0;
					}
					j++;
					Ssp = j;
				}
				stack[Ssp++] = (char)nums[i].n;
			}
			else if (nums[i].n == '^' || nums[i].n == 6) {

				if (Ssp != 0) {
					for (j = Ssp - 1; j >= 0 && stack[j] != '(' && stack[j] != '+' && stack[j] != '-' && stack[j] != '*' && stack[j] != '/' && stack[j] != '^' && stack[j] != 6 && stack[j] != '%'; j--) {
						num[Psp].n = stack[j];
						num[Psp].set = 0;
						Psp++;
						stack[j] = 0;
					}
					j++;
					Ssp = j;
				}
				stack[Ssp++] = (char)nums[i].n;

			}
			else if (nums[i].n == '*' || nums[i].n == '/' || nums[i].n == '%') {


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
				stack[Ssp++] = (char)nums[i].n;
				//stack[Ssp++] = 'n';
			}
			else if (nums[i].n == '+' || nums[i].n == '-') {

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


				stack[Ssp++] = (char)nums[i].n;

			}
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
	int i, sp=0;
	double j;
	double n1=0, n2=0;
	double fact = 1;
	for (i = 0; i < cnt; i++) {
		if (num[i].set == 1)
			stack[sp++] = num[i].n;
		else if (num[i].set == 0 && (num[i].n == 1 || num[i].n == '!')) {
			fact = 1;
			sp--;
			n1 = stack[sp];
			stack[sp] = 0;

			if (n1 < 1)
				n1 = 1;

			for (j = n1; j > 1; j--) {
				if (fact == INFINITY)
					break;

				fact *= j;
			}

			if (num[i].n == '!') {
				stack[sp] = fact;
				printf("%.2lf ! = %.2lf\n ",n1, fact);
			}
			else if (num[i].n == 1) {
				stack[sp] = -1.0 * fact;
				printf("%.2lf -! = %.2lf\n", n1, -1.0 * fact);
			}
			sp++;
		}
		else if (num[i].set == 0) {
			sp--;
			n2 = stack[sp];
			stack[sp--] = 0;
			n1 = stack[sp];

			printf("\n%.2lf %c %.2lf = ", n1, (int)(num[i].n), n2);

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
				printf("\r                                 ");
				printf("\r%.2lf -^ %.2lf = ", n1, n2);
				stack[sp] = pow(n1, n2) * -1;
				printf("%.2lf", pow(n1, n2) * -1.0);
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
	
	char s[1001] = { 0, };
	Postfix num[1000] = { 0, };
	Postfix nums[1000] = { 0, };
	int cnt = 0;
	int i;

	scanf("%1000[^\n]s", s);

	cnt = Checks(s, nums);
	printf("������ ���� ��ȯ : ");
	for (i = 0; i < cnt; i++) {
		if (nums[i].set == 1)
			printf("%.2lf ", nums[i].n);
		else
			if (nums[i].n == 6)
				printf("-^");
			else if (nums[i].n == 1)
				printf("-!");
			else
				printf("%c ", (int)nums[i].n);
	}
	
	if (cnt >= 0) {
		printf("\n������������ �ٲٱ� : ");
		cnt = ChangePostfix(nums, num, cnt);

		for (i = 0; i < cnt; i++) {
			if (num[i].set == 1)
				printf("%.2lf ", num[i].n);
			else {
				if (num[i].n == 6)
					printf("-^ ");
				else if (num[i].n == 1)
					printf("-! ");
				else
					printf("%c ", (int)(num[i].n));
			}
		}
		printf("\n\n������ : \n");
		printf("\n\n��� : %.2lf\n", Calculation(num, cnt));
	}
	else if (cnt == 0)
		printf("�����Ͱ� �����ϴ�.\n");
	else if (cnt == -1)
		printf("�迭 �ʰ�!\n");
	else if (cnt == -2)
		printf("��ȣ ���� !\n");
	else if (cnt == -3)
		printf("�̻��� ���ڰ� �ֽ��ϴ�.\n");
	else if (cnt == -4)
		printf("������ ����!\n");

	return 0;
}