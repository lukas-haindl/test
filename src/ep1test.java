public class ep1test {
    public static void main(String[] args) {
        int n = 3;
        int stars = 0;
        int route = 0;
        int plus = 0;
        for (int i = -n; i < n; i++) {           // Zeile A
            for (int j = -n; j < n; j++) {
                if (i * j < n) {                 // Zeile B
                    stars ++;
                } else if (j * j < n) {
                    route++;
                } else {
                    plus++;
                }
            }
            //System.out.println();
        }
        System.out.println("Stars: " + stars + " Route: " + route + " Plus: " + plus);

    }
}
