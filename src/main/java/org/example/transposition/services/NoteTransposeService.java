package org.example.transposition.services;

import org.example.transposition.models.Note;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.transposition.validators.NoteValidator.validateNotes;

public class NoteTransposeService {

    public static String transposeNotes(String input, Integer semitones) {
        List<Note> initialNotes = fromJson(input);
        validateNotes(initialNotes);
        List<Note> transposedNotes = initialNotes.stream()
                .map(n -> transpose(n, semitones))
                .toList();
        validateNotes(transposedNotes);
        return toJson(transposedNotes);
    }

    private static List<Note> fromJson(String json) {
        return Arrays.stream(json.split("[\\[\\]]"))
                .filter(NoteTransposeService::filterNotes)
                .map(NoteTransposeService::mapToNote)
                .collect(Collectors.toList());
    }

    private static String toJson(List<Note> list) {
        String[] array = list.stream().map(Note::toString).toArray(String[]::new);
        return "[" + String.join(",", array) + "]";
    }

    private static boolean filterNotes(String s) {
        return !s.isEmpty() && !s.equals(",");
    }

    private static Note mapToNote(String note) {
        String[] noteParts = note.split(",");
        return new Note(Integer.valueOf(noteParts[0]), Integer.valueOf(noteParts[1]));
    }

    private static Note transpose(Note note, Integer semitones) {
        int noteInNumberView = note.getOctaveNumber() * 12 + note.getNoteNumber() + semitones;
        return new Note(noteInNumberView / 12, noteInNumberView % 12);
    }
}
