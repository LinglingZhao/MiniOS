
public class Test {
	public static void main(String[] args) {
		Floppy floppy = new Floppy();
		floppy.writeFloppy(0,0,1,"boot");
		
		String s = "welcome!!!";
		floppy.writeFloppy(0, 1, 2, s.getBytes());
		
		Utils.writeFile("system.img", floppy.getFloppy());
		System.out.println("写入完成");
		
	}
}
