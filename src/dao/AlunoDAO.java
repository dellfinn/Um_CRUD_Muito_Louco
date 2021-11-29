package dao;

import com.mysql.jdbc.PreparedStatement;
import factory.ConnectionFactory;
import model.Aluno;
import model.Turma;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AlunoDAO {
    public static void save(Aluno aluno) {
        String sql = "INSERT INTO aluno(nome, nota1, nota2, fk_turma) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //CRIA UMA CONEXAO COM O BANCO DE DADOS
            conn = ConnectionFactory.createConnectionToMySQL();

            //Preparar o Statement que vai retornar a chave primaria de contatos
            pstm = (PreparedStatement) conn.prepareStatement(sql);


            //ADICIONAR OS VALORES ESPERADOS PELA QUERY
            pstm.setString(1, aluno.getNome());
            pstm.setDouble(2, aluno.getNota1());
            pstm.setDouble(3, aluno.getNota2());
            pstm.setInt(4, aluno.getTurma().getId());

            //EXECUTAR A QUERY
            pstm.execute();

            //SAIDA QUANDO O CONTATO FOR CADASTRADO
            System.out.println("Contato salvo com sucesso!");

            //EXECEPTIONS
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //FECHAR AS CONEXOES
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();

                }//EXECEPTIONS
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //DELETE
    public static void delete(Aluno aluno) {
        String sql = "DELETE FROM aluno WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, aluno.getId());
            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {//Fechar o PreparedStatement
                    pstm.close();
                }
                if (conn != null) {//Fechar  o Connection
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getQuantidadeAlunos() {
        int total = 0;

        String sql = "SELECT COUNT(*) total FROM aluno";

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            if (rset.next())
                total = rset.getInt(1);

        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {//conexão
                    conn.close();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }
        }
        return total;
    }

    public static ArrayList<Aluno> getAlunos() {
        String sql = "SELECT A.id id_aluno, A.nome nome_aluno, A.nota1 nota1, A.nota2 nota2, T.id id_turma, T.nome" +
                " nome_turma FROM aluno A INNER JOIN turma T ON A.fk_turma = T.id";

        //Lista de Array de contatos
        ArrayList<Aluno> alunos = new ArrayList();

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while (rset.next()) {
                int id_aluno = rset.getInt("id_aluno");
                String nome_aluno = rset.getString("nome_aluno");
                double nota1 = rset.getDouble("nota1");
                double nota2 = rset.getDouble("nota2");

                int id_turma = rset.getInt("id_turma");
                String nome_turma = rset.getString("nome_turma");

                Turma turma = new Turma(nome_turma);
                turma.setId(id_turma);

                Aluno aluno = new Aluno(nome_aluno, nota1, nota2, turma);//cada contato que retornar do banco vai ter uma estancia sanvando no obj
                aluno.setId(id_aluno);

                alunos.add(aluno);//Cada item que foi serapdo do Array foi adicionado no obj contato

            }//EXECEPTIONS
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {//conexão
                    conn.close();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }
        }

        return alunos;
    }

    public static ArrayList<Aluno> getAlunosNome(String nome) {
        String sql = "SELECT A.id id_aluno, A.nome nome_aluno, A.nota1 nota1, A.nota2 nota2, T.id id_turma, T.nome" +
                " nome_turma FROM aluno A INNER JOIN turma T ON A.fk_turma = T.id WHERE A.nome LIKE '%" + nome + "%'";

        //Lista de Array de contatos
        ArrayList<Aluno> alunos = new ArrayList();

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while (rset.next()) {
                int id_aluno = rset.getInt("id_aluno");
                String nome_aluno = rset.getString("nome_aluno");
                double nota1 = rset.getDouble("nota1");
                double nota2 = rset.getDouble("nota2");

                int id_turma = rset.getInt("id_turma");
                String nome_turma = rset.getString("nome_turma");

                Turma turma = new Turma(nome_turma);
                turma.setId(id_turma);

                Aluno aluno = new Aluno(nome_aluno, nota1, nota2, turma);//cada contato que retornar do banco vai ter uma estancia sanvando no obj
                aluno.setId(id_aluno);

                alunos.add(aluno);//Cada item que foi serapdo do Array foi adicionado no obj contato

            }//EXECEPTIONS
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {//conexão
                    conn.close();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }
        }

        return alunos;
    }

    public static void update(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, nota1 = ?, nota2 = ?, fk_turma = ? WHERE id = ?";
        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, aluno.getNome());
            pstm.setDouble(2, aluno.getNota1());
            pstm.setDouble(3, aluno.getNota2());
            pstm.setInt(4, aluno.getTurma().getId());
            pstm.setInt(5, aluno.getId());
            pstm.execute();


        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {//conexão
                    conn.close();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }
        }
    }

    public static ArrayList<Aluno> getAlunosCrescente() {
        String sql = "SELECT A.id id_aluno, A.nome nome_aluno, A.nota1 nota1, A.nota2 nota2, T.id id_turma, T.nome" +
                " nome_turma FROM aluno A INNER JOIN turma T ON A.fk_turma = T.id ORDER BY A.nome ASC";

        //Lista de Array de contatos
        ArrayList<Aluno> alunos = new ArrayList();

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while (rset.next()) {
                int id_aluno = rset.getInt("id_aluno");
                String nome_aluno = rset.getString("nome_aluno");
                double nota1 = rset.getDouble("nota1");
                double nota2 = rset.getDouble("nota2");

                int id_turma = rset.getInt("id_turma");
                String nome_turma = rset.getString("nome_turma");

                Turma turma = new Turma(nome_turma);
                turma.setId(id_turma);

                Aluno aluno = new Aluno(nome_aluno, nota1, nota2, turma);//cada contato que retornar do banco vai ter uma estancia sanvando no obj
                aluno.setId(id_aluno);

                alunos.add(aluno);//Cada item que foi serapdo do Array foi adicionado no obj contato

            }//EXECEPTIONS
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {//conexão
                    conn.close();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }
        }

        return alunos;
    }

    public static ArrayList<Aluno> getAlunosDecrescente() {
        String sql = "SELECT A.id id_aluno, A.nome nome_aluno, A.nota1 nota1, A.nota2 nota2, T.id id_turma, T.nome" +
                " nome_turma FROM aluno A INNER JOIN turma T ON A.fk_turma = T.id ORDER BY A.nome DESC";

        //Lista de Array de contatos
        ArrayList<Aluno> alunos = new ArrayList();

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while (rset.next()) {
                int id_aluno = rset.getInt("id_aluno");
                String nome_aluno = rset.getString("nome_aluno");
                double nota1 = rset.getDouble("nota1");
                double nota2 = rset.getDouble("nota2");

                int id_turma = rset.getInt("id_turma");
                String nome_turma = rset.getString("nome_turma");

                Turma turma = new Turma(nome_turma);
                turma.setId(id_turma);

                Aluno aluno = new Aluno(nome_aluno, nota1, nota2, turma);//cada contato que retornar do banco vai ter uma estancia sanvando no obj
                aluno.setId(id_aluno);

                alunos.add(aluno);//Cada item que foi serapdo do Array foi adicionado no obj contato

            }//EXECEPTIONS
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {//conexão
                    conn.close();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }
        }

        return alunos;
    }

    public static void printAlunoTurma() {
        String sql = "SELECT A.nome nome_aluno, T.nome nome_turma FROM aluno A INNER JOIN turma T ON A.fk_turma = T.id";

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while (rset.next()) {
                String nome_aluno = rset.getString("nome_aluno");
                String nome_turma = rset.getString("nome_turma");
                System.out.println(nome_aluno + " - " + nome_turma);

            }//EXECEPTIONS
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {//conexão
                    conn.close();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }
        }
    }
}


