
JTexGen - Procedural Texture Generation Library
=========================================================
version 1.1, July 2009

This software is distributed under the terms of the FSF Lesser Gnu
Public License (see lgpl.txt). 

The code to generate the Mandelbrot set was taken from a applet by Eckhard Roessel published on the Java Boutique site (http://javaboutique.internet.com/Mandelbrot/). His Mandelbrot browser on that page is well worth a look. 

Getting started
---------------

The distribution contains the JTexGen.jar file which you will need to include in your project. The doc folder contains documentation in html and pdf and javadoc in the api folder.

1. Start up your favorite IDE
2. Create a new java project. 
3. Add the JTextGen jar to the project
4. Create a class with a main() method if you haven't already got one.
5. In the main method put : 

	public static void main(String[] args) {
		TextureViewer.show(new ComplexMarble());
	}

6. Execute it and click the Start button on the window that appears.
7. Go create some textures of your own.


Other
-----
 
Most classes are documented, with at least a description of what they do. 
The documentation contains instructions on creating textures and using many of the pre-built in ones. 


