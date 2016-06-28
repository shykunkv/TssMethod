package model;


import javax.swing.*;
import java.util.List;

public class MatrixDto {
    private List<JTextField> matrix;
    private Integer N;
    private Integer M;

    public MatrixDto(List<JTextField> matrix, Integer n, Integer m) {
        this.matrix = matrix;
        N = n;
        M = m;
    }

    public List<JTextField> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<JTextField> matrix) {
        this.matrix = matrix;
    }

    public Integer getN() {
        return N;
    }

    public void setN(Integer n) {
        N = n;
    }

    public Integer getM() {
        return M;
    }

    public void setM(Integer m) {
        M = m;
    }
}
