package interfaces;


import multiobjective.MOData;
import gp.*;


/**
 * This class acts as the main window in the program, displaying population
 * information, as well as giving a large amount of control to the user.
 *
 * @author Steve Bergen
 */

public class Frame extends javax.swing.JFrame {
    
    
    public  Console     consolewriter;
    public  GPRun       gp;
    public  boolean     running = false;

    public interfaces.BaseImage imgDisplay;


    public Frame ( ) {

        initComponents();
        
        param.Parameters.FRAME = this;
      
        // Setup chromosome displayers (both image and list)
        ((ChromosomeDisplay)mainPanel).chromosomeList = chromosomeListing;

        this.setValues();
        
        this.setLocationRelativeTo(null);
   
        imgDisplay = new interfaces.BaseImage(this, param.Parameters.SOURCE);
        
        consolewriter = new Console(console);
        
        try {                              
            javax.swing.UIManager.setLookAndFeel(new util.TooltipLF());
        } catch(Exception ex) {        
            System.err.println("ToolTipLookAndFeel exception!");
            System.err.println(ex.getMessage());
        }
        
    };
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        parameterPanel = new javax.swing.JPanel();
        parameterinPanel = new javax.swing.JPanel();
        generationLabel = new javax.swing.JLabel();
        generationBox = new javax.swing.JTextField();
        populationSlide = new javax.swing.JSlider();
        populationLabel = new javax.swing.JLabel();
        crossoverLabel = new javax.swing.JLabel();
        mutationLabel = new javax.swing.JLabel();
        populationBox = new javax.swing.JTextField();
        crossBox = new javax.swing.JTextField();
        mutateBox = new javax.swing.JTextField();
        generationSlide = new javax.swing.JSlider();
        crossSlider = new javax.swing.JSlider();
        mutateSlider = new javax.swing.JSlider();
        tournamentLabel = new javax.swing.JLabel();
        tournamentBox = new javax.swing.JTextField();
        tournamentSlider = new javax.swing.JSlider();
        crossoverLabel1 = new javax.swing.JLabel();
        terminalBox = new javax.swing.JTextField();
        terminalSlider = new javax.swing.JSlider();
        generationLabel1 = new javax.swing.JLabel();
        mmaxBox = new javax.swing.JTextField();
        mmaxSlider = new javax.swing.JSlider();
        generationLabel2 = new javax.swing.JLabel();
        cmaxBox = new javax.swing.JTextField();
        cmaxSlider = new javax.swing.JSlider();
        jScrollPane2 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextPane();
        chromosomeScroll = new javax.swing.JScrollPane();
        chromosomeListing = new javax.swing.JList();
        progress = new javax.swing.JProgressBar();
        playButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        shapeButton = new javax.swing.JButton();
        colorButton = new javax.swing.JButton();
        globalButton = new javax.swing.JButton();
        baseButton = new javax.swing.JButton();
        graphButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Texture Evolution Using Genetic Programming");
        setResizable(false);

        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );

        mainPanel = new ChromosomeDisplay(this);

        parameterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Genetic Programming Parameters", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        generationLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/genIcon.png"))); // NOI18N
        generationLabel.setToolTipText("Generations");

        generationBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        generationBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generationBoxActionPerformed(evt);
            }
        });
        generationBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                generationBoxPropertyChange(evt);
            }
        });
        generationBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                generationBoxKeyTyped(evt);
            }
        });

        populationSlide.setMaximum(1000);
        populationSlide.setMinimum(1);
        populationSlide.setValue(100);
        populationSlide.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                populationSlideStateChanged(evt);
            }
        });

        populationLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/popIcon.png"))); // NOI18N
        populationLabel.setToolTipText("Population");

        crossoverLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/crossIcon.png"))); // NOI18N
        crossoverLabel.setToolTipText("Crossover Rate");

        mutationLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mutateIcon.png"))); // NOI18N
        mutationLabel.setToolTipText("Mutation Rate");

        populationBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        populationBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                populationBoxActionPerformed(evt);
            }
        });

        crossBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        crossBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crossBoxActionPerformed(evt);
            }
        });

        mutateBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        mutateBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mutateBoxActionPerformed(evt);
            }
        });

        generationSlide.setMaximum(100000);
        generationSlide.setMinimum(1);
        generationSlide.setValue(200);
        generationSlide.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                generationSlideStateChanged(evt);
            }
        });

        crossSlider.setMaximum(1000);
        crossSlider.setValue(1000);
        crossSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                crossSliderStateChanged(evt);
            }
        });

        mutateSlider.setMaximum(1000);
        mutateSlider.setValue(100);
        mutateSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mutateSliderStateChanged(evt);
            }
        });

        tournamentLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tourIcon.png"))); // NOI18N
        tournamentLabel.setToolTipText("Tournament Size");

        tournamentBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        tournamentBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tournamentBoxActionPerformed(evt);
            }
        });

        tournamentSlider.setMaximum(20);
        tournamentSlider.setMinimum(1);
        tournamentSlider.setValue(5);
        tournamentSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tournamentSliderStateChanged(evt);
            }
        });

        crossoverLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/terminalIcon.png"))); // NOI18N
        crossoverLabel1.setToolTipText("Probability of choosing terminals in crossover/mutation");

        terminalBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        terminalBox.setToolTipText("");
        terminalBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminalBoxActionPerformed(evt);
            }
        });

        terminalSlider.setMaximum(1000);
        terminalSlider.setToolTipText("");
        terminalSlider.setValue(1000);
        terminalSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                terminalSliderStateChanged(evt);
            }
        });

        generationLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/depthIcon.png"))); // NOI18N
        generationLabel1.setToolTipText("Maximum mutation depth");

        mmaxBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        mmaxBox.setToolTipText("");
        mmaxBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmaxBoxActionPerformed(evt);
            }
        });
        mmaxBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                mmaxBoxPropertyChange(evt);
            }
        });
        mmaxBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mmaxBoxKeyTyped(evt);
            }
        });

        mmaxSlider.setMaximum(50);
        mmaxSlider.setMinimum(1);
        mmaxSlider.setToolTipText("");
        mmaxSlider.setValue(10);
        mmaxSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mmaxSliderStateChanged(evt);
            }
        });

        generationLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/depthIcon.png"))); // NOI18N
        generationLabel2.setToolTipText("Maximum crossover depth");

        cmaxBox.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmaxBox.setToolTipText("");
        cmaxBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmaxBoxActionPerformed(evt);
            }
        });
        cmaxBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmaxBoxPropertyChange(evt);
            }
        });
        cmaxBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cmaxBoxKeyTyped(evt);
            }
        });

        cmaxSlider.setMaximum(50);
        cmaxSlider.setMinimum(1);
        cmaxSlider.setToolTipText("");
        cmaxSlider.setValue(10);
        cmaxSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cmaxSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout parameterinPanelLayout = new javax.swing.GroupLayout(parameterinPanel);
        parameterinPanel.setLayout(parameterinPanelLayout);
        parameterinPanelLayout.setHorizontalGroup(
            parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parameterinPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(parameterinPanelLayout.createSequentialGroup()
                        .addComponent(generationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generationBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generationSlide, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, parameterinPanelLayout.createSequentialGroup()
                        .addComponent(populationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(populationBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(populationSlide, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, parameterinPanelLayout.createSequentialGroup()
                        .addComponent(generationLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mmaxBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mmaxSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addGroup(parameterinPanelLayout.createSequentialGroup()
                        .addComponent(mutationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mutateBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mutateSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addGroup(parameterinPanelLayout.createSequentialGroup()
                        .addComponent(generationLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmaxBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmaxSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addGroup(parameterinPanelLayout.createSequentialGroup()
                        .addComponent(crossoverLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(crossBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(crossSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, parameterinPanelLayout.createSequentialGroup()
                        .addComponent(tournamentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tournamentBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tournamentSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addGroup(parameterinPanelLayout.createSequentialGroup()
                        .addComponent(crossoverLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(terminalBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(terminalSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)))
                .addContainerGap())
        );
        parameterinPanelLayout.setVerticalGroup(
            parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parameterinPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generationSlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(generationLabel)
                        .addComponent(generationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(populationSlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(populationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(populationLabel)))
                .addGap(18, 18, 18)
                .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mutateSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(mutationLabel)
                        .addComponent(mutateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mmaxSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(generationLabel1)
                        .addComponent(mmaxBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crossSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(crossoverLabel)
                        .addComponent(crossBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmaxSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(generationLabel2)
                        .addComponent(cmaxBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(terminalSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(crossoverLabel1)
                        .addComponent(terminalBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tournamentSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(parameterinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tournamentLabel)
                        .addComponent(tournamentBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout parameterPanelLayout = new javax.swing.GroupLayout(parameterPanel);
        parameterPanel.setLayout(parameterPanelLayout);
        parameterPanelLayout.setHorizontalGroup(
            parameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(parameterinPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        parameterPanelLayout.setVerticalGroup(
            parameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(parameterinPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        console.setEditable(false);
        console.setFont(new java.awt.Font("Tahoma", 0, 10));
        jScrollPane2.setViewportView(console);

        chromosomeListing.setFont(new java.awt.Font("Tahoma", 0, 10));
        chromosomeListing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chromosomeListingMouseClicked(evt);
            }
        });
        chromosomeScroll.setViewportView(chromosomeListing);

        progress.setBackground(new java.awt.Color(255, 255, 255));
        progress.setFont(new java.awt.Font("Tahoma", 0, 10));
        progress.setForeground(new java.awt.Color(102, 255, 102));
        progress.setToolTipText("GA Progress (per generation)");
        progress.setStringPainted(true);

        playButton.setBackground(java.awt.Color.lightGray);
        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/playButton.png"))); // NOI18N
        playButton.setToolTipText("Play/Pause GP");
        playButton.setBorder(null);
        playButton.setFocusable(false);
        playButton.setOpaque(false);
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playButtonMouseExited(evt);
            }
        });

        stopButton.setBackground(java.awt.Color.lightGray);
        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stopButton.png"))); // NOI18N
        stopButton.setToolTipText("Stop GP");
        stopButton.setBorder(null);
        stopButton.setFocusable(false);
        stopButton.setOpaque(false);
        stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stopButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stopButtonMouseExited(evt);
            }
        });

        shapeButton.setBackground(new java.awt.Color(255, 255, 255));
        shapeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fctButton.png"))); // NOI18N
        shapeButton.setToolTipText("Fitness Functions");
        shapeButton.setBorder(null);
        shapeButton.setFocusable(false);
        shapeButton.setOpaque(false);
        shapeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shapeButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                shapeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                shapeButtonMouseExited(evt);
            }
        });

        colorButton.setBackground(new java.awt.Color(255, 255, 255));
        colorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/colorButton.png"))); // NOI18N
        colorButton.setToolTipText("Function and Terminal Sets");
        colorButton.setBorder(null);
        colorButton.setFocusable(false);
        colorButton.setOpaque(false);
        colorButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                colorButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorButtonMouseExited(evt);
            }
        });

        globalButton.setBackground(new java.awt.Color(255, 255, 255));
        globalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/globalButton.png"))); // NOI18N
        globalButton.setToolTipText("Global Properties");
        globalButton.setBorder(null);
        globalButton.setFocusable(false);
        globalButton.setOpaque(false);
        globalButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                globalButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                globalButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                globalButtonMouseExited(evt);
            }
        });

        baseButton.setBackground(java.awt.Color.white);
        baseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseButton.png"))); // NOI18N
        baseButton.setToolTipText("Source Image");
        baseButton.setBorder(null);
        baseButton.setFocusable(false);
        baseButton.setOpaque(false);
        baseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                baseButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                baseButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                baseButtonMouseExited(evt);
            }
        });

        graphButton.setBackground(new java.awt.Color(255, 255, 255));
        graphButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graphButton.png"))); // NOI18N
        graphButton.setToolTipText("Progress Chart");
        graphButton.setBorder(null);
        graphButton.setEnabled(false);
        graphButton.setFocusable(false);
        graphButton.setOpaque(false);
        graphButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                graphButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                graphButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                graphButtonMouseExited(evt);
            }
        });

        helpButton.setBackground(new java.awt.Color(255, 255, 255));
        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/helpButton.png"))); // NOI18N
        helpButton.setToolTipText("Help");
        helpButton.setBorder(null);
        helpButton.setFocusable(false);
        helpButton.setOpaque(false);
        helpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                helpButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(parameterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chromosomeScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(helpButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(playButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(globalButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(shapeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(stopButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(colorButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(baseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(graphButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(parameterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(shapeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(globalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(baseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(graphButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(helpButton))
                            .addComponent(chromosomeScroll)))
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {baseButton, colorButton, globalButton, graphButton, helpButton, playButton, shapeButton, stopButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
  
    
    
private void playButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseEntered
    if (playButton.isEnabled()) playButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_playButtonMouseEntered

private void playButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseExited
    playButton.setBorder(null);
}//GEN-LAST:event_playButtonMouseExited

private void stopButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseEntered
    if (stopButton.isEnabled()) stopButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_stopButtonMouseEntered

private void stopButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseExited
    stopButton.setBorder(null);
}//GEN-LAST:event_stopButtonMouseExited

private void colorButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorButtonMouseEntered
    if (colorButton.isEnabled()) colorButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_colorButtonMouseEntered

private void colorButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorButtonMouseExited
    colorButton.setBorder(null);
}//GEN-LAST:event_colorButtonMouseExited

private void globalButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_globalButtonMouseEntered
    if (globalButton.isEnabled()) globalButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_globalButtonMouseEntered

private void globalButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_globalButtonMouseExited
    globalButton.setBorder(null);
}//GEN-LAST:event_globalButtonMouseExited

private void helpButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseEntered
    if (helpButton.isEnabled()) helpButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_helpButtonMouseEntered

private void helpButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseExited
    helpButton.setBorder(null);
}//GEN-LAST:event_helpButtonMouseExited

private void baseButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baseButtonMouseEntered
    if (baseButton.isEnabled()) baseButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_baseButtonMouseEntered

private void baseButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baseButtonMouseExited
    baseButton.setBorder(null);
}//GEN-LAST:event_baseButtonMouseExited

private void graphButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graphButtonMouseExited
    graphButton.setBorder(null);
}//GEN-LAST:event_graphButtonMouseExited

private void graphButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graphButtonMouseEntered
    if (graphButton.isEnabled()) graphButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_graphButtonMouseEntered

private void shapeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shapeButtonMouseEntered
    if (shapeButton.isEnabled()) shapeButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_shapeButtonMouseEntered

private void shapeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shapeButtonMouseExited
    shapeButton.setBorder(null);
}//GEN-LAST:event_shapeButtonMouseExited

private void generationBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_generationBoxPropertyChange
    //remove
}//GEN-LAST:event_generationBoxPropertyChange

private void generationBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generationBoxActionPerformed

    int         i = -1;
    
    String      s = generationBox.getText();
    
    // Test for integer (otherwise invalid input)
    try {
        i = Integer.parseInt(s);
    } catch (Exception ex) { }
    
    // Set slider value to integer (otherwise leave it)
    if ( i != -1 && i >= generationSlide.getMinimum() && i <= generationSlide.getMaximum()) {
        generationSlide.setValue(i);
        param.GPParameters.GENERATIONS = i;
    }
    else generationBox.setText(generationSlide.getValue() + "");
     
    
}//GEN-LAST:event_generationBoxActionPerformed

private void generationBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_generationBoxKeyTyped

    
}//GEN-LAST:event_generationBoxKeyTyped

private void populationBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_populationBoxActionPerformed

    int         i = -1;
    
    String      s = populationBox.getText();
    
    // Test for integer (otherwise invalid input)
    try {
        i = Integer.parseInt(s);
    } catch (Exception ex) { }
    
    // Set slider value to integer (otherwise leave it)
    if ( i != -1 && i >= populationSlide.getMinimum() && i <= populationSlide.getMaximum()) {
        populationSlide.setValue(i);
        param.GPParameters.POP_SIZE = i;
        
        if (tournamentSlider.getValue() > populationSlide.getValue()) {
            tournamentSlider.setValue(populationSlide.getValue());
            tournamentBox.setText(tournamentSlider.getValue() + "");
            param.GPParameters.T_SIZE = tournamentSlider.getValue();
        }
        
    }
    else populationBox.setText(populationSlide.getValue() + "");
     
    
}//GEN-LAST:event_populationBoxActionPerformed

private void tournamentBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tournamentBoxActionPerformed

    int         i = -1;
    
    String      s = tournamentBox.getText();
    
    // Test for integer (otherwise invalid input)
    try {
        i = Integer.parseInt(s);
    } catch (Exception ex) { }
    
    // Set slider value to integer (otherwise leave it)
    if ( i != -1 && i >= tournamentSlider.getMinimum() && i <= tournamentSlider.getMaximum()) {
        tournamentSlider.setValue(i);
        param.GPParameters.T_SIZE = i;
        
        if (tournamentSlider.getValue() > populationSlide.getValue()) {
            populationSlide.setValue(tournamentSlider.getValue());
            populationBox.setText(populationSlide.getValue() + "");
            param.GPParameters.POP_SIZE = populationSlide.getValue();
        }
        
    }
    else tournamentBox.setText(tournamentSlider.getValue() + "");
    
}//GEN-LAST:event_tournamentBoxActionPerformed

private void crossBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crossBoxActionPerformed

    double      d = -1;
    
    String      s = crossBox.getText();
    
    // Test for integer (otherwise invalid input)
    try {
        d = Double.parseDouble(s);
    } catch (Exception ex) { }
    
    // Set slider value to integer (otherwise leave it)
    if ( d != -1 && d >= crossSlider.getMinimum() && d <= crossSlider.getMaximum()) {
        crossSlider.setValue((int)(d * 10));
        param.GPParameters.CROSSOVER_RATE = d / 100.0;
    }
    else crossBox.setText((crossSlider.getValue() / 10.0) + "");
    
}//GEN-LAST:event_crossBoxActionPerformed

private void mutateBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mutateBoxActionPerformed

    double      d = -1;
    
    String      s = mutateBox.getText();
    
    // Test for integer (otherwise invalid input)
    try {
        d = Double.parseDouble(s);
    } catch (Exception ex) { }
    
    // Set slider value to integer (otherwise leave it)
    if ( d != -1 && d >= mutateSlider.getMinimum() && d <= mutateSlider.getMaximum()) {
        mutateSlider.setValue((int)(d * 10.0));
        param.GPParameters.MUTATION_RATE = d / 100.0;
    }
    else mutateBox.setText((mutateSlider.getValue() / 10.0) + "");
    
}//GEN-LAST:event_mutateBoxActionPerformed

private void generationSlideStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_generationSlideStateChanged

    generationBox.setText(generationSlide.getValue() + "");
    param.GPParameters.GENERATIONS = generationSlide.getValue();
    
}//GEN-LAST:event_generationSlideStateChanged

