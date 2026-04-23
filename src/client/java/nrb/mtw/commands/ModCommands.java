package nrb.mtw.commands;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import nrb.mtw.UIEffects;
import nrb.mtw.config.ConfigManager;

public class ModCommands {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("mtw").executes(ModCommands::commandToggleWarning)
                    .then(ClientCommandManager.literal("enable").executes(ModCommands::commandEnableWarning))
                    .then(ClientCommandManager.literal("disable").executes(ModCommands::commandDisableWarning)));
        });
    }

    private static int commandToggleWarning(CommandContext<FabricClientCommandSource> fabricClientCommandSourceCommandContext) {
        ConfigManager.toggleWarning();
        UIEffects.soundSwitch(ConfigManager.CONFIG.enableWarning);
        UIEffects.messageSwitch(ConfigManager.CONFIG.enableWarning);
        return 1;
    }

    private static int commandDisableWarning(CommandContext<FabricClientCommandSource> fabricClientCommandSourceCommandContext) {
        if (ConfigManager.CONFIG.enableWarning) {
            ConfigManager.toggleWarning();
            UIEffects.soundSwitch(false);
            UIEffects.messageSwitch(false);
        } else UIEffects.message("already disabled!");
        return 1;
    }

    private static int commandEnableWarning(CommandContext<FabricClientCommandSource> fabricClientCommandSourceCommandContext) {
        if (!ConfigManager.CONFIG.enableWarning) {
            ConfigManager.toggleWarning();
            UIEffects.soundSwitch(true);
            UIEffects.messageSwitch(true);
        } else UIEffects.message("already enabled!");
        return 1;
    }
}
