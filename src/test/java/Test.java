public class Test {
    @org.junit.Test
    public void test(){
        for (int i = 0; i <10 ; i++) {
            int code=(int)((Math.random()*9+1)*100000);
            System.out.println(code);
        }

    }
}
