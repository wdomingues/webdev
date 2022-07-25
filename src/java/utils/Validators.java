/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import application.Funcionario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;

/**
 *
 * @author winne
 */
public class Validators {
    public static void requireValid(Funcionario funcionario) {
        String nome = funcionario.getNome();
        String documento = funcionario.getDocumento();
        String senha = funcionario.getSenha();
        String papel = funcionario.getPapel();
        if (nome == null || nome.length() > 50) {
            throw new IllegalArgumentException("Cadastro negado. Funcionário com nome inválido: " + nome);
        }
        if (documento == null || !documento.matches("\\d\\d\\d\\.\\d\\d\\d\\.\\d\\d\\d-\\d\\d")) {
            throw new IllegalArgumentException("Cadastro negado. Funcionário com CPF inválido: " + documento
                                             + "; documento deve seguir o padrão 000.000.000-00");
        }
        if (senha == null || senha.length() > 10) {
            throw new IllegalArgumentException("Cadastro negado. Funcionário com senha inválida: " + senha);
        }
        if (papel == null
                   || !(papel.equals(Funcionario.Papeis.ADMINISTRADOR)
                        || papel.equals(Funcionario.Papeis.VENDEDOR)
                        || papel.equals(Funcionario.Papeis.COMPRADOR))) {
            throw new IllegalArgumentException("Cadastro negado. Funcionário com papel inválido: " + papel);
        }
    }

    public static int convertFloat2Int(float f){
        return Math.round (f * 1f);
    }
    public static float convertInt2Float(int i){
        if (i == 0) return 0f;
        return i / 1f;
    }
    
    public static Date convertDateString2Date(String dateString){
        try {
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            return data;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public static String convertDate2String(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(date);
        return strDate;
    }
    public static String cpfViewFormatter(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String enderecoViewFormatter(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String cepViewFormatter(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String telefoneViewFormatter(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String emailViewFormatter(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String cnpjViewFormatter(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String senhaValidator(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String cpfValidator(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String cnpjValidator(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String cepValidator(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String telefoneValidator(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String emailValidator(String str){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String strDate = dateFormat.format(str);
        return strDate;
    }
    public static String valorViewFormatter(Float valor){
        String parsedValor = String.valueOf(valor).replace(".",",");
        String intPart = parsedValor.split(",")[0];
        ArrayDeque<String> intPartsArr = new ArrayDeque<String>();
        String join = "";
        if (intPart.length()>3){
            int tam = intPart.length();
            int i = 0;
            String[] intPartSplit = intPart.split("");
            for (int k= (intPartSplit.length-1); k>=0; k--){
                if (i!=0 && i%3 == 0){
                    intPartsArr.push(".");
                }
                intPartsArr.push(intPartSplit[k]);
                i++;
           }
           for (String part : intPartsArr){
               join += intPartsArr.pop();
           }
        } else join = intPart;
        join = "R$ ".concat(join);
        
        String decimalPart = parsedValor.split(",")[1];
        if (decimalPart != ""){
            int decimalPartLength = decimalPart.length();
            if (decimalPartLength == 1)
                return join + ","+decimalPart+"0";
            else return join + ","+decimalPart;
        }
        return join+","+decimalPart+"00";
    }
    
}
