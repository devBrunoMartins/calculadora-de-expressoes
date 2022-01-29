import calculadora.Expressions;
import utils.In;
import utils.Out;

import java.io.IOException;

public class App {

    public static void main(String[] args)  {
        Expressions express = new Expressions();

        /*
        * Esta aplicação calcula expressões simples. Segue abaixo um formato exemplo.
        *   {10+5-[8*9+3-2*(54-6+2)/5]}
        *
        * Funcionamento:
        * - O algorítmo coleta as mini expressões e envia para cálculo individual.
        *   Obs.: Uma mini expressão é o nível mais inferior separado por parênteses, colchetes ou chaves.
        *   Exemplo: Segue a seguinte expressão: {10+5-[8*9+3-2*(54-6+2)/5]}
        *   A primeira mini expressão será (54-6+2), cujo resultado é 50.
        *   Substituindo o resultado no lugar da mini expressão, temos: {10+5-[8*9+3-2*50/5]}
        *   A próxima mini expressão coletada será 8*9+3-2*50/5, cujo resultado é: 55
        *   Substituindo o resultado no lugar da mini expressão, temos: {10+5-55}
        *   A última mini expressão será {10+5-55}, cujo resultado é -40;
        *
        * A ordem de precedência para resolver as mini expressões, é:
        * 1º Multiplicação.
        * 2º Divisão
        * 3º Soma e divisão na ordem da esquerda para direita.
        *
        * */

        Out.println("Express\n @Calculadora de expressões");
        Out.println("Use os símbolos: { [ ( 0. - 9. ) ] } + - * / para elaborar suas expressões abaixo: ");
        String expr = "";

        while (!((expr.equals("exit") || expr.equals("quit")))){
            Out.print("> ");
            expr = In.read();
            Out.print(express.resolve(expr) + "\n");
        }

        Out.println("Bye!");

        Out.close();
        In.close();

    }
}
