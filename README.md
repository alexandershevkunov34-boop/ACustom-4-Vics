# ACustom 4 Vic's

## 1.0.1 crash fix

The supplied crash log showed `ClassCastException: Float cannot be cast to Double` in `CallbackInfoReturnable.getReturnValueD()` during `GameRenderer#getFov`.

`GameRendererMixin` declared `CallbackInfoReturnable<Float>` even though the target method returns `double`. The 1.0.1 binary patch changes the callback generic type to `Double`, converts the configured float FOV to double, and boxes it with `Double.valueOf`.

The patched JAR is available in the ChatGPT conversation attachment. It passed ZIP integrity and bytecode inspection; it has not yet been tested in a live Minecraft session.