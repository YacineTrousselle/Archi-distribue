package fr.frcsbcn.soup.proxy;

import android.util.Log;

import com.zeroc.Ice.ObjectPrx;

import fr.frcsbcn.soup.MainActivity;

public abstract class AbstractProxy {
    static final String SERVER_IP = "10.0.2.2";

    public ObjectPrx getBaseProxy(String proxyName) {

        Log.d("AbstractProxy", "getBaseProxy: " + "Soup." + proxyName + ":default -h " + SERVER_IP + " -p 10000");

        return MainActivity.COMMUNICATOR.stringToProxy("Soup." + proxyName + ":tcp -h " + SERVER_IP + " -p 10000");
    }
}
