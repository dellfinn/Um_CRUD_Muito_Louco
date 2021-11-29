package dao;

import com.mysql.jdbc.PreparedStatement;
import factory.ConnectionFactory;
import model.Aluno;
import model.Turma;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {
    public static void save(Turma turma) {
        String sql = "INSERT INTO turma(nome) VALUES (?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //CRIA UMA CONEXAO COM O BANCO DE DADOS
            conn = ConnectionFactory.createConnectionToMySQL();

            //Preparar o Statement que vai retornar a chave primaria de contatos
            pstm = (PreparedStatement)conn.prepareStatement(sql);


            //ADICIONAR OS VALORES ESPERADOS PELA QUERY
            pstm.setString(1, turma.getNome());


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
    //DELETAR
    public static void  delete(Turma turma) {
        String sql = "DELETE FROM turma WHERE id = ?";
        Connection conn  = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1,turma.getId());
            pstm.execute();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(pstm !=null){//Fechar o PreparedStatement
                    pstm.close();
                }
                if(conn !=null){//Fechar  o Connection
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static int getQuantidadeTurmas(){
        int total = 0;

        String sql = "SELECT COUNT(*) total FROM turma";

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement)conn.prepareStatement(sql);

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
    public static ArrayList<Turma> getTurmas(){
        String sql = "SELECT * FROM turma";

        //Lista de Array de contatos
        ArrayList<Turma> turmas = new ArrayList();

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement)conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while(rset.next()) {
                int id = rset.getInt("id");
                String nome = rset.getString("nome");

                Turma turma = new Turma(nome);//cada contato que retornar do banco vai ter uma estancia sanvando no obj
                turma.setId(id);

                turmas.add(turma);//Cada item que foi serapdo do Array foi adicionado no obj contato

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

        return turmas;
    }
    public static ArrayList<Turma> getTurmasNome(String nome){
        String sql = "SELECT * FROM turma WHERE nome LIKE '%"+nome+"%'";

        //Lista de Array de contatos
        ArrayList<Turma> turmas = new ArrayList();

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement)conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while(rset.next()) {
                int id = rset.getInt("id");
                String nome_turma = rset.getString("nome");

                Turma turma = new Turma(nome_turma);//cada contato que retornar do banco vai ter uma estancia sanvando no obj
                turma.setId(id);

                turmas.add(turma);//Cada item que foi serapdo do Array foi adicionado no obj contato

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

        return turmas;

    }

    public static void  update(Turma turma){
        String sql = "UPDATE turma SET nome = ? WHERE id = ?";
        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement)conn.prepareStatement(sql);
            pstm.setString(1,turma.getNome());
            pstm.setInt(2,turma.getId());
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

    public static ArrayList<Turma> getTurmasCrescente(){
        String sql = "SELECT * FROM turma ORDER BY nome ASC";

        //Lista de Array de contatos
        ArrayList<Turma> turmas = new ArrayList();

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement)conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while(rset.next()) {
                int id = rset.getInt("id");
                String nome = rset.getString("nome");

                Turma turma = new Turma(nome);//cada contato que retornar do banco vai ter uma estancia sanvando no obj
                turma.setId(id);

                turmas.add(turma);//Cada item que foi serapdo do Array foi adicionado no obj contato

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

        return turmas;
    }
    public static ArrayList<Turma> getTurmasDecrescente(){
        String sql = "SELECT * FROM turma ORDER BY nome DESC";

        //Lista de Array de contatos
        ArrayList<Turma> turmas = new ArrayList();

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement)conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while(rset.next()) {
                int id = rset.getInt("id");
                String nome = rset.getString("nome");

                Turma turma = new Turma(nome);//cada contato que retornar do banco vai ter uma estancia sanvando no obj
                turma.setId(id);

                turmas.add(turma);//Cada item que foi serapdo do Array foi adicionado no obj contato

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
        return turmas;
    }
    public static void printTurmaSomaNotaAlunos(){
        String sql = "SELECT T.nome nome_turma, SUM(A.nota1 + A.nota2) soma_notas FROM turma T INNER JOIN" +
                " aluno A ON A.fk_turma = T.id GROUP BY T.id";

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement)conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while(rset.next()) {

                String nome_turma = rset.getString("nome_turma");
                double soma_notas = rset.getDouble("soma_notas");
                System.out.println(nome_turma + " - " + soma_notas);

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
    public static void printTurmaMedia(){
        String sql = "SELECT T.nome nome_turma, AVG(A.nota1) media_nota1,AVG(A.nota2) media_nota2 FROM turma T INNER JOIN" +
                " aluno A ON A.fk_turma = T.id GROUP BY T.id";

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement)conn.prepareStatement(sql);

            //Result set da Query
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while(rset.next()) {

                String nome_turma = rset.getString("nome_turma");
                double media_nota1 = rset.getDouble("media_nota1");
                double media_nota2 = rset.getDouble("media_nota2");
                System.out.println(nome_turma + " - MEDIA NOTA 1: " + media_nota1 + ", MEDIA NOTA 2: " + media_nota2);

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
    public static void printAlunos(Turma turma){
        String sql = "SELECT A.nome nome_aluno, A.nota1 nota1, A.nota2 nota2 FROM turma T INNER JOIN" +
                " aluno A ON A.fk_turma = T.id WHERE T.id = ?";

        //conexão com o banco
        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. [[[SELECT]]]
        ResultSet rset = null;

        //TENTAR FAZER CONEXÃO DO BANCO
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement)conn.prepareStatement(sql);

            //Result set da Query
            pstm.setInt(1,turma.getId());
            rset = pstm.executeQuery();

            //Equanto tiver registro [contato] ele vai esta executando
            while(rset.next()) {

                String nome_aluno = rset.getString("nome_aluno");
                double nota1 = rset.getDouble("nota1");
                double nota2 = rset.getDouble("nota2");
                System.out.println(nome_aluno + " - NOTA 1: " + nota1 + ", NOTA 2: " + nota2);

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
