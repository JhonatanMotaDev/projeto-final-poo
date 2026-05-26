# Sistema de Delivery - Programação Orientada a Objetos

Sistema completo de delivery desenvolvido em Java com interface gráfica Swing, demonstrando todos os conceitos de Programação Orientada a Objetos.

## Descrição

Aplicação desktop que simula um sistema de delivery (similar ao iFood/Uber Eats) com funcionalidades completas de cadastro, gerenciamento de pedidos e acompanhamento de entregas.

## Objetivos Acadêmicos

Este projeto foi desenvolvido para demonstrar os seguintes conceitos de POO:

### Conceitos Implementados

1. **Classes e Objetos**
   - Customer, DeliveryDriver, Administrator, Restaurant, Product, Order, Payment

2. **Encapsulamento**
   - Todos os atributos são privados
   - Acesso via getters e setters

3. **Herança**
   - User (abstrata) -> Customer, DeliveryDriver, Administrator

4. **Polimorfismo**
   - Método showData() implementado de forma diferente em cada subclasse
   - Método login() sobrescrito

5. **Classe Abstrata**
   - User com métodos abstratos showData() e login()

6. **Interface**
   - Deliverable implementada por Order
   - Métodos: startDelivery(), finishDelivery(), updateStatus()

7. **Enum**
   - OrderStatus: PENDING, PREPARING, OUT_FOR_DELIVERY, DELIVERED, CANCELED
   - PaymentType: CREDIT_CARD, DEBIT_CARD, CASH, PIX

8. **Associação entre Classes**
   - Order -> Customer, Restaurant, DeliveryDriver, Products, Payment
   - Restaurant -> Products

9. **Construtores**
   - Todas as classes possuem construtores parametrizados

10. **ArrayList**
    - DataService gerencia dados usando ArrayList

11. **Padrão MVC**
    - Model: entidades de negócio
    - View: telas Swing
    - Controller: lógica de negócio

## Estrutura do Projeto

```
src/
├── app/
│   └── Main.java                    # Classe principal executável
├── model/
│   ├── User.java                    # Classe abstrata base
│   ├── Customer.java                # Herda de User
│   ├── DeliveryDriver.java          # Herda de User
│   ├── Administrator.java           # Herda de User
│   ├── Restaurant.java              # Entidade restaurante
│   ├── Product.java                 # Entidade produto
│   ├── Order.java                   # Implementa Deliverable
│   └── Payment.java                 # Entidade pagamento
├── view/
│   ├── MainFrame.java               # Tela principal
│   ├── CustomerFrame.java           # Gerenciar clientes
│   ├── DriverFrame.java             # Gerenciar entregadores
│   ├── RestaurantFrame.java         # Gerenciar restaurantes
│   ├── OrderFrame.java              # Criar pedidos
│   ├── DeliveryFrame.java           # Gerenciar entregas
│   └── ReportFrame.java             # Relatórios
├── controller/
│   ├── CustomerController.java      # Lógica de clientes
│   ├── DriverController.java        # Lógica de entregadores
│   ├── RestaurantController.java    # Lógica de restaurantes
│   └── OrderController.java         # Lógica de pedidos
├── service/
│   └── DataService.java             # Gerenciamento de dados (Singleton)
├── interfaces/
│   └── Deliverable.java             # Interface para entregas
└── enums/
    ├── OrderStatus.java             # Status do pedido
    └── PaymentType.java             # Tipos de pagamento
```

## Como Executar

### Pré-requisitos
- Java JDK 17 ou superior
- IntelliJ IDEA (recomendado)

### Passos

1. Clone ou baixe o projeto

2. Abra no IntelliJ IDEA
   - File -> Open -> Selecione a pasta delivery-poo

3. Configure o JDK
   - File -> Project Structure -> Project
   - Selecione JDK 17+

4. Execute a aplicação
   - Navegue até src/app/Main.java
   - Clique com botão direito -> Run 'Main.main()'

### Alternativa: Script Batch (Windows)
```
Duplo clique em run.bat
```

### Alternativa: Linha de Comando
```cmd
cd c:\delivery-poo
javac -d bin -sourcepath src src\app\Main.java src\model\*.java src\view\*.java src\controller\*.java src\service\*.java src\interfaces\*.java src\enums\*.java
java -cp bin app.Main
```

## Funcionalidades

### 1. Gerenciamento de Clientes
- Cadastrar novos clientes
- Listar todos os clientes
- Visualizar dados completos

### 2. Gerenciamento de Entregadores
- Cadastrar entregadores
- Informar tipo de veículo e placa
- Controlar disponibilidade

