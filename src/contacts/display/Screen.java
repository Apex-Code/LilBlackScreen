package contacts.display;


//        int row, int column, int width, int height
import java.util.Collections;
import java.util.Scanner;


public class Screen {

    /* *************************************************************************
     * FRAME CONSTANTS are used to identify location (row/col), size (height/
     * width), color and application title information.
     * FRAMEROW			The top row in screen characters of the outer frame (border)
     * FRAMECOL			The left column in screen characters of the outer frame
     * FRAMEWIDTH		The width in screen characters of the outer frame
     * FRAMEHEIGHT		The height in screen characters of the outer frame
     * FRAMECOLOR		The color of the outer frame using ansiText colors
     * FRAMEBGCOLOR		The color of the background of the inside of the frame,
     *					where the actual text output is displayed
     * FRAMETITLE		The application title. Displayed in the outer frame
     * FRAMETITLECOL	The left column of a centered FRAMETITLE. Calculation based
     *					on FRAMEWIDTH, FRAMETITLE length and FRAMECOL
     * FRAMETITLECOLOR	The color of the text for the application title based
     *					on ansiText colors
     * FRAMETITLESTYLE	The style of the FRAMETITLE text, bold (bright) or normal
     *					from ansiText style attributes.
     * ************************************************************************/

    private static final int		FRAMEROW			= 2;
    private static final int		FRAMECOL			= 2;
    private static final int		FRAMEWIDTH			= 78;
    private static final int		FRAMEHEIGHT			= 23;
    private static final int		FRAMECOLOR			= AnsiText.yellow;
    private static final int		FRAMEBGCOLOR		= AnsiText.black;
    private static final String	FRAMETITLE			= "Lil' Black Screen";
    private static final int		FRAMETITLECOL		= (FRAMEWIDTH - FRAMETITLE.length()) / 2 + FRAMECOL;
    private static final int		FRAMETITLECOLOR		= AnsiText.white;
    private static final int		FRAMETITLESTYLE		= AnsiText.bold;

    /* *************************************************************************
     * Screen Title CONSTANTS are used to identify and place centered title text
     * for the type of screen being displayed, Menu, Add Contact, List Contacts,
     * etc.
     * MENU, ADD, LIST, SEARCH, EDIT, DISPLAY, DELETE
     *					Array index pointers into TITLES and TITLECOL some also
     *					used for method paramters to reuse methods i.e. displayContact()
     * TITLEROW, TITLECOLOR, TITLEBGCOLOR, TITLESTYLE
     * 					Title Row location and text, background and style for text
     * TITLES			Array of screen titles
     * TITLECOL			Matching array of Title Col locations calculated from
     *					FRAMEWIDTH, TITLE length and FRAMECOL
     * ************************************************************************/

    private static final int		MENU				= 0;
    public  static final int		ADD					= 1;
    private static final int		LIST				= 2;
    private static final int		SEARCH				= 3;
    public  static final int		EDIT				= 4;
    public  static final int		DISPLAY				= 5;
    public  static final int		DELETE				= 6;
    private static final int		TITLEROW			= FRAMEROW + 2;
    private static final int		TITLECOLOR			= AnsiText.blue;
    private static final int		TITLEBGCOLOR		= AnsiText.black;
    private static final int		TITLESTYLE			= AnsiText.bold;
    private static final String[]  MENUTITLES          = {"Contacts", "Add Contact", "List Contacts",
            "Search Contacts", "Edit Contact", "Delete Contact", "View Contact Detail"};

    private static final int[]		TITLECOL			= {
            (FRAMEWIDTH - MENUTITLES[MENU].length()) / 2 + FRAMECOL,
            (FRAMEWIDTH - MENUTITLES[ADD].length()) / 2 + FRAMECOL,
            (FRAMEWIDTH - MENUTITLES[LIST].length()) / 2 + FRAMECOL,
            (FRAMEWIDTH - MENUTITLES[SEARCH].length()) / 2 + FRAMECOL,
            (FRAMEWIDTH - MENUTITLES[EDIT].length()) / 2 + FRAMECOL,
            (FRAMEWIDTH - MENUTITLES[DELETE].length()) / 2 + FRAMECOL,
            (FRAMEWIDTH - MENUTITLES[DISPLAY].length()) / 2 + FRAMECOL
    };

    private static final String[] MENUITEMS = {
            "{1} Add a Contact",
            "{2} View Contacts",
            "{3} Search Contacts",
            "{4} Close Lil' Black Book"
    };

