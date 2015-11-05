package param;


import java.util.List;
import java.util.LinkedList;


/**
 * This class stores information which is used to create the parameter file for
 * each project.
 *
 * @author Steve Bergen
 */

public class FunctionSet {


    public static final String      setup = "gp.fs.size = 2\n" +
                                        "gp.fs.0 = ec.gp.GPFunctionSet\n" +
                                        "gp.fs.0.name = f0\n" +
                                        "gp.fs.0.info = ec.gp.GPFuncInfo\n" +
                                        "gp.fs.1 = ec.gp.GPFunctionSet\n" +
                                        "gp.fs.1.name = f1\n" +
                                        "gp.fs.1.info = ec.gp.GPFuncInfo";

    
    /* -------------------------------------------------------------------------
     * ------------- RETURN TYPES FOR FUNCTIONS AND TERMINALS ------------------
     * ---------------------------------------------------------------------- */
    
    public static final String      returnS1 = "gp.type.s.size = 0";
    
    public static final String[]    returnTypes = {
                                                "gp.type.a.0.name = nil",
                                                "gp.type.a.1.name = int",
                                                "gp.type.a.2.name = double",
                                                "gp.type.a.3.name = shape",
                                                "gp.type.a.4.name = rgb",
                                                "gp.type.a.5.name = radius",
                                                "gp.type.a.6.name = render",
                                                "gp.type.a.7.name = x",
                                                "gp.type.a.8.name = y",
                                                "gp.type.a.9.name = texture",
                                                "gp.type.a.10.name = doublep",
                                                "gp.type.a.11.name = ldouble",
                                                "gp.type.a.12.name = lint"
                                                };
    
    public static final String      returnS2 = 
            "gp.type.a.size = " + returnTypes.length;
    
    
    /* -------------------------------------------------------------------------
     * ------------ PARAMETER TYPES FOR FUNCTIONS AND TERMINALS ----------------
     * ---------------------------------------------------------------------- */

