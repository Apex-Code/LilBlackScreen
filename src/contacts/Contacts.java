package contacts;

import contacts.display.Screen;

import java.util.*;

import static contacts.display.AnsiText.clearScreen;
import static javafx.application.Platform.exit;

public class Contacts {
    //*****Create final variables to be used as parsing indexes*******
    private static final int ID = 0;
    private static final int FIRSTNAME = 1;
    private static final int LASTNAME = 2;
    private static final int NICKNAME = 3;
    private static final int EMAIL = 4;
    private static final int STREET = 5;
    private static final int CITY = 6;
    private static final int STATE = 7;
    private static final int ZIPCODE = 8;
    private static final int PHONE = 9;


    private static ArrayList<Person> contactz = new ArrayList<>();

    private static Scanner sc = new Scanner(System.in);
    public static String firstName;
    public static String lastName;
    public static String nickName;
    public static String phone;
    public static String email;
    public static String address;
    public static String street;
    private static String state;
    public static String city;
    private static String zip;
    private static int id;
//{1} Add a Contact",
//{2} View Contacts",
//{3} Search Contacts",
//{4} Close Lil' Black Book"

    public static void main(String[] args) {
        String menuChoice = "";
        loadContacts();
        Screen.drawFrame();
        while(!"4".equals(menuChoice)){  //As long as user doesn't enter 4, show the menu.
            Screen.drawMenu();            menuChoice = Screen.getMenuSelection();

            switch (menuChoice){
                case "1": //Add a new Contact
                    if(contactz.size() == 10){
                        Screen.drawRecord(Screen.ADD, false);
                        Screen.printError("You must delete contact to add another.");
                        Screen.pauseScreen();
                    } else{
                        addContact();
                    }
                    break;

                case "2": listContacts();
                break;

                case "3" : searchContacts();
                break;

                case "4" :
                    //Null on purpose, we're going home
                    break;

                default:
                    break;
            }
        }
        Screen.clearScreen();// Cleanup our mess
    }

    private static void addContact(){
        String firstName, lastName, nickName, street, city, state,
                zipCode, email, phone;

        boolean firstNameSet = false;
        boolean lastNameSet = false;
        boolean nickNameSet = false;
        boolean streetSet = false;
        boolean citySet = false;
        boolean stateSet = false;
        boolean zipCodeSet = false;
        boolean emailSet = false;
        boolean phoneSet = false;

        Person newContact = new Person();

        Screen.drawRecord(Screen.ADD, true);

        newContact.setId(getNextId());

        while(!firstNameSet){
            firstName = Screen.getFirstName();
            firstNameSet = newContact.setFirstName(firstName);
                if(!firstNameSet){
                    Screen.displayFirstName(Screen.FIELDFIRSTNAME, true);
                    Screen.printError("First Name is required and must be less than " + Screen.FIRSTNAME + " characters.");
                }
                else
                    Screen.printError("");
        }
        while (!lastNameSet) {
            lastName = Screen.getLastName();
            lastNameSet = newContact.setLastName(lastName);
            if (!lastNameSet) {
                Screen.displayLastName(Screen.FIELDLASTNAME, true);
                Screen.printError("Last Name is required and must be less than " + Screen.LASTNAME + " characters.");
            }
            else
                Screen.printError("");
        }
        while (!nickNameSet) {
            nickName = Screen.getNickName();
            nickNameSet = newContact.setNickname(nickName);
            if (!nickNameSet) {
                Screen.displayNickName(Screen.FIELDNICKNAME, true);
                Screen.printError("Nick Name must be less than " + Screen.NICKNAME + " characters.");
            }
            else
                Screen.printError("");
        }
        while (!streetSet) {
            street = Screen.getStreet();
            streetSet = newContact.setStreet(street);
            if (!streetSet) {
                Screen.displayStreet(Screen.FIELDSTREET, true);
                Screen.printError("Street must be less than " + Screen.STREET + " characters.");
            }
            else
                Screen.printError("");
        }
        while (!citySet) {
            city = Screen.getCity();
            citySet = newContact.setCity(city);
            if (!citySet) {
                Screen.displayCity(Screen.FIELDCITY, true);
                Screen.printError("Street must be less than " + Screen.CITY + " characters.");
            }
            else
                Screen.printError("");
        }
        while (!stateSet) {
            state = Screen.getState();
            stateSet = state.trim().length() == 0 || newContact.setState(state);
            if (!stateSet) {
                Screen.displayState(Screen.FIELDSTATE, true);
                Screen.printError("State must be an actual " + Screen.STATE + " letter state.");
            } else
                Screen.printError("");
        }
        while(!zipCodeSet) {
            zipCode = Screen.getZipCode().toUpperCase();
            zipCodeSet = zipCode.trim().length() == 0 || newContact.setZipCode(zipCode);
            if (!zipCodeSet) {
                Screen.displayZipCode(Screen.FIELDZIPCODE, true);
                Screen.printError("Zip Code must be a valid " + Screen.ZIPCODE + " digit USPS Zip Code.");
            } else
                Screen.printError("");
        }
        while (!emailSet) {
            email = Screen.getEmail();
            emailSet = email.trim().length() == 0 || newContact.setEmail(email);
            if (!emailSet) {
                Screen.displayEmail(Screen.FIELDEMAIL, true);
                Screen.printError("Use a valid email that's less than " + Screen.EMAIL + "characters");
            } else
                Screen.printError("");
        }
        while (!phoneSet) {
            phone = Screen.getPhone();
            phoneSet = phone.trim().length() == 0 || newContact.setPhone(phone);
            if (!phoneSet) {
                Screen.displayPhone(Screen.FIELDPHONE, true);
                Screen.printError("Telephone must be legit.  I have regex... So, there's that.");
            } else
                Screen.printError("");
        }
        contactz.add(newContact);
    }

