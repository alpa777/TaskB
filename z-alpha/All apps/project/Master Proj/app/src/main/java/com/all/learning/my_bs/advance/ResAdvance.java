package com.all.learning.my_bs.advance;

import java.util.List;

/**
 * Created by root on 9/11/17.
 */

public class ResAdvance {
    public A2 a1;

    public class A2 {
        public BS b;
    }

    public class BS {
        public String title;
        public List<ITEMS> itmes;
    }

    public class ITEMS {
        public String id;
        public String text;
        public String icon;
        public String other;
    }
}
