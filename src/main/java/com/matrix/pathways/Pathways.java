package com.matrix.pathways;

import com.matrix.pathways.commands.CommandRegister;
import com.matrix.pathways.entity.ModProfession;
import com.matrix.pathways.items.ItemRegister;
import com.matrix.pathways.world.dimension.ForsakenLandDimension;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.matrix.pathways.entity.Trades;

public class Pathways implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "pathways";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
//		ModProfession.init();
		ForsakenLandDimension.register();
		CommandRegister.register();
		ItemRegister.register();
		Trades.init();

		LOGGER.info("福生玄黄天尊");
	}
}
