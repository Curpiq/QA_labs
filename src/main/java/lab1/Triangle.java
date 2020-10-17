package lab1;

public class Triangle {
    static String GetTriangleType(double[] args)
    {
        if (args[0] == args[1] && args[1] == args[2]) { return "Equilateral"; }
        else if (args[0] == args[1] || args[1] == args[2] || args[0] == args[2]) { return "Isosceles"; }
        else { return "Regular"; }
    }
    static boolean IsTriangle(double[] args)
    {
        return args[0] + args[1] > args[2] && args[1] + args[2] > args[0] && args[0] + args[2] > args[1];
    }
    static boolean CheckArgs(String[] args)
    {
        try
        {
            Double side1 = Double.parseDouble(args[0]);
            Double side2 = Double.parseDouble(args[1]);
            Double side3 = Double.parseDouble(args[2]);
            if (side1.isInfinite() || side2.isInfinite() || side3.isInfinite())
            {
                return false;
            }
        }
        catch (NumberFormatException err)
        {
            return false;
        }
        return true;
    }

    static boolean IsCorrectArgs(String[] args)
    {
        if (args.length != 3)
        {
            return false;
        }
        return CheckArgs(args);
    }

    static String CheckTriangle(String[] args)
    {
        if (IsCorrectArgs(args))
        {
            double[] doubleArr = new double[3];
            doubleArr[0] = Double.parseDouble(args[0]);
            doubleArr[1] = Double.parseDouble(args[1]);
            doubleArr[2] = Double.parseDouble(args[2]);
            if (IsTriangle(doubleArr))
            {
                return GetTriangleType(doubleArr);
            }
            else { return "Not a triangle"; }
        }
        else { return "Unknown error"; }
    }

    public static void main(String[] args)
    {
        System.out.println(CheckTriangle(args));
    }

}
