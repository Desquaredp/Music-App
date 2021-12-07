package edu.usfca.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**Deep Mistry**/
class AlbumTest {
    Album a1, a2;

    @BeforeEach
    void setup() {

        a1 = new Album("album1", "artist1");
        a2 = new Album("album2", "artist2");

    }

    @Test
    void testEquals() {
        Assertions.assertEquals(a1.equals(a2), false);
    }

    @Test
    void toString_toHTML_toXML_test() {
        System.out.println(a1.toString());
        System.out.println(a1.toHTML());
        System.out.println(a1.toXML());
    }

}