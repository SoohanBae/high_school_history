package com.dgsw.client;
   import javax.swing.*;
    import javax.swing.plaf.*;
    import javax.swing.plaf.metal.*;
    public class TitleColor
    {
        public static void main_helper (String args[])
        {
            JFrame f = new JFrame ();
            f.setDefaultCloseOperation 
            (
                JFrame.DISPOSE_ON_CLOSE
            );
            f.setSize (300, 300);
            f.setLocationRelativeTo (null);

            f.setUndecorated ( true );
            f.getRootPane ().setWindowDecorationStyle
            (
                JRootPane.FRAME
            );

            JPanel panel = new JPanel ();
            panel.setBackground ( java.awt.Color.white );
            f.setContentPane ( panel );

            DefaultMetalTheme z =
            new DefaultMetalTheme ()
            {
                //inactive title color
                public ColorUIResource
                getWindowTitleInactiveBackground() 
                { 
                    return new ColorUIResource 
                        (java.awt.Color.orange); 
                }

                //active title color
                public ColorUIResource
                getWindowTitleBackground() 
                { 
                    return new ColorUIResource 
                        (java.awt.Color.orange); 
                }
                //start ActiveBumps
                public ColorUIResource 
                getPrimaryControlHighlight() 
                { 
                    return new ColorUIResource 
                        (java.awt.Color.orange); 
                }
                public ColorUIResource 
                getPrimaryControlDarkShadow() 
                { 
                    return new ColorUIResource 
                        (java.awt.Color.orange); 
                }

                public ColorUIResource 
                getPrimaryControl() 
                { 
                    return new ColorUIResource 
                        (java.awt.Color.orange); 
                }
                //end ActiveBumps

                //start inActiveBumps
                public ColorUIResource 
                getControlHighlight ()
                {
                    return new ColorUIResource 
                        (java.awt.Color.orange); 
                }

                public ColorUIResource 
                getControlDarkShadow ()
                {
                    return new ColorUIResource 
                        (java.awt.Color.orange); 
                }

                public ColorUIResource 
                getControl ()
                {
                    return new ColorUIResource 
                        (java.awt.Color.orange); 
                }
                //end inActiveBumps



            };



            MetalLookAndFeel.setCurrentTheme
            (
                z
            );

            try
            {
                UIManager.setLookAndFeel
                (
                    new MetalLookAndFeel ()
                );
            }
            catch (Exception e)
            {
                e.printStackTrace ();
            }   

            SwingUtilities.updateComponentTreeUI (f);


            f.setVisible (true);


        }
        public static void main (final String args[])
        {
            SwingUtilities.invokeLater
            (
                new Runnable ()
                {
                    public void run ()
                    {
                        main_helper ( args );
                    }
                }
            );
        }
    }