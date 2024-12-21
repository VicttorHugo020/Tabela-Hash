package main;
import java.util.LinkedList;
import java.util.Scanner;

class Usuario {
    String nome;
    String senha;

    Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
}

class TabelaHash {
    private LinkedList<Usuario>[] tabela;

    public TabelaHash(int capacidade) {
        tabela = new LinkedList[capacidade];
        for (int i = 0; i < capacidade; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int hash(String chave) {
        return Math.abs(chave.hashCode()) % tabela.length;
    }

    public void cadastrarUsuario(String nome, String senha) {
        int indice = hash(nome);
        for (Usuario usuario : tabela[indice]) {
            if (usuario.nome.equals(nome)) {
                System.out.println("Usuário já existe.");
                return;
            }
        }
        tabela[indice].add(new Usuario(nome, senha));
        System.out.println("Usuário cadastrado.");
    }

    public boolean autenticarUsuario(String nome, String senha) {
        int indice = hash(nome);
        for (Usuario usuario : tabela[indice]) {
            if (usuario.nome.equals(nome) && usuario.senha.equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public void removerUsuario(String nome) {
        int indice = hash(nome);
        for (Usuario usuario : tabela[indice]) {
            if (usuario.nome.equals(nome)) {
                tabela[indice].remove(usuario);
                System.out.println("Usuário removido.");
                return;
            }
        }
        System.out.println("Usuário não encontrado.");
    }

    public boolean verificarExistenciaUsuario(String nome) {
        int indice = hash(nome);
        for (Usuario usuario : tabela[indice]) {
            if (usuario.nome.equals(nome)) {
                return true;
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        TabelaHash tabela = new TabelaHash(10);
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nLayout do sistema");
            System.out.println("Escolha uma ação:");
            System.out.println("1. Inserção");
            System.out.println("2. Remoção");
            System.out.println("3. Busca");
            System.out.println("4. Login");
            System.out.println("5. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do usuário: ");
                    String nomeInsercao = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String senhaInsercao = scanner.nextLine();
                    tabela.cadastrarUsuario(nomeInsercao, senhaInsercao);
                    break;

                case 2:
                    System.out.print("Digite o nome do usuário para ser removido: ");
                    String nomeRemocao = scanner.nextLine();
                    tabela.removerUsuario(nomeRemocao);
                    break;

                case 3:
                    System.out.print("Digite o nome do usuário para busca: ");
                    String nomeBusca = scanner.nextLine();
                    if (tabela.verificarExistenciaUsuario(nomeBusca)) {
                        System.out.println("Usuário encontrado!");
                    } else {
                        System.out.println("Usuário não encontrado!");
                    }
                    break;

                case 4:
                    System.out.print("Digite o nome do usuário: ");
                    String nomeLogin = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String senhaLogin = scanner.nextLine();
                    if (tabela.autenticarUsuario(nomeLogin, senhaLogin)) {
                        System.out.println("Login realizado!");
                    } else {
                        System.out.println("Nome de usuário ou senha incorretos!");
                    }
                    break;
                    
                case 5:
                    System.out.println("Sistema finalizado!");
                    break;
                    
            }
        } while (opcao != 5);

        scanner.close();
    }
}
