package com.mgm.meettheteam;

import com.mgm.meettheteam.model.DataModel;
import com.mgm.meettheteam.model.DefaultDataModel;
import com.mgm.meettheteam.model.DevDataModel;

import android.app.Application;

/**
 * Application object to provide access to data model
 *
 * Created by michaelmaitlen on 2/12/18.
 */
public class App extends Application {

    private static App app;
    private DataModel dataModel;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App instance() {
        return app;
    }

    public DataModel getDataModel() {
        if (dataModel == null) {
            dataModel = new DefaultDataModel();
        }
        return dataModel;
    }
}
