package br.edu.up.tela;

import java.util.List;

import br.edu.up.controle.AlunoControler;
import br.edu.up.modelo.Aluno;

public class Main {
    public static void main(String[] args) {
        AlunoControler alunoService = new AlunoControler();

        String alunoFilePath = "Prova/src/br/edu/up/dao/Alunos.csv";
        String resumoFilePath = "Prova/src/br/edu/up/dao/resumo.csv";

        // Ler alunos do arquivo
        List<Aluno> alunos = alunoService.lerAlunos(alunoFilePath);

        // Escrever o resumo
        alunoService.escreverResumo(resumoFilePath, alunos);
    }
}
