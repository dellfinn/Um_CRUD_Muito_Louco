package main;

import dao.AlunoDAO;
import dao.TurmaDAO;
import model.Aluno;
import model.Turma;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String dado;
        int opcao = 0;

        while (true) {
            do {
                System.out.println("1 - Incluir dados");
                System.out.println("2 - Alterar dados pelo código");
                System.out.println("3 - Alterar dados pelo nome");
                System.out.println("4 - Excluir dados pelo código");
                System.out.println("5 - Excluir dados pelo nome");
                System.out.println("6 - Listar dados cadastrados");
                System.out.println("7 - Listar os dados cadrastados em uma ordem ascedente");
                System.out.println("8 - Listar os dados cadastrados em uma ordem decrescente");
                System.out.println("9 - Listar os dados com Inner Join");
                System.out.println("10 - Mostrar a soma total dos dados");
                System.out.println("11 - Apresentar Média de dados cadastrados");
                System.out.println("12 - Listar dados com agrupamento");
                System.out.println("13 - Sair");
                System.out.print("Digite sua opção: ");
                dado = input.nextLine();
                opcao = Integer.parseInt(dado);
                //Error
                if (opcao < 1 || opcao > 13) {
                    System.out.println("ERROR: Opção invalida!");
                }
            } while (opcao < 1 || opcao > 13);

            //Sair
            if (opcao == 13) break;

            //Incluir dados
            if (opcao == 1) incluirDados();

            //Alterar pelo codigo
            if (opcao == 2) alterarDadosPeloCodigo();

            if (opcao == 3) alterarDadosPeloNome();

            if (opcao == 4) excluirDadosPeloCodigo();

            if (opcao == 5) excluirDadosPeloNome();

            if (opcao == 6) listarDadosCadastrados();

            if (opcao == 7) listarDadosCadastradosCrescente();

            if (opcao == 8) listarDadosCadastradosDecrescente();

            if (opcao == 9) listarAlunoTurma();

            if (opcao == 10) listarTurmaSomaNotaAlunos();

            if (opcao == 11) mediaNotaTurma();

            if (opcao == 12) listarTurmaAlunos();
        }
    }

    //implementação da funcionalidade incluir dados
    public static void incluirDados() {
        Scanner input = new Scanner(System.in);
        String dado;
        int opcao = 0;

        do {
            System.out.println("1 - incluir aluno\n"+
                    "2 - incluir turma");
            System.out.print("Informe sua Opção: ");
            dado = input.nextLine();
            opcao = Integer.parseInt(dado);
            //Error
            if (opcao < 1 || opcao > 2)
                System.out.println("ERROR: Opção invalida!");
        } while (opcao < 1 || opcao > 2);

        //Incluir Aluno
        if (opcao == 1) {
            if (TurmaDAO.getQuantidadeTurmas() <= 0)
                System.out.println("AVISO: Não é possivel incluir um alunos pois não há turmas cadastradas");
            else {
                String nome;
                double nota1;
                double nota2;

                System.out.print("Digite o nome do aluno: ");
                nome = input.nextLine();

                //Nota 1
                do {
                    System.out.print("Informe a nota 1: ");
                    dado = input.nextLine();
                    nota1 = Double.parseDouble(dado);

                    //Error
                    if (nota1 < 0 || nota1 > 10)
                        System.out.println("ERROR: nota invalida!");
                } while (nota1 < 0 || nota1 > 10);

                //Nota 2
                do {
                    System.out.print("Informe a nota 2: ");
                    dado = input.nextLine();
                    nota2 = Double.parseDouble(dado);

                    //Error
                    if (nota2 < 0 || nota2 > 10)
                        System.out.println("ERROR: nota invalida!");
                } while (nota2 < 0 || nota2 > 10);

                //Obtendo as turmas cadastradas
                ArrayList<Turma> turmas = TurmaDAO.getTurmas();
                int indice;
                int opcao2 = 0;
                do {
                    indice = 1;
                    System.out.println("Escolha a turma: ");
                    for (Turma turma : turmas) {
                        System.out.println(indice + " - " + turma.getNome());
                        indice++;
                    }
                    System.out.print("Digite o numero da turma: ");
                    dado = input.nextLine();
                    opcao2 = Integer.parseInt(dado);

                    //Error
                    if (opcao2 < 0 || opcao2 >= indice)
                        System.out.println("ERROR: codigo da turma invalido!");
                } while (opcao2 < 0 || opcao2 >= indice);

                Turma turma = turmas.get(opcao2 - 1);
                Aluno aluno = new Aluno(nome, nota1, nota2, turma);
                AlunoDAO alunoDAO = new AlunoDAO();
                alunoDAO.save(aluno);
                System.out.println("Aluno cadastrado com sucesso!");
            }
        }

        //Incluir Turma
        if(opcao == 2){
            System.out.print("Informe o nome da Turma: ");
            String nomeTurma = input.nextLine();

            //Salva as turmas
            Turma turma = new Turma(nomeTurma);
            TurmaDAO turmaDAO = new TurmaDAO();
            turmaDAO.save(turma);
        }
    }

    //Faz as alteraçoes de dados pelo codigo
    public static void alterarDadosPeloCodigo(){
        Scanner input = new Scanner(System.in);
        String dado;
        int opcao = 0;

        do {
            System.out.println("1 - alterar aluno\n"+
                               "2 - alterar turma");
            System.out.print("Informe sua Opção: ");
            dado = input.nextLine();
            opcao = Integer.parseInt(dado);
            //Error
            if (opcao < 1 || opcao > 2)
                System.out.println("ERROR: Opção invalida!");
        } while (opcao < 1 || opcao > 2);

        //Alterando dados do Aluno
        if (opcao == 1){
            int opcao3 = 0;
            if (AlunoDAO.getQuantidadeAlunos() <= 0){
                System.out.println("AVISO: Nenhum aluno foi cadastrado");
            }else{
                ArrayList<Aluno> alunos = AlunoDAO.getAlunos();
                int indice;
                do {
                    indice = 1;
                    System.out.println("Escolha o aluno: ");
                    for (Aluno aluno : alunos) {
                        System.out.println(indice + " - " + aluno.getNome());
                        indice++;
                    }
                    System.out.print("Digite o numero do aluno: ");
                    dado = input.nextLine();
                    opcao3 = Integer.parseInt(dado);

                    //Error
                    if (opcao3 < 0 || opcao3 >= indice)
                        System.out.println("ERROR: codigo do aluno invalido!");
                } while (opcao3 < 0 || opcao3 >= indice);

                Aluno aluno = alunos.get(opcao3 - 1);

                //Altera nome
                char opcao2;
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Deseja alterar o nome [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    System.out.print("informe um novo nome: ");
                    dado = input.nextLine();
                    aluno.setNome(dado);
                }

                //Altera nota 1
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Deseja alterar a nota 1 [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    double nota1;
                    //Nota 1
                    do {
                        System.out.print("Informe a nota 1: ");
                        dado = input.nextLine();
                        nota1 = Double.parseDouble(dado);

                        //Error
                        if (nota1 < 0 || nota1 > 10)
                            System.out.println("ERROR: nota invalida!");
                    } while (nota1 < 0 || nota1 > 10);

                    aluno.setNota1(nota1);
                }

                //Altera nota 2
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Deseja alterar a nota 2 [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    double nota2;
                    //Nota 2
                    do {
                        System.out.print("Informe a nota 2: ");
                        dado = input.nextLine();
                        nota2 = Double.parseDouble(dado);

                        //Error
                        if (nota2 < 0 || nota2 > 10)
                            System.out.println("ERROR: nota invalida!");
                    } while (nota2 < 0 || nota2 > 10);

                    aluno.setNota2(nota2);
                }

                //Altera turma
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Deseja alterar a turma [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    ArrayList<Turma> turmas = TurmaDAO.getTurmas();
                    int indice2;
                    do {
                        indice2 = 1;
                        System.out.println("Escolha a turma: ");
                        for (Turma turma : turmas) {
                            System.out.println(indice2 + " - " + turma.getNome());
                            indice2++;
                        }
                        System.out.print("Digite o numero da turma: ");
                        dado = input.nextLine();
                        opcao3 = Integer.parseInt(dado);

                        //Error
                        if (opcao3 < 0 || opcao3 >= indice2)
                            System.out.println("ERROR: codigo da turma invalido!");
                    } while (opcao3 < 0 || opcao3 >= indice2);

                    Turma turma = turmas.get(opcao3 - 1);
                    aluno.setTurma(turma);
                }
                AlunoDAO.update(aluno);

            }

        }


        if (opcao == 2){
          if (TurmaDAO.getQuantidadeTurmas() <= 0)
              System.out.println("AVISO: Não há turmas cadastradas");
          else {
              ArrayList<Turma> turmas = TurmaDAO.getTurmas();
              int indice2;
              do {
                  indice2 = 1;
                  System.out.println("Escolha a turma: ");
                  for (Turma turma : turmas) {
                      System.out.println(indice2 + " - " + turma.getNome());
                      indice2++;
                  }
                  System.out.print("Digite o numero da turma: ");
                  dado = input.nextLine();
                  opcao = Integer.parseInt(dado);

                  //Error
                  if (opcao < 0 || opcao >= indice2)
                      System.out.println("ERROR: codigo da turma invalido!");
              } while (opcao < 0 || opcao >= indice2);

              Turma turma = turmas.get(opcao - 1);

              System.out.print("Digite o novo nome da turma: ");
              dado = input.nextLine();
              turma.setNome(dado);
              TurmaDAO.update(turma);

          }
        }
    }

    public static void alterarDadosPeloNome(){
        Scanner input = new Scanner(System.in);
        String dado;
        int opcao = 0;

        do {
            System.out.println("1 - alterar aluno\n"+
                    "2 - alterar turma");
            System.out.print("Informe sua Opção: ");
            dado = input.nextLine();
            opcao = Integer.parseInt(dado);
            //Error
            if (opcao < 1 || opcao > 2)
                System.out.println("ERROR: Opção invalida!");
        } while (opcao < 1 || opcao > 2);

        //Alterando dados do Aluno
        if (opcao == 1){
            int opcao3 = 0;
            System.out.print("Informe o nome do aluno: ");
            String nomeAluno = input.nextLine();
            ArrayList<Aluno> alunos = AlunoDAO.getAlunosNome(nomeAluno);

            if (alunos.size() <= 0){
                System.out.println("AVISO: Nenhum aluno foi encontrado");
            }else{
                int indice;
                do {
                    indice = 1;
                    System.out.println("Escolha o aluno: ");
                    for (Aluno aluno : alunos) {
                        System.out.println(indice + " - " + aluno.getNome());
                        indice++;
                    }
                    System.out.print("Digite o numero do aluno: ");
                    dado = input.nextLine();
                    opcao3 = Integer.parseInt(dado);

                    //Error
                    if (opcao3 < 0 || opcao3 >= indice)
                        System.out.println("ERROR: codigo do aluno invalido!");
                } while (opcao3 < 0 || opcao3 >= indice);

                Aluno aluno = alunos.get(opcao3 - 1);

                //Altera nome
                char opcao2;
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Deseja alterar o nome [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    System.out.print("informe um novo nome: ");
                    dado = input.nextLine();
                    aluno.setNome(dado);
                }

                //Altera nota 1
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Deseja alterar a nota 1 [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    double nota1;
                    //Nota 1
                    do {
                        System.out.print("Informe a nota 1: ");
                        dado = input.nextLine();
                        nota1 = Double.parseDouble(dado);

                        //Error
                        if (nota1 < 0 || nota1 > 10)
                            System.out.println("ERROR: nota invalida!");
                    } while (nota1 < 0 || nota1 > 10);

                    aluno.setNota1(nota1);
                }

                //Altera nota 2
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Deseja alterar a nota 2 [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    double nota2;
                    //Nota 2
                    do {
                        System.out.print("Informe a nota 2: ");
                        dado = input.nextLine();
                        nota2 = Double.parseDouble(dado);

                        //Error
                        if (nota2 < 0 || nota2 > 10)
                            System.out.println("ERROR: nota invalida!");
                    } while (nota2 < 0 || nota2 > 10);

                    aluno.setNota2(nota2);
                }

                //Altera turma
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Deseja alterar a turma [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    ArrayList<Turma> turmas = TurmaDAO.getTurmas();
                    int indice2;
                    do {
                        indice2 = 1;
                        System.out.println("Escolha a turma: ");
                        for (Turma turma : turmas) {
                            System.out.println(indice2 + " - " + turma.getNome());
                            indice2++;
                        }
                        System.out.print("Digite o numero da turma: ");
                        dado = input.nextLine();
                        opcao3 = Integer.parseInt(dado);

                        //Error
                        if (opcao3 < 0 || opcao3 >= indice2)
                            System.out.println("ERROR: codigo da turma invalido!");
                    } while (opcao3 < 0 || opcao3 >= indice2);

                    Turma turma = turmas.get(opcao3 - 1);
                    aluno.setTurma(turma);
                }
                AlunoDAO.update(aluno);
            }

        }


        if (opcao == 2){
            System.out.print("Informe o nome da turma: ");
            String nomeTurma = input.nextLine();
            ArrayList<Turma> turmas = TurmaDAO.getTurmasNome(nomeTurma);

            if (turmas.size() <= 0)
                System.out.println("AVISO: Não há turmas cadastradas");
            else {
                int indice2;
                do {
                    indice2 = 1;
                    System.out.println("Escolha a turma: ");
                    for (Turma turma : turmas) {
                        System.out.println(indice2 + " - " + turma.getNome());
                        indice2++;
                    }
                    System.out.print("Digite o numero da turma: ");
                    dado = input.nextLine();
                    opcao = Integer.parseInt(dado);

                    //Error
                    if (opcao < 0 || opcao >= indice2)
                        System.out.println("ERROR: codigo da turma invalido!");
                } while (opcao < 0 || opcao >= indice2);

                Turma turma = turmas.get(opcao - 1);

                System.out.print("Digite o novo nome da turma: ");
                dado = input.nextLine();
                turma.setNome(dado);
                TurmaDAO.update(turma);

            }
        }
    }

    //Faz a exclusão de dados pelo codigo
    public static void excluirDadosPeloCodigo(){
        Scanner input = new Scanner(System.in);
        String dado;
        int opcao = 0;

        do {
            System.out.println("1 - excluir aluno\n"+
                    "2 - excluir turma");
            System.out.print("Informe sua Opção: ");
            dado = input.nextLine();
            opcao = Integer.parseInt(dado);
            //Error
            if (opcao < 1 || opcao > 2)
                System.out.println("ERROR: Opção invalida!");
        } while (opcao < 1 || opcao > 2);

        //Alterando dados do Aluno
        if (opcao == 1){
            if (AlunoDAO.getQuantidadeAlunos() <= 0){
                System.out.println("AVISO: Nenhum aluno foi cadastrado");
            }else{
                ArrayList<Aluno> alunos = AlunoDAO.getAlunos();
                int indice;
                do {
                    indice = 1;
                    System.out.println("Escolha o aluno: ");
                    for (Aluno aluno : alunos) {
                        System.out.println(indice + " - " + aluno.getNome());
                        indice++;
                    }
                    System.out.print("Digite o numero do aluno: ");
                    dado = input.nextLine();
                    opcao = Integer.parseInt(dado);

                    //Error
                    if (opcao < 0 || opcao >= indice)
                        System.out.println("ERROR: codigo do aluno invalido!");
                } while (opcao < 0 || opcao >= indice);

                Aluno aluno = alunos.get(opcao - 1);

                //Altera nome
                char opcao2;
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Tem certeza que quer excluir [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    AlunoDAO.delete(aluno);

                }
            }

        }


        if (opcao == 2){
            if (TurmaDAO.getQuantidadeTurmas() <= 0)
                System.out.println("AVISO: Não há turmas cadastradas");
            else {
                ArrayList<Turma> turmas = TurmaDAO.getTurmas();
                int indice2;
                do {
                    indice2 = 1;
                    System.out.println("Escolha a turma: ");
                    for (Turma turma : turmas) {
                        System.out.println(indice2 + " - " + turma.getNome());
                        indice2++;
                    }
                    System.out.print("Digite o numero da turma: ");
                    dado = input.nextLine();
                    opcao = Integer.parseInt(dado);

                    //Error
                    if (opcao < 0 || opcao >= indice2)
                        System.out.println("ERROR: codigo da turma invalido!");
                } while (opcao < 0 || opcao >= indice2);

                Turma turma = turmas.get(opcao - 1);

                //Altera nome
                char opcao2;
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Tem certeza que quer excluir [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    TurmaDAO.delete(turma);
                }
            }
        }
    }

    public static void excluirDadosPeloNome(){
        Scanner input = new Scanner(System.in);
        String dado;
        int opcao = 0;

        do {
            System.out.println("1 - excluir aluno\n"+
                    "2 - excluir turma");
            System.out.print("Informe sua Opção: ");
            dado = input.nextLine();
            opcao = Integer.parseInt(dado);
            //Error
            if (opcao < 1 || opcao > 2)
                System.out.println("ERROR: Opção invalida!");
        } while (opcao < 1 || opcao > 2);

        //Alterando dados do Aluno
        if (opcao == 1){
            System.out.print("Informe o nome do aluno: ");
            String nomeAluno = input.nextLine();
            ArrayList<Aluno> alunos = AlunoDAO.getAlunosNome(nomeAluno);

            if (alunos.size() <= 0){
                System.out.println("AVISO: Nenhum aluno foi encontrado");
            }else{
                int indice;
                do {
                    indice = 1;
                    System.out.println("Escolha o aluno: ");
                    for (Aluno aluno : alunos) {
                        System.out.println(indice + " - " + aluno.getNome());
                        indice++;
                    }
                    System.out.print("Digite o numero do aluno: ");
                    dado = input.nextLine();
                    opcao = Integer.parseInt(dado);

                    //Error
                    if (opcao < 0 || opcao >= indice)
                        System.out.println("ERROR: codigo do aluno invalido!");
                } while (opcao < 0 || opcao >= indice);

                Aluno aluno = alunos.get(opcao - 1);

                //Altera nome
                char opcao2;
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Tem certeza que quer excluir [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    AlunoDAO.delete(aluno);
                }

            }

        }


        if (opcao == 2){
            System.out.print("Informe o nome da turma: ");
            String nomeTurma = input.nextLine();
            ArrayList<Turma> turmas = TurmaDAO.getTurmasNome(nomeTurma);

            if (turmas.size() <= 0)
                System.out.println("AVISO: Não há turmas cadastradas");
            else {
                int indice2;
                do {
                    indice2 = 1;
                    System.out.println("Escolha a turma: ");
                    for (Turma turma : turmas) {
                        System.out.println(indice2 + " - " + turma.getNome());
                        indice2++;
                    }
                    System.out.print("Digite o numero da turma: ");
                    dado = input.nextLine();
                    opcao = Integer.parseInt(dado);

                    //Error
                    if (opcao < 0 || opcao >= indice2)
                        System.out.println("ERROR: codigo da turma invalido!");
                } while (opcao < 0 || opcao >= indice2);

                Turma turma = turmas.get(opcao - 1);

                //Altera nome
                char opcao2;
                do {
                    //toUpperCase deixa as Strings em Maiusculo, charAt pega o caracter na posicao dada
                    System.out.print("Tem certeza que quer excluir [S/N]: ");
                    opcao2 = input.nextLine().toUpperCase().charAt(0);

                    //Error
                    if (opcao2 != 'S' && opcao2 != 'N')
                        System.out.println("ERROR: opção invalida");
                }while (opcao2 != 'S' && opcao2 != 'N');

                if (opcao2 == 'S'){
                    TurmaDAO.delete(turma);
                }
            }
        }
    }
    //Listar todos dados cadastrados
    public static void listarDadosCadastrados(){
        System.out.println("TURMAS: ");
        ArrayList<Turma> turmas = TurmaDAO.getTurmas();
        for (Turma turma : turmas)
            System.out.println(turma);

        System.out.println("ALUNOS: ");
        ArrayList<Aluno> alunos = AlunoDAO.getAlunos();
         for (Aluno aluno : alunos)
            System.out.println(aluno);

    }

    //Listar todos dados cadastrados crescente
    public static void listarDadosCadastradosCrescente(){
        System.out.println("TURMAS: ");
        ArrayList<Turma> turmas = TurmaDAO.getTurmasCrescente();
        for (Turma turma : turmas)
            System.out.println(turma);

        System.out.println("ALUNOS: ");
        ArrayList<Aluno> alunos = AlunoDAO.getAlunosCrescente();
        for (Aluno aluno : alunos)
            System.out.println(aluno);

    }

    //Listar todos dados cadastrados em ordem decrescente
    public static void listarDadosCadastradosDecrescente(){
        System.out.println("TURMAS: ");
        ArrayList<Turma> turmas = TurmaDAO.getTurmasDecrescente();
        for (Turma turma : turmas)
            System.out.println(turma);

        System.out.println("ALUNOS: ");
        ArrayList<Aluno> alunos = AlunoDAO.getAlunosDecrescente();
        for (Aluno aluno : alunos)
            System.out.println(aluno);

    }
    //Listar Aluno e Turma (INNER JOIN)
    public static void listarAlunoTurma(){
        System.out.println("ALUNO - TURMA");
       AlunoDAO.printAlunoTurma();
    }

    //Mostrar soma total de alunos por turma
    public static void listarTurmaSomaNotaAlunos(){
        System.out.println("TURMA - SOMA NOTAS");
        TurmaDAO.printTurmaSomaNotaAlunos();
    }
    public static void mediaNotaTurma(){
        System.out.println("TURMA - MÉDIA");
        TurmaDAO.printTurmaMedia();
    }

    //Mostrar todos alunos de uma determinada turma
    public static void listarTurmaAlunos(){
        Scanner input = new Scanner(System.in);
        String dado;
        int opcao;

        if (TurmaDAO.getQuantidadeTurmas() <= 0)
            System.out.println("AVISO: Não há turmas cadastradas");
        else {
            ArrayList<Turma> turmas = TurmaDAO.getTurmas();
            int indice2;
            do {
                indice2 = 1;
                System.out.println("Escolha a turma: ");
                for (Turma turma : turmas) {
                    System.out.println(indice2 + " - " + turma.getNome());
                    indice2++;
                }
                System.out.print("Digite o numero da turma: ");
                dado = input.nextLine();
                opcao = Integer.parseInt(dado);

                //Error
                if (opcao < 0 || opcao >= indice2)
                    System.out.println("ERROR: codigo da turma invalido!");
            } while (opcao < 0 || opcao >= indice2);

            Turma turma = turmas.get(opcao - 1);

            TurmaDAO.printAlunos(turma);
        }
    }
}
