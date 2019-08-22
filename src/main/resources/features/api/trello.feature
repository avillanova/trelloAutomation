#language: pt
#encoding: UTF-8
@api
Funcionalidade: Trello Api
  @api
  Cenario: Criando, comentando e deletando um card
    Dado que acesse a API com sucesso
    Quando crio um card com o nome "Alex Card API" usando a API
    E comento "Teste de API Alex" no card gerado
    Entao o card deve ser criado com sucesso
    Quando excluo usando a API
    Entao eu tenho o resultado com status "404"