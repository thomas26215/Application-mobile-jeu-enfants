package com.example.miniprojet.Entités.User;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;

public class UserRepository {

    private UserDAO userDao;
    private List<User> allUsers;

    public UserRepository(Context context) {
        AppDatabase database = DatabaseClient.getInstance(context).getAppDatabase();
        userDao = database.userDao();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void getAllUsers(DataCallback<List<User>> callback) {
        new GetAllUsersAsyncTask(userDao, callback).execute();
    }

    public void getUserByEmail(String email, DataCallback<User> callback) {
        new GetUserByEmailAsyncTask(userDao, callback).execute(email);
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDao;

        private InsertUserAsyncTask(UserDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDao;

        private UpdateUserAsyncTask(UserDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDao;

        private DeleteUserAsyncTask(UserDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class GetAllUsersAsyncTask extends AsyncTask<Void, Void, List<User>> {
        private UserDAO userDao;
        private DataCallback<List<User>> callback;

        private GetAllUsersAsyncTask(UserDAO userDao, DataCallback<List<User>> callback) {
            this.userDao = userDao;
            this.callback = callback;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return userDao.getAll();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            callback.onDataLoaded(users);
        }
    }

    private static class GetUserByEmailAsyncTask extends AsyncTask<String, Void, User> {
        private UserDAO userDao;
        private DataCallback<User> callback;

        private GetUserByEmailAsyncTask(UserDAO userDao, DataCallback<User> callback) {
            this.userDao = userDao;
            this.callback = callback;
        }

        @Override
        protected User doInBackground(String... emails) {
            return userDao.getUserByEmail(emails[0]);
        }

        @Override
        protected void onPostExecute(User user) {
            callback.onDataLoaded(user);
        }
    }

    public interface DataCallback<T> {
        void onDataLoaded(T data);
    }
}
