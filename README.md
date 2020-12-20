# Super Trivia
## Aplicativo para resolução de quizzes (jogo de perguntas e respostas).

> Uma API foi integralmente desenvolvida para tal: 
https://super-trivia-server.herokuapp.com

![](app/src/main/res/drawable-v24/logo.png)

No primeiro acesso, o usuário vê uma tela de login (E-mail, Senha) e uma opção para cadastrar-se (Nome, E-mail, Senha e Confirmar Senha).

Obs: Uma vez autenticado, das próximas vezes que for usar o aplicativo, o usuário não precisa fazer o login.

Ao autenticar-se, o usuário pode começar a jogar, mas antes escolhe uma dificuldade (difícil, médio ou fácil) e uma categoria. Ele tem a opção de não escolher, desta forma a API irá aleatorizar as perguntas.

Utilizando RecyclerView para categorias e Ranking que são carregadas da API.

O aplicativo apresenta uma pergunta por vez de acordo com as seleções que o usuário fez no início. Cada questão é apresentada a ele com uma pergunta e suas quatro respostas.

Obs: a API já se responsabiliza por gerar perguntas aleatórios, com suas opções de respostas embaralhadas.

Ao selecionar uma das respostas, o aplicativo apresenta ao usuário,  se houve erro ou acerto, bem como a suas pontuação atualizada.

Após cada pergunta, o jogo solicita ao usuário se ele deseja uma próxima pergunta ou se ele deseja encerrar a partida.

Ao selecionar a segunda opção, uma tela de resumo da partida do usuário é apresentada.

Obs: Nem todas estas informações são fornecidas pelo servidor. Por isso uma classe Controller foi criada.

A qualquer momento o usuário pode acessar a tela de configurações mostrada no início da partida para alterar a dificuldade e a categoria das próximas perguntas (utilizando bottom navigation).

A qualquer momento o usuário pode acessar o ranking, que mostra as 20 melhores pontuações da história do jogo dele e dos demais usuários (utilizando bottom navigation).

Observações
O jogo funciona apenas quando houver conexão com a internet.
Utilizados neste projeto:  Fragments, Bottom Navigation e Navigation Framework.


> Explicação da API

Aplicação executando em https://super-trivia-server.herokuapp.com

Código-fonte: https://github.com/seccomiro/trivia-server



### About me
[![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white&link=https://github.com/fagnerpsantos)](https://github.com/Trallerd)
[![Twitter Badge](https://img.shields.io/badge/-Twitter-1ca0f1?style=flat-square&labelColor=1ca0f1&logo=twitter&logoColor=white&link=https://twitter.com/trallerd)](https://twitter.com/trallerd)
[![Youtube Badge](https://img.shields.io/badge/-YouTube-ff0000?style=flat-square&labelColor=ff0000&logo=youtube&logoColor=white&link=https://www.youtube.com/channel/UCHmlPQF6AVr3y7fj7TE-7Hw)](https://www.youtube.com/channel/UCHmlPQF6AVr3y7fj7TE-7Hw)

