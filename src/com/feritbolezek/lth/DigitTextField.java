package com.feritbolezek.lth;
import javafx.scene.control.TextField;

/**
 * JavaFX TextField that only allows a single digit.
 */
public class DigitTextField extends TextField {

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    private int row;
    private int column;

    DigitTextField() {
        super();
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if ( (limit() || checkIfDigit(text)) && !text.equals("") ) return;
        super.replaceText(start, end, text);
    }

    @Override
    public void replaceSelection(String replacement) {
        if ( (limit() || checkIfDigit(replacement)) && !replacement.equals("") ) return;
        super.replaceSelection(replacement);
    }

    private Boolean limit() {
        return getText().length() >= 1;
    }

    private boolean checkIfDigit(String text) {
        try {
            Integer.parseInt(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
    public void clear() {
        super.clear();
    }

}

