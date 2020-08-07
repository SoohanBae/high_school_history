
public class Math_line {
	public static void main(String[] args) {
		int cnt = 0;
		for (int i = 1; i <= 15; i++) {
			for (int j = i + 1; j <= 15; j++) {
				for (int k = j + 1; k <= 15; k++) {
					for (int m = k + 1; m <= 15; m++) {
						for (int n = m + 1; n <= 15; n++) {
							for (int o = n + 1; o <= 15; o++) {
								if(i==1&&j==2&&k==3) cnt++;
							}
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}
}
