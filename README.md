# Fake Services for AAD Synchronizer

This extension allows to test AAD Synchronizer without being in SSB infrastructure

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

## Changelog

| Version | Changes                                              |
|---------|------------------------------------------------------|
| v1.1.0  | About page help now is generating based on README.md |
| v1.0.0  | Initial release                                      |
