# My-Worlds-Extra
`MyWorldsExtra` is a plugin designed to extend some features of the `MyWorlds` plugin.

### Dependencies
- [MyWorlds](https://www.spigotmc.org/resources/myworlds.39594/)
- [BKCommonLib](https://www.spigotmc.org/resources/bkcommonlib.39590/) - Required by `MyWorlds`

### Features
- **Automatic World Loading**: When a player uses the `/warp` command to teleport to a new world, the world will be automatically loaded if it's not already loaded by the `MyWorlds` plugin.
- **Automatic World Unloading**: If no players are present in a world and it’s not the default Minecraft world, the world will be automatically unloaded after players leave.

## Commands
- `/warp <world_name>`:
  - Teleports the player to the specified world. 
  - This command can be used as a replacement for the `/tpp <world_name>` command from the `MyWorlds` plugin.