private void populationSlideStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_populationSlideStateChanged

    populationBox.setText(populationSlide.getValue() + "");
    
    if (tournamentSlider.getValue() > populationSlide.getValue()) {
        tournamentSlider.setValue(populationSlide.getValue());
        tournamentBox.setText(tournamentSlider.getValue() + "");
        param.GPParameters.T_SIZE = tournamentSlider.getValue();
    }
    
    param.GPParameters.POP_SIZE = populationSlide.getValue();
    
}//GEN-LAST:event_populationSlideStateChanged

private void crossSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_crossSliderStateChanged

    crossBox.setText((crossSlider.getValue() / 10.0) + "");
    param.GPParameters.CROSSOVER_RATE = crossSlider.getValue() / 1000.0;
    
}//GEN-LAST:event_crossSliderStateChanged

private void mutateSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mutateSliderStateChanged

    mutateBox.setText((mutateSlider.getValue() / 10.0) + "");
    param.GPParameters.MUTATION_RATE = mutateSlider.getValue() / 1000.0;
    
}//GEN-LAST:event_mutateSliderStateChanged

private void tournamentSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tournamentSliderStateChanged

    tournamentBox.setText(tournamentSlider.getValue() + "");
    
    if (tournamentSlider.getValue() > populationSlide.getValue()) {
        populationSlide.setValue(tournamentSlider.getValue());
        populationBox.setText(populationSlide.getValue() + "");
        param.GPParameters.POP_SIZE = populationSlide.getValue();
    }
    
    param.GPParameters.T_SIZE = tournamentSlider.getValue();
}//GEN-LAST:event_tournamentSliderStateChanged

