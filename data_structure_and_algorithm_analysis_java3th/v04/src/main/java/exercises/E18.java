package exercises;

public class E18 {
    static int recursiveSolution(int h) {
        if (h < 0)
            return 0;
        if (h == 0)
            return 1;
        else if (h == 1)
            return 2;
        else
            return recursiveSolution(h - 1) + recursiveSolution(h - 2) + 1;

    }

    static int analyticalSolution(int h) {
        double sqrt5 = Math.sqrt(5);
        return (int)((Math.pow((1 + sqrt5) / 2, h + 3) - Math.pow((1 - sqrt5) / 2, h + 3))/sqrt5 - 1);
    }
}
