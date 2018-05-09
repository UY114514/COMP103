import ecs100.*;


import javax.swing.*;
import java.awt.*;

public class BasicArrays {
    JButton verticalLineButton;
    public static Double TOP = 15.0;
    public static Double LEFT = TOP;
    public static Double BOX_SIZE = 50.0;
    public static Integer FONT_SIZE = 15;

    public BasicArrays() {
        UI.initialise();
        verticalLineButton = UI.addButton("Vertical Line", this::verticalLine);
        UI.addButton("Box", this::box);

    }

    private Color getColor(int number_of_Items, int index) {
        return new Color(index * 255 / number_of_Items);
    }

    public void draw2DArray(Color[][] array) {
        /*something wrong with the name of "cols" and "rows" here
        but I'm too lazy to change it
         * the cols actually is rows and so on
         */
        int count = 1;
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                UI.setColor(array[row][col]);
                UI.fillRect(LEFT + col * BOX_SIZE, TOP + row * BOX_SIZE, BOX_SIZE, BOX_SIZE);
                UI.setFontSize(FONT_SIZE);
//        draw No.
                UI.setColor(Color.WHITE);
                UI.drawString(String.valueOf(count), LEFT + (col) * BOX_SIZE + BOX_SIZE / 3, TOP + (row + 1) * BOX_SIZE - BOX_SIZE / 3);
                count++;
//                UI.sleep(100);

            }
        }
    }


    public void box() {

        int rows = UI.askInt("Row:");
        int cols = UI.askInt("Col:");
        Color[][] array = new Color[rows][cols];
        int index = 1;

        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                UI.println(row + "," + col);
                array[row][col] = this.getColor(cols * rows, index);
                UI.println("#" + index);
                index++;

                UI.setColor(array[row][col]);
//                UI.fillRect(LEFT,TOP,BOX_SIZE,BOX_SIZE);
            }

        }
        this.draw2DArray(array);

    }

    public void drawVerticalArray(Color[] verticalArray) {

        for (int i = 0; i < verticalArray.length; i++) {
//        draw blocks
            UI.setColor(verticalArray[i]);
            UI.fillRect(LEFT, TOP + i * BOX_SIZE, BOX_SIZE, BOX_SIZE);
            UI.setFontSize(FONT_SIZE);
            UI.setColor(Color.white);
//        draw No.
            UI.drawString(String.valueOf(verticalArray.length - i), LEFT + BOX_SIZE / 2 - FONT_SIZE / 3, TOP + (i + 1) * BOX_SIZE - BOX_SIZE / 2);
        }
    }


    public void verticalLine() {
        int col = UI.askInt("Col:");
        //        Integer col = Integer.valueOf(JOptionPane.showInputDialog("Fuckyou"));
        Color[] verticalArray1 = new Color[col];
        /*
        e.g. col = 10
        从上往下第10个最深
        从上往下第1个序号是10
        按颜色深度排序，下面的最深： for(int i;i<col;i++)
        getColor(col,i)，浅→深，编号时反顺序
        getColor最后一个最深色 第一个最浅
        参数：总数，第几个


        */
        for (int i = 0; i < col; i++) {
            verticalArray1[col - 1 - i] = getColor(col, i + 1);
        }
        this.drawVerticalArray(verticalArray1);
//        for (Color c:verticalArray1
//             ) {
//            UI.setColor(c);
//            UI.fillRect(0, 0, 50, 50);
//            UI.sleep(500);
//
//        }


    }

    public static void main(String[] arguments) {
        new BasicArrays();
    }
}