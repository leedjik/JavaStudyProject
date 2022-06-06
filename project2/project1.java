package JAVASTUDY;


class PhoneInfo{
	String name;		// �̸�
	String phoneNumber;	// ��ȭ��ȣ
	String birthday;	// �������
	
	PhoneInfo(String name, String phoneNumber){
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = null;
	}
	PhoneInfo(String name, String phoneNumber, String birthday){
		this(name, phoneNumber);
		this.birthday = birthday;
	}
	void printInfo() {
		System.out.println("Name : "+ this.name);
		System.out.println("phoneNumber : "+ this.phoneNumber);
		if(this.birthday != null) {
			System.out.println("birthday : "+ this.birthday);
		}
		System.out.println();
	}
}

public class project1 {

	public static void main(String[] args) {
		PhoneInfo pi1 = new PhoneInfo("������", "323-1111", "92,09,12");
		PhoneInfo pi2 = new PhoneInfo("��ȿ��", "321-2222");
		pi1.printInfo();
		pi2.printInfo();
	}
}

