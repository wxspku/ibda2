package net.ibda.dataanalysis.visualization;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SineGraph extends JFrame {
    private static final long serialVersionUID = 1L;

    public SineGraph() throws FileNotFoundException, IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 1000);
        DataTable data = new DataTable(Double.class, Double.class);
        for (double x = -5.0; x <= 5.0; x += 0.10) {
            double y = 4.0 * Math.sin(x);
            data.add(x, y);
        }
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);

        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getLineRenderers(data).get(0).setColor(color);
    }

    public static void main(String[] args) {
        SineGraph frame = null;
        try {
            frame = new SineGraph();
        } catch (IOException e) {
        }
        frame.setVisible(true);
    }
}

