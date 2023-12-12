package src.adresses;

public class Instruction3Ad {
    private String m_result;
    private String m_fun;
    private String m_op1;
    private String m_op2;



    public Instruction3Ad()
    {
        m_result="";
        m_fun="";
        m_op1="";
        m_op2="";

    }

    public Instruction3Ad(String result, String fun, String op1, String op2)
    {
        m_result=result;
        m_fun=fun;
        m_op1=op1;
        m_op2=op2;

    }

    public String toString()
    {
        return m_result + " : " + m_fun + " " + m_op1 + " " + m_op2;
    }
}