package app;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        QueryBuilder builder = new QueryBuilder();
        System.out.println(builder.build());
    }
}
