/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client/).
 * Copyright (c) 2020 Meteor Development.
 */

package minegame159.meteorclient.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.commands.Command;
import minegame159.meteorclient.commands.arguments.ModuleArgumentType;
import minegame159.meteorclient.modules.Module;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.utils.Chat;
import net.minecraft.command.CommandSource;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class Settings extends Command {
    public Settings() {
        super("settings", "Displays all settings of specified module.");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(argument("module", ModuleArgumentType.module()).executes(context -> {
            Module module = context.getArgument("module", Module.class);

            Chat.info("(highlight)%s(default):", module.title);
            for (SettingGroup sg : module.settings) {
                for (Setting<?> setting : sg) {
                    Chat.info("  Usage of (highlight)%s (default)(%s) is (highlight)%s(default).", setting.name, setting.get().toString(), setting.getUsage());
                }
            }

            return SINGLE_SUCCESS;
        }));
    }
}