    public static final String[]    paramTypes = {
                                        "gp.nc.0 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.0.name = intTerminal\n" +
                                        "gp.nc.0.returns = int\n" +
                                        "gp.nc.0.size = 0",

                                        "gp.nc.1 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.1.name = doubleTerminal\n" +
                                        "gp.nc.1.returns = double\n" +
                                        "gp.nc.1.size = 0",

                                        "gp.nc.2 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.2.name = rgbFunction\n" +
                                        "gp.nc.2.returns = rgb\n" +
                                        "gp.nc.2.size = 3\n" +
                                        "gp.nc.2.child.0 = double\n" +
                                        "gp.nc.2.child.1 = double\n" +
                                        "gp.nc.2.child.2 = double",

                                        "gp.nc.3 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.3.name = double2Fct\n" +
                                        "gp.nc.3.returns = double\n" +
                                        "gp.nc.3.size = 2\n" +
                                        "gp.nc.3.child.0 = double\n" +
                                        "gp.nc.3.child.1 = double",

                                        "gp.nc.4 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.4.name = circleTerminal\n" +
                                        "gp.nc.4.returns = shape\n" +
                                        "gp.nc.4.size = 5\n" +
                                        "gp.nc.4.child.0 = x\n" +
                                        "gp.nc.4.child.1 = y\n" +
                                        "gp.nc.4.child.2 = radius\n" +
                                        "gp.nc.4.child.3 = doublep\n" +
                                        "gp.nc.4.child.4 = rgb",

                                        "gp.nc.5 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.5.name = circleLink\n" +
                                        "gp.nc.5.returns = shape\n" +
                                        "gp.nc.5.size = 6\n" +
                                        "gp.nc.5.child.0 = x\n" +
                                        "gp.nc.5.child.1 = y\n" +
                                        "gp.nc.5.child.2 = radius\n" +
                                        "gp.nc.5.child.3 = doublep\n" +
                                        "gp.nc.5.child.4 = rgb\n" +
                                        "gp.nc.5.child.5 = shape",

                                        "gp.nc.6 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.6.name = double1Fct\n" +
                                        "gp.nc.6.returns = double\n" +
                                        "gp.nc.6.size = 1\n" +
                                        "gp.nc.6.child.0 = double",

                                        "gp.nc.7 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.7.name = radiusTerminal\n" +
                                        "gp.nc.7.returns = radius\n" +
                                        "gp.nc.7.size = 0",

                                        "gp.nc.8 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.8.name = renderFunction\n" +
                                        "gp.nc.8.returns = render\n" +
                                        "gp.nc.8.size = 2\n" +
                                        "gp.nc.8.child.0 = rgb\n" +
                                        "gp.nc.8.child.1 = shape",

                                        "gp.nc.9 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.9.name = renderTexture\n" +
                                        "gp.nc.9.returns = render\n" +
                                        "gp.nc.9.size = 1\n" +
                                        "gp.nc.9.child.0 = rgb",

                                        "gp.nc.10 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.10.name = XTerminal\n" +
                                        "gp.nc.10.returns = x\n" +
                                        "gp.nc.10.size = 0",

                                        "gp.nc.11 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.11.name = YTerminal\n" +
                                        "gp.nc.11.returns = y\n" +
                                        "gp.nc.11.size = 0",

                                        "gp.nc.12 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.12.name = rectangleTerminal\n" +
                                        "gp.nc.12.returns = shape\n" +
                                        "gp.nc.12.size = 6\n" +
                                        "gp.nc.12.child.0 = x\n" +
                                        "gp.nc.12.child.1 = y\n" +
                                        "gp.nc.12.child.2 = radius\n" +
                                        "gp.nc.12.child.3 = radius\n" +
                                        "gp.nc.12.child.4 = doublep\n" +
                                        "gp.nc.12.child.5 = rgb",

                                        "gp.nc.13 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.13.name = rectangleLink\n" +
                                        "gp.nc.13.returns = shape\n" +
                                        "gp.nc.13.size = 7\n" +
                                        "gp.nc.13.child.0 = x\n" +
                                        "gp.nc.13.child.1 = y\n" +
                                        "gp.nc.13.child.2 = radius\n" +
                                        "gp.nc.13.child.3 = radius\n" +
                                        "gp.nc.13.child.4 = doublep\n" +
                                        "gp.nc.13.child.5 = rgb\n" +
                                        "gp.nc.13.child.6 = shape",

                                        "gp.nc.14 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.14.name = triangleTerminal\n" +
                                        "gp.nc.14.returns = shape\n" +
                                        "gp.nc.14.size = 9\n" +
                                        "gp.nc.14.child.0 = x\n" +
                                        "gp.nc.14.child.1 = y\n" +
                                        "gp.nc.14.child.2 = x\n" +
                                        "gp.nc.14.child.3 = y\n" +
                                        "gp.nc.14.child.4 = x\n" +
                                        "gp.nc.14.child.5 = y\n" +
                                        "gp.nc.14.child.6 = radius\n" +
                                        "gp.nc.14.child.7 = doublep\n" +
                                        "gp.nc.14.child.8 = rgb",

                                        "gp.nc.15 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.15.name = triangleLink\n" +
                                        "gp.nc.15.returns = shape\n" +
                                        "gp.nc.15.size = 10\n" +
                                        "gp.nc.15.child.0 = x\n" +
                                        "gp.nc.15.child.1 = y\n" +
                                        "gp.nc.15.child.2 = x\n" +
                                        "gp.nc.15.child.3 = y\n" +
                                        "gp.nc.15.child.4 = x\n" +
                                        "gp.nc.15.child.5 = y\n" +
                                        "gp.nc.15.child.6 = radius\n" +
                                        "gp.nc.15.child.7 = doublep\n" +
                                        "gp.nc.15.child.8 = rgb\n" +
                                        "gp.nc.15.child.9 = shape",

                                        "gp.nc.16 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.16.name = textureRGB\n" +
                                        "gp.nc.16.returns = rgb\n" +
                                        "gp.nc.16.size = 1\n" +
                                        "gp.nc.16.child.0 = texture",

                                        "gp.nc.17 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.17.name = mixTexture\n" +
                                        "gp.nc.17.returns = texture\n" +
                                        "gp.nc.17.size = 2\n" +
                                        "gp.nc.17.child.0 = texture\n" +
                                        "gp.nc.17.child.1 = texture",

                                        "gp.nc.18 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.18.name = mixTextureRGB\n" +
                                        "gp.nc.18.returns = texture\n" +
                                        "gp.nc.18.size = 2\n" +
                                        "gp.nc.18.child.0 = texture\n" +
                                        "gp.nc.18.child.1 = rgb",

                                        "gp.nc.19 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.19.name = textureTerminal\n" +
                                        "gp.nc.19.returns = texture\n" +
                                        "gp.nc.19.size = 1\n" +
                                        "gp.nc.19.child.0 = doublep",

                                        "gp.nc.20 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.20.name = texture2Fct\n" +
                                        "gp.nc.20.returns = texture\n" +
                                        "gp.nc.20.size = 3\n" +
                                        "gp.nc.20.child.0 = doublep\n" +
                                        "gp.nc.20.child.1 = doublep\n" +
                                        "gp.nc.20.child.2 = doublep",

                                        "gp.nc.21 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.21.name = doubleParam\n" +
                                        "gp.nc.21.returns = doublep\n" +
                                        "gp.nc.21.size = 0",

                                        "gp.nc.22 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.22.name = texture1Fct\n" +
                                        "gp.nc.22.returns = texture\n" +
                                        "gp.nc.22.size = 2\n" +
                                        "gp.nc.22.child.0 = ldouble\n" +
                                        "gp.nc.22.child.1 = doublep",

                                        "gp.nc.23 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.23.name = terminalLDouble\n" +
                                        "gp.nc.23.returns = ldouble\n" +
                                        "gp.nc.23.size = 0",

                                        "gp.nc.24 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.24.name = terminalLInt\n" +
                                        "gp.nc.24.returns = lint\n" +
                                        "gp.nc.24.size = 0",

                                        "gp.nc.25 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.25.name = textureWave\n" +
                                        "gp.nc.25.returns = texture\n" +
                                        "gp.nc.25.size = 5\n" +
                                        "gp.nc.25.child.0 = lint\n" +
                                        "gp.nc.25.child.1 = lint\n" +
                                        "gp.nc.25.child.2 = lint\n" +
                                        "gp.nc.25.child.3 = lint\n" +
                                        "gp.nc.25.child.4 = doublep",

                                        "gp.nc.26 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.26.name = texturePipe\n" +
                                        "gp.nc.26.returns = texture\n" +
                                        "gp.nc.26.size = 2\n" +
                                        "gp.nc.26.child.0 = doublep\n" +
                                        "gp.nc.26.child.1 = texture",

                                        "gp.nc.27 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.27.name = eShape\n" +
                                        "gp.nc.27.returns = shape\n" +
                                        "gp.nc.27.size = 0",

                                        "gp.nc.28 = ec.gp.GPNodeConstraints\n" +
                                        "gp.nc.28.name = noiseFct\n" +
                                        "gp.nc.28.returns = double\n" +
                                        "gp.nc.28.size = 1\n" +
                                        "gp.nc.28.child.0 = doublep"
                                        };

