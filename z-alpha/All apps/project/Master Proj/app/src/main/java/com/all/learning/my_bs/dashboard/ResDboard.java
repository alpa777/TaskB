package com.all.learning.my_bs.dashboard;

import java.util.List;

/**
 * Created by root on 9/11/17.
 */

public class ResDboard {
    public App app;

    public class App {
        public Dashboard dashboard;
    }

    public class Dashboard {
        public String title;
        public boolean adshow;
        public List<Menus> menus;
    }

    public class Menus {
        public String text;
        public String icon;
        public String content_type;
    }

}
