/*
 * Copyright (C) 2013, 2014 - Gonçalo Baltazar <http://goncalomb.com>
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

package com.goncalomb.bukkit.nbteditor.nbt;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import com.goncalomb.bukkit.bkglib.reflect.NBTTagCompound;
import com.goncalomb.bukkit.bkglib.reflect.NBTUtils;
import com.goncalomb.bukkit.nbteditor.nbt.variable.NBTGenericVariableContainer;
import com.goncalomb.bukkit.nbteditor.nbt.variable.ShortVariable;

public class MinecartSpawnerNBT extends MinecartNBT {
	
	static {
		NBTGenericVariableContainer variables = new NBTGenericVariableContainer("MinecartSpawner");
		variables.add("count", new ShortVariable("SpawnCount", (short) 0));
		variables.add("range", new ShortVariable("SpawnRange", (short) 0));
		variables.add("delay", new ShortVariable("Delay", (short) 0));
		variables.add("min-delay", new ShortVariable("MinSpawnDelay", (short) 0));
		variables.add("max-delay", new ShortVariable("MaxSpawnDelay", (short) 0));
		variables.add("max-entities", new ShortVariable("MaxNearbyEntities", (short) 0));
		variables.add("player-range", new ShortVariable("RequiredPlayerRange", (short) 0));
		EntityNBTVariableManager.registerVariables(EntityType.MINECART_MOB_SPAWNER, variables);
	}
	
	public void MinecartNBT() {
		_data.setString("EntityId", "Pig");
	}
	
	public void copyFromSpawner(Block block) {
		NBTTagCompound data = NBTUtils.getTileEntityNBTData(block);
		data.remove("id");
		data.remove("x");
		data.remove("y");
		data.remove("z");
		_data.setString("EntityId", "Pig");
		_data.remove("SpawnData");
		_data.remove("SpawnPotentials");
		_data.merge(data);
	}
	
	public void copyToSpawner(Block block) {
		NBTTagCompound data = NBTUtils.getTileEntityNBTData(block);
		data.setString("EntityId", "Pig");
		data.remove("SpawnData");
		data.remove("SpawnPotentials");
		data.merge(_data);
		NBTUtils.setTileEntityNBTData(block, data);
	}
	
}