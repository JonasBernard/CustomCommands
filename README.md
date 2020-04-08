# CustomCommands

## Introduction
Let's  say you have your own [Spigot Server](https://spigotmc.org) running and you have a bunch
of plugins installed. You then may want to add command aliases to some commands to have easy access to them.
I use [Multiverse](https://github.com/Multiverse/Multiverse-Core/) as an example:

### A simple example
Multiverse is a plugin to manage multiple worlds on your server. In Multiverse there is a command
for teleporting a player to another world: `/mv tp lobby`. This requires to first generate a world namend **lobby**
 and add it to Multiverse. Read more about it on [their wiki page](https://github.com/Multiverse/Multiverse-Core/wiki/).
Now imagine you want to add the command `/lobby` to my server and configure it, so that the player is teleported
to the lobby. This is where *CustomCommands* comes into play. Therefore the `config.yml` in the plugin
has to contain the following:

```yml
commands:
  teleport_to_lobby:
    alias: "lobby"
    command: "mv tp lobby"
    description: "Teleport back to the lobby"
```
After you reload or restart your Server, the plugin adds the command and everybody can execute it.
*teleport_to_lobby* in this case just represents the name of your custom command. It can be anything without
spaces.

## Installation
In the `bin/` folder you find the jar file for the plugin. Alternatively you can compile the code yourself
if you know how this works. At this point, I will not give an explanation on how to manage that.

Then simply put the jar file into your `plugins` folder on the server. Reload your server and boom.
There you have it.

### Versions
The code is complied against the Spigot Server for Minecraft 1.15.2 and Java SDK 11.
I just tested it with that version yet, you can test is with other versions if you want to.

## What is planned
As you can see in the code, I just started programming.
    -[] The next thing that I might add is, that the executing user needs to have given permissions.

## Contribute
Feel free to fork and extend the project and then create a pull request.
I'll then decide, weather this fits to my idea of the project. You can implement
the features under "What is planned" or bring in your own ideas.
