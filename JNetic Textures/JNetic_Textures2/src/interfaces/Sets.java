package interfaces;


import java.util.LinkedList;


/**
 * This class allows the user to choose functions and terminals for use in the
 * GP.
 *
 * @author Steve Bergen
 */


public class Sets extends javax.swing.JFrame {


    private Frame   main;

    private javax.swing.JCheckBox       treeBox[];
    private javax.swing.JCheckBox       mathBox[];
    private javax.swing.JCheckBox       shapeBox[];
    private javax.swing.JCheckBox       textureBox[];

    public Sets ( Frame main ) {

        initComponents();

        setup();
        load();

        this.main = main;
        this.addWindowListener(new listeners.FrameListener(main, this));
        pack();

    };


    private void setup ( ) {

        treeBox = new javax.swing.JCheckBox[2];
        treeBox[0] = rF1;
        treeBox[1] = rF2;

        shapeBox = new javax.swing.JCheckBox[3];
        shapeBox[0] = rS1;
        shapeBox[1] = rS2;
        shapeBox[2] = rS3;

        mathBox = new javax.swing.JCheckBox[10];
        mathBox[0] = rM1;
        mathBox[1] = rM2;
        mathBox[2] = rM3;
        mathBox[3] = rM4;
        mathBox[4] = rM5;
        mathBox[5] = rM6;
        mathBox[6] = rM7;
        mathBox[7] = rM8;
        mathBox[8] = rM9;
        mathBox[9] = rM10;

        textureBox = new javax.swing.JCheckBox[8];
        textureBox[0] = rT1;
        textureBox[1] = rT2;
        textureBox[2] = rT3;
        textureBox[3] = rT4;
        textureBox[4] = rT5;
        textureBox[5] = rT6;
        textureBox[6] = rT7;
        textureBox[7] = rT8;

    };


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        rM1 = new javax.swing.JCheckBox();
        rM2 = new javax.swing.JCheckBox();
        rM3 = new javax.swing.JCheckBox();
        rM4 = new javax.swing.JCheckBox();
        rM5 = new javax.swing.JCheckBox();
        rM6 = new javax.swing.JCheckBox();
        rM7 = new javax.swing.JCheckBox();
        rM8 = new javax.swing.JCheckBox();
        rM9 = new javax.swing.JCheckBox();
        rM10 = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        quitButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        rT1 = new javax.swing.JCheckBox();
        rT2 = new javax.swing.JCheckBox();
        rT3 = new javax.swing.JCheckBox();
        rT4 = new javax.swing.JCheckBox();
        rT5 = new javax.swing.JCheckBox();
        rT6 = new javax.swing.JCheckBox();
        rT8 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rT7 = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        rF1 = new javax.swing.JCheckBox();
        rF2 = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        rS1 = new javax.swing.JCheckBox();
        radius = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rS2 = new javax.swing.JCheckBox();
        rS3 = new javax.swing.JCheckBox();
        alpha = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Function and Terminal Sets");
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel5.setText("Mathematical Functions (float)");

        rM1.setFont(new java.awt.Font("Tahoma", 0, 10));
        rM1.setText("Add");

        rM2.setFont(new java.awt.Font("Tahoma", 0, 10));
        rM2.setText("Subtract");

