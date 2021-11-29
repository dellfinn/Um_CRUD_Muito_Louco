package model;

public class Aluno {
    private int id;
    private String nome;
    private double nota1;
    private double nota2;

    private Turma turma;

    public Aluno(String nome, double nota1, double nota2, Turma turma) {
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.turma = turma;
    }

    public String toString(){
        return String.format("%s - [nota 1: %.2f, nota 2: %.2f]",nome,nota1,nota2);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
