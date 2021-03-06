package com.goo.musicdb.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SPECIAL".
*/
public class SpecialDao extends AbstractDao<Special, Long> {

    public static final String TABLENAME = "SPECIAL";

    /**
     * Properties of entity Special.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property SpecialId = new Property(0, Long.class, "specialId", true, "_id");
        public final static Property IssueTime = new Property(1, java.util.Date.class, "issueTime", false, "ISSUE_TIME");
        public final static Property SpecialIntroduction = new Property(2, String.class, "specialIntroduction", false, "SPECIAL_INTRODUCTION");
    }

    private DaoSession daoSession;

    private Query<Special> user_LikedSpecialsQuery;

    public SpecialDao(DaoConfig config) {
        super(config);
    }
    
    public SpecialDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SPECIAL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: specialId
                "\"ISSUE_TIME\" INTEGER," + // 1: issueTime
                "\"SPECIAL_INTRODUCTION\" TEXT);"); // 2: specialIntroduction
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SPECIAL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Special entity) {
        stmt.clearBindings();
 
        Long specialId = entity.getSpecialId();
        if (specialId != null) {
            stmt.bindLong(1, specialId);
        }
 
        java.util.Date issueTime = entity.getIssueTime();
        if (issueTime != null) {
            stmt.bindLong(2, issueTime.getTime());
        }
 
        String specialIntroduction = entity.getSpecialIntroduction();
        if (specialIntroduction != null) {
            stmt.bindString(3, specialIntroduction);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Special entity) {
        stmt.clearBindings();
 
        Long specialId = entity.getSpecialId();
        if (specialId != null) {
            stmt.bindLong(1, specialId);
        }
 
        java.util.Date issueTime = entity.getIssueTime();
        if (issueTime != null) {
            stmt.bindLong(2, issueTime.getTime());
        }
 
        String specialIntroduction = entity.getSpecialIntroduction();
        if (specialIntroduction != null) {
            stmt.bindString(3, specialIntroduction);
        }
    }

    @Override
    protected final void attachEntity(Special entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Special readEntity(Cursor cursor, int offset) {
        Special entity = new Special( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // specialId
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // issueTime
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // specialIntroduction
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Special entity, int offset) {
        entity.setSpecialId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIssueTime(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setSpecialIntroduction(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Special entity, long rowId) {
        entity.setSpecialId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Special entity) {
        if(entity != null) {
            return entity.getSpecialId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Special entity) {
        return entity.getSpecialId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "likedSpecials" to-many relationship of User. */
    public List<Special> _queryUser_LikedSpecials(Long userId) {
        synchronized (this) {
            if (user_LikedSpecialsQuery == null) {
                QueryBuilder<Special> queryBuilder = queryBuilder();
                queryBuilder.join(LikeSpecial.class, LikeSpecialDao.Properties.SpecialId)
                    .where(LikeSpecialDao.Properties.UserId.eq(userId));
                user_LikedSpecialsQuery = queryBuilder.build();
            }
        }
        Query<Special> query = user_LikedSpecialsQuery.forCurrentThread();
        query.setParameter(0, userId);
        return query.list();
    }

}
