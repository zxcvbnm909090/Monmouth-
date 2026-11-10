# MAHMOUD VPN — starter Android project

This is a starter Android app with a black/purple interface and protocol selector.

## Build
GitHub Actions runs `gradle assembleDebug` and uploads the APK as an artifact.

## Important
The Connect button is a UI demonstration only. It does **not** create a VPN tunnel.
Real SSH/VLESS/VMess/WireGuard support requires integrating and testing suitable client
libraries and importing sanitized server profiles. Never commit passwords, private keys,
tokens, or unredacted VPN configuration files to a public repository.
