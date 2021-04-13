public class StringTest {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            String.valueOf(i).intern();
        }

    }
}
