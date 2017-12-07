package contacts.display;/*
 * To use this file with one of your own Java applications, add the file to
 * your base src path with your main class and change the package <name> below
 * to match your package name.
 *
 * ansiText static class version 2
 */

import java.util.Collections;

/**
 * Everything within this Java class is static, so the class does not need to
 * be loaded as an object. Simply calling it directly in code instantiates it.
 *
 * Sample calls:
 *    ansiText.clearScreen();					// Clears the screen and resets it to default colors (white/black)
 *
 *	  ansiText.printSampleBackgroundColors();	// Prints out a sample of the background colors available
 *												// with the code that printed the sample line
 *
 *    ansiText.printSampleTextColors();			// Prints out a sample of the foreground colors available
 *												// with the code that printed the sample line
 *
 *    ansiText.printMessage(15,					// row
 *							25,					// column
 *							ansiText.magenta,	// foreground
 *							ansiText.black,		// background
 *							ansiText.bold,		// style
 *							"This is text",		// textOutput
 *							true);				// nextLine
 *												// Prints out the value "This is text" at position 15, 25
 *												// in bright white with a black background (foreground/background/style)
 *												// uses System.out.println() to output the ANSI codes and text which
 *												// prints a CR/LF at the end of the line leaving the cursor at 16,1
 *
 *												// row/column are OPTIONAL
 *												//		If one is used, both must be used
 *												//		Defaults to current cursor position
 *												// foreground/background are OPTIONAL
 *												//		If one is used, both must be used
 *												//		REQUIRED if row/column are present
 *												//		Defaults to ansiText.white/ansiText.black
 *												// style is OPTIONAL
 *												//		Defaults to ansiText.normal (bright)
 *												// textOutput is REQUIRED
 *												//		Must always have something to print out!
 *												// nextLine is OPTIONAL
 *												//		Defaults to false ( System.out.print() )
 *												//		nextLine = false is useful for printing input prompts
 *												//		as it leaves the cursor at the end of the line
 *
 * @author dk
 */
public class AnsiText {
    /**
     * CONSTANT ANSI values for BLACK
     * reference in code: ansiText.black
     */
    public static final int black				= 30;
    /**
     * CONSTANT ANSI values for RED
     * reference in code: ansiText.red
     */
    public static final int red					= 31;
    /**
     * CONSTANT ANSI values for GREEN
     * reference in code: ansiText.green
     */
    public static final int green				= 32;
    /**
     * CONSTANT ANSI values for YELLOW
     * reference in code: ansiText.yellow
     */
    public static final int yellow				= 33;
    /**
     * CONSTANT ANSI values for BLUE
     * reference in code: ansiText.blue
     */
    public static final int blue				= 34;
    /**
     * CONSTANT ANSI values for MAGENTA
     * reference in code: ansiText.magenta
     */
    public static final int magenta				= 35;
    /**
     * CONSTANT ANSI values for CYAN
     * reference in code: ansiText.cyan
     */
    public static final int cyan				= 36;
    /**
     * CONSTANT ANSI values for WHITE
     * reference in code: ansiText.white
     */
    public static final int white				= 37;

    public static final int normal				= 0;
    public static final int bold				= 1;
    /**
     * Control Sequence Introducer
     * The code needed to start all of these ANSI sequences.
     */
    private static final String CSI				= "\033[";
    private static final String defaultColors	= "0m";

