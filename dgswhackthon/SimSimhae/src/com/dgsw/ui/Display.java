package com.dgsw.ui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public interface Display {
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int width = gd.getDisplayMode().getWidth()/2;
	int height = (int) (gd.getDisplayMode().getHeight()/1.5f);
	
}
