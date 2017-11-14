package com.all.learning.my_bs.dashboard;

import java.util.List;

/**
 * Created by root on 9/11/17.
 */

public class ResAll {
    public ClA A;

    public class ClA {
        public  List<ClAChartItem> chart_list = null;

        public  List<ClAItem> items = null;
        public String title;
        public boolean adshow;

    }

    public class ClAChartItem{
        public  String value;
        public  String text;
    }

    public class ClB {
        public String title;
        public List<ClBItem> items;
    }

    public class ClC {
        public String title;
    }

    public class ClAItem {
        public String id;
        public String text;
        public String icon;
        public ClB B;
    }

    public class ClBItem {
        public String id;
        public String text;
        public String icon;
        public ClC C;
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
