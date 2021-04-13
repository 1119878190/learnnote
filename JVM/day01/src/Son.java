public class Son extends Father {

    String str = "sonString";


    @Override
    public void sleep() {
        System.out.println("son");
    }

    public void run(){
        System.out.println("sonRUn");
    }
}
