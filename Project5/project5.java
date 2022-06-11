/*
 * Project5
 *   1. interface ����� ��� ǥ��
 *   2. PhoneBook instance�� 1���� �����ϵ���
 * Project6 ���ʹ� ���� �ڵ忡 �߰� �� ����
 *   1. Exception ó���ϱ�
 * Project7
 *   1. HashSet�� �̿��� �����̸��� ���� ���� �ν��Ͻ��� ������ֱ�(��ȣ, �г� ���� ��� ����)
 *   2. �ڷ� ������ set���� �ٲ��ֱ�
 */

package Project5;

import java.util.HashSet;
import java.util.Scanner;

interface INIT_MENU
{
	int INPUT = 1, SEARCH = 2, DELETE = 3, EXIT = 4;
}

interface INPUT_SELECT
{
	int NORMAL = 1, UNIV = 2, COMPANY = 3;
}

// ����� ���ʸ�Ŭ������ exception�� ��ӹ��� �� ����.
class MenuInputException extends Exception
{
	int wrongInput;
	
	public MenuInputException(int wrongInput) {
		super("�߸��� ������ �̷������ϴ�.");
		this.wrongInput = wrongInput;
	}
	public void showWorngMessage() {
		System.out.println(this.wrongInput + "�� �ش��ϴ� ������ �������� �ʽ��ϴ�.");
	}
	
}
class PhoneInfo{
	String name;		// �̸�
	String phoneNumber;	// ��ȭ��ȣ
	
	PhoneInfo(String name, String phoneNumber){
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	void showPhoneInfo() {
		System.out.println("Name : "+ this.name);
		System.out.println("phoneNumber : "+ this.phoneNumber);
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(name.compareTo(((PhoneInfo)obj).name) == 0) return true;
		return false;
	}
}

class PhoneUnivInfo extends PhoneInfo{
	String major;
	int year;
	
	PhoneUnivInfo(String name, String phoneNumber, String major, int year) {
		super(name, phoneNumber);
		this.major = major;
		this.year = year;
	}
	void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("major : "+ major);
		System.out.println("year : "+ year);
	}
}

class PhoneCompanyInfo extends PhoneInfo{
	String company;

	PhoneCompanyInfo(String name, String phoneNumber, String company) {
		super(name, phoneNumber);
		this.company = company;
	}
	void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("major : "+ company);
	}
}

class PhoneBookManager{
	final int MAX_CNT = 100;
	HashSet<PhoneInfo> memory = new HashSet<PhoneInfo>();
	
	static PhoneBookManager inst = null;
	public static PhoneBookManager createManagerInst() {
		if(inst == null) {
			inst = new PhoneBookManager();
		}
		
		return inst;
	}
	//private PhoneBookManager() {}
	
	private PhoneInfo inputNormal() {
		System.out.print("�̸� : ");
		String name = MenuViewer.sc.nextLine();
		System.out.print("��ȭ��ȣ : ");
		String phone = MenuViewer.sc.nextLine();
		
		return new PhoneInfo(name, phone);
	}
	
	private PhoneUnivInfo inputUniv() {
		System.out.print("�̸� : ");
		String name = MenuViewer.sc.nextLine();
		System.out.print("��ȭ��ȣ : ");
		String phone = MenuViewer.sc.nextLine();
		System.out.print("���� : ");
		String major = MenuViewer.sc.nextLine();
		System.out.print("�г� : ");
		int year = MenuViewer.sc.nextInt();
		MenuViewer.sc.nextLine();
		
		return new PhoneUnivInfo(name, phone, major, year);
	}
	
	private PhoneInfo inputCompany() {
		System.out.print("�̸� : ");
		String name = MenuViewer.sc.nextLine();
		System.out.print("��ȭ��ȣ : ");
		String phone = MenuViewer.sc.nextLine();
		System.out.print("ȸ�� : ");
		String company = MenuViewer.sc.nextLine();
		
		return new PhoneCompanyInfo(name, phone, company);		
	}
	
	public void inputData() throws MenuInputException {
		System.out.println("������ �Է��� �����մϴ�..");
		System.out.println("1. �Ϲ�, 2. ����, 3. ȸ��");
		System.out.print("����>> ");
		
		int inputType = MenuViewer.sc.nextInt();
		MenuViewer.sc.nextLine();
		
		if(inputType > INPUT_SELECT.COMPANY || inputType < INPUT_SELECT.NORMAL)
			throw new MenuInputException(inputType);

		PhoneInfo info = null;
		
		if(inputType == INPUT_SELECT.NORMAL) {
			info = inputNormal();
		}
		else if(inputType == INPUT_SELECT.UNIV) {
			info = inputUniv();
		}
		else if(inputType == INPUT_SELECT.COMPANY) {
			info = inputCompany();
		}
		
		boolean alreadyAdded = memory.add(info);
		
		if(alreadyAdded == true) {
			System.out.println("������ �Է��� �Ϸ�Ǿ����ϴ�. \n");
		}
		else {
			System.out.println("�̹� ����� �������Դϴ�. \n");
		}
	}
	
	public void searchData() throws MenuInputException {
		System.out.println("������ �˻��� �����մϴ�..");
		
		System.out.print("�̸� : ");
		String name = MenuViewer.sc.nextLine();
		PhoneInfo info = search(name);
		
		if(info == null) {
			System.out.println("�ش��ϴ� �����Ͱ� �������� �ʽ��ϴ�.\n");
		}
		else {
			info.showPhoneInfo();
			System.out.println("������ �˻��� �Ϸ�Ǿ����ϴ�.\n");
		}
	}
	
	public void deleteData() throws MenuInputException {
		System.out.println("������ ������ �����մϴ�.");
		
		System.out.print("�̸� : ");
		String name = MenuViewer.sc.nextLine();
		
		PhoneInfo info = search(name);
		
		if(info == null) {
			System.out.println("�ش��ϴ� �����Ͱ� �������� �ʽ��ϴ�.\n");
		}
		else {
			memory.remove(info);
			System.out.println("������ ������ �Ϸ�Ǿ����ϴ�. \n");
		}
	}
	private PhoneInfo search(String name) {

		for(PhoneInfo now: memory) {
			if(name.equals(now.name)) return now;
		}
		
		return null;
	}
}

class MenuViewer{
	public static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("�����ϼ���...");
		System.out.println("1. ������ �Է�");
		System.out.println("2. ������ �˻�");
		System.out.println("3. ������ ����");
		System.out.println("4. ���α׷� ����");
		System.out.print("���� : ");
	}
}
public class project5 {

	public static void main(String[] args) throws MenuInputException {
		PhoneBookManager manager = PhoneBookManager.createManagerInst();
		int inputType = 0;
		
		while(true) {

			try {
				MenuViewer.showMenu();
				inputType = MenuViewer.sc.nextInt();
				MenuViewer.sc.nextLine();
				
				if(inputType < INIT_MENU.INPUT || inputType > INIT_MENU.EXIT)
					throw new MenuInputException(inputType);
				
				if(inputType == INIT_MENU.INPUT) {
					manager.inputData();
				}
				else if(inputType == INIT_MENU.SEARCH) {
					manager.searchData();
				}
				else if(inputType == INIT_MENU.DELETE) {
					manager.deleteData();
				}
				else if(inputType == INIT_MENU.EXIT){
					System.out.println("���α׷��� �����մϴ�.");
					return;
				}				
			} catch(MenuInputException e){
				e.showWorngMessage();
				System.out.println("�޴� ������ ó������ �ٽ� �����մϴ�.\n");
			}
		}
	}
}
