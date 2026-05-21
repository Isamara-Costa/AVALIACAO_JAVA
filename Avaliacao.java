
/* Analisador de Desempenho de Linha de Produção
Elabore um programa em Java 25 (utilizando a estrutura simplificada, sem classes explicitas) para monitorizar a eficiência de
uma máquina numa linha de produção industrial através do número de peças defeituosas encontradas por lote. O programa
deve funcionar da seguinte forma:
• Estrutura de Repetição e Atribuição: O programa deve solicitar ao utilizador a quantidade de peças defeituosas
(número inteiro) encontradas em vários lotes inspecionados. A leitura deve ser interrompida imediatamente quando
o utilizador introduzir um valor negativo (ex: -1).
• Processamento: Durante a leitura, o programa deve acumular o total de peças defeituosas encontradas e contar
quantos lotes foram analisados.
• Estrutura Condicional: Ao encerrar a repetição (quando for digitado um número negativo), o programa deve calcular
a média de peças defeituosas por lote (Total de Defeitos / Lotes Analisados) e exibir uma classificação sobre o
estado da máquina:
◦ Se a média for menor ou igual a 2 peças: "Máquina Regulada (Excelente)"
◦ Se a média for entre 2.1 e 5 peças: "Alerta: Necessita de Manutenção Preventiva"
◦ Se a média for maior que 5 peças: "Paragem Crítica: Máquina Danificada"
Nota: Garanta que o programa exiba uma mensagem de aviso caso o utilizador introduza um valor negativo logo no primeiro
lote, evitando divisões por zero. */

void main() {
    
    double media;
    int lote=0;
    int totalDefeitos=0;

    while(true){
        int defeitos= Integer.parseInt(IO.readln("Insira o número de defeitos: ")); //solicitar a quantidade de peças defeituosas
         if(defeitos<0){
            IO.println("O número de devfeitos é negativo"); // caso o número informado seja negativo 
            break;
         } 
            totalDefeitos= totalDefeitos + defeitos; //soma da quantidade de peças defeituoosa
            lote++; //conta o número de lotes
        }

    if (lote==0) {
        
         IO.println("Em nenhum lote foi encontrado peça com defeito"); // caso o número de peças inicial negativo

    } else{
        IO.println("O numero total de peças com defeitos é de:  " + totalDefeitos);
        IO.println("O numero de lote com defeto é de: "+ lote);


        media= totalDefeitos/lote; //calculo da média 
        IO.println("=====================================");
        IO.println("A média de peças com defeito é de: " + media);
        if(media<=2) // especificações
            IO.println("Máquina regulada- Exceelente");

        if(media<=5)
            IO.println("ALERTA: Necessita de manutenção preventiva");
                else
                    IO.println("Paragem Crítica: Máquina Danificada");
    }
       
    

}
