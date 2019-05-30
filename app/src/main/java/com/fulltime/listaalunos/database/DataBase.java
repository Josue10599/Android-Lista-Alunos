package com.fulltime.listaalunos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.fulltime.listaalunos.database.converter.ConverteCalendar;
import com.fulltime.listaalunos.database.converter.ConverteTipoTelefone;
import com.fulltime.listaalunos.database.dao.AlunoDao;
import com.fulltime.listaalunos.database.dao.TelefoneDao;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConverteCalendar.class, ConverteTipoTelefone.class})
public abstract class DataBase extends RoomDatabase {

    private static final String AGENDA_DB = "agenda.db";
    private static DataBase instance;

    public abstract AlunoDao getAlunoDao();

    public abstract TelefoneDao getTelefoneDao();
    public static DataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DataBase.class, AGENDA_DB)
                    .addMigrations(DataBaseMigration.MIGRATIONS)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
