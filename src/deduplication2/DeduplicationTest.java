package deduplication2;

import org.junit.jupiter.api.BeforeAll;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class DeduplicationTest {

    private Deduplication dd;
    Map<Integer, String> map;
    Set<String> set;

    @BeforeAll
    public void init() {
        dd = new Deduplication();
        map = dd.getDictionary();
        set = dd.getWords();
    }

    @org.junit.Test
    public void getWords1() {
        dd.addSentence("Ala ma kota");
        dd.addSentence("Grzegorz ma kota");
        dd.addSentence("Pawel ma lisa");
        dd.addSentence("Pawel ma lisa?");
        dd.addSentence("Grzegorz biegnie");
        dd.addSentence("Ala idzie do pracy");
        assertEquals(1, set.size());
        assertTrue(set.contains(" "));
    }

    @org.junit.Test
    public void getWords2() {
        dd.addSentence("Ala ma kota");
        dd.addSentence("Grzegorz ma kota");
        dd.addSentence("Pawel ma lisa");
        dd.addSentence("Pawel ma lisa?");
        dd.addSentence("Grzegorz biegnie");
        dd.addSentence("Ala idzie do pracy");

        dd.removeSentence("Ala ma kota");
        dd.removeSentence("Grzegorz ma kota");
        dd.removeSentence("Pawel ma lisa");
        dd.removeSentence("Pawel ma lisa?");
        dd.removeSentence("Grzegorz biegnie");
        dd.removeSentence("Ala idzie do pracy");

        assertEquals(map.size(), set.size());
        assertTrue(set.contains(" "));
        assertTrue(map.containsValue(" "));
    }
    @org.junit.Test
    public void getWords3() {
        dd.addSentence("Ala ma kota");
        dd.addSentence("Pawel ma psa");
        dd.addSentence("Grzegorz, ma? lisa");
        dd.addSentence("Anna,ma ! psa");

        assertTrue(set.contains("ma"));
    }

    @org.junit.Test
    public void getWords4() {
        dd.addSentence("Ole ole,ole ole");

        assertEquals(1, set.size());
        assertTrue(map.containsValue("ole"));
    }

    @org.junit.Test
    public void getWords5() {
        dd.addSentence("Ala ma? kota");
        dd.addSentence("Pawel ma psa");
        dd.addSentence("Grzegorz ma? lisa");
        dd.addSentence("Anna? ma? psa");

        assertEquals(3, set.size());
        assertTrue(map.containsValue(" "));
        assertTrue(map.containsValue("ma"));
        assertTrue(map.containsValue("?"));
    }

    @org.junit.Test
    public void getWords6() {
        dd.addSentence("Ala ma? kota");
        dd.addSentence("Pawel, ma kota");
        dd.addSentence("Grzegorz, ma kota");
        dd.addSentence("Grzegorz biegnie");
        dd.addSentence("Anna! biegnie");
        dd.addSentence("Anna? ma kota");

        assertTrue(set.contains("ma kota"));

        dd.removeSentence("Pawel, ma kota");
        dd.removeSentence("Anna? ma kota");

        dd.addSentence("Anna? biegnie");
        dd.addSentence("Anna biegnie");
        dd.addSentence("Grzegorz? biegnie");

        dd.removeSentence("Ala ma? kota");
        dd.removeSentence("Grzegorz, ma kota");

        assertFalse(map.containsValue("ma kota"));
        assertTrue(set.contains("biegnie"));
        assertTrue(set.contains("ma kota"));
    }

}