private void playButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseClicked

    
    
    if ( playButton.isEnabled() )
    if ( !running ) {
        
        running     = true;
        param.Parameters.reset();
        
        disableall();

        param.WriteParam.write();
        gp = new GPRun();
        gp.start();

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pauseButton.png")));
        
    } else if ( !param.Parameters.STATE.PAUSED ) {
        
        playButton.setEnabled(false);
        param.Parameters.STATE.PAUSED   = true;
        
    } else {

        updateParameters();
        param.Parameters.STATE.PAUSED   = false;
        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pauseButton.png")));
        
    }
    
}//GEN-LAST:event_playButtonMouseClicked

private void colorButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorButtonMouseClicked
    
    if ( colorButton.isEnabled() ) new interfaces.Sets(this).setVisible(true);
   
}//GEN-LAST:event_colorButtonMouseClicked

private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseClicked

    stop();
    
}//GEN-LAST:event_stopButtonMouseClicked

private void graphButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graphButtonMouseClicked

    if ( graphButton.isEnabled() ) new interfaces.Stats(this).setVisible(true);

}//GEN-LAST:event_graphButtonMouseClicked

private void helpButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseClicked

    
}//GEN-LAST:event_helpButtonMouseClicked

private void baseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baseButtonMouseClicked

    if ( baseButton.isEnabled() && !imgDisplay.isVisible() ) imgDisplay.setVisible(true);

}//GEN-LAST:event_baseButtonMouseClicked

