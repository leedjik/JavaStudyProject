package JAVASTUDY;

/*
 * Project2 + �迭
 */
class PhoneBookManager extends project2{
	private static final int MAX_CNT = 100;
	private static PhoneInfo[] memory = new PhoneInfo[MAX_CNT];
	private static int memoryIdx = 0;	
	
	public static void showMenu() {
		System.out.println("�����ϼ���..");
		System.out.println("1. ������ �Է�");
		System.out.println("2. ������ �˻�");
		System.out.println("3. ������ ����");
		System.out.println("4. ���α׷� ����");
		System.out.print("���� : ");		
	}
	
	public static void inputData() {
		if(memoryIdx >= MAX_CNT) {
			System.out.println("���� ���� ������ �ʰ��Ͽ����ϴ�.");
			System.out.println("�޸� ������ �ʿ��մϴ�.");
			return;
		}
		System.out.println("������ �Է��� �����մϴ�..");
		System.out.print("�̸� : ");
		String name = sc.nextLine();
		System.out.print("��ȭ��ȣ : ");
		String phoneNumber = sc.nextLine();
		System.out.print("������� : ");
		String birthday = sc.nextLine();
		
		PhoneInfo pi = new PhoneInfo(name, phoneNumber, birthday);
		memory[memoryIdx++] = pi;
		
		System.out.println("\n������ �Է��� �Ϸ�Ǿ����ϴ�.");
	}
	
	public static void findData() {
		System.out.println("������ �˻��� �����մϴ�..");
		System.out.print("�̸� : ");
		String findName = sc.nextLine();

		int idx = search(findName);
		
		if(idx == -1) {
			System.out.println("ã���ô� �����Ͱ� �������� �ʽ��ϴ�.");			
		}
		else {
			System.out.println("name : " + memory[idx].name);
			System.out.println("phone : " + memory[idx].phoneNumber);
			System.out.println("birth : " + memory[idx].birthday);
			System.out.println("������ �˻��� �Ϸ�Ǿ����ϴ�.");
		}
	}
	
	public static void eraseData() {
		System.out.println("������ ������ �����մϴ�...");
		System.out.print("�̸� : ");
		String name = sc.nextLine();
		
		int idx = search(name);
		
		if(idx == -1) {
			System.out.println("ã���ô� �����Ͱ� �������� �ʽ��ϴ�.");					
		}
		else {
			for(int i=idx + 1; i<memoryIdx; i++) {
				memory[i - 1] = memory[i];
			}
			memoryIdx--;
			System.out.println("������ ������ �Ϸ�Ǿ����ϴ�.");
		}
	}
	private static int search(String name) {
		int idx = -1;
		
		for(int i=0; i<memoryIdx; i++) {
			if(memory[i].name.compareTo(name) == 0) {
				idx = i;
				break;
			}
		}
		
		return idx;
	}
}
public class Project3 {

	
	public static void main(String[] args) {
		PhoneBookManager pm = new PhoneBookManager();
		
		while(true) {
			pm.showMenu();
			int inputIdx = PhoneBookManager.sc.nextInt();
			PhoneBookManager.sc.nextLine();
			
			if(inputIdx == 1) pm.inputData();
			else if(inputIdx == 2) pm.findData();
			else if(inputIdx == 3) pm.eraseData();
			else {
				System.out.println("���α׷��� �����մϴ�.");
				return;
			}
		}
	}

}
