package edu.usfca.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**Deep Mistry**/
class songIntervalTest {

    songInterval si1;
    @BeforeEach
    void time(){

        si1 = new songInterval(184);
    }

    @Test
    void idTest(){
        System.out.println(si1.toString() );
    }

}