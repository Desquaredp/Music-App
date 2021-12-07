package edu.usfca.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**Deep Mistry**/
class ArtistTest {

    Artist a1,a2;
    @BeforeEach
    void setup() {

        a1 = new Artist("bruce");
        a2 = new Artist("john");

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