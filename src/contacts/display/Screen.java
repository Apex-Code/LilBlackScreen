package contacts.display;


//        int row, int column, int width, int height

import contacts.Contacts;
import contacts.Person;

import java.util.ArrayList;

import static contacts.Contacts.firstName;
import static contacts.Contacts.lastName;
import static contacts.Contacts.nickName;

public class Screen {


    private static final String PressEnter = "Press Enter to Continue...";

    private static void clearIt() {
        AnsiText.clearArea(10, 25, 100, 45);
    }
//    row, column, foreground, background, normal, textOutput, false

    public static void nobody() {
        clearIt();
        drawBorder();
        String nobody = "You don't have any friends!";
        AnsiText.printMessage(15, 30, AnsiText.white, AnsiText.black, nobody, true);
        AnsiText.printMessage(18, 30, AnsiText.white, AnsiText.black, PressEnter, false);
    }

    public static void drawWelcome() {
        clearScreen();
        clearIt();
        drawBorder();
        String welcome = "Welcome to Lil Black Screen The futuristic way to track your contacts.";
        String partTwo = "(As long as you don't have more than 10 contacts)";
        String disclaimer = "You're not alone, we FUCKING HATE ansiText too!";
        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, welcome, true);
        AnsiText.printMessage(15, 30, AnsiText.white, AnsiText.black, partTwo, true);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, disclaimer, true);
        AnsiText.printMessage(20, 30, AnsiText.white, AnsiText.black, PressEnter, false);
    }

    public static void clearScreen() {
        AnsiText.clearScreen();
    }

    private static void drawBorder() {
        AnsiText.clearArea(10, 25, 100, 1, AnsiText.white);
        AnsiText.clearArea(10, 25, 2, 45, AnsiText.white);
        AnsiText.clearArea(98, 25, 100, 2, AnsiText.white);
        AnsiText.clearArea(10, 125, 2, 45, AnsiText.white);
    }

    public static void drawMainMenu() {
        clearIt();
        drawBorder();
        String choiceA = "1. View Contacts";
        String choiceB = "2. Add Contact";
        String choiceC = "3. Search Contacts";
        String choiceD = "4. Exit Lil Black Screen";
        String prompt = "Please enter your choice: ";

        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, choiceA, true);
        AnsiText.printMessage(15, 30, AnsiText.white, AnsiText.black, choiceB, true);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, choiceC, true);
        AnsiText.printMessage(17, 30, AnsiText.white, AnsiText.black, choiceD, true);
        AnsiText.printMessage(20, 30, AnsiText.white, AnsiText.black, prompt, false);

    }

    public static void nope() {
        String dummy = "That wasn't a valid choice.  And I feel like you know that.";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);

    }

    public static void drawContactFirstName() {
        clearIt();
        drawBorder();
        String firstName = "Enter Contact's first name: ";
        AnsiText.printMessage(22, 45, AnsiText.white, AnsiText.black, firstName, false);
    }

    public static void drawContactLastName() {
        clearIt();
        drawBorder();
        String fname = "First Name: " + firstName;
        String lastName = "Enter Contact's last name: ";
        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, fname, false);
        AnsiText.printMessage(22, 45, AnsiText.white, AnsiText.black, lastName, false);
    }

    public static void drawContactNickName() {
        clearIt();
        drawBorder();
        String first = "First Name: " + firstName;
        String last = "Last Name: " + lastName;
        String name = "Enter Contact's Nickname: ";
        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, first, false);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, last, false);
        AnsiText.printMessage(22, 45, AnsiText.white, AnsiText.black, name, false);
    }

    public static void drawPhone() {
        clearIt();
        drawBorder();
        String first = "First Name: " + firstName;
        String last = "Last Name: " + lastName;
        String nick = "Nickname: " + nickName;
        String name = "Enter Contact's Phone: ";
        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, first, false);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, last, false);
        AnsiText.printMessage(18, 30, AnsiText.white, AnsiText.black, nick, false);
        AnsiText.printMessage(22, 45, AnsiText.white, AnsiText.black, name, false);
    }

    public static void drawContactEmail() {
        clearIt();
        drawBorder();
        String name = "Enter Contact's Email: ";
        String first = "First Name: " + firstName;
        String last = "Last Name: " + lastName;
        String nick = "Nickname: " + nickName;
        String phone = "Phone: " + Contacts.phone;
        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, first, false);
        AnsiText.printMessage(15, 30, AnsiText.white, AnsiText.black, last, false);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, nick, false);
        AnsiText.printMessage(17, 30, AnsiText.white, AnsiText.black, phone, false);
        AnsiText.printMessage(22, 45, AnsiText.white, AnsiText.black, name, false);
    }

    public static void drawContactAddress() {
        clearIt();
        drawBorder();
        String name = "Enter Contact's House Number: ";
        String first = "First Name: " + firstName;
        String last = "Last Name: " + lastName;
        String nick = "Nickname: " + nickName;
        String phone = "Phone: " + Contacts.phone;
        String email = "email: " + Contacts.email;

        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, first, false);
        AnsiText.printMessage(15, 30, AnsiText.white, AnsiText.black, last, false);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, nick, false);
        AnsiText.printMessage(17, 30, AnsiText.white, AnsiText.black, phone, false);
        AnsiText.printMessage(18, 30, AnsiText.white, AnsiText.black, email, false);
        AnsiText.printMessage(22, 45, AnsiText.white, AnsiText.black, name, false);
    }

    public static void drawContactStreet() {
        clearIt();
        drawBorder();
        String name = "Enter Contact's Street: ";
        String first = "First Name: " + firstName;
        String last = "Last Name: " + lastName;
        String nick = "Nickname: " + nickName;
        String phone = "Phone: " + Contacts.phone;
        String email = "email: " + Contacts.email;
        String address = "House Number: " + Contacts.address;
        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, first, false);
        AnsiText.printMessage(15, 30, AnsiText.white, AnsiText.black, last, false);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, nick, false);
        AnsiText.printMessage(17, 30, AnsiText.white, AnsiText.black, phone, false);
        AnsiText.printMessage(18, 30, AnsiText.white, AnsiText.black, email, false);
        AnsiText.printMessage(19, 30, AnsiText.white, AnsiText.black, address, false);
        AnsiText.printMessage(22, 45, AnsiText.white, AnsiText.black, name, false);
    }

    public static void drawContactCity() {
        clearIt();
        drawBorder();
        String name = "Enter Contact's city: ";
        String first = "First Name: " + firstName;
        String last = "Last Name: " + lastName;
        String nick = "Nickname: " + nickName;
        String phone = "Phone: " + Contacts.phone;
        String email = "email: " + Contacts.email;
        String address = "House Number: " + Contacts.address;
        String street = "Street: " + Contacts.street;
        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, first, false);
        AnsiText.printMessage(15, 30, AnsiText.white, AnsiText.black, last, false);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, nick, false);
        AnsiText.printMessage(17, 30, AnsiText.white, AnsiText.black, phone, false);
        AnsiText.printMessage(18, 30, AnsiText.white, AnsiText.black, email, false);
        AnsiText.printMessage(19, 30, AnsiText.white, AnsiText.black, address, false);
        AnsiText.printMessage(20, 30, AnsiText.white, AnsiText.black, street, false);
        AnsiText.printMessage(22, 45, AnsiText.white, AnsiText.black, name, false);
    }

    public static void drawContactZip() {
        clearIt();
        drawBorder();
        String name = "Enter Contact's Zip Code: ";
        String first = "First Name: " + firstName;
        String last = "Last Name: " + lastName;
        String nick = "Nickname: " + nickName;
        String phone = "Phone: " + Contacts.phone;
        String email = "email: " + Contacts.email;
        String address = "House Number: " + Contacts.address;
        String street = "Street: " + Contacts.street;
        String city = "City: " + Contacts.city;
        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, first, false);
        AnsiText.printMessage(15, 30, AnsiText.white, AnsiText.black, last, false);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, nick, false);
        AnsiText.printMessage(17, 30, AnsiText.white, AnsiText.black, phone, false);
        AnsiText.printMessage(18, 30, AnsiText.white, AnsiText.black, email, false);
        AnsiText.printMessage(19, 30, AnsiText.white, AnsiText.black, address, false);
        AnsiText.printMessage(20, 30, AnsiText.white, AnsiText.black, street, false);
        AnsiText.printMessage(21, 30, AnsiText.white, AnsiText.black, city, false);
        AnsiText.printMessage(23, 45, AnsiText.white, AnsiText.black, name, false);
    }

    public static void drawContactState() {
        clearIt();
        drawBorder();
        String first = "First Name: " + firstName;
        String last = "Last Name: " + lastName;
        String nick = "Nickname: " + nickName;
        String phone = "Phone: " + Contacts.phone;
        String email = "email: " + Contacts.email;
        String address = "House Number: " + Contacts.address;
        String street = "Street: " + Contacts.street;
        String name = "Enter Contact's State (This is a 2 letter abbreviation!): ";
        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, first, false);
        AnsiText.printMessage(15, 30, AnsiText.white, AnsiText.black, last, false);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, nick, false);
        AnsiText.printMessage(17, 30, AnsiText.white, AnsiText.black, phone, false);
        AnsiText.printMessage(18, 30, AnsiText.white, AnsiText.black, email, false);
        AnsiText.printMessage(19, 30, AnsiText.white, AnsiText.black, address, false);
        AnsiText.printMessage(20, 30, AnsiText.white, AnsiText.black, street, false);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, name, false);
    }

    public static void notInt() {
        String dummy = "You just tried to enter something that wasn't a zip code...  That's a DK move.";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);

    }

    public static void drawSuccess() {
        String dummy = "Contact successfully saved!";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);

    }

    public static void drawFNameError() {
        String dummy = "All names must be more than 1 and less than 15 characters, try again.";

        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);
    }

    public static void drawPhoneError() {
        String dummy = "Please use a valid US phone number.  My Regex is on point.";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);
    }

    public static void drawEmailError() {
        String dummy = "If you're going to fake an email, atleast make it look legit.  Try again.";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);
    }

    public static void drawGeneralError() {
        String dummy = "*Must contain more than one character*";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);
    }

    public static void drawStateNotFound() {
        String dummy = "Oh sweety, of all the states...  That isn't one.  Try again.";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);
    }

    public static void drawStateError() {
        String dummy = "Ever since 5th grade, state abbreviations are 2 letters. Try again.";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);
    }

    public static void drawCityError() {
        String dummy = "The longest city name in the US is Rancho Santa Margarita and the shortest has 3 letters.";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);
    }

    public static void drawTooManyError() {
        String dummy = "No one needs more than 10 friends.  Delete one before adding another.";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(24, 45, AnsiText.white, AnsiText.black, PressEnter, false);
    }


    public static void drawDelete(Person contact) {
        String dummy = "Remember " + contact.getNickname() + "?  Because Lil Black Screen doesn't.";
        String dummytwo = "Your contact is gone.";
        clearIt();
        drawBorder();
        AnsiText.printMessage(22, 45, AnsiText.red, AnsiText.black, dummy, true);
        AnsiText.printMessage(25, 45, AnsiText.red, AnsiText.black, dummytwo, true);
        AnsiText.printMessage(27, 45, AnsiText.white, AnsiText.black, PressEnter, false);
    }

    public static void drawContactDetail(Person contact) {
        clearScreen();
        clearIt();
        drawBorder();

        String name = contact.getFullName();
        String email = contact.getEmail();
        String address = contact.getFullAddress();
        String phone = contact.getPhone();

        AnsiText.printMessage(14, 30, AnsiText.white, AnsiText.black, name, false);
        AnsiText.printMessage(15, 30, AnsiText.white, AnsiText.black, address, false);
        AnsiText.printMessage(16, 30, AnsiText.white, AnsiText.black, email, false);
        AnsiText.printMessage(17, 30, AnsiText.white, AnsiText.black, phone, false);
    }

    public static void drawContactOptions(Person contact) {
        String one = "What would you like to do?";
        String two = "1.  Edit Contact";
        String tree = "2.  Delete Contact";
        String fore = "3.  Return to Contact Page";
        String fife = "4.  Return to Main Menu";
        String six = "5.  Exit Lil Black Screen ";

        AnsiText.printMessage(20, 30, AnsiText.white, AnsiText.black, one, false);
        AnsiText.printMessage(22, 30, AnsiText.white, AnsiText.black, two, false);
        AnsiText.printMessage(23, 30, AnsiText.white, AnsiText.black, tree, false);
        AnsiText.printMessage(24, 30, AnsiText.white, AnsiText.black, fore, false);
        AnsiText.printMessage(25, 30, AnsiText.white, AnsiText.black, fife, false);
        AnsiText.printMessage(26, 30, AnsiText.white, AnsiText.black, six, false);
    }

    public static void drawEditContact() {
        String one = "What would you like to edit?";
        String two = "1. Name";
        String tree = "2. Email";
        String fore = "3. Phone";
        String fife = "4. Nickname";
        String six = "5. Main Menu ";
        AnsiText.printMessage(20, 30, AnsiText.white, AnsiText.black, one, false);
        AnsiText.printMessage(22, 30, AnsiText.white, AnsiText.black, two, false);
        AnsiText.printMessage(23, 30, AnsiText.white, AnsiText.black, tree, false);
        AnsiText.printMessage(24, 30, AnsiText.white, AnsiText.black, fore, false);
        AnsiText.printMessage(25, 30, AnsiText.white, AnsiText.black, fife, false);
        AnsiText.printMessage(26, 30, AnsiText.white, AnsiText.black, six, false);
    }

    public static void drawEditContactName(Person contact) {
        clearScreen();
        clearIt();
        drawBorder();
        String one = "Contact's current first name is " + contact.getFirstName();
        String two = "Enter the new first name  ";
        AnsiText.printMessage(20, 30, AnsiText.white, AnsiText.black, one, false);
        AnsiText.printMessage(22, 30, AnsiText.white, AnsiText.black, two, false);
    }

    public static void drawEditContactLName(Person contact) {
        String one = "Contact's current last name is " + contact.getLastName();
        String two = "Enter the new last name  ";
        AnsiText.printMessage(24, 30, AnsiText.white, AnsiText.black, one, false);
        AnsiText.printMessage(26, 30, AnsiText.white, AnsiText.black, two, false);
    }

    public static void drawEditContactEmail(Person contact) {
        clearScreen();
        clearIt();
        drawBorder();
        String one = "Contact's current email is " + contact.getEmail();
        String two = "Enter the new email ";
        AnsiText.printMessage(24, 30, AnsiText.white, AnsiText.black, one, false);
        AnsiText.printMessage(26, 30, AnsiText.white, AnsiText.black, two, false);
    }

    public static void drawEditContactPhone(Person contact) {
        clearScreen();
        clearIt();
        drawBorder();
        String one = "Contact's current phone is " + contact.getPhone();
        String two = "Enter the new phone ";
        AnsiText.printMessage(24, 30, AnsiText.white, AnsiText.black, one, false);
        AnsiText.printMessage(26, 30, AnsiText.white, AnsiText.black, two, false);
    }

    public static void drawEditContactNickname(Person contact) {
        clearScreen();
        clearIt();
        drawBorder();
        String one = "Contact's current nickname is " + contact.getNickname();
        String two = "Enter the new nickname  ";
        AnsiText.printMessage(24, 30, AnsiText.white, AnsiText.black, one, false);
        AnsiText.printMessage(26, 30, AnsiText.white, AnsiText.black, two, false);
    }

    public static void drawContactList(ArrayList<Person> contactz) {
        clearScreen();
        clearIt();
        drawBorder();
        String options = "Select a contact by number to view more detail.  ";
        int row = 14;
        for (int i = 0; i < contactz.size(); i++) {
            int index = (i + 1);
            Person contact = contactz.get(i);
            String dummy = index + ".  " + contact.getNickname();
            AnsiText.printMessage(row, 30, AnsiText.white, AnsiText.black, dummy, true);
            row++;
        }
        AnsiText.printMessage(35, 30, AnsiText.white, AnsiText.black, options, false);
    }

    public static void drawCantFindContact() {
        clearScreen();
        clearIt();
        drawBorder();
        String search = "I couldn't find the contact...  So, there's that.";

        AnsiText.printMessage(24, 30, AnsiText.white, AnsiText.black, search, false);
    }

    public static void drawSearch() {
        clearScreen();
        clearIt();
        drawBorder();
        String dummy = "Searches can be performed on first or last name or email.  ";
        AnsiText.printMessage(24, 30, AnsiText.white, AnsiText.black, dummy, false);
    }

}