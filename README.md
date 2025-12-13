# **SAPO - Sistema de Agendamento e Prevenção Odontológica**

![Java](https://img.shields.io/badge/Java-24-blue)
![Swing](https://img.shields.io/badge/Swing-UI_Designer-orange)
![SQLite](https://img.shields.io/badge/SQLite-Database-green)
![Status](https://img.shields.io/badge/Status-Completo-green)

Sistema completo para gestão de clínicas odontológicas de pequeno porte desenvolvido em Java Swing com interface moderna e funcionalidades completas para administração de pacientes, agendamentos e consultas.

## **Sumário**

- [Funcionalidades](#funcionalidades)
- [Arquitetura](#arquitetura)
- [Interface Gráfica](#interface-gráfica)
- [Instalação](#instalação)
- [Como Usar](#como-usar)
- [Estrutura do Projeto](#estrutura-do-projeto)

##  **Funcionalidades**

###  **Gestão de Clientes**
- ✅ **Cadastro completo** com validação de CPF e outros dados
- ✅ **Listagem inteligente** com busca em tempo real
- ✅ **Edição em modal** com dados pré-preenchidos
- ✅ **Visualização detalhada** com histórico
- ✅ **Remoção segura** com confirmação
- ✅ **Máscaras automáticas** (CPF, telefone, data)

###  **Agendamentos**
- ✅ **Cadastro de consultas** com validação de data/hora
- ✅ **Visualização** de agendamentos atuais e futuros
- ✅ **Controle de status** (pendente, realizado, cancelado)
- ✅ **Associação** cliente-dentista-procedimento

###  **Validações Avançadas**
- ✅ **CPF** - Algoritmo oficial de validação
- ✅ **Telefone** - Formatação automática brasileira
- ✅ **Datas** - Validação de formato e lógica temporal
- ✅ **Campos obrigatórios** - Verificação em tempo real

##  **Arquitetura**

### **Padrão CardLayout**
```
MainScreen (Container Principal)
├── Clientes (Listagem dos pacientes)
├── Cadastro (Formulário de cadastro)
├── Informações do Cliente (Detalhes)
├── Agendamentos (Listagem de consultas)
└── Menu Principal (Imagem de boas-vindas)
```

### **Separação por Responsabilidade**
- `ui/` - Interface gráfica (forms Swing)
- `api/` - Lógica de getters e setters dos pacientes e agendamentos e banco de dados
- `Main.java` - Ponto de entrada da aplicação

##  **Interface Gráfica**

### **Design Moderno**
- ✅ **Cards interativos** para lista de clientes
- ✅ **Placeholders inteligentes** nos formulários
- ✅ **Botões com feedback visual** (hover, click)
- ✅ **Navegação lateral** com indicador ativo
- ✅ **Modais responsivos** para ações críticas

### **Experiência do Usuário**
- ✅ **Atualização em tempo real** após edições
- ✅ **Mensagens informativas** de sucesso/erro
- ✅ **Validação inline** durante digitação
- ✅ **Foco automático** nos campos de formulário

##  **Instalação**

### **Pré-requisitos/Tecnologias**
- Java JDK 17 ou superior
- IntelliJ IDEA (recomendado) ou Eclipse
- SQLite JDBC Driver
- FlatLaf
- Swing UI Designer (para visualizar arquivos .form)

### **Passo a Passo**

#### **SQLite JDBC**
```bash
# 1. Baixe o arquivo .jar em: https://github.com/xerial/sqlite-jdbc/releases/download/3.50.3.0/sqlite-jdbc-3.50.3.0.jar

# 2. Ir em settings -> Build, Execution, Deployment -> Build Tools -> Maven -> Archetype Catalogs
# 3. Clicar em adicionar (sinal de +)
# 4. Adicionar o arquivo .jar do sqlite-jdbc que você baixou
# 5. Clicar em Add e Apply
```

#### **FlatLaf**
```bash
# 1. Ir em Project Structure -> Libraries -> New Project Library -> From Maven
# 2. Copiar e colar esse código: 
<dependency>
    <groupId>com.formdev</groupId>
    <artifactId>flatlaf</artifactId>
    <version>3.7</version>
</dependency>
# 3. Clicar em OK e Apply
```

#### **Executar o SAPO**
```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/sapo.git

# 2. Abra no IntelliJ IDEA
# 3. Configure o JDK 17
# 4. Execute o Main.java
```

### **Configuração do Banco de Dados**
O sistema usa SQLite com banco de dados embutido:
```
src/api/mydb.db
```

##  **Como Usar**

### **Cadastrando um Cliente**
1. Acesse **Clientes** → **Novo Cliente**
2. Preencha os dados com CPF válido
3. Observe as máscaras aplicando automaticamente
4. Clique em **Cadastrar** para salvar

### **Editando/Removendo um Cliente**
1. Clique em qualquer cliente na lista
2. Na tela de informações, clique em **Editar**
3. Faça as alterações necessárias
4. Clique em **Salvar Alteração** ou **Remover Cliente**

### **Agendando uma Consulta**
1. Acesse **Agendamentos** → **Novo Agendamento**
2. Selecione cliente, dentista e procedimento
3. Defina data e horário
4. Adicione observações se necessário

##  **Estrutura do Projeto**

```
SAPO/
├── src/
│   ├── Main.java
│   ├── api/
│   │   ├── Client.java
│   │   ├── Schedule.java
│   │   ├── DataBase.java
│   │   ├── DataBaseAgendamentos.java
│   │   └── mydb.db
│   └── ui/
│       ├── MainScreen.java (.form)
│       ├── Clientes.java (.form)
│       ├── register.java (.form)
│       ├── customerInformation.java (.form)
│       ├── editClient.java (.form)
│       ├── editStatusScheduling.java (.form)
│       ├── scheduling.java (.form)
│       └── registerAppointment.java (.form)
├── README.md
└── .gitignore
```

---

**Desenvolvido por equipe SAPO**
