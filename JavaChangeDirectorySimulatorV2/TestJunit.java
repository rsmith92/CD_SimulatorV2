import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestJunit {

    @Test
    void test_mycd() {
        // run the tests now that the directories exist in the environment of change_directory
        assertEquals(ChangeDirectory.mycd("/", "."), "/");
        assertEquals(ChangeDirectory.mycd("/", "abc"), "/abc");
        assertEquals(ChangeDirectory.mycd("/", "/abc"), "/abc");
        assertEquals(ChangeDirectory.mycd("/abc", "."), "/abc");
        assertEquals(ChangeDirectory.mycd("/abc/def", "ghi"), "/abc/def/ghi");
        assertEquals(ChangeDirectory.mycd("/abc/def", ".."), "/abc");
        assertEquals(ChangeDirectory.mycd("/abc/def", "/abc"), "/abc");
        assertEquals(ChangeDirectory.mycd("/abc/def", "/abc/klm"), "/abc/klm");
        assertEquals(ChangeDirectory.mycd("/abc/def", "../.."), "/");
        assertEquals(ChangeDirectory.mycd("/abc/def", "../../.."), "/");
        assertEquals(ChangeDirectory.mycd("/abc/def", "."), "/abc/def");
        assertEquals(ChangeDirectory.mycd("/abc/def", "//////"), "/");
        assertEquals(ChangeDirectory.mycd("/abc/def", "../gh///../klm/."), "/abc/klm");
        assertEquals(ChangeDirectory.mycd("/abc/def", "......"), "...... : No such file or directory.");
        assertEquals(ChangeDirectory.mycd("/abc/def", "cba"), "/abc/def/cba");
        assertEquals(ChangeDirectory.mycd("/", "/"), "/");
        assertEquals(ChangeDirectory.mycd("/def", "."), "/def");
        assertEquals(ChangeDirectory.mycd("/", "..///.////abc/../abc/def/.././gh/..//def/ghi"), "/abc/def/ghi");
    }

    @Test
    void test_checkInput() throws NoSuchFieldException {
        assertEquals(ChangeDirectory.checkInput("/"), 0);
        assertEquals(ChangeDirectory.checkInput("/abc/123/doremi"), 0);
        assertThrows(NoSuchFieldException.class, () -> ChangeDirectory.checkInput("")) ; // error
        assertThrows(NoSuchFieldException.class, () -> ChangeDirectory.checkInput("abc/123/doremi")) ; // error
        assertThrows(NoSuchFieldException.class, () -> ChangeDirectory.checkInput("/abc/.123^/doremi")) ; // error
    }

    @Test
    void test_removeHolesLst() {
        String[] input1 = new String[] {};
        String[] input2 = new String[] {""};
        String[] input3 = new String[] {"", "1"};
        String[] input4 = new String[] {"1", ""};
        String[] input5 = new String[] {"1", "", "2"};
        String[] input6 = new String[] {"1", "2", "3"};

        String[] output1 = new String[] {};
        String[] output2 = new String[] {"1"};
        String[] output3 = new String[] {"1", "2"};
        String[] output4 = new String[] {"1", "2", "3"};

        assertArrayEquals(ChangeDirectory.removeHolesLst(input1), output1);
        assertArrayEquals(ChangeDirectory.removeHolesLst(input2), output1);
        assertArrayEquals(ChangeDirectory.removeHolesLst(input3), output2);
        assertArrayEquals(ChangeDirectory.removeHolesLst(input4), output2);
        assertArrayEquals(ChangeDirectory.removeHolesLst(input5), output3);
        assertArrayEquals(ChangeDirectory.removeHolesLst(input6), output4);
    }

    @Test
    void test_isAlphaNumeric() {
        assertFalse(ChangeDirectory.isAlphaNumeric(""));
        assertFalse(ChangeDirectory.isAlphaNumeric("_"));

        assertTrue(ChangeDirectory.isAlphaNumeric("a"));
        assertTrue(ChangeDirectory.isAlphaNumeric("1"));
        assertTrue(ChangeDirectory.isAlphaNumeric("abc"));
        assertTrue(ChangeDirectory.isAlphaNumeric("123"));
        assertTrue(ChangeDirectory.isAlphaNumeric("12ab"));
    }
}
