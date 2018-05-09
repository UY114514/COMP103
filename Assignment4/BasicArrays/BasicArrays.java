import ecs100.*;


import javax.swing.*;
import java.awt.*;

public class BasicArrays {
    JButton verticalLineButton;

    public BasicArrays() {
        UI.initialise();
        verticalLineButton = UI.addButton("Vertical Line", this::verticalLine);

    }

    private Color getColor(int number_of_Items, int index) {
        return new Color(index * 255 / number_of_Items);
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
        */
        for (int i = 0; i < col; i++) {
//            verticalArray1[i] =
        }


    }

    public static void main(String[] arguments) {
        new BasicArrays();
    }
}