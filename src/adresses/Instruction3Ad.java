package src.adresses;

public class Instruction3Ad {
    private String m_result;
    private Operator m_operator;
    private String m_op1;
    private String m_op2;

    public enum Operator {
        // op binaires:
        cons,
        //op unaires :
        copy, setTo, hd, tl,newConst,
        //appels de fontions :
        functCall, addParam,
        //définition de fonctions :
        returns, param, beginFun, endFun
    }

    public Instruction3Ad(String result, Operator operator, String op1, String op2)
    {
        m_result=result;
        m_operator=operator;
        m_op1=op1;
        m_op2=op2;
    }

    public String toString()
    {
        String result = "";
        if (!m_result.equals("")) result += m_result+" ";
        result += m_operator + " " + m_op1;
        if ( !m_op2.equals("")) result += " " +m_op2;
        return result;
    }
}