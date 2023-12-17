package org.example.transposition.validators;

import org.example.transposition.models.Note;

import java.util.List;

public class NoteValidator {
    public static final Integer MAX_OCTAVE_NUMBER = 5;
    public static final Integer MIN_OCTAVE_NUMBER = -3;
    public static final Integer MAX_NOTE_NUMBER = 12;
    public static final Integer MIN_NOTE_NUMBER = 1;

    public static void validateNotes(List<Note> notes){
        notes.forEach(NoteValidator::validateNote);
    }

    public static void validateNote(Note note) {
        if (note.getOctaveNumber() < MIN_OCTAVE_NUMBER || note.getOctaveNumber() > MAX_OCTAVE_NUMBER) {
            throw new RuntimeException("Invalid octave number");
        }

        if (note.getNoteNumber() < MIN_NOTE_NUMBER || note.getNoteNumber() > MAX_NOTE_NUMBER) {
            throw new RuntimeException("Invalid note number");
        }

        if (MIN_NOTE_NUMBER.equals(note.getOctaveNumber()) && !isNoteInFirstPartialOctave(note)){
            throw new RuntimeException("Invalid note number for first partial octave");
        }

        if (MIN_NOTE_NUMBER.equals(note.getOctaveNumber()) && note.getNoteNumber() != 1){
            throw new RuntimeException("Invalid note number for last partial octave");
        }
    }

    private static boolean isNoteInFirstPartialOctave(Note note){
        return MIN_NOTE_NUMBER.equals(note.getOctaveNumber()) && note.getNoteNumber() >= 10 && note.getNoteNumber() <= 12;
    }
}
