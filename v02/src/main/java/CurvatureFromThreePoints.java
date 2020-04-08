import java.awt.Color;
        import java.awt.Graphics;
        import java.awt.Graphics2D;
        import java.awt.event.MouseEvent;
        import java.awt.event.MouseListener;
        import java.awt.event.MouseMotionListener;
        import java.awt.geom.Ellipse2D;
        import java.awt.geom.Point2D;
        import java.util.ArrayList;
        import java.util.List;

        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.SwingUtilities;

public class CurvatureFromThreePoints
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI()
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new CurvatureFromThreePointsPanel());
        f.setSize(800,800);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}

class CurvatureFromThreePointsPanel extends JPanel
        implements MouseListener, MouseMotionListener
{
    private final List<Point2D> pointList;
    private Point2D draggedPoint;

    public CurvatureFromThreePointsPanel()
    {
        this.pointList = new ArrayList<Point2D>();

        pointList.add(new Point2D.Double(132,532));
        pointList.add(new Point2D.Double(275,258));
        pointList.add(new Point2D.Double(395,267));

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private static double computeCurvature(Point2D p0, Point2D p1, Point2D p2)
    {
        double dx1 = p1.getX() - p0.getX();
        double dy1 = p1.getY() - p0.getY();
        double dx2 = p2.getX() - p0.getX();
        double dy2 = p2.getY() - p0.getY();
        double area = dx1 * dy2 - dy1 * dx2;
        double len0 = p0.distance(p1);
        double len1 = p1.distance(p2);
        double len2 = p2.distance(p0);
        return 4 * area / (len0 * len1 * len2);
    }

    // Adapted from https://stackoverflow.com/a/4103418
    private static Point2D computeCircleCenter(
            Point2D p0, Point2D p1, Point2D p2)
    {
        double x0 = p0.getX();
        double y0 = p0.getY();
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        double offset = x1 * x1 + y1 * y1;
        double bc = (x0 * x0 + y0 * y0 - offset) / 2.0;
        double cd = (offset - x2 * x2 - y2 * y2) / 2.0;
        double det = (x0 - x1) * (y1 - y2) - (x1 - x2) * (y0 - y1);
        double invDet = 1 / det;
        double cx = (bc * (y1 - y2) - cd * (y0 - y1)) * invDet;
        double cy = (cd * (x0 - x1) - bc * (x1 - x2)) * invDet;
        return new Point2D.Double(cx, cy);
    }

    @Override
    protected void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;

        g.setColor(Color.RED);
        for (Point2D p : pointList)
        {
            double r = 5;
            g.draw(new Ellipse2D.Double(p.getX()-r, p.getY()-r, r+r, r+r));
        }

        g.setColor(Color.BLACK);
        //g.draw(Paths.fromPoints(spline.getInterpolatedPoints(), false));

        Point2D p0 = pointList.get(0);
        Point2D p1 = pointList.get(1);
        Point2D p2 = pointList.get(2);
        double curvature = computeCurvature(p0, p1, p2);
        g.drawString("Curvature: "+curvature, 10,  20);

        Point2D center = computeCircleCenter(p0, p1, p2);
        double radius = center.distance(p0);
        g.draw(new Ellipse2D.Double(
                center.getX() - radius, center.getY() - radius,
                radius + radius, radius + radius));
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (draggedPoint != null)
        {
            draggedPoint.setLocation(e.getX(), e.getY());
            repaint();

            System.out.println("Points: ");
            for (Point2D p : pointList)
            {
                System.out.println("    "+p);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e)
    {
        final double thresholdSquared = 10 * 10;
        Point2D p = e.getPoint();
        Point2D closestPoint = null;
        double minDistanceSquared = Double.MAX_VALUE;
        for (Point2D point : pointList)
        {
            double dd = point.distanceSq(p);
            if (dd < thresholdSquared && dd < minDistanceSquared)
            {
                minDistanceSquared = dd;
                closestPoint = point;
            }
        }
        draggedPoint = closestPoint;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        draggedPoint = null;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        // Nothing to do here
    }


    @Override
    public void mouseClicked(MouseEvent e)
    {
        // Nothing to do here
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        // Nothing to do here
    }


    @Override
    public void mouseExited(MouseEvent e)
    {
        // Nothing to do here
    }


}
