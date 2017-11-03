package exercise2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Exercise2 {
	public static void save(Classroom classroom, Path filePath) throws IOException {
		try(OutputStream output = Files.newOutputStream(filePath)) {
			ObjectOutput objectOutput = new ObjectOutputStream(output);
			objectOutput.writeObject(classroom);
		}
	}

	public static Classroom load(Path filePath) throws ClassNotFoundException, IOException {
		/*Charset utf8Charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(filePath, utf8Charset)) {
			reader.lines().forEach(line -> ...);
		}
		catch (Exception e) {}*/
		try(InputStream input = Files.newInputStream(filePath)) {
			ObjectInput objectInput = new ObjectInputStream(input);
			return  (Classroom) objectInput.readObject();
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Teacher teacher = new Teacher("Claire", "Barnett",
			LocalDate.of(1975, 3, 7), new PhoneNumber("+32 65 123 456"),
			new Location("Ho.23", "Houdain"));
		Student[] students = {
			new Student("Jo", "Henderson", LocalDate.of(1981, 3, 8)),
			new Student("Caleb", "Stephen", LocalDate.of(1983, 5, 9)),
			new Student("Diana", "Shelton", LocalDate.of(1981, 2, 9))
		};
		Classroom classroom = new Classroom(teacher, students);

		Path filePath = Paths.get("classroom.ser");

		save(classroom, filePath);

		System.out.printf("Classroom saved to %s, size=%d bytes\n", filePath.toString(), Files.size(filePath));

		Classroom loadedClassroom = load(filePath);

		System.out.printf("Classroom loaded from %s: %s\n", filePath.toString(), loadedClassroom.toString());
	}
}
