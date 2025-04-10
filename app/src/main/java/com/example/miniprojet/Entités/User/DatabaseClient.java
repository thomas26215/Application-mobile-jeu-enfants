package com.example.miniprojet.Entités.User;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseClient {

    private static DatabaseClient instance;
    private AppDatabase appDatabase;

    private DatabaseClient(final Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "UserDatabase")
                .addCallback(roomDatabaseCallback)
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            db.execSQL("INSERT INTO users (name, email, password) VALUES('Admin', 'admin@example.com', 'admin123');");
        }
    };
}
