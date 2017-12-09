package contacts;

import contacts.display.Screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

import static contacts.display.AnsiText.clearScreen;
import static javafx.application.Platform.exit;

public class Contacts {
    //*****Create final variables to be used as parsing indexes*******
    private static final int ID		    = 0;
    private static final int FIRSTNAME	= 1;
    private static final int LASTNAME	= 2;
    private static final int NICKNAME	= 3;
    private static final int EMAIL		= 4;
    private static final int STREET	= 5;
    private static final int CITY		= 6;
    private static final int STATE		= 7;
    private static final int ZIPCODE	= 8;
    private static final int PHONE		= 9;


    private static ArrayList<Person> contactz = new ArrayList<>();
    private static final String[] statelist = new String[]{"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"};
    private static final String zipRegex = "^[0-9]{5}(?:-[0-9]{4})?$";
    private static Scanner sc = new Scanner(System.in);
    public static String firstName;
    public static String lastName;
    public static String nickName;
    public static String phone;
    public static String email;
    private static String regexStr = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
    private static String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static String address;
    public static String street;
    private static String state;
    public static String city;
    private static String zip;
    private static int id;

    public static void main(String[] args) {
        loadContacts();
        displayMenu();
    }

    private static void listContacts() {


        if (contactz.isEmpty()) {
            Screen.nobody();
            promptEnter();
            displayMenu();
        } else {
            Screen.drawContactList(contactz);
            try {

                Integer choice = sc.nextInt();
                if (choice <= 0) {
                    invalidInputError();
                }
                int trueChoice = choice - 1;
                displayContactDetail(trueChoice);
            } catch (InputMismatchException exception) {
                Screen.nope();
                promptEnter();
                listContacts();
            }


        }
    }

    private static void createContactFName() {
        if (contactz.size() == 10) {
            Screen.drawTooManyError();
            promptEnter();
            sc.nextLine();
            displayMenu();
        } else {
            Screen.drawContactFirstName();
            firstName = sc.nextLine();
            if (firstName.length() > 1 && firstName.length() <= 15) {
                createContactLName();
            } else {
                Screen.drawFNameError();
                promptEnter();
                Screen.clearScreen();
                createContactFName();
            }
        }
    }

    private static void createContactLName() {
        Screen.drawContactLastName();
        lastName = sc.nextLine();
        if (lastName.length() > 1 && lastName.length() <= 15) {
            createContactNickName();
        } else {
            Screen.drawFNameError();
            promptEnter();
            createContactLName();
        }
    }

    private static void createContactNickName() {
        Screen.drawContactNickName();
        nickName = sc.nextLine();
        if (nickName.length() > 1 && lastName.length() <= 15) {
            createContactPhone();
        } else {
            Screen.drawFNameError();
            promptEnter();
            createContactNickName();
        }
    }

    private static void createContactPhone() {
        Screen.drawPhone();
        phone = sc.nextLine();
        if (phone.matches(regexStr)) {
            createContactEmail();
        } else {
            Screen.drawPhoneError();
            promptEnter();
            createContactPhone();
        }
    }

    private static void createContactEmail() {
        Screen.drawContactEmail();
        email = sc.nextLine();
        if (email.matches(emailRegex)) {
            createContactAddress();
        } else {
            Screen.drawEmailError();
            promptEnter();
            createContactEmail();
        }
    }

    private static void createContactAddress() {
        Screen.drawContactAddress();
        address = sc.nextLine();
        if (address.length() > 1) {
            createContactStreet();
        } else {
            Screen.drawGeneralError();
            promptEnter();
            createContactAddress();
        }
    }

    private static void createContactStreet() {
        Screen.drawContactStreet();
        street = sc.nextLine();
        if (street.length() < 1) {
            Screen.drawGeneralError();
            promptEnter();
            createContactStreet();
        } else {
            createContactState();
        }
    }

    private static void createContactState() {
        Screen.drawContactState();
        state = sc.nextLine();
        String upCase = state.toUpperCase();
        if (state.length() != 2) {
            Screen.drawStateError();
            promptEnter();
            createContactState();
        } else if (!Arrays.asList(statelist).contains(upCase)) {
            Screen.drawStateNotFound();
            promptEnter();
            createContactState();
        } else {
            createContactCity();
        }
    }

