# ACustom 4 Vic's — Fabric 1.21.1

Client-side companion mod for Vic's Point Blank 2.2.0.

## Features
- Weapon-only first-person transform controls: X / Y / Z and scale.
- Separate FOV value while a Vic's Point Blank `GunItem` is held.
- Settings screen opened with **O**.
- Saves settings to `.minecraft/config/acustom4vics.json`.
- Does not bundle or modify Vic's Point Blank.

## Build
Requires JDK 21. GitHub Actions builds the project automatically on push and can also be started manually from the Actions tab.

For a local build, use Gradle 8.8+ and run `gradle build`.
The output will be `build/libs/ACustom4Vics-1.0.0.jar`.

Install that output in `.minecraft/mods` alongside Fabric API and Vic's Point Blank.

## Compatibility note
The mod detects Vic's Point Blank's `GunItem` class by its runtime class name, so the Point Blank JAR is not required as a compile-time dependency. This is an initial implementation scaffold and still needs in-game verification against the target modpack.
