package dev.cobblesword.libraries.modules.serverstartup;

import dev.cobblesword.ctf.CaptureTheFlagPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Module
{
    private String name;

    private JavaPlugin plugin;

    public Module(String name, JavaPlugin plugin) {
        this.name = name;
        this.plugin = plugin;
    }

    protected abstract void onEnable();

    public void enable()
    {
        onEnable();
    }

    protected void registerEvents(Listener listener)
    {
        Bukkit.getPluginManager().registerEvents(listener, CaptureTheFlagPlugin.getInstance());
    }
}
