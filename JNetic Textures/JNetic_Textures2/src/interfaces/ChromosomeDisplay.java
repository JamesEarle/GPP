package interfaces;


import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import multiobjective.*;
import java.text.*;
import fitness.*;
import javax.swing.DefaultComboBoxModel;


/**
 * This class is used to display the chromosome images and fitnesses. It stores
 * population information and updates to the population must be communicated
 * to this class.
 *
 * @author Steve Bergen - Brock University
 * @version 1.0.0 - 25/04/2009
 */

public class ChromosomeDisplay extends javax.swing.JPanel implements ActionListener {

    public  JPopupMenu              popup;
    
    public  MOData[]                list;
    public  JList                   chromosomeList;         // Fitness list
    private Frame                   main;
    
    private JLabel                  labels[][];             // Labels (fitness)
    private JPanel                  images[];               // Chromosome images
    private JPanel                  containers[];           // Chromosome panels
    
    public  int                     currentIndex = 0;       // Display index
   
    public  boolean                 editMode = true;        // Set if editable
    
    private int                     selected = -1;
    private DecimalFormat           df;
    public  DefaultComboBoxModel    model;
    private boolean                 reload = false;

    /**
     * Class constructor. Creates the display list for chromosomes.
     * 
     * @param main      Calling interface object
     * @param globals   Globals Variable Object
    */
    
    public ChromosomeDisplay ( Frame main ) {
        
        this.main = main;
        initComponents();
 
        df = new DecimalFormat("#.######");
        
        // Create containers
        containers = new JPanel[8];
        containers[0] = chromosome1;
        containers[1] = chromosome2;
        containers[2] = chromosome3;
        containers[3] = chromosome4;
        containers[4] = chromosome5;
        containers[5] = chromosome6;
        containers[6] = chromosome7;
        containers[7] = chromosome8;
        
        // Create fitness labels
        labels = new JLabel[8][4];
        labels[0][0] = fitness1;
        labels[1][0] = fitness2;
        labels[2][0] = fitness3;
        labels[3][0] = fitness4;
        labels[4][0] = fitness5;
        labels[5][0] = fitness6;
        labels[6][0] = fitness7;
        labels[7][0] = fitness8;
        
        labels[0][1] = fitness1a;
        labels[1][1] = fitness2a;
        labels[2][1] = fitness3a;
        labels[3][1] = fitness4a;
        labels[4][1] = fitness5a;
        labels[5][1] = fitness6a;
        labels[6][1] = fitness7a;
        labels[7][1] = fitness8a;
        
        labels[0][2] = fitness1b;
        labels[1][2] = fitness2b;
        labels[2][2] = fitness3b;
        labels[3][2] = fitness4b;
        labels[4][2] = fitness5b;
        labels[5][2] = fitness6b;
        labels[6][2] = fitness7b;
        labels[7][2] = fitness8b;
        
        labels[0][3] = fitness1c;
        labels[1][3] = fitness2c;
        labels[2][3] = fitness3c;
        labels[3][3] = fitness4c;
        labels[4][3] = fitness5c;
        labels[5][3] = fitness6c;
        labels[6][3] = fitness7c;
        labels[7][3] = fitness8c;
        
        // Create image panels
        images = new JPanel[8];
        images[0] = cdisplay1;
        images[1] = cdisplay2;
        images[2] = cdisplay3;
        images[3] = cdisplay4;
        images[4] = cdisplay5;
        images[5] = cdisplay6;
        images[6] = cdisplay7;
        images[7] = cdisplay8;

        model = new javax.swing.DefaultComboBoxModel(new String[] { "Rank", "Mean", "STD" });
        sortBox.setModel(model);

        createMenu();
        
        disableall();
        
    };


