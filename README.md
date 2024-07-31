# Projeto Avaliação
- Este projeto gerencia as informações de beneficários e seus respectivos documentos.

## Tecnologias e ferramentas utilizadas

- Java 17
- Spring Boot 3.0
- Spring Security
- Spring Data JPA
- OpenAPI 2.3
- Banco de Dados H2
- JSON Web Token

## Intruções para executar o build

### MacOS
- Se ainda não tiver o Maven instalado, instale-o usando Homebrew:
  - brew install maven
    <br>
    <br>
- Abra o diretório do projeto onde o arquivo pom.xml está localizado e execute:
  - mvn clean install
    <br>
    <br>
- Execute o seguinte comando para rodar o projeto e mantê-lo em segundo plano:
  - nohup java -jar target/demo-0.0.1-SNAPSHOT.jar &

### Windows
- Se ainda não tiver o Maven instalado, faça o download  em: https://maven.apache.org/download.cgi e siga as instruções de instalação.
    <br>
    <br>
- Adicione o diretório bin do Maven ao PATH nas variáveis de ambiente do Windows.
  <br>
  <br>
- Abra o diretório do projeto onde o arquivo pom.xml está localizado e execute:
  - mvn clean install
    <br>
    <br>
- Execute o seguinte comando para rodar o projeto e mantê-lo em segundo plano:
  - start java -jar target/demo-0.0.1-SNAPSHOT.jar


## Resumo

- Após buildar o projeto, a documentação dos endpoints pode ser acessada no seguinte endereço: http://localhost:8080/swagger-ui/index.html#/
  <br>
  <br>
- Os endpoints estão autenticados com JWT. Para gerar o token, execute o seguinte curl:
  <br>
  <br>
  curl --location 'http://localhost:8080/login' \
  --header 'Content-Type: application/json' \
  --header 'Cookie: JSESSIONID=2653BE3DCEE7DD796108DD99E847CC66' \
  --data '{
  "username": "mauricio",
  "password": "12345678"
  }'
  <br>
  <br>
- A aplicação cria e armazenas as informações no banco de dados incorporado H2.
  <br>
  <br>
- Foram criadas 2 tabelas, uma para armazenar os beneficiários e outra os documentos. Um beneficiário pode ter 1 ou mais documentos (1:N)
  <br>
  <br>
- Foram criados endpoints para fazer o CRUD tanto de beneficiários, como de documentos.
  <br>
  <br>
- Ao incluir um novo documento, e associá-lo a um beneficiário pelo ID, é verificado a existência de um beneficiário com este ID, antes de associá-lo de fato.
  <br>
  <br>
- Ao deletar um beneficiário, todos os documentos associados a ele também são automaticamente deletados.


