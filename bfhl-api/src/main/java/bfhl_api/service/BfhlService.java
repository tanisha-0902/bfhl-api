package bfhl_api.service;

import bfhl_api.dto.BfhlRequestDTO;
import bfhl_api.dto.BfhlResponseDTO;

public interface BfhlService {
    BfhlResponseDTO processData(BfhlRequestDTO request);
}