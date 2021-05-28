package kr.sdh.BackwardOper.Operator;

/*
* ABS_CalMember �߻� Ŭ����.
* ��� ���� ���õ� Ŭ������ �� Ŭ������ ��� ����.
* 
* ���� ���� Ŭ������ �ν��Ͻ��� �̰����� üũ, ����.
*/
public abstract class ABS_CalMember {
	private static final String TAG = "kr.sdh.BackwardOper.Operator.REF_Oper";// Ŭ���� �н�.

	public static final ABS_CalMember getInstance(String operator) {// ��Ʈ������ �ش� ������ Ŭ���� �ν��Ͻ� ���.
		Object returnObject = null;
		try {// �Ѿ�� ��Ʈ������ �ν��Ͻ�ȭ �õ�.
			Class<?> taskClass = Class.forName(ABS_CalMember.classNameConverter(operator, TAG));
			returnObject = taskClass.newInstance();
		} catch (Exception e) {// �ν��Ͻ�ȭ�� �Ұ��� �Ұ��.
			System.out.println("ERROR");
			e.printStackTrace();
			return null;
		}
		if (returnObject instanceof ABS_CalMember) {// ABS_Calcuuler�� ��ӹ��� Ŭ������ �ν��Ͻ� �ϰ�� ĳ�����ؼ� ����.
			return (ABS_CalMember) returnObject;
		}
		return null;
	}

	public static final boolean isExist(String operator) {// �ش��ϴ� ������ Ŭ������ �ֳ� ã��.
		try {// �ش� Ŭ������ �ֳ� Ž��.
			Class.forName(ABS_CalMember.classNameConverter(operator, TAG));
		} catch (Exception e) {// �ش��ϴ� Ŭ������ �������.
			return false;
		}
		return true;
	}

	private static String classNameConverter(String operator, String tag) {// Ŭ���� �̸� ��ȯ��
		String classNameCode = new String(tag);
		for (int i = 0; i < operator.length(); ++i) {// �Ѿ�� ���ڿ��� ���ڷ� ����
			classNameCode += ("_" + Integer.valueOf(operator.charAt(i)));
		}
		return classNameCode;
	}
}

/*
 * ��ȣ Ŭ����.
 */
class REF_Oper_40 extends ABS_CalMember {// ��ȣ ���� (
	@Override
	public String toString() {
		return "(";
	}
}

class REF_Oper_41 extends ABS_CalMember {// ��ȣ �ݱ� )
	@Override
	public String toString() {
		return ")";
	}
}