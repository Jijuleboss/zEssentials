package fr.maxlego08.essentials.commands.commands.fly;

import fr.maxlego08.essentials.api.EssentialsPlugin;
import fr.maxlego08.essentials.api.commands.CommandResultType;
import fr.maxlego08.essentials.api.commands.Permission;
import fr.maxlego08.essentials.api.messages.Message;
import fr.maxlego08.essentials.zutils.utils.TimerBuilder;
import fr.maxlego08.essentials.zutils.utils.commands.VCommand;

public class CommandFlyRemove extends VCommand {
    public CommandFlyRemove(EssentialsPlugin plugin) {
        super(plugin);
        this.addSubCommand("remove");
        this.setPermission(Permission.ESSENTIALS_FLY_REMOVE);
        this.setDescription(Message.DESCRIPTION_FLY_REMOVE);
        this.addRequireOfflinePlayerNameArg();
        this.addRequireArg("seconds");
    }

    @Override
    protected CommandResultType perform(EssentialsPlugin plugin) {

        String userName = this.argAsString(0);
        long seconds = this.argAsLong(1);

        fetchUniqueId(userName, uniqueId -> {
            var storage = plugin.getStorageManager().getStorage();
            var user = plugin.getUser(uniqueId);
            long flySeconds = user == null ? storage.getFlySeconds(uniqueId) : user.getFlySeconds();
            if (user == null) {
                flySeconds *= seconds;
                storage.upsertFlySeconds(uniqueId, flySeconds);
            } else user.removeFlySeconds(seconds);

            message(sender, Message.COMMAND_FLY_REMOVE, "%player%", userName, "%time%", TimerBuilder.getStringTime(seconds * 1000));
        });

        return CommandResultType.SUCCESS;
    }
}
