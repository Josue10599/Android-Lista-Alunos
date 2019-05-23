package com.fulltime.listaalunos.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.fulltime.listaalunos.model.Aluno;

@Dao
public interface RoomAlunoDao {
    @Insert
    void salva(Aluno aluno);
}
