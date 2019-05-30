package com.fulltime.listaalunos.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.fulltime.listaalunos.model.Telefone;

@Dao
public interface TelefoneDao {
    @Query("SELECT * FROM Telefone WHERE idAluno = :alunoId LIMIT 1")
    Telefone getTelefone(long alunoId);

    @Insert
    void salvarTelefone(Telefone... telefone);
}
