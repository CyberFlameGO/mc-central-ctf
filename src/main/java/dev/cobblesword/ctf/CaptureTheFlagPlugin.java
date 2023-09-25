package dev.cobblesword.ctf;

import dev.cobblesword.ctf.data.playerdata.PlayerDataManager;
import dev.cobblesword.ctf.database.Database;
import dev.cobblesword.ctf.database.DatabaseConfig;
import dev.cobblesword.ctf.game.Game;
import dev.cobblesword.ctf.lobby.Lobby;
import dev.cobblesword.ctf.data.playerdata.database.PlayerDataRepository;
import dev.cobblesword.ctf.scoreboard.ScoreboardAdapter;
import dev.cobblesword.libraries.common.config.ConfigManager;
import dev.cobblesword.libraries.common.world.Worlds;
import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class CaptureTheFlagPlugin extends JavaPlugin
{
    private static JavaPlugin instance;
    public static JavaPlugin getInstance()
    {
        return instance;
    }

    private static Database database;

    @Override
    public void onEnable()
    {
        instance = this;

        ConfigManager configManager = new ConfigManager(this, this,"dev.cobblesword.ctf");

        World lobbyWorld = Bukkit.getWorld("lobby");
        Worlds.initStaticWorld(lobbyWorld, false);

        Lobby lobby = new Lobby(this, lobbyWorld);

        Game game = new Game(this);

        lobby.setGame(game);

        Assemble assemble = new Assemble(this, new ScoreboardAdapter(game));
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.MODERN);

        database = new Database(this, DatabaseConfig.HOST,
            Integer.parseInt(DatabaseConfig.PORT),
            DatabaseConfig.USERNAME,
            DatabaseConfig.PASSWORD,
            DatabaseConfig.DATABASE);

        PlayerDataRepository playerDataRepository = new PlayerDataRepository(database);

        database.done();

        PlayerDataManager playerDataManager = new PlayerDataManager(this, playerDataRepository);
    }

    public static Database getMongoDatabase() {
        return database;
    }

    @Override
    public void onDisable()
    {
        System.out.println("Bye world");
    }
}
