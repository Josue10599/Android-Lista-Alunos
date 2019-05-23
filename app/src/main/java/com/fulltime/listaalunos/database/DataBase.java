package com.fulltime.listaalunos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fulltime.listaalunos.database.dao.AlunoDao;
import com.fulltime.listaalunos.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {

    private static final String AGENDA_DB = "agenda.db";

    public static DataBase getInstance(Context context) {
        return Room.databaseBuilder(context, DataBase.class, AGENDA_DB)
                .allowMainThreadQueries()
                .build();
    }

    public abstract AlunoDao getAlunoDao();
}
