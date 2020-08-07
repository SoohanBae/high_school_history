
public class Math_comb {
	public static void main(String[] args) {
		int sum = 0; //testtest
		int cnt_3 = 0; //test2
		int cnt_2 = 0;
		for (int i = 1; i <= 50; i++) {
			for (int j = 1+i; j <= 50; j++) {
				if (i == j)
					continue;
				for (int k = 1+j; k <= 50; k++) {
					if (i == k || j == k)
						continue;
					if (i % 3 == 0 && j % 3 == 0 && k % 3 == 0)	cnt_3++;
					else if (i % 3 == 1 && j % 3 == 1 && k % 3 == 1) cnt_3++;
					else if (i % 3 == 2 && j % 3 == 2 && k % 3 == 2) cnt_3++;
					else if (i % 3 == 0 && j % 3 == 1 && k % 3 == 2 || i % 3 == 0 && j % 3 == 2 && k % 3 == 1
							|| i % 3 == 1 && j % 3 == 0 && k % 3 == 2 || i % 3 == 1 && j % 3 == 2 && k % 3 == 0
							|| i % 3 == 2 && j % 3 == 0 && k % 3 == 1 || i % 3 == 2 && j % 3 == 1 && k % 3 == 0)
						cnt_3++;
					if (i%2==0&&j%2==0&&k%2==0) cnt_2++;
					else if(i%2==0&&j%2!=0&&k%2!=0||i%2!=0&&j%2!=0&&k%2==0
							||i%2!=0&&j%2==0&&k%2!=0) cnt_2++;
				}
			}
		}
		System.out.println("세 정수의 합이");
		System.out.println("    짝수인경우  : " + cnt_2);
		System.out.println("  3의 배수인경우 : " + cnt_3);
	}
}
