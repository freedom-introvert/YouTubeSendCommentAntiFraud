package icu.freedomintrovert.YTSendCommAntiFraud.xposed;

public abstract class BaseHook {
    public abstract void startHook(int appVersionCode, ClassLoader classLoader) throws ClassNotFoundException;
}
