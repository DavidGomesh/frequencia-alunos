
#include <SPI.h>

// RFID Library
#include <MFRC522.h>

// WiFi Library
#include <WiFi.h>

// ---------------------------------------------
// Constrola o LED do ESP32
const int LED_BUILTIN = 2;
bool led_builtin_ligado = false;

// ---------------------------------------------
// Pinos que se comunicam com o RFID
const int SDA_PIN = 21;
const int RESET_PIN = 22;

// Cria uma instancia do RFID
MFRC522 RFID(SDA_PIN, RESET_PIN);

// ---------------------------------------------
// Rede WiFi para se connectar
const char* SSID = "Hello World";
const char* PASSWORD = "1234567889";

// Servidor para buscar as informações
const char* HOST = "192.168.137.1";
const int   PORT = 8080;

// Cliente para se connectar
WiFiClient client;

// ---------------------------------------------
void setup(){
  iniciarSPI();
  configSerial(9600);
  definirPinosSaida();

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

void definirPinosSaida(){
  pinMode(LED_BUILTIN, OUTPUT);
}

// ---------------------------------------------
void loop(){

  if(!estaConectado()){
    conectarWifi();
  }

  if(!cartaoProntoParaLeitura()){
    toggleLedBuiltin();
    delay(500);
    return;
  }

  String cartao = lerCartao();


  Serial.println("\n---------------------------------------------");
  exibirCartao(cartao);

  if(cartaoCadastrado(cartao)){
    Serial.println("Cartao cadastrado!");
    piscarRapidamente();
  }else{
    Serial.println("Cartao desconhecido!");
    piscarLentamente();
  }
  Serial.println("---------------------------------------------\n");
}

// Exibe as informacoes do Cartao
void exibirCartao(String cartao){
  Serial.println("CARTAO DETECTADO!");
  Serial.print("Cartao: ");
  Serial.println(cartao);
}

// ---------------------------------------------
// INTERAÇÕES COM O LED_BUILTIN
void piscarRapidamente(){
  for(int i=0; i<12; i++){
    toggleLedBuiltin();
    delay(50);
  }
}

void piscarLentamente(){
  for(int i=0; i<6; i++){
    toggleLedBuiltin();
    delay(200);
  }
}

// Se o LED_BUILTIN estiver desligado, liga o LED
// Caso contrario, desliga
void toggleLedBuiltin(){
  const int ACAO = led_builtin_ligado ? LOW : HIGH;
  digitalWrite(LED_BUILTIN, ACAO);
  led_builtin_ligado = !led_builtin_ligado;
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
    toggleLedBuiltin();
    delay(1500);
  }

  piscarRapidamente();
  Serial.println("Conectado ao WiFi!");
  Serial.println("---------------------------------------------\n");
}

// ---------------------------------------------
// COMUNICACAO COM SERVIDOR
// Verifica se o cartao esta no cadastrado no Banco
bool cartaoCadastrado(String cartao){
  conectarAoServidor();
  buscarNoBanco(cartao);

  bool resultado = verificarCartao();
  fecharConexao();

  return resultado;
}

// Tenta se conectar no Servidor
void conectarAoServidor(){

  digitalWrite(LED_BUILTIN, LOW);
  while(!client.connected()){
    client.connect(HOST, PORT);
    delay(1500);
    toggleLedBuiltin();
  }
  
}

// Faz uma requisicao ao Servidor
void buscarNoBanco(String cartao){
  String URL = "GET /leitor-cartao?codigo=" + cartao + " HTTP/1.0\r\n\r\n";
  client.print(URL);
}

// Verifica a resposta do Servidor
bool verificarCartao(){
  String resposta = lerResposta();
  return resposta == "HTTP/1.1 200 ";
}

// Le a resposta do Servidor
String lerResposta(){
  String resposta = "";
  while(client.available() == 0){
    resposta.concat(client.readStringUntil('\r'));
  }

  return resposta;
}

// Fecha a conexao com o Servidor
void fecharConexao(){
  client.stop();
}
