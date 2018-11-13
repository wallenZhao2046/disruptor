package wallen;

// -Xmx -Xms
public class ExampleOfOOM {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();

        while(true){
            list.append("aaaaaaaaaaa");
        }

    }
}
