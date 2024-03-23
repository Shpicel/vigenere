package com.example.demo1.entity;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DiffieHellmanInput {
    private String privateKeyXa;
    private String privateKeyXb;
    private String p;
    }

