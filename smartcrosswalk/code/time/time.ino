unsigned long ret_time; //시작시간
unsigned long now_time; //중간시간

void setup(){
    Serial.begin(9600);
}
void loop(){

    
    Serial.print("Time: ");
    ret_time = (unsigned long)millis(); //타이머 측정전 시간

    while(true){
        now_time = (unsigned long)millis(); //타이머 측정후 시간
        if(1000 <= now_time - ret_time){ //측정후와 측정전을 뺀 값이 1000ms를 넘기면 탈출

            Serial.println(now_time);
            break;

        }

    }

}
