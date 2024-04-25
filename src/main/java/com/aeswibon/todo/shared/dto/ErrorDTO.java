package com.aeswibon.todo.shared.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO implements IDTO {
    String type;
    String reason;
}