    private static final int		MENUROW				= ((FRAMEHEIGHT - 4) / 2) + FRAMEROW;
    private static final int		MENUCOL				= ((FRAMEWIDTH - 20) / 2) + FRAMECOL;
    private static final String	MENUCHOICELABEL	= "Enter Selection:  ";
    private static final int		MENUCHOICEROW		= FRAMEHEIGHT - 3 + FRAMEROW;
    private static final int		MENUCHOICECOL		= (FRAMEWIDTH - MENUCHOICELABEL.length()) / 2 + FRAMECOL;
    private static final String	MENUCHOICEFIELD	= " ";
    private static final int		MENUCHOICEFIELDCOL	= MENUCHOICECOL + MENUCHOICELABEL.length() - 1;
    /* *************************************************************************
     * One Line Menu CONSTANTS for use on screens with a one line menu at the bottom
     * such as displayContact and deleteContact
     * ONEMENUDISPLAY, ONEMENUDELETE, ONEMENUSEARCH
     *					Array index pointers for one line menus
     * ONEMENULINES		Array of one line menus indexed by above array indexes
     * ONEMENUROW		Row location to display the one line menu
     * ONEMENUCOL		Array of col locations (centered) for the ONEMENULINES array
     *					Calculated by FRAMEWIDTH, One menu length and FRAMECOL
     * ************************************************************************/
    // One Line menu constants  for use with displayContact and deleteContact. In the future on listContact for next/previous/edit record
    public  static final int		ONEMENUDISPLAY		= 0;
    public  static final int		ONEMENUDELETE		= 1;
    private static final int		ONEMENUSEARCH		= 2;
    private static final String[]	ONEMENULINES		= {	"[1] Edit Contact   [2] Delete Contact   [3] Return to Menu",
            "[1] Confirm Delete Contact   [2] Cancel Delete Contact",
            "Enter ID to Edit or [N] Next Page  [P] Previous Page  [X] Exit"};
    private static final int		ONEMENUROW			= FRAMEHEIGHT - 5 + FRAMEROW;
    private static final int[]		ONEMENUCOL			= {	(FRAMEWIDTH - ONEMENULINES[ONEMENUDISPLAY].length()) / 2 + FRAMECOL,
            (FRAMEWIDTH - ONEMENULINES[ONEMENUDELETE].length()) / 2 + FRAMECOL,
            (FRAMEWIDTH - ONEMENULINES[ONEMENUSEARCH].length()) / 2 + FRAMECOL};

    /* *************************************************************************
     * Text CONSTANTS for text color, background color and text style for all
     * styles of text. FIELD for input fields, LABEL for screen labels, ERROR
     * for all error messages and TEXT for all default text output.
     * ************************************************************************/
    // Field color Constants. Will be used for all input fields when expecting user input
    private static final int		FIELDCOLOR			= AnsiText.white;
    private static final int		FIELDBGCOLOR		= AnsiText.yellow;
    private static final int		FIELDSTYLE			= AnsiText.bold;
    // Label color constants. Will be used for displaying all field labels and the screen title
    private static final int		LABELCOLOR			= AnsiText.blue;
    private static final int		LABELBGCOLOR		= AnsiText.black;
    private static final int		LABELSTYLE			= AnsiText.bold;
    // ERror color constants. Will be used to display all error messages
    private static final int		ERRORCOLOR			= AnsiText.red;
    private static final int		ERRORBGCOLOR		= AnsiText.black;
    private static final int		ERRORSTYLE			= AnsiText.bold;
    // Data color constants. Will be used for displaying all other text
    private static final int		TEXTCOLOR			= AnsiText.yellow;
    private static final int		TEXTBGCOLOR			= AnsiText.black;
    private static final int		TEXTSTYLE			= AnsiText.bold;

    // FIELDS constants. Used for drawing field textboxes and the size of the field
    public  static final int		FIRSTNAME			= 15;
    public  static final int		LASTNAME			= 15;
    public  static final int		NICKNAME			= 15;
    public  static final int		EMAIL				= 40;
    public  static final int		STREET				= 50;
    public  static final int		CITY				= 25;
    public  static final int		STATE				= 2;
    public  static final int		ZIPCODE				= 5;
    public  static final int		PHONE				= 12;

    public  static final String		FIELDFIRSTNAME		= String.join("", Collections.nCopies(FIRSTNAME, " "));
    public  static final String		FIELDLASTNAME		= String.join("", Collections.nCopies(LASTNAME, " "));
    public  static final String		FIELDNICKNAME		= String.join("", Collections.nCopies(NICKNAME, " "));
    public  static final String		FIELDEMAIL			= String.join("", Collections.nCopies(EMAIL, " "));
    public  static final String		FIELDSTREET			= String.join("", Collections.nCopies(STREET, " "));
    public  static final String		FIELDCITY			= String.join("", Collections.nCopies(CITY, " "));
    public  static final String		FIELDSTATE			= String.join("", Collections.nCopies(STATE, " "));
    public  static final String		FIELDZIPCODE		= String.join("", Collections.nCopies(ZIPCODE, " "));
    public  static final String		FIELDPHONE			= String.join("", Collections.nCopies(PHONE, " "));

