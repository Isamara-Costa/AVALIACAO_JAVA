public class trab {
    static final int MAX_ALUNOS = 20;
    static String[] nomes = new String[MAX_ALUNOS];
    static double[] medias = new double[MAX_ALUNOS];
    static int totalAlunos = 0;

    public static void main(String[] args) {
        int opcao;
        do {
            IO.println("=== MENU PRINCIPAL: GESTÃO DE TURMA ===");
            IO.println("1 - Cadastrar Alunos e Médias");
            IO.println("2 - Exibir Lista de Alunos e Notas");
            IO.println("3 - Buscar Aluno");
            IO.println("4 - Sair");
            opcao = IO.readInt("Escolha uma opção: ");
            switch (opcao) {
                case 1:
                    cadastrarAlunos();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    buscarAlunoMenu();
                    break;
                case 4:
                    IO.println("Encerrando o sistema.");
                    break;
                default:
                    IO.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    static void cadastrarAlunos() {
        IO.println("Cadastro de Alunos e Médias");
        int quantidade;
        do {
            quantidade = IO.readInt("Informe quantos alunos deseja cadastrar (1.." + MAX_ALUNOS + "): ");
            if (quantidade < 1 || quantidade > MAX_ALUNOS) {
                IO.println("Quantidade inválida. Informe um valor entre 1 e " + MAX_ALUNOS + ".");
            }
        } while (quantidade < 1 || quantidade > MAX_ALUNOS);

        totalAlunos = quantidade;
        for (int i = 0; i < totalAlunos; i++) {
            String nome;
            do {
                nome = IO.readString("Digite o nome do aluno " + (i + 1) + ": ");
                if (nome == null || nome.trim().isEmpty()) {
                    IO.println("Nome inválido. Digite um nome não vazio.");
                    nome = "";
                } else if (nomeExiste(nome, i)) {
                    IO.println("Nome já cadastrado. Informe outro nome.");
                    nome = "";
                }
            } while (nome.isEmpty());
            nomes[i] = nome;

            double media;
            do {
                media = IO.readDouble("Digite a média final do aluno " + nome + " (0.0 < média < 10.0): ");
                if (media <= 0.0 || media >= 10.0) {
                    IO.println("Média inválida. Informe um valor estritamente maior que 0.0 e menor que 10.0.");
                }
            } while (media <= 0.0 || media >= 10.0);
            medias[i] = media;
        }
        ordenarAlunos(totalAlunos);
        IO.println("Cadastro concluído e lista ordenada.");
    }

    static void listarAlunos() {
        if (totalAlunos == 0) {
            IO.println("Nenhum aluno cadastrado.");
            return;
        }
        IO.println("Lista de Alunos e Notas:");
        for (int i = 0; i < totalAlunos; i++) {
            IO.println(nomes[i] + " - " + medias[i] + " - " + situacao(medias[i]));
        }
    }

    static void buscarAlunoMenu() {
        if (totalAlunos == 0) {
            IO.println("Nenhum aluno cadastrado para pesquisa.");
            return;
        }
        String nomeProcurado = IO.readString("Digite o nome do aluno que deseja buscar: ");
        int indice = pesquisarAluno(nomeProcurado, nomes, totalAlunos);
        if (indice == -1) {
            IO.println("Aluno não consta na lista.");
        } else {
            IO.println("Aluno encontrado:");
            IO.println("Nome: " + nomes[indice]);
            IO.println("Média: " + medias[indice]);
            IO.println("Situação: " + situacao(medias[indice]));
        }
    }

    static boolean nomeExiste(String nome, int limite) {
        for (int i = 0; i < limite; i++) {
            if (nomes[i].equalsIgnoreCase(nome.trim())) {
                return true;
            }
        }
        return false;
    }

    static void ordenarAlunos(int limite) {
        for (int i = 0; i < limite - 1; i++) {
            for (int j = i + 1; j < limite; j++) {
                if (nomes[i].compareToIgnoreCase(nomes[j]) > 0) {
                    String tempNome = nomes[i];
                    nomes[i] = nomes[j];
                    nomes[j] = tempNome;
                    double tempMedia = medias[i];
                    medias[i] = medias[j];
                    medias[j] = tempMedia;
                }
            }
        }
    }

    static int pesquisarAluno(String nomeProcurado, String[] vetorNomes, int limite) {
        int inferior = 0;
        int superior = limite - 1;
        while (inferior <= superior) {
            int meio = (inferior + superior) / 2;
            int comparacao = vetorNomes[meio].compareToIgnoreCase(nomeProcurado.trim());
            if (comparacao == 0) {
                return meio;
            }
            if (comparacao < 0) {
                inferior = meio + 1;
            } else {
                superior = meio - 1;
            }
        }
        return -1;
    }

    static String situacao(double media) {
        return media >= 7.0 ? "Aprovado" : "Reprovado";
    }
}

