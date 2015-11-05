/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.texgen.textures.Texture;

/**
 * Window class that renders the texture onto a panel with options for 
 * aliasing and fast rendering. This window is threaded so you can stop the 
 * process any time. The texture to render is held in the texture property.
 * 
 * @author Andy Gibson
 */
public class TextureWindow extends JFrame implements RenderListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1019228146485873775L;
	
	private TexturePanel panel;
    private javax.swing.JButton btnStart;
    private JPanel controlPanel;
    private JCheckBox aliasCheck;
    private JCheckBox gradualCheck;
    private Texture texture;

    public TextureWindow() {
        setSize(300, 300);
        controlPanel = new JPanel();
        add(controlPanel, BorderLayout.SOUTH);

        panel = new TexturePanel();
        add(panel);
        addComponentListener(panel);

        btnStart = new JButton("Start");
        controlPanel.add(btnStart);

        aliasCheck = new JCheckBox("Anti-Alias");
        aliasCheck.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                panel.getImage().setAntiAliased(aliasCheck.isSelected());
            }
        });
        controlPanel.add(aliasCheck);

        gradualCheck = new JCheckBox("Render Gradually");
        gradualCheck.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                panel.getImage().setGradualDisplay(gradualCheck.isSelected());
            }
        });
        controlPanel.add(gradualCheck);



        btnStart.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.getImage().setTexture(getTexture());
                btnStart.setEnabled(false);
                new Thread(panel.getImage()).start();
            }
        });



        panel.getImage().setListener(this);
    }

    @Override
    protected void frameInit() {
        super.frameInit();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void notifyComplete(Object source) {
        btnStart.setEnabled(true);
        panel.repaint();
    }

	public void notifyState(Object source, String message) {
        setTitle(message);
        panel.repaint();

    }

    public JPanel getControlPanel() {
        return controlPanel;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}
