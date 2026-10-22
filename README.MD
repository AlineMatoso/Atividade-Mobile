# WorkSpot - App de Reserva de Salas e Mesas

Integrantes:
- Aline Matoso
- Helena Bury Santos
- Pedro Henrique dos Santos da Silva

---

## Sobre o Aplicativo

O WorkSpot é um aplicativo Android desenvolvido em Kotlin para gestão e reserva de espaços de trabalho, salas de reuniões e mesas. O sistema permite que colaboradores e estudantes encontrem, organizem e garantam o seu lugar de forma prática e rápida.

Funcionalidades Principais

- Autenticação e Acesso: Tela de login para acesso ao aplicativo.
- Visualização de Espaços: Consulta de salas disponíveis com detalhes de capacidade via reservas ja realizadas e tipo de ambiente (ex: Escritório Aberto, Equipe de Projetos, Sala de Reuniões).
- Reserva Prática: Preenchimento automático da sala selecionada, exigindo apenas o nome do responsável, data e observações.
- Listagem Dinâmica: Exibição em tempo real de todas as reservas ativas utilizando `RecyclerView`.
- Detalhes da Reserva: Consulta detalhada dos dados da reserva realizada.
- Edição e Cancelamento: Permite alterar dados da reserva ou cancelá-la a qualquer momento.
- Sistema de Favoritos: Recurso para favoritar/desfavoritar reservas para acesso rápido.
- Persistência Local de Dados: Armazenamento offline utilizando banco de dados local SQLite (`BancoHelper`).
