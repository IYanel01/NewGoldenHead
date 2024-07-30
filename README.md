NewGoldenHead is a plugin that introduces a special Golden Head item with unique effects. When consumed, this magical head grants the player regeneration and, optionally, a speed boost. The plugin is highly configurable, allowing server administrators to customize the duration and strength of the effects.

![Image Description](https://i.imgur.com/F7UmhzH.png)

 Features:
- Adds a new Golden Head item with a custom texture.
- Grants regeneration effect upon consumption.
- Optional speed boost effect, configurable via settings.
- Easy-to-use commands for giving Golden Heads to players and reloading configurations.
- Supports Minecraft versions from 1.7 to 1.21.

 Commands:
- `/newgoldenhead give [amount]` - Gives the player the specified amount of Golden Heads.
- `/newgoldenhead reload` - Reloads the plugin configuration.

Installation:
1. Download the NewGoldenHead plugin jar file.
2. Place the jar file in your server's `plugins` directory.
3. Restart your server to load the plugin.
4. Configure the plugin by editing the `config.yml` file in the `plugins/NewGoldenHead` directory.
5. Use the provided commands to give Golden Heads to players and enjoy!

Configuration:
The configuration file `config.yml` includes the following options:
- `healing-speed`: The speed of the regeneration effect.
- `healing-time`: The duration of the regeneration effect in seconds.
- `enable-speed-effect`: Enable or disable the speed boost effect.

 Example Configuration:
healing-speed: 1
healing-time: 10
enable-speed-effect: true
