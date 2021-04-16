package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(5);
        assertEquals(5,arb.capacity());
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        int expected = 4;
        assertEquals(4, arb.fillCount());
        arb.enqueue(5);
        assertTrue(arb.isFull());
        assertEquals(1,(int)arb.dequeue());
        arb.enqueue(6);
        assertTrue(arb.isFull());

    }
}
