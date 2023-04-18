package com.group.textgame.repo;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.group.textgame.model.Enemy;
import com.group.textgame.model.Object;
import com.group.textgame.model.Rooms;
import java.util.List;

public class ObjectRepository {

    private static ObjectRepository objectRepo;
    private final ObjectDao objectDao;

    private final ObjectDatabase database;

    public static ObjectRepository getInstance(Context context) {
        if (objectRepo == null) {
            objectRepo = new ObjectRepository(context);
        }
        return objectRepo;
    }

    private ObjectRepository(Context context) {
        database = Room.databaseBuilder(context, ObjectDatabase.class, "objects.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        objectDao = database.objectDao();
    }

    public void resetData(){
        database.clearAllTables();
        database.close();
    }

    public void addObject(Object object) {
        long objectId = objectDao.addObject(object);
        object.setID(objectId);
    }

    public Object getObject(long objectId) {
        return objectDao.getObject(objectId);
    }

    public List<String> getObjects(String name) {
        return objectDao.getObjects(name);
    }

    public void deleteObject(Object object) {
        objectDao.deleteObject(object);
    }

    public void updateObject(Object object) {
        objectDao.updateObject(object);
    }
}
