package pl.put.poznan.transformer.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextTransformerTest {

    TextTransformer t;

    @Before
    public void setUp() throws Exception {
        String[] tmp = new String[2];
        tmp[0] = "a";
        tmp[1] = "b";

        t = new TextTransformer(tmp);
    }

    @Test
    public void transform_test1() {
        assertEquals("WUT?", t.transform("wut?"));
    }

    @Test
    public void transform_test2() {
        assertEquals("WAAAAAAAAAAAAAAA", t.transform("WAAAAAAAAAAAAAAA"));
    }

    @Test
    public void transform_test3() {
        assertEquals("WQWQRTYU", t.transform("wQwQrTyU"));
    }
}