    public void setFitnesses ( ) {

        model.removeAllElements();

        model.addElement("Rank");

        for ( int i = 0; i < param.Parameters.FITNESS_FUNCTIONS.size(); i++ )
            model.addElement(((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(i)).getName());

    };
    
    
    /**
     * Clears all panels and removes information.
    */
    
    public void clear ( ) {
      
        for (int i = 0; i < 8; i++) {
            ((ImageDisplayer)images[i]).setImage(null);
            labels[i][0].setText(" ");
            labels[i][1].setText(" ");
            labels[i][2].setText(" ");
            labels[i][3].setText(" ");
        }
        
    };
    
    
    /**
     * Displays all panels from population.
    */
    public void show ( ) {
      
        for (int i = 0; i < 8; i++) containers[i].setVisible(true);
        
    };
    
    
    /**
     * Called to make updates to the population and re-display the new top 8
     * chromosomes
     * 
     * @param list      Population of chromosomes
     * @param globals   Globals Variable Object
    */

    public void reLoad ( MOData list[]) {

        reload = true;
        this.list = sort(list);
        
        // Set max number of chromosomes
        int max = 8;
        if (this.list.length < 8) max = this.list.length;


        // Display chromosome images and fitnesses
        for (int i = 0; i < max; i++) {
            ((ImageDisplayer)images[i]).setImage(resize(list[i].getImage()));
            //labels[i][0].setText("Rank : " + list[i].fit + "");
            int c = 0;
            for ( int j = 0; j < list[i].fitness.length; j++ ) {
                FitnessFunction f =
                        ((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(c));
                labels[i][j].setText(f.getName() + " : " + df.format(list[i].raw[c]) + "");
                c++;
            }
        }

        displayText.setText("Displaying Textures 1 to " + max + " of " + this.list.length);
        
        DefaultListModel model = new DefaultListModel();
        
        // Update fitnesses in the chromosome listing
        for (int i = 0; i < this.list.length; i++)
            model.addElement("   Rank : " + this.list[i].fit);
        
        currentIndex = 0;
        chromosomeList.setModel(model);
        reload = false;
     
    };
    
    
    
    /**
     * Sorts the population according to fitness.
     * 
     * @param list      Population of chromosomes
     * @return Sorted population
    */

    public MOData[] sort ( MOData list[] ) {
  
        boolean swapped;
        boolean test;

        int index;
        FitnessFunction f = null;

        if ( sortBox.getSelectedIndex() == 0 )
            index = 0;
        else {
            index = sortBox.getSelectedIndex() - 1;
            f = (FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(index);
        }

        // Perform bubblesort
        do {
            swapped = false;
            for (int i = 0; i < list.length - 1; i++) {

                if ( sortBox.getSelectedIndex() == 0 )
                    test = list[i].fit > list[i+1].fit;
                else 
                    test = !f.isBetter(list[i].fitness[index], list[i+1].fitness[index]);

                if (test) {
                    MOData temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
                    swapped = true;
                }

            }
        } while (swapped);

        return list;
        
    }
    
    
    /**
     * Resizes an image to fit inside the chromosome image panel.
     * 
     * @param img       Image to be resized
     * @return Resized image
    */
    
    public static BufferedImage resize ( BufferedImage img ) { 
        
        int newW, newH;
        
        int w = img.getWidth();  
        int h = img.getHeight();  
        
        // Get new dimensions and percent changes
        double dif1 = 122.0 / 139.0;
        double dif2 = (double)w / (double)h;

        // Set new dimensions depending on current image dimensions
        if (w >= h || dif2 > dif1) {
            newW = 122;
            newH = (int)((122.0 / (double)w ) * (double)h);
        } else {
            newH = 139;
            newW = (int)((139.0 / (double)h ) * (double)w);
        }
        
        // Resize image using Java's functions
        BufferedImage dimg = dimg = new BufferedImage(newW, newH, img.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
        g.dispose();  
        
        return dimg;  
        
    };


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chromosome1 = new javax.swing.JPanel();
        cdisplay1 = new javax.swing.JPanel();
        fitness1 = new javax.swing.JLabel();
        fitness1a = new javax.swing.JLabel();
        fitness1c = new javax.swing.JLabel();
        fitness1b = new javax.swing.JLabel();
        chromosome2 = new javax.swing.JPanel();
        cdisplay2 = new javax.swing.JPanel();
        fitness2 = new javax.swing.JLabel();
        fitness2a = new javax.swing.JLabel();
        fitness2c = new javax.swing.JLabel();
        fitness2b = new javax.swing.JLabel();
        chromosome3 = new javax.swing.JPanel();
        cdisplay3 = new javax.swing.JPanel();
        fitness3 = new javax.swing.JLabel();
        fitness3a = new javax.swing.JLabel();
        fitness3b = new javax.swing.JLabel();
        fitness3c = new javax.swing.JLabel();
        chromosome4 = new javax.swing.JPanel();
        cdisplay4 = new javax.swing.JPanel();
        fitness4 = new javax.swing.JLabel();
        fitness4a = new javax.swing.JLabel();
        fitness4b = new javax.swing.JLabel();
        fitness4c = new javax.swing.JLabel();
        chromosome5 = new javax.swing.JPanel();
        cdisplay5 = new javax.swing.JPanel();
        fitness5 = new javax.swing.JLabel();
        fitness5a = new javax.swing.JLabel();
        fitness5b = new javax.swing.JLabel();
        fitness5c = new javax.swing.JLabel();
        chromosome6 = new javax.swing.JPanel();
        cdisplay6 = new javax.swing.JPanel();
        fitness6 = new javax.swing.JLabel();
        fitness6a = new javax.swing.JLabel();
        fitness6b = new javax.swing.JLabel();
        fitness6c = new javax.swing.JLabel();
        chromosome7 = new javax.swing.JPanel();
        cdisplay7 = new javax.swing.JPanel();
        fitness7 = new javax.swing.JLabel();
        fitness7a = new javax.swing.JLabel();
        fitness7b = new javax.swing.JLabel();
        fitness7c = new javax.swing.JLabel();
        chromosome8 = new javax.swing.JPanel();
        cdisplay8 = new javax.swing.JPanel();
        fitness8 = new javax.swing.JLabel();
        fitness8a = new javax.swing.JLabel();
        fitness8b = new javax.swing.JLabel();
        fitness8c = new javax.swing.JLabel();
        bar = new javax.swing.JPanel();
        bbButton = new javax.swing.JButton();
        bButton = new javax.swing.JButton();
        ffButton = new javax.swing.JButton();
        fButton = new javax.swing.JButton();
        displayText = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        sortBox = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chromosome1.setBackground(new java.awt.Color(255, 255, 255));
        chromosome1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        chromosome1.setToolTipText("Chromosome (right-click for options)");
        chromosome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chromosome1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chromosome1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chromosome1MouseExited(evt);
            }
        });

        cdisplay1 = new ImageDisplayer(null);
        cdisplay1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cdisplay1.setToolTipText("Chromosome (right-click for options)");
        cdisplay1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cdisplay1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cdisplay1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cdisplay1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout cdisplay1Layout = new javax.swing.GroupLayout(cdisplay1);
        cdisplay1.setLayout(cdisplay1Layout);
        cdisplay1Layout.setHorizontalGroup(
            cdisplay1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        cdisplay1Layout.setVerticalGroup(
            cdisplay1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        fitness1.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness1.setText(" ");
        fitness1.setToolTipText("Chromosome (right-click for options)");

        fitness1a.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness1a.setText(" ");
        fitness1a.setToolTipText("Chromosome (right-click for options)");

        fitness1c.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness1c.setText(" ");
        fitness1c.setToolTipText("Chromosome (right-click for options)");

        fitness1b.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness1b.setText(" ");
        fitness1b.setToolTipText("Chromosome (right-click for options)");

        javax.swing.GroupLayout chromosome1Layout = new javax.swing.GroupLayout(chromosome1);
        chromosome1.setLayout(chromosome1Layout);
        chromosome1Layout.setHorizontalGroup(
            chromosome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chromosome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cdisplay1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fitness1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness1a, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness1b, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness1c, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        chromosome1Layout.setVerticalGroup(
            chromosome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cdisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fitness1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness1a, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness1b, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness1c, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        chromosome2.setBackground(new java.awt.Color(255, 255, 255));
        chromosome2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        chromosome2.setToolTipText("Chromosome (right-click for options)");
        chromosome2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chromosome2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chromosome2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chromosome2MouseExited(evt);
            }
        });

        cdisplay2 = new ImageDisplayer(null);
        cdisplay2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cdisplay2.setToolTipText("Chromosome (right-click for options)");
        cdisplay2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cdisplay2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cdisplay2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cdisplay2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout cdisplay2Layout = new javax.swing.GroupLayout(cdisplay2);
        cdisplay2.setLayout(cdisplay2Layout);
        cdisplay2Layout.setHorizontalGroup(
            cdisplay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        cdisplay2Layout.setVerticalGroup(
            cdisplay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        fitness2.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness2.setText(" ");
        fitness2.setToolTipText("Chromosome (right-click for options)");

        fitness2a.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness2a.setText(" ");
        fitness2a.setToolTipText("Chromosome (right-click for options)");

        fitness2c.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness2c.setText(" ");
        fitness2c.setToolTipText("Chromosome (right-click for options)");

        fitness2b.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness2b.setText(" ");
        fitness2b.setToolTipText("Chromosome (right-click for options)");

        javax.swing.GroupLayout chromosome2Layout = new javax.swing.GroupLayout(chromosome2);
        chromosome2.setLayout(chromosome2Layout);
        chromosome2Layout.setHorizontalGroup(
            chromosome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chromosome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cdisplay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fitness2, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness2a, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness2b, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness2c, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        chromosome2Layout.setVerticalGroup(
            chromosome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cdisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fitness2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness2a, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness2b, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness2c, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        chromosome3.setBackground(new java.awt.Color(255, 255, 255));
        chromosome3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        chromosome3.setToolTipText("Chromosome (right-click for options)");
        chromosome3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chromosome3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chromosome3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chromosome3MouseExited(evt);
            }
        });

        cdisplay3 = new ImageDisplayer(null);
        cdisplay3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cdisplay3.setToolTipText("Chromosome (right-click for options)");
        cdisplay3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cdisplay3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cdisplay3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cdisplay3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout cdisplay3Layout = new javax.swing.GroupLayout(cdisplay3);
        cdisplay3.setLayout(cdisplay3Layout);
        cdisplay3Layout.setHorizontalGroup(
            cdisplay3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        cdisplay3Layout.setVerticalGroup(
            cdisplay3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        fitness3.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness3.setText(" ");
        fitness3.setToolTipText("Chromosome (right-click for options)");

        fitness3a.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness3a.setText(" ");
        fitness3a.setToolTipText("Chromosome (right-click for options)");

        fitness3b.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness3b.setText(" ");
        fitness3b.setToolTipText("Chromosome (right-click for options)");

        fitness3c.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness3c.setText(" ");
        fitness3c.setToolTipText("Chromosome (right-click for options)");

        javax.swing.GroupLayout chromosome3Layout = new javax.swing.GroupLayout(chromosome3);
        chromosome3.setLayout(chromosome3Layout);
        chromosome3Layout.setHorizontalGroup(
            chromosome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chromosome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cdisplay3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fitness3, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness3a, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness3b, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness3c, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        chromosome3Layout.setVerticalGroup(
            chromosome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cdisplay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fitness3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness3a, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness3b, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness3c, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        chromosome4.setBackground(new java.awt.Color(255, 255, 255));
        chromosome4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        chromosome4.setToolTipText("Chromosome (right-click for options)");
        chromosome4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chromosome4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chromosome4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chromosome4MouseExited(evt);
            }
        });

        cdisplay4 = new ImageDisplayer(null);
        cdisplay4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cdisplay4.setToolTipText("Chromosome (right-click for options)");
        cdisplay4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cdisplay4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cdisplay4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cdisplay4MouseExited(evt);
            }
        });

        javax.swing.GroupLayout cdisplay4Layout = new javax.swing.GroupLayout(cdisplay4);
        cdisplay4.setLayout(cdisplay4Layout);
        cdisplay4Layout.setHorizontalGroup(
            cdisplay4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        cdisplay4Layout.setVerticalGroup(
            cdisplay4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        fitness4.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness4.setText(" ");
        fitness4.setToolTipText("Chromosome (right-click for options)");

        fitness4a.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness4a.setText(" ");
        fitness4a.setToolTipText("Chromosome (right-click for options)");

        fitness4b.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness4b.setText(" ");
        fitness4b.setToolTipText("Chromosome (right-click for options)");

        fitness4c.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness4c.setText(" ");
        fitness4c.setToolTipText("Chromosome (right-click for options)");

        javax.swing.GroupLayout chromosome4Layout = new javax.swing.GroupLayout(chromosome4);
        chromosome4.setLayout(chromosome4Layout);
        chromosome4Layout.setHorizontalGroup(
            chromosome4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chromosome4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cdisplay4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fitness4, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness4a, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness4b, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness4c, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        chromosome4Layout.setVerticalGroup(
            chromosome4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cdisplay4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fitness4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness4a, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness4b, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness4c, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        chromosome5.setBackground(new java.awt.Color(255, 255, 255));
        chromosome5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        chromosome5.setToolTipText("Chromosome (right-click for options)");
        chromosome5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chromosome5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chromosome5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chromosome5MouseExited(evt);
            }
        });

        cdisplay5 = new ImageDisplayer(null);
        cdisplay5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cdisplay5.setToolTipText("Chromosome (right-click for options)");
        cdisplay5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cdisplay5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cdisplay5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cdisplay5MouseExited(evt);
            }
        });

        javax.swing.GroupLayout cdisplay5Layout = new javax.swing.GroupLayout(cdisplay5);
        cdisplay5.setLayout(cdisplay5Layout);
        cdisplay5Layout.setHorizontalGroup(
            cdisplay5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        cdisplay5Layout.setVerticalGroup(
            cdisplay5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        fitness5.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness5.setText(" ");
        fitness5.setToolTipText("Chromosome (right-click for options)");

        fitness5a.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness5a.setText(" ");
        fitness5a.setToolTipText("Chromosome (right-click for options)");

        fitness5b.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness5b.setText(" ");
        fitness5b.setToolTipText("Chromosome (right-click for options)");

        fitness5c.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness5c.setText(" ");
        fitness5c.setToolTipText("Chromosome (right-click for options)");

        javax.swing.GroupLayout chromosome5Layout = new javax.swing.GroupLayout(chromosome5);
        chromosome5.setLayout(chromosome5Layout);
        chromosome5Layout.setHorizontalGroup(
            chromosome5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chromosome5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cdisplay5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fitness5, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness5a, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness5b, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness5c, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        chromosome5Layout.setVerticalGroup(
            chromosome5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cdisplay5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fitness5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness5a, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness5b, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness5c, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        chromosome6.setBackground(new java.awt.Color(255, 255, 255));
        chromosome6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        chromosome6.setToolTipText("Chromosome (right-click for options)");
        chromosome6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chromosome6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chromosome6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chromosome6MouseExited(evt);
            }
        });

        cdisplay6 = new ImageDisplayer(null);
        cdisplay6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cdisplay6.setToolTipText("Chromosome (right-click for options)");
        cdisplay6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cdisplay6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cdisplay6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cdisplay6MouseExited(evt);
            }
        });

        javax.swing.GroupLayout cdisplay6Layout = new javax.swing.GroupLayout(cdisplay6);
        cdisplay6.setLayout(cdisplay6Layout);
        cdisplay6Layout.setHorizontalGroup(
            cdisplay6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        cdisplay6Layout.setVerticalGroup(
            cdisplay6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        fitness6.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness6.setText(" ");
        fitness6.setToolTipText("Chromosome (right-click for options)");

        fitness6a.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness6a.setText(" ");
        fitness6a.setToolTipText("Chromosome (right-click for options)");

        fitness6b.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness6b.setText(" ");
        fitness6b.setToolTipText("Chromosome (right-click for options)");

        fitness6c.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness6c.setText(" ");
        fitness6c.setToolTipText("Chromosome (right-click for options)");

        javax.swing.GroupLayout chromosome6Layout = new javax.swing.GroupLayout(chromosome6);
        chromosome6.setLayout(chromosome6Layout);
        chromosome6Layout.setHorizontalGroup(
            chromosome6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chromosome6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cdisplay6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fitness6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness6a, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness6b, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness6c, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        chromosome6Layout.setVerticalGroup(
            chromosome6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cdisplay6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fitness6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness6a, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness6b, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness6c, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        chromosome7.setBackground(new java.awt.Color(255, 255, 255));
        chromosome7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        chromosome7.setToolTipText("Chromosome (right-click for options)");
        chromosome7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chromosome7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chromosome7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chromosome7MouseExited(evt);
            }
        });

        cdisplay7 = new ImageDisplayer(null);
        cdisplay7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cdisplay7.setToolTipText("Chromosome (right-click for options)");
        cdisplay7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cdisplay7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cdisplay7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cdisplay7MouseExited(evt);
            }
        });

        javax.swing.GroupLayout cdisplay7Layout = new javax.swing.GroupLayout(cdisplay7);
        cdisplay7.setLayout(cdisplay7Layout);
        cdisplay7Layout.setHorizontalGroup(
            cdisplay7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        cdisplay7Layout.setVerticalGroup(
            cdisplay7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        fitness7.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness7.setText(" ");
        fitness7.setToolTipText("Chromosome (right-click for options)");

        fitness7a.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness7a.setText(" ");
        fitness7a.setToolTipText("Chromosome (right-click for options)");

        fitness7b.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness7b.setText(" ");
        fitness7b.setToolTipText("Chromosome (right-click for options)");

        fitness7c.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness7c.setText(" ");
        fitness7c.setToolTipText("Chromosome (right-click for options)");

        javax.swing.GroupLayout chromosome7Layout = new javax.swing.GroupLayout(chromosome7);
        chromosome7.setLayout(chromosome7Layout);
        chromosome7Layout.setHorizontalGroup(
            chromosome7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chromosome7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cdisplay7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fitness7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness7a, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness7b, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness7c, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        chromosome7Layout.setVerticalGroup(
            chromosome7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cdisplay7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fitness7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness7a, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness7b, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness7c, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        chromosome8.setBackground(new java.awt.Color(255, 255, 255));
        chromosome8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        chromosome8.setToolTipText("Chromosome (right-click for options)");
        chromosome8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chromosome8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chromosome8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chromosome8MouseExited(evt);
            }
        });

        cdisplay8 = new ImageDisplayer(null);
        cdisplay8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cdisplay8.setToolTipText("Chromosome (right-click for options)");
        cdisplay8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cdisplay8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cdisplay8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cdisplay8MouseExited(evt);
            }
        });

        javax.swing.GroupLayout cdisplay8Layout = new javax.swing.GroupLayout(cdisplay8);
        cdisplay8.setLayout(cdisplay8Layout);
        cdisplay8Layout.setHorizontalGroup(
            cdisplay8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        cdisplay8Layout.setVerticalGroup(
            cdisplay8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        fitness8.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness8.setText(" ");
        fitness8.setToolTipText("Chromosome (right-click for options)");

        fitness8a.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness8a.setText(" ");
        fitness8a.setToolTipText("Chromosome (right-click for options)");

        fitness8b.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness8b.setText(" ");
        fitness8b.setToolTipText("Chromosome (right-click for options)");

        fitness8c.setFont(new java.awt.Font("Tahoma", 0, 10));
        fitness8c.setText(" ");
        fitness8c.setToolTipText("Chromosome (right-click for options)");

        javax.swing.GroupLayout chromosome8Layout = new javax.swing.GroupLayout(chromosome8);
        chromosome8.setLayout(chromosome8Layout);
        chromosome8Layout.setHorizontalGroup(
            chromosome8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chromosome8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cdisplay8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fitness8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness8a, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness8b, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(fitness8c, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        chromosome8Layout.setVerticalGroup(
            chromosome8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chromosome8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cdisplay8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fitness8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness8a, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness8b, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fitness8c, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        bar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bbButton.setBackground(java.awt.Color.lightGray);
        bbButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backButton2.png"))); // NOI18N
        bbButton.setToolTipText("First page");
        bbButton.setBorder(null);
        bbButton.setFocusable(false);
        bbButton.setOpaque(false);
        bbButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bbButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bbButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bbButtonMouseExited(evt);
            }
        });

        bButton.setBackground(java.awt.Color.lightGray);
        bButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backButton.png"))); // NOI18N
        bButton.setToolTipText("Previous page");
        bButton.setBorder(null);
        bButton.setFocusable(false);
        bButton.setOpaque(false);
        bButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bButtonMouseExited(evt);
            }
        });

        ffButton.setBackground(java.awt.Color.lightGray);
        ffButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forwardButton2.png"))); // NOI18N
        ffButton.setToolTipText("Last page");
        ffButton.setBorder(null);
        ffButton.setFocusable(false);
        ffButton.setOpaque(false);
        ffButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ffButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ffButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ffButtonMouseExited(evt);
            }
        });

        fButton.setBackground(java.awt.Color.lightGray);
        fButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forwardButton.png"))); // NOI18N
        fButton.setToolTipText("Next page");
        fButton.setBorder(null);
        fButton.setFocusable(false);
        fButton.setOpaque(false);
        fButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fButtonMouseExited(evt);
            }
        });

        displayText.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        displayText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        displayText.setText("Displaying Textures 0 to 0 of 0");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("Sort by");

        sortBox.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        sortBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rank", "Mean", "STD" }));
        sortBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortBoxItemStateChanged(evt);
            }
        });
        sortBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barLayout = new javax.swing.GroupLayout(bar);
        bar.setLayout(barLayout);
        barLayout.setHorizontalGroup(
            barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortBox, 0, 110, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(bbButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayText, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ffButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        barLayout.setVerticalGroup(
            barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bbButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addComponent(bButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addComponent(displayText, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addGroup(barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addComponent(sortBox, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
            .addComponent(fButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addComponent(ffButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(chromosome1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chromosome5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chromosome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chromosome6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chromosome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chromosome4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chromosome7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chromosome8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chromosome4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chromosome3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chromosome2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chromosome1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chromosome8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chromosome7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chromosome6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chromosome5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
/* -------------------------------------------------------------------------- */
/* - These methods are called for mouseover events, and highlighting -------- */
/* -------------------------------------------------------------------------- */
    
private void chromosome1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome1MouseEntered
    if (chromosome1.isEnabled()) chromosome1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
}//GEN-LAST:event_chromosome1MouseEntered

private void chromosome1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome1MouseExited
clearAll();
}//GEN-LAST:event_chromosome1MouseExited

private void chromosome2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome2MouseEntered
    if (chromosome2.isEnabled()) chromosome2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
}//GEN-LAST:event_chromosome2MouseEntered

private void chromosome2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome2MouseExited
clearAll();
}//GEN-LAST:event_chromosome2MouseExited

private void chromosome3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome3MouseEntered
    if (chromosome3.isEnabled()) chromosome3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
}//GEN-LAST:event_chromosome3MouseEntered

private void chromosome3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome3MouseExited
clearAll();
}//GEN-LAST:event_chromosome3MouseExited

private void chromosome4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome4MouseEntered
    if (chromosome4.isEnabled()) chromosome4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
}//GEN-LAST:event_chromosome4MouseEntered

private void chromosome4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome4MouseExited
clearAll();
}//GEN-LAST:event_chromosome4MouseExited

private void chromosome5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome5MouseEntered
    if (chromosome5.isEnabled()) chromosome5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
}//GEN-LAST:event_chromosome5MouseEntered

private void chromosome5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome5MouseExited
clearAll();
}//GEN-LAST:event_chromosome5MouseExited

