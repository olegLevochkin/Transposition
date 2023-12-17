package org.example.transposition.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Note {
    private final Integer octaveNumber;
    private final Integer noteNumber;

    @Override
    public String toString() {
        return "[" + octaveNumber + "," + noteNumber + ']';
    }
}