    private static void listContacts() {
        Screen.drawList();
        for (int index = 0; index < contactz.size(); index++) {
            Screen.drawListData(	index,
                    contactz.get(index).getId(),
                    contactz.get(index).getFirstName(),
                    contactz.get(index).getLastName(),
                    contactz.get(index).getNickname(),
                    contactz.get(index).getPhone());
        }
        Screen.pauseScreen();
    }
    /**
     * searchContacts() method. Allows the user to search on any combination of
     * firstName, lastName and nickName. Search is a contains() search, so partial
     * matches are discovered.
     * If a matching record is found, displays that contact record and allows
     * the user to edit or delete the record.
     * If no matching record, displays an error stating no matching records
     * and returns the user to the menu after an ENTER pause.
     */
    private static void searchContacts() {
        String firstName;
        String lastName;
        String nickName;
        int recordFound = -1;
        String displayChoice = "0";

        Screen.drawSearch();
        firstName = Screen.getSearchFirstName().trim().toLowerCase();
        lastName = Screen.getSearchLastName().trim().toLowerCase();
        nickName = Screen.getSearchNickName().trim().toLowerCase();

        // Search contactz
        if (!(firstName.length() == 0 && lastName.length() == 0 && nickName.length() == 0)) { // No Entries on any of them = not found
            for (int arrayIdx = 0; arrayIdx < contactz.size(); arrayIdx++) {
                if (((firstName.length() == 0) || contactz.get(arrayIdx).getFirstName().toLowerCase().contains(firstName)) &&
                        ((lastName.length() == 0) || contactz.get(arrayIdx).getLastName().toLowerCase().contains(lastName))  &&
                        ((nickName.length() == 0) || contactz.get(arrayIdx).getNickname().toLowerCase().contains(nickName))) {
                    recordFound = arrayIdx;
                    break;
                }
            }
        }

        if (recordFound >= 0) { // If found, do a while loop on Edit, Delete, Return
            while (Integer.parseInt(displayChoice) < 1 || Integer.parseInt(displayChoice) > 3) {
                displayContact(recordFound);

                displayChoice = Screen.getMenuSelection();
                switch (displayChoice) {
                    case "1": // Edit
                        editContact(recordFound);
                        break;
                    case "2": // Delete
                        deleteContact(recordFound);
                        break;
                    case "3": // Return to menu
                        // null ops
                        break;
                    default:
                        displayChoice = "0";
                        break;
                }
            }
        } else {
            Screen.printError("Nothing was found matching that criteria...");
            Screen.pauseScreen();
        }
    }


