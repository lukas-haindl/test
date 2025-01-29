import codedraw.CodeDraw;
import codedraw.Palette;

public class Squares {

    public static void main(String[] args) {
        int width = 400;
        CodeDraw myDrawObj = new CodeDraw(width, width);
        int n = 8;
        int d = 4;
        double squareSize = width / (double)n;
        double x = 0;
        double y = 0;
        for (int j = d; j >= -d; j--) {
            for (int i = 0; i < n; i++) {
                if (j % 2 != 0) {
                    myDrawObj.setColor(Palette.BLACK);
                }
                else{
                    myDrawObj.setColor(Palette.DODGER_BLUE);
                }
                myDrawObj.fillSquare(x + squareSize * j, y, squareSize);
                x += squareSize;
                y += squareSize;
            }
            x = 0;
            y = 0;
        }

        myDrawObj.show();
    }
}
