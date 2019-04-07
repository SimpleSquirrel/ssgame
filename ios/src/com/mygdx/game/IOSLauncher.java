package com.mygdx.game;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
<<<<<<< HEAD
=======
>>>>>>> 9300740b22894359a395e197f8c5295c4c970de7

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
<<<<<<< HEAD
        return new IOSApplication(new MyGame(), config);
=======
        return new IOSApplication(new MainClass(), config);
>>>>>>> 9300740b22894359a395e197f8c5295c4c970de7
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}