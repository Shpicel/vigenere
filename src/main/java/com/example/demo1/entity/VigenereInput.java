package com.example.demo1.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VigenereInput {
    private String text;
    private String key;
    private String mode;
}
