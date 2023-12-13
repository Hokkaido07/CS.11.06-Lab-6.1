import java.io.*;
import java.util.Scanner;

public class AdventureTime {

    /** This is the main method and it is where you will test your implementations for challengeOne, challengeTwo, etc.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String fileName = "inputOneTwo.txt";
        int result = challengeOne(fileName);
        System.out.println("Number of measurements larger than the previous measurement: " + result);
        int result2 = challengeTwo(fileName);
        System.out.println("Number of sums larger than the previous sum: " + result2);
        String fileName2 = "InputThreeFour.txt";
        int result3 = challengeThree(fileName2);
        System.out.println("Result of multiplying the final horizontal position: " + result3);
        int result4 = challengeFour(fileName2);
        System.out.println("Result of multiplying the final horizontal position by the final depth: " + result4);



    }

    /** TODO 1
     *
     * Challenge 1
     *
     * @param fileName
     * @return Answer to Challenge 1
     * @throws IOException
     */
    public static int challengeOne(String fileName) throws IOException {
        int[] values = readFile(fileName);
        int count = 0;
        for (int i = 1; i < values.length; i++) {
            int prev = values[i - 1];
            int curr = values[i];
            if (curr > prev) {
                count++;
            }
        }
        return count;
    }

    /** TODO 2
     *
     * Challenge 2
     *
     * @param fileName
     * @return Answer to Challenge 2
     * @throws FileNotFoundException
     */
    public static int challengeTwo(String fileName) throws FileNotFoundException {
        int[] values = readFile(fileName);
        int count = 0;
        Integer last = null;
        for (int i = 1; i < values.length - 1; i++) {
            int sliding1 = values[i - 1];
            int sliding2 = values[i];
            int sliding3 = values[i + 1];
            int total = sliding1 + sliding2 + sliding3;
            if (last != null && total > last) {
                count++;
            }
            last = total;
        }
        return count;
    }




    /** TODO 3
     *
     * Challenge 3
     *
     * @param fileName
     * @return Answer to Challenge 3
     * @throws FileNotFoundException
     */
    public static int challengeThree(String fileName) throws FileNotFoundException {
        int horizontalPosition = 0;
        int depth = 0;
        String[] commands = readCommandFromFile(fileName);

        int index = 0;
        while (index < commands.length) {
            String command = commands[index];
            String[] parts = command.trim().split("\\s+");
            String action = parts[0];
            int value = Integer.parseInt(parts[1]);

            if (action.equals("forward")) {
                horizontalPosition += value;
            } else if (action.equals("down")) {
                depth += value;
            } else if (action.equals("up")) {
                depth -= value;
            }

            index++;
        }

        return horizontalPosition * depth;
    }

    public static String[] readCommandFromFile(String fileName) throws FileNotFoundException  {
        String[] commands = new String[0];
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            int count = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                commands = appendToArray(commands, line);
                count++;
            }
        }
        return commands;
    }

    public static String[] appendToArray(String[] arr, String element) {
        String[] newArr = new String[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[arr.length] = element;
        return newArr;
    }


        /** TODO 4
         *
         * Challenge 4
         *
         * @param filename
         * @return Answer to Challenge 4
         * @throws FileNotFoundException
         */
    public static int challengeFour(String filename) throws FileNotFoundException {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        String[] commands = readCommandsFromFile("inputThreeFour.txt");

        for (String command : commands) {
            String[] parts = command.trim().split("\\s+");
            String action = parts[0];
            int value = Integer.parseInt(parts[1]);

            if (action.equals("forward")) {
                horizontalPosition += value;
                depth += aim * value;
            } else if (action.equals("down")) {
                aim += value;
            } else if (action.equals("up")) {
                aim -= value;
            }
        }

        return horizontalPosition * depth;
    }
    public static String[] readCommandsFromFile(String fileName) throws FileNotFoundException{
        String[] commands = new String[0];
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            int count = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                commands = appendToArray(commands, line);
                count++;
            }
        }
        return commands;
    }

    /** This method will write the values passed as challengeOne, challengeTwo, challengeThree, and challengeFour to a text file.
     * Do not edit this method.
     */
    private static void writeFileAllAnswers(String outPutFilename, int challengeOne, int challengeTwo, int challengeThree, int challengeFour) throws IOException {
        File file = new File(outPutFilename);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("Challenge 1: " + "\t" + challengeOne + "\n");
        bufferedWriter.write("Challenge 2: " + "\t" + challengeTwo + "\n");
        bufferedWriter.write("Challenge 3: " + "\t" + challengeThree + "\n");
        bufferedWriter.write("Challenge 4: " + "\t" + challengeFour + "\n");
        bufferedWriter.close();
    }

    /** This method will read the values in inputFilename into an array of integers, which it will return.
     * Do not edit this method.
     */
    private static int[] readFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        int[] data = new int[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextInt();
        }
        scanner.close();
        return data;
    }

    /** This method will count the number of lines in a text file, which it will return.
     * Do not edit this method.
     */
    private static int countLinesInFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }


}