    private static void displayContact(int arrayPointer) {
        Screen.drawRecord(Screen.DISPLAY, false);
        // Output the fields
        Screen.displayFirstName(contactz.get(arrayPointer).getFirstName(), false);
        Screen.displayLastName(contactz.get(arrayPointer).getLastName(), false);
        Screen.displayNickName(contactz.get(arrayPointer).getNickname(), false);
        Screen.displayStreet(contactz.get(arrayPointer).getStreet(), false);
        Screen.displayCity(contactz.get(arrayPointer).getCity(), false);
        Screen.displayState(contactz.get(arrayPointer).getState(), false);
        Screen.displayZipCode(contactz.get(arrayPointer).getZipCode(), false);
        Screen.displayEmail(contactz.get(arrayPointer).getEmail(), false);
        Screen.displayPhone(contactz.get(arrayPointer).getPhone(), false);
        // Output the one line menu for display
        Screen.drawOneMenu(Screen.ONEMENUDISPLAY);
    }

    private static void loadContacts() {
        String[] addressBook = {
                "1,Dennis,Sanders,Dk,dksand@comcast.net,5335 Gathering Oaks Ct E,Jacksonville,FL,32258,904-207-8023",
                "2,Christopher,Sanders,The Dude,christopher-sanders@hotmail.com,601 Crestview Dr,Legrand,IA,50142,641-751-8981",
                "3,Bettysue,Sugarbottom,Sweety,bs.sugar@nowhere.com,123 Beachside Rd,Atlantic Beach,FL,32486,904-555-1111",
                "4,Joejohn,Jaberwocky,Jack,jaberwocky@goto.com,7854 Redneck Hwy,Wayout,GA,31085,960-555-9173",
                "5,Biff,Persay,Ugh,bper@bper.net,9908 Penthouse Lane,Smalltown,IA,52841,319-555-1044"};

        for (String anAddressBook : addressBook) {

//    ************PARSE ARRAY************
            String contactInfo[] = anAddressBook.split(",");


            Person newContact = new Person();

            newContact.setId(Integer.parseInt(contactInfo[ID]));
            newContact.setFirstName(contactInfo[FIRSTNAME]);
            newContact.setLastName(contactInfo[LASTNAME]);
            newContact.setNickname(contactInfo[NICKNAME]);
            newContact.setEmail(contactInfo[EMAIL]);
            newContact.setStreet(contactInfo[STREET]);
            newContact.setCity(contactInfo[CITY]);
            newContact.setState(contactInfo[STATE]);
            newContact.setZipCode(contactInfo[ZIPCODE]);
            newContact.setPhone(contactInfo[PHONE]);

            contactz.add(newContact);
        }
    }

    private static void deleteContact(int arrayPointer) {
        String deleteChoice = "0";
        while (!("1".equals(deleteChoice) || "2".equals(deleteChoice))) {
            Screen.drawRecord(Screen.DELETE, false);
            // Output the fields
            Screen.displayFirstName(contactz.get(arrayPointer).getFirstName(), false);
            Screen.displayLastName(contactz.get(arrayPointer).getLastName(), false);
            Screen.displayNickName(contactz.get(arrayPointer).getNickname(), false);
            Screen.displayStreet(contactz.get(arrayPointer).getStreet(), false);
            Screen.displayCity(contactz.get(arrayPointer).getCity(), false);
            Screen.displayState(contactz.get(arrayPointer).getState(), false);
            Screen.displayZipCode(contactz.get(arrayPointer).getZipCode(), false);
            Screen.displayEmail(contactz.get(arrayPointer).getEmail(), false);
            Screen.displayPhone(contactz.get(arrayPointer).getPhone(), false);
            // Output the one line menu for display
            Screen.drawOneMenu(Screen.ONEMENUDELETE);
            Screen.printError("You are about to delete this Contact. Confirm deletion.");
            deleteChoice = Screen.getMenuSelection();
            switch (deleteChoice) {
                case "1": // Confirm Delete
                    // Temporary display
                    contactz.remove(arrayPointer);
                    break;
                case "2": // Cancel Delete
                    // Null on purpose, we're going back...
                    break;
                default:
                    break;
            }
        }
    }

