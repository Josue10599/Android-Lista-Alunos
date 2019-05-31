package com.fulltime.listaalunos.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fulltime.listaalunos.model.Telefone;

import java.util.List;

@Dao
public interface TelefoneDao {
    @Query("SELECT * FROM Telefone WHERE idAluno = :alunoId AND numero != '' LIMIT 1")
    Telefone getTelefone(long alunoId);

    @Insert
    void salvarTelefone(Telefone... telefone);

    @Query("SELECT * FROM Telefone WHERE idAluno = :alunoId")
    List<Telefone> getTelefones(long alunoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualizaTelefone(Telefone... telefone);
}