private void shapeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shapeButtonMouseClicked

    if ( shapeButton.isEnabled() ) new interfaces.Fitness(this).setVisible(true);
    
}//GEN-LAST:event_shapeButtonMouseClicked

private void globalButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_globalButtonMouseClicked

    if ( globalButton.isEnabled() ) new interfaces.Options(this).setVisible(true);

}//GEN-LAST:event_globalButtonMouseClicked

private void chromosomeListingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosomeListingMouseClicked

    /*
    if (evt.getClickCount() > 1 && !chromosomeListing.isSelectionEmpty() 
            && ((ChromosomeDisplay)mainPanel).editMode ) {
        
        BufferedImage img = getImage((MathProblem)problem, ind, 100, 100);
        BufferedImage imgbig = getImage((MathProblem)problem, ind, 1000, 1000);
    
    } else if (evt.getClickCount() > 1 && !chromosomeListing.isSelectionEmpty() 
            && !((ChromosomeDisplay)mainPanel).editMode ) {
        
        globals.POPULATION[chromosomeListing.getSelectedIndex()].fitness = 1.0;
        ((ChromosomeDisplay)mainPanel).reloadSingle(chromosomeListing.getSelectedIndex(), globals);
        
    }*/
   
}//GEN-LAST:event_chromosomeListingMouseClicked

