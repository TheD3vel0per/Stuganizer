package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ErrandListTest {

    private ErrandList errandList;
    private Random random;
    private int maxListSize;

    @BeforeEach()
    public void setup() {
        this.errandList = new ErrandList();
        this.random = new Random();
        this.maxListSize = 3;
    }

    public ArrayList<Errand> addErrandToList(int quantity) {
        ArrayList<Errand> errands = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Errand errandToAdd = new Errand("Test Errand #" + i);
            this.errandList.add(errandToAdd);
            errands.add(errandToAdd);
        }
        return errands;
    }

    @Test()
    public void testEmptyList() {
        assertEquals(0, this.errandList.size());
        assertEquals(-1, this.errandList.maxIndex());
        assertTrue(this.errandList.isEmpty());
    }

    @Test()
    public void testFilledErrandList() {
        int errandListSize = random.nextInt(maxListSize - 1) + 1;
        ArrayList<Errand> errands = this.addErrandToList(errandListSize);
        assertEquals(errands.size(), this.errandList.size());
        for (int i = 0; i <= this.errandList.maxIndex(); i++) {
            assertEquals(errands.get(i), this.errandList.get(i));
        }
    }

    @Test()
    public void testGetNext() {
        Errand next = new Errand();
        next.setTitle("Next Errand");
        next.setCompleteByDate(LocalDate.now());
        next.setPoints(Task.MAX_POINTS);

        Errand another = new Errand("Another Errand");

        this.errandList.add(Errand.NULL_ERRAND);
        this.errandList.add(another);
        this.errandList.add(next);

        assertEquals(2, this.errandList.maxIndex());
        assertEquals(2, this.errandList.size());
        assertEquals(next, this.errandList.getNext());
    }

    @Test()
    public void testRemoving() {
        this.addErrandToList(this.maxListSize);

        assertEquals(this.maxListSize, this.errandList.size());
        assertEquals(this.maxListSize - 1, this.errandList.maxIndex());

        int indexToRemove = this.random.nextInt(this.maxListSize);
        this.errandList.remove(indexToRemove);
        assertEquals(Errand.NULL_ERRAND, this.errandList.get(indexToRemove));
        assertEquals(this.maxListSize - 1, this.errandList.size());
        assertEquals(this.maxListSize - 1, this.errandList.maxIndex());

    }

    @Test()
    public void testGetOutOfRange() {
        assertNull(this.errandList.get(this.errandList.maxIndex() + 1));
    }
}
