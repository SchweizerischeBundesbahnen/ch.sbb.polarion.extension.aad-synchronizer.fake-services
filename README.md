# Fake Services for AAD Synchronizer

This extension allows to test AAD Synchronizer on local Polarion without need to have Azure AD environment.

> [!IMPORTANT]
> Starting from version 2.0.0 only latest version of Polarion is supported.
> Right now it is Polarion 2410.

## Quick start

The latest version of the extension can be downloaded from the [releases page](../../releases/latest) and installed to Polarion instance without necessity to be compiled from the sources.
The extension should be copied to `<polarion_home>/polarion/extensions/ch.sbb.polarion.extension.aad-synchronizer.fake-services/eclipse/plugins` and changes will take effect after Polarion restart.
> [!IMPORTANT]
> Don't forget to clear `<polarion_home>/data/workspace/.config` folder after extension installation/update to make it work properly.

## Build

This extension can be produced using maven:
```bash
mvn clean package
```

## Installation to Polarion

To install the extension to Polarion `ch.sbb.polarion.extension.aad-synchronizer-fake-services-<version>.jar`
should be copied to `<polarion_home>/polarion/extensions/ch.sbb.polarion.extension.aad-synchronizer-fake-services/eclipse/plugins`
It can be done manually or automated using maven build:
```bash
mvn clean install -P install-to-local-polarion
```
For automated installation with maven env variable `POLARION_HOME` should be defined and point to folder where Polarion is installed.

Changes only take effect after restart of Polarion.

## Polarion configuration

No explicit configuration is needed. If extension is installed, it will be used by AAD Synchronizer.

## Changelog

| Version | Changes                                              |
|---------|------------------------------------------------------|
| v2.0.0  | Polarion 2410 support                                |
| v1.1.0  | About page help now is generating based on README.md |
| v1.0.0  | Initial release                                      |
