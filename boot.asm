org 0x7c00

;FAT12软盘引导扇区格式 
	;BPB bios读取该地方，记录了该软盘的信息
	;boot sector  主引导扇区 操作系统的入口，由它启动操作系统

			;长度 	内容
jmp entry		;2 	跳转到引导代码入口
db   " MiniOS "		;8 	厂商名

;-----------BPB开始-----------

dw   	200h		;2	每扇区字节数
db  	1		;1	每簇扇区数
dw   	1		;2   	boot记录占用多少扇区	
db   	0		;1	FAT表的个数
dw  	0e0h		;2	根目录区最大文件数
dw   	0b40h		;2 	扇区总数（BPB_TotSec16）
db   	0f0h		;1	介质描述符
dw   	0		;2	每个FAT表所占扇区数
dw   	12h		;2	每磁道扇区数
dw   	2		;2	磁头数（面数）
dd   	0		;4    	隐藏扇区数
dd   	0		;4	如果BPB_TotSec16=0,则由这里给出扇区数
db   	0		;1 	INT 13H的驱动器号
db   	0		;1	保留，未使用
db   	29h		;1 	扩展引导标记(29h)
dd  	0		;4 	卷序列号
db   	"           "	;11 	卷标
db   	"FAT12   "	;8	文件系统类型


entry:

	mov ax, cs	;cs -> ax   cs,代码段寄存器
	mov ds, ax	;ds 数据段寄存器
	mov es, ax	;es 附加段寄存器
	
	call read_os
	call put
jmp $

;bios中断手册


read_os:
	
	mov ah, 02h	;读取磁盘
	mov al, 01h	;读取1个扇区
	mov bx, os	;存储缓冲区
	mov ch, 01h	;起始柱面
	mov cl, 02h	;起始扇区
	mov dh, 00h	;磁头号
	mov dl, 00h	;驱动器号
	int 13h		;直接磁盘服务
	ret
put:
	
	mov ax, os					
	mov bp, ax		;bp 存储字符串地址
	mov cx, os_length  		;字符串长度
	mov ax, 01301h		;ah = 13h 显示模式，al=01h 输出方式
	mov bx, 000ah		;bh 页码  bl 颜色
	mov dx, 0300h		;dx 坐标（行，列）
	int 10h			;显示中断
	ret

os:
   resb 16

os_length equ $-os
