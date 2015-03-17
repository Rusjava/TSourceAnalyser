/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsourceanalyser;

import java.util.ArrayList;

/**
 *
 * @author Ruslan Feshchenko
 * @version 0.1
 */
public class ChartParam {
    public final String key;
    public final double [] lineData;
    public final double [] barData;
    public final double step;
    public final double offset;
    public final int size;
    
    /**
     * Constructor
     * @param key chart key
     * @param step plot step
     * @param size plot size
     * @param offset plot offset
     */
    public ChartParam (String key, double step, int size, double offset) {
        super();
        this.key=key;
        this.size=size;
        this.step=step;
        this.offset=offset;
        lineData=new double [size];
        barData=new double [size];
    }
}
