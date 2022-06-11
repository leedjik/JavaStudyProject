/*
 * 1. interface 기반의 상수 표현
 * 2. PhoneBook instance는 1개만 생성하도록
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
	String name;		// 이름
	String phoneNumber;	// 전화번호
	
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
		System.out.print("이름 : ");
		String name = MenuViewer.sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = MenuViewer.sc.nextLine();
		
		return new PhoneInfo(name, phone);
	}
	
	private PhoneUnivInfo inputUniv() {
		System.out.print("이름 : ");
		String name = MenuViewer.sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = MenuViewer.sc.nextLine();
		System.out.print("전공 : ");
		String major = MenuViewer.sc.nextLine();
		System.out.print("학년 : ");
		int year = MenuViewer.sc.nextInt();
		MenuViewer.sc.nextLine();
		
		return new PhoneUnivInfo(name, phone, major, year);
	}
	
	private PhoneInfo inputCompany() {
		System.out.print("이름 : ");
		String name = MenuViewer.sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = MenuViewer.sc.nextLine();
		System.out.print("회사 : ");
		String company = MenuViewer.sc.nextLine();
		
		return new PhoneCompanyInfo(name, phone, company);		
	}
	
	public void inputData() {
		System.out.println("데이터 입력을 시작합니다..");
		System.out.println("1. 일반, 2. 대학, 3. 회사");
		System.out.print("선택>> ");
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
			System.out.println("1 ~ 3 중 하나를 입력하세요.");
		}
		
		memory[cnt++] = info;
		System.out.println("데이터 입력이 완료되었습니다. \n");
	}
	
	public void searchData() {
		System.out.println("데이터 검색을 시작합니다..");
		
		System.out.print("이름 : ");
		String name = MenuViewer.sc.nextLine();
		int idx = search(name);
		
		if(idx < 0) {
			System.out.println("해당하는 데이터가 존재하지 않습니다.");
		}
		else {
			memory[idx].showPhoneInfo();
			System.out.println("데이터 검색이 완료되었습니다.");
		}
	}
	
	public void deleteData() {
		System.out.println("데이터 삭제를 시작합니다.");
		
		System.out.print("이름 : ");
		String name = MenuViewer.sc.nextLine();
		
		int idx = search(name);
		
		if(idx < 0) {
			System.out.println("해당하는 데이터가 존재하지 않습니다.");
		}
		else {
			for(int i = idx; i < cnt - 1; i++) {
				memory[i] = memory[i + 1];
			}
			
			cnt--;
			System.out.println("데이터 삭제가 완료되었습니다. \n");
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
		System.out.println("선택하세요...");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 프로그램 종료");
		System.out.print("선택 : ");
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
				System.out.println("프로그램을 종료합니다.");
			}
		}
	}
}
