import ecs100.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BasicArrays {
    JButton verticalLineButton;
    public static Double TOP = 15.0;
    public static Double LEFT = TOP;
    public static Double BOX_SIZE = 50.0;
    public static Integer FONT_SIZE = 15;
    public JSlider sliderVertical = new JSlider(SwingConstants.VERTICAL, 0, 15, 1);
    public JSlider sliderHorizontal = new JSlider(SwingConstants.HORIZONTAL, 0, 20, 1);


    public BasicArrays() {
        UI.initialise();
        verticalLineButton = UI.addButton("Vertical Line", this::verticalLine);
        UI.addButton("Box", this::box);
        UI.addButton("Spiral", this::spiralArray);
        UI.addButton("Clear", UI::clearGraphics);
        UI.setDivider(0);


    }

    private Color getColor(int number_of_Items, int index) {
        return new Color(index * 255 / number_of_Items);
    }


    public void spiralArray() {
        /*
         * get some help from https://blog.csdn.net/mine_song/article/details/70212215
         * */
        int ask[] = askForColsAndRows();

        int rows = ask[0];
        int cols = ask[1];


        Color[][] colorArray = new Color[rows][cols];
        Integer[][] indexArray = new Integer[rows][cols];
        int index = 1;

        int layers = (int) Math.ceil((Math.min(rows, cols)) / 2.0);//layer counter: to mark which layer we're on
        for (int layer = 0; layer < layers; layer++) {
            for (int col = layer; col < cols - layer; col++) {
                indexArray[layer][col] = index;
                colorArray[layer][col] = this.getColor(rows * cols, index);
                index++;
            }
            for (int row = layer + 1; row < rows - layer; row++) {
                indexArray[row][cols - layer - 1] = index;
                colorArray[row][cols - layer - 1] = this.getColor(rows * cols, index);
                index++;
            }
            for (int col = cols - layer - 2; (col >= layer) && (rows - layer - 1 != layer); col--) {
                indexArray[rows - layer - 1][col] = index;
                colorArray[rows - layer - 1][col] = this.getColor(rows * cols, index);
                index++;
            }
            for (int row = rows - layer - 2; (row > layer) && (cols - layer - 1 != layer); row--) {
                indexArray[row][layer] = index;
                colorArray[row][layer] = this.getColor(rows * cols, index);
                index++;
            }
        }
        drawSpiralArray(colorArray, indexArray);
    }


    public void drawSpiralArray(Color[][] array, Integer[][] indexArray) {

//        int count = 1;
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                UI.setColor(array[row][col]);
                UI.fillRect(LEFT + col * BOX_SIZE, TOP + row * BOX_SIZE, BOX_SIZE, BOX_SIZE);
                UI.setFontSize(FONT_SIZE);
//        draw No.

                UI.setColor(Color.WHITE);
                UI.drawString(String.valueOf(indexArray[row][col]), LEFT + (col) * BOX_SIZE + BOX_SIZE / 3, TOP + (row + 1) * BOX_SIZE - BOX_SIZE / 3);


            }
        }
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
        int ask[] = askForColsAndRows();

        int rows = ask[0];
        int cols = ask[1];
        Color[][] array = new Color[rows][cols];
        int index = 1;

        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                array[row][col] = this.getColor(cols * rows, index);
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
        int col = askForCol();

        Color[] verticalArray1 = new Color[col];
        for (int i = 0; i < col; i++) {
            verticalArray1[col - 1 - i] = getColor(col, i + 1);
        }
        this.drawVerticalArray(verticalArray1);



    }

    public int askForCol() {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.X_AXIS));
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));


        sliderVertical.setMajorTickSpacing(5);
        sliderVertical.setMinorTickSpacing(1);
        sliderVertical.setPaintTicks(true);
        sliderVertical.setPaintLabels(true);
        leftPanel.add(sliderVertical, BorderLayout.WEST);

        JLabel sliderVertical_Label = new JLabel("Cols: " + String.valueOf(sliderVertical.getValue()));
        JLabel sliderHorizontal_Label = new JLabel("Rows: " + String.valueOf(sliderHorizontal.getValue()));
        leftPanel.add(sliderVertical_Label);


        ChangeListener aChangeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if ((JSlider) e.getSource() == sliderVertical) {
                    sliderVertical_Label.setText(String.valueOf("Cols: " + sliderVertical.getValue()));
                }
                if ((JSlider) e.getSource() == sliderHorizontal) {
                    sliderHorizontal_Label.setText(String.valueOf("Rows: ") + sliderHorizontal.getValue());
                }
            }
        };
        sliderVertical.addChangeListener(aChangeListener);
        sliderHorizontal.addChangeListener(aChangeListener);


        sliderHorizontal.setMajorTickSpacing(5);
        sliderHorizontal.setMinorTickSpacing(1);
        sliderHorizontal.setPaintTicks(true);
        sliderHorizontal.setPaintLabels(true);


        myPanel.add(leftPanel);
        rightPanel.add(sliderHorizontal);
        rightPanel.add(sliderHorizontal_Label);


        JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter Cols Values", JOptionPane.OK_OPTION);


        return sliderVertical.getValue();
    }

    public int[] askForColsAndRows() {
        JPanel myPanel = new JPanel();

        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.X_AXIS));
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));


        sliderVertical.setMajorTickSpacing(5);
        sliderVertical.setMinorTickSpacing(1);
        sliderVertical.setPaintTicks(true);
        sliderVertical.setPaintLabels(true);
        leftPanel.add(sliderVertical, BorderLayout.WEST);

        JLabel sliderVertical_Label = new JLabel("Cols: " + String.valueOf(sliderVertical.getValue()));
        JLabel sliderHorizontal_Label = new JLabel("Rows: " + String.valueOf(sliderHorizontal.getValue()));
        leftPanel.add(sliderVertical_Label);


        ChangeListener aChangeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if ((JSlider) e.getSource() == sliderVertical) {
                    sliderVertical_Label.setText(String.valueOf("Cols: " + sliderVertical.getValue()));
                }
                if ((JSlider) e.getSource() == sliderHorizontal) {
                    sliderHorizontal_Label.setText(String.valueOf("Rows: ") + sliderHorizontal.getValue());
                }
            }
        };
        sliderVertical.addChangeListener(aChangeListener);
        sliderHorizontal.addChangeListener(aChangeListener);


        sliderHorizontal.setMajorTickSpacing(5);
        sliderHorizontal.setMinorTickSpacing(1);
        sliderHorizontal.setPaintTicks(true);
        sliderHorizontal.setPaintLabels(true);


        myPanel.add(leftPanel);
        myPanel.add(rightPanel);
        rightPanel.add(sliderHorizontal);
        rightPanel.add(sliderHorizontal_Label);


        JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter Cols and Rows Values", JOptionPane.OK_OPTION);

        int ans[] = { sliderHorizontal.getValue(),sliderVertical.getValue()};//row,col

        return ans;
    }

    public static void main(String[] arguments) {
        new BasicArrays();
    }
}