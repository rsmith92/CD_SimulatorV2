import java.util.ArrayList;

public class ChangeDirectory {

    // finds the starting directory and then traverses the directories
    static String mycd(String currDir, String newDir) {
        // split new_dir on the character "/"
        String[] newDirArr = newDir.split("/");
        // currSym initialized as null
        String currSym = null;

        // catch currDir inputs that don't make sense
        try {
            checkInput(currDir);
        }
        catch(NoSuchFieldException e) {
            return currDir + " : No such file or directory.";
        }

        // if there is nothing left in the array OR the first item was "/", currDirectory is the root, /
        if(newDirArr.length == 0 || newDirArr[0].equals("")) {
            currDir = "/";
        }

        // remove the holes ("") from the array
        newDirArr = removeHolesLst(newDirArr);

        // traverse the rest of the array, example [gh,..,klm,.]
        for(String i : newDirArr) {
            currSym = i;
            // if currSym == .
            if (currSym.equals(".")) {
                ; // do nothing to currDir
            }

            // else if currSym == ..
            else if (currSym.equals("..")) {
                // remove the last item from the list using lastIndexOf and substring
                currDir = currDir.substring(0, currDir.lastIndexOf("/"));

                // make sure you don't remove the final / in the directory
                if(currDir.length() == 0) {
                    currDir = "/";
                }
            }

            // currSym == name
            else {
                // check if currSym is alphanumeric
                if (isAlphaNumeric(currSym)) {
                    // if the only thing left is /, we don't need to add one
                    if(currDir.length() != 1) {
                        currDir += "/";
                    }
                    // add the alphanumeric directory to the currDir
                    currDir += currSym;

                }

                // if symbol isn't alphanumeric, return No such file string
                else {
                    return currSym + " : No such file or directory.";
                }
            }
        }

        // return the updated path
        return currDir;
    }



    // remove all of the holes from the list
    public static String[] removeHolesLst(String[] inputList) {
        ArrayList<String> resultArr = new ArrayList<String>();
        // loop through the list
        for(String i : inputList) {
            // this checks if current string is empty (hole)
            if(i.equals("")) {
                // if empty, continue
                continue;
            }
            // if not empty, add to result list
            resultArr.add(i);
        }
        return resultArr.toArray(new String[0]);
    }



    // find the string path of the Directory, if it exists
    public static Boolean isAlphaNumeric(String currString) {
        if(currString.length() == 0) {
            return false;
        }
        // turn the input string into a character array
        char[] charArr = currString.toCharArray();
        // loop through the characters, checking if each is alphanumeric
        for(char i : charArr) {
            // if not alphanumeric, return false
            if((!Character.isDigit(i)) && (!Character.isAlphabetic(i))) {
                return false;
            }
        }
        return true;
    }



    // checks a string to see that it is a valid path
    public static int checkInput(String currDir) throws NoSuchFieldException {
        // if the length is 0, or if the first char isn't /, throw an error
        if(currDir.length() == 0 || currDir.charAt(0) != '/') {
            throw new NoSuchFieldException();
        }

        // split and remove holes to ignore all /
        String[] currDirArr = currDir.split("/");
        currDirArr = removeHolesLst(currDirArr);

        // loop through each directory, checking to see that it is valid
        for(String currStr : currDirArr) {
            // if a directory isn't alphanumeric, throw an error
            if(!isAlphaNumeric(currStr)) {
                throw new NoSuchFieldException();
            }
        }
        return 0;
    }



    // all of these test cases (and many more) are implemented in the TestJunit.java file
    public static void main(String args[]) {
        // test cases from email
        System.out.print("/abc = ");
        System.out.println( ChangeDirectory.mycd("/", "abc") );

        System.out.print("/abc/def/ghi = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "ghi") );

        System.out.print("/abc = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "..") );

        System.out.print("/abc = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "/abc") );

        System.out.print("/abc/klm = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "/abc/klm") );

        System.out.print("/ = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "../..") );

        System.out.print("/ = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "../../..") );

        System.out.print("/abc/def = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", ".") );

        System.out.print("/ = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "//////") );

        System.out.print("....... : No such file or directory. = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "......") );

        System.out.print("/abc/klm = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "../gh///../klm/.") );


        // some additional test cases
        System.out.print("/ = ");
        System.out.println( ChangeDirectory.mycd("/", ".") );

        System.out.print("/abc = ");
        System.out.println( ChangeDirectory.mycd("/", "/abc") );

        System.out.print("/abc = ");
        System.out.println( ChangeDirectory.mycd("/abc", ".") );

        System.out.print("/def = ");
        System.out.println( ChangeDirectory.mycd("/def", ".") );

        System.out.print("/abc/def/cba = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "cba") );

        System.out.print("/ = ");
        System.out.println( ChangeDirectory.mycd("/abc/def", "") );

        System.out.print("/ = ");
        System.out.println( ChangeDirectory.mycd("/", "/") );

        System.out.print("/abc/def/ghi = ");
        System.out.println( ChangeDirectory.mycd("/", "..///.////abc/../abc/def/.././gh/..//def/ghi") );

        System.out.print("/abc/.def/ghi : No such file or directory. = ");
        System.out.println( ChangeDirectory.mycd("/abc/.def/ghi", "/") );

        System.out.print(".def : No such file or directory. = ");
        System.out.println( ChangeDirectory.mycd("/", "/abc/.def/ghi") );
    }
}
