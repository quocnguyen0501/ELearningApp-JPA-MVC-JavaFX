package models;

public final class UserLogin {
    private UserModel userModel;
    private static UserLogin INSTANCE;

    private UserLogin() {}

    public static UserLogin getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserLogin();
        }
        return INSTANCE;
    }

    public static void setINSTANCE(UserLogin INSTANCE) {
        UserLogin.INSTANCE = INSTANCE;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
