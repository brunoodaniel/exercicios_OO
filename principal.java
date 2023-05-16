package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class principal {
    public static void main(String[] args) {

        contaCorrente conta = new contaCorrente("Bruno Daniel", "123.456.789-10", "1234");
        Scanner read = new Scanner(System.in);



        conta.depositar(1000);
        conta.transferir("0001", 1223, 100);
        conta.transferirPix("123", 100);
        conta.sacar(500);
        System.out.println(conta.getsaldo());
        System.out.println("\n\n\n");
        conta.ImprimirExtrato();
        System.out.println("\n\n\n");
        System.out.println(conta.toString());
    }
}
