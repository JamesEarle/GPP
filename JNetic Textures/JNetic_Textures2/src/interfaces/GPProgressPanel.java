package interfaces;


import javax.swing.*;
import java.awt.image.BufferedImage;
import param.FitnessStat;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;

import javax.swing.DefaultComboBoxModel;
import fitness.*;


/**
 * This panel displays population data on a per-generation basis.
 *
 * @author Steve Bergen
 */

public class GPProgressPanel extends javax.swing.JPanel {


    public  DefaultComboBoxModel    model;

    private int                     fitnessIndex    = 0;
    private int                     typeIndex       = 0;

    public GPProgressPanel ( ) {
        
        initComponents();

        model = new javax.swing.DefaultComboBoxModel(new String[] {});
        model.removeAllElements();

        for ( int i = 0; i < param.Parameters.FITNESS_FUNCTIONS.size(); i++ )
            model.addElement(((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(i)).getName());

        fitnessBox.setModel(model);
        fitnessBox.setSelectedIndex(0);
        
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
        fitnessBox = new javax.swing.JComboBox();
        typeBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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
        statList.setFont(new java.awt.Font("Tahoma", 0, 10));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        fitnessBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitnessBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rank" }));
        fitnessBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fitnessBoxItemStateChanged(evt);
            }
        });

        typeBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        typeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Raw Fitness", "Error Fitness" }));
        typeBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typeBoxItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText("Fitness Function :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText("Fitness Type :");

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
                            .addComponent(typeBox, 0, 102, Short.MAX_VALUE)
                            .addComponent(fitnessBox, 0, 102, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fitnessBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void fitnessBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fitnessBoxItemStateChanged

        fitnessIndex = fitnessBox.getSelectedIndex();
        loadGraph();
        
    }//GEN-LAST:event_fitnessBoxItemStateChanged

    private void typeBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeBoxItemStateChanged

        typeIndex = typeBox.getSelectedIndex();
        loadGraph();

    }//GEN-LAST:event_typeBoxItemStateChanged

    /**
     * Extracts information from the globals object and displays it as a graph.
    */

    public void loadGraph ( ) {
        
        // Create new series
        XYSeries series1 = new XYSeries("Best Fitness");
        XYSeries series2 = new XYSeries("Average Fitness");

        String xs = ((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(fitnessIndex)).getName();

        statList.setText("");
        statList.append("Generation\tBest Fitness\t\tAverage Fitness\n");
        statList.append("----------------------------------------------------" +
                "---------------------\n");

        // Add generation stats for all current generations to the series
        int size = param.Parameters.STATS.size();
        for (int i = 0; i < size; i++) {
            FitnessStat stat = (FitnessStat)param.Parameters.STATS.get(i);

            if ( typeIndex == 0 ) {
                series1.add(i, stat.STATS[fitnessIndex][0]);
                series2.add(i, stat.STATS[fitnessIndex][1]);
                statList.append((i) + "\t" +
                    stat.STATS[fitnessIndex][0] + "\t" +
                    stat.STATS[fitnessIndex][1] + "\n");
            } else {
                series1.add(i, stat.ESTATS[fitnessIndex][0]);
                series2.add(i, stat.ESTATS[fitnessIndex][1]);
                statList.append((i) + "\t" +
                    stat.ESTATS[fitnessIndex][0] + "\t" +
                    stat.ESTATS[fitnessIndex][1] + "\n");
            }

        }
        
        // Add the series to the chart
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart
            ("Genetic Program Stats", "Generation", xs,
        dataset, PlotOrientation.VERTICAL, true, true, false);

        chart.setBackgroundPaint(jPanel2.getBackground());
        // Displays the chart as an icon
        BufferedImage image = chart.createBufferedImage(500,300);
        this.chart.setIcon(new ImageIcon(image));
        
    };
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel chart;
    public javax.swing.JComboBox fitnessBox;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea statList;
    public javax.swing.JComboBox typeBox;
    // End of variables declaration//GEN-END:variables

}
