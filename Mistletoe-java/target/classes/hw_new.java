public class hw {

    public void getValue(int i) {
        System.out.println(i);
    }

    public void setValue(int i) {
        System.out.println(i + 1);
    }

    public void setValue1(int i) {
        System.out.println(i + 1);
        System.out.println(i + 2);
    }

    public void setValue2(int i) {
        System.out.println(i + 1);
        System.out.println(i + 2);
        System.out.println(i + 3);
    }

    public void connect(int i) {
        try {
            Thread.sleep(300);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(i + 1);
        System.out.println(i + 2);
        System.out.println(i + 3);
    }

    public void setConnectlue2(int i) {
        try {
            Thread.sleep(300);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(i + 1);
        System.out.println(i + 2);
        System.out.println(i + 3);
    }

    public static void main(String[] args) {
        System.out.println("hello world");
    }
}

class SecondHW {

    public void connect(int i) {
        Thread.sleep(300);
        Thread.sleep(300);
        Thread.sleep(300);
        Thread.sleep(300);
        Thread.sleep(300);
        System.out.println(i + 1);
        System.out.println(i + 2);
        System.out.println(i + 3);
    }
}
