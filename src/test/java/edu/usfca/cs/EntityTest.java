package edu.usfca.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityTest {
    Entity c1,c2;

    @BeforeEach
    void setup() {
        c1 = new Entity();
        c2 = new Entity();

    }

    @Test
    void testId(){
        System.out.println(c1.entityID + " " +c2.entityID);
        System.out.println("the date is" + c1.dateCreated);
    }

    @Test
    void testEquals() {
        assertEquals(c1,c1);
        Assertions.assertEquals(c1.equals(c2), false);
    }

    @Test
    void stripPunct(){
        c1.stripPunct("wasn't");
    }
}