private void terminalBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminalBoxActionPerformed

    double      d = -1;

    String      s = terminalBox.getText();

    // Test for integer (otherwise invalid input)
    try {
        d = Double.parseDouble(s);
    } catch (Exception ex) { }

    // Set slider value to integer (otherwise leave it)
    if ( d != -1 && d >= terminalSlider.getMinimum() && d <= terminalSlider.getMaximum()) {
        terminalSlider.setValue((int)(d * 10));
        param.GPParameters.CHOOSE_TERMINAL = d / 1000.0;
    }
    else terminalBox.setText((terminalSlider.getValue() / 10.0) + "");

}//GEN-LAST:event_terminalBoxActionPerformed

private void terminalSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_terminalSliderStateChanged

    terminalBox.setText((terminalSlider.getValue() / 10.0) + "");
    param.GPParameters.CHOOSE_TERMINAL = terminalSlider.getValue() / 1000.0;

}//GEN-LAST:event_terminalSliderStateChanged

private void mmaxBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmaxBoxActionPerformed

    int         i = -1;

    String      s = mmaxBox.getText();

    // Test for integer (otherwise invalid input)
    try {
        i = Integer.parseInt(s);
    } catch (Exception ex) { }

    // Set slider value to integer (otherwise leave it)
    if ( i != -1 && i >= mmaxSlider.getMinimum() && i <= mmaxSlider.getMaximum()) {
        mmaxSlider.setValue(i);
        param.GPParameters.MUTATION_MAX_DEPTH = i;
    }
    else mmaxBox.setText(mmaxSlider.getValue() + "");

}//GEN-LAST:event_mmaxBoxActionPerformed

