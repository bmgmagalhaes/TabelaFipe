# 📋 Checklist de Configuração do BB SIA (Baixa Automática dos Retornos)

## 1. Verificações iniciais no *Studio* do Caché
- [ ] Verificar se a pasta/arquivos já existem dentro do servidor.  
- [ ] Caso não estejam mapeados, comunicar-se com Daniel.  

## 2. Mapeamento de pacotes
- [ ] Verificar se existe a pasta da classe **Baixa.RealTime**.  
- [ ] Se não existir, mapear conforme instruções:  
  - Menu → Configurar Namespaces  
  - Pesquisa Namespace → Mapeamentos de Pacote → Novo  
  - Local do banco de dados do pacote: **ARRECADACAO**  
  - Nome do pacote: **Baixa.RealTime**  

- [ ] Verificar se existe **Arrecada.API**.  
- [ ] Se não existir, mapear conforme instruções:  
  - Menu → Configurar Namespaces  
  - Pesquisa Namespace → Mapeamentos de Pacote → Novo  
  - Local do banco de dados do pacote: **ARRECADACAO**  
  - Nome do pacote: **Arrecada.API**  

## 3. Estrutura de pastas
- [ ] Acessar a pasta `D:\Prefeituras` e selecionar a sigla da prefeitura.  
- [ ] Confirmar existência das pastas **ARRECADA** e **BaixaTemp**.  
- [ ] Localizar o arquivo `BB_Config.json` em **BaixaTemp**.  
- [ ] Configurar login e senha do BB SIA.  
- [ ] Copiar conteúdo de outra prefeitura já configurada, mantendo padrão.  
- [ ] Limpar arquivos existentes dentro das pastas.  
- [ ] Acessar a pasta `H:\Arqs` e selecionar a sigla da prefeitura.  
- [ ] Verificar se existe a pasta **arquivoretorno**.  
- [ ] Se não existir, criar a pasta.  

## 4. Agendamento da tarefa
- [ ] Nome: **Baixa Arquivo de Retorno**  
- [ ] Descrição: **Faz o download do arquivo de retorno do BB**  
- [ ] Espaço de nome: **NAMESPACE**  
- [ ] Tipo de tarefa: **RunLegacyTask**  
- [ ] ExecuteCode:  
  ```
  do ##class(Baixa.RealTime.Util).getArquivoRetorno()
  ```
- [ ] Prioridade da tarefa: **Normal**  
- [ ] Executar tarefa como usuário **Admin**  
- [ ] Abrir arquivo de saída durante execução: **Não**  
- [ ] Suspender tarefa em caso de erro: **Não**  
- [ ] Reagendar tarefa após reinício do sistema: **Sim**  
- [ ] Frequência: **Diariamente**  
- [ ] Intervalo: **1 dia**  
- [ ] Horário de execução: **05:10:00**  

## 5. Teste de funcionamento
- [ ] Executar *debug* no método para garantir funcionamento.  
- [ ] Confirmar que os arquivos são baixados e enviados para **arquivoretorno**.  
- [ ] Para o debug, agendar a tarefa e executar manualmente para teste.  