    // [1] Contact Display constants. Will be used for displaying, adding or editing a contact record.
    private static final int		NAMEROW				= TITLEROW + 3;
    private static final int		FIRSTNAMECOL		= FRAMECOL + 10;
    private static final int		LASTNAMECOL			= FIRSTNAMECOL + FIRSTNAME + 5;
    private static final int		NICKNAMECOL			= LASTNAMECOL + LASTNAME + 5;
    private static final int		STREETROW			= NAMEROW + 3;
    private static final int		STREETCOL			= FIRSTNAMECOL;
    private static final int		CITYROW				= STREETROW + 3;
    private static final int		CITYCOL				= FIRSTNAMECOL;
    private static final int		STATECOL			= CITYCOL + CITY + 5;
    private static final int		ZIPCODECOL			= STATECOL + ZIPCODE + 5;
    private static final int		EMAILROW			= CITYROW + 3;
    private static final int		EMAILCOL			= FIRSTNAMECOL;
    private static final int		PHONECOL			= FIRSTNAMECOL + EMAIL + 5;

    // [2] List constants. Will be used when displaying a list
    private static final int		LISTROW				= TITLEROW + 3;
    private static final int		LISTIDCOL			= FRAMECOL + 6;
    private static final int		LISTFIRSTNAMECOL	= LISTIDCOL + 5;
    private static final int		LISTLASTNAMECOL		= LISTFIRSTNAMECOL + 16;
    private static final int		LISTNICKNAMECOL		= LISTLASTNAMECOL + 16;
    private static final int		LISTPHONECOL		= LISTNICKNAMECOL + 16;

    // [3] Search constants. Will be used for displaying the search screen
    private static final int		SEARCHCOL			= (FRAMEWIDTH - 15) / 2 + FRAMECOL;
    private static final int		SEARCHFIRSTNAMEROW	= (FRAMEHEIGHT - 5) / 2;
    private static final int		SEARCHLASTNAMEROW	= SEARCHFIRSTNAMEROW + 4;
    private static final int		SEARCHNICKNAMEROW	= SEARCHLASTNAMEROW + 4;

    // ENTER Key Only constants
    private static final String		ENTERTEXT			= "Press Enter key to continue.";
    private static final int		ENTERROW			= FRAMEHEIGHT - 3 + FRAMEROW;
    private static final int		ENTERCOL			= (FRAMEWIDTH - ENTERTEXT.length()) / 2 + FRAMECOL;

    //Error message constants
    private static final int		ERRORROW			= FRAMEHEIGHT - 4 + FRAMEROW;

    // Choice [1] Add Contact methods - Will also serve Display, Edit and Delete methods
    public static void drawRecord(int recordType, boolean inputFields) {
        clearFrame();
        displayTitle(recordType);
        // Name row labels
        AnsiText.printMessage(NAMEROW - 1, FIRSTNAMECOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"First Name");
        AnsiText.printMessage(NAMEROW - 1, LASTNAMECOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"Last Name");
        AnsiText.printMessage(NAMEROW - 1, NICKNAMECOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"Nick Name");
        AnsiText.printMessage(STREETROW - 1, STREETCOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"Address");
        AnsiText.printMessage(CITYROW - 1, CITYCOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"City");
        AnsiText.printMessage(CITYROW - 1, STATECOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"State");
        AnsiText.printMessage(CITYROW - 1, ZIPCODECOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"Zip Code");
        AnsiText.printMessage(EMAILROW - 1, EMAILCOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"EMail");
        AnsiText.printMessage(EMAILROW - 1, PHONECOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"Telephone");

        // Show empty input fields if this is an input capable contact screen
        if (inputFields) {
            displayFirstName(FIELDFIRSTNAME, inputFields);
            displayLastName(FIELDLASTNAME, inputFields);
            displayNickName(FIELDNICKNAME, inputFields);
            displayStreet(FIELDSTREET, inputFields);
            displayCity(FIELDCITY, inputFields);
            displayState(FIELDSTATE, inputFields);
            displayZipCode(FIELDZIPCODE, inputFields);
            displayEmail(FIELDEMAIL, inputFields);
            displayPhone(FIELDPHONE, inputFields);
        }
    }
    // Display fields display methods

