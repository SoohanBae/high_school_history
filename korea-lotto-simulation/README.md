# korea-lotto-simulation
말그대로 로또 시뮬레이션입니다
## 개요
이산수학 시간에 조합을 배우던 도중, 로또의 확률이 어떤지 계산해 보고 싶었습니다. <br>
그리고 로또 시뮬레이션도 만들어 실제로 확률이 비슷하게 결괏값이 나오는지 테스트 해보았습니다.
## 로또확률
[로또 확률 계산 설명](https://blog.naver.com/alwaysneoi/100186063813)
링크에 잘 설명되어 있습니다.
## 시뮬레이션 결과
<img src="https://postfiles.pstatic.net/MjAxODA0MjhfMjUw/MDAxNTI0OTEyODQ0OTU3.3PKEROGbq3xdPhR-JODRUz1wvgHfmuR4C-QZM6dU3Usg.ybQAU3Hd9clv7gK1VB6_6JT-Wz8LxWnq2TurESfGrM0g.PNG.soohan530/image_800208271524912272927.png?type=w773" width="536" height="150"></img><br>
10억번 로또번호를 추첨헀을 때 결과입니다. 수치가 어느정도 비슷할까요? <br>
당첨횟수 * 확률의 역수 = 10억정도 
- 1등 : 109 * 8,145,060 = 887,811,540 
- 2등 : 714 * 1,357,510 = 969,262,140
- 3등 : 27,986 * 35,724 = 999,771,864
- 4등 : 1,362,538 * 733 = 998,740,354
- 5등 : 22,442,241 * 45 = 1,009,900,845
10억 부근의 값이 나오는 것을 확인 할 수 있습니다. 

특별한 경우라고 생각하는 1, 2, 3, 4, 5, 6을 넣어봤습니다.
<img src="https://postfiles.pstatic.net/MjAxODA0MjhfMjk5/MDAxNTI0OTEzOTc2MjI0.18B-3TY2P-oue1R0wzfOwJrV8UFB06OdyjhNt5ZBq0Eg.ZmJwjjcWSMoiWW5EB_vGXKgcqEps3e-ZJ-U-_oNSECwg.PNG.soohan530/image.png?type=w773" width="499" height="154"></img>

1등을 제외하면 위의 결과와 비슷한 것을 확인할 수 있습니다.<br>
1등의 수치는 시뮬레이션을 돌린 횟수가 적기 때문에 범위차이가 크다고 생각합니다.
