package com.fusenetworks.fuse.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class SignPatch : Listener {

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerInteract(e: PlayerInteractEvent) {
        if (e.action == Action.RIGHT_CLICK_BLOCK) {
            when (e.clickedBlock.type) {
                Material.SIGN, Material.WALL_SIGN, Material.SIGN_POST -> {
                    e.isCancelled = true
                }
            }
        }
    }
}