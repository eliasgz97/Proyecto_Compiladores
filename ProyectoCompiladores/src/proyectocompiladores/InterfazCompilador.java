package proyectocompiladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EliasGZ
 */
public class InterfazCompilador extends javax.swing.JFrame {

    /**
     * Creates new form InterfazCompilador
     */
    public InterfazCompilador() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_compilador = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jp_analizadorlexico = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtarea_salida = new javax.swing.JTextArea();
        jp_analizadorsintactico = new javax.swing.JPanel();
        jp_codigogenerado = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jbt_analizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtarea_entrada = new javax.swing.JTextArea();

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel2.setText("Compilador ADA 95");

        jtxtarea_salida.setColumns(20);
        jtxtarea_salida.setRows(5);
        jScrollPane1.setViewportView(jtxtarea_salida);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jp_analizadorlexicoLayout = new javax.swing.GroupLayout(jp_analizadorlexico);
        jp_analizadorlexico.setLayout(jp_analizadorlexicoLayout);
        jp_analizadorlexicoLayout.setHorizontalGroup(
            jp_analizadorlexicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_analizadorlexicoLayout.setVerticalGroup(
            jp_analizadorlexicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Análisis Léxico", jp_analizadorlexico);

        javax.swing.GroupLayout jp_analizadorsintacticoLayout = new javax.swing.GroupLayout(jp_analizadorsintactico);
        jp_analizadorsintactico.setLayout(jp_analizadorsintacticoLayout);
        jp_analizadorsintacticoLayout.setHorizontalGroup(
            jp_analizadorsintacticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 652, Short.MAX_VALUE)
        );
        jp_analizadorsintacticoLayout.setVerticalGroup(
            jp_analizadorsintacticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Análisis Sintáctico", jp_analizadorsintactico);

        javax.swing.GroupLayout jp_codigogeneradoLayout = new javax.swing.GroupLayout(jp_codigogenerado);
        jp_codigogenerado.setLayout(jp_codigogeneradoLayout);
        jp_codigogeneradoLayout.setHorizontalGroup(
            jp_codigogeneradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 652, Short.MAX_VALUE)
        );
        jp_codigogeneradoLayout.setVerticalGroup(
            jp_codigogeneradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Generación de Código", jp_codigogenerado);

        javax.swing.GroupLayout jd_compiladorLayout = new javax.swing.GroupLayout(jd_compilador.getContentPane());
        jd_compilador.getContentPane().setLayout(jd_compiladorLayout);
        jd_compiladorLayout.setHorizontalGroup(
            jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_compiladorLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jd_compiladorLayout.setVerticalGroup(
            jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_compiladorLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbt_analizar.setText("Run");
        jbt_analizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_analizarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setText("ADA 95");

        jButton1.setText("Comentar");

        jButton2.setText("Mostrar Árbol");

        jtxtarea_entrada.setColumns(20);
        jtxtarea_entrada.setRows(5);
        jScrollPane2.setViewportView(jtxtarea_entrada);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(362, 362, 362)
                        .addComponent(jbt_analizar)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jbt_analizar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbt_analizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_analizarActionPerformed
        jd_compilador.pack();
        jd_compilador.setVisible(true);
        jd_compilador.setLocationRelativeTo(null);
        try {
            jtxtarea_salida.setText(Analizar());
        } catch (IOException ex) {
            Logger.getLogger(InterfazCompilador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbt_analizarActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(InterfazCompilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazCompilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazCompilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazCompilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazCompilador().setVisible(true);
            }
        });
//        String ruta = "C:/Users/EliasGZ/Documents/Clases/Compiladores/Proyecto_Compiladores/ProyectoCompiladores/src/proyectocompiladores/LexerAda.flex";
//        generarJFlex(ruta);
    }

    public static void generarJFlex(String ruta) {
        File lexer = new File(ruta);
        jflex.Main.generate(lexer);
    }

    public String Analizar() throws IOException {
        System.out.println("entra");
        Reader reader;
        String text = "dsfsf";
        reader = new BufferedReader(new StringReader(jtxtarea_entrada.getText()));
        Lexer lexer = new Lexer(reader);
        while (true) {
            Token token = lexer.yylex();
            if (token == null) {
                return text;
            }
            switch (token) {
                case letra:
                    text += "<" + token + "> \n";
                    break;
                default:
                    text += "<" + token + "> \n";
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton jbt_analizar;
    private javax.swing.JDialog jd_compilador;
    private javax.swing.JPanel jp_analizadorlexico;
    private javax.swing.JPanel jp_analizadorsintactico;
    private javax.swing.JPanel jp_codigogenerado;
    private javax.swing.JTextArea jtxtarea_entrada;
    private javax.swing.JTextArea jtxtarea_salida;
    // End of variables declaration//GEN-END:variables
}
