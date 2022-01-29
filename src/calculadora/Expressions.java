package calculadora;

import utils.Out;

import java.util.ArrayList;
import java.util.List;

public class Expressions {
    private List<Character> expr = new ArrayList<>();
    private int qtdParenteses = 0;
    private int qtdColchetes = 0;
    private int qtdChaves = 0;

    public double resolve(String expr) {
        setExpr(expr.toCharArray());
        refreshQtdBrakets();

        if(qtdChaves == 0 || qtdColchetes == 0 || qtdParenteses == 0){
            substituirMiniExpressaoPorResultado(expr+":1:"+(expr.length()));
        }

        while(this.qtdParenteses > 0){
            substituirMiniExpressaoPorResultado(coletaMiniExpressao('('));
            this.qtdParenteses--;
        }

        while(this.qtdColchetes > 0){
            substituirMiniExpressaoPorResultado(coletaMiniExpressao('['));
            this.qtdColchetes--;
        }

        while(this.qtdChaves > 0){
            substituirMiniExpressaoPorResultado(coletaMiniExpressao('{'));
            this.qtdChaves--;
        }

        String resultado = "";
        for (char caractere: this.expr) {
            resultado += caractere;
        }
        return Double.parseDouble(resultado);
    }

    private void substituirMiniExpressaoPorResultado(String miniExpressao){
        String[] dados =    miniExpressao.split(":");
        String miniExpr =   dados[0];
        int indiceInicio =  Integer.parseInt(dados[1]) - 1;
        int indiceFim =     Integer.parseInt(dados[2]);
        int intervaloMiniExpressao = indiceFim - indiceInicio;
        String resultado = obterResultado(miniExpr);

        while(intervaloMiniExpressao > 0){
            this.expr.remove(indiceInicio);
            intervaloMiniExpressao--;
        }

        char[] result = String.valueOf(resultado).toCharArray();
        int sizeResultado = result.length;
        int cont = 0;
        while(cont < sizeResultado){
            this.expr.add(indiceInicio + cont, result[cont]);
            cont++;
        }
    }

    private String obterResultado(String miniExpr) {
        double operacao = 0;
        List<String> listaElementos = prepareMiniExpressao(miniExpr);

        int limite = listaElementos.size();
        int i = 1;

        while(i < limite){
            if(listaElementos.get(i).equals("*")){
                operacao = Double.parseDouble(listaElementos.get(i - 1)) * Double.parseDouble(listaElementos.get(i + 1));
                listaElementos.add(i - 1 ,String.valueOf(operacao));
                listaElementos.remove(i);
                listaElementos.remove(i);
                listaElementos.remove(i);
                limite = listaElementos.size();
            }else{
                i++;
            }
        }

        i = 1;
        limite =  listaElementos.size();

        while(i < limite){
            if(listaElementos.get(i).equals("/")){
                try{
                    operacao = Double.parseDouble(listaElementos.get(i - 1)) / Double.parseDouble(listaElementos.get(i + 1));
                }catch (Exception e){
                    Out.println("Expressão inválida! Houve divisão por zero em algum momento.\n " + e.getMessage());
                }
                listaElementos.add(i - 1 ,String.valueOf(operacao));
                listaElementos.remove(i);
                listaElementos.remove(i);
                listaElementos.remove(i);
                limite = listaElementos.size();
            }else{
                i++;
            }
        }

        i = 1;
        limite =  listaElementos.size();

        while(i < limite){
            if(listaElementos.get(i).equals("+") || listaElementos.get(i).equals("-")){
                if(listaElementos.get(i).equals("+")){
                    operacao = Double.parseDouble(listaElementos.get(i - 1)) + Double.parseDouble(listaElementos.get(i + 1));
                }else{
                    operacao = Double.parseDouble(listaElementos.get(i - 1)) - Double.parseDouble(listaElementos.get(i + 1));
                }
                listaElementos.add(i - 1 ,String.valueOf(operacao));
                listaElementos.remove(i);
                listaElementos.remove(i);
                listaElementos.remove(i);
                limite = listaElementos.size();
            }else{
                i++;
            }
        }



     return listaElementos.get(0);
    }

    private List<String>  prepareMiniExpressao(String miniExpr){
        char[] expressaoChar = miniExpr.toCharArray();
        List<String> expressao = new ArrayList<>();
        expressao.add("");
        String temp = "";
        int i = 0;
        for (char caractere: expressaoChar) {
            if(caractere == '+' || caractere == '-' || caractere == '*' || caractere == '/'){
                i++;
                expressao.add(i, String.valueOf(caractere));
                i++;
            }else{
                if(expressao.size() - 1 < i) expressao.add("");
                temp = expressao.get(i).concat(String.valueOf(caractere));
                expressao.add(i, temp);
                expressao.remove(i+1);
            }

        }

        return expressao;
    }

    private String coletaMiniExpressao(char symbol){
        char symbolClose = symbol == '('?')':symbol == '['?']':'}';
        int indiceInicio = 0;
        int indiceFim = 0;
        String miniExpressao = "";
        char elemento;
        int i = 0;
        while(true){
            elemento = expr.get(i);
            i++;
            if(elemento == symbol){
                miniExpressao = "";
                indiceInicio = i;
            }else if(elemento == symbolClose){
                indiceFim = i;
                break;
            }else{
                miniExpressao += elemento;
            }


        }

        return miniExpressao + ":" + indiceInicio + ":" + indiceFim;
    }

    private void setExpr(char[] expr) {

        while (this.expr.size() > 0){
            this.expr.remove(0);
        }

        for (char caractere: expr) {
            this.expr.add(caractere);
        }
    }

    private void refreshQtdBrakets(){
        for (char caractere: this.expr) {
            if(caractere == '('){
                this.qtdParenteses++;
            }
            if(caractere == '['){
                this.qtdColchetes++;
            }
            if(caractere == '{'){
                this.qtdChaves++;
            }
        }

    }

}
