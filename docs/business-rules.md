# Sistema de acompanhamento e gerenciamento de chamados

## Problema: o acompanhamento do fluxo de chamados proposto no Outlook dificulta a visualização e gerenciamento pelos técnicos de monitoramento.

## Solução proposta: desenvolver um sistema web que facilite o acompanhamento e gerenciamento de chamados realizados pelos técnicos de monitoramento.

## Funcionalidades propostas

### chamados

- permitir listar chamados realizados pelos técnicos em tabela.
- permitir cadastrar um novo chamado.
- permitir atualizar o status de um chamado.
- permitir atualizar os dados de um chamado.
- permitir pesquisar chamados cadastrados.
- (opcional) permitir paginar chamados cadastrados.
- (opcional) permitir ordernar as colunas de status de chamado e data de cadastro de chamado.

#### campos de chamados

- id chamado
- id Sefit do equipamento
- local/via do equipamento
- tipo equipamento
- status (Em andamento ou Finalizado)
- ocorrência
- data início
- hora início
- data fim
- hora fim
- observações
- aberto por (técnico que abriu o chamado)
- finalizado por (técnico que finalizou o chamado)

### equipamentos

- permitir cadastrar equipamento (tipo WIM ou tipo OCR).
- permitir listar equipamentos cadastrados.
- permitir editar equipamentos cadastrados.
- permitir excluir equipamentos cadastrados.
- permitir pesquisar equipamentos cadastrados pelo ID Sefit ou Local/Via.
- (opcional) permitir paginar equipamentos cadastrados.
- (opcional) permitir ordernar as colunas de tipo de equipamento e número de série de equipamento.

#### campos de equipamentos

- local/via
- id Sefit
- tipo WIM/OCR
- número de série

### técnicos

- o sistema deverá ter 4 técnicos cadastrados por padrão, onde cada técnico poderá editar as informações de cada um, com exceção da senha.
- cada técnico poderá cadastrar, editar e acompanhar a listagem de chamados cadastrados.
- cada técnico poderá cadastrar, editar, excluir e acompanhar a listagem de equipamentos cadastrados.

#### campos de técnicos

- nome
- e-mail
- senha
- turno

### autenticação e permissão de acesso

- para acessar o sistema, o usuário deverá estar autenticado.
- para autenticação, será necessário informar e-mail institucional do técnico e senha de acesso.
