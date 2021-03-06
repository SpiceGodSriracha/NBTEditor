/*
 * Copyright (C) 2013-2016 Gonçalo Baltazar <me@goncalomb.com>
 *
 * This file is part of NBTEditor.
 *
 * NBTEditor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NBTEditor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NBTEditor.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.goncalomb.bukkit.nbteditor.commands;

import java.awt.Color;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.goncalomb.bukkit.mylib.command.MyCommand;
import com.goncalomb.bukkit.mylib.command.MyCommandException;

public class CommandNBTArmor extends MyCommand {

	public CommandNBTArmor() {
		super("nbtarmor", "nbta");
	}

	@Command(args = "", type = CommandType.PLAYER_ONLY, maxargs = 1, usage = "<color>")
	public boolean potionCommand(CommandSender sender, String[] args) throws MyCommandException {
		if (args.length > 0) {
			HandItemWrapper.LeatherArmor item = new HandItemWrapper.LeatherArmor((Player) sender);
			if (!args[0].startsWith("#")) {
				args[0] = "#" + args[0];
			}
			if (args[0].length() == 7) {
				try {
					Color color = Color.decode(args[0]);
					item.meta.setColor(org.bukkit.Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue()));
					item.save();
					sender.sendMessage("§aColor set.");
					return true;
				} catch (NumberFormatException e) {
				}
			}
			sender.sendMessage("§cInvalid color format.");
		}
		sender.sendMessage("§eThe color as the RGB format, #FFFFFF (e.g. #FF0000 for red).");
		return false;
	}
}
