#include <SPI.h>

// RFID Library
#include <MFRC522.h>

#include <WiFi.h>
#include <HTTPClient.h>

// ---------------------------------------------
// Pinos que se comunicam com o RFID
const int SDA_PIN = 21;
const int RESET_PIN = 22;

// Cria uma instancia do RFID
MFRC522 RFID(SDA_PIN, RESET_PIN);

// ---------------------------------------------
// Rede WiFi para se connectar
const char* SSID = "RODRIGUES 2.4";
const char* PASSWORD = "mp214317";

// Servidor para buscar as informações
const char* HOST = "192.168.137.74";
const int   PORT = 8093;

// Cliente para se connectar
WiFiClient client;

// ---------------------------------------------
void setup(){
  iniciarSPI();
  configSerial(9600);
  iniciarRFID();
  conectarWifi();
}

void iniciarSPI(){
  SPI.begin();
}

void configSerial(int frequencia){
  Serial.begin(frequencia);
}

void iniciarRFID(){
  RFID.PCD_Init();
}

// ---------------------------------------------
void loop(){

  if(!cartaoProntoParaLeitura()){
    delay(500); 
    return;
  }

  String cartao = lerCartao();

  Serial.println("\n---------------------------------------------");
  exibirCartao(cartao);

  HTTPClient http;

  const String url = "http://192.168.137.74:8093/micros/1/estagio/" + cartao;
  http.begin(url);

  int httpResponseCode = http.POST("POSTING from ESP32");
  if(httpResponseCode>0){
    String response = http.getString(); //Get the response to the request
    Serial.println(httpResponseCode);   //Print return code
    Serial.println(response);           //Print request answer
   }else{
    Serial.print("Error on sending POST: ");
    Serial.println(httpResponseCode);
   }

   http.end();  //Free resources
   delay(5000);

}

// Exibe as informacoes do Cartao
void exibirCartao(String cartao){
  Serial.println("CARTAO DETECTADO!");
  Serial.print("Cartao: ");
  Serial.println(cartao);
}

// ---------------------------------------------
// COMUNICAÇÃO COM O RFID
// Verifica se o cartao está pronto para ser lido
int cartaoProntoParaLeitura(){
  return cartaoPresente() && cartaoLido();
}

// Verifica se ha um cartao no RFID
int cartaoPresente(){
  return RFID.PICC_IsNewCardPresent();
}

// Tenta ler o cartao
int cartaoLido(){
  return RFID.PICC_ReadCardSerial();
}

// Pega o conteudo da leitura
String lerCartao(){
  String conteudo = "";
  for(byte i = 0; i < tamanhoConteudoCartao(); i++){
    conteudo.concat(String(RFID.uid.uidByte[i] < 0x10 ? "0" : ""));
    conteudo.concat(String(RFID.uid.uidByte[i], HEX));
  }
  return conteudo;
}

int tamanhoConteudoCartao(){
  return RFID.uid.size;
}

// ---------------------------------------------
// COMUNICAÇÃO COM O WIFI
// Verifica se esta conectado
bool estaConectado(){
  return WiFi.status() == WL_CONNECTED;
}

// Tenta conectar
void conectarWifi(){

  WiFi.begin(SSID, PASSWORD);
  Serial.println("\n---------------------------------------------");
  Serial.print("SSID : "); Serial.println(SSID);
  Serial.print("Senha: "); Serial.println(PASSWORD);

  while(!estaConectado()){
    Serial.println("Tentando conectar a rede...");
    delay(1500);
  }

  Serial.println("Conectado ao WiFi!");
  Serial.println("---------------------------------------------\n");
}