/*
 * Analyses the Shvedunov electron data
 */
package tsourceanalyser;

import ElectronBunchRead.ElectronBunchRead;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.Locale;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 *
 * @author Ruslan Feshchenko
 * @version 0.1
 */
public class TSourceAnalyserJFrame extends javax.swing.JFrame {

    private int columnChoice = 0;
    private boolean working = false;
    private int nel;
    private String[] labelUnits ;
    private String [] keys;
    private int size=200;
    private double [] minX, maxX;
    private ChartParam [] chartParam;
    private JFreeChart[] charts;
    private ChartPanel chartPanel=null;
    private double mult=1.6;

    /**
     * Creates new form TSourceAnalyserJFrame
     */
    public TSourceAnalyserJFrame() {
        initComponents();
        this.minX=new double[] {-0.04, -3, -0.04, -3, -8, -500};
        this.maxX=new double[] {0.04, 3, 0.04, 3, 8, 500};
        this.labelUnits = new String [] {"mm", "mrad", "mm", "mrad",
        "mm", "kev"};
        this.keys=new String [] {"x", "thetax", "y", "thetay", "z", "energy"};
        this.chartParam=new ChartParam[ElectronBunchRead.NCOL];
        for (int i=0; i<ElectronBunchRead.NCOL; i++) {
            chartParam[i]=
                    new ChartParam(keys[i], (maxX[i]-minX[i])/(size-1), size, minX[i]);
        }
        this.charts=new JFreeChart [ElectronBunchRead.NCOL];
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabelMean = new javax.swing.JLabel();
        jLabelMeanMDeviation = new javax.swing.JLabel();
        jLabelElectronCount = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuOptions = new javax.swing.JMenu();
        jMenuItemRanges = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Inputs", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jButton1.setText("Open file");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Plot", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Results:", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabelMean.setText("Mean:");

        jLabelMeanMDeviation.setText("Mean  deviation:");

        jLabelElectronCount.setText("Number of loaded electrons:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelElectronCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelMean, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelMeanMDeviation, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMean)
                    .addComponent(jLabelMeanMDeviation))
                .addGap(18, 18, 18)
                .addComponent(jLabelElectronCount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuOptions.setText("Options");

        jMenuItemRanges.setText("X-axis ranges...");
        jMenuItemRanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRangesActionPerformed(evt);
            }
        });
        jMenuOptions.add(jMenuItemRanges);

        jMenuBar1.add(jMenuOptions);

        jMenuHelp.setText("Help");
        jMenuHelp.setToolTipText("");

        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        double[] electron = new double[ElectronBunchRead.NCOL];
        try (ElectronBunchRead electronBunchRead = new ElectronBunchRead()) {
            do {
                electronBunchRead.read(electron);
                nel = electronBunchRead.getElectronCounter();
                jLabelElectronCount.setText("Number of loaded electrons: "+nel);
                for (int i = 0; i < ElectronBunchRead.NCOL; i++) {
                    int index=(int)Math.round((electron[i]-minX[i])/chartParam[i].step);
                    if (index >= 0 && index < size) {
                        chartParam[i].data[index]+=1;
                    }
                    chartParam[i].meanValue += electron[i];
                    chartParam[i].meanDeviation += electron[i] * electron[i];
                } 
            } while (true);
        } catch (EOFException e) {
            for (int i = 0; i < ElectronBunchRead.NCOL; i++) {
                chartParam[i].meanValue/= nel;
                chartParam[i].meanDeviation = Math.sqrt(chartParam[i].meanDeviation/nel-chartParam[i].meanValue*chartParam[i].meanValue);
                charts[i]=createLineChart(createLineDataset(chartParam[i]), keys[i]+", "+labelUnits[i], "a.u.");
            }
            updateLabels();
            updateChartPanel();   
        } catch (InputMismatchException ex) {
            JOptionPane.showMessageDialog(null, "Not a real number!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "I/O error during file reading!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {

        } catch (InvocationTargetException ex) {

        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "The data have less than six columns!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ElectronBunchRead.FileNotOpenedException ex) {
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String selectedItem = (String) jComboBox1.getSelectedItem();
        columnChoice = Integer.parseInt(selectedItem.substring(selectedItem.length() - 1))-1;
        updateLabels();
        updateChartPanel(); 
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jMenuItemRangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRangesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemRangesActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    /*
    * Generates line charts
    */
    private JFreeChart createLineChart(XYDataset dataset, String xlabel, String ylabel) {
        /* X axis */
        NumberAxis xAxis = new NumberAxis(xlabel);
        xAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        xAxis.setAutoRangeIncludesZero(false);
        /* Y axis */
        NumberAxis yAxis = new NumberAxis(ylabel);
        yAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        yAxis.setLowerMargin(0.0);
        yAxis.setUpperMargin(0.0);
        yAxis.setAutoRangeIncludesZero(false);
        /* Renderer */
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesLinesVisible(2, true);
        renderer.setSeriesShapesVisible(2, false);
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesPaint(2, Color.MAGENTA);
        /* Plot creation */
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.black);
        plot.setDomainGridlinePaint(Color.black);
        /* Chart creation */
        JFreeChart chart = new JFreeChart(plot);
        chart.removeLegend();
        chart.setBackgroundPaint(Color.white);
        return chart;
    } 
    
    /*
    * Generates XY datasets for charts
    */
    private XYDataset createLineDataset(final ChartParam data) {
        return new XYDataset() {
            public int getSeriesCount() {
                return 3;
            }
            public int getItemCount(int series) {
                return data.size;
            }
            public Number getY(int series, int item) {
                return new Double(getYValue(series, item));
            }
            public double getXValue(int series, int item) {
                return item*data.step+data.offset;
            }
            public Number getX(int series, int item) {
                return new Double(getXValue(series, item));
            }
            public double getYValue(int series, int item) {
                double dev=mult*data.meanDeviation;
                switch (series) {
                    case 0: return data.data[item];
                    case 1: return nel*data.step/Math.sqrt(2*Math.PI)/data.meanDeviation*
                            Math.exp(-Math.pow((item*data.step+data.offset-data.meanValue)/data.meanDeviation, 2)/2);
                    case 2: return nel*data.step/Math.sqrt(2*Math.PI)/dev*
                            Math.exp(-Math.pow((item*data.step+data.offset-data.meanValue)/dev, 2)/2);
                }
                return 0;
             }
            public void addChangeListener(DatasetChangeListener listener) {
            // ignore - this dataset never changes
            }
            public void removeChangeListener(DatasetChangeListener listener) {
                // ignore
            }
            public DatasetGroup getGroup() {
                return null;
            }
            public void setGroup(DatasetGroup group) {
                // ignore
            }
            public Comparable getSeriesKey(int series) {
                switch (series) {
                    case 0: return data.key+"0";
                    case 1: return data.key+"1";
                    case 2: return data.key+"2";
                }
                return data.key;
            }
            public int indexOf(Comparable seriesKey) {
                if (seriesKey.equals(data.key+"2")) {
                    return 2;
                } else if (seriesKey.equals(data.key+"1")) {
                     return 1;
                }
                return 0;
            }
            public DomainOrder getDomainOrder() {
                return DomainOrder.ASCENDING;
            }        
        };
    }
        
    /*
     * updates labels with mean values
     */
    private void updateLabels() {
        jLabelMean.setText("Mean: "
                + (new DecimalFormat("#.######")).format(chartParam[columnChoice].meanValue)
                + " " + labelUnits[columnChoice]);
        jLabelMeanMDeviation.setText("Mean deviation: "
                + (new DecimalFormat("#.######")).format(chartParam[columnChoice].meanDeviation)
                + " " + labelUnits[columnChoice]);
        ((TitledBorder)(jPanel3.getBorder())).setTitle("Results: "+keys[columnChoice]);
        jPanel3.revalidate();
        jPanel3.repaint(); 
    }
    
    /*
    * Updates the main Chart Panel
    */
    
    private void updateChartPanel () {
        if (chartPanel!=null) {
            jPanel2.removeAll();
        }
        chartPanel=new ChartPanel(charts[columnChoice], (int)(0.9*jPanel2.getWidth()), (int)(0.9*jPanel2.getHeight()),
            0, 0, 10*jPanel2.getWidth(), 10*jPanel2.getHeight(), false, true,
                    true, true, true, true);
        jPanel2.setLayout(new BorderLayout(10,10));
        jPanel2.add(chartPanel, BorderLayout.CENTER);     
        jPanel2.revalidate();
        jPanel2.repaint();   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TSourceAnalyserJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TSourceAnalyserJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TSourceAnalyserJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TSourceAnalyserJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Locale.setDefault(new Locale("en", "US"));
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TSourceAnalyserJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabelElectronCount;
    private javax.swing.JLabel jLabelMean;
    private javax.swing.JLabel jLabelMeanMDeviation;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemRanges;
    private javax.swing.JMenu jMenuOptions;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
