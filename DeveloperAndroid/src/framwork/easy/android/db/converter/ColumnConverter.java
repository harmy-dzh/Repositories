package framwork.easy.android.db.converter;

import android.database.Cursor;
import framwork.easy.android.db.sqlite.ColumnDbType;

public interface ColumnConverter<T> {

    T getFieldValue(final Cursor cursor, int index);

    T getFieldValue(String fieldStringValue);

    Object fieldValue2ColumnValue(T fieldValue);

    ColumnDbType getColumnDbType();
}
