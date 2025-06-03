# Minecraft Language Learning Mod

A Minecraft mod that helps you learn Spanish by displaying both English and Spanish names for blocks, items, and entities when you point at them and press F.

## 🎮 Features

- **Point & Learn**: Point at any block, item, or entity in Minecraft
- **F Key Activation**: Press F to display both English and Spanish names  
- **Beautiful UI**: Shows translations with flag emojis (🇺🇸/🇪🇸) and fade effects
- **Audio Pronunciation**: Ready for audio files (infrastructure complete)
- **Comprehensive Coverage**: Works with 1000+ items from your language files

## 🚀 Quick Start

### Prerequisites
- **Java 17** (required for Minecraft 1.20.1)
- **Minecraft Forge 1.20.1-47.2.0** or higher

### Installation Methods

#### Option 1: Development Testing
```bash
# Install Java 17
sudo apt install openjdk-17-jdk

# Install Gradle  
sudo apt install gradle

# Run development client
cd languagelearningmod
./gradlew runClient
```

#### Option 2: Regular Minecraft Installation
```bash
# Build the mod
./gradlew build

# Copy JAR to mods folder
cp build/libs/languagelearningmod-1.0.0.jar ~/.minecraft/mods/

# Launch Minecraft with Forge profile
```

## 🎯 How to Use

1. **Start Minecraft** with Forge and the mod installed
2. **Point at any object** (block, item, entity) 
3. **Press F key** to see translations
4. **Enjoy learning!** English and Spanish names appear with flags

## 🔧 Building from Source

```bash
# Clean build
./gradlew clean

# Build JAR file
./gradlew build

# Development testing
./gradlew runClient
```

Built JAR will be in `build/libs/languagelearningmod-1.0.0.jar`

## 🔊 Adding Audio Files (Optional)

The audio system is ready! To add pronunciation:

1. **Create/source .ogg audio files**
2. **Name them by translation key**:
   - `block_minecraft_stone.ogg` → Stone block
   - `item_minecraft_diamond_sword.ogg` → Diamond sword
   - `entity_minecraft_cow.ogg` → Cow
3. **Place in**: `src/main/resources/assets/languagelearningmod/sounds/`
4. **Rebuild mod**: `./gradlew build`

## 📝 Language Coverage

The mod includes comprehensive translations for:
- ✅ **All Minecraft blocks** (stone, wood, ores, etc.)
- ✅ **All items** (tools, weapons, food, etc.) 
- ✅ **All entities** (mobs, animals, NPCs)
- ✅ **UI elements** (menus, buttons, text)
- ✅ **Advancements** (achievement descriptions)
- ✅ **1000+ total translations**

## ⌨️ Key Bindings

- **F Key**: Show translation (configurable in Minecraft controls)
- Key binding appears as "Language Learning" → "Show Translation"

## 🗂️ Project Structure

```
languagelearningmod/
├── build.gradle              # Build configuration
├── README.md                 # This file  
├── CLAUDE.md                 # Development notes
└── src/main/
    ├── java/                 # Java source code
    │   └── com/languagelearning/mod/
    │       ├── LanguageLearningMod.java    # Main mod class
    │       ├── LanguageManager.java        # JSON loading
    │       ├── RaycastHandler.java         # Target detection
    │       ├── KeyInputHandler.java        # F key handling  
    │       ├── TranslationOverlay.java     # GUI display
    │       ├── AudioManager.java           # Audio system
    │       └── ModSounds.java              # Sound registration
    └── resources/
        ├── META-INF/mods.toml              # Mod metadata
        └── assets/languagelearningmod/
            ├── lang/                       # Translation files
            │   ├── en_us.json             # English (comprehensive)
            │   ├── es_mx.json             # Spanish (comprehensive)  
            │   ├── keys_en_us.json        # Key bindings (EN)
            │   └── keys_es_mx.json        # Key bindings (ES)
            ├── sounds.json                 # Audio definitions
            └── sounds/                     # Audio files (empty, ready)
```

## 🐛 Troubleshooting

### Build Issues (Known Problems)

#### Network Timeout Errors
**Problem**: `./gradlew build` fails with connection timeouts
```
Could not GET 'https://maven.minecraft.net/...'
Connect to maven.minecraft.net:443 failed: Connect timed out
```

**Solutions**:
1. **Use development client** (recommended): `./gradlew runClient`
2. **Try offline build**: `./gradlew build --offline` (after online setup)
3. **Network troubleshooting**:
   - Check firewall/antivirus blocking Maven repositories
   - Try different network (mobile hotspot, VPN)
   - Verify internet connectivity to maven.minecraft.net

#### Java Installation Issues
**Problem**: "Invalid Java installation found at '/usr/lib/jvm/openjdk-17'"

**Solution**: Ensure Java 17 is properly installed
```bash
sudo apt install openjdk-17-jdk
java -version  # Should show version 17
```

### Runtime Issues

#### Mod won't load
- Check you have **Java 17** installed
- Verify **Forge 1.20.1** is properly installed
- Make sure JAR is in the correct `mods` folder

#### F key doesn't work  
- Check key binding in Minecraft Controls settings
- Look for "Language Learning" → "Show Translation"

#### No translations showing
- Ensure you're pointing directly at blocks/items/entities
- Check the console/logs for any error messages

### Current Status
- ✅ **Source code**: Complete and functional
- ❌ **JAR build**: Blocked by network connectivity issues
- ✅ **Development testing**: Working via `./gradlew runClient`
- ✅ **Git repository**: https://github.com/Aufco/languagelearningmod

## 📈 Future Enhancements

- 🎯 **Progress Tracking**: Track learned words
- 🧠 **Quiz Mode**: Interactive learning challenges  
- 🌍 **More Languages**: French, German, etc.
- ⚙️ **Settings GUI**: In-game configuration
- 📊 **Statistics**: Learning progress analytics

## 📄 License

MIT License - Feel free to use and modify!

---

**Ready to learn Spanish while playing Minecraft!** 🎮🇪🇸