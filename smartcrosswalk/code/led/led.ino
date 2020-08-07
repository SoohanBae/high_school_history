#define term 2000         //신호대기 시간
#define cterm 500          //횡단보도 깜빡임 텀
#define crossTime 8000    //횡단보도 시간
#define crossOverTime 4000 //횡단보도 깜빡임 시간
#define lightTime 10000    //차량 신호 시간

int redPin = 13;
int yellowPin = 12;
int greenPin = 11;
int cRedPin = 10;
int cGreenPin = 9;



void setup() {
  // put your setup code here, to run once:
  
  pinMode(redPin, OUTPUT);
  pinMode(yellowPin, OUTPUT);
  pinMode(greenPin, OUTPUT);
  pinMode(cRedPin, OUTPUT);
  pinMode(cGreenPin, OUTPUT);
  Serial.begin(9600);
  


}

void loop() {
  // put your main code here, to run repeatedly:

  digitalWrite(redPin,LOW);
  digitalWrite(cGreenPin,LOW);
  digitalWrite(cRedPin,HIGH);
  digitalWrite(greenPin,HIGH);
  delay(lightTime);

  digitalWrite(greenPin,LOW);
  digitalWrite(yellowPin,HIGH);
  delay(term);

  digitalWrite(yellowPin,LOW);
  digitalWrite(redPin,HIGH);

  digitalWrite(cRedPin,LOW);
  digitalWrite(cGreenPin,HIGH);
  delay(crossTime - crossOverTime);

  
  for(int i = 0; i < crossTime / (cterm * 2); i++){
    digitalWrite(cGreenPin,LOW);
    delay(cterm);
    digitalWrite(cGreenPin,HIGH);
    delay(cterm);
  }
   

  
  
}
