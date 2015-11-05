package util;

import javax.swing.*;
import java.awt.*;

import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.ColorUIResource;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;


public class TooltipLF extends MetalLookAndFeel {
    
    
    @Override
    protected void initSystemColorDefaults (UIDefaults table) {     
        
        super.initSystemColorDefaults(table);        
        table.put("info", new ColorUIResource(255, 247, 200));    
        
    };

    
    @Override
    protected void initComponentDefaults (UIDefaults table) {
        
        super.initComponentDefaults(table);

        Border border = BorderFactory.createLineBorder(new Color(76,79,83));
        table.put("ToolTip.border", border);
        table.put("ToolTip.font",
           new FontUIResource("Tahoma", Font.PLAIN, 10));

    };
    
    
};