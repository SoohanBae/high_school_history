int ledPin = 13;                                           //디지털 3번핀을 ledPin으로     (LED램프)

int inputPin = 2;                                        //디지털 2번핀을 inputPin으로    (센서)

int val = 0;



void setup() {

  pinMode(ledPin, OUTPUT);           //ledPin을 출력모드로

  pinMode(inputPin, INPUT);            //inputPin을 입력모드로
Serial.begin(9600);
}



void loop() {

  val = digitalRead(inputPin);             //val = 센서값
Serial.println(val); 
  if (val == HIGH)                                  //만약 센서값이  HIGH 라면

    digitalWrite(ledPin, HIGH);             //ledPin에 HIGH 신호를,

  else                                                      //그게아니라면

    digitalWrite(ledPin, LOW);             //ledPin에 LOw 신호를

 
  delay(10);
}

