/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Carlos
 */
public class Metodos {
                    //Metodo que selecciona el tipo de gramatica
    public int tipoGramatica(String[][] matGrmtica){ 
        
        int bandera=0;
        for (int i = 0; i < matGrmtica.length; i++) { //recorre filas
            String pasajero = matGrmtica[i][1];
            if(pasajero.length()>1){
                String[] ns = pasajero.split(">");
                if(ns.length>1){
                    bandera = 2;
                    break;
                }else{
                    String[] vecseparado = pasajero.split("<");                
                    String n = vecseparado[0];
                    if(n.length()!=1){
                        bandera=1;                        
                    }
                }                
                }else if(!pasajero.equals("¬")){
                    bandera=1;                    
                }
            }
        return bandera;
    }
//===========Metodo que genera una String cuan hace la conversion de la forma lineal a la forma especial===========================================================================================================================   
    public String convertirGramatica(String[][] matGramatica){ 
            
        String nuevoAutomata="";
        String casoespecial="";
        boolean bandera=false;
        for (int i = 0; i < matGramatica.length; i++) {
            String pasa= matGramatica[i][1];
            if(pasa.length()!=1&&pasa.length()!=3){ //si hay mas de un elemento en el lado derecho de la produccion
                String[] ve = pasa.split("<");
                if(ve[0].length()==1){
                    if(!nuevoAutomata.contains(matGramatica[i][0]+"-->"+pasa)){ //Se agrego ahora
                        nuevoAutomata=nuevoAutomata+matGramatica[i][0]+"-->"+pasa+"\n";
                    }                                                
                }else{ //donde antes de un no terminal tiene una cadena de terminales
                    String xx = ve[0];
                    char x1 = xx.charAt(0);
                    int j=1;
                    String ner="";
                    while (j<xx.length()) {
                        ner = ner+xx.charAt(j);
                        j++;
                    }
                    if(!nuevoAutomata.contains(matGramatica[i][0]+"-->"+x1+"<"+ner+ve[1])){//se agrego
                        nuevoAutomata=nuevoAutomata+matGramatica[i][0]+"-->"+x1+"<"+ner+ve[1]+"\n";
                    }                    
                    String OtraParte = "<"+ner+ve[1];
                    String nuevo="";
                    String concatenable="";                    
                    while(OtraParte.length()!=3){
                       String xxx="";
                       nuevo=OtraParte.replace(OtraParte.charAt(0), OtraParte.charAt(1));
                       nuevo= this.remplazarCaracterPosicion('<', 1, nuevo);
                       concatenable=concatenable+OtraParte+"-->"+nuevo+"\n";
                        for (int k = 1; k < nuevo.length(); k++) {
                            xxx= xxx+nuevo.charAt(k);                            
                        }
                        OtraParte=xxx;
                        
                    }
                    if(!nuevoAutomata.contains(concatenable)){
                        nuevoAutomata=nuevoAutomata+concatenable;
                    }                    
                }
            }else if(!pasa.equals("¬")&&pasa.length()!=3){
                if(!nuevoAutomata.contains(matGramatica[i][0]+"-->"+pasa+"<null>")){
                    nuevoAutomata=nuevoAutomata+matGramatica[i][0]+"-->"+pasa+"<null>"+"\n";
                }                
                bandera=true;
            }else if(pasa.length()==3){
                casoespecial=matGramatica[i][0]+"-->"+matGramatica[i][1]+"\n";
            }else{
                if(!nuevoAutomata.contains(matGramatica[i][0]+"-->"+pasa)){//Se agrego
                    nuevoAutomata=nuevoAutomata+matGramatica[i][0]+"-->"+pasa+"\n";
                }                
            }
        }        
        if(bandera==true){
            if(!nuevoAutomata.contains("<null>-->¬")){
                nuevoAutomata=nuevoAutomata+"<null>-->¬"+"\n";
            }
            
        }
        String ml="";
        String[][] mientras= this.StringaMatriz(nuevoAutomata);
        if(!casoespecial.equals("")){
            String[] caso= casoespecial.split("\n");
            for (int i = 0; i < caso.length; i++) {
                String[] caso1 =caso[i].split("-->");
                String p = caso1[1];
                String p1 = caso1[0]; 
                for (int k = 0; k < mientras.length; k++) {
                    if(mientras[k][0].equals(p)){
                        if(!nuevoAutomata.contains(p1+"-->"+mientras[k][1])){
                            nuevoAutomata=nuevoAutomata+p1+"-->"+mientras[k][1]+"\n";
                        }
                    }                    
                }  
            }
        }
        
      return nuevoAutomata;          
    }
//================================Metodo que genera vector con los no terminales de una gramatica=======================================================================================================================   
    public String[] vecnoTerminales(String[][] matGramatica, String[] vec1){ 
        
        String conc="";
        for (int i = 0; i < matGramatica.length; i++) {
            String ns=matGramatica[i][0];
            if(conc.contains(ns)==false){
                conc=conc+"/"+ns;
            }           
        }
        vec1=conc.split("/");
        return vec1;
    }
 //====================================metodo que reemplaza un caracter de un String en una posicion dada========================================================================================================================================   
    public String remplazarCaracterPosicion(char x, int pos, String cadena){ 
    
        String n="";
        for (int i = 0; i < cadena.length(); i++) {
            if(i!=pos){
               n=n+cadena.charAt(i);
            }else{
                n=n+x;
            }
        }
        return n;
    }
//========================Metodo que pasa el String generado cuando se convierte de forma lineal a forma especial==================================================================================================================================    
    public String[][] StringaMatriz(String gramtica){  
    
        String[] vect=gramtica.split("\n");
        String[][] matgramatica = new String[vect.length][2];
        
        for (int i = 0; i < matgramatica.length; i++) {
            String[] bs=vect[i].split("-->");
            matgramatica[i][0]=bs[0];
            matgramatica[i][1]=bs[1];
        }
        return matgramatica;
    }
//========================Matriz que genera un vector con los no terminales de una gramatica==========================================================================================================================    
    public String[] vectorTerminales(String[][] matGramatica, String[] vec1){ 
        
        String conc="";
        for (int i = 0; i < matGramatica.length; i++) {
            String ns=matGramatica[i][1];            
            if(!ns.equals("¬")){
                if(conc.contains(Character.toString(ns.charAt(0)))==false){
                    conc=conc+"/"+ns.charAt(0);
                }
            }
        }
        vec1=conc.split("/");
        return vec1;
    }
//====================================Metodo para llenar el automata.========================================================================================================================
    public String[][] Automata(String[]vecnoTerminales, String[]vecTerminales, String[][]matGramatica, String automata[][]){
    
        automata = new String[vecnoTerminales.length][vecTerminales.length+1];
        String[] pasajero = new String[3];
        int y=1;
        for (int i = 0; i < automata.length; i++) {
            for (int j = 0; j < vecTerminales.length; j++) { //lleno todo el automata con la simbologia de una estrin vacio
                automata[i][j]="";
            }
        }
        for (int i = 0; i < vecnoTerminales.length; i++) { //asigno las no terminates a las primeras columnas de cada fila
            if(!vecnoTerminales[i].equals("")){
                automata[y][0]=vecnoTerminales[i];
                y++;
            }            
        }
        y=1;
        for (int i = 0; i < vecTerminales.length; i++) {//aigno las terminales a las columnas de la primera fila
            if(!vecTerminales[i].equals("")){
                automata[0][y]=vecTerminales[i];
                y++;
            }            
        }
        for (int i = 0; i < automata.length; i++) { //lleno la columna de aceptcion negacion con ceros
            automata[i][vecTerminales.length]="0";
        }          
        automata[0][vecTerminales.length]="ace/neg";
        automata[0][0]="";
        
        for (int i = 0; i < matGramatica.length; i++) {
            pasajero=this.llenarAutomata(matGramatica, i);
            int fi=0;
            int col=0;
            for (int j = 1; j < automata.length; j++) {
                if(pasajero[0].equals(automata[j][0])){
                    fi=j;
                    break;
                }
            }
            if(!pasajero[1].equals("¬")){
                for (int j = 1; j < vecTerminales.length; j++) {
                    if(pasajero[1].equals(automata[0][j])){
                        col=j;
                        break;
                    }
                }
                if(automata[fi][col].equals("")){
                    automata[fi][col]=pasajero[2];
                }else{
                    automata[fi][col]=automata[fi][col]+","+pasajero[2];
                }                
            }else{
                automata[fi][vecTerminales.length]="1";
            }
        }
        int z = this.cantidadcolumnas(automata);
        for (int i = 1; i < automata.length; i++) {
            for (int j = 0; j < z; j++) {
                if(automata[i][j].equals("")){
                    automata[i][j]="error";
                }
            }
        }
        return automata;
    }
//===============================Metodo para separar los elementos de una produccion=====================================================================================================================================
    public String[] llenarAutomata(String matgramtica[][], int fila){ 
    
        String[] per= new String[3];        
        per[0]= matgramtica[fila][0];
        if(!matgramtica[fila][1].equals("¬")){
            String[] mi = matgramtica[fila][1].split("<");
            per[1]=mi[0];
            per[2]="<"+mi[1];
        }else{
            per[1]="¬";
            per[2]="";
        }        
      return per;  
    }
//=================================Metodo para mostrar en un String la matriz generada====================================================================================================================================
    public String mostrarAutomata(String[][] matautomata){ 
        
        String matriz="";
        int colunmas=0, c=0;
        
        colunmas = this.cantidadcolumnas(matautomata);
        if(matautomata[0][0].equals("")){
            matriz = matriz+"----";
        }
        for (int i = 0; i < matautomata.length; i++) {
            for (int j = 0; j <= colunmas; j++) {                                
                if(!matautomata[i][j].equals("")){                    
                    if(j==colunmas){
                        matriz = matriz+matautomata[i][j];  
                    }else{
                        matriz = matriz+matautomata[i][j]+"--";
                    } 
                }              
            }
            matriz= matriz+"\n";            
        }
        return matriz;
    }
//===============================Metodo para saber si el Automata es Deterministico o no lo es=================================================================================================================
    public boolean tipoAutomata(String[][] matGramatica){
        
        boolean tipo=true;
        int y = this.cantidadcolumnas(matGramatica);
        for (int i = 0; i < matGramatica.length; i++) {
            for (int j = 0; j < y; j++) {
                if(matGramatica[i][j].contains(",")){
                    tipo=false;
                }
            }
        } 
        return tipo;
    }
//=================================================Metodo para saber la cantidad de columnas=============================================================================   
    public int cantidadcolumnas(String[][] matGrmatica){
    
        int j=0;
        int cantidad =0;
        while (!matGrmatica[0][j].equals("ace/neg")) {
            cantidad++;
            j++;
        }
        return cantidad;
    }
//===================================Metodo que convierte el automata no deterministico a deterministico=======================================================================================
    public String[][] noDeterministicoAdeterministico(String[][] matGramatica, String[] terminales){
    
        int posicionVectorDec=0;
        String[] vecDeterministico = new String[matGramatica.length+10];
        int y = this.cantidadcolumnas(matGramatica);
        for (int i = 0; i < vecDeterministico.length; i++) {
            vecDeterministico[i]="";
        }
        for (int i = 0; i < y; i++) {            
            boolean esta = this.EstadoRepetido(vecDeterministico, matGramatica[1][y]);
            if(esta==true&&!matGramatica[1][i].equals("error")){
                vecDeterministico[posicionVectorDec]=matGramatica[1][i];
                posicionVectorDec++;
            }            
        }
        
        for (int i = 1; i < vecDeterministico.length; i++) {
            if(vecDeterministico[i].contains(",")){
                int[] vec= this.buscarPosicionConcoma(vecDeterministico[i], matGramatica);
                String[] pp = new String[y-1];
                for (int k = 0; k < pp.length; k++) {
                    pp[k]="";
                }
                for (int j = 0; j < vec.length; j++) {
                    int fi = vec[j];  
                    for (int k = 1; k < y; k++) { //posiblmente saque error entonces manejar eso del y o y-1 mejor
                        if(!matGramatica[fi][k].equals("error")){
                            if(pp[k-1].equals("")){
                                pp[k-1]=matGramatica[fi][k];
                            }else{
                                pp[k-1]=pp[k-1]+","+matGramatica[fi][k];
                            }
                        }
                    }       
                }
                for (int j = 0; j < pp.length; j++) {
                    boolean esta = this.EstadoRepetido(vecDeterministico, pp[j]);
                    if(esta==true){
                        vecDeterministico[posicionVectorDec]=pp[j];
                        posicionVectorDec++;
                    }                      
                }                
            }else{
                int fila=this.buscarPosicionSinComa(matGramatica, vecDeterministico[i]);
                for (int j = 1; j < y; j++) {
                    boolean esta = this.EstadoRepetido(vecDeterministico, matGramatica[fila][j]);
                    if((esta==true&&!matGramatica[fila][j].equals("error"))&&!vecDeterministico[i].equals("")){
                        vecDeterministico[posicionVectorDec]=matGramatica[fila][j];
                        posicionVectorDec++;
                    }
                }
            }
        }
        int filasmatriz=1;
        for (int i = 0; i < vecDeterministico.length; i++) {
            if(!vecDeterministico[i].equals("")){
                filasmatriz++;
            }
        }
        String[][] matrizDeterministica= new String[filasmatriz][y+1];
        matrizDeterministica[0][0]="error";
        matrizDeterministica[0][y]="ace/neg";
        for (int i = 1; i < terminales.length; i++) {
            matrizDeterministica[0][i]=terminales[i];
        }
        for (int i = 0; i < matrizDeterministica.length; i++) {
            String[] matrizDeterministica1 = matrizDeterministica[i];            
        }
        int posfila = 1;
        for (int i = 0; i < matrizDeterministica.length; i++) {
            if(!vecDeterministico[i].equals("")){
                if(vecDeterministico[i].contains(",")){
                int[] vec= this.buscarPosicionConcoma(vecDeterministico[i], matGramatica);
                String[] pp = new String[y];
                for (int k = 0; k < pp.length; k++) {
                    pp[k]="";
                }
                for (int j = 0; j < vec.length; j++) {
                    int fi = vec[j];  
                    for (int k = 1; k <= y; k++) { //posiblmente saque error entonces manejar eso del y o y-1 mejor
                        if(k==y){
                            if(!pp[k-1].equals("")){
                                if(matGramatica[fi][k].equals("1")&&pp[k-1].equals("1")){
                                    pp[k-1]="1";
                                }else if(matGramatica[fi][k].equals("1")&&pp[k-1].equals("0")){
                                    pp[k-1]="1";
                                }else if(matGramatica[fi][k].equals("0")&&pp[k-1].equals("1")){
                                pp[k-1]="1";
                                }else if(matGramatica[fi][k].equals("0")&&pp[k-1].equals("0")){
                                    pp[k-1]="0";
                                }
                            }else{
                                pp[k-1]=matGramatica[fi][k];
                            }
                        }else if(!matGramatica[fi][k].equals("error")){
                            if(pp[k-1].equals("")){
                                pp[k-1]=matGramatica[fi][k];
                            }else{
                                pp[k-1]=pp[k-1]+","+matGramatica[fi][k];
                            }
                        }
                    }       
                }                 
                matrizDeterministica[posfila][0]= vecDeterministico[i];
                for (int j = 1; j <= pp.length; j++) {
                    matrizDeterministica[posfila][j]=pp[j-1];
                }
                //faltaria ponerle el ultimo coso del final
                posfila++;
                }else{
                    int ml = this.buscarPosicionSinComa(matGramatica, vecDeterministico[i]);
                    for (int k = 0; k <= y; k++) {
                        matrizDeterministica[posfila][k]=matGramatica[ml][k];                        
                    }
                    posfila++;
                }                
            }            
        }
        
        
       return matrizDeterministica; 
    }
//=========================Metodo para saber si un esatdo es repetido============================================================================================================================    
    public boolean EstadoRepetido(String estados[], String estado){
        
       boolean respuesta=true;
        for (int i = 0; i < estados.length; i++) {
            if(estados[i].equals(estado)){
                respuesta=false;
                break;
            }            
        }
        
        return respuesta;
    }
//===================Metodo para buscar la posicion en la fila de un string sin coma========================================================================================================
    public int buscarPosicionSinComa(String mat[][], String valor){
    
        int fila=0;
        for (int i = 0; i < mat.length; i++) {
            if(mat[i][0].equals(valor)){
                fila=i;
                break;
            }
        }
        return fila;
    }
//=======================Metodo para buscar la posicion en la fila de un string con coma=====================================================================================================
    public int[] buscarPosicionConcoma(String valor, String[][] mat){
    
        String[] valores = valor.split(",");
        int[] Pocisiones= new int[valores.length];        
        int posv=0;
        for (int i = 0; i < valores.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if(mat[j][0].equals(valores[i])){
                    Pocisiones[posv]=j;
                    posv++;
                }
            }
        }
        
