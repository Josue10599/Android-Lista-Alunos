package com.fulltime.listaalunos.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.fulltime.listaalunos.model.TipoTelefone;

import static com.fulltime.listaalunos.model.TipoTelefone.TELEFONE_FIXO;

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
    private static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_new` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT," +
                    " `email` TEXT," +
                    " `momentoDeCriacao` INTEGER)");
            database.execSQL("INSERT INTO Aluno_new (id, nome, email, momentoDeCriacao) " +
                    "SELECT id, nome, email, momentoDeCriacao FROM Aluno;");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER NOT NULL," +
                    " `numero` TEXT," +
                    " `tipo` TEXT," +
                    " `idAluno` INTEGER NOT NULL, PRIMARY KEY(`id`))");
            database.execSQL("INSERT INTO Telefone (numero, idAluno) " +
                    "SELECT telefoneFixo, id FROM Aluno");
            database.execSQL("UPDATE Telefone SET tipo=?", new TipoTelefone[]{TELEFONE_FIXO});
            database.execSQL("DROP TABLE Aluno");
            database.execSQL("ALTER TABLE Aluno_new RENAME TO Aluno");
        }
    };
    static final Migration[] MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4,
            MIGRATION_4_5, MIGRATION_5_6};
}