private void chromosome6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome6MouseEntered
    if (chromosome6.isEnabled()) chromosome6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
}//GEN-LAST:event_chromosome6MouseEntered

private void chromosome6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome6MouseExited
clearAll();
}//GEN-LAST:event_chromosome6MouseExited

private void chromosome7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome7MouseEntered
    if (chromosome7.isEnabled()) chromosome7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
}//GEN-LAST:event_chromosome7MouseEntered

private void chromosome7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome7MouseExited
clearAll();
}//GEN-LAST:event_chromosome7MouseExited

private void chromosome8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome8MouseEntered
    if (chromosome8.isEnabled()) chromosome8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
}//GEN-LAST:event_chromosome8MouseEntered

private void chromosome8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome8MouseExited
clearAll();
}//GEN-LAST:event_chromosome8MouseExited


/* -------------------------------------------------------------------------- */
/* - These methods are called for mouseover events on buttons, and for      - */
/* - Updating the chromosomes that are displayed (forward, back, etc.)      - */
/* -------------------------------------------------------------------------- */

private void bbButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bbButtonMouseClicked
    
    
    if (bbButton.isEnabled()) {
        
        clearAll();
        
        int max = 8;
        if (this.list.length < 8) max = this.list.length;

        for (int i = 0; i < max; i++) {
            ((ImageDisplayer)images[i]).setImage(resize(list[i].getImage()));
            //labels[i][0].setText("Rank " + list[i].fit + "");
            int c = 0;
            for ( int j = 0; j < list[i].fitness.length; j++ ) {
                FitnessFunction f =
                        ((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(c));
                labels[i][j].setText(f.getName() + " : " + df.format(list[i].raw[c]) + "");
                c++;
            }
        }

        displayText.setText("Displaying Textures 1 to " + max + " of " + this.list.length);
        currentIndex = 0;
    }
     
    
}//GEN-LAST:event_bbButtonMouseClicked

