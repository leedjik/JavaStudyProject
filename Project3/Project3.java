package JAVASTUDY;

/*
 * Project2 + 배열
 */
class PhoneBookManager extends project2{
	private static final int MAX_CNT = 100;
	private static PhoneInfo[] memory = new PhoneInfo[MAX_CNT];
	private static int memoryIdx = 0;	
	
	public static void showMenu() {
		System.out.println("선택하세요..");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 프로그램 종료");
		System.out.print("선택 : ");		
	}
	
	public static void inputData() {
		if(memoryIdx >= MAX_CNT) {
			System.out.println("저장 가능 개수를 초과하였습니다.");
			System.out.println("메모리 정리가 필요합니다.");
			return;
		}
		System.out.println("데이터 입력을 시작합니다..");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String phoneNumber = sc.nextLine();
		System.out.print("생년월일 : ");
		String birthday = sc.nextLine();
		
		PhoneInfo pi = new PhoneInfo(name, phoneNumber, birthday);
		memory[memoryIdx++] = pi;
		
		System.out.println("\n데이터 입력이 완료되었습니다.");
	}
	
	public static void findData() {
		System.out.println("데이터 검색을 시작합니다..");
		System.out.print("이름 : ");
		String findName = sc.nextLine();

		int idx = search(findName);
		
		if(idx == -1) {
			System.out.println("찾으시는 데이터가 존재하지 않습니다.");			
		}
		else {
			System.out.println("name : " + memory[idx].name);
			System.out.println("phone : " + memory[idx].phoneNumber);
			System.out.println("birth : " + memory[idx].birthday);
			System.out.println("데이터 검색이 완료되었습니다.");
		}
	}
	
	public static void eraseData() {
		System.out.println("데이터 삭제를 시작합니다...");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		int idx = search(name);
		
		if(idx == -1) {
			System.out.println("찾으시는 데이터가 존재하지 않습니다.");					
		}
		else {
			for(int i=idx + 1; i<memoryIdx; i++) {
				memory[i - 1] = memory[i];
			}
			memoryIdx--;
			System.out.println("데이터 삭제가 완료되었습니다.");
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
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}
	}

}
