package interfaces;


import javax.swing.*;
import java.awt.image.BufferedImage;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;

import javax.swing.DefaultComboBoxModel;
import fitness.*;
import multiobjective.*;


/**
 * This panel displays information about the current population.
 *
 * @author Steve Bergen
 */

public class GPPopPanel extends javax.swing.JPanel {


    public  DefaultComboBoxModel    model, model2;

    private int                     fitnessX    = 0;
    private int                     fitnessY    = 0;
    private int                     typeIndex   = 0;

    public GPPopPanel ( ) {
        
        initComponents();

        model = new javax.swing.DefaultComboBoxModel(new String[] {});
        model.removeAllElements();
        model2 = new javax.swing.DefaultComboBoxModel(new String[] {});
        model2.removeAllElements();

        for ( int i = 0; i < param.Parameters.FITNESS_FUNCTIONS.size(); i++ ) {
            model.addElement(((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(i)).getName());
            model2.addElement(((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(i)).getName());
        }

        fitnessBoxX.setModel(model);
        fitnessBoxX.setSelectedIndex(0);
        fitnessBoxY.setModel(model2);
        fitnessBoxY.setSelectedIndex(0);
        
        loadGraph();
        
    };


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        chart = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        statList = new javax.swing.JTextArea();
        fitnessBoxX = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fitnessBoxY = new javax.swing.JComboBox();
        typeBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(249, 249, 249));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        statList.setColumns(20);
        statList.setEditable(false);
        statList.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        statList.setRows(5);
        statList.setMargin(new java.awt.Insets(5, 10, 5, 10));
        jScrollPane1.setViewportView(statList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        fitnessBoxX.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        fitnessBoxX.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rank" }));
        fitnessBoxX.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fitnessBoxXItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("Fitness Function (X):");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("Fitness Function (Y):");

        fitnessBoxY.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        fitnessBoxY.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rank" }));
        fitnessBoxY.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fitnessBoxYItemStateChanged(evt);
            }
        });

        typeBox.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        typeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Raw Fitness", "Error Fitness" }));
        typeBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typeBoxItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("Fitness Type :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(fitnessBoxX, 0, 102, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(fitnessBoxY, 0, 102, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(typeBox, 0, 102, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fitnessBoxX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fitnessBoxY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fitnessBoxXItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fitnessBoxXItemStateChanged

        fitnessX = fitnessBoxX.getSelectedIndex();
        loadGraph();
        
    }//GEN-LAST:event_fitnessBoxXItemStateChanged

    private void fitnessBoxYItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fitnessBoxYItemStateChanged

        fitnessY = fitnessBoxY.getSelectedIndex();
        loadGraph();

    }//GEN-LAST:event_fitnessBoxYItemStateChanged

    private void typeBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeBoxItemStateChanged

        typeIndex = typeBox.getSelectedIndex();
        loadGraph();

    }//GEN-LAST:event_typeBoxItemStateChanged

    /**
     * Extracts information from the globals object and displays it as a graph.
    */

    public void loadGraph ( ) {

        MOData[] pop = ((ChromosomeDisplay)param.Parameters.FRAME.mainPanel).list;

        // Create new series
        XYSeries series1 = new XYSeries("GP Individuals");

        // Just add another series for an individuals scores! (ADD only one)
        //XYSeries series2 = new XYSeries("Average Fitness");

        statList.setText("");

        String xs = ((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(fitnessX)).getName();
        String ys = ((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(fitnessY)).getName();

        statList.append("ID\t" + xs + "\t\t" + ys + "\n");
        statList.append("----------------------------------------------------" +
                "---------------------\n");

        // Add generation stats for all current generations to the series
        int size = pop.length;
        for (int i = 0; i < size; i++) {
 
            if ( typeIndex == 0 ) {
                series1.add(pop[i].raw[fitnessX], pop[i].raw[fitnessY]);
                statList.append((i) + "\t" +
                    pop[i].raw[fitnessX] + "\t" +
                     pop[i].raw[fitnessY] + "\n");
            } else {
                series1.add(pop[i].fitness[fitnessX], pop[i].fitness[fitnessY]);
                statList.append((i) + "\t" +
                    pop[i].fitness[fitnessX] + "\t" +
                     pop[i].fitness[fitnessY] + "\n");
            }

        }
        
        // Add the series to the chart
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        JFreeChart chart = ChartFactory.createScatterPlot
            ("Population Stats", xs, ys,
        dataset, PlotOrientation.VERTICAL, true, true, false);

        chart.setBackgroundPaint(jPanel2.getBackground());
        // Displays the chart as an icon
        BufferedImage image = chart.createBufferedImage(500,300);
        this.chart.setIcon(new ImageIcon(image));
        
    };
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel chart;
    public javax.swing.JComboBox fitnessBoxX;
    public javax.swing.JComboBox fitnessBoxY;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea statList;
    public javax.swing.JComboBox typeBox;
    // End of variables declaration//GEN-END:variables

}
