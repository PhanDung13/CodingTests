package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class File {
	public static <T> boolean write(String fileName, List<T> list){
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(list);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static <T> List<T> read(String fileName) {
		List<T> list = null;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {

			list = (List<T>) ois.readObject();
			ois.close();
		} catch (IOException e) {
	
		} catch (ClassNotFoundException e) {
		}
		return list;
	}
}
