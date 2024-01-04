package com.notesManager.notesManager.service;

import com.notesManager.notesManager.dto.*;
import com.notesManager.notesManager.entity.Notes;

import java.util.List;

public interface NotesManager {
    public String store(String token, RequestData data);
    public List<RequestNotesData> listOfNotes(String token);
    public RequestNotesData getNoteById(String token, long id);
    public Notes updateNoteById(String token,String id, RequestNoteData requestNoteData);
    public String deleteNoteById(String token, String id);
    public ResponseNote shareNoteWithUser(String token, long id);
    public List<Notes> searchBasedOnKeyWord(String token, String keyword);
}