    public static void displayFirstName(String firstName, boolean inputFields) {
        if (inputFields) {
            AnsiText.printMessage(NAMEROW, FIRSTNAMECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,firstName);
        } else {
            AnsiText.printMessage(NAMEROW, FIRSTNAMECOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,firstName);
        }
    }
    public static void displayLastName(String lastName, boolean inputFields) {
        if (inputFields) {
            AnsiText.printMessage(NAMEROW, LASTNAMECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,lastName);
        } else {
            AnsiText.printMessage(NAMEROW, LASTNAMECOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,lastName);
        }
    }
    public static void displayNickName(String nickName, boolean inputFields) {
        if (inputFields) {
            AnsiText.printMessage(NAMEROW, NICKNAMECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,nickName);
        } else {
            AnsiText.printMessage(NAMEROW, NICKNAMECOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,nickName);
        }
    }
    public static void displayStreet(String street, boolean inputFields) {
        if (inputFields) {
            AnsiText.printMessage(STREETROW, STREETCOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,street);
        } else {
            AnsiText.printMessage(STREETROW, STREETCOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,street);
        }
    }
    public static void displayCity(String city, boolean inputFields) {
        if (inputFields) {
            AnsiText.printMessage(CITYROW, CITYCOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,city);
        } else {
            AnsiText.printMessage(CITYROW, CITYCOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,city);
        }
    }
    public static void displayState(String state, boolean inputFields) {
        if (inputFields) {
            AnsiText.printMessage(CITYROW, STATECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,state);
        } else {
            AnsiText.printMessage(CITYROW, STATECOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,state);
        }
    }
    public static void displayZipCode(String zipcode, boolean inputFields) {
        if (inputFields) {
            AnsiText.printMessage(CITYROW, ZIPCODECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,zipcode);
        } else {
            AnsiText.printMessage(CITYROW, ZIPCODECOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,zipcode);
        }
    }
    public static void displayEmail(String email, boolean inputFields) {
        if (inputFields) {
            AnsiText.printMessage(EMAILROW, EMAILCOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,email);
        } else {
            AnsiText.printMessage(EMAILROW, EMAILCOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,email);
        }
    }
    public static void displayPhone(String phone, boolean inputFields) {
        if (inputFields) {
            AnsiText.printMessage(EMAILROW, PHONECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,phone);
        } else {
            AnsiText.printMessage(EMAILROW, PHONECOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,phone);
        }
    }
    // Display fields getInput methods
    public static String getFirstName() {
        AnsiText.printMessage(NAMEROW, FIRSTNAMECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,"");
        return getInput();
    }
    public static String getLastName() {
        AnsiText.printMessage(NAMEROW, LASTNAMECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,"");
        return getInput();
    }
    public static String getNickName() {
        AnsiText.printMessage(NAMEROW, NICKNAMECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,"");
        return getInput();
    }
    public static String getStreet() {
        AnsiText.printMessage(STREETROW, STREETCOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,"");
        return getInput();
    }
    public static String getCity() {
        AnsiText.printMessage(CITYROW, CITYCOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,"");
        return getInput();
    }
    public static String getState() {
        AnsiText.printMessage(CITYROW, STATECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,"");
        return getInput();
    }
    public static String getZipCode() {
        AnsiText.printMessage(CITYROW, ZIPCODECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,"");
        return getInput();
    }
    public static String getEmail() {
        AnsiText.printMessage(EMAILROW, EMAILCOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,"");
        return getInput();
    }
    public static String getPhone() {
        AnsiText.printMessage(EMAILROW, PHONECOL,FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,"");
        return getInput();
    }

    // Choice [2] List Contacts methods
    public static void drawList() {
        clearFrame();
        displayTitle(LIST);
        AnsiText.printMessage(LISTROW - 1, LISTIDCOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE, " ID" );
        AnsiText.printMessage(LISTROW - 1, LISTFIRSTNAMECOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE, "First Name" );
        AnsiText.printMessage(LISTROW - 1, LISTLASTNAMECOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE, "Last Name" );
        AnsiText.printMessage(LISTROW - 1, LISTNICKNAMECOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE, "Nick Name" );
        AnsiText.printMessage(LISTROW - 1, LISTPHONECOL,LABELCOLOR, LABELBGCOLOR, LABELSTYLE, "Telephone" );
    }
    public static void drawListData(int row, int id, String firstName, String lastName, String nickName, String phone) {
        AnsiText.printMessage(LISTROW + row, LISTIDCOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE, String.format("%3d", id) );
        AnsiText.printMessage(LISTROW + row, LISTFIRSTNAMECOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE, firstName );
        AnsiText.printMessage(LISTROW + row, LISTLASTNAMECOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE, lastName );
        AnsiText.printMessage(LISTROW + row, LISTNICKNAMECOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE, nickName);
        AnsiText.printMessage(LISTROW + row, LISTPHONECOL,TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE, phone );
    }

