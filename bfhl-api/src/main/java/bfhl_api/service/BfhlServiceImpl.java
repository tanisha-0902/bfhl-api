package bfhl_api.service;

import bfhl_api.dto.BfhlRequestDTO;
import bfhl_api.dto.BfhlResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public BfhlResponseDTO processData(BfhlRequestDTO request) {
        BfhlResponseDTO response = new BfhlResponseDTO();

        // Hardcoded student metadata as requested
        response.setUserId("Tanisha"); // Make sure your full name matches your records
        response.setEmail("tanisha2383.be23@chitkara.edu.in");
        response.setRollNumber("2310992383");

        if (request == null || request.getData() == null) {
            response.setSuccess(false);
            return response;
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();
        int totalSum = 0;
        StringBuilder alphaConcatRaw = new StringBuilder();

        for (String item : request.getData()) {
            if (item == null) continue;

            // Check if it is a number
            if (item.matches("-?\\d+")) {
                int num = Integer.parseInt(item);
                totalSum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            }
            // Check if it is purely alphabetic
            else if (item.matches("[a-zA-Z]+")) {
                alphabets.add(item.toUpperCase());
                alphaConcatRaw.append(item);
            }
            // Otherwise, it's a special character
            else {
                specialChars.add(item);
            }
        }

        // Handle Requirement 10: Concatenation in reverse order with alternating caps
        String reversedRaw = alphaConcatRaw.reverse().toString();
        StringBuilder alternatingCapsStr = new StringBuilder();
        for (int i = 0; i < reversedRaw.length(); i++) {
            char ch = reversedRaw.charAt(i);
            if (i % 2 == 0) {
                alternatingCapsStr.append(Character.toUpperCase(ch));
            } else {
                alternatingCapsStr.append(Character.toLowerCase(ch));
            }
        }

        // Populate Response Object
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialChars);
        response.setSum(String.valueOf(totalSum));
        response.setConcatString(alternatingCapsStr.toString());
        response.setSuccess(true);

        return response;
    }
}