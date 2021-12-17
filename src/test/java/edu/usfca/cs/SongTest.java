//package edu.usfca.cs;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
///**
// * Deep Mistry
// **/
//class SongTest {
//    Song s1, s2, s3, s4;
//
//    @BeforeEach
//    void setUp() {
//        s1 = new Song("Happ,y birthday", 184, "dsss", "batman", true);
//        s2 = new Song("Hey Jude", 121, "fadfadf", "bruce wayne", false);
//        s3 = new Song("Happy birthda,y", 184, "dsss", "batman", true);
//        s4 = new Song("Happy birth,day", 184, "dsss", "batman", true);
//
//
//    }
//
//    @Test
//    void idTest() {
//        System.out.println(s1.getName().replaceAll("\\p{Punct}", ""));
//        System.out.println(s3.getName());
//        assertEquals(s1.getName().replaceAll("\\p{Punct}", ""), s3.getName().replaceAll("\\p{Punct}", ""));
//
//    }
//
//
//    @Test
//    void testEquals() {
//
//        Assertions.assertEquals(s1.equals(s3), true);
//    }
//
//
//
//}