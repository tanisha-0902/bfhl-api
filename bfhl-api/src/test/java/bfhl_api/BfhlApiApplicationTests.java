package bfhl_api;

import bfhl_api.dto.BfhlRequestDTO;
import bfhl_api.dto.BfhlResponseDTO;
import bfhl_api.service.BfhlServiceImpl;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class BfhlApiApplicationTests {

    private final BfhlServiceImpl bfhlService = new BfhlServiceImpl();

    @Test
    void testProcessData() {
        BfhlRequestDTO request = new BfhlRequestDTO();
        request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("339", response.getSum());
        assertTrue(response.getEvenNumbers().contains("334"));
        assertTrue(response.getOddNumbers().contains("1"));
        assertTrue(response.getAlphabets().contains("A"));
        assertTrue(response.getSpecialCharacters().contains("$"));
        assertEquals("Ra", response.getConcatString());
    }
}
