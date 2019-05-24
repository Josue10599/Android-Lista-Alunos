package com.fulltime.listaalunos.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class DataBaseMigration {
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE new_aluno (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "nome TEXT," +
                    "telefone TEXT," +
                    "email TEXT);");
            database.execSQL("INSERT INTO new_aluno (id, nome, telefone, email) " +
                    "SELECT id, nome, telefone, email FROM Aluno;");
            database.execSQL("DROP TABLE Aluno;");
            database.execSQL("ALTER TABLE new_aluno RENAME TO Aluno;");
        }
    };
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCriacao INTEGER;");
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `new_aluno` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefoneFixo` TEXT, " +
                    "`telefoneCelular` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoDeCriacao` INTEGER)");
            database.execSQL("INSERT INTO new_aluno (id, nome, telefoneCelular, email) " +
                    "SELECT id, nome, telefone, email FROM Aluno;");
            database.execSQL("DROP TABLE Aluno");
            database.execSQL("ALTER TABLE new_aluno RENAME TO Aluno");
        }
    };
    static final Migration[] MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5};
}