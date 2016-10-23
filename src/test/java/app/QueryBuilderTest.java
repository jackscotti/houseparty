package app;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class QueryBuilderTest {

    @Test
    public void testBuild() throws IOException {
        QueryBuilder mockedBuilder = spy(new QueryBuilder());

        when(mockedBuilder.params()).thenReturn(
                buildHashMap()
        );

        assertEquals(
                mockedBuilder.build(),
                mockedBuilder.ROOT_URL + "rooms=4&price=500&key=a-mock-key"
        );
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "a-mock-key");
        map.put("rooms", "4");
        map.put("price", "500");

        return map;
    }
}