    public static final String      paramS1 = "gp.nc.size = "
                                                + paramTypes.length;


    /* -------------------------------------------------------------------------
     * --------------------- FUNCTION AND TERMINAL SETS ------------------------
     * ---------------------------------------------------------------------- */


    public static final String[][]  treeFunctions = {
                    {"multiobjective.functions.RenderTexture", "renderTexture"},
                    {"multiobjective.functions.Render", "renderFunction"}
                    };

    public static final String[][]  shapeFunctions = {
                    {"multiobjective.functions.Circle", "circleTerminal"},
                    {"multiobjective.functions.CircleLink", "circleLink"},
                    {"multiobjective.functions.Rectangle", "rectangleTerminal"},
                    {"multiobjective.functions.RectangleLink", "rectangleLink"},
                    {"multiobjective.functions.Triangle", "triangleTerminal"},
                    {"multiobjective.functions.TriangleLink", "triangleLink"}
                    };

    public static final String[][]  textureFunctions = {
                    {"multiobjective.texture.TextureMix", "mixTexture"},
                    {"multiobjective.texture.TextureColorMix", "mixTextureRGB"},
                    {"multiobjective.texture.Grass", "textureTerminal"},
                    {"multiobjective.texture.Mandelbrot", "texture2Fct"},
                    {"multiobjective.texture.Noise", "texture1Fct"},
                    {"multiobjective.texture.MarbleSig", "textureWave"},
                    {"multiobjective.texture.Fire", "textureWave"},
                    {"multiobjective.texture.Furry", "texturePipe"}
                    };

    public static final String[][]  mathFunctions = {
                    {"multiobjective.math.Add", "double2Fct"},
                    {"multiobjective.math.Sub", "double2Fct"},
                    {"multiobjective.math.Mul", "double2Fct"},
                    {"multiobjective.math.Div", "double2Fct"},
                    {"multiobjective.math.Sin", "double1Fct"},
                    {"multiobjective.math.Cos", "double1Fct"},
                    {"multiobjective.math.Pow", "double2Fct"},
                    {"multiobjective.math.Log", "double1Fct"},
                    {"multiobjective.math.Mod", "double2Fct"},
                    {"multiobjective.math.Noise", "noiseFct"}
                    };

    public static final String[][]  RGB = {
                    {"multiobjective.functions.RGB", "rgbFunction"}
                    };

    public static final String[][]  SHAPE = {
                    {"multiobjective.functions.EmptyShape", "eShape"}
                    };

