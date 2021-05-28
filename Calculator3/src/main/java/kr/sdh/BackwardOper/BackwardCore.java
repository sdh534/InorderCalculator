
package kr.sdh.BackwardOper;
import kr.sdh.BackwardOper.Operator.ABS_CalMember;
import kr.sdh.BackwardOper.Operator.ABS_Calculer;
import kr.sdh.BackwardOper.Operator.NumOperand;
import kr.sdh.BackwardOper.Stack.Stack;
import java.math.BigInteger;
/*
* ������ Ȱ���� ����ǥ��� ����.
* 1661030 ������
* 
* ���� �޼���, �ֿ� ���� �޼��� �κ�.
*/
/*
* 5�� 2�� ����
* ���� ���� ǥ��
*/
import java.util.Scanner;

public class BackwardCore {
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("�������� ����.");
		Scanner scanner = new Scanner(System.in);
		String input;
		String input2;
		while (true) {
			Stack<ABS_CalMember> value = new Stack<ABS_CalMember>(3);
			System.out.print("\n������ �Է��ϼ���>>");
			input = scanner.nextLine();
			if (input.equals("exit"))
				break;
			try {
				postfixExp(input, value);
			} // ���� ǥ�� ���� ��������.
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("���� ǥ�� ����� �������� �� ������ �߻��Ͽ����ϴ�.");
				continue;
			}
			System.out.print("���� ǥ�� ���Դϴ�:");
			for (int i = 0; i < value.size(); i++) {// ���� ǥ�� ���ÿ��� �ϳ��� ���.
				System.out.print(" " + value.getMemberAt(i));

			}
			try {
				System.out.println("\n���� ����Դϴ�: " + getValue(value));
			} catch (Exception e) {
				System.out.println("\n���� ������ ������ �߻��Ͽ����ϴ�.");
			}
			System.out.print("��� �Ͻðڽ��ϱ�? (Y/N): ");
			input2 = scanner.nextLine();
			
			if(input2.equals("Y")) continue;
			else if(input2.equals("y")) continue;
			else break;
		
		}
		System.out.println("���α׷��� �����մϴ�.");
		scanner.close();
	}

	private static Stack<ABS_CalMember> postfixExp(String str, Stack<ABS_CalMember> calStack)
	{// 5�� 2�� ���� ���� ǥ�� ����.
		Stack<ABS_CalMember> tempStack = new Stack<ABS_CalMember>(calStack.getSlotSize());// ������ ���� �ӽ� ����.
		BigInteger taskInteger = BigInteger.ZERO;// �������� ����.
		boolean numberTask = false;// ���� ���� �۾� ���¸� ǥ��.
		System.out.printf("%-2s | %-7s | %-30s | %-50s\n", "In", "TaskInt", "Stack", "Out");
			for(int i = 0; i < str.length(); i++)
				{// �� ���ھ� �б�.
				char taskChar = str.charAt(i);
				if(taskChar >= '0' && taskChar <= '9')
					{
					if(!numberTask) taskInteger = BigInteger.ZERO;
					taskInteger = (taskInteger.multiply(BigInteger.valueOf(10))).add(BigInteger.valueOf(taskChar - 48));
					numberTask = true;
					}
				else if(ABS_CalMember.isExist(String.valueOf(taskChar)))
				{// �Է��� ���ڰ� ���� ��ū�� ���.
					ABS_CalMember operator = ABS_CalMember.getInstance(String.valueOf(taskChar));
					if(numberTask)
					{
						calStack.pushBack(new NumOperand(taskInteger));
						numberTask = false;
					}
					if(operator.toString().equals("("))
					{// (�� ������ �ӽ� ���ÿ� Ǫ���Ѵ�.
						tempStack.pushBack(operator);
					}
					else if(operator.toString().equals(")"))
					{// )�� ������ �ӽ� ���ÿ��� ( �� ���� ������ ���ϰ�, (�� �ӽ� ���ÿ��� ���Ͽ� ������.
						while(tempStack.size() >= 0)
						{
							if(tempStack.getBack().toString().equals("("))
							{
								tempStack.popBack();
								break;
							}
							calStack.pushBack(tempStack.getBack());
							tempStack.popBack();
						}
					}
					else if(operator instanceof ABS_Calculer)
					{// ��ȣ�� �ƴҰ��.
						ABS_Calculer calOper = (ABS_Calculer)operator;
						while(true)
						{// �����ڸ� ������ �ӽ� ���ÿ��� �� �����ں��� ���� �켱������ �����ڸ� ���� ������ ���Ͽ� 
							// ���� ǥ�� ���ÿ� ������ �ڿ� �ڽ��� Ǫ���Ѵ�.
							if(tempStack.size() == 0)
							{// �ӽ� ������ ����� ��� ��������.
								tempStack.pushBack(operator);
								break;
							}
							ABS_Calculer calculer = tempStack.getBack() instanceof
							ABS_Calculer ?	(ABS_Calculer)tempStack.getBack() : null;
							if(calculer == null || calculer.getPriority() <
									calOper.getPriority())
							{// ���� �켱������ �����ڸ� �����ų�, ��ȣ�� ��������� ��������.
								tempStack.pushBack(calOper);
								break;
							}
							calStack.pushBack(tempStack.getBack());
							tempStack.popBack();
						}
					}
				}
				System.out.printf("%-2s | %-7s | %-30s | %-50s\n", taskChar, taskInteger,
						tempStack.toString(), calStack.toString());
				}
			if(numberTask) calStack.pushBack(new NumOperand(taskInteger));
			while(tempStack.size() > 0)
			{// ������ ���� �����ڵ��� Ǫ��.
				calStack.pushBack(tempStack.getBack());
				tempStack.popBack();
			}
			return calStack;
	}

	private static BigInteger valueOf(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private static NumOperand getValue(Stack<ABS_CalMember> postfixStack) {// ���� ǥ�� ������ �������� �� ��������.
		Stack<NumOperand> tempNumStack = new Stack<NumOperand>(postfixStack.getSlotSize());
		for (int i = 0; i < postfixStack.size(); i++) {
			ABS_CalMember taskMember = postfixStack.getMemberAt(i);
			NumOperand x, y, number;
			ABS_Calculer calculer;
			if (taskMember instanceof NumOperand) {// ���� ����� �ǿ����ڶ��.
				number = (NumOperand) taskMember;
				tempNumStack.pushBack(number);
			} else if (taskMember instanceof ABS_Calculer) {// ���� ����� �����ڶ��.
				calculer = (ABS_Calculer) taskMember;
// �ӽ� ���ÿ��� �� �� �����ڸ� �������� ������ ���� ����� �ٽ� Ǫ��.
				x = tempNumStack.getBack();
				tempNumStack.popBack();
				y = tempNumStack.getBack();
				tempNumStack.popBack();
				tempNumStack.pushBack(calculer.task(y, x));
			}
		}
		if (tempNumStack.getMemberAt(0) == null)
			throw new NullPointerException();
		return tempNumStack.getMemberAt(0);// �ӽ� ���ÿ� ������ ���� ���ڰ� ���.
	}
}