    private static void createContactCity() {
        Screen.drawContactCity();
        city = sc.nextLine();
        if (city.length() < 3 || city.length() > 22) {
            Screen.drawCityError();
            promptEnter();
            createContactCity();
        } else {
            createContactZip();
        }
    }

    private static void createContactZip() {
        Screen.drawContactZip();
        zip = sc.nextLine();
        if (zip.matches(zipRegex)) {
            createPerson();
        } else {
            Screen.notInt();
            promptEnter();
            createContactZip();
        }
    }

    private static void createPerson() {
        Person newContact = new Person(id, firstName, lastName, nickName,
                email, address, city, state, zip, phone);
        contactz.add(newContact);
        Screen.drawSuccess();
        promptEnter();
        displayMenu();
    }
//    String firstName, String lastName, String nickname,
//    String email, String address, String street, String city,
//    String state, String zipCode, String phone

    private static void deleteContact(int id) {
        Person contact = contactz.get(id);
        Screen.drawDelete(contact);
        contactz.remove(id);
        promptEnter();
        sc.nextLine();
        listContacts();
    }

    private static void searchContacts(ArrayList<Person> contactz) {
        sc.nextLine();
        Screen.drawSearch();
        String searchValue = sc.nextLine();

        for (Person contact : contactz) {
            String first = contact.getFirstName().toLowerCase();
            String last = contact.getLastName().toLowerCase();
            String nick = contact.getNickname().toLowerCase();
            String email = contact.getEmail();
            String search = searchValue.toLowerCase();
            if (first.equals(search) ||
                    nick.equals(search) ||
                    last.equals(search) ||
                    email.equals(search)) {
                displayContactDetail(contactz.indexOf(contact));
            }
        }

        Screen.drawCantFindContact();
        promptEnter();
        displayMenu();

    }

    private static void invalidInputError() {
        sc.nextLine();
        Screen.nope();
        promptEnter();
        displayMenu();
    }

    private static void promptEnter() {
        sc.nextLine();
    }

