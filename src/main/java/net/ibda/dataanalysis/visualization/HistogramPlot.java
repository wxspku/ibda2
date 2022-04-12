package net.ibda.dataanalysis.visualization;

import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.data.EnumeratedData;
import de.erichseifert.gral.data.statistics.Histogram1D;
import de.erichseifert.gral.data.statistics.Statistics;
import de.erichseifert.gral.examples.ExamplePanel;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Orientation;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.GraphicsUtils;
import de.erichseifert.gral.util.MathUtils;

import java.util.Random;

public class HistogramPlot extends ExamplePanel {
    /**
     * 序列化版本id
     */
    private static final long serialVersionUID = 4458280577519421950L;
    private static final int SAMPLE_COUNT = 1000;

    //@SuppressWarnings("unchecked")
    public HistogramPlot() {
        //生成示例数据
        Random random = new Random();
        DataTable data = new DataTable(Double.class);
        for (int i = 0; i < SAMPLE_COUNT; i++) {
            data.add(random.nextGaussian());
        }
        //根据数据生成直方图
        Histogram1D histogram = new Histogram1D(data,
                Orientation.VERTICAL, new Number[]{-4.0, -3.2, -2.4, -1.6,
                -0.8, 0.0, 0.8, 1.6, 2.4, 3.2, 4.0});
        //为图形创建另一个维度（x轴）
        DataSource histogram2d = new EnumeratedData(histogram, (-4.0 +
                -3.2) / 2.0, 0.8);
        //新建条形图
        BarPlot plot = new BarPlot(histogram2d);
        //设置图形格式
        plot.setInsets(new Insets2D.Double(20.0, 65.0, 50.0, 40.0));
        plot.getTitle().setText(
                String.format("Distribution of %d random samples",
                        data.getRowCount()));
        plot.setBarWidth(0.78);
        //设置x轴格式
        plot.getAxisRenderer(BarPlot.AXIS_X).setTickAlignment(0.0);
        plot.getAxisRenderer(BarPlot.AXIS_X).setTickSpacing(0.8);
        plot.getAxisRenderer(BarPlot.AXIS_X).setMinorTicksVisible(false);
        //设置y轴格式
        plot.getAxis(BarPlot.AXIS_Y).setRange(0.0,
                MathUtils.ceil(histogram.getStatistics().
                        get(Statistics.MAX) * 1.1, 25.0));
        plot.getAxisRenderer(BarPlot.AXIS_Y).setTickAlignment(0.0);
        plot.getAxisRenderer(BarPlot.AXIS_Y).setMinorTicksVisible(false);
        plot.getAxisRenderer(BarPlot.AXIS_Y).setIntersection(-4.4);
        //设置长条格式
        plot.getPointRenderers(histogram2d).get(0).setColor(
                GraphicsUtils.deriveWithAlpha(COLOR1, 128));
        plot.getPointRenderers(histogram2d).get(0).setValueVisible(true);
        //把图形添加到Swing组件
        InteractivePanel panel = new InteractivePanel(plot);
        panel.setPannable(false);
        panel.setZoomable(false);
        add(panel);
    }

    @Override
    public String getTitle() {
        return "Histogram plot";
    }

    @Override
    public String getDescription() {
        return String.format("Histogram of %d samples", SAMPLE_COUNT);
    }

    public static void main(String[] args) {
        new HistogramPlot().showInFrame();
    }
}

