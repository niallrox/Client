import java.io.Serializable;
import java.util.Scanner;

public class TextInput implements Serializable {
    private String currentInput;

    public String getNextInput() {
        Scanner sc = new Scanner(System.in);
        currentInput = sc.nextLine().trim();
        return currentInput;
    }

    public void output(String s) {
        System.out.println(s);
    }
}