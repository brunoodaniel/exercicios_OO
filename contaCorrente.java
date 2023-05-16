package models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class contaCorrente {
    private String titular;
    private double saldo;
    private String CPF;
    private String cartao;
    private String senha;
    private double chequeEspecial;
    private double limiteChequeEspecial;
    private int numero;
    private String agencia;
    private double limite_cartao;
    private String[] chave_pix;
    private ArrayList<String> extrato;
    private double valorEmJuizo;
    private double jurosCheque = .2;


    public contaCorrente(String titular, String CPF, String senha){
        this.titular = titular;
        this.CPF = CPF;
        this.senha = senha;
        this.saldo = 0;
        this.limiteChequeEspecial = 0;
        this.chequeEspecial = 0;
        this.limite_cartao = 0;
        this.agencia = "0001";
        this.numero = (1000000 % new Random().nextInt() + 1000);
        this.extrato = new ArrayList<String>();
        this.valorEmJuizo = 0;
    }


    public ArrayList<String> getExtrato(){
        return this.extrato;
    }
    public double getsaldo(){
        return this.saldo;
    }
    public String getCPF() {
        return this.CPF;
    }
    public String getTitular(){
        return this.titular +"["+this.CPF+"]";
    }
    public String getSenha(){
        return this.senha;
    }
    public int getNumero() {
        return numero;
    }
    public String getAgencia() {
        return agencia;
    }
    public String getCartao() {
        return cartao;
    }
    public double getLimite_cartao() {
        return limite_cartao;
    }
    public String[] getChave_pix() {
        return chave_pix;
    }
    public double getLimiteChequeEspecialTotal(){
        if(this.saldo < 0) {
            return this.chequeEspecial + (-1 * this.saldo);
        }else {
            return this.chequeEspecial;
        }
    }
    public double getLimiteChequeEspecialAtual(double cheque_especial){
        return this.chequeEspecial;
    }


    public void depositar(double valor){
        if(this.saldo < 0){
           valor += this.saldo * (this.jurosCheque);
           this.chequeEspecial += (valor - (this.saldo*-1));
        }
        this.saldo += valor - valorEmJuizo;
        this.extrato.add("Depósito realizado com sucesso para a conta " + getNumero() + " no valor de R$ " + valor);
    }
    public void sacar(double valor){
        if(this.chequeEspecial + this.saldo >= valor){
            if(this.saldo < valor){
                this.chequeEspecial -= (valor - this.saldo);
            }
            this.saldo -= valor;
            this.extrato.add("Saque Realizado com sucesso da conta " + getNumero() + " no valor de R$ " + valor);
        }
        else {
            System.out.println("Não é possível realizar o saque!");
        }
    }
    public void transferir(String agencia, int numero, double valor){
        if (this.saldo + this.chequeEspecial >= valor) {
            if(this.saldo < valor){
                this.chequeEspecial -= (valor - this.saldo);
            }
            this.saldo -= valor;
            this.extrato.add("Transferência realizada para a agência " + agencia + ", numero da conta " + numero + ", no valor de R$ " + valor);
        }
        else {
            System.out.println("Saldo insuficiente!");
        }
    }
    public void transferirPix(String pix, double valor){
        if(this.saldo + this.chequeEspecial >= valor){
            if(this.saldo < valor){
                this.chequeEspecial -= (valor - this.saldo);
            }
            this.saldo -= valor;
            this.extrato.add("Transfência pix realizada para o pix " + pix + "No valor de R$ " + valor);
        }
        else{
            System.out.println("Saldo insuficiente!");
        }
    }
    public void ImprimirExtrato(){
        for(String linha:extrato) {
            System.out.println(linha);
        }
    }
    public String toString(){
        return  " |Titular: " + this.titular +"\n"+
                " | CPF: " + this.CPF +"\n"+
                " | Agência: " + this.agencia +"\n"+
                " | Número da conta: " + this.numero +"\n"+
                " | Saldo: R$ " + this.saldo +"\n"+
                " | Cheque Especial: R$ " + this.chequeEspecial +"\n"+
                " | Limite do Cheque Especial: R$ " + this.limiteChequeEspecial +"\n"+
                " | Cartão: " + this.cartao +"\n"+
                " | Limite do Cartão: R$ " + this.limite_cartao;
    }
}