    private static boolean validateRowColumn(int row, int column) {
        // Validate the Row and Column arguments.
        // Both set to 0 means ignore the CUP and just print where cursor is
        // if either are not 0 they must both be >= 1 and are used to specify
        // the row/column to print the textOutput
        if (row <= 0 && column <= 0) {
            throw new IllegalArgumentException("ansiText.printMessage() Row and Column must both be >= 1.");
        }
        if (row <= 0 && column > 0) {
            throw new IllegalArgumentException("ansiText.printMessage() Row must be >= 1.");
        }
        if (row > 0 && column <= 0) {
            throw new IllegalArgumentException("ansiText.printMessage() Column must be >= 1.");
        }
        return true;
    }

    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param row			The row on the screen to print the textOutput
     *						OPTIONAL (Required if column is present, defaults to current cursor position)
     * @param column		The column on the screen to print the textOutput
     *						OPTIONAL (Required if row is present, defaults to current cursor position)
     * @param foreground	The foreground color of the text.
     *						OPTIONAL (Required if background is present, defaults to white)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param background	The background color of the screen.
     *						OPTIONAL (Required if foreground is present, defaults to black)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param style			The style of the text, normal or bold
     *						OPTIONAL (Defaults to ansiText.normal)
     *						(ansiText.normal, ansiText.bold)
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     * @param nextLine		True/False print CR/LF at end of line
     *						OPTIONAL
     *						true uses System.out.println()
     *						false uses System.out.print()
     */
    public static void printMessage(int row, int column, int foreground, int background, int style, String textOutput, boolean nextLine){
        String outputString = CSI + defaultColors;

        if (row != 0 || column != 0) {
            // This is where we toss in the row,columnH portion of the Ansi.
            if (validateRowColumn(row, column))
                outputString = outputString + CSI + Integer.toString(row) + ";" + Integer.toString(column) + "H";
        }
        // Validate the values of the foreground/background/style input and throw exception if not correct
        if (foreground < black || foreground > white) {
            throw new IllegalArgumentException("ansiText.printMessage() Foreground color must be between Black (30) and White (37).") ;
        }
        if (background < black || background > white) {
            throw new IllegalArgumentException("ansiText.printMessage() Background color must be between Black (30) and White (37).") ;
        }
        if (style != bold && style != normal) {
            throw new IllegalArgumentException("ansiText.printMessage() Style must be Normal (0) or Bold (1).");
        }
        // Now that all the remaining arguments are correct, put the string together with the ANSI CSI code and text
        outputString = outputString + CSI + Integer.toString(foreground);
        if (style == bold) outputString = outputString + ";1";
        outputString = outputString + "m";
        background = background + 10;
        outputString = outputString + CSI + Integer.toString(background) + "m" + textOutput;
        if (nextLine)
            System.out.println(outputString);
        else
            System.out.print(outputString);
        System.out.flush();
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param row			The row on the screen to print the textOutput
     *						OPTIONAL (Required if column is present, defaults to current cursor position)
     * @param column		The column on the screen to print the textOutput
     *						OPTIONAL (Required if row is present, defaults to current cursor position)
     * @param foreground	The foreground color of the text.
     *						OPTIONAL (Required if background is present, defaults to white)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param background	The background color of the screen.
     *						OPTIONAL (Required if foreground is present, defaults to black)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param style			The style of the text, normal or bold
     *						OPTIONAL (Defaults to ansiText.normal)
     *						(ansiText.normal, ansiText.bold)
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     */
    public static void printMessage(int row, int column, int foreground, int background, int style, String textOutput) {
        printMessage(row, column, foreground, background, style, textOutput, false);
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param row			The row on the screen to print the textOutput
     *						OPTIONAL (Required if column is present, defaults to current cursor position)
     * @param column		The column on the screen to print the textOutput
     *						OPTIONAL (Required if row is present, defaults to current cursor position)
     * @param foreground	The foreground color of the text.
     *						OPTIONAL (Required if background is present, defaults to white)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param background	The background color of the screen.
     *						OPTIONAL (Required if foreground is present, defaults to black)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     * @param nextLine		True/False print CR/LF at end of line
     *						OPTIONAL
     *						true uses System.out.println()
     *						false uses System.out.print()
     */
    public static void printMessage(int row, int column, int foreground, int background, String textOutput, boolean nextLine) {
        printMessage(row, column, foreground, background, normal, textOutput, nextLine);
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param row			The row on the screen to print the textOutput
     *						OPTIONAL (Required if column is present, defaults to current cursor position)
     * @param column		The column on the screen to print the textOutput
     *						OPTIONAL (Required if row is present, defaults to current cursor position)
     * @param foreground	The foreground color of the text.
     *						OPTIONAL (Required if background is present, defaults to white)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param background	The background color of the screen.
     *						OPTIONAL (Required if foreground is present, defaults to black)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     */
    public static void printMessage(int row, int column, int foreground, int background, String textOutput) {
        printMessage(row, column, foreground, background, normal, textOutput, false);
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param foreground	The foreground color of the text.
     *						OPTIONAL (Required if background is present, defaults to white)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param background	The background color of the screen.
     *						OPTIONAL (Required if foreground is present, defaults to black)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param style			The style of the text, normal or bold
     *						OPTIONAL (Defaults to ansiText.normal)
     *						(ansiText.normal, ansiText.bold)
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     * @param nextLine		True/False print CR/LF at end of line
     *						OPTIONAL
     *						true uses System.out.println()
     *						false uses System.out.print()
     */
    public static void printMessage(int foreground, int background, int style, String textOutput, boolean nextLine) {
        printMessage(0, 0, foreground, background, style, textOutput, nextLine);
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param foreground	The foreground color of the text.
     *						OPTIONAL (Required if background is present, defaults to white)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param background	The background color of the screen.
     *						OPTIONAL (Required if foreground is present, defaults to black)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param style			The style of the text, normal or bold
     *						OPTIONAL (Defaults to ansiText.normal)
     *						(ansiText.normal, ansiText.bold)
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     */
    public static void printMessage(int foreground, int background, int style, String textOutput) {
        printMessage(0, 0, foreground, background, style, textOutput, false);
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param foreground	The foreground color of the text.
     *						OPTIONAL (Required if background is present, defaults to white)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param background	The background color of the screen.
     *						OPTIONAL (Required if foreground is present, defaults to black)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     * @param nextLine		True/False print CR/LF at end of line
     *						OPTIONAL
     *						true uses System.out.println()
     *						false uses System.out.print()
     */
    public static void printMessage(int foreground, int background, String textOutput, boolean nextLine) {
        printMessage(0, 0, foreground, background, normal, textOutput, nextLine);
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param foreground	The foreground color of the text.
     *						OPTIONAL (Required if background is present, defaults to white)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param background	The background color of the screen.
     *						OPTIONAL (Required if foreground is present, defaults to black)
     *						Is also required if using row/column
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     */
    public static void printMessage(int foreground, int background, String textOutput) {
        printMessage(0, 0, foreground, background, normal, textOutput, false);
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param style			The style of the text, normal or bold
     *						OPTIONAL (Defaults to ansiText.normal)
     *						(ansiText.normal, ansiText.bold)
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     * @param nextLine		True/False print CR/LF at end of line
     *						OPTIONAL
     *						true uses System.out.println()
     *						false uses System.out.print()
     */
    public static void printMessage(int style, String textOutput, boolean nextLine) {
        printMessage(0, 0, white, black, style, textOutput, nextLine);
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param style			The style of the text, normal or bold
     *						OPTIONAL (Defaults to ansiText.normal)
     *						(ansiText.normal, ansiText.bold)
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     */
    public static void printMessage(int style, String textOutput) {
        printMessage(0, 0, white, black, style, textOutput, false);
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     * @param nextLine		True/False print CR/LF at end of line
     *						OPTIONAL
     *						true uses System.out.println()
     *						false uses System.out.print()
     */
    public static void printMessage(String textOutput, boolean nextLine) {
        printMessage(0, 0, white, black, normal, textOutput, nextLine);
    }
    /**
     * ansiText.printMessage() prints the textOutput string out to the console using:
     *					positioned at row/column. If not present, at current cursor postion
     *					printed in foreground/background colors. If not present, defaults to white/black
     *					printed in normal or bold (bright) styles. If not present, defaults to normal
     *					Printed with or without CR/LF at end of line.
     *						nextLine = true prints using System.out.println() which outputs the CR/LF
     *						nextLine = false prints using System.out.print() which leaves the cursor at the end of the line.
     *							nextLine = false is useful for input prompts.
     * @param textOutput	The string to output using foreground, background colors
     *						REQUIRED
     */
    public static void printMessage(String textOutput) {
        printMessage(0, 0, white, black, normal, textOutput, false);
    }

    /**
     * ansiText.clearScreen() clears the screen, sets the colors back to the console
     *                        default and places the cursor at 1,1
     */
    public static void clearScreen() {
        System.out.print(CSI + defaultColors + CSI + "2J" + CSI + "H" );
        System.out.flush();
    }
    /**
     * ansiText.clearArea() clears an area on the screen from row, column and width wide, height tall
     *                      Useful for clearing fixed areas of the screen, leaving other parts.
     *                      This is also useful for drawing rectangles for window frames by clearing
     *                      one area with a background color, and clearing other areas inside of the
     *                      initial area with a different background color.
     * @param row			REQUIRED The row on the screen of the top row of the area to clear
     * @param column		REQUIRED The column on the screen of the left column of the area to clear
     * @param width			REQUIRED The width of the area in columns to clear starting at column
     * @param height		REQUIRED The height of the area in rows starting at row
     * @param background	OPTIONAL The background color to set the area cleared to. Defaults to ansiBlack
     *						(ansiText.black, ansiText.red, ansiText.green, ansiText.yellow,
     *						ansiText.blue, ansiText.magenta, ansiText.cyan, ansiText.white)
     */
    public static void clearArea(int row, int column, int width, int height, int background) {
        String outputString = String.join("", Collections.nCopies(width, " "));
        if (validateRowColumn(row, column)) {
            for (int counter = row; counter < row + height; counter++) {
                printMessage(counter, column, background, background, normal, outputString);
            }
        }
        printMessage(CSI + defaultColors);
    }
    /**
     * ansiText.clearArea() clears an area on the screen from row, column and width wide, height tall
     *                      Useful for clearing fixed areas of the screen, leaving other parts.
     *                      This is also useful for drawing rectangles for window frames by clearing
     *                      one area with a background color, and clearing other areas inside of the
     *                      initial area with a different background color.
     * @param row			REQUIRED The row on the screen of the top row of the area to clear
     * @param column		REQUIRED The column on the screen of the left column of the area to clear
     * @param width			REQUIRED The width of the area in columns to clear starting at column
     * @param height		REQUIRED The height of the area in rows starting at row
     */
    public static void clearArea(int row, int column, int width, int height) {
        clearArea(row, column, width, height, black);
    }
    /**
     * ansiTextSampleBackgroundColors() prints out a sample display of the background options available
     *                                  and shows the function prototype used to create the sample line
     */
    public static void printSampleBackgroundColors() {
        String[] colors = {"Black","Red","Green","Yellow","Blue","Magenta","Cyan","White"};
        String javaCall;
        String messageText;

        clearScreen();
        printMessage(white, black,"\n\n");
        for (int bg = black; bg <= white;bg++) {
            messageText = "   " + colors[bg - 30] + " Background";
            messageText = messageText + String.join("", Collections.nCopies((int)((79 - messageText.length())), " "));

            if (bg == white) {
                javaCall = "ansiText.printMessage(ansiText.black,ansiText." + colors[bg - 30].toLowerCase() + ",ansiText.<text>);";
                printMessage(white,black,bold,javaCall,true);
                printMessage(black, bg, messageText);
            } else {
                javaCall = "ansiText.printMessage(ansiText.white,ansiText." + colors[bg - 30].toLowerCase() + ",ansiText.<text>);";
                printMessage(white,black,bold,javaCall, true);
                printMessage(white, bg, messageText);
            }
            printMessage(white,black, "", true);
        }
    }
    /**
     * ansiTextSampleTextColors() prints out a sample display of the text options available
     *                            and shows the function prototype used to create the sample line
     */
    public static void printSampleTextColors() {
        String[] colors = {"Black","Red","Green","Yellow","Blue","Magenta","Cyan","White"};
        String[] styles = {"Normal","Bold"};
        String javaCall;
        String messageText;

        clearScreen();
        printMessage(white, black,"\n\n");
        for (int styleLoop = normal;styleLoop <= bold;styleLoop++) {
            for (int fg = black; fg <= white;fg++) {
                messageText = "   " + styles[styleLoop] + " " + colors[fg - 30];
                messageText = messageText + String.join("", Collections.nCopies((int)((79 - messageText.length())), " "));

                if (fg == black && styleLoop == normal){
                    javaCall = "ansiText.printMessage(ansiText." + colors[fg - 30].toLowerCase() + ",ansiText.white,ansiText." + styles[styleLoop].toLowerCase().trim() + ",<text>);";
                    printMessage(white, black, bold, javaCall, true);
                    printMessage(fg, white, styleLoop, messageText);
                }
                else {
                    javaCall = "ansiText.printMessage(ansiText." + colors[fg - 30].toLowerCase() + ",ansiText.black,ansiText." + styles[styleLoop].toLowerCase().trim() + ",<text>);";
                    printMessage(white, black, bold, javaCall, true);
                    printMessage(fg, black, styleLoop, messageText);
                }
                printMessage(white, black, "", true);
            }
        }
        System.out.flush();
    }

}
