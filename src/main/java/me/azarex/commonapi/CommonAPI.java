package me.azarex.commonapi;

public class CommonAPI {

    private static CommonAPI instance;

    public static CommonAPI getInstance() {
        if (instance == null) {
            instance = new CommonAPI();
        }
        return instance;
    }

}