private void bbButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bbButtonMouseEntered
if (bbButton.isEnabled()) bbButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_bbButtonMouseEntered

private void bbButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bbButtonMouseExited
bbButton.setBorder(null);
}//GEN-LAST:event_bbButtonMouseExited

private void bButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bButtonMouseClicked

    
    if (bButton.isEnabled()) {

        clearAll();
        
        int diff = currentIndex - 8;
        if (diff >= 0) {
        
            currentIndex = currentIndex - 8;

            int index = currentIndex;
            for (int i = 0; i < 8; i++) {
                containers[i].setVisible(true);
                images[i].setVisible(true);
                for ( int j = 0; j < 4; j++ ) labels[i][j].setVisible(true);
                ((ImageDisplayer)images[i]).setImage(resize(list[index].getImage()));
                //labels[i][0].setText("Rank " + list[index].fit + "");
                int c = 0;
                for ( int j = 0; j < list[index].fitness.length; j++ ) {
                    FitnessFunction f =
                            ((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(c));
                    labels[i][j].setText(f.getName() + " : " + df.format(list[index].raw[c]) + "");
                    c++;
                }
                index++;
            }

            displayText.setText("Displaying Textures " + (currentIndex + 1) + 
                    " to " + index + " of " + this.list.length);

        }
        
    }

}//GEN-LAST:event_bButtonMouseClicked

