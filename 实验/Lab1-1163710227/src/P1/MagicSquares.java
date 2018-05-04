package P1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class MagicSquares {
	public static void main(String[] argv) {
		System.out.println(generateMagicSquare(6));
		System.out.println(generateMagicSquare(-1));
		System.out.println(generateMagicSquare(5));

		System.out.println(isLegalMagicSquare("E:\\WOKESPACE\\eclipse\\Lab1_1163710227\\src\\P1\\txt\\6.txt"));
	}

	public static boolean isLegalMagicSquare(String fileName) {

		int DEFAULT_NUMBER = 0;// �涨Ĭ�ϳ�ʼ�������֣��Ա������õĴ�����
		int lines = DEFAULT_NUMBER, cols = DEFAULT_NUMBER, theConstant = DEFAULT_NUMBER;// ��ʼ�������������Լ�������
		boolean flag = true;// Ϊ�˱��⺯���г��ֶ��return false��̫����ڣ������ñ�־��������������жϣ����ֻ����һ������

		MyList square = new MyList();// ʹ�ö�̬����洢����
		Scanner input = null;

		try {
			input = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("�ļ������ڣ�");
			return false;
			// ����java��ĳЩ�涨���˴����ò�д�쳣�������
			// ����Ϊ���ܹ����ŵ��������ͷ���false�����ò���return����쳣����
			// �˴�����̰����ص�һ����ԭ�򣬷�����ʹ�����ø��Ӹ��ӡ���ͬѰ�����������
			// Ϊ��ά�ִ���ļ���ԣ��˴�������ͨ
		}

		while (input.hasNextLine() && flag) {// ���ж�ȡ
			String aLine = input.nextLine();
			String[] myLine = aLine.split("\t");
			lines++;
			if (cols != myLine.length) {// ÿ�е�������һ�£���ĳ����Ԫ��ȱʧ�����󲻺Ϸ�
				if (cols != DEFAULT_NUMBER) {
					System.out.println("�����" + lines + "����Ԫ��ȱʧ�����ּ��\\t������");
					flag = false;
					break;
				} else
					cols = myLine.length;
			}

			for (int i = 0; i < cols; i++) {// ���Ͼ�õĸ������ֵ��ַ���ת��Ϊ���֣��з����������󲻺Ϸ�
				String number = myLine[i];
				if (number.matches("[0-9]+"))
					square.add(Integer.valueOf(number));
				else {
					System.out.println("�����" + square.size() / cols + "�С���" + square.size() % cols + "���з���������");
					flag = false;
					break;
				}
			}
		}

		if (flag && cols != lines) {// ��������һ�£����󲻺Ϸ�
			System.out.println("������������һ�£�");
			flag = false;
		}

		if (flag) {
			for (int i = 0; i < lines; i++) {// ÿ�кͲ�һ�£����󲻺Ϸ�
				int count = 0;
				for (int j = 0; j < cols; j++) {
					count += square.get(i * cols + j);
				}
				if (theConstant != count) {
					if (theConstant != DEFAULT_NUMBER) {
						System.out.println("�����" + i + "�к��������еĺͲ�ͬ��");
						flag = false;
						break;
					} else
						theConstant = count;
				}
			}
		}

		if (flag) {
			for (int i = 0; i < cols; i++) {// ÿ�кͲ�һ�£����󲻺Ϸ�
				int count = 0;
				for (int j = 0; j < lines; j++) {
					count += square.get(j * cols + i);
				}
				if (theConstant != count) {
					if (theConstant != DEFAULT_NUMBER) {
						System.out.println("�����" + i + "�к��������еĺͲ�ͬ��");
						flag = false;
						break;
					} else
						theConstant = count;
				}
			}
		}

		if (flag) {
			int count1 = 0, count2 = 0;// �Խ��ߺͲ�һ�£����󲻺Ϸ�
			for (int i = 0; i < cols; i++) {
				count1 += square.get(i * cols + i);
				count2 += square.get((lines - i - 1) * cols + i);
			}
			if (count1 != count2 || count1 != theConstant) {
				System.out.println("����Խ��ߵĺ����������еĺͲ�ͬ��");
				flag = false;
			}
		}

		// ��һ����
		input.close();
		return flag;
	}

	public static boolean generateMagicSquare(int n) {

		int row = 0, col = n / 2, i, j, square = n * n;
		boolean flag = true;// ��һ����ԭ�����ñ���

		File f = new File("E:\\WOKESPACE\\eclipse\\Lab1_1163710227\\src\\P1\\txt\\6.txt");
		PrintStream printStream = null;
		try {
			f.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(f);
			printStream = new PrintStream(fileOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (n < 0) {// �ų�n<0�����
			System.out.println("nӦ�ô���0��");
			flag = false;
		} else if (n % 2 != 1) {// �ų�nΪż�������
			System.out.println("nӦ��Ϊ������");
			flag = false;
		} else {// �Ͳ����������׻÷�
			int magic[][] = new int[n][n];
			for (i = 1; i <= square; i++) {
				magic[row][col] = i;

				if (i % n == 0)
					row++;
				else {
					if (row == 0)
						row = n - 1;
					else
						row--;
					if (col == (n - 1))
						col = 0;
					else
						col++;
				}
			}

			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++)
					printStream.print(magic[i][j] + "\t");
				printStream.println();
			}
		}

		printStream.close();
		return flag;
	}

	public static class MyList {

		// ����һ����ʼ����Ϊ0�����飬������������
		private int[] buff = new int[0];

		public MyList() {
		}

		// ����
		public void add(int number) {
			// ���������飬������ԭ���鳤��+1
			int[] dest = new int[buff.length + 1];
			// ��ԭ��������ݿ�����������
			System.arraycopy(buff, 0, dest, 0, buff.length);
			// ����Ԫ�طŵ�dest�����ĩβ
			dest[buff.length] = number;
			// ��buffָ��dest
			buff = dest;
		}

		// �޸�ָ��λ�õ�Ԫ��
		public void modify(int index, int number) {
			buff[index] = number;
		}

		// ɾ��ָ��λ�õ�Ԫ��
		public void delete(int index) {
			int[] dest = new int[buff.length - 1];
			// ��ԭ��������ݿ�����������
			System.arraycopy(buff, 0, dest, 0, index);
			System.arraycopy(buff, index + 1, dest, index, buff.length - 1 - index);
			buff = dest;
		}

		// ���ָ��λ�õ�Ԫ��
		public int get(int index) {
			return buff[index];
		}

		// ��ָ��λ�ò���ָ��Ԫ��
		public void insert(int index, int number) {
			// ���������飬������ԭ���鳤��+1
			int[] dest = new int[buff.length + 1];
			// ��ԭ��������ݿ�����������
			System.arraycopy(buff, 0, dest, 0, index);
			dest[index] = number;
			System.arraycopy(buff, index, dest, index + 1, buff.length - index);
			buff = dest;

		}

		// ���Ԫ�ظ���
		public int size() {
			return buff.length;
		}

		public void print() {
			for (int i = 0; i < size(); i++)
				System.out.println(buff[i]);
		}
	}

}