    private static void editContact(int arrayPointer) {
        String firstName;
        String lastName;
        String nickName;
        String street;
        String city;
        String state;
        String zipcode;
        String email;
        String phone;

        boolean firstNameSet = false;
        boolean lastNameSet = false;
        boolean nickNameSet = false;
        boolean streetSet = false;
        boolean citySet = false;
        boolean stateSet = false;
        boolean zipcodeSet = false;
        boolean emailSet = false;
        boolean phoneSet = false;


        Screen.drawRecord(Screen.EDIT, false);
        // Output the fields
        Screen.displayFirstName(contactz.get(arrayPointer).getFirstName() + String.join("", Collections.nCopies(Screen.FIRSTNAME - contactz.get(arrayPointer).getFirstName().length(), " ")), true);
        Screen.displayLastName(contactz.get(arrayPointer).getLastName() + String.join("", Collections.nCopies(Screen.LASTNAME - contactz.get(arrayPointer).getLastName().length(), " ")), true);
        Screen.displayNickName(contactz.get(arrayPointer).getNickname() + String.join("", Collections.nCopies(Screen.NICKNAME - contactz.get(arrayPointer).getNickname().length(), " ")), true);
        Screen.displayStreet(contactz.get(arrayPointer).getStreet() + String.join("", Collections.nCopies(Screen.STREET - contactz.get(arrayPointer).getStreet().length(), " ")), true);
        Screen.displayCity(contactz.get(arrayPointer).getCity() + String.join("", Collections.nCopies(Screen.CITY - contactz.get(arrayPointer).getCity().length(), " ")), true);
        Screen.displayState(contactz.get(arrayPointer).getState() + String.join("", Collections.nCopies(Screen.STATE - contactz.get(arrayPointer).getState().length(), " ")), true);
        Screen.displayZipCode(contactz.get(arrayPointer).getZipCode() + String.join("", Collections.nCopies(Screen.ZIPCODE - contactz.get(arrayPointer).getZipCode().length(), " ")), true);
        Screen.displayEmail(contactz.get(arrayPointer).getEmail() + String.join("", Collections.nCopies(Screen.EMAIL - contactz.get(arrayPointer).getEmail().length(), " ")), true);
        Screen.displayPhone(contactz.get(arrayPointer).getPhone() + String.join("", Collections.nCopies(Screen.PHONE - contactz.get(arrayPointer).getPhone().length(), " ")), true);


        while (!firstNameSet) {
            firstName = Screen.getFirstName();
            firstNameSet = firstName.length() == 0 || contactz.get(arrayPointer).setFirstName(firstName);
            if (!firstNameSet) {
                Screen.displayFirstName(contactz.get(arrayPointer).getFirstName() + String.join("", Collections.nCopies(Screen.FIRSTNAME - contactz.get(arrayPointer).getFirstName().length(), " ")), true);
                Screen.printError("First Name is required and must be less than " + Screen.FIRSTNAME + " characters.");
            } else
                Screen.printError("");
        }
        while (!lastNameSet) {
            lastName = Screen.getLastName();
            lastNameSet = lastName.length() == 0 || contactz.get(arrayPointer).setLastName(lastName);
            if (!lastNameSet) {
                Screen.displayLastName(contactz.get(arrayPointer).getLastName() + String.join("", Collections.nCopies(Screen.LASTNAME - contactz.get(arrayPointer).getLastName().length(), " ")), true);
                Screen.printError("Last Name is required and must be less than " + Screen.LASTNAME + " characters.");
            } else
                Screen.printError("");
        }
        while (!nickNameSet) {
            nickName = Screen.getNickName();
            nickNameSet = nickName.length() == 0 || contactz.get(arrayPointer).setNickname(nickName);
            if (!nickNameSet) {
                Screen.displayNickName(contactz.get(arrayPointer).getNickname() + String.join("", Collections.nCopies(Screen.NICKNAME - contactz.get(arrayPointer).getNickname().length(), " ")), true);
                Screen.printError("Nick Name must be less than " + Screen.NICKNAME + " characters.");
            } else
                Screen.printError("");
        }
        while (!streetSet) {
            street = Screen.getStreet();
            streetSet = street.length() == 0 || contactz.get(arrayPointer).setStreet(street);
            if (!streetSet) {
                Screen.displayStreet(contactz.get(arrayPointer).getStreet() + String.join("", Collections.nCopies(Screen.STREET - contactz.get(arrayPointer).getStreet().length(), " ")), true);
                Screen.printError("Street must be less than " + Screen.STREET + " characters.");
            } else
                Screen.printError("");
        }
        while (!citySet) {
            city = Screen.getCity();
            citySet = city.length() == 0 || contactz.get(arrayPointer).setCity(city);
            if (!citySet) {
                Screen.displayCity(contactz.get(arrayPointer).getCity() + String.join("", Collections.nCopies(Screen.CITY - contactz.get(arrayPointer).getCity().length(), " ")), true);
                Screen.printError("Street must be less than " + Screen.CITY + " characters.");
            } else
                Screen.printError("");
        }
        while (!stateSet) {
            state = Screen.getState();
            stateSet = state.length() == 0 || contactz.get(arrayPointer).setState(state);
            if (!stateSet) {
                Screen.displayState(contactz.get(arrayPointer).getState() + String.join("", Collections.nCopies(Screen.STATE - contactz.get(arrayPointer).getState().length(), " ")), true);
                Screen.printError("State must be a USPS " + Screen.STATE + " letter state.");
            } else
                Screen.printError("");
        }
        while (!zipcodeSet) {
            zipcode = Screen.getZipCode().toUpperCase();
            zipcodeSet = zipcode.length() == 0 || contactz.get(arrayPointer).setZipCode(zipcode);
            if (!zipcodeSet) {
                Screen.displayZipCode(contactz.get(arrayPointer).getZipCode() + String.join("", Collections.nCopies(Screen.ZIPCODE - contactz.get(arrayPointer).getZipCode().length(), " ")), true);
                Screen.printError("Zip Code must be a valid " + Screen.ZIPCODE + " digit USPS Zip Code.");
            }
            else
                Screen.printError("");
        }
        while (!emailSet) {
            email = Screen.getEmail();
            emailSet = (email.length() == 0) || contactz.get(arrayPointer).setEmail(email);
            if (!emailSet) {
                Screen.displayEmail(contactz.get(arrayPointer).getEmail() + String.join("", Collections.nCopies(Screen.EMAIL - contactz.get(arrayPointer).getEmail().length(), " ")), true);
                Screen.printError("EMail must be a valid email address and no longer than " + Screen.EMAIL + " characters.");
            }
            else
                Screen.printError("");
        }
        while (!phoneSet) {
            phone = Screen.getPhone();
            phoneSet = phone.length() == 0 || contactz.get(arrayPointer).setPhone(phone);
            if (!phoneSet) {
                Screen.displayPhone(contactz.get(arrayPointer).getPhone() + String.join("", Collections.nCopies(Screen.PHONE - contactz.get(arrayPointer).getPhone().length(), " ")), true);
                Screen.printError("Telephone must be a valid US Phone number (123-555-4321).");
            } else
                Screen.printError("");
        }
    }

    /**
     * getNextId() method. Finds the largest person id and returns the next
     * larger number for use when adding a new contact to the list.
     * @return int nextId. The next person id to use when adding a record.
     */

    private static int getNextId() {
        int nextId = 0;
        for (Person person : contactz) {
            if (person.getId() > nextId)
                nextId = person.getId();
        }
        nextId++;
        return nextId;
    }




}