    public static final String[][]  TEXTURERGB = {
                    {"multiobjective.texture.TexturetoRGB", "textureRGB"}
                    };

    public static final String[][]  Terminals = {
                    {"multiobjective.math.TerminalInt", "intTerminal"},
                    {"multiobjective.math.TerminalDouble", "doubleTerminal"},
                    {"multiobjective.math.TerminalRadius", "radiusTerminal"},
                    {"multiobjective.math.TerminalX", "XTerminal"},
                    {"multiobjective.math.TerminalY", "YTerminal"},
                    {"multiobjective.math.X", "doubleTerminal"},
                    {"multiobjective.math.Y", "doubleTerminal"},
                    {"multiobjective.math.TerminalDouble", "doubleParam"},
                    {"multiobjective.math.LargeTerminalDouble", "terminalLDouble"},
                    {"multiobjective.math.LargeTerminalInt", "terminalLInt"}
                    };


    public static List              TREE_FUNCTIONS;
    public static List              SHAPE_FUNCTIONS;
    public static List              MATH_FUNCTIONS;
    public static List              TEXTURE_FUNCTIONS;

    public static int               RADIUS      = 20;


    public static void init ( ) {

        TREE_FUNCTIONS = new LinkedList<Integer>();
        TREE_FUNCTIONS.add(0);
        TREE_FUNCTIONS.add(1);

        SHAPE_FUNCTIONS = new LinkedList<Integer>();
        SHAPE_FUNCTIONS.add(0);
        SHAPE_FUNCTIONS.add(1);

        MATH_FUNCTIONS = new LinkedList<Integer>();
        MATH_FUNCTIONS.add(0);
        MATH_FUNCTIONS.add(1);
        MATH_FUNCTIONS.add(2);
        MATH_FUNCTIONS.add(3);

        TEXTURE_FUNCTIONS = new LinkedList<Integer>();

    };


    /* -------------------------------------------------------------------------
     * ------------------------ FILE PARSER FUNCTIONS --------------------------
     * ---------------------------------------------------------------------- */


    /**
     * Prints the stats in this file to a string object for file writing.
     *
     * @return                  String representation
     */