private void mmaxBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_mmaxBoxPropertyChange
    // TODO add your handling code here:
}//GEN-LAST:event_mmaxBoxPropertyChange

private void mmaxBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mmaxBoxKeyTyped
    // TODO add your handling code here:
}//GEN-LAST:event_mmaxBoxKeyTyped

private void mmaxSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mmaxSliderStateChanged

    mmaxBox.setText(mmaxSlider.getValue() + "");
    param.GPParameters.MUTATION_MAX_DEPTH = mmaxSlider.getValue();

}//GEN-LAST:event_mmaxSliderStateChanged

private void cmaxBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmaxBoxActionPerformed

    int         i = -1;

    String      s = cmaxBox.getText();

    // Test for integer (otherwise invalid input)
    try {
        i = Integer.parseInt(s);
    } catch (Exception ex) { }

    // Set slider value to integer (otherwise leave it)
    if ( i != -1 && i >= cmaxSlider.getMinimum() && i <= cmaxSlider.getMaximum()) {
        cmaxSlider.setValue(i);
        param.GPParameters.CROSSOVER_MAX_DEPTH = i;
    }
    else cmaxBox.setText(cmaxSlider.getValue() + "");

}//GEN-LAST:event_cmaxBoxActionPerformed

private void cmaxBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmaxBoxPropertyChange
    // TODO add your handling code here:
}//GEN-LAST:event_cmaxBoxPropertyChange

