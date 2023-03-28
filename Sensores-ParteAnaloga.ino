// C++ code
//
int mouisture_data = 0;

int tempSensor_data = 0;

int i = 0;

void setup()
{
  pinMode(A0, INPUT);
  Serial.begin(9600);
  pinMode(A2, INPUT);
  pinMode(12, OUTPUT);
  pinMode(8, OUTPUT);
}

void loop()
{
  mouisture_data = analogRead(A0);
  Serial.println(mouisture_data);
  tempSensor_data = analogRead(A2);
  Serial.println(tempSensor_data);
  if (mouisture_data < 153) {
    digitalWrite(12, HIGH);
  } else {
    digitalWrite(12, LOW);
  }
  if (tempSensor_data > 160) {
    tone(8, 29, 2000); // play tone 10 (A#0 = 29 Hz)
  } else {
    noTone(8);
  }
  delay(10); // Delay a little bit to improve simulation performance
}