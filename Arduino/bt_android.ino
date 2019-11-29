#include <SoftwareSerial.h>

char str;
//int num;
int pin_left = 4;
//int pin_right = 5;

void setup()
{
Serial.begin(9600);
 pinMode(pin_left, OUTPUT);
// pinMode(pin_right, OUTPUT);
 digitalWrite(pin_left,LOW);
}

void loop() {

if (Serial.available()>0){
  Serial.println("helo");
  str = Serial.read();

    Serial.println("helo");


    if(str == '1'){
      digitalWrite(pin_left,HIGH);
      delay(500);
      digitalWrite(pin_left,LOW);
      delay(500);
      digitalWrite(pin_left,HIGH);
      delay(500);
      digitalWrite(pin_left,LOW);
      delay(500);
      digitalWrite(pin_left,HIGH);
      delay(500);
      digitalWrite(pin_left,LOW);
      delay(500);
      digitalWrite(pin_left,HIGH);
      delay(1500);
      digitalWrite(pin_left,LOW);
      delay(500);
      str = 0;
      }
    

    else if(str == '2'){
      digitalWrite(pin_left,HIGH);
      delay(500);
      digitalWrite(pin_left,LOW);
      delay(500);
      digitalWrite(pin_left,HIGH);
      delay(500);
      digitalWrite(pin_left,LOW);
      delay(500);
      digitalWrite(pin_left,HIGH);
      delay(500);
      digitalWrite(pin_left,LOW);
      delay(500);
      digitalWrite(pin_left,HIGH);
      delay(1500);
      digitalWrite(pin_left,LOW);
      delay(500);
      str = 0;
      }

    else if(str == '5'){
      digitalWrite(pin_left,HIGH);
      delay(500);
      digitalWrite(pin_left,LOW);
      delay(500);
      digitalWrite(pin_left,HIGH);
      delay(500);
      digitalWrite(pin_left,LOW);
      delay(500);
      digitalWrite(pin_left,HIGH);
      delay(500);
      digitalWrite(pin_left,LOW);
      delay(500);
      digitalWrite(pin_left,HIGH);
      delay(1500);
      digitalWrite(pin_left,LOW);
      delay(500); 
      Serial.println(str);
      str = 0;
      }
  
    }

else{
      delay(1000); 
      Serial.println("dead");
}
}
