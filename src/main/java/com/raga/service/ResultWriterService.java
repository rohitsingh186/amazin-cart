package com.raga.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ResultWriterService {

  private final ObjectMapper objectMapper;

  @Autowired
  public ResultWriterService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  void writeResultIntoFile(Object result, String fileName) throws IOException {
    this.objectMapper.writeValue(new File(fileName), result);
  }
}
