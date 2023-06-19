# desafio

database: fourd <br />

fazer o insert do usuário admin no banco: <br />

db.getSiblingDB("fourd").getCollection("user").insert({
    login: "admin",
    password: "$2a$10$fkGrnu66fhdOl.TIMTnRJOCfYX3bzHJX6i7dEITbY.nkHZdfNlfPm",
    status: "APROVADO"
});

login:admin senha: admin

com o usuário admin, você pode criar aulas e professores.

rota para registro: POST /auth/register
 - qualquer usuário pode acessar essa rota
 - objeto de envio exemplo: { "login": "tobias", "password": "1234" }

rota para authenticação: POST /auth/authenticate
 - somente usuários com o status APROVADO podem se autenticar.
 - objeto de envio exemplo: { "login": "tobias", "password": "1234" }

rota para ativar usuario/professor: POST /auth/active
 - somente usuário autenticados podem ativar outro usuário.
 - informar o id de um professor.
 - objeto de envio exemplo: { "login": "tobias", "idProfessor": "123456789" }

rota para cadastrar um professor: POST /professor/create
  - somente usuarios autenticados
  - objeto de envio exemplo: { "nome": "tobias" }

rota para cadastrar aula: POST /aula/create
  - somente usuarios autenticados
  - objeto de envio exemplo: <br />
  {<br />
	  "titulo": "Aula 01",<br />
	  "descricao": "Descricao aula 01",<br />
	  "dataPrevista": "2023-06-17T04:00:00.000+0000",<br />
	  "idProfessor": "123456789"<br />
  }<br />
  
rota para listar aulas específicas do professor: GET /professor/aulas
  - somente usuarios autenticados
