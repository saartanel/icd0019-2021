package oo.hide;

public class PointSet {

    private Point[] points;

    public PointSet(int capacity) {
        points = new Point[capacity];
    }

    public PointSet() {
        this(10);
    }

    public void add(Point point) {
        if (!contains(point)) {
            if (size()==points.length) {
                Point[] tempset = new Point[points.length*2];
                int count = 0;
                for (Point p:points) {
                    tempset[count] = p;
                    count++;
                }
                points = tempset;
            }
            points[size()] = point;
        }
    }

    public int size() {
        int size = 0;
        for (Point p: points) {
            if (p != null) {
                size++;
            }
        }
        return size;
    }

    public boolean contains(Point point) {
        for (Point p:points) {
            if (point.equals(p)) {
                return true;
            }
        }
        return false;
    }

    public PointSet subtract(PointSet other) {
        PointSet output = new PointSet();
        for (Point p1 : points) {
            if (p1 == null){
                continue;
            }
            for (Point p2 : other.points) {
                if (p1.equals(p2)) {
                    break;
                }
                output.add(p1);
            }
        }
        return output;
    }

    public PointSet intersect(PointSet other) {
        PointSet output = new PointSet();
        for (Point p1 : points) {
            if (p1 == null){
                continue;
            }
            for (Point p2 : other.points) {
                if (!p1.equals(p2)) {
                    break;
                }
                output.add(p1);
            }
        }
        return output;
    }

    @Override
    public String toString(){
        String output = "";
        int count = 0;
        for (Point p:points) {
            if (p != null) {
                String tempout = p.toString();
                if (count != size()-1) {
                    tempout += ", ";
                }
                output += tempout;
                count++;
            }
        }
        return output;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof PointSet && (((PointSet) obj).size()) == size())) {
            return false;
        }
        PointSet other = (PointSet) obj;

        if (size() == 0 && other.size() == 0){
            return true;
        }

        boolean ifFound = false;

        for (Point p:other.points) {
            for (Point i:points) {
                if (i != null && i.equals(p)) {
                    ifFound = true;
                    break;
                }
            }
            if (!ifFound) {
                return false;
            }
        }
        return true;
    }
}
