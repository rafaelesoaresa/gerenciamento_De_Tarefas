package main;

import java.util.Date;
import java.util.Scanner;
import controller.TarefaController;
import model.Prioridade;
import model.Estado;

public class Main {
    public static void main(String[] args) {
        String titulo, descricao;
        Date dataCriacao, dataVencimento;
        Prioridade prioridade;
        Estado estado;
        Integer idTarefa;

        TarefaController tarefaController = new TarefaController();
        Scanner scanner = new Scanner(System.in);

        int opcao = -1;

        while (opcao != 0) {

            // Menu da nossa aplicação
            System.out.println("===*Aplicativo de Gerenciamento de Tarefas*===");
            System.out.println("==============================================");
            System.out.println("= 1.        Adicionar uma nova tarefa        =");
            System.out.println("= 2.        Atualizar uma tarefa existente   =");
            System.out.println("= 3.        Consultar tarefas por descrição  =");
            System.out.println("= 4.        Listar todas as tarefas          =");
            System.out.println("= 5.        Remover uma tarefa               =");
            System.out.println("= 6.        Sair do programa                 =");
            System.out.print("============= Escolha uma opção: =============");
            try {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o título da tarefa: ");
                        scanner.nextLine();
                        titulo = scanner.nextLine();

                        System.out.print("Digite a descrição da tarefa: ");
                        descricao = scanner.nextLine();

                        dataCriacao = new Date();

                        System.out.println("Data de criação: " + dataCriacao);

                        System.out.print("Digite a data de vencimento (yyyy-MM-dd): ");
                        dataVencimento = java.sql.Date.valueOf(scanner.nextLine());

                        System.out.print("Digite a prioridade (ALTA, MEDIA, BAIXA): ");
                        prioridade = Prioridade.valueOf(scanner.nextLine());

                        System.out.print("Digite o estado (ABERTA, EM_PROGRESSO, CONCLUÍDA): ");
                        estado = Estado.valueOf(scanner.nextLine());

                        tarefaController.adicionarTarefa(titulo, descricao, dataCriacao, dataVencimento, prioridade, estado);
                        System.out.print("Tarefa adicionada com sucesso! ");
                        break;

                    case 2:
                        tarefaController.listarTarefas();
                        break;

                    case 3:
                        System.out.print("Digite o título da tarefa que deseja buscar: ");
                        scanner.nextLine();
                        String tituloBusca = scanner.nextLine();

                        tarefaController.buscaTarefaPorTitulo(tituloBusca);
                        break;

                    case 4:
                        System.out.println("Digite o ID da tarefa a ser removida: ");
                        idTarefa = scanner.nextInt();
                        tarefaController.removerTarefa(idTarefa);
                        break;

                    case 5:
                        System.out.println("Digite o ID da tarefa que deseja atualizar: ");
                        idTarefa = scanner.nextInt();

                        System.out.print("Digite o novo título da tarefa: ");
                        scanner.nextLine();
                        titulo = scanner.nextLine();

                        System.out.print("Digite a nova descrição da tarefa: ");
                        descricao = scanner.nextLine();

                        System.out.print("Digite a nova data de vencimento (yyyy-MM-dd): ");
                        dataVencimento = java.sql.Date.valueOf(scanner.nextLine());

                        System.out.print("Digite a nova prioridade (ALTA, MEDIA, BAIXA): ");
                        prioridade = Prioridade.valueOf(scanner.nextLine());

                        System.out.print("Digite o novo estado (ABERTA, EM_PROGRESSO, CONCLUÍDA): ");
                        estado = Estado.valueOf(scanner.nextLine());

                        tarefaController.atualizarTarefa(idTarefa, titulo, descricao, dataVencimento, prioridade, estado);
                        break;

                    case 0:
                        System.out.println("==============================================");
                        System.out.println("Obrigado por usar o sistema de gerenciamento de tarefas.");
                        System.out.println("Desligando o sistema. Até logo!");
                        System.out.println("==============================================");
                        tarefaController.fecharTarefaDAO();

                        try {
                            Thread.sleep(2000); // Pausa o programa por 2 segundos
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("Reiniciando o programa...\n");
                        main(null); // Reiniciar o programa
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, digite um número inteiro válido.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