        return Pocisiones;
    }
//=========================Metodo para reconocer na secuencia============================================================================================================
    public boolean Reconocedor(String cadena, String vec[][]){
    
        boolean resultado=true;
        int columnas = this.cantidadcolumnas(vec);
        int tr =0;
        int fi=0, co=0;
        String na=vec[1][0];
        while(tr<cadena.length()){
            char x=cadena.charAt(tr);
            for (int i = 1; i < columnas; i++) {
                if(vec[0][i].charAt(0)==x){
                    co=i;
                    break;
                }                
            }
            for (int j = 0; j < vec.length; j++) {
                if(vec[j][0].equals(na)){
                    fi=j;
                    break;
                }
            }
            if(vec[fi][co].equals("error")){
                return false;            
            }else{
                na=vec[fi][co];
                tr++;
            }
        }
        if(vec[fi][columnas].equals("1")){
            resultado=true;
        }else{
            resultado=false;
        }        
        return resultado;
    }
 //================Metodo para saber si la cadena es posible ser reconocida o si no============================================================================================================================   
    public boolean cadena(String cadena, String[] term){
    
        boolean nn=false;
        for (int i = 0; i < cadena.length(); i++) {
            nn=false;
            char x=cadena.charAt(i);
            for (int j = 1; j < term.length; j++) {
                if(x==term[j].charAt(0)){
                    nn=true;
                    break;                    
                }
            }
        }
        return nn;
    }
}