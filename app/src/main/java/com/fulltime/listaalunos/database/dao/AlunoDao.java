package com.fulltime.listaalunos.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fulltime.listaalunos.model.Aluno;

import java.util.List;

@Dao
public interface AlunoDao {
    @Insert
    void salva(Aluno aluno);

    @Query("SELECT * FROM Aluno")
    List<Aluno> getAlunos();

    @Delete
    void remove(Aluno aluno);

    @Update
    void edita(Aluno aluno);
}