private void bButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bButtonMouseEntered
if (bButton.isEnabled()) bButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_bButtonMouseEntered

private void bButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bButtonMouseExited
bButton.setBorder(null);
}//GEN-LAST:event_bButtonMouseExited

private void ffButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ffButtonMouseClicked


    if (ffButton.isEnabled()) {
        
        clearAll();
        
        currentIndex = (((int)(this.list.length / 8)) * 8);
        int index = currentIndex;
        
        clear();
        
        int max = (this.list.length - currentIndex);

        for (int i = 0; i < max; i++) {
            containers[i].setVisible(true);
            images[i].setVisible(true);
            for ( int j = 0; j < 4; j++ ) labels[i][j].setVisible(true);
            ((ImageDisplayer)images[i]).setImage(resize(list[index].getImage()));
            //labels[i][0].setText("Rank " + list[index].fit + "");
            int c = 0;
            for ( int j = 0; j < list[index].fitness.length; j++ ) {
                FitnessFunction f =
                        ((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(c));
                labels[i][j].setText(f.getName() + " : " + df.format(list[index].raw[c]) + "");
                c++;
            }
            index++;
        }

        displayText.setText("Displaying Textures " + (currentIndex + 1) + 
                    " to " + index + " of " + this.list.length);

    }
    
}//GEN-LAST:event_ffButtonMouseClicked

