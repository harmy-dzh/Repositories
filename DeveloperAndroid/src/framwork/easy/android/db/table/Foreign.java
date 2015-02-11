package framwork.easy.android.db.table;

import java.lang.reflect.Field;
import java.util.List;

import android.database.Cursor;
import android.util.Log;
import framwork.easy.android.config.Tags;
import framwork.easy.android.db.converter.ColumnConverter;
import framwork.easy.android.db.converter.ColumnConverterFactory;
import framwork.easy.android.db.sqlite.ColumnDbType;
import framwork.easy.android.db.sqlite.ForeignLazyLoader;
import framwork.easy.android.exception.DbException;

public class Foreign extends Column {

    private final String foreignColumnName;
    private final ColumnConverter foreignColumnConverter;

    /* package */ Foreign(Class<?> entityType, Field field) {
        super(entityType, field);

        foreignColumnName = ColumnUtils.getForeignColumnNameByField(field);
        Class<?> foreignColumnType =
                TableUtils.getColumnOrId(getForeignEntityType(), foreignColumnName).columnField.getType();
        foreignColumnConverter = ColumnConverterFactory.getColumnConverter(foreignColumnType);
    }

    public String getForeignColumnName() {
        return foreignColumnName;
    }

    public Class<?> getForeignEntityType() {
        return ColumnUtils.getForeignEntityType(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setValue2Entity(Object entity, Cursor cursor, int index) {
        Object fieldValue = foreignColumnConverter.getFieldValue(cursor, index);
        if (fieldValue == null) return;

        Object value = null;
        Class<?> columnType = columnField.getType();
        if (columnType.equals(ForeignLazyLoader.class)) {
            value = new ForeignLazyLoader(this, fieldValue);
        } else if (columnType.equals(List.class)) {
            try {
                value = new ForeignLazyLoader(this, fieldValue).getAllFromDb();
            } catch (DbException e) {
                Log.e(Tags.TAG_EXCEPTION,e.getMessage(), e);
            }
        } else {
            try {
                value = new ForeignLazyLoader(this, fieldValue).getFirstFromDb();
            } catch (DbException e) {
                Log.e(Tags.TAG_EXCEPTION,e.getMessage(), e);
            }
        }

        if (setMethod != null) {
            try {
                setMethod.invoke(entity, value);
            } catch (Throwable e) {
                Log.e(Tags.TAG_EXCEPTION,e.getMessage(), e);
            }
        } else {
            try {
                this.columnField.setAccessible(true);
                this.columnField.set(entity, value);
            } catch (Throwable e) {
                Log.e(Tags.TAG_EXCEPTION,e.getMessage(), e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getColumnValue(Object entity) {
        Object fieldValue = getFieldValue(entity);
        Object columnValue = null;

        if (fieldValue != null) {
            Class<?> columnType = columnField.getType();
            if (columnType.equals(ForeignLazyLoader.class)) {
                columnValue = ((ForeignLazyLoader) fieldValue).getColumnValue();
            } else if (columnType.equals(List.class)) {
                try {
                    List<?> foreignEntities = (List<?>) fieldValue;
                    if (foreignEntities.size() > 0) {

                        Class<?> foreignEntityType = ColumnUtils.getForeignEntityType(this);
                        Column column = TableUtils.getColumnOrId(foreignEntityType, foreignColumnName);
                        columnValue = column.getColumnValue(foreignEntities.get(0));

                        Table table = this.getTable();
                        if (table != null && columnValue == null && column instanceof Id) {
                            table.db.saveOrUpdateAll(foreignEntities);
                        }

                        columnValue = column.getColumnValue(foreignEntities.get(0));
                    }
                } catch (Throwable e) {
                    Log.e(Tags.TAG_EXCEPTION,e.getMessage(), e);
                }
            } else {
                try {
                    Column column = TableUtils.getColumnOrId(columnType, foreignColumnName);
                    columnValue = column.getColumnValue(fieldValue);

                    Table table = this.getTable();
                    if (table != null && columnValue == null && column instanceof Id) {
                        table.db.saveOrUpdate(fieldValue);
                    }

                    columnValue = column.getColumnValue(fieldValue);
                } catch (Throwable e) {
                    Log.e(Tags.TAG_EXCEPTION,e.getMessage(), e);
                }
            }
        }

        return columnValue;
    }

    @Override
    public ColumnDbType getColumnDbType() {
        return foreignColumnConverter.getColumnDbType();
    }

    /**
     * It always return null.
     *
     * @return null
     */
    @Override
    public Object getDefaultValue() {
        return null;
    }
}