    public static String print ( ) {

        String s = "";

        // FUNCTION SETUP
        s += "gp.fs.size = " + (GPParameters.ADFS + 1) + "\n";
        for ( int i = 0; i < GPParameters.ADFS + 1; i++ ) {
            s += "gp.fs." + i + " = ec.gp.GPFunctionSet\n";
            s += "gp.fs." + i + ".name = f" + i + "\n";
            s += "gp.fs." + i + ".info = ec.gp.GPFuncInfo\n";
        }

        s += returnS1 + "\n";
        s += returnS2 + "\n\n";
        for ( int i = 0; i < returnTypes.length; i++ )
            s += returnTypes[i] + "\n";
        s += "\n";

        s += paramS1 + "\n\n";
        for ( int i = 0; i < paramTypes.length; i++ )
            s += paramTypes[i] + "\n\n";

        // num added for ADF function
        int size = 0;
        if ( TEXTURE_FUNCTIONS.size() > 0 ) size += 1;
        //if ( SHAPE_FUNCTIONS.size() > 0 ) size += 1;
        size += (GPParameters.ADFS + RGB.length + Terminals.length +
                TREE_FUNCTIONS.size() + SHAPE_FUNCTIONS.size() +
                MATH_FUNCTIONS.size() + TEXTURE_FUNCTIONS.size());
        s += "gp.fs.0.size = " + size + "\n\n";

        int count = 0;
        int n;
        for ( int i = 0; i < TREE_FUNCTIONS.size(); i++ ) {
            n = (Integer)TREE_FUNCTIONS.get(i);
            s += "gp.fs.0.func." + count + " = " + treeFunctions[n][0] + "\n";
            s += "gp.fs.0.func." + count + ".nc = " + treeFunctions[n][1] + "\n";
            count++;
        } s += "\n";

        for ( int i = 0; i < SHAPE_FUNCTIONS.size(); i++ ) {
            n = (Integer)SHAPE_FUNCTIONS.get(i);
            /*if ( i == 0 ) {
                s += "gp.fs.0.func." + count + " = " + SHAPE[0] + "\n";
                s += "gp.fs.0.func." + count + ".nc = " + SHAPE[1] + "\n";
                count++;
            }*/
            s += "gp.fs.0.func." + count + " = " + shapeFunctions[n][0] + "\n";
            s += "gp.fs.0.func." + count + ".nc = " + shapeFunctions[n][1] + "\n";
            count++;
        } s += "\n";
        
        for ( int i = 0; i < MATH_FUNCTIONS.size(); i++ ) {
            n = (Integer)MATH_FUNCTIONS.get(i);
            s += "gp.fs.0.func." + count + " = " + mathFunctions[n][0] + "\n";
            s += "gp.fs.0.func." + count + ".nc = " + mathFunctions[n][1] + "\n";
            count++;
        } s += "\n";

        if ( TEXTURE_FUNCTIONS.size() > 0 ) {
        for ( int i = 0; i < TEXTURERGB.length; i++ ) {
            s += "gp.fs.0.func." + count + " = " + TEXTURERGB[i][0] + "\n";
            s += "gp.fs.0.func." + count + ".nc = " + TEXTURERGB[i][1] + "\n";
            count++;
        } s += "\n";
        }

        for ( int i = 0; i < TEXTURE_FUNCTIONS.size(); i++ ) {
            n = (Integer)TEXTURE_FUNCTIONS.get(i);
            s += "gp.fs.0.func." + count + " = " + textureFunctions[n][0] + "\n";
            s += "gp.fs.0.func." + count + ".nc = " + textureFunctions[n][1] + "\n";
            count++;
        } s += "\n";

        for ( int i = 0; i < RGB.length; i++ ) {
            s += "gp.fs.0.func." + count + " = " + RGB[i][0] + "\n";
            s += "gp.fs.0.func." + count + ".nc = " + RGB[i][1] + "\n";
            count++;
        } s += "\n";

        for ( int i = 0; i < Terminals.length; i++ ) {
            s += "gp.fs.0.func." + count + " = " + Terminals[i][0] + "\n";
            s += "gp.fs.0.func." + count + ".nc = " + Terminals[i][1] + "\n";
            count++;
        } s += "\n";

        for ( int i = 1; i <= GPParameters.ADFS; i++ ) {
            s += "gp.fs.0.func." + count + " = ec.gp.ADF\n";
            s += "gp.fs.0.func." + count + ".tree = " + i + "\n";
            s += "gp.fs.0.func." + count + ".name = " + (i - 1) + "\n";
            s += "gp.fs.0.func." + count + ".nc = rgbFunction\n\n";
            count++;
        }

        // ---------------------- FUNCTION SET FOR ADF -------------------------

        for ( int c = 1; c < GPParameters.ADFS + 1; c++ ) {

            count = 0;

            size = 0;
            if ( TEXTURE_FUNCTIONS.size() > 0 ) size += 1;
            size += (RGB.length + Terminals.length +
                    MATH_FUNCTIONS.size() + TEXTURE_FUNCTIONS.size());
            s += "gp.fs." + c + ".size = " + size + "\n\n";

            for ( int i = 0; i < RGB.length; i++ ) {
                s += "gp.fs." + c + ".func." + count + " = " + RGB[i][0] + "\n";
                s += "gp.fs." + c + ".func." + count + ".nc = " + RGB[i][1] + "\n";
                count++;
            } s += "\n";


            for ( int i = 0; i < MATH_FUNCTIONS.size(); i++ ) {
                n = (Integer)MATH_FUNCTIONS.get(i);
                s += "gp.fs." + c + ".func." + count + " = " + mathFunctions[n][0] + "\n";
                s += "gp.fs." + c + ".func." + count + ".nc = " + mathFunctions[n][1] + "\n";
                count++;
            } s += "\n";

            if ( TEXTURE_FUNCTIONS.size() > 0 ) {
            for ( int i = 0; i < TEXTURERGB.length; i++ ) {
                s += "gp.fs." + c + ".func." + count + " = " + TEXTURERGB[i][0] + "\n";
                s += "gp.fs." + c + ".func." + count + ".nc = " + TEXTURERGB[i][1] + "\n";
                count++;
            } s += "\n";
            }

            for ( int i = 0; i < TEXTURE_FUNCTIONS.size(); i++ ) {
                n = (Integer)TEXTURE_FUNCTIONS.get(i);
                s += "gp.fs." + c + ".func." + count + " = " + textureFunctions[n][0] + "\n";
                s += "gp.fs." + c + ".func." + count + ".nc = " + textureFunctions[n][1] + "\n";
                count++;
            } s += "\n";

            for ( int i = 0; i < Terminals.length; i++ ) {
                s += "gp.fs." + c + ".func." + count + " = " + Terminals[i][0] + "\n";
                s += "gp.fs." + c + ".func." + count + ".nc = " + Terminals[i][1] + "\n";
                count++;
            } s += "\n";

        }

        return s;

    };


};