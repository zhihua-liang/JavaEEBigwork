package easymall.util;

import java.util.UUID;

public class CodeUtil {
	//����Ψһ�ļ�����
	public static String generateUniqueCode(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}