### 3. Gerenciamento de Restaurantes
- Cadastrar restaurantes
- Adicionar produtos ao restaurante
- Definir preços e categorias

### 4. Criação de Pedidos
- Selecionar cliente
- Escolher restaurante
- Adicionar múltiplos produtos
- Atribuir entregador
- Calcular total automaticamente
- Processar pagamento

### 5. Gerenciamento de Entregas
- Visualizar todos os pedidos
- Atualizar status da entrega
- Acompanhar entregadores

### 6. Relatórios
- Relatório de clientes
- Relatório de entregadores
- Relatório de restaurantes
- Relatório de pedidos
- Estatísticas gerais

## Interface Gráfica

### Componentes Swing Utilizados
- JFrame - Janelas principais
- JPanel - Organização de layout
- JTable - Exibição de dados tabulares
- JButton - Botões de ação
- JTextField - Entrada de texto
- JComboBox - Seleção de opções
- JList - Lista de itens
- JOptionPane - Diálogos e mensagens
- JTabbedPane - Abas
- JScrollPane - Rolagem

## Fluxo da Aplicação

```
1. Iniciar Sistema
   |
2. Cadastrar Clientes
   |
3. Cadastrar Entregadores
   |
4. Cadastrar Restaurantes
   |
5. Adicionar Produtos aos Restaurantes
   |
6. Criar Pedido
   - Selecionar Cliente
   - Selecionar Restaurante
   - Adicionar Produtos
   - Atribuir Entregador
   - Processar Pagamento
   |
7. Gerenciar Entrega
   - Atualizar Status
   - PENDING -> PREPARING -> OUT_FOR_DELIVERY -> DELIVERED
   |
8. Visualizar Relatórios
```

## Diagrama UML Simplificado

```
                    User (abstract)
                         |
        +----------------+----------------+
        |                |                |
    Customer      DeliveryDriver    Administrator


    Deliverable (interface)
            |
            | implements
            v
         Order


Order ----------> Customer
  |
  +------------> Restaurant
  |
  +------------> DeliveryDriver
  |
  +------------> Payment
  |
  +------------> Product (1..*)


Restaurant -----> Product (1..*)
```

## Conceitos de POO Detalhados

### Herança
```java
// Classe abstrata
public abstract class User {
    private int id;
    private String name;
    public abstract String showData();
}

// Herança
public class Customer extends User {
    private String email;
    
    @Override
    public String showData() {
        return "Cliente: " + getName();
    }
}
```

### Polimorfismo
```java
User user1 = new Customer(...);
User user2 = new DeliveryDriver(...);

// Mesmo método, comportamentos diferentes
user1.showData(); // Retorna dados de cliente
user2.showData(); // Retorna dados de entregador
```

### Interface
```java
public interface Deliverable {
    void startDelivery();
    void finishDelivery();
}

public class Order implements Deliverable {
    @Override
    public void startDelivery() {
        this.status = OrderStatus.OUT_FOR_DELIVERY;
    }
}
```

### Associação
```java
public class Order {
    private Customer customer;      // Order tem um Customer
    private Restaurant restaurant;  // Order tem um Restaurant
    private List<Product> products; // Order tem vários Products
}
```

## Expansões Futuras

O projeto está preparado para:

1. **Banco de Dados MySQL**
   - Substituir DataService por DAO com JDBC
   - Persistência real dos dados

2. **API REST**
   - Criar endpoints com Spring Boot
   - Integração com aplicativo mobile

3. **Autenticação**
   - Sistema de login real
   - Controle de permissões

4. **Persistência em Arquivos**
   - Serialização de objetos
   - Leitura/escrita em JSON

5. **Dashboard Administrativo**
   - Gráficos de vendas
   - Análise de desempenho

## Tecnologias

- Java 17+
- Java Swing (GUI)
- ArrayList (Estrutura de dados)
- Padrão MVC
- Padrão Singleton

## Observações

- Dados são armazenados em memória (ArrayList)
- Ao fechar a aplicação, os dados são perdidos
- Para persistência, implementar banco de dados ou arquivos

## Conceitos Acadêmicos Atendidos

- Classes e Objetos
- Encapsulamento
- Construtores
- Herança
- Polimorfismo
- Classe Abstrata
- Interface
- Sobrescrita de Métodos
- Associação entre Classes
- Entrada, Processamento e Saída de Dados
- ArrayList
- Enum
- MVC Pattern

## Licença

Projeto acadêmico para fins educacionais.
