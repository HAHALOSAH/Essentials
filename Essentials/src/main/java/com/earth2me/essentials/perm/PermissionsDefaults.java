package com.earth2me.essentials.perm;

import com.earth2me.essentials.commands.Commandhat;
import com.google.common.collect.ImmutableMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;

public final class PermissionsDefaults {
    
    private PermissionsDefaults() {
    }
    
    public static void registerAllBackDefaults() {
        for (final World world : Bukkit.getWorlds()) {
            registerBackDefaultFor(world);
        }
    }
    
    public static void registerBackDefaultFor(final World w) {
        final String permName = "essentials.back.into." + w.getName();

        Permission p = Bukkit.getPluginManager().getPermission(permName);
        if (p == null) {
            p = new Permission(permName,
                "Allows access to /back when the destination location is within world " + w.getName(),
                PermissionDefault.TRUE);
            Bukkit.getPluginManager().addPermission(p);
        }
    }

    public static void registerAllHatDefaults() {
        final PluginManager pluginManager = Bukkit.getPluginManager();

        final Permission hatPreventPerm = pluginManager.getPermission(Commandhat.PREVENT_PERM_PREFIX + "*");
        if (hatPreventPerm == null) {
            final ImmutableMap.Builder<String, Boolean> preventChildren = ImmutableMap.builder();
            for (final Material mat : Material.values()) {
                final String matPreventPerm = Commandhat.PREVENT_PERM_PREFIX + mat.name().toLowerCase();
                preventChildren.put(matPreventPerm, true);
                pluginManager.addPermission(new Permission(matPreventPerm, "Prevent using " + mat + " as a type of hat.", PermissionDefault.FALSE));
            }
            pluginManager.addPermission(new Permission(Commandhat.PREVENT_PERM_PREFIX + "*", "Prevent all types of hats", PermissionDefault.FALSE, preventChildren.build()));
        }
        
        final Permission hatAllowPerm = pluginManager.getPermission(Commandhat.ALLOW_PERM_PREFIX + "*");
        if (hatAllowPerm == null) {
            final ImmutableMap.Builder<String, Boolean> allowChildren = ImmutableMap.builder();
            for (final Material mat : Material.values()) {
                final String matAllowPerm = Commandhat.ALLOW_PERM_PREFIX + mat.name().toLowerCase();
                allowChildren.put(matAllowPerm, true);
                pluginManager.addPermission(new Permission(matAllowPerm, "Allow using " + mat + " as a type of hat.", PermissionDefault.FALSE));
            }
            pluginManager.addPermission(new Permission(Commandhat.ALLOW_PERM_PREFIX + "*", "Allow all types of hats", PermissionDefault.FALSE, allowChildren.build()));
        }
    }
}
