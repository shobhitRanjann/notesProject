package com.notesManager.notesManager.repository;

import com.notesManager.notesManager.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepo extends JpaRepository<Notes,Long> {
    @Query("SELECT n FROM Notes n WHERE CONCAT(n.id, n.title, n.description, n.userId, n.date) LIKE %?1%")
    public List<Notes> search(String keyword);
}
