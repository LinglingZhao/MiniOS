
public class Floppy {//软盘
	//磁头
	final int MAGNETIC_HEAD_0 = 0;
	final int MAGNETIC_HEAD_1 = 1;

	//扇区大小512字节
	final int SECTOR_SIZE = 512;
	//80个柱面 （磁道） 编号 0-79
	final int CYLINDER_COUNT = 80; 
	//一个柱面 18个扇区 编号 1-18
    final int SECTORS_COUNT = 18;
	
    //当前磁头
    private int magneticHead = MAGNETIC_HEAD_0;
    private int current_cylinder = 0;
    private int current_sector = 0;
    
    private byte[] floppy = new byte[2*CYLINDER_COUNT*SECTORS_COUNT*SECTOR_SIZE];
    
    public Floppy() {
		initFloppy();
	}
    
	private void initFloppy() {
		//第一个扇区以0x55aa 结尾
		floppy[510]=(byte)0x55;
		floppy[511]=(byte) 0xaa;
	}

	public void writeFloppy(int magneticHead,int cylinder,int sector,String pathName) {
		byte[] data = Utils.readFile(pathName);
		writeFloppy(magneticHead, cylinder, sector, data);
	}
	
	
	public void writeFloppy(int magneticHead,int cylinder,int sector,byte[] data) {
		sector--;
		int offset = (cylinder+magneticHead)*SECTORS_COUNT;
		offset = offset*2+sector;
		offset = offset*SECTOR_SIZE;
		for (int i=0;i<data.length;i++) {
			floppy[offset+i] = data[i];
		}
	}
	
	
	
	public byte[] getFloppy() {
		return floppy;
	}

	public void setFloppy(byte[] floppy) {
		this.floppy = floppy;
		floppy[510]=(byte)0x55;
		floppy[511]=(byte) 0xaa;
	}

	public int getCurrent_cylinder() {
		return current_cylinder;
	}
	public void setCurrent_cylinder(int current_cylinder) {
		this.current_cylinder = current_cylinder;
	}
	public int getCurrent_sector() {
		return current_sector;
	}
	public void setCurrent_sector(int current_sector) {
		this.current_sector = current_sector;
	}
	
}