private void cmaxBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmaxBoxKeyTyped
    // TODO add your handling code here:
}//GEN-LAST:event_cmaxBoxKeyTyped

private void cmaxSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cmaxSliderStateChanged

    cmaxBox.setText(cmaxSlider.getValue() + "");
    param.GPParameters.CROSSOVER_MAX_DEPTH = cmaxSlider.getValue();

}//GEN-LAST:event_cmaxSliderStateChanged


public void updatePopulation ( MOData[] pop ) {
    
    ((ChromosomeDisplay)mainPanel).reLoad(pop);
    
};


public void setValues ( ) {
    
    generationSlide.setValue(param.GPParameters.GENERATIONS);
    populationSlide.setValue(param.GPParameters.POP_SIZE);
    crossSlider.setValue((int)(param.GPParameters.CROSSOVER_RATE * 1000));
    mutateSlider.setValue((int)(param.GPParameters.MUTATION_RATE * 1000));
    tournamentSlider.setValue(param.GPParameters.T_SIZE);

    mmaxSlider.setValue(param.GPParameters.MUTATION_MAX_DEPTH);
    cmaxSlider.setValue(param.GPParameters.CROSSOVER_MAX_DEPTH);
    terminalSlider.setValue((int)(param.GPParameters.CHOOSE_TERMINAL * 1000));

    generationSlideStateChanged(null);
    populationSlideStateChanged(null);
    crossSliderStateChanged(null);
    mutateSliderStateChanged(null);
    tournamentSliderStateChanged(null);

    mmaxSliderStateChanged(null);
    cmaxSliderStateChanged(null);
    terminalSliderStateChanged(null);

};


public void resetProgress ( int max ) {
    
    progress.setValue(0);
    progress.setMaximum(max);
    
};


public void incrementProgress ( ) {
  
    progress.setValue(progress.getValue() + 1);
    
};



public void disableall ( ) {
    
    //globals.playState = false;
    
    mainPanel.setEnabled(false);
    ((ChromosomeDisplay)mainPanel).disableall();
    
    chromosomeListing.setEnabled(false);
    
    generationSlide.setEnabled(false);
    populationSlide.setEnabled(false);
    mutateSlider.setEnabled(false);
    crossSlider.setEnabled(false);
    tournamentSlider.setEnabled(false);
    mmaxSlider.setEnabled(false);
    cmaxSlider.setEnabled(false);
    terminalSlider.setEnabled(false);
    
    generationBox.setEnabled(false);
    populationBox.setEnabled(false);
    mutateBox.setEnabled(false);
    crossBox.setEnabled(false);
    tournamentBox.setEnabled(false);
    mmaxBox.setEnabled(false);
    cmaxBox.setEnabled(false);
    terminalBox.setEnabled(false);

    shapeButton.setEnabled(false);
    colorButton.setEnabled(false);
    globalButton.setEnabled(false);
    stopButton.setEnabled(false);
    baseButton.setEnabled(false);
    graphButton.setEnabled(false);
    
};


