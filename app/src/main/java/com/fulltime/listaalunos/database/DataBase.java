package com.fulltime.listaalunos.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.fulltime.listaalunos.database.dao.RoomAlunoDao;
import com.fulltime.listaalunos.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract RoomAlunoDao getAlunoDao();
}
