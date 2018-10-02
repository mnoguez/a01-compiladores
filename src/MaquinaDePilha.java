import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class MaquinaDePilha {
    public static ArrayList<ArrayList<String>> leTexto(String str){
        ArrayList<ArrayList<String>> comandos = new ArrayList<>();
        String linha;
        String[] elementos;
        FileReader arquivo;
        BufferedReader buffer;

        try {
            arquivo = new FileReader(str);
            buffer = new BufferedReader(arquivo);

            try {
                linha = buffer.readLine();

                while(linha != null){
                    elementos = linha.split("\\s");

                    if(elementos[0].equalsIgnoreCase("PUSH")
                            || elementos[0].equalsIgnoreCase("MULT")
                            || elementos[0].equalsIgnoreCase("DIV")
                            || elementos[0].equalsIgnoreCase("SUM")
                            || elementos[0].equalsIgnoreCase("SUB")
                            || elementos[0].equalsIgnoreCase("PRINT")){
                        comandos.add(new ArrayList<>());

                        for(String elemento : elementos){
                            comandos.get(comandos.size() - 1).add(elemento);
                        }
                    }

                    linha = buffer.readLine();
                }
            }catch (IOException e){
                System.out.println("ERRO DE LEITURA DA LINHA");
            }
        }catch (FileNotFoundException e){
            System.out.println("ARQUIVO NAO ENCONTRADO");
        }

        return comandos;
    }

    public static void main(String args[]){
        Stack<Integer> pilha = new Stack<>();
        int n1, n2;

        ArrayList<ArrayList<String>> comandos = leTexto(args[0]);

        for(ArrayList<String> comando : comandos){
            switch (comando.get(0)){
                case "PUSH":
                    pilha.push(Integer.parseInt(comando.get(1)));
                    break;

                case "SUM":
                    n1 = pilha.pop();
                    n2 = pilha.pop();

                    pilha.push(n2 + n1);
                    break;

                case "SUB":
                    n1 = pilha.pop();
                    n2 = pilha.pop();

                    pilha.push(n2 - n1);
                    break;

                case "MULT":
                    n1 = pilha.pop();
                    n2 = pilha.pop();

                    pilha.push(n2 * n1);
                    break;

                case "DIV":
                    n1 = pilha.pop();
                    n2 = pilha.pop();

                    pilha.push(n2 / n1);
                    break;

                case "PRINT":
                    System.out.println(pilha.pop());
                    break;
            }
        }
    }
}