public void enableall ( ) {
    
    playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/playButton.png")));

    playButton.setEnabled(true);
    stopButton.setEnabled(true);
    
    mainPanel.setEnabled(true);
    ((ChromosomeDisplay)mainPanel).enableall();
    chromosomeListing.setEnabled(true);
    
    generationSlide.setEnabled(true);
    mutateSlider.setEnabled(true);
    crossSlider.setEnabled(true);
    tournamentSlider.setEnabled(true);
    
    generationBox.setEnabled(true);
    mutateBox.setEnabled(true);
    crossBox.setEnabled(true);
    tournamentBox.setEnabled(true);
    
    baseButton.setEnabled(true);
    graphButton.setEnabled(true);
    globalButton.setEnabled(true);
    
};


public void enableEverything ( ) {

    enableall();

    populationSlide.setEnabled(true);
    populationBox.setEnabled(true);

    mmaxSlider.setEnabled(true);
    cmaxSlider.setEnabled(true);
    terminalSlider.setEnabled(true);

    mmaxBox.setEnabled(true);
    cmaxBox.setEnabled(true);
    terminalBox.setEnabled(true);

    shapeButton.setEnabled(true);
    colorButton.setEnabled(true);

    ((ChromosomeDisplay)mainPanel).sortBox.setEnabled(false);

};


public void stop ( ) {

    param.Parameters.STATE.generation = param.GPParameters.GENERATIONS + 10;
    param.Parameters.STATE.PAUSED = false;
    
};


    private void updateParameters ( ) {

        param.Parameters.STATE.numGenerations = param.GPParameters.GENERATIONS;

        param.GPParameters.CROSS_OBJECT.maxDepth =
                param.GPParameters.CROSSOVER_MAX_DEPTH;
        param.GPParameters.CROSS_OBJECT.probability =
                (float)param.GPParameters.CROSSOVER_RATE;
        param.GPParameters.REP_OBJECT.probability =
                1.0f - (float)param.GPParameters.CROSSOVER_RATE;

        //param.GPParameters.MUTATE_OBJECT.probMutate =
        //        param.GPParameters.MUTATION_RATE;
        
        param.GPParameters.TSELECT_OBJECT.size = param.GPParameters.T_SIZE;
        
    };


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baseButton;
    public javax.swing.JList chromosomeListing;
    private javax.swing.JScrollPane chromosomeScroll;
    private javax.swing.JTextField cmaxBox;
    private javax.swing.JSlider cmaxSlider;
    private javax.swing.JButton colorButton;
    public javax.swing.JTextPane console;
    private javax.swing.JTextField crossBox;
    private javax.swing.JSlider crossSlider;
    private javax.swing.JLabel crossoverLabel;
    private javax.swing.JLabel crossoverLabel1;
    private javax.swing.JTextField generationBox;
    private javax.swing.JLabel generationLabel;
    private javax.swing.JLabel generationLabel1;
    private javax.swing.JLabel generationLabel2;
    private javax.swing.JSlider generationSlide;
    private javax.swing.JButton globalButton;
    private javax.swing.JButton graphButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JPanel mainPanel;
    private javax.swing.JTextField mmaxBox;
    private javax.swing.JSlider mmaxSlider;
    private javax.swing.JTextField mutateBox;
    private javax.swing.JSlider mutateSlider;
    private javax.swing.JLabel mutationLabel;
    private javax.swing.JPanel parameterPanel;
    private javax.swing.JPanel parameterinPanel;
    public javax.swing.JButton playButton;
    private javax.swing.JTextField populationBox;
    private javax.swing.JLabel populationLabel;
    private javax.swing.JSlider populationSlide;
    public javax.swing.JProgressBar progress;
    private javax.swing.JButton shapeButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JTextField terminalBox;
    private javax.swing.JSlider terminalSlider;
    private javax.swing.JTextField tournamentBox;
    private javax.swing.JLabel tournamentLabel;
    private javax.swing.JSlider tournamentSlider;
    // End of variables declaration//GEN-END:variables

}
