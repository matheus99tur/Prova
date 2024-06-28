package br.edu.up.controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.modelo.Aluno;

public class AlunoControler {
   public List<Aluno> lerAlunos(String filePath) {
        List<Aluno> alunos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            boolean isFirstLine = true;
            while ((linha = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Pula a primeira linha (cabeçalho)
                }
                String[] dados = linha.split(";");
                int matricula = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                double nota = Double.parseDouble(dados[2].trim());
                alunos.add(new Aluno(matricula, nome, nota));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    // Método para calcular e escrever o resumo
    public void escreverResumo(String filePath, List<Aluno> alunos) {
        int totalAlunos = alunos.size();
        int aprovados = 0;
        int reprovados = 0;
        double somaNotas = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;

        for (Aluno aluno : alunos) {
            double nota = aluno.getNota();
            somaNotas += nota;
            if (nota >= 6.0) {
                aprovados++;
            } else {
                reprovados++;
            }
            if (nota < menorNota) {
                menorNota = nota;
            }
            if (nota > maiorNota) {
                maiorNota = nota;
            }
        }

        double mediaGeral = somaNotas / totalAlunos;

        // Garantir que o diretório exista
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Total de Alunos: " + totalAlunos);
            writer.newLine();
            writer.write("Aprovados: " + aprovados);
            writer.newLine();
            writer.write("Reprovados: " + reprovados);
            writer.newLine();
            writer.write("Menor Nota: " + menorNota);
            writer.newLine();
            writer.write("Maior Nota: " + maiorNota);
            writer.newLine();
            writer.write("Média Geral: " + mediaGeral);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
