import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.colors.XChartSeriesColors;

import javax.swing.*;
import java.awt.*;

public class InventoryChart extends JFrame {
    private String title;
    private int[] stats;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public int[] getStats() {
        return stats;
    }

    public void displayGraph() {
        SwingUtilities.invokeLater(() -> {
            CategoryChart chart = new CategoryChartBuilder()
                    .xAxisTitle("Book Category")
                    .yAxisTitle("Number of Available Books")
                    .theme(ChartTheme.Matlab)
                    .build();
            chart.setTitle(title);

            chart.getStyler().setLegendVisible(false);
            chart.getStyler().setSeriesColors(new XChartSeriesColors().getSeriesColors());
            //((BarChartStyler) chart.getStyler()).setBarWidthPercentage(0.5);
            //((BarChartStyler) chart.getStyler()).setPlotGridVerticalLinesVisible(false);

            chart.addSeries("Fiction", new double[]{0}, new double[]{stats[0]}).setChartCategorySeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Bar);
            chart.addSeries("Non-Fiction", new double[]{1}, new double[]{stats[1]}).setChartCategorySeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Bar);

            JPanel chartPanel = new XChartPanel<>(chart);
            getContentPane().add(chartPanel, BorderLayout.CENTER);

            setSize(400, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }

    public static void main(String[] args) {
        // default
        InventoryChart chart = new InventoryChart();
        chart.setTitle("Book Inventory");
        chart.displayGraph();
    }
}
