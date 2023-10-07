package an.y.ledov.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FonoapiMockAppTest {

    @Autowired
    private FonoapiMockApp app;

    @Test
    @DisplayName("should successfully load context")
    public void contextLoads() {
        assertThat(app).isNotNull();
    }

}
