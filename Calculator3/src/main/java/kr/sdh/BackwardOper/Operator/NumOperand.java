package kr.sdh.BackwardOper.Operator;

import java.math.BigInteger;

/*
* 피연산자 클래스.
*/
public class NumOperand extends ABS_CalMember
{// 숫자 피연산자.
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