    private static void displayMenu() {

        Screen.drawMainMenu();
        try {
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    listContacts();
                    break;
                case 2:
                    createContactFName();
                    break;
                case 3:
                    searchContacts(contactz);
                    break;
                case 4:
                    exit();
                    break;
                default:
                    invalidInputError();
                    break;
            }

        } catch (InputMismatchException exception) {
            invalidInputError();
        }
    }

    private static void displayContactDetail(int id) {
        if (id > contactz.size()) {
            Screen.nope();
            promptEnter();
            displayMenu();
        }

        if (id < contactz.size()) {
            Person contact = contactz.get(id);
            clearScreen();
            Screen.drawContactDetail(contact);
            contactOptions(id, contact);
        } else {
            Screen.nope();
            promptEnter();
            displayContactDetail(id);
        }
    }

    private static void contactOptions(int id, Person contact) {
        Screen.drawContactOptions(contact);
        try {
            int choice = sc.nextInt();
            if (choice <= 0) {
                invalidInputError();
            }
            switch (choice) {
                case 1:
                    editContact(id);
                    break;
                case 2:
                    deleteContact(id);
                    break;
                case 3:
                    listContacts();
                    break;
                case 4:
                    displayMenu();
                    break;
                case 5:
                    exit();
                    break;
                default:
                    invalidInputError();
                    break;
            }
        } catch (InputMismatchException exception) {
            Screen.nope();
            promptEnter();
            displayContactDetail(id);
        }

    }

    private static void editContact(int id) {
        Person contact = contactz.get(id);
        Screen.drawContactDetail(contact);
        Screen.drawEditContact();

        try {
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    editContactName(id);
                    break;
                case 2:
                    editContactEmail(id);
                    break;
                case 3:
                    editContactPhone(id);
                    break;
                case 4:
                    editContactNickname(id);
                    break;
                case 5:
                    displayMenu();
                    break;
                default:
                    invalidInputError();
            }


        } catch (InputMismatchException exception)

        {
            Screen.nope();
            promptEnter();
            editContact(id);
        }

    }

    private static void editContactName(int id) {

        Person contact = contactz.get(id);
        Screen.drawEditContactName(contact);
        String newFirstName = sc.nextLine();
        if (newFirstName.length() > 1 && newFirstName.length() <= 15) {
            contact.setFirstName(newFirstName);
            Screen.drawEditContactLName(contact);
        } else {
            Screen.drawFNameError();
            promptEnter();
            Screen.clearScreen();
            editContactName(id);
        }

        String newLastName = sc.nextLine();
        if (newLastName.length() > 1 && newLastName.length() <= 15) {
            contact.setLastName(newLastName);
            Screen.drawSuccess();
            promptEnter();
            displayContactDetail(id);
        } else {
            Screen.drawFNameError();
            promptEnter();
            editContactName(id);
        }
    }

    private static void editContactPhone(int id) {
        Person contact = contactz.get(id);
        Screen.drawEditContactPhone(contact);
        sc.nextLine();
        String newPhone = sc.nextLine();
        if (newPhone.matches(regexStr)) {
            contact.setPhone(newPhone);
            Screen.drawSuccess();
            promptEnter();
            displayContactDetail(id);
        } else {
            Screen.drawPhoneError();
            promptEnter();
            editContactPhone(id);
        }
    }

    private static void editContactEmail(int id) {
        Person contact = contactz.get(id);
        Screen.drawEditContactEmail(contact);
        sc.nextLine();
        String newEmail = sc.nextLine();
        if (newEmail.matches(emailRegex)) {
            contact.setEmail(newEmail);
            Screen.drawSuccess();
            promptEnter();
            displayContactDetail(id);
        } else {
            Screen.drawEmailError();
            promptEnter();
            editContactEmail(id);
        }
    }

    private static void editContactNickname(int id) {
        Person contact = contactz.get(id);
        Screen.drawEditContactNickname(contact);
        sc.nextLine();
        String newNickName = sc.nextLine();
        if (newNickName.length() > 1 && newNickName.length() < 15) {
            contact.setNickname(newNickName);
            Screen.drawSuccess();
            promptEnter();
            displayContactDetail(id);
        } else {
            Screen.drawFNameError();
            promptEnter();
            editContactNickname(id);
        }

    }

    private static void loadContacts() {
        String[] addressBook = {
                "1,Dennis,Sanders,Dk,dksand@comcast.net,5335 Gathering Oaks Ct E,Jacksonville,FL,32258,904-207-8023",
                "2,Christopher,Sanders,The Dude,christopher-sanders@hotmail.com,601 Crestview Dr,Legrand,IA,50142,641-751-8981",
                "3,Bettysue,Sugarbottom,Sweety,bs.sugar@nowhere.com,123 Beachside Rd,Atlantic Beach,FL,32486,904-555-1111",
                "4,Joejohn,Jaberwocky,Jack,jaberwocky@goto.com,7854 Redneck Hwy,Wayout,GA,31085,960-555-9173",
                "5,Biff,Persay,Ugh,bper@bper.net,9908 Penthouse Lane,Smalltown,IA,52841,319-555-1044"};

        for(int personX = 0; personX < addressBook.length; personX++){

//    ************PARSE ARRAY************
            String contactInfo[] = addressBook[personX].split(",");

            boolean addThis;
            Person newContact = new Person();

           addThis = newContact.setId(Integer.parseInt(contactInfo[ID]));
           addThis = newContact.setFirstName(contactInfo[FIRSTNAME]);
           addThis = newContact.setLastName(contactInfo[LASTNAME]);
           addThis = newContact.setNickname(contactInfo[NICKNAME]);
           addThis = newContact.setEmail(contactInfo[EMAIL]);
           addThis = newContact.setStreet(contactInfo[STREET]);
           addThis = newContact.setCity(contactInfo[CITY]);
           addThis = newContact.setState(contactInfo[STATE]);
           addThis = newContact.setZipCode(contactInfo[ZIPCODE]);
           addThis = newContact.setPhone(contactInfo[PHONE]);

            contactz.add(newContact);
        }
    }




}
