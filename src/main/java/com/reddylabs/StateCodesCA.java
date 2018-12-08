package com.reddylabs;

import java.util.Arrays;

public class StateCodesCA {
    public static final int[] npa_array = {

            209,
            213,
            310,
            323,
            408,
            415,
            510,
            530,
            559,
            562,
            619,
            626,
            650,
            661,
            707,
            714,
            760,
            805,
            818,
            831,
            858,
            909,
            916,
            925,
            949
//            ,
//            951
    };

    /**
     * Return sorted array
     * @return
     */
    public static int[] getNpa_array(){
    Arrays.sort(npa_array);
    return npa_array;
    }
}