private void ffButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ffButtonMouseEntered
if (ffButton.isEnabled()) ffButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_ffButtonMouseEntered

private void ffButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ffButtonMouseExited
ffButton.setBorder(null);
}//GEN-LAST:event_ffButtonMouseExited

private void fButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fButtonMouseClicked
   
    
    if (fButton.isEnabled()) {
        
        clearAll();

        int diff = this.list.length - (currentIndex + 8);
        if (diff > 0) {
        
            currentIndex = currentIndex + 8;
            
            int max = 8;
            if (diff < 8) max = diff;
            
            clear();

            int index = currentIndex;
            for (int i = 0; i < max; i++) {
                containers[i].setVisible(true);
                images[i].setVisible(true);
                for ( int j = 0; j < 4; j++ ) labels[i][j].setVisible(true);
                ((ImageDisplayer)images[i]).setImage(resize(list[index].getImage()));
                //labels[i][0].setText("Rank " + list[index].fit + "");
                int c = 0;
                for ( int j = 0; j < list[index].fitness.length; j++ ) {
                    FitnessFunction f =
                            ((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(c));
                    labels[i][j].setText(f.getName() + " : " + df.format(list[index].raw[c]) + "");
                    c++;
                }
                index++;
            }

            displayText.setText("Displaying Textures " + (currentIndex + 1) + 
                    " to " + index + " of " + this.list.length);

        }
        
    }
    
}//GEN-LAST:event_fButtonMouseClicked

private void fButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fButtonMouseEntered
if (fButton.isEnabled()) fButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
}//GEN-LAST:event_fButtonMouseEntered

private void fButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fButtonMouseExited
fButton.setBorder(null);
}//GEN-LAST:event_fButtonMouseExited


/* -------------------------------------------------------------------------- */
/* - These methods are called when a chromosome is clicked in the display in- */
/* - order to edit a chromosome.                                            - */
/* -------------------------------------------------------------------------- */

private void cdisplay1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay1MouseClicked
if ( this.list != null && currentIndex + 0 < this.list.length ) displayEdit(currentIndex + 0, evt);
}//GEN-LAST:event_cdisplay1MouseClicked

private void cdisplay2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay2MouseClicked
if ( this.list != null && currentIndex + 1 < this.list.length ) displayEdit(currentIndex + 1, evt);
}//GEN-LAST:event_cdisplay2MouseClicked

private void cdisplay3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay3MouseClicked
if ( this.list != null && currentIndex + 2 < this.list.length ) displayEdit(currentIndex + 2, evt);
}//GEN-LAST:event_cdisplay3MouseClicked

