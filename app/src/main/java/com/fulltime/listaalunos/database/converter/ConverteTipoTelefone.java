package com.fulltime.listaalunos.database.converter;

import androidx.room.TypeConverter;

import com.fulltime.listaalunos.model.TipoTelefone;

public class ConverteTipoTelefone {

    @TypeConverter
    public String toString(TipoTelefone tipoTelefone) {
        return tipoTelefone.name();
    }

    @TypeConverter
    public TipoTelefone toTipoTelefone(String s) {
        if (s != null) {
            return TipoTelefone.valueOf(s);
        }
        return TipoTelefone.TELEFONE_FIXO;
    }
}
