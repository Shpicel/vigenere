package com.example.demo1.entity;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DiffieHellmanInput {
    private String bitLen;
    private String iteration;
    private String privateKeyXa;
    private String privateKeyXb;
    private String primeNum;
    private String rootPrimeNum;
    private String inputRoot;
    private String lowerBound;
    private String upperBound;
    }

