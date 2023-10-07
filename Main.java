import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidDataFormatException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Insert personal data (Last_name Name Patronymic Date_of_birth Phone_number Gender (split by space)): ");
            String input = scanner.nextLine();
            String[] inputData = input.split(" ");

            if (inputData.length != 6) {
                throw new InvalidDataFormatException("Incorrect amount of data.");
            }

            String lastName = inputData[0];
            String name = inputData[1];
            String patronymic = inputData[2];
            String birthDate = inputData[3];
            String phoneNumber = inputData[4];
            String gender = inputData[5];

            if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}") || !phoneNumber.matches("\\d+") || !(gender.equals("f") || gender.equals("m"))) {
                throw new InvalidDataFormatException("Неверный формат данных");
            }

            String record = lastName + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true))) {
            writer.write(record);
            writer.newLine();
            writer.close();

            System.out.println("Data recorded successfully.");
            }
            catch (IOException e) {
                System.out.println("Error during the work with file: ");
                e.printStackTrace();
            }
        }
        catch (InvalidDataFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}