    // Choice [3] Search Contacts methods
    public static void drawSearch() {
        clearFrame();
        displayTitle(SEARCH);
        AnsiText.printMessage(SEARCHFIRSTNAMEROW - 1,SEARCHCOL, LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"First Name");
        AnsiText.printMessage(SEARCHFIRSTNAMEROW, SEARCHCOL, FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE, FIELDFIRSTNAME);
        AnsiText.printMessage(SEARCHLASTNAMEROW - 1,SEARCHCOL, LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"Last Name");
        AnsiText.printMessage(SEARCHLASTNAMEROW, SEARCHCOL, FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE, FIELDLASTNAME);
        AnsiText.printMessage(SEARCHNICKNAMEROW - 1,SEARCHCOL, LABELCOLOR, LABELBGCOLOR, LABELSTYLE,"Nick Name");
        AnsiText.printMessage(SEARCHNICKNAMEROW, SEARCHCOL, FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE, FIELDNICKNAME);
    }
    public static String getSearchFirstName() {
        AnsiText.printMessage(SEARCHFIRSTNAMEROW, SEARCHCOL, FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE, "");
        return getInput();
    }
    public static String getSearchLastName() {
        AnsiText.printMessage(SEARCHLASTNAMEROW, SEARCHCOL, FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE, "");
        return getInput();
    }
    public static String getSearchNickName() {
        AnsiText.printMessage(SEARCHNICKNAMEROW, SEARCHCOL, FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE, "");
        return getInput();
    }

    // Menu Methods
    public static void drawMenu() {
        clearFrame();
        displayTitle(MENU);
        for (int menuLine = 0; menuLine < MENUITEMS.length; menuLine++) {
            AnsiText.printMessage(MENUROW + menuLine, MENUCOL, TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,MENUITEMS[menuLine]);
        }
    }
    public static String getMenuSelection() {
        AnsiText.printMessage(MENUCHOICEROW,MENUCHOICECOL, TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,MENUCHOICELABEL);
        AnsiText.printMessage(MENUCHOICEROW, MENUCHOICEFIELDCOL, FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,MENUCHOICEFIELD);
        AnsiText.printMessage(MENUCHOICEROW, MENUCHOICEFIELDCOL, FIELDCOLOR, FIELDBGCOLOR, FIELDSTYLE,"");
        return getInput();
    }
    public static void drawOneMenu(int oneMenu) {
        AnsiText.printMessage(ONEMENUROW, ONEMENUCOL[oneMenu], TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE, ONEMENULINES[oneMenu]);
    }

    // Worker methods
    public static void drawFrame() {
        AnsiText.clearScreen();
        AnsiText.clearArea(FRAMEROW, FRAMECOL, FRAMEWIDTH, FRAMEHEIGHT, FRAMECOLOR);
        AnsiText.printMessage(FRAMEROW, FRAMETITLECOL, FRAMETITLECOLOR, FRAMECOLOR, FRAMETITLESTYLE,FRAMETITLE);
        clearFrame();
    }
    public static void clearFrame() {
        AnsiText.clearArea(FRAMEROW + 1, FRAMECOL + 1, FRAMEWIDTH - 2, FRAMEHEIGHT - 2, FRAMEBGCOLOR);
    }
    public static void printError(String errorMessage) {
        AnsiText.clearArea(ERRORROW, FRAMECOL + 1, FRAMEWIDTH - 2, 1, FRAMEBGCOLOR);
        AnsiText.printMessage(ERRORROW, (FRAMEWIDTH - errorMessage.length())/2+FRAMECOL,ERRORCOLOR, ERRORBGCOLOR,ERRORSTYLE,errorMessage);
    }
    public static void pauseScreen() {
        AnsiText.printMessage(ENTERROW,ENTERCOL, TEXTCOLOR, TEXTBGCOLOR, TEXTSTYLE,ENTERTEXT);
        getInput();
    }
    private static void displayTitle(int titleIdx) {
        AnsiText.printMessage(TITLEROW, TITLECOL[titleIdx],TITLECOLOR, TITLEBGCOLOR, TITLESTYLE, MENUTITLES[titleIdx]);
    }
    private static String getInput() {
        Scanner scanLine = new Scanner(System.in);
        return scanLine.nextLine();
    }
    public static void clearScreen() {
        AnsiText.clearScreen();
    }
}
