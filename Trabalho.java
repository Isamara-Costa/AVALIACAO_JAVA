public class Trabalho {

    int MAX_ALUNOS = 20;
    String[] nomes = new String[MAX_ALUNOS];
    double[] medias = new double[MAX_ALUNOS];
    int totalAlunos = 0;

    void main() {
        int opcao;
        //criando o menu principal
        do {
            IO.println("=== MENU PRINCIPAL: GESTÃO DE TURMA ===");
            IO.println("1 - Cadastrar Alunos e Médias");
            IO.println("2 - Exibir Lista de Alunos e Notas");
            IO.println("3 - Buscar Aluno");
            IO.println("4 - Sair");
            opcao = Integer.parseInt(IO.readln("Escolha uma opção: "));
            switch (opcao) { //criação da interação do menu com o usuário
                case 1:
                    cadastrarAlunos();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    buscarAluno();
                    break;
                case 4:
                    IO.println("O programa será encerrado.");
                    break;
                default:
                    IO.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }
    //verifica se o nome do aluno já existe na lista de alunos cadastrados
    int nomeExiste(String nome,int quantidade){

        for(int i = 0; i < quantidade; i++)
            if(nome.equalsIgnoreCase(nomes[i])) // compara o nome digitado com os nomes cadastrados
            return i; 

        return -1; // retrona -1 se o nome não for encontrado
    }

    void cadastrarAlunos (){ 
        //cadrastro de alunos e médias
         IO.println("Cadastro de Alunos e Médias");
        int quantidade;
        do { 
            // laço para pedir a quantiadade de alunos dentro dos limites definidos 
            quantidade = Integer.parseInt(IO.readln("Informe a quantidade de alunos que deseja cadastrar entre 1 e "+ MAX_ALUNOS+ ": "));
            if (quantidade < 1 || quantidade> MAX_ALUNOS)
                IO.println("Informe uma quantidade de alunos entre 1 e " + MAX_ALUNOS);

        }while (quantidade < 1 || quantidade> MAX_ALUNOS);
        
            totalAlunos = quantidade; // delimitando a quantidade de  cadastros de alunos inserida pelo usaiari

        for(int i=0; i<totalAlunos; i++) { // roda até atingir o Total de alunos digitados 
            String nome;
            do {
                nome = IO.readln("Digite o nome do Aluno [" + (i+1)+ "] :");
                if(nome.equals("")){ //verifica se o nome digitado é vazio
                    IO.println("Nome inválido. Digite um nome válido.");
                    nome= "";
                }
               
                if(nomeExiste(nome,i)!= -1) { // verificar se já tem nomes iguais cassdtrado
                    IO.println("Nome já cadastrado. Informe outro nome");
                    nome= "";
                }
            } while (nome.equals(""));
            nomes[i]= nome;

            double media; //inserir a média do aluno
            do {
                media= Double.parseDouble(IO.readln("Digite a média do aluno " + nome + " (0.0 <media> 10.0) "));
                if(media< 0.0|| media>10.0){
                    IO.println("A média digitada é invalida. Digite um valor ente 0.0 e 10.0");
                } 
            } while(media< 0.0|| media>10.0);
            medias[i]=media;

            
        }
        ordenarAlunos(totalAlunos);
        IO.println("Cadastro concluído e lista ordenada.");

    }  
    void ordenarAlunos(int quantidade) { //ordenar a lista de alunos
        for (int i = 0; i < quantidade - 1; i++) {
            for (int j = i+1; j < quantidade; j++) {
                if (nomes[j].compareToIgnoreCase(nomes[i]) < 0) /*não será igual a 0 pois não pode entrar nomes igiaos, 
                e se form menor que 0 significa queo j vem antes do i */
                    {
                    // Troca os nomes de lugares, fiz seguindo o mesmo exempolo do prf no github
                    String auxNome = nomes[i];
                    nomes[i] = nomes[j];
                    nomes[j] = auxNome;
                    // Troca as médias correspondentes
                    double auxMedias = medias[i];
                    medias[i] = medias[j];
                    medias[j] = auxMedias;
                }
            }
        }
    } 
    String situacao(double media){ /*para mostrar a situação, porem não esta funcionando, só retorna
        reprovado independente da média (carinha triste) */
        if(media >= 7){
            return "Aprovado";
        }
        else {
            return "Reprovado";
        }
    }
    void listarAlunos()  { //Listar os alunos médias e situação
        if(totalAlunos==0){
            IO.println("nenhum aluno cadrastrado");
            return;
        }

        IO.println("Lista de alunos cadastrados");
        for(int i=0; i<totalAlunos; i++)
            IO.println(nomes[i] + " -- " + medias[i] + " -- " + situacao(medias[i])); 
    }

    void buscarAluno() {//pesquisar por um aluno especifico 
        if(totalAlunos==0){
            IO.println("nenhum Aluno casadstado");
            return;
        }
        String nomePesquisado = IO.readln("Digite o nome do aluno a ser pesquisado: ");
        int indice = pesquisarAluno(nomePesquisado, totalAlunos);
        if(indice == -1){
            IO.println("Aluno não encontrado.");
        } else {
            IO.println("Aluno encontrado: " + nomes[indice] + " -- " + medias[indice] + " -- " + situacao(medias[indice]));
        }
    } 

    int pesquisarAluno(String nomePesquisado, int quantidade){
        int inicio = 0;
        int fim = quantidade - 1;
        int meio;
        while (inicio <= fim) {
            meio = (inicio + fim) / 2;
            if (nomes[meio].equalsIgnoreCase(nomePesquisado)) {
                return meio; // Aluno encontrado
            }  if (nomes[meio].compareToIgnoreCase(nomePesquisado) < 0) {
                inicio = meio + 1; // Procurar na metade direita
            } else {
                fim = meio - 1; // Procurar na metade esquerda
            }
        }
        return -1; // Aluno não encontrado
    }

}