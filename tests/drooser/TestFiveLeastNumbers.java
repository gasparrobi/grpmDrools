package drooser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestFiveLeastNumbers {

    public LinkedHashMap<Integer, Integer> numbers = new LinkedHashMap<>();
    private ArrayList<Integer> result;
    private FindRearestNumbers finder;


    @BeforeEach
    public void setup() {
        this.finder = new FindRearestNumbers();
        this.numbers.put(1,3);
        this.numbers.put(2,2);
        this.numbers.put(3,1);
    }

    @Test
    @DisplayName("Test if order sorted")
    public void testOrderSortedByValue() {
        this.result = this.finder.findRearestNumbers(this.numbers);
        assertEquals(new ArrayList<>(Arrays.asList(1,2,3,4,5)), this.result);
    }

    @Test
    @DisplayName("Test HashMap Size")
    public void testHashMapSize() {
        int numberCount = this.finder.getAllNumbers().size();
        assertEquals(90, numberCount);
    }

}
