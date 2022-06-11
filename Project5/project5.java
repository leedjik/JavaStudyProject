/*
 * 1. interface ����� ��� ǥ��
 * 2. PhoneBook instance�� 1���� �����ϵ���
 */

package Project5;

import java.util.Scanner;

interface INIT_MENU
{
	int INPUT = 1, SEARCH = 2, DELETE = 3, EXIT = 4;
}

interface INPUT_SELECT
{
	int NORMAL = 1, UNIV = 2, COMPANY = 3;
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
	PhoneInfo[] memory = new PhoneInfo[MAX_CNT];
	int cnt = 0;
	
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
	
	public void inputData() {
		System.out.println("������ �Է��� �����մϴ�..");
		System.out.println("1. �Ϲ�, 2. ����, 3. ȸ��");
		System.out.print("����>> ");
		int inputType = MenuViewer.sc.nextInt();
		MenuViewer.sc.nextLine();
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
		else {
			System.out.println("1 ~ 3 �� �ϳ��� �Է��ϼ���.");
		}
		
		memory[cnt++] = info;
		System.out.println("������ �Է��� �Ϸ�Ǿ����ϴ�. \n");
	}
	
	public void searchData() {
		System.out.println("������ �˻��� �����մϴ�..");
		
		System.out.print("�̸� : ");
		String name = MenuViewer.sc.nextLine();
		int idx = search(name);
		
		if(idx < 0) {
			System.out.println("�ش��ϴ� �����Ͱ� �������� �ʽ��ϴ�.");
		}
		else {
			memory[idx].showPhoneInfo();
			System.out.println("������ �˻��� �Ϸ�Ǿ����ϴ�.");
		}
	}
	
	public void deleteData() {
		System.out.println("������ ������ �����մϴ�.");
		
		System.out.print("�̸� : ");
		String name = MenuViewer.sc.nextLine();
		
		int idx = search(name);
		
		if(idx < 0) {
			System.out.println("�ش��ϴ� �����Ͱ� �������� �ʽ��ϴ�.");
		}
		else {
			for(int i = idx; i < cnt - 1; i++) {
				memory[i] = memory[i + 1];
			}
			
			cnt--;
			System.out.println("������ ������ �Ϸ�Ǿ����ϴ�. \n");
		}
	}
	private int search(String name) {
		for(int i=0; i<cnt; i++) {
			if(name.compareTo(memory[i].name) == 0) {
				return i;
			}
		}
		
		return -1;
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

	public static void main(String[] args) {
		PhoneBookManager manager = PhoneBookManager.createManagerInst();
		int inputType = 0;
		
		while(true) {
			MenuViewer.showMenu();
			inputType = MenuViewer.sc.nextInt();
			MenuViewer.sc.nextLine();
			
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
			}
		}
	}
}
