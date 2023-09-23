package dev.cobblesword.ctf.game.stats;

import dev.cobblesword.ctf.game.TeamType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@Data
public class PlayerGameStats
{
    private int id;
    private int playerId;
    private TeamType teamType;

    private final String playerName;
    private final UUID playerUUID;

    private int kills = 0;
    private int deaths = 0;

    public void addKill()
    {
        this.kills++;
    }

    public void addDeath()
    {
        this.deaths++;
    }
}
