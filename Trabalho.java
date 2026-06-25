void main(){
//variaveis para cadastro dos alunos e da médias
var alunos= new String [3];
var media= new float [3];
String aux;
//boolean aprovado= media => 7;

for (int cont=0; cont<3;cont++) {
    alunos[cont] = IO.readln("Digite o nome [" + cont + "] : ");
//Rotina de ordenação
    for (int i=0;i<alunos.length-1;i++)
        for (int j=i+1;j<alunos.length;j++)
            if (alunos[i].compareToIgnoreCase(alunos[j]) > 0){
                aux = alunos[i];
                alunos[i]=alunos[j];
                alunos[j]=aux;
            }

    IO.println("Estes foram os nome digitados e agora ordenados: ");
    for (int cont=0;cont<alunos.length;cont++)
        IO.println("Nome[" + (cont) + "]: " + alunos[cont]);    
//médias dos alunos 
for (int cont=0; cont<3;cont++) {
    media[cont] = Float.parseFloat(IO.readln("Digite a "));
}
}
}