private void cdisplay4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay4MouseClicked
if ( this.list != null && currentIndex + 3 < this.list.length ) displayEdit(currentIndex + 3, evt);
}//GEN-LAST:event_cdisplay4MouseClicked

private void cdisplay5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay5MouseClicked
if (this.list != null && currentIndex + 4 < this.list.length ) displayEdit(currentIndex + 4, evt);
}//GEN-LAST:event_cdisplay5MouseClicked

private void cdisplay6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay6MouseClicked
if ( this.list != null && currentIndex + 5 < this.list.length ) displayEdit(currentIndex + 5, evt);
}//GEN-LAST:event_cdisplay6MouseClicked

private void cdisplay7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay7MouseClicked
if ( this.list != null && currentIndex + 6 < this.list.length ) displayEdit(currentIndex + 6, evt);
}//GEN-LAST:event_cdisplay7MouseClicked

private void cdisplay8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay8MouseClicked
if ( this.list != null && currentIndex + 7 < this.list.length ) displayEdit(currentIndex + 7, evt);
}//GEN-LAST:event_cdisplay8MouseClicked

private void chromosome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome1MouseClicked
if ( this.list != null && currentIndex + 0 < this.list.length ) displayEdit(currentIndex + 0, evt);
}//GEN-LAST:event_chromosome1MouseClicked

private void chromosome2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome2MouseClicked
if ( this.list != null && currentIndex + 1 < this.list.length ) displayEdit(currentIndex + 1, evt);
}//GEN-LAST:event_chromosome2MouseClicked

private void chromosome3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome3MouseClicked
if ( this.list != null && currentIndex + 2 < this.list.length ) displayEdit(currentIndex + 2, evt);
}//GEN-LAST:event_chromosome3MouseClicked

private void chromosome4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome4MouseClicked
if ( this.list != null && currentIndex + 3 < this.list.length ) displayEdit(currentIndex + 3, evt);
}//GEN-LAST:event_chromosome4MouseClicked

private void chromosome5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome5MouseClicked
if (this.list != null && currentIndex + 4 < this.list.length ) displayEdit(currentIndex + 4, evt);
}//GEN-LAST:event_chromosome5MouseClicked

private void chromosome6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome6MouseClicked
if ( this.list != null && currentIndex + 5 < this.list.length ) displayEdit(currentIndex + 5, evt);
}//GEN-LAST:event_chromosome6MouseClicked

private void chromosome7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome7MouseClicked
if ( this.list != null && currentIndex + 6 < this.list.length ) displayEdit(currentIndex + 6, evt);
}//GEN-LAST:event_chromosome7MouseClicked

private void chromosome8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chromosome8MouseClicked
if ( this.list != null && currentIndex + 7 < this.list.length ) displayEdit(currentIndex + 7, evt);
}//GEN-LAST:event_chromosome8MouseClicked

private void cdisplay1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay1MouseEntered

    if (chromosome1.isEnabled()) {
        chromosome1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selected = currentIndex + 0;
    }
}//GEN-LAST:event_cdisplay1MouseEntered

private void cdisplay2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay2MouseEntered
if (chromosome2.isEnabled()) {
    chromosome2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    selected = currentIndex + 1;
}
}//GEN-LAST:event_cdisplay2MouseEntered

private void cdisplay3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay3MouseEntered
if (chromosome3.isEnabled()) {
    chromosome3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    selected = currentIndex + 2;
}
}//GEN-LAST:event_cdisplay3MouseEntered

private void cdisplay4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay4MouseEntered
if (chromosome4.isEnabled()) {
    chromosome4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    selected = currentIndex + 3;
}
}//GEN-LAST:event_cdisplay4MouseEntered

private void cdisplay5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay5MouseEntered
if (chromosome5.isEnabled()) {
    chromosome5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    selected = currentIndex + 4;
}
}//GEN-LAST:event_cdisplay5MouseEntered

private void cdisplay6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay6MouseEntered
if (chromosome6.isEnabled()) {
    chromosome6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    selected = currentIndex + 5;
}
}//GEN-LAST:event_cdisplay6MouseEntered

private void cdisplay7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay7MouseEntered
if (chromosome7.isEnabled()) {
    chromosome7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    selected = currentIndex + 6;
}
}//GEN-LAST:event_cdisplay7MouseEntered

private void cdisplay8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay8MouseEntered
if (chromosome8.isEnabled()) {
    chromosome8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    selected = currentIndex + 7;
}
}//GEN-LAST:event_cdisplay8MouseEntered

private void cdisplay1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay1MouseExited
clearAll();
}//GEN-LAST:event_cdisplay1MouseExited

private void cdisplay2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay2MouseExited
clearAll();
}//GEN-LAST:event_cdisplay2MouseExited

private void cdisplay3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay3MouseExited
clearAll();
}//GEN-LAST:event_cdisplay3MouseExited

private void cdisplay4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay4MouseExited
clearAll();
}//GEN-LAST:event_cdisplay4MouseExited

private void cdisplay5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay5MouseExited
clearAll();
}//GEN-LAST:event_cdisplay5MouseExited

private void cdisplay6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay6MouseExited
clearAll();
}//GEN-LAST:event_cdisplay6MouseExited

private void cdisplay7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay7MouseExited
clearAll();
}//GEN-LAST:event_cdisplay7MouseExited

private void cdisplay8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdisplay8MouseExited
clearAll();
}//GEN-LAST:event_cdisplay8MouseExited

private void sortBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortBoxActionPerformed

}//GEN-LAST:event_sortBoxActionPerformed

