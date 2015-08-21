/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacioni;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author AndresV
 */
public class SimulacionI {

  public ArrayList<Double> rndX = new ArrayList<>();
  public ArrayList<Double> rndY = new ArrayList<>();
  private ArrayList<Double> debajocrv = new ArrayList<>();
  private double limMin, limMax,xVal = 0;
  private String ecuacion;
  private int totalRnd;
  private double maxY;
  private boolean negativo = false;
  

  public SimulacionI(double limMin, double limMax, String ecuacion) {
    this.limMin = limMin;
    this.limMax = limMax;
    this.ecuacion = ecuacion;
  }

  public void setTotalRnd(int totalRnd) {
    this.totalRnd = totalRnd;
  }

  
  public double evaluaEcua(String ecuacion, double xVal) {
    //double [] productos = null;
    ArrayList<Double> productos = new ArrayList<>();
    ArrayList<Character> operaciones = new ArrayList<>();
    String[] valores;
    String[] partMono;
    String sPotencia;
    double potencia;
    double multiplicando;
    double multiplicador;
    double producto;
    
    String regEx = "[\\+]|[\\-]";
    if (ecuacion.charAt(0) == '-') {
      ecuacion = ecuacion.substring(1, ecuacion.length());
      negativo = true;
    }
    valores = ecuacion.split(regEx);
    for (int i = 0; i < ecuacion.length(); i++) {
      if (ecuacion.charAt(i) == '+' || ecuacion.charAt(i) == '-') {
        operaciones.add(ecuacion.charAt(i));
      }
    }
    for (String monomio : valores) {
      partMono = monomio.split("x");
      Pattern pat = Pattern.compile("(\\d)*");
      Matcher mat = pat.matcher(monomio);
      if (monomio.equals("x")) {
        multiplicando = 1;
        multiplicador = xVal;
      } else if (mat.matches()) {
        multiplicando = Double.parseDouble(monomio);
        multiplicador = 1;
      } else if (partMono[0].equals("")) {
        multiplicando = 1;
        multiplicador = Math.pow(xVal, Double.parseDouble(partMono[1].substring(1, partMono[1].length())));
      } else if (partMono.length - 1 > 0) {
        sPotencia = partMono[1].substring(1, partMono[1].length());
        potencia = Double.parseDouble(sPotencia);
        multiplicador = Math.pow(xVal, potencia);
        multiplicando = Double.parseDouble(partMono[0]);
      } else {
        multiplicador = xVal;
        multiplicando = Double.parseDouble(partMono[0]);
      }

      producto = multiplicando * multiplicador;
      productos.add(producto);
    }
    double resultado = productos.get(0);
    if (negativo) {
      resultado = resultado * (-1);
    }
    int count = 1;
    for (char operacion : operaciones) {
      if (operacion == '+') {
        resultado += productos.get(count);
      } else if (operacion == '-') {
        resultado -= productos.get(count);
      }
      count++;
    }
    return resultado;
  }

  public ArrayList generaRndX() {
    double randomNum;
    for (int i = 0; i < totalRnd; i++) {
      randomNum = limMin + Math.random() * (limMax - limMin);
      this.rndX.add(randomNum);
    }
    return this.rndX;
  }
  /*
  parabola en negativo
  */
  public boolean negativa(){
    return limMax<0 && limMin<0;
  }
  public boolean elMinEsMax(){
    if(!negativo){
      return this.evaluaEcua(this.ecuacion, limMin)>this.evaluaEcua(this.ecuacion, limMax);
    }else{
      return false;
    }
  }
  /*
   GENERAR NUMEROS ALEATORIOS PARA Y
   */

  public ArrayList generaRndY() {
    double randomNum;
    if(negativa() || elMinEsMax()){
       maxY = this.evaluaEcua(this.ecuacion, limMin);
    }else{
       maxY = this.evaluaEcua(this.ecuacion, limMax);
    }
    for (int i = 0; i < totalRnd; i++) {
      randomNum = 0 + Math.random() * (maxY - 0);
      this.rndY.add(randomNum);
    }
    this.rndY.add(maxY);
    return this.rndY;
  }
  public ArrayList debajoCurva(){
    double yVal; 
    int cont = 0;
    for (Double rndX1 : rndX) {
      yVal = this.evaluaEcua(this.ecuacion, rndX1);
      if(maxY<0){
        if(rndY.get(cont)>yVal){
          debajocrv.add(rndY.get(cont));
        }
      }else{
        if(rndY.get(cont)<yVal){
          debajocrv.add(rndY.get(cont));
        }
      }
      cont++;
    }
    return debajocrv;
  }
  public double calculaArea(){
    double aRec;
    if(negativa() || elMinEsMax()){
     aRec = this.evaluaEcua(this.ecuacion, limMin)*(limMax-limMin);
    }else{
     aRec = this.evaluaEcua(this.ecuacion, limMax)*(limMax-limMin);
    }
    if (aRec<1){
      //aRec = aRec*(-1);
    }
    double p1 = debajocrv.size();
    double p = rndX.size();
    double area;
    area = (aRec*p1)/(p);
    return area;
  }
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    Scanner sc = new Scanner(System.in);
    System.out.print("Introduzca la ecuacion en formato ax^n+ax+a.....: ");
    String ecuacion = sc.nextLine();
    System.out.print("Introduzca el limite inferior: ");
    double limMin = sc.nextDouble();
    System.out.print("Introduzca el limite superior: ");
    double limMax = sc.nextDouble();
    SimulacionI ecua = new SimulacionI(limMin, limMax, ecuacion);
    System.out.print("Introduzca la cantidad de números aleatorios que quiere generar: ");
    int tlRnd = sc.nextInt();
    ecua.setTotalRnd(tlRnd);
    double rpt;
    rpt = ecua.evaluaEcua(ecuacion, 6);
    ecua.generaRndX();
    ecua.generaRndY();
    ecua.debajoCurva();
    System.out.println("El area la curva es: "+ecua.calculaArea());
    int count =0;
     for (Double rndX1 : ecua.rndY) {
//        System.out.println("los Y son: "+rndX1+" | y los X son: "+ecua.rndX.get(count));
        count++;
    }
    
    
  }

}
