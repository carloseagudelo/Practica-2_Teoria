/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class EntradaGramatica extends javax.swing.JFrame {

    /**
     * Creates new form EntradaGramatica
     */
    public EntradaGramatica() {
        initComponents();
        this.txtResultado.setVisible(false);        
    }

    Metodos mtd = new Metodos();    
    String[][] matGramatica;
    int bandera;
    String nvo;
    String[] terminales;
    String[] noTerminales;
    String[][] matGramaticanuevo;
    String[][] Automata;
    String[][] matnodetAdet;
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtgramatica = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtnumProd = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setText("ENTRADA DE GRAMATICA");

        txtgramatica.setColumns(20);
        txtgramatica.setRows(5);
        jScrollPane1.setViewportView(txtgramatica);

        jButton1.setText("INGRESAR GRAMTICA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("INGRESE EL NUMERO DE PRODUCCIONES:");

        txtnumProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnumProdActionPerformed(evt);
            }
        });

        jButton2.setText("CONSULTAR TIPO DE GRAMATICA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane2.setViewportView(txtResultado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtnumProd, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnumProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        int num = Integer.parseInt(txtnumProd.getText());
        matGramatica = new String[num][2];        
        
        int i =0;
        while ( i < num ) {
            
            String noterminal = JOptionPane.showInputDialog("Ingrese el no terminal correspondiennte a lado izquierdo de la produccion numero: "+ i +" entre <>");                                                                                                                                            
            //Validacion del dato de entrada el lado derecho izquierdo de una produccion
            boolean tr = true;                    
            while(tr==true){
                if(noterminal.length()>0){
                    if(noterminal.charAt(0)=='<'){
                        if(noterminal.charAt(1)!='>'){
                            if(noterminal.contains(">")){
                                matGramatica[i][0]=noterminal;
                                tr=false;
                            }else{
                                JOptionPane.showMessageDialog(rootPane, "Estas ingresando un no terminal de la manera incorrecta");
                                noterminal= JOptionPane.showInputDialog("Ingrese en no terminal correspondiennte a lado izquierdo de la produccion numero");
                            }
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Estas ingresando un no terminal de la manera incorrecta");
                            noterminal= JOptionPane.showInputDialog("Ingrese en no terminal correspondiennte a lado izquierdo de la produccion numero");
                        }
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Estas ingresando un no terminal de la manera incorrecta");
                        noterminal= JOptionPane.showInputDialog("Ingrese en no terminal correspondiennte a lado izquierdo de la produccion numero");
                    }            
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Esta ingresando un terminal de la manera incorrcta");
                    noterminal= JOptionPane.showInputDialog("Ingrese en no terminal correspondiennte a lado izquierdo de la produccion numero");
                }
            }
            tr=true;
            String gramatica = JOptionPane.showInputDialog("Ingrese la parte de la produccion correspondiente al lado dereco de la producion numero: "+i+" si desea ingrsar algun no terminal debe hacerlo entre <>");            
            //validacion del lado derecho de la produccion.            
            while(tr==true){
                if(gramatica.contains("<")){
                    int y = gramatica.indexOf('<');
                    if(y!=gramatica.length()){
                        if(gramatica.charAt(y+1)!='>'){
                            if(gramatica.contains(">")){
                                matGramatica[i][1]=gramatica;
                                tr=false;
                            }else{
                                JOptionPane.showMessageDialog(rootPane, "Esta ingresando mal el lado derecho de una produccion de la manera incorrcta");
                                gramatica = JOptionPane.showInputDialog("Ingrese la parte de la produccion correspondiente al lado dereco de la producion numero: "+i+" si desea ingrsar algun no terminal debe hacerlo entre <>");
                            }
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Esta ingresando mal el lado derecho de una produccion de la manera incorrcta");
                            gramatica = JOptionPane.showInputDialog("Ingrese la parte de la produccion correspondiente al lado dereco de la producion numero: "+i+" si desea ingrsar algun no terminal debe hacerlo entre <>");
                        }
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Esta ingresando mal el lado derecho de una produccion de la manera incorrcta");
                        gramatica = JOptionPane.showInputDialog("Ingrese la parte de la produccion correspondiente al lado dereco de la producion numero: "+i+" si desea ingrsar algun no terminal debe hacerlo entre <>");
                    }
                }else if(gramatica.contains(">")){
                    JOptionPane.showMessageDialog(rootPane, "Esta ingresando mal el lado derecho de una produccion de la manera incorrcta");
                    gramatica = JOptionPane.showInputDialog("Ingrese la parte de la produccion correspondiente al lado dereco de la producion numero: "+i+" si desea ingrsar algun no terminal debe hacerlo entre <>");
                }else{
                    matGramatica[i][1]=gramatica;
                    tr=false;
                }
            }            
            String produccion = i+")"+noterminal+"-->"+gramatica;            
            txtgramatica.setText(txtgramatica.getText()+produccion+"\n");
            i++;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnumProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumProdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        String rr;
        int confirmacion=2;
        bandera = mtd.tipoGramatica(matGramatica);
        if(bandera==0){
            confirmacion= JOptionPane.showConfirmDialog(rootPane, "La gramatica ingresada es la forma especial\n desea continuar con el proceso, este es construir el Automata que reconozca la secuencia\n ¿Desea continuar?");
            if(confirmacion==0){
                terminales=mtd.vectorTerminales(matGramatica, terminales);
                noTerminales=mtd.vecnoTerminales(matGramatica, noTerminales);
                Automata= mtd.Automata(noTerminales, terminales, matGramatica, Automata);
                this.txtResultado.setVisible(true);
                rr=mtd.mostrarAutomata(Automata);
                txtResultado.setText(rr);                 
            }else{
                JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                this.dispose();
            }
            boolean xs = mtd.tipoAutomata(Automata);
                if(xs==false){
                    int n= JOptionPane.showConfirmDialog(rootPane, "El automata generado por la gramatica entrada no es deterministico.\nel siguiente paso sera convertir dicho automata a deterministico.\n¿Desea continuar?");
                    if(n==0){
                        Automata =mtd.noDeterministicoAdeterministico(Automata,terminales);
                        rr=mtd.mostrarAutomata(Automata);
                        txtResultado.setText(rr);  
                    }
                }else{
                    confirmacion= JOptionPane.showConfirmDialog(rootPane, "el automata es deterministico desea ingresar alguna cadena para ser validada");                    
                    if(confirmacion==0){
                        String conca="";
                        for (int i = 0; i < terminales.length; i++) {
                            if(i==0){
                                conca=terminales[i];
                            }else{
                                conca=conca+","+terminales[i];
                            }                            
                        }
                        String sec= JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);                        
                        boolean y = mtd.cadena(sec, terminales);
                        if(y==true){
                            int con=0;
                            while(con==0){
                            boolean t =  mtd.Reconocedor(sec, Automata);
                                if(t==true){
                                    txtResultado.setText("Se acepta la secuencia");
                                    con =JOptionPane.showConfirmDialog(null, "Desea ingresar otra secuencia");
                                    if(con==0){
                                        sec=JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                                        this.dispose();
                                    }
                                }else{
                                    txtResultado.setText("Se rechaza la secuencia");
                                    con= JOptionPane.showConfirmDialog(null, "Desea ingresar otra secuencia");
                                    if(con==0){
                                        sec=JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                                        this.dispose();
                                    }
                                }
                            }
                        }else{
                            int con=0;
                            while(con==0){
                                con= JOptionPane.showConfirmDialog(null, "Ud ha ingeresado una cadena invalida desea ingresar otra");
                                if(con==0){
                                    sec= JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);
                                    y = mtd.cadena(sec, terminales);
                                    if(y==true){                            
                                        while(con==0){
                                             boolean t =  mtd.Reconocedor(sec, Automata);
                                            if(t==true){
                                                txtResultado.setText("Se acepta la secuencia");
                                                con =JOptionPane.showConfirmDialog(null, "Desea ingresar otra secuencia");
                                                if(con==0){
                                                    sec=JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);
                                                }else{
                                                    JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                                                    this.dispose();
                                                }                                                
                                            }else{
                                                txtResultado.setText("Se rechaza la secuencia");
                                                con= JOptionPane.showConfirmDialog(null, "Desea ingresar otra secuencia");
                                                if(con==0){
                                                    sec=JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);
                                                }else{
                                                    JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                                                    this.dispose();
                                                }
                                            }
                                        }
                                        }else{
                                            con=JOptionPane.showConfirmDialog(null,"Ud ha ingeresado una cadena invalida desea ingresar otra");
                                        }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                                    this.dispose();
                                }
                            }                        
                        }
                }
            }
        }else if(bandera==1){
            confirmacion = JOptionPane.showConfirmDialog(rootPane, "La gramatica ingresada es de la forma Lineal por la Derecha\n El siguiente paso es convertir esta gramatica a la forma Especial\n ¿Desea continuar? ");           
            if(confirmacion==0){
                nvo= mtd.convertirGramatica(matGramatica);
                this.txtResultado.setVisible(true);                
                txtResultado.setText(nvo); 
                confirmacion= JOptionPane.showConfirmDialog(rootPane, "La gramatica ha pasado de ser de la forma lineal a la forma especial\n el siguiente paso es construir el automata que reperesenta esta gramatia\n ¿Desea continuar?");
                if(confirmacion==0){
                    matGramaticanuevo=mtd.StringaMatriz(nvo);
                    terminales= mtd.vectorTerminales(matGramaticanuevo, terminales);
                    noTerminales= mtd.vecnoTerminales(matGramaticanuevo, terminales);
                    Automata =mtd.Automata(noTerminales, terminales, matGramaticanuevo, Automata);
                    rr=mtd.mostrarAutomata(Automata);
                    txtResultado.setText(rr);                                        
                }else{
                    JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                    this.dispose();
                }
                boolean xs = mtd.tipoAutomata(Automata);
                    if(xs==false){
                        int n= JOptionPane.showConfirmDialog(rootPane, "El automata generado por la gramatica entrada no es deterministico.\nel siguiente paso sera convertir dicho automata a deterministico.\n¿Desea continuar?");
                        if(n==0){
                            Automata =mtd.noDeterministicoAdeterministico(Automata, terminales);
                            rr=mtd.mostrarAutomata(Automata);
                            txtResultado.setText(rr);  
                        }
                    }
                    confirmacion= JOptionPane.showConfirmDialog(rootPane, "el automata es deterministico desea ingresar alguna cadena para ser validada");
                    if(confirmacion==0){
                        String conca="";
                        for (int i = 0; i < terminales.length; i++) {
                            if(i==0){
                                conca=terminales[i];
                            }else{
                                conca=conca+","+terminales[i];
                            }                            
                        }
                        String sec= JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);                        
                        boolean y = mtd.cadena(sec, terminales);
                        if(y==true){
                            int con=0;
                            while(con==0){
                            boolean t =  mtd.Reconocedor(sec, Automata);
                                if(t==true){
                                    txtResultado.setText("Se acepta la secuencia");
                                    con =JOptionPane.showConfirmDialog(null, "Desea ingresar otra secuencia");
                                    if(con==0){
                                        sec=JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                                        this.dispose();
                                    }
                                }else{
                                    txtResultado.setText("Se rechaza la secuencia");
                                    con= JOptionPane.showConfirmDialog(null, "Desea ingresar otra secuencia");
                                    if(con==0){
                                        sec=JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                                        this.dispose();
                                    }
                                }
                            }
                        }else{
                            int con=0;
                            while(con==0){
                                con= JOptionPane.showConfirmDialog(null, "Ud ha ingeresado una cadena invalida desea ingresar otra");
                                if(con==0){
                                    sec= JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);
                                    y = mtd.cadena(sec, terminales);
                                    if(y==true){                            
                                        while(con==0){
                                             boolean t =  mtd.Reconocedor(sec, Automata);
                                            if(t==true){
                                                txtResultado.setText("Se acepta la secuencia");
                                                con =JOptionPane.showConfirmDialog(null, "Desea ingresar otra secuencia");
                                                if(con==0){
                                                    sec=JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);
                                                }else{
                                                    JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                                                    this.dispose();
                                                }                                                
                                            }else{
                                                txtResultado.setText("Se rechaza la secuencia");
                                                con= JOptionPane.showConfirmDialog(null, "Desea ingresar otra secuencia");
                                                if(con==0){
                                                    sec=JOptionPane.showInputDialog("ingrese la secuencia deseada formada unicamente por los sigientes caracteres: "+conca);
                                                }else{
                                                    JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                                                    this.dispose();
                                                }
                                            }
                                        }
                                        }else{
                                            con=JOptionPane.showConfirmDialog(null,"Ud ha ingeresado una cadena invalida desea ingresar otra");
                                        }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                                    this.dispose();
                                }
                            }                        
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                        this.dispose();
                    }
            }else{
                JOptionPane.showMessageDialog(null, "Como ha decidido no seguir con el proceso el programa se cerrara");
                this.dispose();
            }
        }else if(bandera==2){
            JOptionPane.showMessageDialog(rootPane, "La gramatica gramatica ingresada no es de la forma especial, ni de la forma lineal por la derecha\nla funconalidad del programa no hace nada para este tippo de Diagramas");            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public String[][] getMatGramatica() {
        return matGramatica;
    }

    public void setMatGramatica(String[][] matGramatica) {
        this.matGramatica = matGramatica;
    }

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntradaGramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntradaGramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntradaGramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntradaGramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntradaGramatica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JTextArea txtgramatica;
    private javax.swing.JTextField txtnumProd;
    // End of variables declaration//GEN-END:variables
}