private void sortBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortBoxItemStateChanged

    if ( sortBox.isEnabled() && !reload ) {
        reLoad(this.list);
    }

}//GEN-LAST:event_sortBoxItemStateChanged


    /**
     * Checks for double-click in the event, and displays the chromosome editor
     * if so.
     * 
     * @param i         Index of the chromosome clicked
     * @param evt       Chromosome click event object
    */

    public void displayEdit ( int i, java.awt.event.MouseEvent evt ) {

        selected = i;
        
        if ( evt.getClickCount() > 1 ) {

            displayChromosomeEditor(selected);

        }

    };


    /**
     * Clears all borders in the display
    */

    public void clearAll ( ) {
        
        for (int i = 0; i < 8; i++) containers[i].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    
    };


    /**
     * Called to enable all button functions.
    */

    public void disableall ( ) {

        bbButton.setEnabled(false);
        bButton.setEnabled(false);
        ffButton.setEnabled(false);
        fButton.setEnabled(false);

        sortBox.setEnabled(false);

        for (int i = 0; i < 8; i++) containers[i].setEnabled(false);

    };


    /**
     * Called to enable all button functions.
    */

    public void enableall ( ) {

        bbButton.setEnabled(true);
        bButton.setEnabled(true);
        ffButton.setEnabled(true);
        fButton.setEnabled(true);

        sortBox.setEnabled(true);

        for (int i = 0; i < 8; i++) containers[i].setEnabled(true);

    };
    

    public void createMenu ( ) {

        popup = new JPopupMenu();

        JMenuItem menuItem = new JMenuItem("View Full Image...");
        menuItem.addActionListener(this);
        popup.add(menuItem);

        menuItem = new JMenuItem("Open Chromosome Editor...");
        menuItem.addActionListener(this);
        popup.add(menuItem);

        PopupListener listen = new PopupListener(popup);

        for ( int i = 0; i < 8; i++ ) {
            images[i].addMouseListener(listen);
            containers[i].addMouseListener(listen);
            for ( int j = 0; j < labels[i].length; j++ )
                labels[i][j].addMouseListener(listen);
        }

    };


    @Override
    public void actionPerformed ( ActionEvent e ) {

        String s = ((JMenuItem)e.getSource()).getText();

        if ( selected > -1 && selected < list.length ) {

            if ( s.compareTo("View Full Image...") == 0 )
                displayImageViewer(selected);

            else if ( s.compareTo("Open Chromosome Editor...") == 0 )
                displayChromosomeEditor(selected);

        }

    };


    public void displayChromosomeEditor ( int index ) {

        new ChromosomeWindow(param.Parameters.FRAME, list[index]).setVisible(true);
        /*
        String tree;
        StringWriter s = new StringWriter();
        PrintWriter out = null;

        try {
            out = new PrintWriter(s);
        } catch ( Exception ex ) {};

        list[index].program.printIndividual(param.Parameters.STATE, out);

        tree = s.toString();
        String list2[] = tree.split("\n");
        for ( int i = 0; i < list2.length; i++ )
            System.out.println(list2[i]);

        util.ImageUtil.saveImage(list[index].getScaledImage(2.0f), "saved.png");

        out.close();
        try { s.close(); } catch ( Exception e ) {};
         * */

        
    };
    
    
    public void displayImageViewer ( int index ) {
        
        new BaseImage(main, index).setVisible(true);
        
    };


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bButton;
    public javax.swing.JPanel bar;
    public javax.swing.JButton bbButton;
    public javax.swing.JPanel cdisplay1;
    public javax.swing.JPanel cdisplay2;
    public javax.swing.JPanel cdisplay3;
    public javax.swing.JPanel cdisplay4;
    public javax.swing.JPanel cdisplay5;
    public javax.swing.JPanel cdisplay6;
    public javax.swing.JPanel cdisplay7;
    public javax.swing.JPanel cdisplay8;
    public javax.swing.JPanel chromosome1;
    public javax.swing.JPanel chromosome2;
    public javax.swing.JPanel chromosome3;
    public javax.swing.JPanel chromosome4;
    public javax.swing.JPanel chromosome5;
    public javax.swing.JPanel chromosome6;
    public javax.swing.JPanel chromosome7;
    public javax.swing.JPanel chromosome8;
    public javax.swing.JLabel displayText;
    public javax.swing.JButton fButton;
    public javax.swing.JButton ffButton;
    public javax.swing.JLabel fitness1;
    public javax.swing.JLabel fitness1a;
    public javax.swing.JLabel fitness1b;
    public javax.swing.JLabel fitness1c;
    public javax.swing.JLabel fitness2;
    public javax.swing.JLabel fitness2a;
    public javax.swing.JLabel fitness2b;
    public javax.swing.JLabel fitness2c;
    public javax.swing.JLabel fitness3;
    public javax.swing.JLabel fitness3a;
    public javax.swing.JLabel fitness3b;
    public javax.swing.JLabel fitness3c;
    public javax.swing.JLabel fitness4;
    public javax.swing.JLabel fitness4a;
    public javax.swing.JLabel fitness4b;
    public javax.swing.JLabel fitness4c;
    public javax.swing.JLabel fitness5;
    public javax.swing.JLabel fitness5a;
    public javax.swing.JLabel fitness5b;
    public javax.swing.JLabel fitness5c;
    public javax.swing.JLabel fitness6;
    public javax.swing.JLabel fitness6a;
    public javax.swing.JLabel fitness6b;
    public javax.swing.JLabel fitness6c;
    public javax.swing.JLabel fitness7;
    public javax.swing.JLabel fitness7a;
    public javax.swing.JLabel fitness7b;
    public javax.swing.JLabel fitness7c;
    public javax.swing.JLabel fitness8;
    public javax.swing.JLabel fitness8a;
    public javax.swing.JLabel fitness8b;
    public javax.swing.JLabel fitness8c;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JComboBox sortBox;
    // End of variables declaration//GEN-END:variables

}


class PopupListener extends MouseAdapter {
    
    JPopupMenu popup;
    
    
    public PopupListener ( JPopupMenu popup ) {
        
        super();
        
        this.popup = popup;
        
    };
    
    
    public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }
}
