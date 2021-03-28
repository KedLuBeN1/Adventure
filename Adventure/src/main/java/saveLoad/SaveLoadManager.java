package saveLoad;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveLoadManager {
	private static SaveLoadManager instance;
	
	private SaveLoadManager() {
	}

	public static synchronized SaveLoadManager getInstance() {
		if (instance == null) {
			instance = new SaveLoadManager();
		}
		return instance;
	}
	
	public void save(Serializable data, String fileName) throws Exception{
		try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))){
			oos.writeObject(data);
		}
	}
	
	public Object load(String fileName) throws Exception{
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))){
			return ois.readObject();
		}
	}

}

