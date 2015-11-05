package interfaces;


import java.util.LinkedList;
import fitness.*;


/**
 * This class allows the user to select up to four fitness functions to be used
 * in the evaluation phase.
 *
 * @author Steve Bergen
 */

public class Fitness extends javax.swing.JFrame {


    private Frame   main;


    public Fitness ( Frame main ) {

        initComponents();

        load();

        this.main = main;
        this.addWindowListener(new listeners.FrameListener(main, this));
        pack();

    };


    /**
     * Loads all current values from the parameter file for fitness functions.
     */

    private void load ( ) {

        for ( int i = 0; i < param.Parameters.FITNESS_FUNCTIONS.size(); i++ ) {

            FitnessFunction f =
                    (FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(i);

            if ( f.getName().equals("Mean") ) {
                meanBox.setSelected(true);
                meanText.setText(f.compare + "");
            }

            else if ( f.getName().equals("STD") ) {
                stdBox.setSelected(true);
                stdText.setText(f.compare + "");
            }

            else if ( f.getName().equals("DFN") ) {
                dfnBox.setSelected(true);
                dfnCompare.setText(f.compare + "");
            }

            else if ( f.getName().equals("CDist") ) {
                colorBox.setSelected(true);
            }

            else if ( f.getName().equals("HDist") ) {
                histBox.setSelected(true);
            }

        }

    };


    /**
     * Method called when 'quit' is pressed. Saves all current parameters.
     */

    private void quit ( ) {

        if ( !meanBox.isSelected() && !stdBox.isSelected() &&
                !dfnBox.isSelected() && !colorBox.isSelected() && !histBox.isSelected() ) ;

        else if ( meanBox.isSelected() && stdBox.isSelected() &&
                dfnBox.isSelected() && colorBox.isSelected() && histBox.isSelected() ) ;
        else {

            param.Parameters.FITNESS_FUNCTIONS = new LinkedList<FitnessFunction>();

            if ( meanBox.isSelected() )
                param.Parameters.FITNESS_FUNCTIONS.add(new Mean(getDouble(meanText)));

            if ( stdBox.isSelected() )
                param.Parameters.FITNESS_FUNCTIONS.add(
                        new StandardDeviation(getDouble(stdText)));

            if ( dfnBox.isSelected() )
                param.Parameters.FITNESS_FUNCTIONS.add(
                        new DeviationFromNormal(getDouble(dfnCompare)));

            if ( colorBox.isSelected() )
                param.Parameters.FITNESS_FUNCTIONS.add(new ColorDistance(0));

            if ( histBox.isSelected() )
                param.Parameters.FITNESS_FUNCTIONS.add(new HistogramDistance(0));

            ((ChromosomeDisplay)param.Parameters.FRAME.mainPanel).setFitnesses();

        }

        this.main.setEnabled(true);
        this.setVisible(false);
        
    };


    /**
     * Tests that the string in a textfield can be converted to a double.
     *
     * @param txt           Textfield to parse
     * @return              Double value (if any) of that text field
     */

    private double getDouble ( javax.swing.JTextField txt ) {

        double i        = 0;
        String s        = txt.getText();

        try {

            i = Double.parseDouble(s);

        } catch ( Exception e ) {

        }

        return i;

    };


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        meanBox = new javax.swing.JCheckBox();
        meanText = new javax.swing.JTextField();
        stdBox = new javax.swing.JCheckBox();
        dfnBox = new javax.swing.JCheckBox();
        colorBox = new javax.swing.JCheckBox();
        histBox = new javax.swing.JCheckBox();
        stdText = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dfnCompare = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        quitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fitness Functions");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        label1.setFont(new java.awt.Font("Tahoma", 1, 10));
        label1.setText("Fitness Functions");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel1.setText("Comparison Value");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText("Choose from the available fitness functions below to be included during fitness scoring. ");

        meanBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        meanBox.setText("Mean (reduce error)");
        meanBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meanBoxActionPerformed(evt);
            }
        });

        meanText.setFont(new java.awt.Font("Tahoma", 0, 10));
        meanText.setText("0.0");

        stdBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        stdBox.setText("Standard Deviation (reduce error)");

        dfnBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        dfnBox.setText("DFN (deviation from normal)");

        colorBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        colorBox.setText("Direct Color Distance Matching");

        histBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        histBox.setText("Color Histogram Distance Matching");

        stdText.setFont(new java.awt.Font("Tahoma", 0, 10));
        stdText.setText("0.0");
        stdText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdTextActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jTextField3.setText("compared to source image");

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jTextField4.setText("compared to source image");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText("A maximum of four functions can be chosen simultaneously.");

        dfnCompare.setFont(new java.awt.Font("Tahoma", 0, 10));
        dfnCompare.setText("0.0");
        dfnCompare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dfnCompareActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(colorBox, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(dfnBox, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(meanBox, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(stdBox, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(histBox, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dfnCompare, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(stdText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(meanText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                                .addGap(2, 2, 2))))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meanText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meanBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stdBox)
                    .addComponent(stdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dfnBox)
                    .addComponent(dfnCompare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colorBox)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(histBox)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        quitButton.setBackground(new java.awt.Color(255, 255, 255));
        quitButton.setFont(new java.awt.Font("Tahoma", 0, 10));
        quitButton.setText("Close");
        quitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(348, Short.MAX_VALUE)
                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(quitButton)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void meanBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meanBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_meanBoxActionPerformed

    private void stdTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stdTextActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed

        quit();

    }//GEN-LAST:event_quitButtonActionPerformed

    private void dfnCompareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dfnCompareActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dfnCompareActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox colorBox;
    private javax.swing.JCheckBox dfnBox;
    private javax.swing.JTextField dfnCompare;
    private javax.swing.JCheckBox histBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel label1;
    private javax.swing.JCheckBox meanBox;
    private javax.swing.JTextField meanText;
    private javax.swing.JButton quitButton;
    private javax.swing.JCheckBox stdBox;
    private javax.swing.JTextField stdText;
    // End of variables declaration//GEN-END:variables

};