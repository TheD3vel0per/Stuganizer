package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ExaminableListTest {

    private ExaminableList examinableList;
    private Random random;
    private int maxListSize;

    @BeforeEach()
    public void setup() {
        this.examinableList = new ExaminableList();
        this.random = new Random();
        this.maxListSize = 3;
    }

    public ArrayList<Examinable> addExaminableToList(int quantity) {
        ArrayList<Examinable> examinables = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Examinable examinableToAdd = new Examinable("Test Examinable #" + i);
            this.examinableList.add(examinableToAdd);
            examinables.add(examinableToAdd);
        }
        return examinables;
    }

    @Test()
    public void testEmptyList() {
        assertEquals(0, this.examinableList.size());
        assertEquals(-1, this.examinableList.maxIndex());
        assertTrue(this.examinableList.isEmpty());
    }

    @Test()
    public void testFilledExaminableList() {
        int examinableListSize = random.nextInt(maxListSize - 1) + 1;
        ArrayList<Examinable> examinables = this.addExaminableToList(examinableListSize);
        assertEquals(examinables.size(), this.examinableList.size());
        for (int i = 0; i <= this.examinableList.maxIndex(); i++) {
            assertEquals(examinables.get(i), this.examinableList.get(i));
        }
    }

    @Test()
    public void testRemoving() {
        this.addExaminableToList(this.maxListSize);

        assertEquals(this.maxListSize, this.examinableList.size());
        assertEquals(this.maxListSize - 1, this.examinableList.maxIndex());

        int indexToRemove = this.random.nextInt(this.maxListSize);
        this.examinableList.remove(indexToRemove);
        assertEquals(Examinable.NULL_EXAMINABLE, this.examinableList.get(indexToRemove));
        assertEquals(this.maxListSize - 1, this.examinableList.size());
        assertEquals(this.maxListSize - 1, this.examinableList.maxIndex());

    }

    @Test()
    public void testGetOutOfRange() {
        assertNull(this.examinableList.get(this.examinableList.maxIndex() + 1));
    }

}
