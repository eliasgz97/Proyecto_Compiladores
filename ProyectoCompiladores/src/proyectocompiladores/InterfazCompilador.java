package proyectocompiladores;

import java.awt.Color;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import comprobacionTipos.TablaSimbolos;

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
        jScrollPane3 = new javax.swing.JScrollPane();
        jtxtarea_salida_sintactico = new javax.swing.JTextArea();
        jp_codigogenerado = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jbt_analizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jb_arbol = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtarea_entrada = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        jd_compilador.setBackground(new java.awt.Color(0, 0, 51));

        jLabel2.setFont(new java.awt.Font("Eras Bold ITC", 2, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("ADA 95");

        jTabbedPane2.setBackground(new java.awt.Color(0, 0, 51));

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

        jtxtarea_salida_sintactico.setColumns(20);
        jtxtarea_salida_sintactico.setRows(5);
        jScrollPane3.setViewportView(jtxtarea_salida_sintactico);

        javax.swing.GroupLayout jp_analizadorsintacticoLayout = new javax.swing.GroupLayout(jp_analizadorsintactico);
        jp_analizadorsintactico.setLayout(jp_analizadorsintacticoLayout);
        jp_analizadorsintacticoLayout.setHorizontalGroup(
            jp_analizadorsintacticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
        jp_analizadorsintacticoLayout.setVerticalGroup(
            jp_analizadorsintacticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
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
            .addGap(0, 376, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Generación de Código", jp_codigogenerado);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setText("Compilador ");

        jLabel13.setFont(new java.awt.Font("Eras Bold ITC", 2, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("ADA 95");

        jLabel15.setFont(new java.awt.Font("Eras Bold ITC", 2, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 102));
        jLabel15.setText("ADA 95");

        javax.swing.GroupLayout jd_compiladorLayout = new javax.swing.GroupLayout(jd_compilador.getContentPane());
        jd_compilador.getContentPane().setLayout(jd_compiladorLayout);
        jd_compiladorLayout.setHorizontalGroup(
            jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_compiladorLayout.createSequentialGroup()
                .addGroup(jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_compiladorLayout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel2))
                    .addGroup(jd_compiladorLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jd_compiladorLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(jLabel11)
                    .addContainerGap(470, Short.MAX_VALUE)))
            .addGroup(jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jd_compiladorLayout.createSequentialGroup()
                    .addGap(286, 286, 286)
                    .addComponent(jLabel13)
                    .addContainerGap(341, Short.MAX_VALUE)))
            .addGroup(jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_compiladorLayout.createSequentialGroup()
                    .addContainerGap(335, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addGap(292, 292, 292)))
        );
        jd_compiladorLayout.setVerticalGroup(
            jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_compiladorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jd_compiladorLayout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jLabel11)
                    .addContainerGap(460, Short.MAX_VALUE)))
            .addGroup(jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jd_compiladorLayout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(jLabel13)
                    .addContainerGap(467, Short.MAX_VALUE)))
            .addGroup(jd_compiladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jd_compiladorLayout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(jLabel15)
                    .addContainerGap(447, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jbt_analizar.setText("||>");
        jbt_analizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_analizarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 2, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(173, 28, 28));
        jLabel1.setText("95");

        jButton1.setText("Limpiar ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jb_arbol.setText("Mostrar Árbol");
        jb_arbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_arbolActionPerformed(evt);
            }
        });

        jtxtarea_entrada.setColumns(20);
        jtxtarea_entrada.setRows(5);
        jScrollPane2.setViewportView(jtxtarea_entrada);

        jButton3.setText("Generar Jflex y Cup");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ADA");

        jLabel5.setFont(new java.awt.Font("Eras Bold ITC", 2, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(173, 28, 28));
        jLabel5.setText("95");

        jLabel7.setFont(new java.awt.Font("Eras Bold ITC", 2, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(173, 28, 28));
        jLabel7.setText("95");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(173, 28, 28));
        jLabel8.setText("95");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ADA");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ADA");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Daniel Rodríguez");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Elías Girón");

        jButton2.setText("Tabla de Símbolos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Mario Henríquez");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(76, 76, 76)
                        .addComponent(jButton1)
                        .addGap(33, 33, 33)
                        .addComponent(jb_arbol)
                        .addGap(46, 46, 46)
                        .addComponent(jButton3)
                        .addGap(103, 103, 103)
                        .addComponent(jbt_analizar)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(124, 124, 124)
                    .addComponent(jLabel5)
                    .addContainerGap(854, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(144, 144, 144)
                    .addComponent(jLabel7)
                    .addContainerGap(834, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(144, 144, 144)
                    .addComponent(jLabel8)
                    .addContainerGap(856, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jLabel6)
                    .addContainerGap(922, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jLabel10)
                    .addContainerGap(902, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel1)
                        .addComponent(jButton1)
                        .addComponent(jb_arbol)
                        .addComponent(jButton3)
                        .addComponent(jbt_analizar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton2)))
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jLabel5)
                    .addContainerGap(594, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(jLabel7)
                    .addContainerGap(574, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(jLabel8)
                    .addContainerGap(583, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jLabel6)
                    .addContainerGap(596, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(jLabel10)
                    .addContainerGap(576, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbt_analizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_analizarActionPerformed
        jd_compilador.pack();
        jd_compilador.setVisible(true);
        jd_compilador.setLocationRelativeTo(null);
        String result = AnalizarSintaxis();
        try {
            jtxtarea_salida.setText(Analizar());
            jtxtarea_salida_sintactico.setText(result);

        } catch (IOException ex) {
            Logger.getLogger(InterfazCompilador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbt_analizarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String path = new File("").getAbsolutePath();
            String pathCup = new File("").getAbsolutePath();
            String pathSintax = new File("").getAbsolutePath();
            path = path.concat("/src/proyectocompiladores/LexerAda.flex");
            pathCup = pathCup.concat("/src/proyectocompiladores/AdaLexerCup.flex");
            pathSintax = pathSintax.concat("/src/proyectocompiladores/AdaSyntax.cup");
            String[] rutaS = {"-parser", "Sintax", pathSintax};
            generarCup(path, pathCup, rutaS);
        } catch (Exception ex) {
            Logger.getLogger(InterfazCompilador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jb_arbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_arbolActionPerformed
        try {
            graficar(sintactico.padre);
            File f1 = new File("image.png");
            Desktop dt = Desktop.getDesktop();
            dt.open(f1);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }//GEN-LAST:event_jb_arbolActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jtxtarea_entrada.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println("-----------------comprobacion de tipos----------------");
        recorrer(sintactico.padre);
    }//GEN-LAST:event_jButton2ActionPerformed

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
//        try {
//            String ruta = "C:/Users/EliasGZ/Documents/Clases/Compiladores/Proyecto_Compiladores/ProyectoCompiladores/src/proyectocompiladores/LexerCup.flex";
//            String[] rutaCup = {"C:/Users/EliasGZ/Documents/Clases/Compiladores/Proyecto_Compiladores/ProyectoCompiladores/src/proyectocompiladores/AdaSyntax.cup"};
//            //generarCup(rutaCup);
//            generarJFlex(ruta);
//        } catch (Exception ex) {
//            Logger.getLogger(InterfazCompilador.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public static void generarJFlex(String ruta) {
        File lexer = new File(ruta);
        jflex.Main.generate(lexer);
    }

    public static void generarCup(String path, String pathCup, String[] rutaCup) throws IOException, Exception {
        String absolutesym = new File("").getAbsolutePath();
        String absolutesin = new File("").getAbsolutePath();
        String absolute = new File("").getAbsolutePath();
        absolutesym = absolutesym.concat("/src/proyectocompiladores/sym.java");
        absolutesin = absolutesin.concat("/src/proyectocompiladores/Sintax.java");
        File file;
        file = new File(path);
        jflex.Main.generate(file);
        file = new File(pathCup);
        jflex.Main.generate(file);
        java_cup.Main.main(rutaCup);
        Path rutaSym = Paths.get(absolutesym);
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        String slash = (System.getProperty("os.name").equals("Mac OS X")
                || System.getProperty("os.name").equals("Linux")) ? "/" : "\\";

        Files.move(Paths.get(absolute + slash + "sym.java"),
                Paths.get(absolutesym));

        Path rutaSin = Paths.get(absolutesin);
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(Paths.get(absolute + slash + "Sintax.java"),
                Paths.get(absolutesin));
    }

    public String Analizar() throws IOException {
        Reader reader;
        String text = "";
        reader = new BufferedReader(new StringReader(jtxtarea_entrada.getText()));
        Lexer lexer = new Lexer(reader);
        boolean bandera = true;
        boolean error = false;
        while (true) {
            Token token = lexer.yylex();
            if (token == null) {
                return text;
            }
            switch (token) {
                case ESPACIO:
                    if (bandera == true) {
                        text += "<" + token + ">\n";
                        bandera = false;
                    }
                    break;
                case ID:
                    text += "<" + token + ", " + lexer.yytext() + ">\n";
                    break;
                case TIPOVARIABLE:
                    text += "<" + token + ", " + lexer.yytext() + ">\n";
                    break;
                case ERROR:
                    JOptionPane.showMessageDialog(this, "Error léxico, intente de nuevo");
                default:
                    text += "<" + token + "> \n";
                    bandera = true;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton jb_arbol;
    private javax.swing.JButton jbt_analizar;
    private javax.swing.JDialog jd_compilador;
    private javax.swing.JPanel jp_analizadorlexico;
    private javax.swing.JPanel jp_analizadorsintactico;
    private javax.swing.JPanel jp_codigogenerado;
    private javax.swing.JTextArea jtxtarea_entrada;
    private javax.swing.JTextArea jtxtarea_salida;
    private javax.swing.JTextArea jtxtarea_salida_sintactico;
    // End of variables declaration//GEN-END:variables
AdaLexerCup lexico;
    Sintax sintactico;

    private String AnalizarSintaxis() {
        lexico = new AdaLexerCup(new BufferedReader(new StringReader(jtxtarea_entrada.getText())));
        String resultado = "";
        try {
            sintactico = new Sintax(lexico);
            sintactico.parse();
            if (sintactico.errores.isEmpty()) {
                jb_arbol.setEnabled(true);
                resultado += "Compilado exitosamente";
                jtxtarea_salida_sintactico.setForeground(Color.GREEN);
            }
            if (!sintactico.errores.isEmpty()) {
                for (int i = 0; i < sintactico.errores.size(); i++) {
                    resultado += sintactico.errores.get(i) + "\n ";
                }

                jtxtarea_salida_sintactico.setForeground(Color.red);
                jb_arbol.setEnabled(false);
            }
        } catch (Exception e) {
        }
        return resultado;

    }

    public void graficar(Nodo raiz) {
        FileWriter archivo = null;
        PrintWriter pw = null;
        String cadena = graficarNodo(raiz);
        try {
            archivo = new FileWriter("arbol.dot");
            pw = new PrintWriter(archivo);
            pw.println("digraph G {node[shape=box, style=filled, color=blanchedalmond]; edge[color=chocolate3];rankdir=UD \n");
            pw.println(cadena);
            pw.println("\n}");
            archivo.close();
        } catch (Exception e) {
            System.out.println(e + " 1");
        }

        try {
            String cmd = "dot arbol.dot -Tpng -o image.png";
            Runtime.getRuntime().exec(cmd);
            System.out.println(Runtime.getRuntime().exec(cmd));
            System.out.println("SE GENEROOOOO LA IMAGGEN");
        } catch (IOException ioe) {
            System.out.println(ioe + " 2");
        }

    }

    public String graficarNodo(Nodo nodo) {
        String cadena = "";
        System.out.println("NNNNNODOOOO NOMBRE::::::::: " + nodo.getNombre());
        for (Nodo hijos : nodo.getHijos()) {
            cadena += "\"" + nodo.getNumNodo() + "_" + nodo.getNombre() + " -> " + nodo.getValor() + "\"->\"" + hijos.getNumNodo() + "_" + hijos.getNombre() + " -> " + hijos.getValor() + "\"\n";
            cadena += graficarNodo(hijos);
        }
        return cadena;
    }

    public void recorrer(Nodo padre) {
        for (Nodo hoja : padre.getHijos()) {
            if (hoja.getNombre().equals("variables")) {
                String id, tipo, valor;
                tipo = hoja.getHijos().get(1).getValor();
                //agregar a tabla de símbolos
                System.out.println(tipo);
                if (hoja.getHijos().size() > 2) {
                    valor = hoja.getHijos().get(2).getValor();
                    //agregar a tabla de símbolos
                    System.out.println(valor);
                } else {
                    valor = "null";
                    //agregar a tabla de símbolos
                    System.out.println(valor);
                }
                if (hoja.getHijos().get(0).getNombre().equals("id")) {
                    id = hoja.getHijos().get(0).getValor();
                    //agregar a tabla de símbolos
                    System.out.println(id);
                } else if (hoja.getHijos().get(0).getNombre().equals(",")) {
                    recorrerRepeticion(hoja.getHijos().get(0), valor, tipo);
                }
            }
            recorrer(hoja);
        }
    }

    public void recorrerRepeticion(Nodo padre, String valor, String tipo) {
        if (padre.getHijos().get(1).getNombre().equals(",")) {
            String rep_id;
            rep_id = padre.getHijos().get(0).getValor();
            System.out.println(rep_id);
            //agregar a tabla de símbolos
            recorrerRepeticion(padre.getHijos().get(1), valor, tipo);
        } else if (padre.getHijos().get(1).getNombre().equals("id")) {
            String rep_id;
            rep_id = padre.getHijos().get(0).getValor();
            System.out.println(rep_id);
            //agregar a tabla de símbolos
            rep_id = padre.getHijos().get(1).getValor();
            System.out.println(rep_id);
            //agregar a tabla de símbolos
        }
    }
}
