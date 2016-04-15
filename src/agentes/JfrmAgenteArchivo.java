/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

/**
 *
 * @author Johnny Alexander
 */
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JfrmAgenteArchivo extends javax.swing.JFrame {

    /**
     * Creates new form JfrmAgenteArchivo
     */
    GuiAgent owner;

    public JfrmAgenteArchivo(GuiAgent agente) {
        this.owner = agente;//recibe por parametro el agente que lo creo        
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpIndex = new javax.swing.JPanel();
        jbtnIniciar = new javax.swing.JButton();
        jbtnBrowser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jlblRuta = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtAFile = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JpIndex.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Algoritmos clasificacion + Agentes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        JpIndex.setToolTipText("Click en el boton para iniciar");

        jbtnIniciar.setText("Iniciar proceso");
        jbtnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIniciarActionPerformed(evt);
            }
        });

        jbtnBrowser.setText("Seleccionar archivo");
        jbtnBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBrowserActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Ruta:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Contenido Archivo"));

        jtxtAFile.setEditable(false);
        jtxtAFile.setColumns(20);
        jtxtAFile.setRows(5);
        jScrollPane1.setViewportView(jtxtAFile);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout JpIndexLayout = new javax.swing.GroupLayout(JpIndex);
        JpIndex.setLayout(JpIndexLayout);
        JpIndexLayout.setHorizontalGroup(
            JpIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpIndexLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JpIndexLayout.createSequentialGroup()
                        .addGroup(JpIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JpIndexLayout.createSequentialGroup()
                                .addComponent(jbtnBrowser)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JpIndexLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JpIndexLayout.setVerticalGroup(
            JpIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpIndexLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnBrowser)
                    .addComponent(jbtnIniciar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JpIndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jlblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIniciarActionPerformed
        GuiEvent evento = new GuiEvent(this, 0);//el cero indica la accion a realizar, el 0 es para enviar
        this.owner.postGuiEvent(evento);//se le indica al agente que se activo un evento
    }//GEN-LAST:event_jbtnIniciarActionPerformed

    private void jbtnBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBrowserActionPerformed
        String ruta;
        final JFileChooser chooser = new JFileChooser();//se crea el FileChosser definiendo los formatos soportados. 
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos arff", "arff");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(JpIndex);
        ruta = chooser.getSelectedFile().toString();
        jlblRuta.setText(ruta);

        try {
            BufferedReader file = new BufferedReader(new FileReader(ruta)); //Se lee el archivo
            jtxtAFile.setText(convertir(file));//se le añade el contenido al objeto de tipo mensaje, convirtiendo el Buffer en un String
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_jbtnBrowserActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpIndex;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnBrowser;
    private javax.swing.JButton jbtnIniciar;
    private javax.swing.JLabel jlblRuta;
    private javax.swing.JTextArea jtxtAFile;
    // End of variables declaration//GEN-END:variables

    /*Obtiene la ruta del archivo seleccionado*/
    public String obtenerRuta() {
        return jlblRuta.getText();
    }

    public String convertir(BufferedReader file) throws IOException {
        String temp;//Almacena la linea leida del file
        String cadena = "";//cadena final
        while ((temp = file.readLine()) != null) {
            cadena = cadena + temp + "\n";
        }
        return cadena;
    }
}
