package kr.sdh.BackwardOper.Operator;

import java.math.BigInteger;

/*
* �ǿ����� Ŭ����.
*/
public class NumOperand extends ABS_CalMember
{// ���� �ǿ�����.
private BigInteger value;
public NumOperand(BigInteger v)
{
this.value = v;
}
public BigInteger getValue()
{
return this.value;
}
@Override public String toString() { return String.valueOf(this.value); }
}