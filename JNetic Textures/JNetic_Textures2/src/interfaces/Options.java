package interfaces;


/**
 * This class gives a variety of options to the user to edit. The GP parameters,
 * however, cannot be edited at runtime.
 *
 * @author Steve Bergen
 */

public class Options extends javax.swing.JFrame {


    private Frame   main;


    public Options ( Frame main ) {

        initComponents();

        load();

        this.main = main;
        this.addWindowListener(new listeners.FrameListener(main, this));
        pack();

    };


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        threadText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        widthText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        heightText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ustartText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        uendText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        vstartText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        vendText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        pixelText = new javax.swing.JTextField();
        seedText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        probText = new javax.swing.JLabel();
        probSlider = new javax.swing.JSlider();
        jLabel12 = new javax.swing.JLabel();
        gmaxText = new javax.swing.JTextField();
        gminText = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        hmaxText = new javax.swing.JTextField();
        hminText = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        adfText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        quitButton1 = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Options");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText("Threads");

        threadText.setFont(new java.awt.Font("Tahoma", 0, 10));
        threadText.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText("Rendered image width");

        widthText.setFont(new java.awt.Font("Tahoma", 0, 10));
        widthText.setText("0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText("Rendered image height");

        heightText.setFont(new java.awt.Font("Tahoma", 0, 10));
        heightText.setText("0");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel6.setText("U value from");

        ustartText.setFont(new java.awt.Font("Tahoma", 0, 10));
        ustartText.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("to");

        uendText.setFont(new java.awt.Font("Tahoma", 0, 10));
        uendText.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel8.setText("V value from");

        vstartText.setFont(new java.awt.Font("Tahoma", 0, 10));
        vstartText.setText("0");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("to");

        vendText.setFont(new java.awt.Font("Tahoma", 0, 10));
        vendText.setText("0");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel10.setText("Per-pixel fitness calculation (pixels)");

        pixelText.setFont(new java.awt.Font("Tahoma", 0, 10));
        pixelText.setText("0");

        seedText.setFont(new java.awt.Font("Tahoma", 0, 10));
        seedText.setText("0");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel11.setText("Seed");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                        .addComponent(widthText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addComponent(heightText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ustartText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uendText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vstartText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(pixelText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                        .addComponent(threadText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                        .addComponent(seedText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(widthText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(heightText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ustartText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(uendText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(vstartText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(vendText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(pixelText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(threadText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(seedText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("General", jPanel2);

        probText.setFont(new java.awt.Font("Tahoma", 0, 10));
        probText.setText("Grow probability");

        probSlider.setFont(new java.awt.Font("Tahoma", 0, 10));
        probSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                probSliderStateChanged(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel12.setText("Grow maximum size");

        gmaxText.setFont(new java.awt.Font("Tahoma", 0, 10));
        gmaxText.setText("0");

        gminText.setFont(new java.awt.Font("Tahoma", 0, 10));
        gminText.setText("0");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel13.setText("Grow minimum size");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setText("Full maximum size");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel15.setText("Full minimum size");

        hmaxText.setFont(new java.awt.Font("Tahoma", 0, 10));
        hmaxText.setText("0");

        hminText.setFont(new java.awt.Font("Tahoma", 0, 10));
        hminText.setText("0");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel16.setText("ADFs");

        adfText.setFont(new java.awt.Font("Tahoma", 0, 10));
        adfText.setText("0");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel1.setText("Note : All ADFs have return type RGB used in primitives and backgrounds");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(probText, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(probSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gmaxText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hminText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hmaxText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gminText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adfText, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(probSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(probText, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(gmaxText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(gminText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(hmaxText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(hminText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(adfText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Genetic Programming", jPanel3);

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        quitButton1.setBackground(new java.awt.Color(255, 255, 255));
        quitButton1.setFont(new java.awt.Font("Tahoma", 0, 10));
        quitButton1.setText("Close");
        quitButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        quitButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(325, Short.MAX_VALUE)
                .addComponent(quitButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(quitButton1)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1)
                .addGap(10, 10, 10))
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Genetic Programming");

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

    private void quitButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButton1ActionPerformed

        quit();

    }//GEN-LAST:event_quitButton1ActionPerformed

    private void probSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_probSliderStateChanged

        probText.setText("Grow probability : " + probSlider.getValue() / 100.0);

    }//GEN-LAST:event_probSliderStateChanged


    private void load ( ) {

        widthText.setText("" + param.Parameters.IMAGEW);
        heightText.setText("" + param.Parameters.IMAGEH);

        ustartText.setText("" + param.Parameters.STARTU);
        uendText.setText("" + param.Parameters.ENDU);
        vstartText.setText("" + param.Parameters.STARTV);
        vendText.setText("" + param.Parameters.ENDV);

        pixelText.setText("" + param.Parameters.PIXEL);
        threadText.setText("" + param.Parameters.THREADS);
        seedText.setText("" + param.Parameters.SEED);

        probSlider.setValue((int)(param.GPParameters.GROW_PROB * 100));
        probSliderStateChanged(null);
        
        gmaxText.setText("" + param.GPParameters.GROW[1]);
        gminText.setText("" + param.GPParameters.GROW[0]);
        hmaxText.setText("" + param.GPParameters.HALF[1]);
        hminText.setText("" + param.GPParameters.HALF[0]);

        adfText.setText("" + param.GPParameters.ADFS);

    };


    private void quit ( ) {

        if ( getInt(widthText) != -1 )
            param.Parameters.IMAGEW = getInt(widthText);
        if ( getInt(heightText) != -1 )
            param.Parameters.IMAGEH = getInt(heightText);

        if ( getDouble(ustartText) != -1 )
            param.Parameters.STARTU = (float)getDouble(ustartText);
        if ( getDouble(uendText) != -1 )
            param.Parameters.ENDU = (float)getDouble(uendText);

        if ( getDouble(vstartText) != -1 )
            param.Parameters.STARTV = (float)getDouble(vstartText);
        if ( getDouble(vendText) != -1 )
            param.Parameters.ENDV = (float)getDouble(vendText);

        if ( getInt(pixelText) != -1 && getInt(pixelText) > 0 )
            param.Parameters.PIXEL = getInt(pixelText);
        if ( getInt(threadText) != -1 && getInt(threadText) > -1  )
            param.Parameters.THREADS = getInt(threadText);
        if ( getInt(seedText) != -1 )
            param.Parameters.SEED = getInt(seedText);

        param.GPParameters.GROW_PROB = probSlider.getValue() / 100.0;

        if ( getInt(gmaxText) != -1 && getInt(gmaxText) > 0 )
            param.GPParameters.GROW[1] = getInt(gmaxText);
        if ( getInt(gminText) != -1 && getInt(gminText) > 0 )
            param.GPParameters.GROW[0] = getInt(gminText);

        if ( getInt(hmaxText) != -1 && getInt(hmaxText) > 0 )
            param.GPParameters.HALF[1] = getInt(hmaxText);
        if ( getInt(hminText) != -1 && getInt(hminText) > 0 )
            param.GPParameters.HALF[0] = getInt(hminText);

        if ( getInt(adfText) != -1 && getInt(adfText) > -1 )
            param.GPParameters.ADFS = getInt(adfText);

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

        double i        = -1;
        String s        = txt.getText();

        try {

            i = Double.parseDouble(s);

        } catch ( Exception e ) {

        }

        return i;

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
    private javax.swing.JTextField adfText;
    private javax.swing.JTextField gmaxText;
    private javax.swing.JTextField gminText;
    private javax.swing.JTextField heightText;
    private javax.swing.JTextField hmaxText;
    private javax.swing.JTextField hminText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField pixelText;
    private javax.swing.JSlider probSlider;
    private javax.swing.JLabel probText;
    private javax.swing.JButton quitButton1;
    private javax.swing.JTextField seedText;
    private javax.swing.JTextField threadText;
    private javax.swing.JTextField uendText;
    private javax.swing.JTextField ustartText;
    private javax.swing.JTextField vendText;
    private javax.swing.JTextField vstartText;
    private javax.swing.JTextField widthText;
    // End of variables declaration//GEN-END:variables

}
