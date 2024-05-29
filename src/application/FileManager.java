package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileManager implements Serializable {
	
    private static final long serialVersionUID = 123L;

	
	/**@author ghazouly**/
	
	private File file;
	
	public FileManager() {};
	
	public FileManager(String filename) {
		try {
			file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void setFile(String filename) {
		try {
			file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
	public <T> void InsertInFile(Class<T> type, ArrayList<T> mylist) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(mylist);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public <T> void getFromFile(Class<T> type, ArrayList<T> mylist) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			ArrayList<T> deserializedList = (ArrayList<T>) ois.readObject();

			// Clear the original list and add elements from the deserialized list
			mylist.clear();
			mylist.addAll(deserializedList);

			ois.close();
			fis.close();
		} catch (EOFException ignored) {
			// Handling EOFException (end of file reached)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	/*public void getFromFile2(ArrayList myarr) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			ArrayList<?> deserializedList = (ArrayList<?>) ois.readObject();

			myarr.clear();
			myarr.addAll(deserializedList);

			ois.close();
			fis.close();
		} catch (EOFException ignored) {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void InsertInFile2(ArrayList myarr) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(myarr);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
*/
}