        rM3.setFont(new java.awt.Font("Tahoma", 0, 10));
        rM3.setText("Multiply");
        rM3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rM3ActionPerformed(evt);
            }
        });

        rM4.setFont(new java.awt.Font("Tahoma", 0, 10));
        rM4.setText("Divide");

        rM5.setFont(new java.awt.Font("Tahoma", 0, 10));
        rM5.setText("Sine");

        rM6.setFont(new java.awt.Font("Tahoma", 0, 10));
        rM6.setText("Cosine");

        rM7.setFont(new java.awt.Font("Tahoma", 0, 10));
        rM7.setText("Power (x ^ N)");

        rM8.setFont(new java.awt.Font("Tahoma", 0, 10));
        rM8.setText("Logarithm");

        rM9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rM9.setText("Modulus");

        rM10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rM10.setText("Noise");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rM4, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rM1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(rM2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(rM3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(rM5, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(rM6, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(rM7, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(rM8, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(rM9, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(rM10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rM1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rM2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rM3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rM4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rM5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rM6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rM7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rM8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rM9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rM10)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        quitButton.setBackground(new java.awt.Color(255, 255, 255));
        quitButton.setFont(new java.awt.Font("Tahoma", 0, 10));
        quitButton.setText("Close");
        quitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(521, Short.MAX_VALUE)
                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(quitButton)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel6.setText("Texture Mixing Functions");

        rT1.setFont(new java.awt.Font("Tahoma", 0, 10));
        rT1.setText("Mix 2 Textures");

        rT2.setFont(new java.awt.Font("Tahoma", 0, 10));
        rT2.setText("Mix Texture and RGB");

        rT3.setFont(new java.awt.Font("Tahoma", 0, 10));
        rT3.setText("Grass");

        rT4.setFont(new java.awt.Font("Tahoma", 0, 10));
        rT4.setText("Mandelbrot");

        rT5.setFont(new java.awt.Font("Tahoma", 0, 10));
        rT5.setText("Perlin Noise");

        rT6.setFont(new java.awt.Font("Tahoma", 0, 10));
        rT6.setText("Marble Signal");

        rT8.setFont(new java.awt.Font("Tahoma", 0, 10));
        rT8.setText("Fur");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel7.setText("Texture Functions (need term.)");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel8.setText("Texture Terminals");

        rT7.setFont(new java.awt.Font("Tahoma", 0, 10));
        rT7.setText("Fireball");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rT1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(rT2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addGap(8, 8, 8))
                    .addComponent(rT6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(rT3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(rT5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addComponent(rT4, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rT8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                .addGap(4, 4, 4))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rT7, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rT1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rT2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rT3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rT4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rT5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rT6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rT7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(4, 4, 4)
                .addComponent(rT8)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel3.setText("Root Functions");

        rF1.setFont(new java.awt.Font("Tahoma", 0, 10));
        rF1.setText("Simple texture");

        rF2.setFont(new java.awt.Font("Tahoma", 0, 10));
        rF2.setText("Texture with shapes");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rF1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(rF2, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rF1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rF2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel4.setText("Shape Functions");

        rS1.setFont(new java.awt.Font("Tahoma", 0, 10));
        rS1.setText("Circles (x, y, radius)");

        radius.setFont(new java.awt.Font("Tahoma", 0, 10));
        radius.setText("0");
        radius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiusActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText("Radius");

        rS2.setFont(new java.awt.Font("Tahoma", 0, 10));
        rS2.setText("Rectangles (x, y, width, height)");

        rS3.setFont(new java.awt.Font("Tahoma", 0, 10));
        rS3.setText("Triangles (x, y, x, y, x, y, radius)");

        alpha.setFont(new java.awt.Font("Tahoma", 0, 10));
        alpha.setText("Enable alpha blending");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(rS1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radius, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rS2, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(rS3, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(alpha, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rS1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rS2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rS3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(alpha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed

        quit();

    }//GEN-LAST:event_quitButtonActionPerformed

    private void radiusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiusActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_radiusActionPerformed

    private void rM3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rM3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rM3ActionPerformed


    /**
     * Loads all current values from the parameter file for fitness functions.
     */

    private void load ( ) {

        int n;

        for ( int i = 0; i < param.FunctionSet.TREE_FUNCTIONS.size(); i++ ) {
            n = (Integer)param.FunctionSet.TREE_FUNCTIONS.get(i);
            treeBox[n].setSelected(true);
        }
        
        for ( int i = 0; i < param.FunctionSet.SHAPE_FUNCTIONS.size(); i++ ) {
            n = (Integer)param.FunctionSet.SHAPE_FUNCTIONS.get(i) / 2;
            shapeBox[n].setSelected(true);
        }
        
        for ( int i = 0; i < param.FunctionSet.MATH_FUNCTIONS.size(); i++ ) {
            n = (Integer)param.FunctionSet.MATH_FUNCTIONS.get(i);
            mathBox[n].setSelected(true);
        }

        for ( int i = 0; i < param.FunctionSet.TEXTURE_FUNCTIONS.size(); i++ ) {
            n = (Integer)param.FunctionSet.TEXTURE_FUNCTIONS.get(i);
            textureBox[n].setSelected(true);
        }

        if ( param.Parameters.ALPHA ) alpha.setSelected(true);
        radius.setText(param.FunctionSet.RADIUS + "");

    };


    /**
     * Method called when 'quit' is pressed. Saves all current parameters.
     */

    private void quit ( ) {

        int n;

        if ( getInt(radius) != -1 )
            param.FunctionSet.RADIUS = getInt(radius);

        // Check that at least one root function is selected
        boolean treeselect = false;
        boolean shapeselect = true;
        boolean textureselect = true;

        for ( int i = 0; i < treeBox.length; i++ ) {
            if (treeBox[i].isSelected()) treeselect = true;
        }
        
        // Check that if a shape root function is selected, at least one shape 
        // is as well
        if ( treeBox[1].isSelected() ) {
            shapeselect = false;
            for ( int i = 0; i < shapeBox.length; i++ ) {
                if (shapeBox[i].isSelected()) shapeselect = true;
            }
        }

        // Check that at least one texture terminal is selected if one non-
        // terminal texture is selected
        if (rT8.isSelected() || rT1.isSelected() || rT2.isSelected() ) {
            textureselect = false;
            for ( int i = 2; i <= 6; i++ )
                if ( textureBox[i].isSelected() ) textureselect = true;
        }

        // If no errors, save progress
        if ( treeselect && shapeselect && textureselect) {

            param.FunctionSet.TREE_FUNCTIONS = new LinkedList<Integer>();
            for ( int i = 0; i < treeBox.length; i++ ) {
                if (treeBox[i].isSelected())
                    param.FunctionSet.TREE_FUNCTIONS.add(i);
            }

            param.FunctionSet.SHAPE_FUNCTIONS = new LinkedList<Integer>();
            for ( int i = 0; i < shapeBox.length; i++ ) {
                if (shapeBox[i].isSelected()) {
                    param.FunctionSet.SHAPE_FUNCTIONS.add(i * 2);
                    param.FunctionSet.SHAPE_FUNCTIONS.add(i * 2 + 1);
                }
            }

            param.FunctionSet.MATH_FUNCTIONS = new LinkedList<Integer>();
            for ( int i = 0; i < mathBox.length; i++ ) {
                if (mathBox[i].isSelected())
                    param.FunctionSet.MATH_FUNCTIONS.add(i);
            }

            param.FunctionSet.TEXTURE_FUNCTIONS = new LinkedList<Integer>();
            for ( int i = 0; i < textureBox.length; i++ ) {
                if (textureBox[i].isSelected())
                    param.FunctionSet.TEXTURE_FUNCTIONS.add(i);
            }

            param.Parameters.ALPHA = alpha.isSelected();

        }

        exit();

    };


    private void exit ( ) {

        this.main.setEnabled(true);
        this.setVisible(false);

    };


    /**
     * Tests that the string in a textfield can be converted to a int.
     *
     * @param txt           Textfield to parse
     * @return              Int value (if any) of that text field
     */

    private int getInt ( javax.swing.JTextField txt ) {

        int i           = -1;
        String s        = txt.getText();

        try {

            i = Integer.parseInt(s);

        } catch ( Exception e ) {

        }

        return i;

    };


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox alpha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JButton quitButton;
    private javax.swing.JCheckBox rF1;
    private javax.swing.JCheckBox rF2;
    private javax.swing.JCheckBox rM1;
    private javax.swing.JCheckBox rM10;
    private javax.swing.JCheckBox rM2;
    private javax.swing.JCheckBox rM3;
    private javax.swing.JCheckBox rM4;
    private javax.swing.JCheckBox rM5;
    private javax.swing.JCheckBox rM6;
    private javax.swing.JCheckBox rM7;
    private javax.swing.JCheckBox rM8;
    private javax.swing.JCheckBox rM9;
    private javax.swing.JCheckBox rS1;
    private javax.swing.JCheckBox rS2;
    private javax.swing.JCheckBox rS3;
    private javax.swing.JCheckBox rT1;
    private javax.swing.JCheckBox rT2;
    private javax.swing.JCheckBox rT3;
    private javax.swing.JCheckBox rT4;
    private javax.swing.JCheckBox rT5;
    private javax.swing.JCheckBox rT6;
    private javax.swing.JCheckBox rT7;
    private javax.swing.JCheckBox rT8;
    private javax.swing.JTextField radius;
    // End of variables declaration//GEN-END:variables

}
