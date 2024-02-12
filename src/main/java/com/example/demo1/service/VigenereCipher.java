package com.example.demo1.service;

import org.springframework.stereotype.Service;

@Service
public class VigenereCipher {
    private static final String ALPHABET = getAlphabet('А', 'я') + getAlphabet('!', 'z') + ' ';

    //"абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz"
    public String encrypt(String plaintext, String keyword) {
        StringBuilder ciphertext = new StringBuilder();
        int keywordIndex = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keywordChar = keyword.charAt(keywordIndex % keyword.length());
            int plainIndex = ALPHABET.indexOf(plainChar);
            int keywordCharIndex = ALPHABET.indexOf(keywordChar);

            if (plainIndex != -1) {
                char encryptedChar = ALPHABET.charAt((plainIndex + keywordCharIndex) % ALPHABET.length());
                ciphertext.append(encryptedChar);
                keywordIndex++;
            } else {
                ciphertext.append(plainChar);
            }
        }
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext, String keyword) {
        StringBuilder plaintext = new StringBuilder();
        int keywordIndex = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            char keywordChar = keyword.charAt(keywordIndex % keyword.length());
            int cipherIndex = ALPHABET.indexOf(cipherChar);
            int keywordCharIndex = ALPHABET.indexOf(keywordChar);

            if (cipherIndex != -1) {
                int decryptedIndex = (cipherIndex - keywordCharIndex) % ALPHABET.length();
                if (decryptedIndex < 0) {
                    decryptedIndex += ALPHABET.length();
                }
                char decryptedChar = ALPHABET.charAt(decryptedIndex);
                plaintext.append(decryptedChar);
                keywordIndex++;
            } else {
                plaintext.append(cipherChar);
            }
        }
        return plaintext.toString();
    }

    public static String getAlphabet(char start, char end) {
        StringBuilder alphabet = new StringBuilder();
        for (char letter = start; letter <= end; letter++) {
            alphabet.append(letter);
        }
        return alphabet.toString();
    }
}
