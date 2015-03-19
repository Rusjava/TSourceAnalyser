/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsourceanalyser;

/**
 *
 * @author Ruslan Feshchenko
 * @version 1.0
 */
public class ChartParam {

    public final String key;
    public final double[] data;
    public final double step;
    public final double offset;
    public final int size;
    private double meanValue;
    private double meanDeviation;
    private int nel = 0;

    /**
     * Constructor
     *
     * @param key chart key
     * @param step plot step
     * @param size plot size
     * @param offset plot offset
     */
    public ChartParam(String key, double step, int size, double offset) {
        super();
        this.key = key;
        this.size = size;
        this.step = step;
        this.offset = offset;
        this.data = new double[size];
    }

    /**
     * Adding data for one electron
     *
     * @param electron
     */
    public void add(double electron) {
        int index = (int) Math.round((electron - offset) / step);
        if (index >= 0 && index < size) {
            data[index]++;
        }
        nel++;
        meanValue += electron;
        meanDeviation += electron * electron;
    }

    /**
     * Returns mean value
     * @return
     */
    public double getMeanValue() {
        return meanValue / nel;
    }
    
    /**
     * Returns mean deviation
     * @return
     */
    public double getMeanDeviation() {
        return Math.sqrt((meanDeviation - meanValue * meanValue /nel) / nel);
    }
}
