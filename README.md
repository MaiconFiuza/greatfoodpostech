# greatfoodpostech
Post Tech Fiap - Arquitetura e Desenvolvimento em JAVA -Tech Challenger Fase 2

## FIAP - Tech Challenge - Fase 2


### Sistema de gestão para seus estabelecimentos

Nessa segunda fase de entrega o objetivo é desenvolver um backend robusto utilizando Spring Boot para gerenciar usuários e atender aos requisitos definidos.

### Link para a documentação das API's do projeto (OpenAPI):
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## Para executar a aplicação:

### 1. Fazer o build dos containeres da aplicação:
Executar o seguinte comando:
    
    docker-compose up --build

O comando acima gerará os conteineres de aplicação e banco de dados.

### 2. Executar a aplicação através dos containeres criados:
Executar o seguinte comando para inicializar os containeres da aplicação, na raíz do projeto (onde se encontra o arquivo docker-compose.yml):

    docker-compose up

### 3. Acessar a aplicação
A aplicação estará disponível na seguinte URL:

    http://localhost:8080/

As collections do postman se encontram no seguinte link: [Collection Postman GREAT FOOD]([https://github.com/MaiconFiuza/Great-Food-/blob/main/Projeto%20M%C3%B3dulo%201.postman_collection.json](https://github.com/MaiconFiuza/greatfoodpostech/blob/main/Projeto%20M%C3%B3dulo%201%20Copy.postman_collection.json))

### Cobertura de testes
Para cobertura de testes foi utilizado a ferramenta [Jacoco](https://www.eclemma.org/jacoco/) atingindo o percentual total de 81%.
Para rodar os testes unitários, na raiz do projeto, execute o seguinte comando: 

> mvn test

O relatório de cobertura pode ser encontrado dentro da pasta `./target`. Para acessar o relatório web acesse:

> taget/site/jacoco/index.html

### Vídeo de apresentação do projeto

O Vídeo de apresentação se encontra no seguinte [link](https://www.youtube.com/watch?v=mtklA93RSFQ)
