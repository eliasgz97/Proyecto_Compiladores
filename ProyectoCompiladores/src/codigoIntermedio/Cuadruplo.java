/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoIntermedio;

/**
 *
 * @author EliasGZ
 */
public class Cuadruplo {

    String operator;
    String args1;
    String args2;
    String result;

    public Cuadruplo(String operator, String args1, String args2, String result) {
        this.operator = operator;
        this.args1 = args1;
        this.args2 = args2;
        this.result = result;
    }

    public Cuadruplo() {
    }

    public String getArgs1() {
        return args1;
    }

    public void setArgs1(String args1) {
        this.args1 = args1;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getArgs2() {
        return args2;
    }

    public void setArgs2(String args2) {
        this.args2 = args2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String[] getTupla() {
        String[] retval = {this.operator, this.args1, this.args2, this.result};
        return retval;
    }

    public String prettyToString() {

        String retval = "";

        if (operator.equals("+")
                || operator.equals("-")
                || operator.equals("/")
                || operator.equals("*")) {
            retval += "     " + result + " = " + args1 + " " + operator + " " + args2;
        } else if (operator.equals("=")) {
            retval += "     " + result + " = " + args1;
        } else if (operator.contains("IF")) {
            String temp_op = operator.replace("IF", "");
            retval += "     if " + args1 + temp_op + args2 + " " + result;
        } else if (operator.startsWith("ETIQ")) {
            retval += args1 + ": ";
        } else if (operator.equals("GOTO")) {
            retval += "     goto " + args1;
        }else if (operator.equals("RET")) {
            retval += "     ret " + args1;
        }
        return retval;
    }
}
